/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.persBinaire;

import Model.Groupe;
import Model.Personne;
import Model.Repertoire;
import Model.RepertoireFactory;
import com.sun.org.apache.xerces.internal.impl.ExternalSubsetResolver;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * proxy permettant de sérialiser/désérialiser un Répertoire en binaire tout en
 * profitant du système de sérialisation de Java
 *
 * @author Mathis
 */

// Ne marche pas pour l'instant, c'est que des tests
public class BinaryRepertoire extends Repertoire implements Externalizable {

    public BinaryRepertoire() {
        this(RepertoireFactory.create());
    }

    public BinaryRepertoire(Repertoire model) {
        this.model = model;
    }

    private Repertoire model;

    public Repertoire getModel() {
        return model;
    }

    @Override
    public ReadOnlyListProperty<Personne> contactsProperty() {
        return model.contactsProperty();
    }

    @Override
    public ReadOnlyListProperty<Groupe> groupesProperty() {
        return model.groupesProperty();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Je vais écrire le binaire !!!");
        out.writeObject(new ArrayList<>(getContacts()));
        out.writeObject(new ArrayList<>(getGroupes()));
        System.out.println("J'ai écrit correctement le binaire");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Je vais lire le binaire !!");
        List<Personne> contacts = ((ArrayList<Personne>) in.readObject());
        List<Groupe> groupes = ((ArrayList<Groupe>) in.readObject());
        model = RepertoireFactory.create(contacts, groupes);
        System.out.println("J'ai lu correctement le binaire");
    }
}
