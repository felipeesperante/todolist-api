package br.com.nunesonline.todolistapi.rest;

import io.swagger.annotations.Api;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "APIs - TODO List API")
@RequestMapping(value = "/sisdat", produces = APPLICATION_JSON_VALUE)
public class TodoListApiREST {
    
    
    
}
