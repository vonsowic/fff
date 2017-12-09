package com.dim.fff.socialnetwork.corenetwork.dataobjects;

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

    private boolean relationshipExists = true;

    /**
     * If relationshipExists is true, then this is probability of loosing relationship
     * If relationshipExists is false, then this is probability of creating new relationship
     */
    private int probability = 0;

    public void addProbability(int probability){
        this.probability += probability;
    }

    public Relationship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Relationship(User user1, User user2, boolean relationshipExists) {
        this(user1, user2);
        this.relationshipExists = relationshipExists;
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
        return 31 * super.hashCode() + user1.hashCode() + user2.hashCode();
    }
}
