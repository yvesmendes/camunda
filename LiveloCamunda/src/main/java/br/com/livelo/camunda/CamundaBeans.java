package br.com.livelo.camunda;

import org.camunda.bpm.client.spring.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class CamundaBeans {

    private static Logger log = LoggerFactory.getLogger(CamundaBeans.class);


    @ExternalTaskSubscription(topicName = "init-flow-pj")
    @Bean
    public ExternalTaskHandler creditScoreChecker() {
        return (externalTask, externalTaskService) -> {

            Integer retries = externalTask.getRetries();
            if (retries == null){
                retries = 3;
            }
            // retrieve a variable from the Workflow Engine
            String id = externalTask.getVariable("id");
            double amount = externalTask.getVariable("amount");

            boolean incident = externalTask.getVariable("incident");

            log.info("{}: The External Task {}!", externalTask.getRetries(), externalTask.getId());

            if(incident){
                externalTaskService.handleFailure(externalTask,"one more", "test", retries - 1, 1000l);
            }else{
                externalTaskService.complete(externalTask,
                        Variables.putValueTyped("init", Variables.stringValue("ok")));
            }

            log.info("{}: The External Task {} with amount {} has been checked!", id, externalTask.getId(), amount);
        };
    }

}
