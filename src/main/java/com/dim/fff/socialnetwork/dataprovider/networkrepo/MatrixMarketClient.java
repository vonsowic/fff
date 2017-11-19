package com.dim.fff.socialnetwork.dataprovider.networkrepo;

import com.dim.fff.socialnetwork.basic.BasicUser;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class MatrixMarketClient implements DataLoader<Integer>{

    private HashSet<BasicUser> users = new HashSet<>();
    private HashSet<Relationship> relationships = new HashSet<>();

    public MatrixMarketClient(File mtxDataset) throws IOException{
        Files.readLines(mtxDataset, Charsets.UTF_8)
                .stream()
                .filter(line -> line.matches("\\d*\\s\\d*"))
                .forEach(line -> {
                    String[] splited = line.split(" ");

                    BasicUser user1 = new BasicUser();
                    user1.setId(Integer.valueOf(splited[0]));

                    BasicUser user2 = new BasicUser();
                    user2.setId(Integer.valueOf(splited[1]));

                    users.add(user1);
                    users.add(user2);

                    relationships.add(new Relationship(user1, user2));
                });

    }

    @Override
    public HashSet<BasicUser> getAllUsers() {
        return this.users;
    }

    public HashSet<Relationship> getAllRelationships() {
        return this.relationships;
    }
}
