package com.talla.backmanager.Controlleurs.Services;

import com.talla.backmanager.DTO.Mappers.Convert;
import com.talla.backmanager.DTO.Objects.EcheanceDTO;
import com.talla.backmanager.Entites.Classes.Echeance;
import com.talla.backmanager.Repositories.EcheanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EcheanceRequest {
    final EcheanceRepository echeanceRepository;
    final Convert convert;
    public EcheanceRequest(EcheanceRepository echeanceRepository, Convert convert) {
        this.echeanceRepository = echeanceRepository;
        this.convert = convert;
    }

    public List<EcheanceDTO> getAllEcheances(){
        return echeanceRepository.findAll().stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

    public List<EcheanceDTO> getEcheanceByNumEmprunt(String numeroEmprunt){
        return echeanceRepository.findByEmprunt_NumeroEmprunt(numeroEmprunt).stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

    public List<EcheanceDTO> getEcheanceByDate(String date){
        return echeanceRepository.findByDateEcheance(date).stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }

}
