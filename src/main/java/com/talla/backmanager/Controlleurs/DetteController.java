package com.talla.backmanager.Controlleurs;


import com.talla.backmanager.Controlleurs.Services.BanqueRequest;
import com.talla.backmanager.Controlleurs.Services.EcheanceRequest;
import com.talla.backmanager.DTO.Mappers.Convert;
import com.talla.backmanager.DTO.Objects.BanqueDTO;
import com.talla.backmanager.DTO.Objects.EcheanceDTO;
import com.talla.backmanager.Repositories.BanqueRepository;
import com.talla.backmanager.Repositories.EcheanceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/dette")
@CrossOrigin("*")
public class DetteController {
    final BanqueRequest banqueRequest;
    final EcheanceRequest echeanceRequest;

    public DetteController(  BanqueRequest banqueRequest, EcheanceRequest echeanceRequest) {
        this.banqueRequest = banqueRequest;
        this.echeanceRequest = echeanceRequest;
    }


    ////////////////////////banques/////////////////////////////////////////////////////
    @GetMapping("/Banques/All")
    List<BanqueDTO> getAllBanks(){
        return banqueRequest.getAllBanques();
    }
    @GetMapping("/Banques/{nom}")
    List<BanqueDTO> getBanqueByNom(@PathVariable String nom ) {
        return banqueRequest.getBanqueByNom(nom);
    }

    //////////////////////echeances//////////////////////////////////////////////////
    @GetMapping("/Echeances/All")
    List<EcheanceDTO> getAllEcheances(){
        return echeanceRequest.getAllEcheances();
    }

    @GetMapping("/Echeances/{numeroEmprunt}")
    List<EcheanceDTO> getEcheancesByNumEmprunt(@PathVariable  String numeroEmprunt){
        return echeanceRequest.getEcheanceByNumEmprunt(numeroEmprunt);
    }

    @GetMapping("/Echeances/Date/{date}")
    List<EcheanceDTO> getEcheancesByDate(@PathVariable  String date){
        return echeanceRequest.getEcheanceByDate(date);
    }

    /////////////////////////////comptabilisation////////////////////////////////////////////





//    @GetMapping("/Signals")
//    List<SignaletiqueDTO> getAllSignal(){
//
//    }
}
