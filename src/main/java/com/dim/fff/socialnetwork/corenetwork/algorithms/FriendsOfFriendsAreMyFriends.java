package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Calculate probability based on friends of node's friends.
 */
public class FriendsOfFriendsAreMyFriends extends BasicAlgorithm {
    public FriendsOfFriendsAreMyFriends(Network network) {
        super(network);
    }

    private Map<Edge, Integer> findRelationsToFriendsOfFriendsOfUserWithNumberOfCommonFriends(Node user){
        Map<Edge, Integer> friends = new HashMap<>();

        getNetwork()
                .getNodeStream()
                .filter(friend -> user.hasEdgeBetween(friend) && getNetwork().exists(user.getEdgeBetween(friend)))
                .forEach(friend -> friend.getNeighborNodeIterator()
                        .forEachRemaining(friendOfFriend -> {
                            if(!Objects.equals(user, friendOfFriend)){
                                Edge relationship = getNetwork().getRelationshipBetweenCreateIfNecessary(user, friendOfFriend);

                                if( !getNetwork().exists(relationship)){
                                    friends.put(relationship, 1+friends.getOrDefault(relationship, 0));
                                }
                            }
                        })
                );

        return friends;
    }

    @Override
    public void compute() {
        getGraph()
                .getNodeIterator()
                .forEachRemaining(user -> findRelationsToFriendsOfFriendsOfUserWithNumberOfCommonFriends(user)
                        .forEach((relationship, numberOfCommonFriends) -> getNetwork().addRelationshipStrengthTo(relationship, numberOfCommonFriends*getValue())));
    }
}




