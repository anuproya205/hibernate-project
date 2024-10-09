package com.example;
import jakarta.persistence.*;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle {

    @Column(name = "engine_capacity", nullable = false)
    private int engineCapacity; // Engine capacity of the bike

    // Default constructor
    public Bike() {
    }

    // Parameterized constructor
    public Bike(String licensePlateNumber, String manufacturer, int engineCapacity) {
        super(licensePlateNumber, manufacturer); // Call parent constructor
        this.engineCapacity = engineCapacity;
    }

    // Getters and Setters
    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}

