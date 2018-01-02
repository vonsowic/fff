package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import com.dim.fff.socialnetwork.dataprovider.random.TestDataClient;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 02.01.18
 */
public class NetworkTest {

    private Network network;

    @Before
    public void prepareTestData(){
        DataLoader loader = new TestDataClient();
        network = NetworkBuilder.newInstance()
                .addUsers(loader.getAllUsers())
                .addRelationships(loader.getAllRelationships())
                .addGroups(loader.getAllGroups())
                .build();
    }

    @Test
    public void findAllGroups() throws Exception {
        assertThat(network.findAllGroups())
                .hasSize(9)
                .contains("circle0", "circle1", "circle2", "circle3", "circle4", "circle5");
    }

    @Test
    public void findAllGroupsWithUsers() throws Exception {
        assertThat(network.findAllGroupsWithUsers())
                .hasSize(9)
                .contains(new AbstractMap.SimpleEntry<String, Set<String>>("circle1", new HashSet<>(Arrays.asList("8", "14", "2", "19", "16"))));
    }

}