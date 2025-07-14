package com.example.radiationmonitor.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.radiationmonitor.model.RadiationReading;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RadiationReadingRepositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(
        "mongo:latest"
    );

    @Autowired
    private RadiationReadingRepository radiationReadingRepository;

    @Test
    void shouldSaveAndFindRadiationReading() {
        RadiationReading reading = new RadiationReading(450.5, new Date());
        RadiationReading savedReading = radiationReadingRepository.save(
            reading
        );
        Optional<RadiationReading> foundReadingOpt =
            radiationReadingRepository.findById(savedReading.getId());
        assertThat(foundReadingOpt).isPresent();
        assertThat(foundReadingOpt.get().getValue()).isEqualTo(450.5);
        assertThat(foundReadingOpt.get().getId()).isNotNull();
    }
}
