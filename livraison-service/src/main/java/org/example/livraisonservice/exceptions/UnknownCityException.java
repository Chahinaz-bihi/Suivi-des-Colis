package org.example.livraisonservice.exceptions;

public class UnknownCityException extends RuntimeException {

    public UnknownCityException(String city) {
        super("Ville destinataire inconnue : " + city);
    }
}
