package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.NetworkBuilder;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.corenetwork.User;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.google.common.graph.MutableNetwork;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class BasicNetworkBuilder implements NetworkBuilder {

    private Collection<User> users;
    private Collection<Relationship> relationships;

    public BasicNetworkBuilder(Collection<User> users, Collection<Relationship> relationships) {
        this.users = users;
        this.relationships = relationships;
    }

    public BasicNetworkBuilder(DataLoader loader){
        this(loader.getAllUsers(), loader.getAllRelationships());
    }

    @Override
    public BasicNetwork build() {
        MutableNetwork<User, Relationship> network = com.google.common.graph.NetworkBuilder.undirected()
                .allowsParallelEdges(true)
                //.nodeOrder(ElementOrder.natural())
                .expectedNodeCount(users.size())
                .expectedEdgeCount(relationships.size())
                .build();

        users.forEach(network::addNode);
        relationships.forEach(relationship -> network.addEdge(
                relationship.getUser1(),
                relationship.getUser2(),
                relationship));

        return new BasicNetwork(network);
    }
}
