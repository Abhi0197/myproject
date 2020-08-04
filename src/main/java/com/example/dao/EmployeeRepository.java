package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee
        , Integer> {

}