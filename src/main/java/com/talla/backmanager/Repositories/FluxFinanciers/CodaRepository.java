package com.talla.backmanager.Repositories.FluxFinanciers;

import com.talla.backmanager.Entites.FluxFinanciers.Beans.Coda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CodaRepository extends JpaRepository<Coda, Integer> {
}
