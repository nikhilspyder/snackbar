package com.ncr.snackbar.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.ncr.snackbar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public class Order {
	@Autowired
	private Employee employee;
	
	@Autowired 
	
		   private long orderId;
		   private String employeeId;
		   private String orderStatus;
		   private List<Item> item=new LinkedList<Item>();
		 
		   public long getOrderId() {
		       return orderId;
		   }
		 
		   public void setOrderId(long orderId) {
		       this.orderId = orderId;
		   }
		   public String getEmployeeId() {
		       return employeeId;
		   }
		 
		   public void setEmployeeIds(Employee employee) {
		       this.employeeId = employee.getQuickLookId();
			
		   }
		   public void setEmployeeId(String employeeId) {
		       this.employeeId = employeeId;
			
		   }
		   public String getOrderStatus() {
		       return orderStatus;
		   }
		   public void setOrderStatus(String orderStatus) {
		       this.orderStatus = orderStatus;
		   }
		   public List<Item> getItem() {
				return item;
			}

		   	public void setItem(List<Item> item)
		   	{
		   		this.item=item;
		   	}
		   	
		   	public void setItemList(Item itemObject) {
				item.add(itemObject);	
			}
		   	
		   @Override
		   public String toString() {
		       return "Order{" +
		               "orderId=" + orderId +
		               ", employeeId='" + employeeId + '\'' +
		               ",orderStatus='" + orderStatus + '\'' +
		               '}';
		   }
		   public static Order create(Order order) {
		      // Person person = new Person();
		       /*person.setFirstName(p.firstName);
		       person.setLastName(p.lastName);
		       person.setAddress(p.address);*/
		       return order;
		   }
		
}
