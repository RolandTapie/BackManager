package com.talla.backmanager.Controlleurs.Services;

import com.talla.backmanager.DTO.Mappers.Convert;
import com.talla.backmanager.DTO.Objects.BanqueDTO;
import com.talla.backmanager.Entites.Classes.Banque;
import com.talla.backmanager.Repositories.BanqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BanqueRequest {
    final BanqueRepository banqueRepository;
    final Convert convert;
    public BanqueRequest(BanqueRepository banqueRepository, Convert convert) {
        this.banqueRepository = banqueRepository;
        this.convert = convert;
    }

    public List<BanqueDTO> getAllBanques(){
        return banqueRepository.findAll().stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }
    public List<BanqueDTO> getBanqueByNom(String nom){
        return banqueRepository.findByNom(nom).stream()
                .map(f -> convert.map(f)).collect(Collectors.toList());
    }


}
