package com.ncr.snackbar.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ncr.snackbar.SnackbarApplication;

@ComponentScan(basePackages="com.ncr.snackbar")

public class DataSourceOrder {
//	@Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/snackbar");
//
//        ds.setUsername("root");
//        ds.setPassword("root");
//        return (DataSource) ds;
//    }
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SnackbarApplication.class);

}
