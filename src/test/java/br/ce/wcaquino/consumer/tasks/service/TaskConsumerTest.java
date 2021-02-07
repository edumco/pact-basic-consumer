package br.ce.wcaquino.consumer.tasks.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ce.wcaquino.consumer.tasks.model.Task;
import br.ce.wcaquino.consumer.utils.RequestHelper;

@RunWith(MockitoJUnitRunner.class)
public class TaskConsumerTest {

    private static final String URL_ERRADA = "url errada";

    @InjectMocks
    private TasksConsumer consumer = new TasksConsumer(URL_ERRADA);

    @Mock
    private RequestHelper helper;

    @Test
    public void shouldGetAnExistingTask() throws ClientProtocolException, IOException {

        Map<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("id", "1");
        expectedMap.put("task", "taskMocada");
        expectedMap.put("dueDate", "2000-01-01");

        Mockito
            .when(helper.get(URL_ERRADA + "/todo/1"))
            .thenReturn(expectedMap);

        Task task = consumer.getTask(1L);

        Assert.assertNotNull(task);
    }
    
}
