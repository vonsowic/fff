package com.dim.fff.gui;

import com.dim.fff.socialnetwork.corenetwork.NetworkBuilder;
import com.dim.fff.socialnetwork.corenetwork.NetworkIterator;
import com.dim.fff.socialnetwork.corenetwork.algorithms.BasicAlgorithm;
import com.dim.fff.socialnetwork.corenetwork.algorithms.CheckIfRelationshipSurvives;
import com.dim.fff.socialnetwork.corenetwork.algorithms.FriendsOfFriendsAreMyFriends;
import com.dim.fff.socialnetwork.corenetwork.algorithms.GroupMembershipProbabilities;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.random.TestDataClient;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 19.11.17
 */
public class Controller implements Initializable {


    @FXML public Label clusteringCoefficient;
    @FXML public Label averagePathLength;
    @FXML public ProgressIndicator progress;
    @FXML public SwingNode graphView;
    @FXML public Button next;
    @FXML public TextField friendsAlgorithm;
    @FXML public TextField groupsAlgorithm;
    @FXML public TextField threshold;

    private Class<? extends DataLoader> client = TestDataClient.class;

    public Class<? extends DataLoader> getClient() {
        return client;
    }

    public void setClient(Class<? extends DataLoader> client) {
        this.client = client;
        try {
            network = createIterator();
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
        createView();
    }

    private NetworkIterator network = createIterator();

    public Controller() throws IOException, IllegalAccessException, InstantiationException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createView();
        progress.setProgress(-1);
        updateGraphValues();
        setOnNumberChangeListener(friendsAlgorithm, FriendsOfFriendsAreMyFriends.class);
        setOnNumberChangeListener(groupsAlgorithm, GroupMembershipProbabilities.class);
        setOnNumberChangeListener(threshold, CheckIfRelationshipSurvives.class);
    }

    private void setOnNumberChangeListener(TextField field, Class<? extends BasicAlgorithm> algorithm){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            getAlgorithmHandler(algorithm)
                    .setValue(Integer.valueOf(newValue));
        });

        field.textProperty().setValue("1");
//        field.textProperty().setValue(getAlgorithmHandler(algorithm).getValue().toString());
    }

    private BasicAlgorithm getAlgorithmHandler(Class<? extends BasicAlgorithm> algorithm){
        return network
                .getNetwork()
                .getAlgorithms()
                .get(algorithm.getSimpleName());
    }

    private void createView(){
        Viewer viewer = new Viewer(network.getNetwork().getGraph(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        graphView.setContent((JComponent) view);
    }

    public void nextGeneration(){
        showProgressBar();
        // render graph in SwingNode view
        network.next();
        updateGraphValues();
        hideProgressBar();
    }

    private void updateGraphValues(){
        clusteringCoefficient.setText(network.getNetwork().clusteringCoefficient().toString());
        averagePathLength.setText(network.getNetwork().averagePathLength().toString());
    }

    private NetworkIterator createIterator() throws IllegalAccessException, InstantiationException, IOException {
        DataLoader loader = client.newInstance();
        return (NetworkIterator) NetworkBuilder.newInstance()
                .addUsers(loader.getAllUsers())
                .addRelationships(loader.getAllRelationships())
                .addGroups(loader.getAllGroups())
                .build()
                .iterator();
    }

    private void showProgressBar(){
        progress.setVisible(true);
    }

    private void hideProgressBar(){
        progress.setVisible(false);
    }

    // TODO: zooming. Tutorial http://graphstream-project.org/doc/Tutorials/Graph-Visualisation/
}
