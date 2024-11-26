package ru.back.app.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class BrandControllerIntegrationTest {@Autowired
private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllBrands() throws Exception {
        mockMvc.perform(get("/brands"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetBrandById() throws Exception {
        Long brandId = 1L; // Make sure this ID exists in your test database
        mockMvc.perform(get("/brands/{id}", brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(brandId));
    }

    @Test
    void testGetBrandByName() throws Exception {
        String brandName = "TestBrand"; // Replace with a valid brand name in your test data
        mockMvc.perform(get("/brands/{name}", brandName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(brandName));
    }

    @Test
    void testCreateBrand() throws Exception {
        BrandCreateDto brandCreateDto = new BrandCreateDto();
        brandCreateDto.setName("NewBrand");
        brandCreateDto.setDescription("This is a new brand.");

        mockMvc.perform(post("/brands/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandCreateDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("NewBrand"))
                .andExpect(jsonPath("$.description").value("This is a new brand."));
    }

    @Test
    void testUpdateBrand() throws Exception {
        BrandDto brandUpdateDto = new BrandDto();
        brandUpdateDto.setId(1L); // Use a valid ID from your test data
        brandUpdateDto.setName("UpdatedBrand");
        brandUpdateDto.setDescription("Updated description");

        mockMvc.perform(put("/brands/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("UpdatedBrand"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    void testDeleteBrand() throws Exception {
        Long brandId = 1L; // Use a valid ID from your test data

        mockMvc.perform(delete("/brands/delete/{id}", brandId))
                .andExpect(status().isOk());
    }
}