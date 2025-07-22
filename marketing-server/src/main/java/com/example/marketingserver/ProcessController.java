package com.example.marketingserver;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/marketing")
@CrossOrigin
public class ProcessController {
    private final RuntimeService runtimeService;

    public ProcessController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("/start/{key}")
    public Map<String, String> startProcess(@PathVariable String key) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key);
        Map<String, String> result = new HashMap<>();
        result.put("id", instance.getId());
        result.put("definitionId", instance.getProcessDefinitionId());
        return result;
    }
}
