package com.dim.fff.socialnetwork.dataprovider;


import com.dim.fff.socialnetwork.BasicUser;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;

import java.util.Collection;

/**
 *
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class FacebookClient implements DataProvider {

    private DefaultFacebookClient client = new DefaultFacebookClient(
            ACCESS_TOKEN,
            APP_SECRET,
            Version.VERSION_2_10);

    @Override
    public BasicUser findUserById(Long userId){
        return client.fetchObject(
                userId.toString(),
                BasicUser.class,
                Parameter.with("fields","id, birthday, first_name, last_name, gender")
        );
    }

    @Override
    public Collection<BasicUser> findUsersFriends(Long userId) {
        return client.fetchConnection(
                userId + "/friends",
                BasicUser.class
        ).getData();
    }


    private static final String APP_ID =        "1132643793537785";
    private static final String APP_SECRET =    "c24119f6f016e515b7443ca633290052";
    private static final String ACCESS_TOKEN =  "1132643793537785|3cHJzBYizg26Fs4RHJU14Ak6vKM";

}
