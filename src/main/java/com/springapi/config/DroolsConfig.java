package com.springapi.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * DroolsConfig
 */

@Configuration
@ComponentScan("com.baeldung.spring.drools.service")
public class DroolsConfig {
    private static final String drlFile = "book_discount.drl";
    
    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
    
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        //KieBase is a repository which contains all knowledge related to the application such as rules
        KieModule kieModule = kieBuilder.getKieModule();
        
        //KieModule where the KieBase has been defined.
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    
}
