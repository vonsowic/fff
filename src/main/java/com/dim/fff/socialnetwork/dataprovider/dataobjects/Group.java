package com.dim.fff.socialnetwork.dataprovider.dataobjects;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public class Group {

    private String name;

    private Collection<Long> members;

    public Group(String name, Collection<Long> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public Collection<Long> getMembers() {
        return members;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }
}
