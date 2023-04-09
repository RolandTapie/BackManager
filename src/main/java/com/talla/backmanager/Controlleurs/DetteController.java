package Dette.Controlleurs;

import Dette.DTO.Mappers.Convert;
import Dette.Entites.Classes.Banque;
import Dette.Entites.Classes.Echeance;
import Dette.Repositories.BanqueRepository;
import Dette.Repositories.EcheanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dette")
@CrossOrigin("*")
public class DetteController {

    @Autowired
    BanqueRepository banqueRepository;

    @Autowired
    EcheanceRepository echeanceRepository;
    @Autowired
    Convert convert;

    @GetMapping("/Banques/All")
    List<Banque> getAllBanks(){
        return banqueRepository.findAll();
    }
    @GetMapping("/Banques/{nom}")
    List<Banque> getBanqueByNom(@PathVariable String nom ) {
        return banqueRepository.findByNom(nom);
    }
    @GetMapping("/Echeances/All")
    List<Echeance> getAllEcheances(){
        return echeanceRepository.findAll();
    }

    @GetMapping("/Echeances/{numeroEmprunt}")
    List<Echeance> getEcheancesByNumEmprunt(@PathVariable  String numeroEmprunt){
        return echeanceRepository.findByEmprunt_NumeroEmprunt(numeroEmprunt);
    }
    @GetMapping("/Echeances/Date/{date}")
    List<Echeance> getEcheancesByDate(@PathVariable  String date){
        return echeanceRepository.findByDateEcheance(date);
    }

//    @GetMapping("/Signals")
//    List<SignaletiqueDTO> getAllSignal(){
//
//    }
}
