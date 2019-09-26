/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.persBinaire;

import Model.DataManager;
import Model.Repertoire;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sérialiseur / désérialiseur binaire
 *
 * @author Mathis
 */
public class BinaryDataManager implements DataManager {

    @Override
    public Repertoire chargeRepertoire() {
        Repertoire result = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("repertoire.bin"))) {
            System.out.println("TEST");
            result = ((Repertoire) ois.readObject());
            System.out.println("Result : " + result);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void sauveRepertoire(Repertoire repertoire) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("repertoire.bin"))) {
            BinaryRepertoire br = new BinaryRepertoire(repertoire);
            oos.writeObject(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
