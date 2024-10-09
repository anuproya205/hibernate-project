package com.example;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    @Column(name = "number_of_doors", nullable = false)
    private int numberOfDoors; // Number of doors in the car

    // Default constructor
    public Car() {
    }

    // Parameterized constructor
    public Car(String licensePlateNumber, String manufacturer, int numberOfDoors) {
        super(licensePlateNumber, manufacturer); // Call parent constructor
        this.numberOfDoors = numberOfDoors;
    }

    // Getters and Setters
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}

