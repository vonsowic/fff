package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.stanford.SnapReaderClient;
import com.google.common.primitives.Longs;
import org.graphstream.graph.Edge;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

    Collection<Group> groups = new HashSet<>();
    SnapReaderClient snapReaderClient = new SnapReaderClient();

    private String firstNodeId = new String();
    private String secondNodeId = new String();


    private Map<String, Object> getMap(String probability, Boolean exist) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Attributes.PROBABILITY, probability);
        map.put(Attributes.EXISTS, exist);
        return map;
    }


    @Override
    public void compute() {

        groups = snapReaderClient.getAllGroups();

        groups.forEach((Group singleGroup) -> {
            long[] group = Longs.toArray(singleGroup.getMembers());

            for (Integer k = 0; k < group.length - 1; k++) {
                firstNodeId = String.valueOf(group[k]);

                for (Integer j = k + 1; j < group.length; j++) {
                    secondNodeId = String.valueOf(group[j]);

                    if (getNetwork().getGraph().getNode(firstNodeId).hasEdgeBetween(secondNodeId)) {
                        Edge edge = getNetwork().getGraph().getNode(firstNodeId).getEdgeBetween(secondNodeId);

                        Boolean existValue = edge.getAttribute(Attributes.EXISTS);
                        System.out.println(existValue);

                        String  probability =  edge.getAttribute(Attributes.PROBABILITY).toString();
                        Integer increasedProbability = Integer.parseInt(probability) + 1;
                        edge.addAttribute(Attributes.PROBABILITY, increasedProbability.toString());

                    } else {

                        String edgeId = firstNodeId + secondNodeId;
                        getNetwork().getGraph()
                                .addEdge(edgeId, firstNodeId, secondNodeId)
                                .addAttributes(getMap("1", false));
                    }
                }
            }
        });
    }
}
