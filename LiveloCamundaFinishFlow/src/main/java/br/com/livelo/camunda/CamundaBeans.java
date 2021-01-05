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

    @ExternalTaskSubscription(topicName = "finish-flow")
    @Bean
    public ExternalTaskHandler creditScoreChecker() {
        return (externalTask, externalTaskService) -> {

            // retrieve a variable from the Workflow Engine
            String id = externalTask.getVariable("id");
            double amount = externalTask.getVariable("amount");
            Boolean ok = externalTask.getVariable("ok");

            if(ok == null){
                log.info("{}: Task {} wih amount {} was {}!", id, externalTask.getId(), amount, "approved");
                externalTaskService.complete(externalTask);
            }else {
                String msg = ok ? "approved" : "denied";
                // complete the external task
                externalTaskService.complete(externalTask);

                log.info("{}: Task {} wih amount {} was {}!", id, externalTask.getId(), amount, msg);
            }
        };
    }
}
