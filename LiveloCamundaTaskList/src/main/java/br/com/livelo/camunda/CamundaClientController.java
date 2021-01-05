package br.com.livelo.camunda;

import br.com.livelo.camunda.clients.CamundaTaskService;
import br.com.livelo.camunda.dto.*;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.camunda.bpm.engine.variable.Variables.*;

@Controller
public class CamundaClientController {

    public static final String REDIRECT_HOME = "home";
    @Autowired
    private CamundaTaskService camundaTaskService;

    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;


    public CamundaClientController(@Qualifier("remote") RuntimeService runtimeService,
                                   @Qualifier("remote") RepositoryService repositoryService) {
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
    }


    @GetMapping("/")
    public String homePage(Model model, Principal principal) {
        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @GetMapping("/createg")
    public String createNew(Model model, Principal principal) {

        String id = UUID.randomUUID().toString();
        double amount = 5001d;
        VariableMap variables = createVariables()
                .putValueTyped("id", stringValue(id));

        variables.putValueTyped("amount", doubleValue(amount));
        variables.putValueTyped("user", stringValue(principal.getName()));
        variables.putValueTyped("incident", booleanValue(false));

        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        model.addAttribute("amount", amount);
        model.addAttribute("id", id);

        runtimeService.startProcessInstanceByKey("pj-livelo-approval", id, variables);
        model.addAttribute("created", true);
        return "home";
    }

    @GetMapping("/creategincident")
    public String createNewIncident(Model model, Principal principal) {

        String id = UUID.randomUUID().toString();
        double amount = 5001d;
        VariableMap variables = createVariables()
                .putValueTyped("id", stringValue(id));

        variables.putValueTyped("amount", doubleValue(amount));
        variables.putValueTyped("user", stringValue(principal.getName()));
        variables.putValueTyped("incident", booleanValue(true));

        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        model.addAttribute("amount", amount);
        model.addAttribute("id", id);

        runtimeService.startProcessInstanceByKey("pj-livelo-approval", id, variables);
        model.addAttribute("created", true);
        return "home";
    }

    @GetMapping("/createp")
    public String createNewLessThan(Model model, Principal principal) {

        String id = UUID.randomUUID().toString();
        double amount = 500d;
        VariableMap variables = createVariables()
                .putValueTyped("id", stringValue(id));

        variables.putValueTyped("amount", doubleValue(amount));
        variables.putValueTyped("user", stringValue(principal.getName()));
        variables.putValueTyped("incident", booleanValue(false));


        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        model.addAttribute("amount", amount);
        model.addAttribute("id", id);
        model.addAttribute("created", true);

        runtimeService.startProcessInstanceByKey("pj-livelo-approval", id, variables);

        return "home";
    }

    private List<TaskLiveloDTO> getTaskLiveloDTOS(Principal principal) {
        return camundaTaskService.getTasks("p1 approval", principal.getName(), 1).stream().map(t -> {
                VariableDTO variableDTO = camundaTaskService.getTask(t.getId());

                return new TaskLiveloDTO(t.getId(),variableDTO.getAmount().getValue(),variableDTO.getId().getValue(), t.getOwner(), t.getAssignee());
            }).collect(Collectors.toList());
    }

    @GetMapping("/approve")
    public String approve(Model model, Principal principal, @RequestParam String id) {
        Root root = new Root();

        Variables variables = new Variables();

        AVariable aVariable = new AVariable();
        aVariable.setValue(principal.getName());

        AnotherVariable anotherVariable = new AnotherVariable();
        anotherVariable.setValue(true);

        variables.setFirstName(aVariable);
        variables.setApproved(anotherVariable);

        root.setVariables(variables);
        camundaTaskService.complete(id,root);
        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        model.addAttribute("approve",true);
        model.addAttribute("taskid",id);
        return REDIRECT_HOME;
    }

    @GetMapping("/assign")
    public String assign(Model model, Principal principal, @RequestParam String id) {
        camundaTaskService.assignee(id,new AssignDTO(principal.getName()));
        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        model.addAttribute("assign",true);
        model.addAttribute("taskid",id);
        return REDIRECT_HOME;
    }

    @GetMapping("/denied")
    public String denied(Model model, Principal principal, @RequestParam String id) {

        Root root = new Root();

        Variables variables = new Variables();

        AVariable aVariable = new AVariable();
        aVariable.setValue(principal.getName());

        AnotherVariable anotherVariable = new AnotherVariable();
        anotherVariable.setValue(false);

        variables.setFirstName(aVariable);
        variables.setApproved(anotherVariable);

        root.setVariables(variables);

        camundaTaskService.complete(id,root);
        List<TaskLiveloDTO> tasks = getTaskLiveloDTOS(principal);
        model.addAttribute("tasks", tasks);
        model.addAttribute("denied",true);
        model.addAttribute("taskid",id);
        return REDIRECT_HOME;
    }
}
