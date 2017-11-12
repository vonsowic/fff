package com.dim.fff.socialnetwork.dataprovider;

import lombok.Data;

import java.util.Date;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
@Data
public class BasicUser implements UserInfo<Long> {

    private Long id;

    private Gender gender;

    private Date birthday;
}
