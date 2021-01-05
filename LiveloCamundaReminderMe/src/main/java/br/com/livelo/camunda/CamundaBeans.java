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

    @ExternalTaskSubscription(topicName = "send-reminder-email")
    @Bean
    public ExternalTaskHandler creditScoreChecker() {
        return (externalTask, externalTaskService) -> {

            // retrieve a variable from the Workflow Engine
            String id = externalTask.getVariable("id");
            double amount = externalTask.getVariable("amount");

            // complete the external task
            externalTaskService.complete(externalTask,
                    Variables.putValueTyped("reminder", Variables.stringValue("ok")));

            log.info("{}: Reminder me External Task {} with amount {}!", id, externalTask.getId(), amount);
        };
    }

}
