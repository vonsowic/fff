package com.dim.fff.socialnetwork.dataprovider.stanford;

import com.dim.fff.socialnetwork.dataprovider.Client;
import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Group;
import com.dim.fff.socialnetwork.dataprovider.dataobjects.Relationship;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.util.*;
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

    protected String getPath(){
        return "facebook";
    }

    private final int fileLimit = 1;

    @Override
    public Collection<String> getAllUsers() {
        HashSet<String> users = new HashSet<>();

        getAllRelationships()
                .forEach(relationship -> users.addAll(
                        Arrays.asList(
                                Relationship.usersOf(relationship)
                        )
                ));

        return users;
    }

    @Override
    public Collection<String> getAllRelationships() {
        Collection<String> relationships = new HashSet<>();
        getFilesFromResources("edges")
                .forEach(file -> {
                    try {
                        Files.readLines(file, Charsets.UTF_8)
                                .stream()
                                .filter(line -> line.matches("\\d*\\s\\d*"))
                                .forEach(line -> {
                                    String[] splited = line.split(" ");

                                    relationships.add(
                                            Relationship.generateEdgeId(
                                                    splited[0],
                                                    splited[1]
                                            ));
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return relationships;
    }


    @Override
    public Collection<Group> getAllGroups() {
        Collection<Group> groups = new HashSet<>();
        getFilesFromResources("circles")
                .forEach(file -> {
                    try {
                        Files.readLines(file, Charsets.UTF_8)
                                .forEach(line -> {
                                    List<String> splited = new ArrayList<>(Arrays.asList(line.split("\t")));

                                    groups.add(
                                            new Group(
                                                    splited.get(0),
                                                    splited.subList(1, splited.size())
                                            )
                                    );
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return groups;
    }


    private File getFileFromResources(String resourcePath){
        return new File(Resources.getResource(resourcePath).getPath());
    }

    private Stream<File> getFilesFromResources(String fileContains){
        return Files
                .fileTreeTraverser()
                .preOrderTraversal(getFileFromResources(getPath()))
                .filter(file -> file.getPath().contains(fileContains))
                .limit(fileLimit)
                .stream();
    }
}
