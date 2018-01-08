package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;

/**
 * Calculate probability based on friends of node's friends.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public class FriendsOfFriendsAreMyFriends extends BasicAlgorithm {
    public FriendsOfFriendsAreMyFriends(Network network) {
        super(network);
    }

    private Set<Node> friends1= new HashSet<>();
    private Set<Node> friends2= new HashSet<>();


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
            friends1=findAllFriends(n);
//            System.out.println("friends1"+friends1);
            for(Node i:friends1){
                friends2=findAllFriends(i);
//                System.out.println("friends2"+friends2);
                for(Node j:friends2){
                    if (getNetwork().getGraph().getNode(n.getId()).hasEdgeBetween(j)){
                        Edge edge = getNetwork().getGraph().getNode(n.getId()).getEdgeBetween(j);
                        Integer increasedProbability = Integer.parseInt(edge.getAttribute(Attributes.PROBABILITY).toString()) + 1;
                        edge.addAttribute(Attributes.PROBABILITY, increasedProbability.toString());
                        //System.out.println("zwiekszono prawdopodbieństwo dla "+n+" "+j);
                    }
                    else
                    {
                        String edgeId = n.getId() + "---" + j.getId();
                        getNetwork().getGraph().addEdge(edgeId, n, j).addAttribute(Attributes.EXISTS, false);
                        getNetwork().getGraph().getEdge(edgeId).addAttribute(Attributes.PROBABILITY, 1);

//                        System.out.println("zwiekszono prawdopodbieństwo i dodano znajomosc dla "+n+" "+j);
                    }
                }
            }
        }
    }
}




