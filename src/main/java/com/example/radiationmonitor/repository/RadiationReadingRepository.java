package com.example.radiationmonitor.repository;

import com.example.radiationmonitor.model.RadiationReading;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for RadiationReading documents.
 * By extending MongoRepository, we get standard database methods from MongoDB like:
 * (save, findAll, findById, etc.).
 */
public interface RadiationReadingRepository
    extends MongoRepository<RadiationReading, String> {
    /**
     * A custom method to find the most recent readings. Spring Data automatically
     * implements this method based on its name. It finds all readings, orders them
     * by timestamp in descending order, and returns the number of results specified
     * by the Pageable object.
     * When we call findAllByOrderByTimestampDesc(), the Spring-generated proxy object intercepts this call,
     * it translates the method name into a query for MongoDB, executes it, and then returns the result
     *
     * @param pageable Contains information about how many results to return.
     * @return A list of the most recent radiation readings.
     */
    List<RadiationReading> findAllByOrderByTimestampDesc(Pageable pageable);
}
