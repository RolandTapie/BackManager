package com.talla.backmanager.Services.Service.XML;

import com.talla.backmanager.Services.Imports.XML.MySAXApp;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import java.io.File;
import java.io.IOException;

public class LectureXML {
    public static String Execution() throws SAXException, IOException {

        XMLReader xr= XMLReaderFactory.createXMLReader();
        MySAXApp handler = new MySAXApp();
        xr.setContentHandler(handler);

        int nbFichier=0;

        String chemin = "C:\\Users\\Liege\\Downloads\\XML" ;
        File path = new File(chemin); //Définition chemin de base vers les fichier CODA
        File [] files = path.listFiles(); //Création d'un tableau de fichier pour stocker la liste des fichier du répertoire

        // On revient voir dans le repertoire s'il existe encore des fichiers CODA

        int nbInjection=0;

        controle: for (int i=0; i< files.length; i++)
        {

            relecture: files = path.listFiles(); //Création d'un tableau de fichier pour stocker la liste des fichier du répertoire


            if ((files[i].getName().indexOf(".xml") > 0) && i < files.length) //On teste s'il existe un fichier CODA
            {
                nbFichier++;
                System.out.println("  > "+files[i].getAbsolutePath());
                handler.setNomFichier(files[i].getName());
                xr.parse(files[i].getAbsolutePath());
            }

        }

        System.out.println("  > Nombre de fichiers traités :" + nbFichier);

        return "Import OK";
    }

}
