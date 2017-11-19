package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.corenetwork.User;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class BasicNetwork extends Network {

    public BasicNetwork(com.google.common.graph.Network<User, Relationship> network) {
        super(network);
    }


    @Override
    protected Object clone() {
        return new BasicNetwork(getNetwork());
    }

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
}
