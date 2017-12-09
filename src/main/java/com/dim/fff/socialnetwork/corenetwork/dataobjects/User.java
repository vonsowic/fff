package com.dim.fff.socialnetwork.corenetwork.dataobjects;

import lombok.Getter;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
public class User{

    @Getter
    private final Long id;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
