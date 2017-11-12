package com.dim.fff.socialnetwork;

import com.dim.fff.socialnetwork.dataprovider.facebook.FacebookClient;
import com.dim.fff.socialnetwork.dataprovider.facebook.FacebookUser;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class FacebookClientTest {

    private FacebookClient client = new FacebookClient();
    private Long id = Long.valueOf("100002246724283");

    @Test
    public void findUserById() throws Exception {
        FacebookUser user = client.findUserById(id);
        System.out.println(user.getId());
        Assert.assertNotNull(user);
    }

}