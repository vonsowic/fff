package com.dim.fff.socialnetwork.corenetwork;


import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class NetworkBuilder implements DataLoader{

    Collection<String> users = new HashSet<>();
    Collection<String> relationships = new HashSet<>();
    Collection<Group> groups = new HashSet<>();

    protected NetworkBuilder(){}

    public static NetworkBuilder newInstance(){
        return new NetworkBuilder();
    }

    public NetworkBuilder addUsers(Collection<String> users){
        this.users = users;
        return this;
    }


    public NetworkBuilder addRelationships(Collection<String> relationships){
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
        graph.setStrict( false );
        getAllUsers().forEach(graph::addNode);

        getAllRelationships().forEach(relationship -> {
            String[] users = Relationship.usersOf(relationship);
            graph.addEdge(
                relationship,
                users[0],
                users[1]);
            });

        graph
                .getEdgeIterator()
                .forEachRemaining(edge -> edge.setAttribute(Attributes.EXISTS, true));

        // add groups
        graph
                .getNodeIterator()
                .forEachRemaining(user -> user.addAttribute(
                        Attributes.GROUPS,
                        getAllGroups()
                            .stream()
                            .filter(group -> group.getMembers().contains(Long.valueOf(user.getId())))
                            .map(Group::getName)
                            .collect(Collectors.toSet())
                ));



//        Wyswietla przy kazdym node grupy do których ten node nalezy
//        /*
        graph
                .getNodeIterator()
                .forEachRemaining(user -> user.setAttribute(
                        Attributes.PROBABILITY,
                        user.getId()
//                        user.getAttribute(Attributes.GROUPS, HashSet.class)
                ));
//*/
        return new Network(graph);
    }

    @Override
    public Collection<String> getAllUsers() {
        return this.users;
    }

    @Override
    public Collection<String> getAllRelationships() {
        return this.relationships;
    }

    @Override
    public Collection<Group> getAllGroups() {
        return this.groups;
    }
}
