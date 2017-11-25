package com.dim.fff.socialnetwork.basic;

import com.dim.fff.socialnetwork.corenetwork.NetworkBuilder;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class BasicNetworkBuilder extends NetworkBuilder {

    public BasicNetworkBuilder(DataLoader loader) {
        super(loader);
    }

    @Override
    public BasicNetwork build() {
        Graph graph = new SingleGraph("Social network");
        graph.setStrict(false);
        graph.setAutoCreate( true );

        getAllRelationships().forEach(relationship -> graph.addEdge(
                relationship.toString(),
                relationship.getUser1().toString(),
                relationship.getUser2().toString())
        );

        return new BasicNetwork(graph, this);
    }
}
