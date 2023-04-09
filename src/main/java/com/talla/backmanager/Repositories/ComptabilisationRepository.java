package com.talla.backmanager.Repositories;

import com.talla.backmanager.Entites.Classes.Comptabilisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptabilisationRepository extends JpaRepository<Comptabilisation, Integer> {
}
