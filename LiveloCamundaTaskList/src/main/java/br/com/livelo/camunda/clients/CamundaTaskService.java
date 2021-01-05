package br.com.livelo.camunda.clients;


import br.com.livelo.camunda.dto.*;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="CamundaTaskService",url = "http://localhost:8080/engine-rest/")
public interface CamundaTaskService {

    @RequestMapping(method = RequestMethod.GET, value = "/task")
    List<TaskDTO> getTasks(@RequestParam("name") String name, @RequestParam String candidateUser, @RequestParam Integer includeAssignedTasks);

    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}/variables/")
    VariableDTO getTask(@PathVariable("id") String taskId);

    @RequestMapping(method = RequestMethod.POST, value = "/task/{id}/assignee/")
    @Headers("Content-Type: application/json")
    void assignee(@PathVariable("id") String id, AssignDTO assignDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/task/{id}/complete/")
    @Headers("Content-Type: application/json")
    void complete(@PathVariable("id") String id, Root variables);
}
