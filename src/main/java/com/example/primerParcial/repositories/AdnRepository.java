package com.example.primerParcial.repositories;

import com.example.primerParcial.entities.Adn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdnRepository extends JpaRepository<Adn, Long> {
    Optional<Adn> findByDna(String dna);

    //Para obtener los mutantes, usando JPQL
    @Query("SELECT COUNT(a) FROM Adn a WHERE a.isMutant = true")
    long cuentaMutantes();

    //Para obtener los humanos, usando JPQL
    @Query("SELECT COUNT(a) FROM Adn a WHERE a.isMutant = false")
    long cuentaHumanos();

}
