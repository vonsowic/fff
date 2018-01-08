package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.corenetwork.algorithms.*;
import org.apache.commons.math3.util.Pair;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Element;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        new SetZeroProbabilities(this).compute();

        new GroupMembershipProbabilities(this).compute();
        new FriendsOfFriendsAreMyFriends(this).compute();
        new ColorEdges(this).compute();

        new CheckIfRelationshipSurvives(this).compute();
        return this;
    }

    public Set<String> findAllGroups(){
        HashSet<String> groups = new HashSet<>();

        getNodeStream()
                .forEach(user -> groups.addAll(user.getAttribute(Attributes.GROUPS, HashSet.class)));

        return groups;
    }

    public Map<String, Set<String>> findAllGroupsWithUsers(){
        return findAllGroups()
                .stream()
                .collect(
                        Collectors.toMap(
                                group -> group,
                                group -> getNodeStream()
                                        .filter(user -> user.getAttribute(Attributes.GROUPS, HashSet.class).contains(group))
                                        .map(Element::getId)
                                        .collect(Collectors.toSet()
                        )
                )
        );
    }

    public Stream<Node> getNodeStream(){
        return StreamSupport
                .stream(getGraph().spliterator(), false);
    }


    public Double clusteringCoefficient(){
        int numberOfNodes = getGraph().getNodeCount();
        if(numberOfNodes == 0 || numberOfNodes == 1) return 1.0;
        return (double) getGraph().getEdgeCount() / (numberOfNodes*(numberOfNodes-1)/2);
    }

    public Double averagePathLength(){
        final Dijkstra dijkstra = new Dijkstra();
        dijkstra.init(getGraph());

        Pair<Double, Integer> value = getNodeStream()
                .map(sourceNode -> {
                    dijkstra.setSource(sourceNode);
                    dijkstra.compute();

                    return getNodeStream()
                            .filter(node -> node != sourceNode)
                            .map(dijkstra::getPathLength)
                            .collect(Collectors.toList()); })
                .map(lengthsOfPaths -> new Pair<>(
                        lengthsOfPaths.stream().mapToDouble(it -> it).sum(),
                        lengthsOfPaths.size()))
                .reduce(
                        new Pair<>(0.0, 0),
                        (prev, it) -> new Pair<>(prev.getFirst() + it.getFirst() * it.getSecond(), prev.getSecond() + it.getSecond())
                );

        return value.getFirst() / value.getSecond();
    }
}
