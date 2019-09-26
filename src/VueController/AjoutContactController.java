/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueController;

import Model.Manager;
import Model.PersonnePerso;
import Model.PersonnePro;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pers.stubData.StubDataManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mathis
 */
public class AjoutContactController {

//    Propriété représentant le manager possédant toutes les données et à utiliser
    private ObjectProperty<Manager> manager = new SimpleObjectProperty<>(new Manager(new StubDataManager()));

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
//    ChoiceBox permettant à l'utilisateur de choisir le type de personne (personnel / professionnel) de la personne qu'il veut ajouter
    private ChoiceBox<String> CBType;

    @FXML
//    TextFields permettant de rentrer le nom, le prénom, le numéro téléphone, et l'adresse mail du contact à ajouter
    private TextField TFNom, TFPrénom, TFTéléphone, TFMail;

    @FXML
//    DatePicker permettant de rentrer la date de naissance du contact à ajouter
    private DatePicker DPNaissance;

    /**
     * Méthode appelée lors de l'initialisation du controller
     *
     */
    public void initialize() {
        List list1 = new ArrayList();
        list1.add("Personnel");
        list1.add("Professionnel");
        ObservableList someObservableList = FXCollections.observableList(list1);
        CBType.setItems(someObservableList);
        CBType.getSelectionModel().select(0);
        DPNaissance.setValue(LocalDate.now());
    }

    /**
     * Méthode permettant d'ajouter la personne dans la liste des contacts du
     * répertoire de l'utilisateur
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "ajouter")
     */
    public void ButtonAddPersonne(ActionEvent event) {
        try {
            switch (CBType.getSelectionModel().getSelectedIndex()) {
                case 1:
                    getManager().ajouterPersonne(new PersonnePro(getManager().getNextId(), TFNom.getText(), TFPrénom.getText(), TFMail.getText(), DPNaissance.getValue()));
                    break;
                default:
                    getManager().ajouterPersonne(new PersonnePerso(getManager().getNextId(), TFNom.getText(), TFPrénom.getText(), TFMail.getText(), DPNaissance.getValue()));
            }
            Stage stage = (Stage) TFNom.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }
}
