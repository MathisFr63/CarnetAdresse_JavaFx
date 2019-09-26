/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * Classe permettant de contrôler la création d'un répertoire.
 *
 * @author mafrizot1
 */
public class RepertoireFactory {

    /**
     * Méthode permettant de créer un répertoire à partir de contacts et de
     * groupes existants
     *
     * @param contacts que l'on veut ajouter directement au répertoire
     * @param groupes que l'on veut ajouter directement au répertoire
     * @return Répertoire qui vient d'être créé
     */
    public static Repertoire create(List<Personne> contacts, List<Groupe> groupes) {
        return new Repertoire(contacts, groupes);
    }

    /**
     * Méthode permettant de créer un répertoire ne contenant aucune données
     * (liste de contacts et de groupes vide)
     *
     * @return Répertoire qui vient d'être créé
     */
    public static Repertoire create() {
        return new Repertoire();
    }
}
