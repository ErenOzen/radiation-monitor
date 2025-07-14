package com.example.radiationmonitor.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.radiationmonitor.repository.RadiationReadingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(properties = "spring.scheduling.enabled=false")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RadiationDataGeneratorTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(
        "mongo:latest"
    );

    @Autowired
    private RadiationDataGenerator radiationDataGenerator;

    @Autowired
    private RadiationReadingRepository radiationReadingRepository;

    @Test
    void whenGenerateAndStoreIsCalled_then10ReadingsAreSaved() {
        // Given: The database is empty because the context has been reset
        assertThat(radiationReadingRepository.count()).isEqualTo(0);

        // When: We manually call the scheduled method once
        radiationDataGenerator.generateAndStoreReadings();

        // Then: Exactly 10 readings should have been saved to the database
        assertThat(radiationReadingRepository.count()).isEqualTo(10);
    }
}
