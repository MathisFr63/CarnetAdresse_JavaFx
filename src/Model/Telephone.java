/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mathis
 */
public class Telephone {

//    Numéro de téléphone
    private final StringProperty numero = new SimpleStringProperty();

//    Getter du numéro de téléphone
    public String getNumero() {
        return numero.get();
    }

//    Setter du numéro de téléphone
    private void setNumero(String value) {
        numero.set(value);
    }

//    Accés à la propriété
    public StringProperty numeroProperty() {
        return numero;
    }

    public final StringProperty infosProperty() {
        return new SimpleStringProperty(getType() + ": " + getNumero());
    }

//    Type de téléphone
    private final ObjectProperty<TypeTelephone> type = new SimpleObjectProperty<>();

//    Getter du type de téléphone
    public TypeTelephone getType() {
        return type.get();
    }

//    Setter du type de téléphone
    private void setType(TypeTelephone value) {
        type.set(value);
    }

//    Accés à la propriété
    public ObjectProperty typeProperty() {
        return type;
    }

    /**
     * Constructeur par défaut d'un téléphone
     *
     * @param numero de téléphone
     * @param type de téléphone
     */
    public Telephone(String numero, TypeTelephone type) {
        setNumero(numero);
        setType(type);
    }

    /**
     * Méthode permettant de retourner les informations du téléphone sous forme
     * de chaîne de caractères
     *
     * @return chaîne de caractères reprèsentant les informations concernant le
     * téléphone (son type et son numéro)
     */
    @Override
    public String toString() {
        return getType().toString() + " : " + getNumero();
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
        Telephone other = (Telephone) obj;
        if (numero == null) {
            if (other.numero != null) {
                return false;
            }
        } else if (!numero.equals(other.numero)) {
            return false;
        }

        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
}
