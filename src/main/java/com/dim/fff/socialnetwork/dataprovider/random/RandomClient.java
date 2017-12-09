package com.dim.fff.socialnetwork.dataprovider.random;

import com.dim.fff.socialnetwork.dataprovider.Client;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 20.11.17
 */
@Client
public class RandomClient implements DataLoader {

    private final Long numberOfNodes;
    private HashSet<User> users = new HashSet<>();
    private HashSet<Relationship> relationships = new HashSet<>();


    public RandomClient(Long numberOfNodes){
        this.numberOfNodes = numberOfNodes;
        generate();
    }

    public RandomClient(){
        this(100L);
    }


    private void generate(){
        Random randomGenerator = new Random();
        for(Long i = 1L; i <= numberOfNodes; i++){
            users.add(new User(i));
            for(Long j = 0L; j<4; j++){
                relationships.add(new Relationship(
                        new User(randomGenerator.nextLong()+1),
                        new User(randomGenerator.nextLong()+1)
                ));
            }
        }
    }


    @Override
    public Collection<User> getAllUsers() {
        return users;
    }

    @Override
    public Collection<Relationship> getAllRelationships() {
        return relationships;
    }

    @Override
    public Collection<Group> getAllGroups() {
        return new HashSet<>();
    }
}
