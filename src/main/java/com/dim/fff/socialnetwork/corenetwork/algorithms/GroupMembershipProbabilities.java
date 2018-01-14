package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.HashMap;
import java.util.Objects;

/**
 * Calculates probability for each edge based on group membership
 */
public class GroupMembershipProbabilities extends BasicAlgorithm {

    public GroupMembershipProbabilities(Network network) {
        super(network);
    }

    @Override
    public void compute() {
        HashMap<Edge, Integer> relationshipsWithNumber = new HashMap<>();

        getNetwork()
                .findAllGroupsWithUsers()
                .forEach((groupId, usersInGroup) -> usersInGroup
                        .forEach(user1 -> usersInGroup
                                .stream()
                                .filter(user2 -> ( !Objects.equals(user1, user2)))
                                .forEach(user2 -> {
                                    Edge edge = getNetwork().getRelationshipBetweenCreateIfNecessary(getNode(user1), getNode(user2));
                                    relationshipsWithNumber.put(edge, 1+relationshipsWithNumber.getOrDefault(edge, 0));
                                })));

        relationshipsWithNumber
                .forEach((relationship, numberOfRelatedGroups) -> getNetwork()
                        .addRelationshipStrengthTo(relationship, numberOfRelatedGroups*getValue()));
    }

    private Node getNode(String userId) {
        return getGraph()
                .getNode(userId);
    }
}
