package com.example.radiationmonitor.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.radiationmonitor.model.RadiationReading;
import com.example.radiationmonitor.repository.RadiationReadingRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @SpringBootTest loads the full application context for an integration test.
 * @AutoConfigureMockMvc provides the MockMvc instance for testing the web layer.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RadiationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * @MockBean still creates a mock of the repository, replacing the real one
     * in the application context for this test.
     */
    @MockBean
    private RadiationReadingRepository radiationReadingRepository;

    @Test
    void whenGetLatestReadings_thenReturnsJsonArray() throws Exception {
        // Given: We create some fake data
        RadiationReading reading1 = new RadiationReading(100.0, new Date());
        RadiationReading reading2 = new RadiationReading(150.0, new Date());
        List<RadiationReading> readings = Arrays.asList(reading1, reading2);

        // When: The repository's method is called, we tell it to return our fake data
        when(
            radiationReadingRepository.findAllByOrderByTimestampDesc(
                any(PageRequest.class)
            )
        ).thenReturn(readings);

        // Then: We perform a fake HTTP GET request and check the results
        mockMvc
            .perform(get("/api/v1/radiation/latest?count=2"))
            .andExpect(status().isOk()) // Expect HTTP 200 OK
            .andExpect(jsonPath("$", hasSize(2))) // Expect the JSON array to have 2 elements
            .andExpect(jsonPath("$[0].value").value(100.0)); // Expect the first element's value to be 100.0
    }
}
