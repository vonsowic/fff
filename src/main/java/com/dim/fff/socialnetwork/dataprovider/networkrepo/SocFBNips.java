package com.dim.fff.socialnetwork.dataprovider.networkrepo;

import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 19.11.17
 */
public class SocFBNips extends MatrixMarket {
    public SocFBNips() throws IOException {
        super(new File(Resources.getResource("socfb-nips-ego/socfb-nips-ego.edges").getPath()));
    }
}
