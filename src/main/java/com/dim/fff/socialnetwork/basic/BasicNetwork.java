package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import org.graphstream.graph.Graph;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class BasicNetwork extends Network {


    protected BasicNetwork(Graph network, DataLoader loader) {
        super(network, loader);
    }

    @Override
    protected Object clone() {
        return new BasicNetwork(getGraph(), this);
    }

    @Override
    public Network nextGeneration() {
        BasicNetwork nextNetwork = (BasicNetwork) this.clone(); // create copy of this network
        //Generator algorithm = new NetworkAlgorithm(nextNetwork);
        return nextNetwork;
    }
}
