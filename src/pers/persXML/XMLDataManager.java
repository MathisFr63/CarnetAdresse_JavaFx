/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.persXML;

import Model.DataManager;
import Model.Repertoire;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Mathis
 */
public class XMLDataManager implements DataManager {

    @Override
    public Repertoire chargeRepertoire() {
        Repertoire result = null;
        try (XMLDecoder ois = new XMLDecoder(new FileInputStream("repertoire.xml"))) {
            result = ((XMLRepertoire) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void sauveRepertoire(Repertoire rep) {
        try (XMLEncoder oos = new XMLEncoder(new FileOutputStream("repertoire.xml"))) {
            XMLRepertoire br = new XMLRepertoire(rep);
            oos.writeObject(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
