package com.dim.fff.gui;

import com.dim.fff.socialnetwork.basic.BasicNetworkBuilder;
import com.dim.fff.socialnetwork.corenetwork.NetworkIterator;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.random.RandomClient;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import lombok.Getter;
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


    @FXML private ProgressIndicator progress;
    @FXML private SwingNode graphView;
    @FXML private Button next;

    @Getter
    private Class<? extends DataLoader> client = RandomClient.class;

    public void setClient(Class<? extends DataLoader> client) {
        this.client = client;
        try {
            network = createIterator();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        nextGeneration();
    }

    private NetworkIterator network = createIterator();

    public Controller() throws IOException, IllegalAccessException, InstantiationException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nextGeneration();
        progress.setProgress(-1);
    }

    public void nextGeneration(){
        showProgressBar();
        // render graph in SwingNode view
        Viewer viewer = new Viewer(network.next().getGraph(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        graphView.setContent((JComponent) view);

        hideProgressBar();
    }

    private NetworkIterator createIterator() throws IllegalAccessException, InstantiationException {
        return (NetworkIterator) new BasicNetworkBuilder(client.newInstance())
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
