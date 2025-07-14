package com.example.radiationmonitor.service;

import com.example.radiationmonitor.model.RadiationReading;
import com.example.radiationmonitor.repository.RadiationReadingRepository;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RadiationDataGenerator {

    // Spring will inject the repository so it can be used to talk to the DB.
    @Autowired
    private RadiationReadingRepository radiationReadingRepository;

    private double lastValue = -1.0;
    private static final double MAX_VALUE = 1000.0;
    private static final double MIN_VALUE = 0.0;
    private static final double MAX_CHANGE = 100.0;

    /**
     * This method is scheduled to run automatically.
     * fixedRate = 100 means it will run every 100 milliseconds.
     * As per our design doc, it generates 10 values per run.
     */
    @Scheduled(fixedRate = 100)
    public void generateAndStoreReadings() {
        for (int i = 0; i < 10; i++) {
            double newValue = generateNextValue();
            RadiationReading reading = new RadiationReading(
                newValue,
                new Date()
            );
            radiationReadingRepository.save(reading);
        }
    }

    /**
     * The core logic for generating a single value.
     * This method is private as it's only used internally by the scheduled task.
     * @return A new radiation value.
     */
    private double generateNextValue() {
        if (lastValue < 0) {
            // Initialize with a random value if it's the first run.
            lastValue = ThreadLocalRandom.current().nextDouble(
                MIN_VALUE,
                MAX_VALUE
            );
        } else {
            // Generate the next value based on the last one.
            double change = ThreadLocalRandom.current().nextDouble(
                -MAX_CHANGE,
                MAX_CHANGE
            );
            lastValue += change;
        }

        // Clamp the value to be within the allowed range [0, 1000].
        lastValue = Math.max(MIN_VALUE, Math.min(lastValue, MAX_VALUE));
        return lastValue;
    }
}
