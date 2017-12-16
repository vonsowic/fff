package com.dim.fff.socialnetwork.dataprovider.dataobjects;


/**
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class Relationship {

    private final User user1;
    private final User user2;


    public Relationship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    @Override
    public String toString(){
        return user1.toString() + '-' +  user2.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relationship)) return false;
        if (!super.equals(o)) return false;

        Relationship that = (Relationship) o;

        if (!user1.equals(that.user1)) return false;
        return user2.equals(that.user2);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
