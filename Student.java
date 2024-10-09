package com.example;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//public class Student
@Entity
    @Table(name = "Student")
    public class Student {
    /*---creating a primary key use @Id annotation----*/
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "course")
        private String course;

        public Student() {
        }
    /*---- Parameterised Constructor ------*/
        public Student(String name, String course) {
            this.name = name;
            this.course = course;
        }

        // Getters and setters for id, name, and course
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        @Override
        public String toString() {
            return "Student [id=" + id + ", name=" + name + ", course=" + course + "]";
        }

    static class HibernateUtils
    {
        private static SessionFactory sessionFactory;
        static
        {
            try {
                /*--- create Object of Configuration class -----*/
                Configuration config=new Configuration();
                /*---- Setting the properties in configuration ----*/
                /*--- Setting the name of driver_class -----*/
                config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                /*--- Setting connection url -----*/
                config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/EmployeeDB");
                /*---- setting username of mysql ----*/
                config.setProperty("hibernate.connection.username","root");
                /*---- setting the password of database ----*/
                config.setProperty("hibernate.connection.password","Mysql_password@123");
                /*---- Setting how hibernate handles schema generation ----*/
                config.setProperty("hibernate.hbm2ddl.auto", "update");
                /*--- setting property to show sql queries being executed ----*/
                config.setProperty("hibernate.show_sql","true");
                /*---- setting queries to be displayed formatted ---*/
                config.setProperty("hibernate.format_sql","true");
                /*--------------------------------------------------------*/
                /*---- Add annotated entities here -----*/
                config.addAnnotatedClass(Employeess.class);
                /*-----------------------------------------------------------------------------*/
                /*--- Creating serviceRegistry class object to register the configuration ----*/
                ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                /*---- Initializing sessionfactory -----*/
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            }
            catch(Throwable tw)
            {
                System.err.println("Unable to create session Factory : "+tw);
                throw new ExceptionInInitializerError(tw);
            }
        }
        /*---- method to return session factory object -----*/
        public static SessionFactory getSessionFactory()
        {
            return sessionFactory;
        }
    }
            static class StudentFetch{
                public static void main(String[] args) {
                    Session session = HibernateUtils.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    // Fetch student with ID 5 (assuming this ID might not exist)
                    int studentId = 5;

                    // The get() method returns null if the student with the given ID doesn't exist
                    Student student = session.get(Student.class, studentId);

                    // Check if the student is null (i.e., not found in the database)
                    if (student == null) {
                        System.out.println("Student with ID " + studentId + " not found.");
                    } else {
                        System.out.println("Student found: " + student);
                    }

                    transaction.commit();
                    session.close();
                }
            }

        }






