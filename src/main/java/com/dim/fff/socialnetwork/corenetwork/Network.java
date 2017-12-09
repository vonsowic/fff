package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.corenetwork.algorithms.ColorEdges;
import com.dim.fff.socialnetwork.corenetwork.algorithms.FriendsOfFriendsAreMyFriends;
import com.dim.fff.socialnetwork.corenetwork.algorithms.GroupMembershipProbabilities;
import com.dim.fff.socialnetwork.corenetwork.algorithms.SetZeroProbabilities;
import org.graphstream.graph.Graph;

import java.util.Iterator;

/**
 * Social network graph.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class Network implements Iterable<Network>, Cloneable{

    private final Graph network;

    protected Network(Graph network) {
        this.network = network;
        new SetZeroProbabilities(this).compute();
    }

    public Graph getGraph(){
        return this.network;
    }

    @Override
    protected Object clone(){
        return new Network(getGraph());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<Network> iterator() {
        return new NetworkIterator(this);
    }

    public Network nextGeneration(){
        new GroupMembershipProbabilities(this).compute();
        new FriendsOfFriendsAreMyFriends(this).compute();
        new ColorEdges(this).compute();
        return this;
    }
}
