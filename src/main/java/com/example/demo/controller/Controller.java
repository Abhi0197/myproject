package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.bean.EmployeeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.model.Employee;

@RestController
public class Controller
{
    @Autowired
    private EmployeeRepository employeeData;
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);


    @GetMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> add(
            @RequestBody EmployeeBean employeeBean) {
        LOGGER.info("This is Log");
        Employee employee = new Employee();
        BeanUtils.copyProperties(employee,employeeBean);
        Employee emp2 = employeeData.save(employee);
        return new ResponseEntity<>("Added Successfully with id:" +
                emp2.getId(),HttpStatus.CREATED);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> get() {
        LOGGER.info("This is Log");
        List<Employee> list1 = employeeData.findAll();

        return new ResponseEntity<>(list1, HttpStatus.OK);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<EmployeeBean> delete(@RequestParam Integer id){

        if (employeeData.existsById(id)) {
            Optional<Employee> employeeOptional = employeeData.findById(id);
            EmployeeBean employeeBean = new EmployeeBean();
            employeeOptional.ifPresent(employee -> BeanUtils.copyProperties(employee,employeeBean));
            employeeData.deleteById(id);
            LOGGER.info("Employee deleted {}", id);
            return new ResponseEntity<>(employeeBean, HttpStatus.OK);
        } else {
            LOGGER.error("User Tries to delete with invalid id {}", id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
