package com.ncr.snackbar.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ncr.snackbar.model.Item;
import com.ncr.snackbar.model.Order;
import com.ncr.snackbar.service.ItemService;
@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public List<Item> display()
  {
      List<Item> list2 = itemService.loadAll();
      return list2;
  }
  
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu/item", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.TEXT_PLAIN_VALUE)
  public Item display(@RequestBody String itemId)
  {
       Item item = itemService.load(itemId);
      return item;
  }
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
  public Item createperson(@RequestBody Item item)
  {
         Item newItem=itemService.save(item);
         return newItem;
  }
    
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu", method=RequestMethod.DELETE, consumes=MediaType.TEXT_PLAIN_VALUE)
  public boolean delete(@RequestBody String itemId)
  {
       boolean result = itemService.delete(itemId);
       return result;
  }
  
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu/increasequantity", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
  public boolean quantityIncrease(@RequestBody Item item)
  {
       boolean result = itemService.quantityIncrease(item);
       return result;
  }
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu/decreasequantity", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
  public boolean quantityDecreaseItem(@RequestBody Item item)
  {
       boolean result = itemService.quantityDecreaseItem(item);
       return result;
  }
  @CrossOrigin(origins="*")
  @RequestMapping(path="/menu/admin", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public List<Item> displayAdmin()
  {
      List<Item> list2 = itemService.loadAllAdmin();
      return list2;
  }	
}