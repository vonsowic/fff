package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.User;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 15.11.17
 */
public interface DataLoader {
    Collection<User> getAllUsers();
    Collection<Relationship> getAllRelationships();
    Collection<Group> getAllGroups();
}
