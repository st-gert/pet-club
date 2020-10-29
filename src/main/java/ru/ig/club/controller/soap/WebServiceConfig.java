package ru.ig.club.controller.soap;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ig.club.service.MemberService;
import ru.ig.club.service.PetService;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpointMember(MemberService service) {
        EndpointImpl endpoint = new EndpointImpl(bus, new MemberSoapController(service));
        endpoint.publish("/member");
        return endpoint;
    }

    @Bean
    public Endpoint endpointPet(PetService service) {
        EndpointImpl endpoint = new EndpointImpl(bus, new PetSoapController(service));
        endpoint.publish("/pet");
        return endpoint;
    }

}
