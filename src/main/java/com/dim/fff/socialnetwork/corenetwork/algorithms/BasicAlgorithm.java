package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public abstract class BasicAlgorithm implements Algorithm {

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private final Network network;

    public Network getNetwork() {
        return network;
    }

    protected Graph getGraph(){
        return getNetwork()
                .getGraph();
    }

    public BasicAlgorithm(Network network) {
        this.value = 1;
        this.network = network;
    }

    @Override
    public void init(Graph graph) {
        // do nothing
    }

    protected Edge addNonExistingRelationship(Node user1, Node user2) {
        Edge result = getGraph()
                .addEdge(Relationship.generateEdgeId(user1, user2), user1.getId(), user2.getId());

        result.setAttribute(Attributes.EXISTS, false);
        result.addAttribute(Attributes.RELATIONSHIP_STRENGTH, 0);
        result.addAttribute(Attributes.CREATED_AT, getNetwork().getGeneration());

        return result;
    }
}
