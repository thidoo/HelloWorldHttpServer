package Handler;

import IO.InputReader;
import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteNameHandlerTest {

    DeleteNameHandler deleteNameHanlder;
    GreetingService greetingService;
    HttpExchange httpExchange;
    OutputStream outputStream;

    @Before
    public void setUp(){
        greetingService = mock(GreetingService.class);
        httpExchange = mock(HttpExchange.class);
        outputStream = mock(OutputStream.class);
        deleteNameHanlder = new DeleteNameHandler(greetingService, new InputReader(), new OutputWriter());
    }

    @Test
    public void handleShouldSendGreetingWithInputNames() throws IOException {
        String fakeInput = "Bob";
        byte[] byteInput = fakeInput.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteInput);

        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(greetingService.deleteNameFromGreeting(fakeInput)).thenReturn(String.format("%s removed", fakeInput));
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        deleteNameHanlder.handle(httpExchange);

        verify(greetingService).deleteNameFromGreeting("Bob");
    }
}
