package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.Network;
import org.graphstream.graph.Graph;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class BasicNetwork extends Network {

    public BasicNetwork(Graph network) {
        super(network);
    }

    @Override
    protected Object clone() {
        return new BasicNetwork(getNetwork());
    }

    // TODO: generowanie następnego grafu. Tutorial: http://graphstream-project.org/doc/Tutorials/Working-with-algorithms-and-generators/
    @Override
    public Network nextGeneration() {
        BasicNetwork nextNetwork = (BasicNetwork) this.clone(); // create copy of this network
        return nextNetwork
                .randomAction()
                .randomAction2();
    }

    private BasicNetwork randomAction(){
        // do sth with network
        return this;
    }

    private BasicNetwork randomAction2(){
        // do sth with network
        return this;
    }

    @Override
    public void init(Graph graph) {

    }

    @Override
    public void compute() {

    }
}
