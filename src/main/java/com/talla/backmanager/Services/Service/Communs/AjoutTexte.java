package com.talla.backmanager.Services.Service.Communs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AjoutTexte {
    public static void append(String filename, String text) {
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename, true);
            bufWriter = new BufferedWriter(fileWriter);
            //InsÃ©rer un saut de ligne
            bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
