package org.camunda.bpm.getstarted.process.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BookFlightAdapter implements JavaDelegate {

  private boolean shouldSucceed = true;


  @Override
  public void execute(DelegateExecution execution) throws Exception {


     if (shouldSucceed) {
         System.out.println("book flight succeeded");

     }
     else{
         System.out.println("book flight crashes..  fail.flight.enabled=" + shouldSucceed);
         throw new RuntimeException("Flight booking did not work");
     }

    
  }

}
