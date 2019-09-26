/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Manager faisant office de façade.
 *
 * @author Mathis
 */
public class Manager {

//    Le responsable de la persistance, permettant le chargement et la sauvegarde des données
    private DataManager dataManager;

//    Setter du DataManager
    public void setDataManager(DataManager dm) {
        dataManager = dm;
    }

//    Répertoire comportant les contacts et les groupes de l'utilisateur.
    private final ObjectProperty<Repertoire> repertoire = new SimpleObjectProperty<>();

//    Getter du répertoire
    public Repertoire getRepertoire() {
        return repertoire.get();
    }

//    Setter du répertoire
    private void setRepertoire(Repertoire value) {
        repertoire.set(value);
    }

//    Accés à la propriété
    public ObjectProperty repertoireProperty() {
        return repertoire;
    }

//    Personne sélectionnée
    private final ObjectProperty<Personne> selectedPersonne = new SimpleObjectProperty<>();

//    Getter de la personne sélectionnée
    public Personne getSelectedPersonne() {
        return selectedPersonne.get();
    }

//    Setter de la personne sélectionnée
    public void setSelectedPersonne(Personne value) {
        selectedPersonne.set(value);
    }

//    Accés à la propriété
    public ObjectProperty selectedPersonneProperty() {
        return selectedPersonne;
    }

//    Groupe sélectionné
    private final ObjectProperty<Groupe> selectedGroupe = new SimpleObjectProperty<>();

//    Getter du groupe sélectionné
    public Groupe getSelectedGroupe() {
        return selectedGroupe.get();
    }

//    Setter du groupe sélectionné
    public void setSelectedGroupe(Groupe value) {
        selectedGroupe.set(value);
    }

//    Accés à la propriété
    public ObjectProperty selectedGroupeProperty() {
        return selectedGroupe;
    }

//    Téléphone sélectionné
    private final ObjectProperty<Telephone> selectedTelephone = new SimpleObjectProperty<>();

//    Getter du téléjphone sélectionné
    public Telephone getSelectedTelephone() {
        return selectedTelephone.get();
    }

//    Setter du téléjphone sélectionné
    public void setSelectedTelephone(Telephone value) {
        selectedTelephone.set(value);
    }

//    Accés à la propriété
    public ObjectProperty SelectedTelephoneProperty() {
        return selectedTelephone;
    }
    
    private ObservableList<Personne> contactsObs = FXCollections.observableArrayList();
//    Liste des contacts triés selon le souhait de l'utilisateur (aprés une recherche ou un tri selon le type)
    private final ListProperty<Personne> contacts = new SimpleListProperty<>(contactsObs);

//    Getter des contacts triés
    public ObservableList<Personne> getContacts() {
        return contacts.get();
    }
    
    private void setContacts(ObservableList<Personne> value) {
        contacts.set(value);
    }

//    Accés à la propriété
    public ReadOnlyListProperty<Personne> contactsProperty() {
        return contacts;
    }
    
    private ObservableList<Groupe> groupesObs = FXCollections.observableArrayList();
//    Liste des groupes auxquels appartient le contact sélectionné
    private ListProperty<Groupe> groupes = new SimpleListProperty<>(groupesObs);

//    Getter de la liste des groupes auxquels appartient le contact sélectionné
    public ObservableList<Groupe> getGroupes() {
        return groupes.get();
    }
    
    private void setGroupes(ObservableList<Groupe> value) {
        groupes.set(value);
    }

//    Accés à la propriété
    public ReadOnlyListProperty<Groupe> GroupesProperty() {
        return groupes;
    }

//    Souhait de l'utilisateur pour afficher les contacts personnels
    private final BooleanProperty CBPerso = new SimpleBooleanProperty();

//    Getter du souhait de l'utilisateur au sujet de l'affichage des contacts personnels
    public boolean isCBPerso() {
        return CBPerso.get();
    }

//    Setter du souhait de l'utilisateur au sujet de l'affichage des contacts personnels
    private void setCBPerso(boolean value) {
        CBPerso.set(value);
    }

//    Accés à la propriété
    public BooleanProperty CBPersoProperty() {
        return CBPerso;
    }

//    Souhait de l'utilisateur pour afficher les contacts professionnels
    private final BooleanProperty CBPro = new SimpleBooleanProperty();

//    Getter du souhait de l'utilisateur au sujet de l'affichage des contacts professionnels
    public boolean isCBPro() {
        return CBPro.get();
    }

//    Setter du souhait de l'utilisateur au sujet de l'affichage des contacts professionnels
    private void setCBPro(boolean value) {
        CBPro.set(value);
    }

//    Accés à la propriété
    public BooleanProperty CBProProperty() {
        return CBPro;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * constructeur par défaut
     */
    public Manager() {
    }

    /**
     * constructeur du manager permettant de choisir le dataManager concret
     * Charge automatiquement les données en utilisant le dataManager
     *
     * @param dm dataManager responsable de la persistance et de la récupération
     * des données
     */
    public Manager(DataManager dm) {
        setDataManager(dm);
        if (dataManager != null) {
            chargeRepertoire();
        }
        setContacts(getRepertoire().getContacts());
        setCBPerso(true);
        setCBPro(true);
    }

    /**
     * Méthode permettant le chargement du répertoire sans se préoccuper du type
     * de persistance utilisé
     */
    public void chargeRepertoire() {
        if (dataManager != null) {
            setRepertoire(dataManager.chargeRepertoire());
        }
    }

    /**
     * Méthode permettant la sauvegarde du répertoire sans se préoccuper du type
     * de persistance utilisé
     */
    public void sauveRepertoire() {
        if (dataManager != null) {
            dataManager.sauveRepertoire(getRepertoire());
        }
    }

    /**
     * Méthode permettant l'ajout d'un nouveau contact dans le répertoire
     *
     * @param p personne que l'utilisateur souhaite ajouter dans le répertoire
     */
    public void ajouterPersonne(Personne p) {
        getRepertoire().AddPersonne(p);
    }

    /**
     * Méthode permettant de supprimer du répertoire la personne sélectionnée
     */
    public void supprimerPersonne() {
        try {
            getRepertoire().supprimerPersonne(getSelectedPersonne());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Méthode permettant l'ajout d'un nouveau groupe dans la liste des groupes
     * du répertoire
     *
     * @param g groupe que l'utilisateur souhaite ajouter dans le répertoire
     */
    public void createGroupe(Groupe g) {
        if (g.getNom() != null && g.getNom().trim().length() > 0) {
            getRepertoire().addGroupe(g);
        } else {
            System.err.println("Erreur : le groupe doit posséder un nom correct");
        }
    }

    /**
     * Méthode permettant la suppression du groupe sélectionné de la liste des
     * groupes du répertoire
     */
    public void supprimerGroupe() {
        try {
            getRepertoire().supprimerGroupe(getSelectedGroupe());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Méthode permettant l'ajout de la personne sélectionnée dans le groupe
     * sélectionné
     */
    public void addPersonneGroupe() {
        System.out.println(getSelectedGroupe() + " | " + getSelectedPersonne());
        getRepertoire().addPersonneGroupe(getSelectedPersonne(), getSelectedGroupe());
    }

    /**
     * Méthode permettant de récupérer les contacts que veut afficher
     * l'utilisateur (personnels / professionnels / tous) et de les mettre dans
     * la liste des contacts triés
     */
    public void getContactsChanged() {
        if (isCBPro() && !isCBPerso()) {
            setContacts(getRepertoire().getContactsPro());
        } else if (isCBPerso() && !isCBPro()) {
            setContacts(getRepertoire().getContactsPerso());
        } else {
            setContacts(getRepertoire().getContacts());
        }
    }

    /**
     * Méthode permettant de mettre à jour le souhait de l'utilisateur quant à
     * l'affiche des contacts de type personnel et professionnel
     *
     * @param perso true si l'utilisateur souhaite voir les contacts personnels,
     * false sinons
     * @param pro true si l'utilisateur souhaite voir les contacts
     * professionnels, false sinon
     */
    public void checkBoxChanged(boolean perso, boolean pro) {
        setCBPerso(perso);
        setCBPro(pro);
        getContactsChanged();
    }

    /**
     * Méthode permettant de récupérer tous les groupes du répertoire possédant
     * le contact sélectionné
     *
     */
    public void getGroupesChanged() {
//        setGroupes(getRepertoire().getGroupes().filtered(g -> g.getContacts().contains(getSelectedPersonne())));
        getGroupes().clear();
        getGroupes().addAll(getRepertoire().getGroupes().filtered(g -> g.getContacts().contains(getSelectedPersonne())));
    }

    /**
     * Méthode permettant de rechercher les contacts par leur nom, prénom,
     * téléphone et début d'adresse mail (avant le @). Les contacts
     * correspondants sont mis dans la liste des contacts triés
     *
     * @param recherche chaîne de caractères recherchée par l'utilisateur
     */
    public void rechercher(String recherche) {
        if (recherche == null || recherche.equals("")) {
            getContactsChanged();
            return;
        }
        setContacts(getRepertoire().getContacts().filtered(s -> s.getNom().contains(recherche) || s.getPrenom().contains(recherche) || s.getTelephones().filtered(t -> t.getNumero().contains(recherche)).size() > 0 || s.getMail().split("@")[0].contains(recherche)));
    }

    /**
     * Méthode permettant de rechercher l'id du contact à créer
     * (auto-incrémentation) permettant de ne jamais avoir 2 fois le même id
     * pour un utilisateur
     *
     * @return valeur de l'id du contact à créer
     */
    public int getNextId() {
        int tmp = -1;
        for (Personne pers : getRepertoire().getContacts()) {
            if (pers.getId() > tmp) {
                tmp = pers.getId();
            }
        }
        return tmp + 1;
    }

    /**
     * Méthode permettant de supprimer le téléphone sélectionné de la liste des
     * téléphones du contact sélectionné
     */
    public void supprimerTel() {
        getSelectedPersonne().supprimerTel(getSelectedTelephone());
    }

    /**
     * Méthode permettant d'ajouter un nouveau téléphone à la liste des
     * téléphones du contact sélectionné
     *
     * @param telephone que l'utilisateur souhaite ajouter au contact
     * sélectionné
     */
    public void ajouterTelephone(Telephone telephone) {
        System.out.println("Téléphone : " + telephone);
        getSelectedPersonne().addTelephone(telephone);
    }
}
