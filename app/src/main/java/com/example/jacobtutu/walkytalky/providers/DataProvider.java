package com.example.jacobtutu.walkytalky.providers;

import java.io.IOException;

/**
 * Created by jacobtutu on 18/03/17.
 */

public interface DataProvider {
    /**
     * Read data source as string
     *
     * @return  string containing data read from source
     * @throws IOException  when error occurs reading from source
     */
    String dataSourceToString() throws IOException;
    /**
     * Read data source as bytes
     *
     * @return  bytes containing data read from source
     * @throws IOException  when error occurs reading from source
     */
    byte[] dataSourceToBytes() throws IOException;
}
