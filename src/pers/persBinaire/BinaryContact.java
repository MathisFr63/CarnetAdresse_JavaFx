/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.persBinaire;

import Model.Personne;
import Model.PersonnePerso;
import Model.PersonnePro;
import Model.Telephone;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Mathis
 */
public class BinaryContact extends Personne implements Externalizable {

    public BinaryContact() {
    }

    public BinaryContact(Personne model) {
        this.model = model;
    }

    private Personne model;

    public Personne getModel() {
        return model;
    }

    public StringProperty nomProperty() {
        return model.nomProperty();
    }

    public StringProperty prenomProperty() {
        return model.prenomProperty();
    }

    public ReadOnlyListProperty<Telephone> telephonesProperty() {
        return model.telephonesProperty();
    }

    public StringProperty mailProperty() {
        return model.mailProperty();
    }

    public ObjectProperty ddnProperty() {
        return model.ddnProperty();
    }

    @Override
    public void writeExternal(ObjectOutput oos) throws IOException {
        if (model.getClass() == PersonnePro.class) {
            oos.writeInt(0);
        } else {
            oos.writeInt(1);
        }
        oos.writeObject(getNom());
        oos.writeObject(getPrenom());
        oos.writeObject(getTelephones());
        oos.writeObject(getMail());
        oos.writeObject(getDdn());
    }

    @Override
    public void readExternal(ObjectInput ois) throws IOException, ClassNotFoundException {
        switch (ois.readInt()) {
            case 0:
                model = new PersonnePro();
                break;

            case 1:
                model = new PersonnePerso();
                break;
        }
        this.setNom((String) ois.readObject());
        this.setPrenom((String) ois.readObject());
        this.setTelephones((ObservableList<Telephone>) ois.readObject());
        this.setMail((String) ois.readObject());
        this.setDdn((LocalDate) ois.readObject());
    }
}
