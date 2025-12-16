package org.example.colisservice.exceptions;

public class ColisAlreadyDeliveredException extends RuntimeException {

    public ColisAlreadyDeliveredException(Long id) {
        super("Le colis avec id " + id + " a déjà été livré et ne peut plus être modifié");
    }
}
