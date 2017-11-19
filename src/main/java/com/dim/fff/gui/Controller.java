package com.dim.fff.gui;

import com.dim.fff.socialnetwork.basic.BasicNetworkBuilder;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.networkrepo.SocFBNips;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML SwingNode graphView;

    @FXML Button next;

    private Network network = new BasicNetworkBuilder(new SocFBNips()).build();

    public Controller() throws IOException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // render graph in SwingNode view
        Viewer viewer = new Viewer(network.getNetwork(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        graphView.setContent((JComponent) view);
    }
}
