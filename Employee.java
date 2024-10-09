package com.example;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//public class Employee
@Entity
@Table(name = "Employee")
public class Employee {
    /*---creating a primary key use @Id annotation----*/
    @Id
    @Column(name = "id")
    private int id;
    //to set column properties use @column annotation
    @Column(name = "name")
    private String name;

    @Column(name = "designation")
    private String designation;

    @Column(name = "salary")
    private double salary;

    /*---- Parameterised Constructor ------*/
    public Employee( int id,String name, String designation, double salary) {
        super();
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    //----- Default Constructor -----
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*----- Getter and Setter methods ------*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){this.name = name;}
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + "," +
                " name=" + name + "," +
                " designation=" + designation + ", " +
                "salary=" + salary + "]";
    }

    // HibernateUtils class
    static class HibernateUtils {
        private static SessionFactory sessionFactory;
        static {
            try {
                /*--- create Object of Configuration class -----*/
                Configuration config = new Configuration();
                /*---- Setting the properties in configuration ----*/
                /*--- Setting the name of driver_class -----*/
                config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                /*--- Setting connection url -----*/
                config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/EmployeeDB");
                /*---- setting username of mysql ----*/
                config.setProperty("hibernate.connection.username", "root");
                /*---- setting the password of database ----*/
                config.setProperty("hibernate.connection.password", "Mysql_password@123");
                /*---- Setting how hibernate handles schema generation ----*/
                config.setProperty("hibernate.hbm2ddl.auto", "update");
                /*--- setting property to show sql queries being executed ----*/
                config.setProperty("hibernate.show_sql", "true");
                /*---- setting queries to be displayed formatted ---*/
                config.setProperty("hibernate.format_sql", "true");
                /*--------------------------------------------------------*/
                /*---- Add annotated entities here -----*/
                config.addAnnotatedClass(Employee.class);
                // Creating serviceRegistry class object to register the configuration
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                // Initializing session factory
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Throwable tw) {
                System.err.println("Unable to create session Factory : " + tw);
                throw new ExceptionInInitializerError(tw);
            }
        }

        /*---- method to return session factory object -----*/
        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
    }
    // EmployeeCreations class
    static class EmployeeCreations {
        public static void main(String[] args) {
            /*--- Creating session object from sessionFacory ----*/
          Session session = HibernateUtils.getSessionFactory().openSession();
            /*----------------------------------------------------------------*/
            /*---- Creating Transaction object ------*/
            Transaction transaction = session.beginTransaction();
            /*---- Creating object of Employee entity -----*/
            //Transient State
            Employee emp1 = new Employee( 0,"Anil kumar", "Manager", 10000);
            Employee emp2 = new Employee( 0,"Anupriya", "senior developer", 200000);
            Employee emp3 = new Employee( 0,"Suman", "HR", 140000);
            Employee emp4 = new Employee(0,"Harini", "developer", 7000);
            // Storing employee into database
            session.persist(emp1);
            session.persist(emp2);
            session.persist(emp3);
            session.persist(emp4);
            //commit the transaction
            transaction.commit();
            System.out.println("----- Employee inserted ------");
            //  Fetching employee from database using session.get() method
            Employee retrievedEmployee = session.get(Employee.class, 2);
            if (retrievedEmployee != null) {
                System.out.println("Employee found: " + retrievedEmployee);
            } else {
                System.out.println("Employee not found.");
            }

            // Close the session
            session.close();
        }
    }
}




