package com.talla.backmanager.Services.Service.Tiers;

import com.talla.backmanager.Entites.Tiers.Beans.Tiers;
import com.talla.backmanager.Repositories.Tiers.TiersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TiersService {

  TiersRepository tiersRepository;

    public List<Tiers> listeTiers(){
        return  tiersRepository.findAll();
    }
}
