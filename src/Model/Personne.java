/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe abstraite représentant un contact
 *
 * @author mafrizot1
 */
public abstract class Personne implements Serializable{

//     Id définissant la personne
    private final IntegerProperty id = new SimpleIntegerProperty();

//    Getter de l'id de la personne
    public int getId() {
        return id.get();
    }

//    Accés à la propriété
    public IntegerProperty idProperty() {
        return id;
    }

//     Nom de la personne
    private final StringProperty nom = new SimpleStringProperty();

//    Getter du nom de la personne
    public String getNom() {
        return nom.get();
    }

//    Setter du nom de la personne avec la bonne typographie
    public void setNom(String value) {
        nom.set(value.toUpperCase());
    }

//    Accés à la propriété
    public StringProperty nomProperty() {
        return nom;
    }

    // Prénom de la personne
    private final StringProperty prenom = new SimpleStringProperty();

//    Getter du prénom de la personne
    public String getPrenom() {
        return prenom.get();
    }

//    Setter du prénom de la personne avec la bonne typographie (reste à le faire pour les prénoms composés)
    public void setPrenom(String value) {
        String cap = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        prenom.set(cap);
    }

//    Accés à la propriété
    public StringProperty prenomProperty() {
        return prenom;
    }

    // Identité de la personne c'est-à-dire son nom et son prénom
    public StringProperty identiteProperty() {
        return new SimpleStringProperty(getNom() + " " + getPrenom());
    }

//    Getter de l'identité de la personne (retourne son nom et son prénom)
    public String getIdentite() {
        return identiteProperty().get();
    }

    private ObservableList<Telephone> telephonesObs = FXCollections.observableArrayList();
//    Liste des téléphones possédant le contact
    private final ListProperty<Telephone> telephones = new SimpleListProperty<>(telephonesObs);

//    Getter de la liste triée des téléphones du contact
    public ObservableList<Telephone> getTelephones() {
        return telephones.get().sorted();
    }

//    Setter de la liste des téléphones du contact
    public void setTelephones(ObservableList<Telephone> value) {
        telephones.set(value);
    }

//    Accès à la propriété
    public ReadOnlyListProperty<Telephone> telephonesProperty() {
        return telephones;
    }

//    Adresse mail de la personne
    private final StringProperty mail = new SimpleStringProperty();

//    Getter de l'adresse mail de la personne
    public String getMail() {
        return mail.get();
    }

//    Setter de l'adresse mail de la personne
    public void setMail(String value) {
        mail.set(value);
    }

//    Accés à la propriété
    public StringProperty mailProperty() {
        return mail;
    }

//    Date de naissance de la personne
    private final ObjectProperty<LocalDate> ddn = new SimpleObjectProperty<>();

//    Getter de la date de naissance de la personne
    public LocalDate getDdn() {
        return ddn.get();
    }

//    Setter de la date de naissance de la personne
    public void setDdn(LocalDate value) {
        ddn.set(value);
    }

//    Accés à la propriété
    public ObjectProperty ddnProperty() {
        return ddn;
    }

    /**
     * Constructeur par défaut d'une personne
     */
    public Personne() {
    }

    /**
     * Constructeur d'une personne prenant son id, son nom, son prénom, son
     * numéro de téléphone, son adresse mail et sa date de naissance
     *
     * @param id de la personne
     * @param nom de la personne
     * @param prenom de la personne
     * @param mail de le personne
     * @param ddn de la personne
     * @throws Exception lorsque le nom ou le prénom ne sont pas remplis
     */
    public Personne(int id, String nom, String prenom, String mail, LocalDate ddn) throws Exception {
        if (nom == null) {
            throw new Exception("Vous devez entrer un nom");
        }

        if (prenom == null) {
            throw new Exception("Vous devez entrer un prénom");
        }

        this.id.set(id);
        setNom(nom);
        setPrenom(prenom);
        setMail(mail);
        setDdn(ddn);
    }

    /**
     * Méthode permettant d'ajouter un téléphone à la liste des téléphones
     *
     * @param telephone que l'utilisateur souhaite ajouter
     */
    public void addTelephone(Telephone telephone) {
        telephones.add(telephone);
    }

    /**
     * Méthode permettant de supprimer un téléphone de la liste des téléphones
     *
     * @param telephone que l'utilisateur souhaite supprimer
     */
    public void supprimerTel(Telephone telephone) {
        telephones.remove(telephone);
    }

    /**
     * Méthode permettant de retourner les informations de la personne sous
     * forme de chaîne de caractères
     *
     * @return string contenant les informations de la personne (nom, prénom,
     * téléphone, mail, date de naissance)
     */
    @Override
    public String toString() {
        return String.format("%s %s : %s | %s (%s)", getNom(), getPrenom(), getTelephones(), getMail(), getDdn().toString());
    }

    /**
     * Protocole d'égalité de la classe Personne
     *
     * @param obj Objet que l'on veut comparer
     * @return true si les objets sont égaux, false sinon
     */
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
        Personne other = (Personne) obj;
        if (id != other.id) {
            return false;
        }

        if (nom == null) {
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        if (prenom == null) {
            if (other.prenom != null) {
                return false;
            }
        } else if (!prenom.equals(other.prenom)) {
            return false;
        }
        if (telephones == null) {
            if (other.telephones != null) {
                return false;
            }
        } else if (!telephones.equals(other.telephones)) {
            return false;
        }
        if (mail == null) {
            if (other.mail != null) {
                return false;
            }
        } else if (!mail.equals(other.mail)) {
            return false;
        }
        if (ddn == null) {
            if (other.ddn != null) {
                return false;
            }
        } else if (!ddn.equals(other.ddn)) {
            return false;
        }
        return true;
    }
}
