package com.ncr.snackbar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import com.ncr.snackbar.model.Item;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ncr.snackbar.model.Employee;
import com.ncr.snackbar.model.Order;
import com.ncr.snackbar.model.Item;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class OrderDao implements OrderDaoInterface<Order> {
  @Autowired
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   private String EmpId;
   
   enum OrderStatus{
	   In_Progress,Completed;
   }
   
   @PostConstruct
   private void postConstruct() {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }
   @Override
   public void save(Order order) {
	   EmpId=order.getEmployeeId();    
   }
   @Override
   public Order load(long id) {
       List<Order> orders = jdbcTemplate.query("select * from USP_DEV.Orders where id =?",
               new Object[]{id}, (resultSet, i) -> {
                   return toOrder(resultSet);
               });
       if (orders.size() == 1) {
           return orders.get(0);
       }
       return null;
   }
   @Override
   public void delete(long id) {
       jdbcTemplate.update("delete from USP_DEV.Orders where id = ?", id);
   }
   @Override
   public void update(Order order) {
       throw new UnsupportedOperationException();
   }
   @Override
   public List<Order> loadAll() {
       return jdbcTemplate.query("select Orders.ORDER_ID,Orders.EMP_ID,Orders.ORDER_STATUS,Order_Item.ITEM_ID,Items.ITEM_NAME,Order_Item.QUANTITY_ITEM  from USP_DEV.Orders,USP_DEV.Order_Item,USP_DEV.Items where USP_DEV.Orders.ORDER_ID=USP_DEV.Order_Item.ORDER_ID AND USP_DEV.Items.ITEM_ID=USP_DEV.order_item.ITEM_ID", (resultSet, i) -> {return toOrder(resultSet);
       });
   }
   
   public List<Order> loadParticularOrder(long orderId){
       return jdbcTemplate.query("select Orders.ORDER_ID,Orders.EMP_ID,Orders.ORDER_STATUS,Order_Item.ITEM_ID,Items.ITEM_NAME,Order_Item.QUANTITY_ITEM  from USP_DEV.Orders,USP_DEV.Order_Item,USP_DEV.Items where USP_DEV.Orders.ORDER_ID=USP_DEV.Order_Item.ORDER_ID AND USP_DEV.Items.ITEM_ID=USP_DEV.order_item.ITEM_ID AND Orders.ORDER_ID = "+orderId, (resultSet, i) -> {return toOrder(resultSet);
      });
  }
   
/*   private Order toOrder(ResultSet resultSet) throws SQLException {
       Order order = new Order();
       Item itemObject = new Item();
      
       order.setOrderId(resultSet.getLong("ORDER_ID"));
       order.setEmployeeId(resultSet.getString("EMP_ID"));
       order.setOrderStatus(resultSet.getString("ORDER_STATUS"));
      // System.out.println(resultSet.getLong("ITEM_ID"));
       //System.out.println(resultSet.getLong("QUANTITY_ITEM"));
       
       itemObject.setItemId(resultSet.getLong("ITEM_ID"));
       itemObject.setItemName(resultSet.getString("ITEM_NAME"));
       itemObject.setItemQuantity(resultSet.getLong("QUANTITY_ITEM"));
//       System.out.println("Item ID "+itemObject.getItemId());
//       System.out.println("Item Quantity "+itemObject.getItemQuantity());
       
       order.setItemList(itemObject);
      
       return order;
   }*/
   
   @Override
   public List<Order> loadAllOrders(String EmpId) {

	   List<Order> orders=jdbcTemplate.query("select * from(select Orders.ORDER_ID,Orders.EMP_ID,Orders.ORDER_STATUS,Order_Item.ITEM_ID,Items.ITEM_NAME,Order_Item.QUANTITY_ITEM  from USP_DEV.Orders,USP_DEV.Order_Item,USP_DEV.Items where USP_DEV.Orders.ORDER_ID=USP_DEV.Order_Item.ORDER_ID AND USP_DEV.Items.ITEM_ID=USP_DEV.order_item.ITEM_ID AND USP_DEV.Orders.EMP_ID ='"+EmpId+"' ORDER BY Orders.ORDER_SEQ DESC) where ROWNUM<=10", (resultSet, i) -> {return toOrder(resultSet);
       });
    		   
    		   HashMap<Long, Order> hmap = new HashMap<>();
	      Iterator<Order> orderIterator = orders.iterator();
	      while (orderIterator.hasNext()) {
	          Order order = orderIterator.next();
	          if(hmap.containsKey(order.getOrderId())) {
	              Order thisOrder=hmap.get(order.getOrderId());
	              List<Item> itemList = thisOrder.getItem();
	              itemList.addAll(order.getItem());
	              thisOrder.setItem(itemList);
	              hmap.put(order.getOrderId(), thisOrder);
	          }
	          else {
	              hmap.put(order.getOrderId(), order);
	          }
	      }
	      return new ArrayList<>(hmap.values());
   }
   
   public List<Order> loadAllOrdersMap() {
       List<Order> orders= jdbcTemplate.query("select Orders.ORDER_ID,Orders.EMP_ID,Orders.ORDER_STATUS,Order_Item.ITEM_ID,Items.ITEM_NAME,"
                  + "Order_Item.QUANTITY_ITEM  from USP_DEV.Orders,USP_DEV.Order_Item,USP_DEV.Items where USP_DEV.Orders.ORDER_ID="
                  + "USP_DEV.Order_Item.ORDER_ID AND USP_DEV.Items.ITEM_ID=USP_DEV.order_item.ITEM_ID ",
                  (resultSet, i) -> {return toOrder(resultSet);});
          HashMap<Long, Order> hmap = new HashMap<>();
          Iterator<Order> orderIterator = orders.iterator();
          while (orderIterator.hasNext()) {
              Order order = orderIterator.next();
              if(hmap.containsKey(order.getOrderId())) {
                  Order thisOrder=hmap.get(order.getOrderId());
                  List<Item> itemList = thisOrder.getItem();
                  itemList.addAll(order.getItem());
                  thisOrder.setItem(itemList);
                  hmap.put(order.getOrderId(), thisOrder);
              }
              else {
                  hmap.put(order.getOrderId(), order);
              }
          }
          return new ArrayList<>(hmap.values());
      }

   private Order toOrder(ResultSet resultSet) throws SQLException {
       Order order = new Order();
       Item itemObject = new Item();
      
       order.setOrderId(resultSet.getLong("ORDER_ID"));
       order.setEmployeeId(resultSet.getString("EMP_ID"));
       order.setOrderStatus(resultSet.getString("ORDER_STATUS"));
      /* System.out.println(resultSet.getLong("ITEM_ID"));
      System.out.println(resultSet.getLong("QUANTITY_ITEM"));
      */ 
       itemObject.setItemId(resultSet.getLong("ITEM_ID"));
       itemObject.setItemName(resultSet.getString("ITEM_NAME"));
       itemObject.setItemQuantity(resultSet.getLong("QUANTITY_ITEM"));
      /* System.out.println("Item ID "+itemObject.getItemId());
       System.out.println("Item Quantity "+itemObject.getItemQuantity());
      */ 
       order.setItemList(itemObject);
      
       return order;
   }
   
   
   public int generateRandomNumber()
   {
	   Random randomNumber = new Random();
	   return randomNumber.nextInt(10000000);
   }
   
   @Override
   public Order saveOrderItem(Order orders) {
	   
	   boolean isOrderIdUnique=true;
	
	   //check item quantity
	   
	   
	   OrderStatus orderStatusValue = OrderStatus.In_Progress;
	   
	   orders.setEmployeeId(EmpId);
	   orders.setOrderStatus(orderStatusValue.toString());
	   orders.setOrderId(generateRandomNumber());
	   
	   while(isOrderIdUnique) {
		   
		try {	
			String sql1 = "insert into USP_DEV.Orders values (order_seq.nextval,?, ? , ?)";
		    jdbcTemplate.update(sql1,orders.getOrderId(), orders.getEmployeeId(),orders.getOrderStatus() );
		    isOrderIdUnique=false;
		}
	   catch(Exception e)
	   {
		   orders.setOrderId(generateRandomNumber());
		   isOrderIdUnique=true;
	   }
	   }
       
      orders.getItem().forEach(Item -> {
	  String sql = "insert into USP_DEV.Order_Item values (?, ? , ?)";
       jdbcTemplate.update(sql,orders.getOrderId(),Item.getItemId(),Item.getItemQuantity());
	   });
	   
	   return orders;
   }
   
   @Override
   public Order updateOrder(String OrderId) {
       //throw new UnsupportedOperationException();
        Long orderId=Long.parseLong(OrderId);
        List<Order> orders = jdbcTemplate.query("select * from USP_DEV.Orders where ORDER_ID ="+orderId, (resultSet, i) -> {
                   return toOrderRow(resultSet);
               });
       if (orders.size() == 1) {
           Order order=orders.get(0);
           String empId = order.getEmployeeId();
           OrderStatus orderStatusValue = OrderStatus.Completed;
           order.setOrderStatus(orderStatusValue.toString());
           
           String query="update  USP_DEV.Orders set ORDER_STATUS='"+orderStatusValue.toString()+"'where ORDER_ID='"+orderId+"'";
           int result= jdbcTemplate.update(query);
           if(result<0)
           {
               return null;
           }
       List<Employee> employee = jdbcTemplate.query("select * from USP_DEV.Employee where EMP_ID =?",
               new String[]{empId}, (resultSet, i) -> {
                   return toEmployee(resultSet);
               });
       if (employee.size() <0) {
           return null;
       }
       Employee e=employee.get(0);
       Order returnOrder=new Order();
       returnOrder.setOrderId(orderId);
       returnOrder.setEmployeeId(e.getEmail());
       //sendEmail(orderId,e.getEmail());
       return returnOrder;
       //getEmail
       }
       return null;
        
   }
   
   private Order toOrderRow(ResultSet resultSet) throws SQLException {
	      Order order = new Order();
	     // Item itemObject = new Item();
	      order.setOrderId(resultSet.getLong("ORDER_ID"));
	      order.setEmployeeId(resultSet.getString("EMP_ID"));
	      order.setOrderStatus(resultSet.getString("ORDER_STATUS"));
	     // System.out.println(resultSet.getLong("ITEM_ID"));
	      //System.out.println(resultSet.getLong("QUANTITY_ITEM"));
	    //  itemObject.setItemId(resultSet.getLong("ITEM_ID"));
	      //itemObject.setItemName(resultSet.getString("ITEM_NAME"));
	     // itemObject.setItemQuantity(resultSet.getLong("QUANTITY_ITEM"));
//	       System.out.println("Item ID "+itemObject.getItemId());
//	       System.out.println("Item Quantity "+itemObject.getItemQuantity());
	    //  order.setItemList(itemObject);
	      return order;
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

   
   public List<Order> loadRunningOrders() {
       return jdbcTemplate.query("select Orders.ORDER_ID,Orders.EMP_ID,Orders.ORDER_STATUS,Order_Item.ITEM_ID,Items.ITEM_NAME,Order_Item.QUANTITY_ITEM  from USP_DEV.Orders,USP_DEV.Order_Item,USP_DEV.Items where USP_DEV.Orders.ORDER_ID=USP_DEV.Order_Item.ORDER_ID AND USP_DEV.Items.ITEM_ID=USP_DEV.order_item.ITEM_ID AND USP_DEV.Orders.ORDER_STATUS= 'In_Progress'", (resultSet, i) -> {return toOrder(resultSet);
       });
   }
   
   public List<Order> loadRunningOrdersMap() {
	      List<Order> orders= jdbcTemplate.query("select Orders.ORDER_ID,Orders.EMP_ID,Orders.ORDER_STATUS,Order_Item.ITEM_ID,Items.ITEM_NAME,"
	              + "Order_Item.QUANTITY_ITEM  from USP_DEV.Orders,USP_DEV.Order_Item,USP_DEV.Items where USP_DEV.Orders.ORDER_ID="
	              + "USP_DEV.Order_Item.ORDER_ID AND USP_DEV.Items.ITEM_ID=USP_DEV.order_item.ITEM_ID AND USP_DEV.Orders.ORDER_STATUS= 'In_Progress'",
	              (resultSet, i) -> {return toOrder(resultSet);});
	      HashMap<Long, Order> hmap = new HashMap<>();
	      Iterator<Order> orderIterator = orders.iterator();
	      while (orderIterator.hasNext()) {
	          Order order = orderIterator.next();
	          if(hmap.containsKey(order.getOrderId())) {
	              Order thisOrder=hmap.get(order.getOrderId());
	              List<Item> itemList = thisOrder.getItem();
	              itemList.addAll(order.getItem());
	              thisOrder.setItem(itemList);
	              hmap.put(order.getOrderId(), thisOrder);
	          }
	          else {
	              hmap.put(order.getOrderId(), order);
	          }
	      }
	      return new ArrayList<>(hmap.values());
	  }
   
}

