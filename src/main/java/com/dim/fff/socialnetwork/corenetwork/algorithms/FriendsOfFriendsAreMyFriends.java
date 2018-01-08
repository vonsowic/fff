package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Calculate probability based on friends of node's friends.
 */
public class FriendsOfFriendsAreMyFriends extends BasicAlgorithm {
    public FriendsOfFriendsAreMyFriends(Network network) {
        super(network);
    }


    private Set<Node> findAllFriends(Node n){
        Set<Node> friends = new HashSet<>();

        for(Edge e:n.getEdgeSet()){
                if(e.getAttribute(Attributes.EXISTS))
                    if(n!=e.getNode0())
                        friends.add(e.getNode0());
                    else
                        friends.add(e.getNode1());
        }
        return friends;
    }

    @Override
    public void compute() {
        for(Node n:getNetwork().getGraph().getEachNode()){
            Set<Node> friends1 = findAllFriends(n);
//            System.out.println("friends1"+friends1);
            for(Node i: friends1){
                Set<Node> friends2 = findAllFriends(i);
//                System.out.println("friends2"+friends2);
                for(Node j: friends2){
                    if (getNetwork().getGraph().getNode(n.getId()).hasEdgeBetween(j)){
                        Edge edge = getNetwork().getGraph().getNode(n.getId()).getEdgeBetween(j);
                        Integer increasedProbability = Integer.parseInt(edge.getAttribute(Attributes.PROBABILITY).toString()) + 1;
                        edge.addAttribute(Attributes.PROBABILITY, increasedProbability.toString());
                        //System.out.println("zwiekszono prawdopodbieństwo dla "+n+" "+j);
                    }
                    else
                    {
                        String edgeId = Relationship.generateEdgeId(n, j);
                        getNetwork().getGraph().addEdge(edgeId, n, j).addAttribute(Attributes.EXISTS, false);
                        getNetwork().getGraph().getEdge(edgeId).addAttribute(Attributes.PROBABILITY, 1);

//                        System.out.println("zwiekszono prawdopodbieństwo i dodano znajomosc dla "+n+" "+j);
                    }
                }
            }
        }
    }
}




