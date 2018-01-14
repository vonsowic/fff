package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;
import org.graphstream.graph.Element;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.01.18
 */
public class RemoveTooOldRelationships extends BasicAlgorithm {
    public RemoveTooOldRelationships(Network network) {
        super(network);
        setValue(10);
    }

    @Override
    public void compute() {
        Set<String> ids = getGraph()
                .getEdgeSet()
                .stream()
                .filter(relationship -> getNetwork().exists(relationship))
                .filter(relationship -> getNetwork().getRelationshipStrength(relationship) - getNetwork().getRelationshipAge(relationship) < getValue())
                .map(Element::getId)
                .collect(Collectors.toSet());

        ids.forEach(id -> getGraph().removeEdge(id));
    }
}
