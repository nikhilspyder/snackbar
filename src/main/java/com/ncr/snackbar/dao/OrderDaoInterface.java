package com.ncr.snackbar.dao;

import java.util.List;

import com.ncr.snackbar.model.Order;

public interface OrderDaoInterface<T> {
	public T saveOrderItem(T t);
	void save(T t);
	   T load(long id);
	   void delete(long id);
	   void update(T t);
	   List<T> loadAll();
	   List<T> loadParticularOrder(long id);
	   List<T> loadAllOrders(String EmpId);
	   List<T> loadAllOrdersMap();
	   List<T> loadRunningOrders();
	   T updateOrder(String s);
	   List<T> loadRunningOrdersMap();
}
