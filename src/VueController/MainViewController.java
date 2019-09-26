/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueController;

import Model.Groupe;
import Model.Telephone;
import Model.Manager;
import Model.Personne;
import java.io.IOException;
import javafx.scene.control.Button;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pers.stubData.StubDataManager;
import projetjavafx.ProjetJavaFx;

/**
 * FXML Controller class
 *
 * @author mafrizot1
 */
public class MainViewController {

//    Propriété représentant le manager possédant toutes les données à utiliser
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
//    TextField permettant à l'utilisateur de rentrer les caractères qu'il souhaite rechercher (afin de rechercher un contact)
    private TextField TFRechercher;

    @FXML
//    ListView permettant l'affichage des contacts triés du manager
    private ListView<Personne> listViewPersonne;

    @FXML
//    ListView permettant l'affichage des groupes triés (dont fait partie le contact sélectionné) du manager
    private ListView<Groupe> listViewGroupes;

    @FXML
//    ListView permettant l'affichage des téléphones appartenant au contact sélectionné
    private ListView<Telephone> listViewTelephones;

    @FXML
//    TextFields permettant l'affichage et la modification du nom, du prénom, du numéro de téléphone, et de l'adresse mail du contact sélectionné
    private TextField textFieldNom, textFieldPrenom, textFieldTelephone, textFieldMail;

    @FXML
//    DatePicker permettant l'affichage et la modification de la date de naissance du contact sélectionné
    private DatePicker pickerDateDeNaissance;

    @FXML
//    ChoiceBox permettant à l'utilisateur de choisir dans quel groupe il souhaite ajouter le contact sélectionné
    private ChoiceBox<Groupe> choiceBoxGroupes;

    @FXML
//    CheckBoxes permettant à l'utilisateur de choisir quel type de contacts il souhaite ajouter (personnel / professionnel)
    private CheckBox checkPerso, checkPro;

    @FXML
//    GridPane permettant l'affichage soit des infos de l'utilisateur sélectionné soit des groupes auxquels il appartient
    private GridPane GPInfos, GPGroupes;

    @FXML
//    Label permettant d'afficher "Aucun" à coté de téléphones au lieu d'une liste vide lorsque le contact sélectionné ne possède pas de téléphones
    private Label aucunTelephones, lblNbGroupesContact;

    @FXML
//    Label permettant d'afficher "Aucun" à coté de téléphones au lieu d'une liste vide lorsque le contact sélectionné ne possède pas de téléphones
    private Button buttonSupprimerTel;

    /**
     * Méthode appelée lors de l'initialisation du controller
     *
     */
    public void initialize() {

        getManager().selectedPersonneProperty().bind(listViewPersonne.getSelectionModel().selectedItemProperty());
        getManager().selectedGroupeProperty().bind(choiceBoxGroupes.getSelectionModel().selectedItemProperty());
        getManager().SelectedTelephoneProperty().bind(listViewTelephones.getSelectionModel().selectedItemProperty());

        listViewPersonne.setCellFactory((param) -> {
            return new ListCell<Personne>() {
                @Override
                protected void updateItem(Personne item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        textProperty().unbind();
                        setText("");
                    } else {
                        textProperty().bind(item.identiteProperty());
                    }
                }
            };
        }
        );

        listViewGroupes.setCellFactory((param) -> {
            return new ListCell<Groupe>() {
                @Override
                protected void updateItem(Groupe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        textProperty().unbind();
                        setText("");
                    } else {
                        textProperty().bind(item.nomProperty());
                    }
                }
            };
        }
        );

        listViewTelephones.setCellFactory((param) -> {
            return new ListCell<Telephone>() {
                @Override
                protected void updateItem(Telephone item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        textProperty().unbind();
                        setText("");
                    } else {
                        textProperty().bind(item.infosProperty());
                    }
                    changeVisibilityTelephones();
                }
            };
        }
        );

//        La suppression d'un contact ne marche pas lorsque l'on bind les property sur celles du Manager. Pour l'instant on fait donc avec la listView.
        listViewPersonne.getSelectionModel().selectedItemProperty().addListener((o, old, newV) -> {
//        getManager().selectedPersonneProperty().addListener((o, old, newV) -> {
            if (old != null) {
                textFieldNom.textProperty().unbindBidirectional(((Personne) old).nomProperty());
                textFieldPrenom.textProperty().unbindBidirectional(((Personne) old).prenomProperty());
                textFieldMail.textProperty().unbindBidirectional(((Personne) old).mailProperty());
                pickerDateDeNaissance.valueProperty().unbindBidirectional(((Personne) old).ddnProperty());
            }
            if (newV != null) {
                textFieldNom.textProperty().bindBidirectional(((Personne) newV).nomProperty());
                textFieldPrenom.textProperty().bindBidirectional(((Personne) newV).prenomProperty());
                textFieldMail.textProperty().bindBidirectional(((Personne) newV).mailProperty());
                pickerDateDeNaissance.valueProperty().bindBidirectional(((Personne) newV).ddnProperty());

                getManager().setSelectedPersonne(listViewPersonne.getSelectionModel().getSelectedItem());
                getManager().getGroupesChanged();
                System.out.println("ListView : " + listViewGroupes.getItems());
                changeVisibilityTelephones();
                listViewPersonne.refresh();
            }
        }
        );

        choiceBoxGroupes.getSelectionModel().selectedItemProperty().addListener((o, old, newV) -> {
            if (old != null) {
            }
            if (newV != null) {
                getManager().setSelectedGroupe(choiceBoxGroupes.getSelectionModel().getSelectedItem());
            }
        }
        );

        checkPerso.setSelected(getManager().isCBPerso());
        checkPro.setSelected(getManager().isCBPro());

        TFRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            getManager().rechercher(TFRechercher.getText());
        });
    }

    /**
     * Méthode permettant à l'utilisateur d'ajouter le contact sélectionnè dans
     * le groupe sélectionné
     *
     * @param event évênement ayant déclenché l'action (clicl sur le bouton
     * "ajouter au groupe")
     */
    public void buttonAddGroupe(ActionEvent event) {
        try {
            getManager().addPersonneGroupe();
        } catch (Exception e) {
        }
    }

    /**
     * Méthode permettant à l'utilisateur d'afficher les informations du contact
     * sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "infos")
     */
    public void buttonAfficherLesInfos(ActionEvent event) {
        GPInfos.visibleProperty().set(true);
        GPGroupes.visibleProperty().set(false);
    }

    /**
     * Méthode permettant à l'utilisateur d'afficher les groupes auxquels
     * appartient le contact sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "groupes")
     */
    public void buttonAfficherLesGroupes(ActionEvent event) {
        GPInfos.visibleProperty().set(false);
        GPGroupes.visibleProperty().set(true);
    }

    /**
     * Méthode permettant à l'utilisateur de supprimer le contact sélectionné du
     * groupe sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "supprimer")
     */
    public void buttonDelPersonne(ActionEvent event) {
        try {
            showMessage(Alert.AlertType.CONFIRMATION, null, "Voulez-vous vraiment supprimer " + getManager().getSelectedPersonne().getIdentite() + " de vos contacts ?", ButtonType.YES, ButtonType.NO)
                    .filter(bouton -> bouton == ButtonType.YES)
                    .ifPresent(bouton -> {
                        getManager().supprimerPersonne();
                        listViewPersonne.getSelectionModel().selectFirst();
                    }
                    );
        } catch (Exception e) {
        }
        // Test pour afficher le nombre de contacts dans le répertoire.
        System.out.println(getManager().getRepertoire().getContacts().size());
    }

    /**
     * Méthode permettant l'affichage d'une fenêtre lors de la suppression d'un
     * contact afin de vérifier si l'utilisateur souhaite réellement supprimer
     * le contact
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

    /**
     * Méthode permettant à l'utilisateur de n'afficher que les utilisateurs du
     * type souhaité (personnel / professionnel)
     *
     * @param event évênement ayant déclenché l'action (clic sur l'une des
     * checkbox "personnel" ou "professionnel")
     */
    public void checkBoxChanged(ActionEvent event) {
        getManager().checkBoxChanged(checkPerso.isSelected(), checkPro.isSelected());
    }

    @FXML
    /**
     * Méthode permettant d'ouvrir une fenêtre amodale afin d'ajouter un contact
     */
    private void clicOuvrirFenetreAmodale() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/Vue/AjoutContact.fxml"));
            Scene scene = new Scene(fxmlloader.load());

            AjoutContactController controller = fxmlloader.getController();
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/Vue/ajoutTel.css").toExternalForm());
            stage.initOwner(ProjetJavaFx.getPrimaryStage());
            stage.show();

            controller.setManager(getManager());
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    /**
     * Méthode permettant d'ouvrir une fenêtre amodale afin de gérer les groupes
     */
    private void clicOuvrirFenetreAmodaleGroupe() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/Vue/AjoutGroupe.fxml"));
            Scene scene = new Scene(fxmlloader.load());

            AjoutGroupeController controller = fxmlloader.getController();
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/Vue/ajoutTel.css").toExternalForm());
            stage.initOwner(ProjetJavaFx.getPrimaryStage());
            stage.show();

            controller.setManager(getManager());
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode permettant à l'utilisateur de supprimer l'utilisateur sélectionné
     * du groupe sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "supprimer")
     */
    public void buttonDelFromGroupe(ActionEvent event) {
//        TO DO
    }

    /**
     * Méthode permettant de mettre à jour l'affichage des téléphones
     */
    private void changeVisibilityTelephones() {
        if (getManager().getSelectedPersonne().getTelephones().size() == 0) {
            listViewTelephones.visibleProperty().set(false);
            aucunTelephones.visibleProperty().set(true);
            buttonSupprimerTel.setVisible(false);

        } else {
            listViewTelephones.visibleProperty().set(true);
            aucunTelephones.visibleProperty().set(false);
            buttonSupprimerTel.setVisible(true);
        }
    }

    /**
     * Méthode permettant d'ouvrir une fenêtre afin que l'utilisateur puisse
     * ajouter un téléphone au contact sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "ajouter")
     */
    public void buttonAjouterTel(ActionEvent event) {
        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/Vue/AjoutTelephone.fxml"));
            Scene scene = new Scene(fxmlloader.load());

            AjoutTelephoneController controller = fxmlloader.getController();
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/Vue/ajoutTel.css").toExternalForm());
            stage.initOwner(ProjetJavaFx.getPrimaryStage());
            stage.show();

            controller.setManager(getManager());
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode permettant à l'utilisateur de supprimer un numéro de téléphone
     * appartenant au contact sélectionné
     *
     * @param event évênement ayant déclenché l'action (clic sur le bouton
     * "supprimer")
     */
    public void buttonSupprimerTel(ActionEvent event) {
        getManager().setSelectedTelephone(listViewTelephones.getSelectionModel().getSelectedItem());
        getManager().supprimerTel();
        listViewTelephones.getSelectionModel().selectFirst();
    }
}
