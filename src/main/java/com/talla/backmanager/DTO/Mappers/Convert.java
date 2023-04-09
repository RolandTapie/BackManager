package com.talla.backmanager.DTO.Mappers;

import com.talla.backmanager.DTO.Objects.BanqueDTO;
import com.talla.backmanager.DTO.Objects.EcheanceDTO;
import com.talla.backmanager.Entites.Classes.Banque;
import com.talla.backmanager.Entites.Classes.Echeance;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public   class Convert {


    public  EcheanceDTO map(Echeance echeance){
        EcheanceDTO echeanceDTO =new EcheanceDTO();
        BeanUtils.copyProperties(echeance,echeanceDTO);
        echeanceDTO.setNumeroEmprunt(echeance.getEmprunt().getNumeroEmprunt());
        return echeanceDTO;
    }

    public BanqueDTO map(Banque banque){
        BanqueDTO banqueDTO =new BanqueDTO();
        BeanUtils.copyProperties(banque,banqueDTO);
        return banqueDTO;
    }

}
