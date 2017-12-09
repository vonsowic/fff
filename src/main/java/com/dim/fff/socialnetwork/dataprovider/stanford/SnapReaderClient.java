package com.dim.fff.socialnetwork.dataprovider.stanford;

import com.dim.fff.socialnetwork.dataprovider.Client;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.User;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Reads data from resources/facebook.
 * Dataset was manually downloaded from https://snap.stanford.edu/data/egonets-Facebook.html
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
@Client
public class SnapReaderClient implements DataLoader {

    private final String path = "facebook";

    @Override
    public Collection<User> getAllUsers() {
        HashSet<User> users = new HashSet<>();

        getAllRelationships()
                .forEach(relationship -> {
                    users.add(relationship.getUser1());
                    users.add(relationship.getUser2());
                });

        return users;
    }

    @Override
    public Collection<Relationship> getAllRelationships() {
        Collection<Relationship> relationships = new HashSet<>();
        getFilesFromResources("edges")
                .forEach(file -> {
                    try {
                        Files.readLines(file, Charsets.UTF_8)
                                .stream()
                                .filter(line -> line.matches("\\d*\\s\\d*"))
                                .forEach(line -> {
                                    String[] splited = line.split(" ");

                                    relationships.add(
                                            new Relationship(
                                                    new User(Long.valueOf(splited[0])),
                                                    new User(Long.valueOf(splited[1]))
                                            )
                                    );
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return relationships;
    }


    @Override
    public Collection<Group> getAllGroups() {
        // TODO : wczytywanie grup. podobnie jak z getAllRelationships, tyle że grupy są w plikach "*.circles"
        return new HashSet<>();
    }


    private File getFileFromResources(String resourcePath){
        return new File(Resources.getResource(resourcePath).getPath());
    }

    private Stream<File> getFilesFromResources(String fileContains){
        return Files
                .fileTreeTraverser()
                .preOrderTraversal(getFileFromResources(path))
                .filter(file -> file.getPath().contains(fileContains))
                .stream();
    }
}
