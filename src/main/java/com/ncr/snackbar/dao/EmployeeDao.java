
package com.ncr.snackbar.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ncr.snackbar.model.Employee;
import com.ncr.snackbar.model.Item;
import com.ncr.snackbar.model.Order;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class EmployeeDao implements EmployeeDaoInterface<Employee> {
    
    
   @Autowired
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   
   @PostConstruct
   private void postConstruct() {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }
   
   public boolean validate(String id, String password)
   {
	   
	   List<Employee> employee = jdbcTemplate.query("select * from USP_DEV.Employee where EMP_ID =?",
               new String[]{id}, (resultSet, i) -> {
                   return toEmployee(resultSet);
               });
       if (employee.size() == 1) {
           
           Employee e=employee.get(0);
        // return "e.password="+e.getPassword()+"password="+password;
       
          if(e.getPassword().equals(password)) {
        	  
        	  
        	  return true;
          }
           
      
       }
       return false;
   }
      
   
  @Override
   public boolean save(Employee employee) {
       String sql = "insert into USP_DEV.Employee values ('"+employee.getQuickLookId()+"','"+employee.getName()+"','"+employee.getPassword()+"','"+ employee.getEmail()+"','"+employee.getRole()+"')";
       int result=jdbcTemplate.update(sql);
       return((result>0)?true:false);
   }
  
  
   @Override
   public Employee load(String id) {
       List<Employee> employee = jdbcTemplate.query("select * from USP_DEV.Employee where EMP_ID =?",
               new String[]{id}, (resultSet, i) -> {
                   return toEmployee(resultSet);
               });
       if (employee.size() == 1) {
           return employee.get(0);
       }
       return null;
   }
   
   
   
   
   @Override
   public boolean delete(String id) {
       int result=jdbcTemplate.update("delete from USP_DEV.Employee where id = ?", id);
       return((result>0)?true:false);
   }
   
   @Override
   public boolean updatePassword(String id,String password) {
       String query="update  USP_DEV.Employee set EMP_PASSWORD='"+password+"'where EMP_ID='"+id+"'";  
             int result= jdbcTemplate.update(query);  
             return((result>0)?true:false);
   }
 
   @Override
   public boolean updateEmail(String id,String email) {
       String query="update  USP_DEV.Employee set EMP_EMAIL='"+email+"'where EMP_ID='"+id+"'";  
       int result=  jdbcTemplate.update(query);  
       return((result>0)?true:false);
   }
 
   @Override
   public boolean updateName(String id,String name) {
       String query="update  USP_DEV.Employee set EMP_NAME='"+name+"'where EMP_ID='"+id+"'";  
       int result= jdbcTemplate.update(query);  
       return((result>0)?true:false);
   }
  
   @Override
   public List<Employee> loadAll() {
       return jdbcTemplate.query("select * from USP_DEV.Employee", (resultSet, i) -> {
           return toEmployee(resultSet);
       });
   }
   
   
   private Employee toEmployee(ResultSet resultSet) throws SQLException {
       Employee employee = new Employee();
       employee.setQuickLookId(resultSet.getString("EMP_ID"));
       employee.setName(resultSet.getString("EMP_NAME"));
       employee.setPassword(resultSet.getString("EMP_PASSWORD"));
       employee.setEmail(resultSet.getString("EMP_EMAIL"));
       employee.setRole(resultSet.getString("EMP_ROLE"));
       return employee;
   }
@Override
public String getEmail(String id) {
    
    List<Employee> employee = jdbcTemplate.query("select * from USP_DEV.Employee where EMP_ID =?",
            new String[]{id}, (resultSet, i) -> {
                return toEmployee(resultSet);
            });
    if (employee.size() == 1) {
       Employee e=employee.get(0);
       return e.getEmail();
    }
 
    
    return null;
}
public boolean verifyEmail(String id, String email)
{
       List<Employee> employee = jdbcTemplate.query("select * from USP_DEV.Employee where EMP_ID =?",
            new String[]{id}, (resultSet, i) -> {
                return toEmployee(resultSet);
            });
    if (employee.size() == 1) {
       
       Employee e=employee.get(0);
    // return "e.password="+e.getPassword()+"password="+password;
    
      if(e.getEmail().equals(email))
        return true;
   
    }
    return false;
}
  

   
}