package com.dim.fff.socialnetwork.dataprovider.networkrepo;

import com.dim.fff.socialnetwork.dataprovider.Client;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 19.11.17
 */
@Client
public class SocFBClient extends MatrixMarketClient {

    public SocFBClient() throws IOException {
        super(new File(Resources.getResource("matrixmarket/socfb-MSU24/socfb-MSU24.mtx").getPath()));
    }
}
