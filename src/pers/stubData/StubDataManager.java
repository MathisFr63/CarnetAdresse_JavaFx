/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.stubData;

import Model.DataManager;
import Model.Groupe;
import Model.Personne;
import Model.PersonnePerso;
import Model.PersonnePro;
import Model.Repertoire;
import Model.RepertoireFactory;
import Model.Telephone;
import Model.TypeTelephone;
import java.time.LocalDate;

/**
 *
 * @author Mathis
 */
public class StubDataManager implements DataManager {

    @Override
    public Repertoire chargeRepertoire() {
        Repertoire rep = RepertoireFactory.create();
        try {
//            Personne mathis = new PersonnePerso(1, "FRIZOT", "Mathis", "0102030405", "mathis.frizot@gmail.com", LocalDate.of(1998, 11, 9));
//            Personne alexis = new PersonnePerso(2, "FRIZOT", "Alexis", "0202030405", "alexis.frizot@gmail.com", LocalDate.of(2000, 9, 21));
//            Personne colin = new PersonnePerso(3, "FRIZOT", "Colin", "0302030405", "colin.frizot@gmail.com", LocalDate.of(2003, 8, 25));
//            Personne jean = new PersonnePro(3, "Jean", "Dupont", "0402030405", "jean.dupont@gmail.com", LocalDate.of(1856, 12, 24));
//            Personne pierre = new PersonnePro(3, "Pierre", "Henry", "0502030405", "jesaispas.henry@gmail.com", LocalDate.of(2017, 6, 13));
            Personne mathis = new PersonnePerso(1, "FRIZOT", "Mathis", "mathis.frizot@gmail.com", LocalDate.of(1998, 11, 9));
            mathis.addTelephone(new Telephone("0102030405", TypeTelephone.Fixe));
            mathis.addTelephone(new Telephone("0602393655", TypeTelephone.Portable));
            mathis.addTelephone(new Telephone("0602393655", TypeTelephone.Portable));   

            Personne alexis = new PersonnePerso(2, "FRIZOT", "Alexis", "alexis.frizot@gmail.com", LocalDate.of(2000, 9, 21));
            Personne colin = new PersonnePerso(3, "FRIZOT", "Colin", "colin.frizot@gmail.com", LocalDate.of(2003, 8, 25));
            Personne jean = new PersonnePro(3, "Jean", "Dupont", "jean.dupont@gmail.com", LocalDate.of(1856, 12, 24));
            Personne pierre = new PersonnePro(3, "Pierre", "Henry", "jesaispas.henry@gmail.com", LocalDate.of(2017, 6, 13));
            rep.AddPersonne(mathis);
            rep.AddPersonne(alexis);
            rep.AddPersonne(colin);
            rep.AddPersonne(jean);
            rep.AddPersonne(pierre);

            Groupe jds = new Groupe("Jeux de société");
            Groupe ecole = new Groupe("École");
            Groupe famille = new Groupe("Famille");
            rep.addGroupe(jds);
            rep.addGroupe(ecole);
            rep.addGroupe(famille);

            rep.addPersonneGroupe(mathis, famille);
            rep.addPersonneGroupe(alexis, famille);
            rep.addPersonneGroupe(alexis, jds);
            rep.addPersonneGroupe(colin, famille);
            rep.addPersonneGroupe(colin, jds);
            rep.addPersonneGroupe(jean, ecole);
            rep.addPersonneGroupe(pierre, ecole);
        } catch (Exception e) {
        }
//        Affichage du nombre de contacts dans chaque groupe.
        for (Groupe groupe : rep.getGroupes()) {
            System.out.println(groupe.getInfos());
        }
        return rep;
    }

    @Override
    public void sauveRepertoire(Repertoire rep) {
        throw new UnsupportedOperationException("Non supporté lorsque les données sont dans un stub"); //To change body of generated methods, choose Tools | Templates.
    }

}
