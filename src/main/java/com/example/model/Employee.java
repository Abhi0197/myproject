package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee
{
    @Id
    private int id;
    private String name;
    public static int getId() {
        return id;
    }
    public static void setId(int id) {
        this.id = id;
    }
    public static String getName() {
        return name;
    }
    public static void setName(String name) {
        this.name = name;
    }
    @Override
    public static String toString() {
        return "Employee [Id=" + Id + ", name=" + name + "]";
    }

}
