package com.dim.fff.socialnetwork.dataprovider.facebook;

import com.dim.fff.socialnetwork.corenetwork.User;
import com.restfb.Facebook;
import lombok.Data;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
@Data
public class FacebookUser implements User<Long> {

    @Facebook("id")
    private Long id;

}
