/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 * Classe héritant de la classe Personne et étant un contact de type personnel
 *
 * @author Mathis
 */
public class PersonnePerso extends Personne {

    /**
     * Constructeur par défaut d'une personne de type personnel
     */
    public PersonnePerso() {
    }

    /**
     * Constructeur d'une personne de type personnel
     *
     * @param id de la personne
     * @param nom de la personne
     * @param prenom de la personne
     * @param mail de la personne
     * @param ddn de la personne
     * @throws Exception lorsque le nom ou le prénom ne sont pas remplis
     */
    public PersonnePerso(int id, String nom, String prenom, String mail, LocalDate ddn) throws Exception {
        super(id, nom, prenom, mail, ddn);
    }

    /**
     * Méthode permettant d'afficher les informations du contact
     *
     * @return string étant les informations du contact plus son type
     * (personnel)
     */
    @Override
    public String toString() {
        return super.toString() + " Personnel";
    }
}
