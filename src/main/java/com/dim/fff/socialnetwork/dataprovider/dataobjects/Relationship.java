package com.dim.fff.socialnetwork.dataprovider.dataobjects;


import org.graphstream.graph.Node;

/**
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class Relationship {

    public static String generateEdgeId(Node node1, Node node2){
        return generateEdgeId(node1.getId(), node2.getId());
    }

    public static String generateEdgeId(String id1, String id2) {
        if(id1.compareTo(id2) < 0){
            return id1 + getSplittingSing() + id2;
        } else {
            return id2 + getSplittingSing() + id1;
        }
    }

    public static String getSplittingSing(){
        return "-";
    }

    public static String[] usersOf(String relationship){
        return relationship.split(getSplittingSing());
    }
}
