/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 * Interface permettant d'imposer le chargement et la sauvegarde d'un répertoire
 * à toutes les classes l'implémentant, qu'importe la méthode utilisée.
 *
 * @author Mathis
 */
public interface DataManager {

    public Repertoire chargeRepertoire();

    public void sauveRepertoire(Repertoire rep);

}
