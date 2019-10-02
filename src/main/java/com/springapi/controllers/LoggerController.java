package com.springapi.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import com.springapi.utils.ReadFile;
// import com.springapi.config.BookAppConfig;
import com.springapi.service.LoggerService;



/**
 * LoggerController
 */
@RestController
@RequestMapping("/api")
public class LoggerController {

    @Autowired
    private Environment env;

    @Autowired
    private LoggerService loggerJson;

    @Autowired
    public RequestMappingHandlerMapping requestMappingHandlerMapping;

    /*
    @param key = Name of the environment property
     */
    @RequestMapping("/env")
    public Object environment(@RequestParam(required = false, defaultValue="") String key){
        if (!key.isEmpty()) { return env.getProperty(key); }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment myenv = context.getEnvironment();
        return myenv.getPropertySources();
    }

    @RequestMapping("/logs/migrate")
    public String migrateLogsToDb(){
        loggerJson.addAll();
        return "migration completed!";
    }
    
    @CrossOrigin
    @RequestMapping("/logs")
    public Map<String, Object> jsonLogs(
        @RequestParam(required =false, defaultValue="10") Integer limit,
        @RequestParam(required =false, defaultValue="1") Integer pg) {

        Integer maxPages = loggerJson.getCount();
        Integer pgLimit = limit != null ? limit : 10;
        Integer startIndex = ((pg - 1) * pgLimit);
        Integer toIndex = ((pgLimit * pg > maxPages)) ? maxPages : (pgLimit * pg);

        Map<String, Object> query = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                if (pg != null && startIndex < maxPages) {
                    put("logs", loggerJson.getJsonLogs().subList(startIndex, toIndex));
                    put("pageCount", maxPages);
                    put("limit", pgLimit);
                }
            }
        };
        return query;
    }

    @RequestMapping("/endpoints")
    @ResponseBody
    public Object showEndpointsAction(){
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream()
                .map(route -> (
                    route.getMethodsCondition().getMethods().size() == 0 ? 
                    "GET" : route.getMethodsCondition().getMethods().toArray()[0]) + " " + route.getPatternsCondition().getPatterns().toArray()[0]
                    )
                .toArray();
    }
    
}