package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.corenetwork.User;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public interface DataProvider<Id> {
    User<Id> findUserById(Id userId);
    Collection<? extends User<Id>> findUsersFriends(User<Id> user);
}
