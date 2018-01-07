package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import org.graphstream.graph.Edge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Calculates probability for each edge based on group membership
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public class GroupMembershipProbabilities extends BasicAlgorithm {

    public GroupMembershipProbabilities(Network network) {
        super(network);
    }


    private String firstNodeId = new String();
    private String secondNodeId = new String();

    private Map<String, Set<String>> groupsWithUsers = new HashMap<String, Set<String>>();

    private Map<String, Object> getMap(String probability, Boolean exist) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Attributes.PROBABILITY, probability);
        map.put(Attributes.EXISTS, exist);
        return map;
    }


    @Override
    public void compute() {
        groupsWithUsers= getNetwork().findAllGroupsWithUsers();

        for (Set<String> key : groupsWithUsers.values()) {
//            System.out.println(key);
            String[] group = key.stream().toArray(String[]::new);

            for (Integer k = 0; k < group.length - 1; k++) {
                firstNodeId = group[k];
                System.out.println(firstNodeId);

                for (Integer j = k + 1; j < group.length; j++) {
                    secondNodeId = group[j];
//                    System.out.println(secondNodeId);

                    if (getNetwork().getGraph().getNode(firstNodeId).hasEdgeBetween(secondNodeId)) {
                        Edge edge = getNetwork().getGraph().getNode(firstNodeId).getEdgeBetween(secondNodeId);

                        //tutaj pewnie jakies inne liczenie prawdododobienstwa dla tych
                        //ktorzy sa znajomymi, i dla tych którzy nie są
                        //Boolean existValue = edge.getAttribute(Attributes.EXISTS);

                        //przykładowe zwiększanie probability, zeby sprawdzic czy dziala
                        String  probability =  edge.getAttribute(Attributes.PROBABILITY).toString();
                        Integer increasedProbability = Integer.parseInt(probability) + 1;
                        edge.addAttribute(Attributes.PROBABILITY, increasedProbability.toString());

                    } else {

                        String edgeId = Relationship.generateEdgeId(firstNodeId, secondNodeId);
                        getNetwork().getGraph()
                                .addEdge(edgeId, firstNodeId, secondNodeId)
                                .addAttributes(getMap("1", false));
                    }
                }
            }
        }

    }
}
