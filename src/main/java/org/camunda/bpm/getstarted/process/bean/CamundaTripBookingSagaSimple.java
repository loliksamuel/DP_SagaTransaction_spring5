package org.camunda.bpm.getstarted.process.bean;


import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.getstarted.process.SagaBuilder;
import org.camunda.bpm.getstarted.process.adapter.*;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

//@Component
//@Singleton
public class CamundaTripBookingSagaSimple  implements InitializingBean {
  @Autowired
  private RepositoryService repositoryService;

  @Autowired
  private RuntimeService runtimeService;
  //@Autowired
  //private ProcessEngine   camunda;

//  @Autowired
//  private RuntimeService runtimeService;

//  @Value("${succeed.flight:true}")
//  boolean shouldSucceed;
//
//  @Value("${succeed.car:true}")
//  String shouldSucceedCar;

  public void afterPropertiesSet() throws Exception {
    //runtimeService.startProcessInstanceByKey("loanApproval");
    //runtimeService.startProcessInstanceByKey("trip");
    System.out.println("\nstarting camunda process trip");
    System.out.println("--------------------------------");
    startJavaApi();
  }

  //@PostConstruct
  public void startJavaApi() {

    // Configure and startup (in memory) engine
    //ProcessEngine camunda = new StandaloneInMemProcessEngineConfiguration().buildProcessEngine();

    //System.out.println("shouldSucceedCar="+shouldSucceedCar+" shouldSucceed="+shouldSucceed);
    SagaBuilder saga = SagaBuilder.newSaga("trip") //
        .activity            ("Book    car"  , BookCarAdapter.class) //
        .compensationActivity("Cancel  car"  , CancelCarAdapter.class) //
        .activity            ("Book   hotel" , BookHotelAdapter.class) //
        .compensationActivity("Cancel hotel" , CancelHotelAdapter.class) //
        .activity            ("Book   flight", BookFlightAdapter.class) //
        .compensationActivity("Cancel flight", CancelFlightAdapter.class) //
        .end() //
        .triggerCompensationOnAnyError();//flow.eventSubProcess()...

    // get model instance
    BpmnModelInstance modelInstance = saga.getModel();

    // write to file to be able to open it in Camunda Modeler
    Bpmn.writeModelToFile(new File("trip.bpmn"), modelInstance);

    //deploy
    repositoryService.createDeployment() //
        .addModelInstance("trip.bpmn", modelInstance) //
        .deploy();


    // run instances of our saga - its state will be persisted
    runtimeService.startProcessInstanceByKey(
            "trip",
            Variables.putValue("someVariableToPass", "someValue")
                     .putValue("name", "mazda"));








  }

}
