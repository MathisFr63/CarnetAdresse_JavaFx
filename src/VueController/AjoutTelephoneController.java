/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueController;

import Model.Manager;
import Model.PersonnePerso;
import Model.PersonnePro;
import Model.Telephone;
import Model.TypeTelephone;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mathis
 */
public class AjoutTelephoneController {

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
//    ChoiceBox permettant à l'utilisateur de choisir le type de téléphone qu'il veut ajouter
    private ChoiceBox<String> CBType;

    @FXML
//    TextField permettant de rentrer le numéro téléphone à ajouter
    private TextField TFTéléphone;

    @FXML
    private Button buttonAdd;

    /**
     * Méthode appelée lors de l'initialisation du controller
     *
     */
    public void initialize() {
        List<TypeTelephone> list = Arrays.asList(TypeTelephone.values());
        ObservableList someObservableList = FXCollections.observableList(list);
        CBType.setItems(someObservableList);
        CBType.getSelectionModel().select(0);
        System.out.println(CBType.getSelectionModel().getSelectedIndex());
    }

//    Problème pour l'instant j'arrives pas à récupérer le type de téléphone directement depuis la valeur de l'élément sélectionné donc c'est moche
    /**
     * Méthode permettant d'ajouter le téléphone dans la liste des téléphones du
     * contact sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "ajouter")
     */
    public void ButtonAddTelephone(ActionEvent event) {
        try {
//            Problème lors de l'ajout, le type de téléphone n'arrive pas à être récupéré !
//            getManager().ajouterTelephone(new Telephone(TFTéléphone.getText(), TypeTelephone.valueOf(CBType.getSelectionModel().getSelectedItem())));
//            Il ne faudra pas faire comme ça mais seul moyen pour l'instant tant que l'erreur n'est pas résolue.
            switch (CBType.getSelectionModel().getSelectedIndex()) {
                case 0:
                    getManager().ajouterTelephone(new Telephone(TFTéléphone.getText(), TypeTelephone.Fixe));
                    break;
                case 1:
                    getManager().ajouterTelephone(new Telephone(TFTéléphone.getText(), TypeTelephone.Portable));
                    break;
                case 2:
                    getManager().ajouterTelephone(new Telephone(TFTéléphone.getText(), TypeTelephone.Fax));
                    break;
            }
            Stage stage = (Stage) buttonAdd.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("ERREUR");
        }
    }
}
