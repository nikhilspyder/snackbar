package com.ncr.snackbar.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ncr.snackbar.dao.EmployeeDao;
import com.ncr.snackbar.model.Employee;
import com.ncr.snackbar.model.Order;
import com.ncr.snackbar.service.EmployeeService;
@Component
@RestController

public class EmployeeController {
    
    @Autowired
        EmployeeService employeeService;
    @Autowired
    Employee emp;
    @Autowired
    EmployeeDao employeeDao;
       @RequestMapping(path="/display_all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
       public List<Employee> displayAllEmployee()
       {
              return employeeService.displayAll();
           
       }
       
       @RequestMapping(path="/display_by_id", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.TEXT_PLAIN_VALUE)
       public Employee displayEmployeeById(@RequestBody String id)
       {
           return employeeService.displayById(id);
       }
       
       /*@RequestMapping(path="/validate_user", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
       public boolean validateUser(@RequestBody ObjectNode objectNode)
       {
           
           String id = objectNode.get("quickLookId").asText();
           String password = objectNode.get("password").asText();
           return employeeService.validateEmployee(id,password);
       }*/
       
       @CrossOrigin(origins="*")
       @RequestMapping(path="/save", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public Employee createNewEmployee(@RequestBody Employee employee)
       {
             // Employee e = Employee.create(employee);
              return employeeService.createEmployee(employee);
           
       }
       
    
       @RequestMapping(path="/get_email", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public String getEmail(@RequestBody ObjectNode objectNode)
       {
           String id = objectNode.get("quickLookId").asText();
           //String email = objectNode.get("email").asText();
           return employeeService.getEmail(id);
               
       }
       
       @RequestMapping(path="/update_password", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public boolean updatePasswordById(@RequestBody ObjectNode objectNode)
       {
           String id = objectNode.get("quickLookId").asText();
           String password = objectNode.get("password").asText();
           return employeeService.updatePasswordById(id,password);
               
       }
       @RequestMapping(path="/verify_email", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public boolean verifyEmail(@RequestBody ObjectNode objectNode)
       {
           String id = objectNode.get("quickLookId").asText();
           String email = objectNode.get("email").asText();
           return employeeService.verifyEmail(id,email);
               
       }
       
       @RequestMapping(path="/update_email", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public boolean updateEmail(@RequestBody ObjectNode objectNode)
       {
           String id = objectNode.get("quickLookId").asText();
           String email = objectNode.get("email").asText();
           return employeeService.updateEmailById(id,email);
               
       }
       
       @RequestMapping(path="/update_name", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public boolean updateName(@RequestBody ObjectNode objectNode)
       {
           String id = objectNode.get("quickLookId").asText();
           String name = objectNode.get("name").asText();
           return employeeService.updateNameById(id,name);
               
       }
       
       @RequestMapping(path="/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
       public boolean delete(@RequestBody String id)
       {
           return employeeService.deleteById(id);
               
       }
       
       
       
       

}
