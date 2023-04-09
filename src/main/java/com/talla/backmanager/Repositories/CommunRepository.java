package Dette.Repositories;

import Dette.Entites.Classes.Commun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommunRepository extends JpaRepository<Commun,Integer> {
}
