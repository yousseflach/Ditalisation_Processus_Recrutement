package ma.marjane.digitalisation_processus_recrutement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("request")
public class RequestController {

    @GetMapping("/request")
    public String request() {
        return "Hello World";
    }
}
