/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Groupe de contacts ayant un nom et une liste des contacts appartenant à ce
 * groupe
 *
 * @author mafrizot1
 */
public class Groupe implements Serializable{

//    Nom du groupe
    private final StringProperty nom = new SimpleStringProperty();

//    Getter de la propriété
    public String getNom() {
        return nom.get();
    }

//    Setter de la propriété
    private void setNom(String value) {
        nom.set(value);
    }

//    Accès à la propriété
    public StringProperty nomProperty() {
        return nom;
    }

    private ObservableList<Personne> contactsObs = FXCollections.observableArrayList();
//    Liste des contacts appartenants au groupe
    private final ListProperty<Personne> contacts = new SimpleListProperty<>(contactsObs);

//    Getter de la propriété
    public ObservableList<Personne> getContacts() {
        return contacts.get();
    }

//    Accès à la propriété
    public ReadOnlyListProperty<Personne> contactsProperty() {
        return contacts;
    }

    /**
     * Constructeur d'un groupe
     *
     * @param nom du groupe à créer
     */
    public Groupe(String nom) {
        setNom(nom);
    }

//    Méthode permettant l'ajout d'un contact dans la liste des contacts du groupe
    public void AddPersonne(Personne pers) {
        contacts.add(pers);
    }

//    Méthode permettant la suppression d'un contact de la liste des contacts du groupe
    public void deletePers(Personne persA) throws Exception {
        boolean rep = contacts.remove(persA);
        if (!rep) {
            throw new Exception("Personne non ajoutable (Probleme)");
        }
    }

//    Mèthode permettant de retourner des informations sur le groupe (son nom) sous forme de chaîne de caractères.
    @Override
    public String toString() {
        return getNom();
    }

//    Mèthode permettant de retourner des informations sur le groupe (son nom et le nombre de contacts appartenants à ce groupe) sous forme de chaîne de caractères.
    public String getInfos() {
        return getNom() + " : " + contacts.getSize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Groupe other = (Groupe) obj;
        if (nom == null) {
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        if (contacts == null) {
            if (other.contacts != null) {
                return false;
            }
        } else if (!contacts.equals(other.contacts)) {
            return false;
        }
        return true;
    }
}
