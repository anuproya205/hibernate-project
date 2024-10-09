package com.example;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class VehicleManagement {

    public static void main(String[] args) {
        insertVehicles(); // Call method to insert vehicle data
        retrieveVehicles(); // Call method to retrieve vehicle data
    }

    // Method to insert vehicle data into the database
    public static void insertVehicles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Creating instances of Car, Bike, and Truck
            Car car = new Car("CAR123", "Toyota", 4);
            Bike bike = new Bike("BIKE456", "Yamaha", 150);
            Truck truck = new Truck("TRUCK789", "Ford", 10000);

            // Saving vehicles to the database
            session.save(car);
            session.save(bike);
            session.save(truck);

            transaction.commit(); // Commit the transaction
            System.out.println("Vehicles inserted successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback in case of an error
            e.printStackTrace();
        } finally {
            session.close(); // Close the session
        }
    }

    // Method to retrieve and display vehicle data from the database
    public static void retrieveVehicles() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Query to retrieve all vehicles
            Query<Vehicle> query = session.createQuery("from Vehicle", Vehicle.class);
            List<Vehicle> vehicles = query.list();

            // Iterate through the list of vehicles and display their details
            for (Vehicle vehicle : vehicles) {
                if (vehicle instanceof Car) {
                    Car car = (Car) vehicle;
                    System.out.println("Car ID: " + car.getId() + ", License Plate: " + car.getLicensePlateNumber() +
                            ", Manufacturer: " + car.getManufacturer() + ", Number of Doors: " + car.getNumberOfDoors());
                } else if (vehicle instanceof Bike) {
                    Bike bike = (Bike) vehicle;
                    System.out.println("Bike ID: " + bike.getId() + ", License Plate: " + bike.getLicensePlateNumber() +
                            ", Manufacturer: " + bike.getManufacturer() + ", Engine Capacity: " + bike.getEngineCapacity());
                } else if (vehicle instanceof Truck) {
                    Truck truck = (Truck) vehicle;
                    System.out.println("Truck ID: " + truck.getId() + ", License Plate: " + truck.getLicensePlateNumber() +
                            ", Manufacturer: " + truck.getManufacturer() + ", Load Capacity: " + truck.getLoadCapacity());
                }
            }
        } finally {
            session.close(); // Close the session
        }
    }
}

