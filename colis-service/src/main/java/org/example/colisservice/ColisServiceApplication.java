package org.example.colisservice;

import org.example.colisservice.entities.Colis;
import org.example.colisservice.repositories.ColisRepository;
import org.example.livraisonservice.StatusLivraison;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ColisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColisServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ColisRepository colisRepository) {
        return args -> {
            Colis colis1 = Colis.builder()
                    .reference("REF-1001")
                    .destinataire("Rabat")
                    .expediteur("Sara")
                    .statut(StatusLivraison.PREPARED)
                    .poids(3.5)
                    .build();
            colisRepository.save(colis1);
            Colis colis2 = Colis.builder()
                    .reference("REF-1002")
                    .destinataire("Mohamadia")
                    .expediteur("Chahinaz")
                    .statut(StatusLivraison.PREPARED)
                    .poids(1.8)
                    .build();
            colisRepository.save(colis2);
            // Colis 3 - En transit vers Marrakech
            Colis colis3 = Colis.builder()
                    .reference("REF-1003")
                    .destinataire("Marrakech")
                    .expediteur("Mohammed")
                    .statut(StatusLivraison.PREPARED)
                    .poids(5.2)
                    .build();
            colisRepository.save(colis3);

            // Colis 4 - En transit vers Fès
            Colis colis4 = Colis.builder()
                    .reference("REF-1004")
                    .destinataire("Fès")
                    .expediteur("Amina")
                    .statut(StatusLivraison.PREPARED)
                    .poids(2.1)
                    .build();
            colisRepository.save(colis4);

            // Colis 5 - Livré à Tanger
            Colis colis5 = Colis.builder()
                    .reference("REF-1005")
                    .destinataire("Tanger")
                    .expediteur("Youssef")
                    .statut(StatusLivraison.PREPARED)
                    .poids(4.7)
                    .build();
            colisRepository.save(colis5);

            // Colis 6 - En transit vers Agadir
            Colis colis6 = Colis.builder()
                    .reference("REF-1006")
                    .destinataire("Agadir")
                    .expediteur("Fatima")
                    .statut(StatusLivraison.PREPARED)
                    .poids(3.0)
                    .build();
            colisRepository.save(colis6);

            // Colis 7 - Préparé pour Meknès
            Colis colis7 = Colis.builder()
                    .reference("REF-1007")
                    .destinataire("Meknès")
                    .expediteur("Hassan")
                    .statut(StatusLivraison.PREPARED)
                    .poids(1.5)
                    .build();
            colisRepository.save(colis7);

            // Colis 8 - En transit vers Oujda
            Colis colis8 = Colis.builder()
                    .reference("REF-1008")
                    .destinataire("Oujda")
                    .expediteur("Khadija")
                    .statut(StatusLivraison.PREPARED)
                    .poids(6.3)
                    .build();
            colisRepository.save(colis8);

            // Colis 9 - Livré à Salé
            Colis colis9 = Colis.builder()
                    .reference("REF-1009")
                    .destinataire("Salé")
                    .expediteur("Omar")
                    .statut(StatusLivraison.PREPARED)
                    .poids(2.8)
                    .build();
            colisRepository.save(colis9);

            // Colis 10 - En transit vers Kenitra
            Colis colis10 = Colis.builder()
                    .reference("REF-1010")
                    .destinataire("Kenitra")
                    .expediteur("Laila")
                    .statut(StatusLivraison.PREPARED)
                    .poids(4.1)
                    .build();
            colisRepository.save(colis10);

            // Colis 11 - Préparé pour El Jadida
            Colis colis11 = Colis.builder()
                    .reference("REF-1011")
                    .destinataire("El Jadida")
                    .expediteur("Rachid")
                    .statut(StatusLivraison.PREPARED)
                    .poids(3.3)
                    .build();
            colisRepository.save(colis11);

            // Colis 12 - En transit vers Tétouan
            Colis colis12 = Colis.builder()
                    .reference("REF-1012")
                    .destinataire("Tétouan")
                    .expediteur("Nadia")
                    .statut(StatusLivraison.PREPARED)
                    .poids(1.9)
                    .build();
            colisRepository.save(colis12);

            // Colis 13 - Livré à Safi
            Colis colis13 = Colis.builder()
                    .reference("REF-1013")
                    .destinataire("Safi")
                    .expediteur("Karim")
                    .statut(StatusLivraison.PREPARED)
                    .poids(5.5)
                    .build();
            colisRepository.save(colis13);

            // Colis 14 - En transit vers Essaouira
            Colis colis14 = Colis.builder()
                    .reference("REF-1014")
                    .destinataire("Oujda")
                    .expediteur("Samira")
                    .statut(StatusLivraison.PREPARED)
                    .poids(2.4)
                    .build();
            colisRepository.save(colis14);

            // Colis 15 - Préparé pour Nador
            Colis colis15 = Colis.builder()
                    .reference("REF-1015")
                    .destinataire("Nador")
                    .expediteur("Mehdi")
                    .statut(StatusLivraison.PREPARED)
                    .poids(3.7)
                    .build();
            colisRepository.save(colis15);

            // Colis 16 - En transit vers Casablanca
            Colis colis16 = Colis.builder()
                    .reference("REF-1016")
                    .destinataire("Kenitra")
                    .expediteur("Zineb")
                    .statut(StatusLivraison.PREPARED)
                    .poids(4.9)
                    .build();
            colisRepository.save(colis16);

            // Colis 17 - Livré à Rabat
            Colis colis17 = Colis.builder()
                    .reference("REF-1017")
                    .destinataire("Rabat")
                    .expediteur("Amine")
                    .statut(StatusLivraison.PREPARED)
                    .poids(2.2)
                    .build();
            colisRepository.save(colis17);

            // Colis 18 - En transit vers Marrakech
            Colis colis18 = Colis.builder()
                    .reference("REF-1018")
                    .destinataire("Marrakech")
                    .expediteur("Soukaina")
                    .statut(StatusLivraison.PREPARED)
                    .poids(6.1)
                    .build();
            colisRepository.save(colis18);

            // Colis 19 - Préparé pour Tanger
            Colis colis19 = Colis.builder()
                    .reference("REF-1019")
                    .destinataire("Tanger")
                    .expediteur("Hamza")
                    .statut(StatusLivraison.PREPARED)
                    .poids(1.6)
                    .build();
            colisRepository.save(colis19);

            // Colis 20 - En transit vers Fès
            Colis colis20 = Colis.builder()
                    .reference("REF-1020")
                    .destinataire("Fès")
                    .expediteur("Imane")
                    .statut(StatusLivraison.PREPARED)
                    .poids(3.9)
                    .build();
            colisRepository.save(colis20);
        };
    }

}
