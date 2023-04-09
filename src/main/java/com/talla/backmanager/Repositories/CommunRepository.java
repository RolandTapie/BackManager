package com.talla.backmanager.Repositories;


import com.talla.backmanager.Entites.Classes.Commun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommunRepository extends JpaRepository<Commun,Integer> {
}
