package org.example.colisservice.repositories;
import org.example.colisservice.entities.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ColisRepository extends JpaRepository<Colis, Long> {
}
