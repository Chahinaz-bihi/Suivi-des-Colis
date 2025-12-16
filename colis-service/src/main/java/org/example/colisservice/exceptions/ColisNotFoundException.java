package org.example.colisservice.exceptions;

public class ColisNotFoundException extends RuntimeException {

    public ColisNotFoundException(Long colisId) {
        super("Colis introuvable avec l'id : " + colisId);
    }
}
