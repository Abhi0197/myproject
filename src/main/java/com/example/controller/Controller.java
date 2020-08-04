package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.EmployeeRepository;
import com.example.model.Employee;

@RestController
public class Controller
{
    @Autowired
    private EmployeeRepository employeeData;
    private static Logger log = LoggerFactory.getLogger(Controller.class);
    @RequestMapping(value = "/save", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public static ResponseEntity<String> add(@RequestBody Employee emp) {
        log.info("This is Log");
        Employee emp2 = employeeData.save(emp);
        return new ResponseEntity<String>("Added Successfully with id:" + emp2.getId(),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public static ResponseEntity<List<Employee>> get() {
        log.info("This is Log");
        List<Employee> list = new ArrayList<Employee>();
        Iterable<Employee> list1 = employeeData.findAll();
        list1.forEach(x->{
            Employee e = new Employee();
            BeanUtils.copyProperties(x, e);
            list.add(e);
        });
        return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
    }
}
