package com.dim.fff.socialnetwork.dataprovider.random;

import com.dim.fff.socialnetwork.dataprovider.Client;
import com.dim.fff.socialnetwork.dataprovider.stanford.SnapReaderClient;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 19.12.17
 */
@Client
public class TestDataClient extends SnapReaderClient{

    @Override
    protected String getPath() {
        return "probne";
    }
}
