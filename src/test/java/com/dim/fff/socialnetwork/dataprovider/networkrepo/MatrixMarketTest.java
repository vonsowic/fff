package com.dim.fff.socialnetwork.dataprovider.networkrepo;

import com.dim.fff.socialnetwork.basic.BasicUser;
import com.dim.fff.socialnetwork.corenetwork.Relationship;
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
public class MatrixMarketTest {

    private MatrixMarket dataset;

    @Before
    public void initialize() throws IOException {
        URL url = Resources.getResource("matrixmarket/socfb-MSU24/socfb-MSU24.mtx");
        dataset = new MatrixMarket(new File(url.getPath()));
    }

    @Test
    public void getAllUsers() throws Exception {
        HashSet<BasicUser> users = dataset.getAllUsers();
        assertThat(users)
                .hasSize(32375)
                .contains(new BasicUser(2561))
                .contains(new BasicUser(1))
                .contains(new BasicUser(21))
                .contains(new BasicUser(251))
                .doesNotContain(new BasicUser(9999999));
    }

    @Test
    public void getAllRelationships() throws Exception {
        HashSet<Relationship> relationships = dataset.getAllRelationships();
        assertThat(relationships).hasSize(1118774);
    }

}