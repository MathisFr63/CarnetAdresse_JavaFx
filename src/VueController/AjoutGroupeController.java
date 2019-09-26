/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueController;

import Model.Groupe;
import Model.Manager;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pers.stubData.StubDataManager;

/**
 *
 * @author ilnourine
 */
public class AjoutGroupeController {

//    Propriété représentant le manager possédant toutes les données à utiliser
    private ObjectProperty<Manager> manager = new SimpleObjectProperty<>();

//    Getter du manager
    public Manager getManager() {
        return manager.get();
    }

//    Setter du manager
    public void setManager(Manager param) {
        manager.set(param);
    }

//    Accés à la propriété
    public ObjectProperty<Manager> managerProperty() {
        return manager;
    }

    @FXML
//     TextField permettant de rentrer le nom du groupe à ajouter
    private TextField TFNom;

    @FXML
//     ListView permettant l'affichage de la liste des groupes déjà existants dans le répertoire
    private ListView<Groupe> LVGroupe;

    /**
     * Méthode appelée lors de l'initialisation du controller
     *
     */
    public void initialize() {
//        getManager().selectedGroupeProperty().bind(LVGroupe.getSelectionModel().selectedItemProperty());
    }

    /**
     * Méthode permettant d'ajouter le groupe dans la liste des groupes du
     * répertoire de l'utilisateur
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "ajouter")
     */
    public void ButtonCreateGroupe(ActionEvent event) {
        if (TFNom.getText() != null) {
            getManager().createGroupe(new Groupe(TFNom.getText()));
        }
    }

    /**
     * Méthode permettant la suppression du groupe sélectionné de la liste des
     * groupes du répertoire de l'utilisateur
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "supprimer")
     */
    public void ButtonDeleteGroupe(ActionEvent event) {
        getManager().setSelectedGroupe(LVGroupe.getSelectionModel().getSelectedItem());
        try {
            showMessage(Alert.AlertType.CONFIRMATION, null, "Voulez-vous vraiment supprimer " + getManager().getSelectedGroupe().getNom() + " de votre liste de groupe ?", ButtonType.YES, ButtonType.NO)
                    .filter(bouton -> bouton == ButtonType.YES)
                    .ifPresent(bouton -> {
                        System.out.println("Sélectionné : " + LVGroupe.getSelectionModel().getSelectedItem());
                        getManager().supprimerGroupe();
                    }
                    );
        } catch (Exception e) {
        }
        // Test pour afficher le nombre de contacts dans le répertoire.
        System.out.println(getManager().getRepertoire().getContacts().size());
    }

    /**
     * Méthode permettant l'affichage d'une fenêtre lors de la suppression d'un
     * groupe afin de vérifier si l'utilisateur souhaite réellement supprimer le
     * groupe
     *
     * @param type
     * @param header
     * @param message
     * @param lesBoutonsDifferents
     * @return
     */
    private Optional<ButtonType> showMessage(Alert.AlertType type, String header, String message, ButtonType... lesBoutonsDifferents) {
        Alert laFenetre = new Alert(type);
        laFenetre.setHeaderText(header);
        laFenetre.setContentText(message);
        if (lesBoutonsDifferents.length > 0) {
            laFenetre.getButtonTypes().clear();
            laFenetre.getButtonTypes().addAll(lesBoutonsDifferents);
        }
        return laFenetre.showAndWait();
    }

}
