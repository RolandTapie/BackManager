package com.talla.backmanager.Repositories.Budgetaire;

import com.talla.backmanager.Entites.Budgetaire.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciceRepository extends JpaRepository<Exercice,Integer> {
    Exercice findByNumeroexercice(String numExercice);
}
