package com.ncr.snackbar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.sql.DataSource;
@Configuration
//@Controller
//@EnableAutoConfiguration(/*exclude={DataSourceAutoConfiguration.class}*/)
@SpringBootApplication
//@Component
@ComponentScan(basePackages="com.ncr.snackbar")
public class SnackbarApplication {

    public static void main(String[] args) {
         SpringApplication.run(SnackbarApplication.class, args);
         
         /*ApplicationContext ctx=new ClassPathXmlApplicationContext("SpringConfig.xml");
           PersonDao dao=(PersonDao)ctx.getBean("personDao");*/
           
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SnackbarApplication.class);
         //context.getBean(PersonClient.class).process();*/
     }
}
