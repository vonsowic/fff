package com.dim.fff.socialnetwork.dataprovider.random;

import com.dim.fff.socialnetwork.dataprovider.Client;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;

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

    private final Integer numberOfNodes;
    private HashSet<String> users = new HashSet<>();
    private HashSet<String> relationships = new HashSet<>();


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
            users.add(i.toString());
            for(Integer j = 0; j<4; j++){
                relationships.add(
                        Relationship.generateEdgeId(
                                String.valueOf(i),
                                String.valueOf(randomGenerator.nextInt(numberOfNodes) + 1)
                        )
                );
            }
        }
    }


    @Override
    public Collection<String> getAllUsers() {
        return users;
    }

    @Override
    public Collection<String> getAllRelationships() {
        return relationships;
    }

    @Override
    public Collection<Group> getAllGroups() {
        return new HashSet<>();
    }
}
