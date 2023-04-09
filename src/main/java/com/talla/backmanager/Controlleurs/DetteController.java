package com.talla.backmanager.Controlleurs;


import com.talla.backmanager.DTO.Mappers.Convert;
import com.talla.backmanager.DTO.Objects.BanqueDTO;
import com.talla.backmanager.DTO.Objects.EcheanceDTO;
import com.talla.backmanager.Entites.Classes.Banque;
import com.talla.backmanager.Entites.Classes.Echeance;
import com.talla.backmanager.Repositories.BanqueRepository;
import com.talla.backmanager.Repositories.EcheanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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

    ////////////////////////banques/////////////////////////////////////////////////////
    @GetMapping("/Banques/All")
    List<BanqueDTO> getAllBanks(){
        return banqueRepository.findAll().stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }
    @GetMapping("/Banques/{nom}")
    List<BanqueDTO> getBanqueByNom(@PathVariable String nom ) {
        return banqueRepository.findByNom(nom).stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

    //////////////////////echeances//////////////////////////////////////////////////
    @GetMapping("/Echeances/All")
    List<EcheanceDTO> getAllEcheances(){
        return echeanceRepository.findAll().stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

    @GetMapping("/Echeances/{numeroEmprunt}")
    List<EcheanceDTO> getEcheancesByNumEmprunt(@PathVariable  String numeroEmprunt){
        return echeanceRepository.findByEmprunt_NumeroEmprunt(numeroEmprunt).stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

    @GetMapping("/Echeances/Date/{date}")
    List<EcheanceDTO> getEcheancesByDate(@PathVariable  String date){
        return echeanceRepository.findByDateEcheance(date).stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

    /////////////////////////////comptabilisation////////////////////////////////////////////





//    @GetMapping("/Signals")
//    List<SignaletiqueDTO> getAllSignal(){
//
//    }
}
