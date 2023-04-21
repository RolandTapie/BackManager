package com.talla.backmanager;


import com.talla.backmanager.Configuration.ServiceConfig;
import com.talla.backmanager.Repositories.BanqueRepository;
import com.talla.backmanager.Repositories.CommunRepository;
import com.talla.backmanager.Repositories.EcheanceRepository;
import com.talla.backmanager.Repositories.EmpruntRepository;
import com.talla.backmanager.Services.Imports.Budgetaire.Imports;
import com.talla.backmanager.Services.Imports.Dette.LireInventaireDette;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BackManagerApplicationTests  {

//    @Test
    void contextLoads() {
    }
    
    @Autowired
    BackManagerApplication  backManagerApplication;
    @Test
    void test1(){
        assertEquals("OK",backManagerApplication.test());
    }
}
