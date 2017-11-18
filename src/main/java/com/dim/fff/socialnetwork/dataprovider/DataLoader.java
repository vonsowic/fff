package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.corenetwork.User;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 15.11.17
 */
public interface DataLoader<Id> {
    Collection<? extends User<Id>> getAllUsers();
    Collection<Relationship> getAllRelationships();
}
