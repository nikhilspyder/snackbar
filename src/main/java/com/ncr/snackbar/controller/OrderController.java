package com.ncr.snackbar.controller;

import java.util.List;
import com.ncr.snackbar.service.EmployeeService;
import com.ncr.snackbar.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ncr.snackbar.dao.OrderDaoInterface;
import com.ncr.snackbar.model.Employee;
import com.ncr.snackbar.model.Order;
@RestController
public class OrderController {
	@Autowired
	 EmployeeService employeeService;
	@Autowired
    OrderDaoInterface<Order> orderDao;
	 @Autowired
	 ItemService itemService;
	
   Order order = new Order();
  
   @CrossOrigin(origins="*")
   @RequestMapping(path="/order/LoadAllOrders", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrderDetails()
    {
        return orderDao.loadAll();
    }
   @CrossOrigin(origins="*")
   @RequestMapping(path="/order/LoadRunningOrders", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
   public List<Order> getRunningOrders()
   {
	   return orderDao.loadRunningOrders();
   }
   
   @CrossOrigin(origins="*")
   @RequestMapping(path="/order/LoadAllOrdersMap", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
   public List<Order> getAllOrdersMap()
   {
        return orderDao.loadAllOrdersMap();
   }
   	
   	@CrossOrigin(origins="*")
    @RequestMapping(path="/validate_user", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    public String validateUser(@RequestBody Employee employee)
    {
        
        String id = employee.getQuickLookId();
        String password = employee.getPassword();
        if(employeeService.validateEmployee(id,password)) {
        order.setEmployeeIds(employee);
        
        return "success"; 
        }
        return "fail";
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(path="/order/PostOrderItem", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public Order getItemQuantity(@RequestBody Order orderObject)
    {
    	orderDao.save(order);
    	Order orders=orderDao.saveOrderItem(orderObject);
    	itemService.quantityDecrease(orders);
        return orders;
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(path="/order/LoadParticularOrder", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getParticularOrder(@RequestParam long orderId)
    {
      return orderDao.loadParticularOrder(orderId) ;
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(path="/order/LoadRunningOrdersMap", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getRunningOrdersMap()
    {
         return orderDao.loadRunningOrdersMap();
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(path="/order/UpdateOrderStatus", method=RequestMethod.PUT, consumes=MediaType.TEXT_PLAIN_VALUE)
    public void updateOrderStatus(@RequestBody String OrderId)
    {
        //order.employeeId=order1.getEmployeeId();
        orderDao.updateOrder(OrderId);
        /*Order orders=orderDao.saveOrderItem(orderObject);
      return orders;*/
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(path="/LoadRunningOrders", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getRunningOrdersforEmployee()
    {	
        return orderDao.loadAllOrders(order.getEmployeeId());
 	   //return employeeService.LOrders();
    }
    
}