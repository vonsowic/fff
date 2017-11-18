package com.dim.fff.socialnetwork.corenetwork;

import com.google.common.graph.Network;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public interface NetworkFactory {
    Network<User, Relationship> buildNetwork();
}
