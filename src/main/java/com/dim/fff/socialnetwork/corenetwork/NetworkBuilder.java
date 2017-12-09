package com.dim.fff.socialnetwork.corenetwork;


import com.dim.fff.socialnetwork.corenetwork.dataobjects.Group;
import com.dim.fff.socialnetwork.corenetwork.dataobjects.Relationship;
import com.dim.fff.socialnetwork.corenetwork.dataobjects.User;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class NetworkBuilder implements DataLoader{

    Collection<User> users = new HashSet<>();
    Collection<Relationship> relationships = new HashSet<>();
    Collection<Group> groups = new HashSet<>();

    protected NetworkBuilder(){}

    public static NetworkBuilder newInstance(){
        return new NetworkBuilder();
    }

    public NetworkBuilder addUsers(Collection<User> users){
        this.users = users;
        return this;
    }


    public NetworkBuilder addRelationships(Collection<Relationship> relationships){
        this.relationships = relationships;
        return this;
    }


    public NetworkBuilder addGroups(Collection<Group> groups){
        this.groups = groups;
        return this;
    }


    public Network build(){
        SingleGraph graph = new SingleGraph("");
        graph.setAutoCreate( true );
        graph.setStrict(false);
        //getAllUsers().forEach(user -> graph.addNode(user.toString()));

        getAllRelationships().forEach(relationship -> graph.addEdge(
                relationship.toString(),
                relationship.getUser1().toString(),
                relationship.getUser2().toString())
        );

        return new Network(graph, this);
    }

    @Override
    public Collection<User> getAllUsers() {
        return this.users;
    }

    @Override
    public Collection<Relationship> getAllRelationships() {
        return this.relationships;
    }

    @Override
    public Collection<Group> getAllGroups() {
        return this.groups;
    }
}
