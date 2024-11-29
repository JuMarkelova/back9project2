package ru.back.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.back.app.client.CoreClient;

@RequestMapping("/web")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final CoreClient coreClient;

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello from Web!";
    }

    @GetMapping("/core/test")
    public String callCore() {
        return coreClient.testCoreConnection();
    }
}
