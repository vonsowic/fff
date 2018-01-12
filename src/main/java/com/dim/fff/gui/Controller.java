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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML public SwingNode graphView;
    @FXML public Button next;
    @FXML public TextField friendsAlgorithm;
    @FXML public TextField groupsAlgorithm;
    @FXML public TextField threshold;
    @FXML public LineChart chart;
    @FXML public Label generationCounter;

    private XYChart.Series clusteringCoefficientStack = new XYChart.Series();
    private XYChart.Series averagePathLengthStack  = new XYChart.Series();

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
        reset();
    }

    private NetworkIterator network = createIterator();

    public Controller() throws IOException, IllegalAccessException, InstantiationException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
    }

    private void reset(){
        createView();
        updateGraphValues();

        setOnNumberChangeListener(friendsAlgorithm, FriendsOfFriendsAreMyFriends.class);
        setOnNumberChangeListener(groupsAlgorithm, GroupMembershipProbabilities.class);
        setOnNumberChangeListener(threshold, CheckIfRelationshipSurvives.class);

        resetChart();
    }

    private void resetChart(){
        chart.getData().clear();
        generationCounter.setText("0");
        averagePathLengthStack = new XYChart.Series();
        averagePathLengthStack.setName("Average path lengths");
        clusteringCoefficientStack = new XYChart.Series();
        clusteringCoefficientStack.setName("Clustering coefficients");

        chart.getData().addAll(
                averagePathLengthStack,
                clusteringCoefficientStack
        );
    }

    private void setOnNumberChangeListener(TextField field, Class<? extends BasicAlgorithm> algorithm){
        try {
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    getAlgorithmHandler(algorithm)
                            .setValue(Integer.valueOf(newValue));
                } catch (NumberFormatException ignore) {}
            });


            field.textProperty().setValue(getAlgorithmHandler(algorithm).getValue().toString());
        } catch (NullPointerException ignoreAlgorithm){
            field.setDisable(true);
        }
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
        network.next();
        incrementGeneration();
        updateGraphValues();
    }

    private void incrementGeneration(){
        generationCounter.setText(String.valueOf(getGeneration()+1));
    }

    private Integer getGeneration(){
        return Integer.valueOf(getGenerationString());
    }

    private String getGenerationString(){
        return generationCounter.getText();
    }

    private void updateGraphValues(){
        Double clusteringCoefficientValue = network.getNetwork().clusteringCoefficient();
        Double averagePathLengthValue = network.getNetwork().averagePathLength();

        clusteringCoefficient.setText(clusteringCoefficientValue.toString());
        averagePathLength.setText(averagePathLengthValue.toString());

        clusteringCoefficientStack.getData().add(new XYChart.Data(String.valueOf(getGenerationString()), clusteringCoefficientValue));
        averagePathLengthStack.getData().add(new XYChart.Data(String.valueOf(getGenerationString()), averagePathLengthValue));
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
}
