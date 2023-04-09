package com.talla.backmanager.Repositories;

import com.talla.backmanager.Entites.Classes.Repartition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartitionRepository extends JpaRepository<Repartition,Integer> {
}
