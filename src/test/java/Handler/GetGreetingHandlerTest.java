package Handler;

import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetGreetingHandlerTest {

    GetGreetingHandler getGreetingHandler;
    GreetingService greetingService;
    HttpExchange httpExchange;
    OutputStream outputStream;

    @Before
    public void setUp(){
        greetingService = mock(GreetingService.class);
        httpExchange = mock(HttpExchange.class);
        outputStream = mock(OutputStream.class);
        getGreetingHandler = new GetGreetingHandler(greetingService, new OutputWriter());
    }

    @Test
    public void rootEndpointShouldSendDefaultGreeting() throws IOException {
        when(greetingService.getDefaultGreeting()).thenReturn("Hello World!");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        getGreetingHandler.handle(httpExchange);
        verify(greetingService).getDefaultGreeting();
    }
}