package Dette.Repositories;

import Dette.Entites.Classes.Echeance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcheanceRepository extends JpaRepository<Echeance,Integer> {
    List<Echeance> findByEmprunt_NumeroEmprunt(String numeroEmprunt);

    List<Echeance> findByDateEcheance(String DateEcheance);
}
