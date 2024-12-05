package ru.back.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class BrandControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        BrandCreateDto brandCreateDto = new BrandCreateDto();
        brandCreateDto.setName("TestBrand");
        brandCreateDto.setDescription("This is a test brand.");

        mockMvc.perform(post("/brands/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandCreateDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBrands() throws Exception {
        mockMvc.perform(get("/brands"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetBrandById() throws Exception {
        Long brandId = 1L;
        mockMvc.perform(get("/brands/id/{id}", brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(brandId));
    }

    @Test
    void testGetBrandByName() throws Exception {
        String brandName = "TestBrand";
        mockMvc.perform(get("/brands/name").param("name", brandName))
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
        brandUpdateDto.setId(1L);
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
        Long brandId = 1L;

        mockMvc.perform(delete("/brands/delete/{id}", brandId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/brands/id/{id}", brandId))
                .andExpect(status().isNotFound());
    }
}