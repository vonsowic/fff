package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.NetworkFactory;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.corenetwork.User;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class BasicNetworkFactory implements NetworkFactory {

    private Collection<User> users;
    private Collection<Relationship> relationships;

    public BasicNetworkFactory(Collection users, Collection relationships) {
        this.users = users;
        this.relationships = relationships;
    }

    public BasicNetworkFactory(DataLoader loader){
        this(loader.getAllUsers(), loader.getAllRelationships());
    }

    @Override
    public Network<User, Relationship> buildNetwork() {
        MutableNetwork<User, Relationship> network = NetworkBuilder.undirected()
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

        return network;
    }
}
