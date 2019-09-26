/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.persXML;

import Model.Groupe;
import Model.Personne;
import Model.Repertoire;
import Model.RepertoireFactory;
import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Mathis
 */
public class XMLRepertoire extends Repertoire implements Serializable {

    public XMLRepertoire() {
        this(RepertoireFactory.create());
    }

    public XMLRepertoire(Repertoire model) {
        this.model = model;
        contacts = new SimpleListProperty<>(getContacts());
        groupes = new SimpleListProperty<>(getGroupes());
    }

    private transient Repertoire model;

    public Repertoire getModel() {
        return model;
    }

    private final ListProperty<Personne> contacts;

    @Override
    public ReadOnlyListProperty<Personne> contactsProperty() {
        return model.contactsProperty();
    }

    @Override
    public ObservableList<Personne> getContacts() {
        return model.getContacts();
    }

    private final ListProperty<Groupe> groupes;

    @Override
    public ReadOnlyListProperty<Groupe> groupesProperty() {
        return model.groupesProperty();
    }

    @Override
    public ObservableList<Groupe> getGroupes() {
        return model.getGroupes();
    }

}
