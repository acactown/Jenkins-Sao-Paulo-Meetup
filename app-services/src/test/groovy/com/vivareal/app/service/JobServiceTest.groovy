package com.vivareal.app.service

import groovy.json.JsonSlurper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.api.mockito.PowerMockito
import org.powermock.modules.junit4.PowerMockRunner

import static org.mockito.Matchers.*
import static org.mockito.MockitoAnnotations.*

/**
 * @author Andrés Amado
 */
@RunWith(PowerMockRunner)
class JobServiceTest extends Assert {

    @Mock
    private RestClient client
    @InjectMocks
    private JobService service

    private static final JSON_RESPONSE = "{\"jobs\":[" +
            "{\"name\":\"job1\",\"url\":\"http://job1.com/\",\"color\":\"disabled\"}," +
            "{\"name\":\"job2\",\"url\":\"http://job2.com/\",\"color\":\"blue\"}," +
            "{\"name\":\"job3\",\"url\":\"http://job3.com/\",\"color\":\"red\"}," +
            "{\"name\":\"job4\",\"url\":\"http://job4.com/\",\"color\":\"disabled\"}]}"

    @Before
    public void setUp() throws Exception {
        initMocks(this)
        PowerMockito.when(client.get(any(String))).thenReturn(new JsonSlurper().parseText(JSON_RESPONSE))
    }

    @Test
    public void testGetJobs() {
        assertEquals("Error getting jobs!", 4, service.getJobs(4).size())
    }
}
