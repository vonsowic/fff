package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
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
        HashMap<Edge, Integer> nonExistingRelationshipsWithNumber = new HashMap<>();

        getNetwork()
                .findAllGroupsWithUsers()
                .forEach((groupId, usersInGroup) -> usersInGroup
                        .forEach(user1 -> usersInGroup
                                .stream()
                                .filter(user2 -> (!getNode(user1).hasEdgeBetween(user2) || !getNode(user1).getEdgeBetween(user2).getAttribute(Attributes.EXISTS, Boolean.class)) && !Objects.equals(user1, user2))
                                .forEach(user2 -> {
                                    Edge edge = addNonExistingRelationship(getNode(user1), getNode(user2));
                                    nonExistingRelationshipsWithNumber.put(edge, 1+nonExistingRelationshipsWithNumber.getOrDefault(edge, 0));
                                })));

        nonExistingRelationshipsWithNumber
                .forEach((edge, numberOfRelatedGroups) -> edge
                            .setAttribute(Attributes.RELATIONSHIP_STRENGTH, numberOfRelatedGroups*getValue()));
    }

    private Node getNode(String userId) {
        return getGraph()
                .getNode(userId);
    }
}
