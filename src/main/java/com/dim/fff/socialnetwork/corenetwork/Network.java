package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.corenetwork.algorithms.ColorEdges;
import com.dim.fff.socialnetwork.corenetwork.algorithms.FriendsOfFriendsAreMyFriends;
import com.dim.fff.socialnetwork.corenetwork.algorithms.GroupMembershipProbabilities;
import com.dim.fff.socialnetwork.corenetwork.algorithms.SetZeroProbabilities;
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

    private Stream<Node> getNodeStream(){
        return StreamSupport
                .stream(getGraph().spliterator(), false);
    }
}
