package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 15.11.17
 */
public interface DataLoader {
    Collection<String> getAllUsers();
    Collection<String> getAllRelationships();
    Collection<Group> getAllGroups();
}
