package com.ncr.snackbar.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ncr.snackbar.dao.EmployeeDaoInterface;
import com.ncr.snackbar.model.Employee;
import com.ncr.snackbar.model.Order;

@Service
public class EmployeeService {
    
     @Autowired
     EmployeeDaoInterface<Employee> employeeDao;
     
     @Autowired
     Employee emp;
    
       public List<Employee> displayAll()
       {
           List<Employee> list2 = employeeDao.loadAll();
           return list2;
       }
       
       public Employee displayById(String id)
       {
           Employee e = employeeDao.load(id);
           return e;
       }
       
       public Employee createEmployee(Employee employee)
       {
              //Employee e = Employee.create(employee);
              employeeDao.save(employee);
              return employee;
       }
       
       public String getEmail(String id)
       {
           return employeeDao.getEmail(id);
       }
       
       public boolean verifyEmail(String id,String email)
       {
           return employeeDao.verifyEmail(id,email);
       }
       public boolean updatePasswordById(String id, String password)
       {
           return employeeDao.updatePassword(id,password);
               
       }
       
       public boolean updateEmailById(String id, String email)
       {
           return employeeDao.updateEmail(id,email);
               
       }
       public boolean validateEmployee(String id, String password)
       {
           return employeeDao.validate(id,password);
               
       }
       
       
       
       public boolean updateNameById(String id,String name)
       {
           return employeeDao.updateName(id,name);
               
       }
       public boolean deleteById(String id)
       {
           return employeeDao.delete(id);
               
       }
       
       

      
}