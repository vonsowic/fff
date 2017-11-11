package com.dim.fff.socialnetwork;

import com.restfb.Facebook;
import lombok.Data;

import java.util.Date;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
@Data
public class BasicUser {

    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private Date birthday;
}
