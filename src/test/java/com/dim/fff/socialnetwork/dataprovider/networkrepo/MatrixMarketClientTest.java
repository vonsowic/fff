package com.dim.fff.socialnetwork.dataprovider.networkrepo;

import com.dim.fff.socialnetwork.corenetwork.dataobjects.Relationship;
import com.dim.fff.socialnetwork.corenetwork.dataobjects.User;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 18.11.17
 */
public class MatrixMarketClientTest {

    private MatrixMarketClient dataset;

    @Before
    public void initialize() throws IOException {
        URL url = Resources.getResource("matrixmarket/socfb-MSU24/socfb-MSU24.mtx");
        dataset = new MatrixMarketClient(new File(url.getPath()));
    }

    @Test
    public void getAllUsers() throws Exception {
        HashSet<User> users = dataset.getAllUsers();
        assertThat(users)
                .hasSize(32375)
                .contains(new User(2561L))
                .contains(new User(1L))
                .contains(new User(21L))
                .contains(new User(251L))
                .doesNotContain(new User(9999999L));
    }

    @Test
    public void getAllRelationships() throws Exception {
        HashSet<Relationship> relationships = dataset.getAllRelationships();
        assertThat(relationships).hasSize(1118774);
    }

}