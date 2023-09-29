package com.ncr.snackbar.dao;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ncr.snackbar.model.Order;

public interface EmployeeDaoInterface<T> {
       boolean save(T t);
       T load(String id);
       boolean delete(String id);
       boolean updatePassword(String id,String password);
       boolean updateEmail(String id,String email);
       boolean updateName(String id,String name);
       boolean validate(String id,String password);
       String getEmail(String id);
       boolean verifyEmail(String id,String Email);
       List<T> loadAll();
}