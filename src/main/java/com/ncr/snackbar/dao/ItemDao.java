package com.ncr.snackbar.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ncr.snackbar.model.IncrementOrDecrement;
import com.ncr.snackbar.model.Item;

@Repository
public class ItemDao implements ItemDaoInterface<Item, IncrementOrDecrement> {
        
    @Autowired
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   @PostConstruct
   private void postConstruct() {
       jdbcTemplate = new JdbcTemplate(dataSource);
   }
   @Override
   public boolean create(Item item) {
       String sql = "insert into USP_DEV.Items values (SQ_ITEMID.nextval, ?, ?)";
       int rowsUpdated=jdbcTemplate.update(sql, item.getItemName(), item.getItemQuantity());
       return((rowsUpdated>0)?true:false);
   }
   @Override
   public Item read(long id) {
       List<Item> item = jdbcTemplate.query("select * from USP_DEV.Items where item_id =?",
               new Object[]{id}, (resultSet, i) -> {
                   return toItem(resultSet);
               });
       if (item.size() == 1) {
           return item.get(0);
       }
       return null;
   }
   @Override
   public boolean delete(long id) {
       int rowsUpdated=jdbcTemplate.update("delete from USP_DEV.Items where item_id = ?", id);
       return((rowsUpdated>0)?true:false);
   }
   @Override
   public boolean update(Item item, IncrementOrDecrement incrementOrDecrement) {      
       List<Item> resultItems = jdbcTemplate.query("select * from USP_DEV.Items where item_id ="+item.getItemId(), (resultSet, i) -> {
                   return toItem(resultSet);
               });
       if (resultItems.size() == 1) {
           Item result = resultItems.get(0);
           if(incrementOrDecrement == IncrementOrDecrement.DECREMENT)
           {
               if(result.getItemQuantity()<item.getItemQuantity()) {
                   return false;
               }
               result.setItemQuantity(result.getItemQuantity()-item.getItemQuantity());
           }
           else if(incrementOrDecrement == IncrementOrDecrement.INCREMENT)
           {
               result.setItemQuantity(result.getItemQuantity()+item.getItemQuantity());
           }
           
           String query="update USP_DEV.Items set ITEM_QUANTITY="+result.getItemQuantity()+"where ITEM_ID="+result.getItemId();
           int rowsUpdated= jdbcTemplate.update(query);
           return((rowsUpdated>0)?true:false);
       }
       return false;//Item not found
   }
   @Override
   public List<Item> readAll() {
       return jdbcTemplate.query("select * from USP_DEV.Items", (resultSet, i) -> {
           return toItem(resultSet);
       });
   }
   private Item toItem(ResultSet resultSet) throws SQLException {
       Item item = new Item();
       item.setItemId(resultSet.getLong("ITEM_ID"));
       item.setItemName(resultSet.getString("ITEM_NAME"));
       item.setItemQuantity(resultSet.getInt("ITEM_QUANTITY"));
       return item;
   }
}