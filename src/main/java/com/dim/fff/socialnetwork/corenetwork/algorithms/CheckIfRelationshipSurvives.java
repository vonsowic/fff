package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;
import org.graphstream.graph.Graph;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 05.01.18
 */
public class CheckIfRelationshipSurvives extends BasicAlgorithm{

    public CheckIfRelationshipSurvives(Network network) {
        super(network);
    }

    @Override
    public void compute() {
        Graph graph = getNetwork().getGraph();

        graph
                .getEdgeIterator()
                .forEachRemaining(edge -> {
                    // FIXME: change random value
                    Integer probability = edge.getAttribute(Attributes.PROBABILITY, Integer.class);
                    if(probability != null) {
                        edge.setAttribute(Attributes.PROBABILITY, 100000000);

                        if (probability < Math.random() * 100) {
                            graph.removeEdge(edge);
                        } else {
                            edge.setAttribute(Attributes.EXISTS, true);
                        }
                    }
                });
    }
}
