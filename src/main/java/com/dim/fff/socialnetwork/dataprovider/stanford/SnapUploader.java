package com.dim.fff.socialnetwork.dataprovider.stanford;

import com.dim.fff.socialnetwork.dataprovider.DataProvider;
import com.dim.fff.socialnetwork.dataprovider.UserInfo;

import java.util.Collection;

/**
 * Reads data from resources/stanford.
 * Dataset was manually downloaded from https://snap.stanford.edu/data/egonets-Facebook.html
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
public class SnapUploader implements DataProvider<Integer> {

    @Override
    public UserInfo<Integer> findUserById(Integer userId) {
        return null;
    }

    @Override
    public Collection<? extends UserInfo<Integer>> findUsersFriends(UserInfo<Integer> user) {
        return null;
    }
}
