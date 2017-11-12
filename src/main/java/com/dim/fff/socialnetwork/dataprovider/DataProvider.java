package com.dim.fff.socialnetwork.dataprovider;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public interface DataProvider<Id> {
    UserInfo<Id> findUserById(Id userId);
    Collection<? extends UserInfo<Id>> findUsersFriends(UserInfo<Id> user);
}
