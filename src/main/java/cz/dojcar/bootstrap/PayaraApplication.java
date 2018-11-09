package cz.dojcar.bootstrap;

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;

public class PayaraApplication {

    public static void main(String[] args) throws BootstrapException {
        PayaraMicro.bootstrap();
    }
}
