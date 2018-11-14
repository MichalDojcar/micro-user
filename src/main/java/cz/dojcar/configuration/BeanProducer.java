package cz.dojcar.configuration;

import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientProperties;

public class BeanProducer {

    @Produces
    public Client produceClient() {
        return ClientBuilder.newBuilder()
                .property(ClientProperties.CONNECT_TIMEOUT, 100)
                .property(ClientProperties.READ_TIMEOUT, 500)
                .build();
    }
}
