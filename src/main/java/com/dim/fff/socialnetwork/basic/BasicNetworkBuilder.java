package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.NetworkBuilder;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.corenetwork.User;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

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
        Graph graph = new SingleGraph("Social network");
        graph.setStrict(false);
        graph.setAutoCreate( true );

        relationships.forEach(relationship -> graph.addEdge(
                relationship.toString(),
                relationship.getUser1().toString(),
                relationship.getUser2().toString())
        );

        return new BasicNetwork(graph);
    }
}
