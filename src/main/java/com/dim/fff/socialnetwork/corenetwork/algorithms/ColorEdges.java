package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;


/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public class ColorEdges extends BasicAlgorithm {

    public ColorEdges(Network network) {
        super(network);
    }

    @Override
    public void compute() {
        getNetwork()
                .getGraph()
                .getNodeIterator()
                .forEachRemaining(edge -> {
//                    if( edge.getAttribute(Attributes.EXISTS)){
                        edge.setAttribute("ui.stylesheet", "graph { fill-color: blue; canvas-color: blue; }");
//                    }
                });
    }
}
