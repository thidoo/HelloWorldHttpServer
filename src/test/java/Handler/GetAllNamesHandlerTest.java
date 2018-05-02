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

public class GetAllNamesHandlerTest {

    GetAllNamesHandler getAllNamesHandler;
    GreetingService greetingService;
    HttpExchange httpExchange;
    OutputStream outputStream;

    @Before
    public void setUp(){
        greetingService = mock(GreetingService.class);
        httpExchange = mock(HttpExchange.class);
        outputStream = mock(OutputStream.class);
        getAllNamesHandler = new GetAllNamesHandler(greetingService, new OutputWriter());
    }

    @Test
    public void rootEndpointShouldSendDefaultGreeting() throws IOException {
        when(greetingService.getAllNames()).thenReturn("Thi, Bob");
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        getAllNamesHandler.handle(httpExchange);
        verify(greetingService).getAllNames();
    }
}
