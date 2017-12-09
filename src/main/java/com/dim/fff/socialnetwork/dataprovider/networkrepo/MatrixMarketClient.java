package com.dim.fff.socialnetwork.dataprovider.networkrepo;

import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.User;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class MatrixMarketClient implements DataLoader {

    private HashSet<User> users = new HashSet<>();
    private HashSet<Relationship> relationships = new HashSet<>();

    public MatrixMarketClient(File mtxDataset) throws IOException{
        Files.readLines(mtxDataset, Charsets.UTF_8)
                .stream()
                .filter(line -> line.matches("\\d*\\s\\d*"))
                .forEach(line -> {
                    String[] splited = line.split(" ");

                    User user1 = new User(Long.valueOf(splited[0]));
                    User user2 = new User(Long.valueOf(splited[1]));

                    users.add(user1);
                    users.add(user2);

                    relationships.add(new Relationship(user1, user2));
                });

    }

    @Override
    public HashSet<User> getAllUsers() {
        return this.users;
    }

    public HashSet<Relationship> getAllRelationships() {
        return this.relationships;
    }

    @Override
    public Collection<Group> getAllGroups() {
        return new HashSet<>();
    }
}
