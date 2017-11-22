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
        new Reflections("com.dim.fff.socialnetwork.dataprovider")
                .getTypesAnnotatedWith(Client.class)
                .forEach(client -> {
                    RadioButton button = new RadioButton(client.getSimpleName().replace("Client", ""));
                    button.setToggleGroup(datasetChoiceContainer);
                    container.add(button);
                });

        // select first button
        datasetChoiceContainer.selectToggle(
                datasetChoiceContainer.getToggles().get(0)
        );

        // add on click listener
        datasetChoiceContainer.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            // TODO: send Class<?>, which is selected by radiobutton, to controller
        });

    }

    private static final String MAINVIEW = "mainview.fxml";

}
