package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.corenetwork.algorithms.*;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import org.apache.commons.math3.util.Pair;
import org.graphstream.algorithm.Algorithm;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Element;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;
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
    private Integer generation = 0;

    protected Network(Graph network) {
        this.network = network;
        algorithms.put("SetZeroProbabilities", new SetZeroProbabilities(this));
        algorithms.put("GroupMembershipProbabilities", new GroupMembershipProbabilities(this));
        algorithms.put("FriendsOfFriendsAreMyFriends", new FriendsOfFriendsAreMyFriends(this));
//        algorithms.put("ColorEdges", new ColorEdges(this));
        algorithms.put("RemoveTooOldRelationships", new RemoveTooOldRelationships(this));
        algorithms.put("CheckIfRelationshipSurvives", new CheckIfRelationshipSurvives(this));
    }

    public Graph getGraph(){
        return this.network;
    }

    private Map<String, BasicAlgorithm> algorithms = new LinkedHashMap<>();

    public Map<String, BasicAlgorithm> getAlgorithms() {
        return algorithms;
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
        generation++;

        algorithms
                .values()
                .forEach(Algorithm::compute);

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
                            .filter(node -> !node.equals(sourceNode))
                            .map(dijkstra::getPathLength)
                            .collect(Collectors.toList()); })
                .map(lengthsOfPaths -> new Pair<>(
                        lengthsOfPaths.stream().mapToDouble(it -> it).sum(),
                        lengthsOfPaths.size()))
                .reduce(
                        new Pair<>(0.0, 0),
                        (prev, it) -> new Pair<>(prev.getFirst() + it.getFirst(), prev.getSecond() + it.getSecond())
                );

        return value.getFirst() / value.getSecond();
    }


    public Integer getRelationshipStrength(Edge relationship){
        return relationship.getAttribute(Attributes.RELATIONSHIP_STRENGTH, Integer.class);
    }

    public void addRelationshipStrengthTo(Edge relationship, Integer valueToBeAdded) {
        relationship.setAttribute(
                Attributes.RELATIONSHIP_STRENGTH,
                relationship.getAttribute(Attributes.RELATIONSHIP_STRENGTH, Integer.class) + valueToBeAdded
        );
    }

    public Integer getGeneration() {
        return generation;
    }

    public Integer getRelationshipAge(Edge relationship){
        return getGeneration() - relationship.getAttribute(Attributes.CREATED_AT, Integer.class);
    }

    public boolean exists(Edge relationship) {
        return relationship.getAttribute(Attributes.EXISTS, Boolean.class);
    }

    public void setExists(Edge relationship, boolean exists) {
        relationship.setAttribute(Attributes.EXISTS, exists);
    }

    public void setStrength(Edge relationship, int strength ) {
        relationship.addAttribute(Attributes.RELATIONSHIP_STRENGTH, strength);
    }

    public Edge addNonExistingRelationship(Node user1, Node user2) {
        Edge result = getGraph()
                .addEdge(Relationship.generateEdgeId(user1, user2), user1.getId(), user2.getId());

        setExists(result, false);
        setStrength(result, 0);
        setCreatedAtNow(result);

        return result;
    }

    private void setCreatedAtNow(Edge result) {
        result.addAttribute(Attributes.CREATED_AT, getGeneration());
    }

    public Edge getRelationshipBetweenCreateIfNecessary(Node user1, Node user2) {
        if(user1.hasEdgeBetween(user2)){
            return user1.getEdgeBetween(user2);
        } else {
            return addNonExistingRelationship(user1, user2);
        }
    }
}
