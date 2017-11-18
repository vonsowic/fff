package com.dim.fff.socialnetwork.dataprovider.stanford;

import com.dim.fff.socialnetwork.corenetwork.User;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.DataProvider;

import java.util.Collection;

/**
 * Reads data from resources/stanford.
 * Dataset was manually downloaded from https://snap.stanford.edu/data/egonets-Facebook.html
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
public class SnapUploader implements DataProvider<Integer>, DataLoader {

    @Override
    public User<Integer> findUserById(Integer userId) {
        return null;
    }

    @Override
    public Collection<? extends User<Integer>> findUsersFriends(User<Integer> user) {
        return null;
    }

    @Override
    public Collection getAllUsers() {
        return null;
    }

    @Override
    public Collection getAllRelationships() {
        return null;
    }
}
