package com.ncr.snackbar.dao;
import java.util.List;

public interface ItemDaoInterface<T, E> {
   boolean create(T t);
   T read(long id);
   boolean delete(long id);
   boolean update(T t, E e);
   List<T> readAll();
}
