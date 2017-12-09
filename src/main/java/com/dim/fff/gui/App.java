package com.dim.fff.gui;

import com.dim.fff.socialnetwork.dataprovider.Client;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.reflections.Reflections;

import java.util.HashMap;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class App extends Application{

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(MAINVIEW));

        Parent root = loader.load();
        primaryStage.setTitle("Find Future Friends");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        // inject controller
        controller = loader.getController();
        addAllDataProviders(scene);
    }

    private void addAllDataProviders(Scene scene){
        // find container for buttons
        ObservableList<Node> container = ((VBox) scene.lookup("#datasetChoiceContainer"))
                .getChildren();

        // create group holder
        final ToggleGroup datasetChoiceContainer = new ToggleGroup();

        // for each client class add radio button
        HashMap<String, Class> classes = new HashMap<>();
        new Reflections("com.dim.fff.socialnetwork.dataprovider")
                .getTypesAnnotatedWith(Client.class)
                .forEach(client -> {
                    String buttonName = client.getSimpleName().replace("Client", "");
                    classes.put(buttonName, client);
                    RadioButton button = new RadioButton(buttonName);
                    button.setToggleGroup(datasetChoiceContainer);
                    container.add(button);
                });

        // add on click listener
        datasetChoiceContainer.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            controller.setClient(classes.get(((RadioButton)new_toggle).getText()));
        });

    }



    private static final String MAINVIEW = "mainview.fxml";

}
