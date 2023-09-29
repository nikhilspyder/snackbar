package com.ncr.snackbar.service;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ncr.snackbar.dao.ItemDaoInterface;
import com.ncr.snackbar.model.IncrementOrDecrement;
import com.ncr.snackbar.model.Item;
import com.ncr.snackbar.model.Order;
@Service
public class ItemService {
    @Autowired
    ItemDaoInterface<Item, IncrementOrDecrement> ItemDAO;
    
  public List<Item> loadAll()
  {
      List<Item> itemList = ItemDAO.readAll();
      Iterator<Item> itemIterator = itemList.iterator();
      while (itemIterator.hasNext()) {
           Item i = itemIterator.next();
           if (i.getItemQuantity()==0) {
               itemIterator.remove();
           }
       }
      return itemList;
  }
  public Item load(String itemId)
  {
       long id= Long.parseLong(itemId);
      Item item = ItemDAO.read(id);
      return item;
  }
  public Item save(Item item)
  {
       boolean result = ItemDAO.create(item);
       if (result==false)
           return null;
      return item;
  }
  public boolean delete(String itemId)
  {
       long id= Long.parseLong(itemId);
       boolean result = ItemDAO.delete(id);
       return result;
  }
  public boolean quantityIncrease(Item item)
  {
       IncrementOrDecrement incrementOrDecrement = IncrementOrDecrement.INCREMENT;
       boolean result = ItemDAO.update(item,incrementOrDecrement);
       return result;
  }
  
  public boolean quantityDecreaseItem(Item item)
  {
       IncrementOrDecrement incrementOrDecrement = IncrementOrDecrement.DECREMENT;
       boolean result = ItemDAO.update(item,incrementOrDecrement);
       return result;
  }
  
  public boolean quantityDecrease(Order order)
  {
       IncrementOrDecrement incrementOrDecrement = IncrementOrDecrement.DECREMENT;
       Iterator<Item> itemIterator = order.getItem().iterator();
       boolean result=true;
      while (itemIterator.hasNext()) {
           Item i = itemIterator.next();
           result = ItemDAO.update(i,incrementOrDecrement);
           if(result==false) {
               return false;
           }
           //if (i.getItemQuantity()==0) {
             //  itemIterator.remove();
           //}
       }
       //boolean result = ItemDAO.update(item,incrementOrDecrement);
       return result;
  }
  
  public List<Item> loadAllAdmin()
  {
      List<Item> itemList = ItemDAO.readAll();
      return itemList;
  }
  
}