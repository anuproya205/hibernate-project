package com.example;
import jakarta.persistence.*;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {

    @Column(name = "load_capacity", nullable = false)
    private int loadCapacity; // Load capacity of the truck

    // Default constructor
    public Truck() {
    }

    // Parameterized constructor
    public Truck(String licensePlateNumber, String manufacturer, int loadCapacity) {
        super(licensePlateNumber, manufacturer); // Call parent constructor
        this.loadCapacity = loadCapacity;
    }

    // Getters and Setters
    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
