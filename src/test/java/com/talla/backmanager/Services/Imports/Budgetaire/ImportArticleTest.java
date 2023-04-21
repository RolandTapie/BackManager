package com.talla.backmanager.Services.Imports.Budgetaire;

import com.talla.backmanager.Services.Imports.Dette.LireInventaireDette;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
class ImportArticleTest {

    @Mock
    LireInventaireDette lireInventaireDette;
    @Autowired
    ImportArticle importArticle;

    @Test
    void execution() throws FileNotFoundException {
        assertEquals("fichier vide",importArticle.Execution());
    }
}