/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjavafx;

import Model.Manager;
import VueController.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pers.persBinaire.BinaryDataManager;
import pers.persXML.XMLDataManager;
import pers.stubData.StubDataManager;

/**
 *
 * @author mafrizot1
 */
public class ProjetJavaFx extends Application {

    private Manager manager;

    private static Stage primaryStage;

    private void chargeRepertoire() {
        //testez de nouvelles méthodes de chargement des données
        manager = new Manager(new StubDataManager());
//        manager = new Manager(new BinaryDataManager());
//        manager = new Manager(new XMLDataManager());
        if (manager.getRepertoire() == null) {
            manager.setDataManager(new StubDataManager());
            manager.chargeRepertoire();
        }
    }

    /**
     * persiste les données du manager
     */
    private void sauveRepertoire() {
        System.out.println("SAUVEGARDE !!!");
        manager.setDataManager(new XMLDataManager());
        manager.sauveRepertoire();
        System.out.println("FIN !!!");
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/projetjavafx/FXMLDocument.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/Vue/MainView.fxml"));

        chargeRepertoire();
        primaryStage = stage;

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/Vue/MainView.fxml"));
        Parent root = fxmlloader.load();
        MainViewController controller = fxmlloader.getController();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/Vue/ajoutTel.css").toExternalForm());
        stage.show();

        controller.setManager(manager);
    }

    /**
     * méthode appelée lors de la fermeture de l'application
     */
    @Override
    public void stop() {
        sauveRepertoire();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
