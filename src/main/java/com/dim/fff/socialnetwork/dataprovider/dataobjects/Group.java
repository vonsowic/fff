package com.dim.fff.socialnetwork.dataprovider.dataobjects;

import java.util.Collection;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public class Group {

    private String name;

    private Collection<String> members;

    public Group(String name, Collection<String> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public Collection<String> getMembers() {
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
