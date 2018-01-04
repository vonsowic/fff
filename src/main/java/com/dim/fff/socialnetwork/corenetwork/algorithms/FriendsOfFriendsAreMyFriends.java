package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.User;
import com.dim.fff.socialnetwork.dataprovider.stanford.SnapReaderClient;
import com.google.common.primitives.Longs;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    @Override
    public void compute() {

    }
}
