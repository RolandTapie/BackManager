package com.talla.backmanager.Services.Imports.Dette;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class LireInventaireDetteTest {

    @Autowired
    LireInventaireDette lireInventaireDette;
    @Value("${chemin.dette.inventaire}")
    String chemin="";
    @Test
    void execution() {
        int nbFichierEX=3;
        int nbEcheanceEX=980;
        String result=lireInventaireDette.execution(chemin);
        assertEquals("extraction OK",result);
        assertEquals(nbEcheanceEX, lireInventaireDette.getNbEcheance());
        assertEquals(nbFichierEX,lireInventaireDette.getNbFichier());
    }
}