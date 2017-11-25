package com.dim.fff.socialnetwork.corenetwork;


import com.dim.fff.socialnetwork.dataprovider.DataLoader;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public abstract class NetworkBuilder implements DataLoader{

    private Collection<User> users;
    private Collection<Relationship> relationships;

    @Override
    public Collection<? extends User> getAllUsers() {
        return users;
    }

    @Override
    public Collection<Relationship> getAllRelationships() {
        return relationships;
    }

    private NetworkBuilder(Collection<User> users, Collection<Relationship> relationships) {
        this.users = users;
        this.relationships = relationships;
    }

    public NetworkBuilder(DataLoader loader){
        this(loader.getAllUsers(), loader.getAllRelationships());
    }

    public abstract Network build();
}
