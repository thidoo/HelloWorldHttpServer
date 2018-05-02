package Handler;

import IO.InputReader;
import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddNameHandlerTest {

    AddNameHandler addNameHandler;
    GreetingService greetingService;
    HttpExchange httpExchange;
    OutputStream outputStream;

    @Before
    public void setUp(){
        greetingService = mock(GreetingService.class);
        httpExchange = mock(HttpExchange.class);
        outputStream = mock(OutputStream.class);
        addNameHandler = new AddNameHandler(greetingService, new InputReader(), new OutputWriter());
    }

    @Test
    public void handleShouldSendGreetingWithInputNames() throws IOException {
        String fakeInput = "Bob";
        byte[] byteInput = fakeInput.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteInput);

        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(greetingService.addNameToGreeting(fakeInput)).thenReturn(String.format("%s added", fakeInput));
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        addNameHandler.handle(httpExchange);

        verify(greetingService).addNameToGreeting("Bob");
    }
}
