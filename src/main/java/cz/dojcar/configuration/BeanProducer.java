package cz.dojcar.configuration;

import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class BeanProducer {

    @Produces
    public Client produceClient() {
        return ClientBuilder.newClient();
    }
}
