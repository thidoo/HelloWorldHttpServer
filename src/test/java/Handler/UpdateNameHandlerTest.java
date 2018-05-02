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

public class UpdateNameHandlerTest {

    UpdateNameHandler updateNameHandler;
    GreetingService greetingService;
    HttpExchange httpExchange;
    OutputStream outputStream;

    @Before
    public void setUp(){
        greetingService = mock(GreetingService.class);
        httpExchange = mock(HttpExchange.class);
        outputStream = mock(OutputStream.class);
        updateNameHandler = new UpdateNameHandler(greetingService, new InputReader(), new OutputWriter());
    }

    @Test
    public void updateNameShouldReplaceExistingNameWithNewName() throws IOException {
        String fakeInput = "Bob,Dylan";
        byte[] byteInput = fakeInput.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteInput);

        when(httpExchange.getRequestBody()).thenReturn(inputStream);
        when(greetingService.updateName("Bob", "Dylan")).thenReturn(String.format("Name Bob has been replaced by Dylan"));
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        updateNameHandler.handle(httpExchange);

        verify(greetingService).updateName("Bob", "Dylan");
    }
}
