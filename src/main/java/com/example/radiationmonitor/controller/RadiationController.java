package com.example.radiationmonitor.controller;

import com.example.radiationmonitor.model.RadiationReading;
import com.example.radiationmonitor.repository.RadiationReadingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RadiationController {

    @Autowired
    private RadiationReadingRepository radiationReadingRepository;

    /**
     * Endpoint to get the latest radiation readings.
     * @param count The number of readings to return. Defaults to 50 if not provided.
     * Example: /api/v1/radiation/latest?count=10
     * @return A list of RadiationReading objects.
     */
    @GetMapping("/radiation/latest")
    public List<RadiationReading> getLatestReadings(
        @RequestParam(defaultValue = "50") int count
    ) {
        // PageRequest is used to limit the number of results from the database.
        Pageable pageable = PageRequest.of(0, count);
        return radiationReadingRepository.findAllByOrderByTimestampDesc(
            pageable
        );
    }
}
