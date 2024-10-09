package com.example;

import jakarta.persistence.*;

// Base class for all vehicles
    @Entity
    @Table(name = "vehicles")
    @Inheritance(strategy = InheritanceType.JOINED) // Joined Table Inheritance strategy
    public class Vehicle {
    /*---creating a primary key use @Id annotation----*/
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key
        private Long id; // Unique identifier for each vehicle

        @Column(name = "license_plate_number", nullable = false, unique = true)
        private String licensePlateNumber; // License plate number of the vehicle

        @Column(name = "manufacturer", nullable = false)
        private String manufacturer; // Manufacturer of the vehicle

        // Constructor
        public Vehicle() {
        }

        // Parameterized constructor
        public Vehicle(String licensePlateNumber, String manufacturer) {
            this.licensePlateNumber = licensePlateNumber;
            this.manufacturer = manufacturer;
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLicensePlateNumber() {
            return licensePlateNumber;
        }

        public void setLicensePlateNumber(String licensePlateNumber) {
            this.licensePlateNumber = licensePlateNumber;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }
    }

