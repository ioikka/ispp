package io.ikka.ispp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("ru.axetta.ecafe.processor.web.partner.integra.soap");
        return marshaller;
    }

    @Bean
    public IsppClient countryClient(Jaxb2Marshaller marshaller) {
        IsppClient client = new IsppClient();
        client.setDefaultUri("https://212.11.151.174:28881/processor/soap/client");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
