/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe réprésentant le répertoire d'un utilisateur
 *
 * @author mafrizot1
 */
public class Repertoire {

    private ObservableList<Personne> contactsObs = FXCollections.observableArrayList();
//    Liste des contacts contenus dans le répertoire
    private final ListProperty<Personne> contacts = new SimpleListProperty<>(contactsObs);

//    Getter de la liste triée des contacts du répertoire
    public ObservableList<Personne> getContacts() {
        return contacts.get().sorted();
    }

//    Accès à la propriété
    public ReadOnlyListProperty<Personne> contactsProperty() {
        return contacts;
    }

    private ObservableList<Groupe> groupesObs = FXCollections.observableArrayList();
//    Liste des groupes contenus dans le répertoire
    private final ListProperty<Groupe> groupes = new SimpleListProperty<>(groupesObs);

//    Getter de la liste des groupes du répertoire
    public ObservableList<Groupe> getGroupes() {
        return groupes.get();
    }

//    Accés à la propriété
    public ReadOnlyListProperty<Groupe> groupesProperty() {
        return groupes;
    }

    /**
     * Constructeur par défaut d'un répertoire
     */
    public Repertoire() {
//        Groupe perdef = new Groupe("Par defaut");
//        groupes.add(perdef);
    }
    
    Repertoire(List<Personne> contacts, List<Groupe> groupes){
        this.contacts.set(FXCollections.observableList(contacts));
        this.groupes.set(FXCollections.observableList(groupes));
    }

    /**
     * Méthode permettant l'ajout d'une personne dans la liste des contacts du
     * répertoire
     *
     * @param pers personne que l'utilisateur souhaite ajouter dans son
     * répertoire
     */
    public void AddPersonne(Personne pers) {
        contacts.add(pers);
    }

    /**
     * Méthode permettant la suppression d'un contact existant de la liste des
     * contacts
     *
     * @param p personne que l'utilisateur souhaite supprimer
     * @throws Exception lorsque le contact n'existe pas
     */
    public void supprimerPersonne(Personne p) throws Exception {
        if (!contacts.contains(p)) {
            throw new Exception("La personne sélectionnée n'existe pas");
        }
        contacts.remove(p);
    }

    /**
     * Méthode permettant l'ajout d'un groupe à la liste des groupes du
     * répertoire
     *
     * @param g groupe que l'utilisateur souhaite ajouter dans son répertoire
     */
    public void addGroupe(Groupe g) {
        groupes.add(g);
    }

    /**
     * Méthode permettant la suppression d'un groupe existant de la liste des
     * groupes
     *
     * @param g groupe que l'utilisateur souhaite supprimer
     * @throws Exception lorsque le groupe n'existe pas
     */
    public void supprimerGroupe(Groupe g) throws Exception {
        if (!groupes.contains(g)) {
            throw new Exception("La personne sélectionnée n'existe pas");
        }
        groupes.remove(g);
    }

    /**
     * Méthode permettant l'ajout d'un contact existant dans un groupe existant
     *
     * @param pers personne que l'utilisateur souhaite ajouter au groupe
     * @param g groupe dans lequel l'utilisateur souhaite ajouter la personne
     */
    public void addPersonneGroupe(Personne pers, Groupe g) {
        if (!groupes.contains(g)) {
            System.err.println("Le groupe sélectionné n'existe pas !");
        } else if (!contacts.contains(pers)) {
            System.err.println("La personne sélectionnée n'existe pas !");
        } else if (g.getContacts().contains(pers)) {
            System.err.println("La personne sélectionnée appartient déjà au groupe !");
        } else {
            g.AddPersonne(pers);
        }
    }

    /**
     * Méthode permettant de récupérer tout les contacts étant des contacts de
     * type personnel
     *
     * @return liste de tous les contacts personnels du répertoire
     */
    public ObservableList<Personne> getContactsPerso() {
        return getContacts().filtered(s -> s.getClass() == PersonnePerso.class);
    }

    /**
     * Méthode permettant de récupérer tout les contacts étant des contacts de
     * type professionnel
     *
     * @return liste de tous les contacts professionnels du répertoire
     */
    public ObservableList<Personne> getContactsPro() {
        return getContacts().filtered(s -> s.getClass() == PersonnePro.class);
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
        Repertoire other = (Repertoire) obj;
        if (contacts == null) {
            if (other.contacts != null) {
                return false;
            }
        } else if (!contacts.equals(other.contacts)) {
            return false;
        }

        if (groupes == null) {
            if (other.groupes != null) {
                return false;
            }
        } else if (!groupes.equals(other.groupes)) {
            return false;
        }
        return true;
    }
}
