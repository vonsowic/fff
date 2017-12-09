package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.corenetwork.dataobjects.Group;
import com.dim.fff.socialnetwork.corenetwork.dataobjects.Relationship;
import com.dim.fff.socialnetwork.corenetwork.dataobjects.User;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 15.11.17
 */
public interface DataLoader {
    Collection<User> getAllUsers() throws IOException;
    Collection<Relationship> getAllRelationships() throws IOException;
    Collection<Group> getAllGroups() throws IOException;
}
