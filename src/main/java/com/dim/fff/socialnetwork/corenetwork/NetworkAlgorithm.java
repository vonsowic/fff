package com.dim.fff.socialnetwork.corenetwork;

import java.util.HashSet;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 25.11.17
 */
public class NetworkAlgorithm  /*implements Generator */{

    private Network network;

    public NetworkAlgorithm(Network network) {
        this.network = network;
    }

    private HashSet<Relationship> getNotRelatedUsers(){
        HashSet<Relationship> result = new HashSet<>();
        network.getAllUsers().forEach(user1 ->
                network.getAllUsers().forEach(user2 -> {
                    Relationship relationship = new Relationship(user1, user2, false);
                    if(!network.getAllRelationships().contains(relationship))
                        result.add(relationship);
                })
        );

        return result;
    }
}
