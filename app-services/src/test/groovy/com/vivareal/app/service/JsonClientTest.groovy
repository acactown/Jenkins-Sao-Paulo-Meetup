package com.vivareal.app.service

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Andrés Amado
 */
class JsonClientTest extends Assert {

    private JsonClient client

    @Before
    public void setUp() throws Exception {
        client = new JsonClient()
    }

    @Test(expected = Exception)
    public void testGet() {
        client.get(null)
    }
}
