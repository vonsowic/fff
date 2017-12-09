package com.dim.fff.socialnetwork.corenetwork.dataobjects;

import lombok.Data;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
@Data
public class Group {

    private String name;

    private Collection<User> members;
}
