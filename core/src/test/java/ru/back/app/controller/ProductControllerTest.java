package ru.back.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.back.app.dto.BrandDto;
import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import ru.back.app.service.ProductService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Spy
    private ProductService service = mock(ProductService.class);

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        ProductDto productDto1 = new ProductDto();
        productDto1.setId(2l);
        ProductDto productDto2 = new ProductDto();
        productDto2.setId(5l);

        when(service.getAllProducts()).thenReturn(List.of(productDto1, productDto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(productDto1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(productDto2.getId()));
    }

    @Test
    public void testGetProductById() throws Exception {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);
        productDto.setName("Vintage Denim Jacket");
        productDto.setPrice(59.99);
        productDto.setBrand(new BrandDto());

        when(service.getProductById(productId)).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/id/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(productDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(productDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(productDto.getBrand()));
    }

    @Test
    public void testGetProductByName() throws Exception {
        String productName = "Vintage Denim Jacket";
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName(productName);
        productDto.setPrice(59.99);
        productDto.setBrand(new BrandDto());

        when(service.getProductByName(productName)).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/name?name={name}", productName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(productDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(productDto.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(productDto.getBrand()));
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductCreateDto product = new ProductCreateDto();
        product.setName("Vintage Denim Jacket");
        product.setPrice(59.99);
        product.setBrandId(1L);

        ProductDto productResponse = ProductDto.builder()
                .id(1L)
                .name("Vintage Denim Jacket")
                .price(59.99)
                .brand(BrandDto.builder()
                        .id(1L)
                        .build())
                .build();

        when(service.createProduct(product)).thenReturn(productResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/create")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(product))
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productResponse.getId()));

        verify(service, times(1)).createProduct(product);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Vintage Denim Jacket");
        productDto.setPrice(59.99);
        productDto.setBrand(new BrandDto());

        when(service.updateProduct(productDto)).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/update")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productDto))
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productDto.getId()));

        verify(service, times(1)).updateProduct(productDto);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);

        doNothing().when(service).deleteProduct(productId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete/{id}", productId))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteProduct(1l);
    }
}
