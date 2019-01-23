package org.camunda.bpm.getstarted.process;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class _App {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
//      HelloWorld obj = (HelloWorld) context.getBean("processEngineConfiguration");
//      obj.getMessage();
   }
}