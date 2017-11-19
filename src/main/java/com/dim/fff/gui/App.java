package com.dim.fff.gui;

import com.dim.fff.socialnetwork.basic.BasicNetworkBuilder;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.networkrepo.SocFB;
import eu.mihosoft.scaledfx.ScalableContentPane;
import eu.mihosoft.vrl.workflow.FlowFactory;
import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Network network = new BasicNetworkBuilder(new SocFB()).build();

        primaryStage.setTitle("Find Future Friends");

        // create scalable root pane
        ScalableContentPane canvas = new ScalableContentPane();

        // define background style
        canvas.setStyle("-fx-background-color: linear-gradient(to bottom, rgb(10,32,60), rgb(42,52,120));");

        // create a new flow object
        VFlow flow = FlowFactory.newFlow();

        // make it visible
        flow.setVisible(true);

        network.getAllUsers()
                .stream()
                .limit(1000)
                .forEach(user -> {
            VNode node = flow.newNode();
            node.setTitle(user.getId().toString());
//            node.setId(user.getId().toString());
        });

        network.getAllRelationships().forEach(relationship -> {

        });

        // add two nodes to the flow
        VNode n1 = flow.newNode();
        VNode n2 = flow.newNode();
        n1.addInput("data");
        n1.addOutput("data");

        n2.addInput("data");
        n2.addOutput("data");



        // create skin factory for flow visualization
        FXSkinFactory fXSkinFactory = new FXSkinFactory((Parent) canvas.getContent());

        // generate the ui for the flow
        flow.setSkinFactories(fXSkinFactory);

        //StackPane root = new StackPane();
        primaryStage.setScene(new Scene(canvas, 300, 250));
        primaryStage.setMaximized(true);
        primaryStage.show();

    }
}
