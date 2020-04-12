package org.g7.robbo.opd.fgs.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * @author Orlov Diga
 */
@MessagingGateway
public interface I {
    @Gateway(requestChannel = "animalFlow.input")
    Animal process(Animal animal);
}

