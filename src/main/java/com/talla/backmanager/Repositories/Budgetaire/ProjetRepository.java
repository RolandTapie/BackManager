package com.talla.backmanager.Repositories.Budgetaire;

import com.talla.backmanager.Entites.Budgetaire.Projet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    Projet findByNumeroprojet (String numProjet);
}
