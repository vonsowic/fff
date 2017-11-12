package com.dim.fff.socialnetwork.dataprovider;

import com.dim.fff.socialnetwork.dataprovider.Gender;

import java.util.Date;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
public interface UserInfo<Id> {
    Id getId();
    Date getBirthday();
    Gender getGender();
}
