package com.dim.fff.socialnetwork.dataprovider.facebook;


import com.dim.fff.socialnetwork.dataprovider.UserInfo;
import com.dim.fff.socialnetwork.dataprovider.DataProvider;
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
public class FacebookClient implements DataProvider<Long> {

    private DefaultFacebookClient client = new DefaultFacebookClient(
            ACCESS_TOKEN,
            APP_SECRET,
            Version.VERSION_2_10);

    @Override
    public FacebookUser findUserById(Long userId){
        return client.fetchObject(
                userId.toString(),
                FacebookUser.class,
                Parameter.with("fields","id, birthday, first_name, last_name, gender")
        );
    }

    @Override
    public Collection<FacebookUser> findUsersFriends(UserInfo user) {
        return client.fetchConnection(
                user.getId().toString() + "/friends",
                FacebookUser.class
        ).getData();
    }


    private static final String APP_ID =        "1132643793537785";
    private static final String APP_SECRET =    "c24119f6f016e515b7443ca633290052";
    private static final String ACCESS_TOKEN =  "1132643793537785|3cHJzBYizg26Fs4RHJU14Ak6vKM";

}
