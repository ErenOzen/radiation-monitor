package com.example.radiationmonitor.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a single radiation reading document in the MongoDB database.
 * The @Document annotation specifies the collection name.
 */
@Document(collection = "radiation_readings")
public class RadiationReading {

    @Id
    private String id; // MongoDB will automatically generate a unique ID for this field.

    private double value; // The radiation value in Gray (Gy)
    private Date timestamp; // The time the reading was taken

    // Constructors
    public RadiationReading() {}

    public RadiationReading(double value, Date timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    // Getters and Setters are required for Spring.
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
