package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.BasicUser;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public interface DataProvider {
    BasicUser findUserById(Long userId);
    Collection<BasicUser> findUsersFriends(Long userId);
}
