package com.talla.backmanager.Repositories.Budgetaire;

import com.talla.backmanager.Entites.Budgetaire.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FonctionRepository extends JpaRepository<Fonction, Integer> {
    public List<Fonction> findByNumfonction(String numeroFonction);
}
