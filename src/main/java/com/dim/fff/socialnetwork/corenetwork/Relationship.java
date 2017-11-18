package com.dim.fff.socialnetwork.corenetwork;

import lombok.Data;

/**
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
@Data
public class Relationship {

    private final User user1;
    private final User user2;

    public Relationship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
