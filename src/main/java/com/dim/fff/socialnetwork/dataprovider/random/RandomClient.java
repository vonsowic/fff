package com.dim.fff.socialnetwork.dataprovider.random;

import com.dim.fff.socialnetwork.basic.BasicUser;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.corenetwork.User;
import com.dim.fff.socialnetwork.dataprovider.Client;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 20.11.17
 */
@Client
public class RandomClient implements DataLoader<Integer>{

    private final Integer numberOfNodes;
    private HashSet<BasicUser> users = new HashSet<>();
    private HashSet<Relationship> relationships = new HashSet<>();


    public RandomClient(Integer numberOfNodes){
        this.numberOfNodes = numberOfNodes;
        generate();
    }

    public RandomClient(){
        this(100);
    }


    private void generate(){
        Random randomGenerator = new Random();
        for(Integer i = 1; i <= numberOfNodes; i++){
            users.add(new BasicUser(i));
            for(Integer j = 0; j<4; j++){
                relationships.add(new Relationship(
                        new BasicUser(randomGenerator.nextInt(i)+1),
                        new BasicUser(randomGenerator.nextInt(i)+1)
                ));
            }
        }
    }


    @Override
    public Collection<? extends User<Integer>> getAllUsers() {
        return users;
    }

    @Override
    public Collection<Relationship> getAllRelationships() {
        return relationships;
    }
}
