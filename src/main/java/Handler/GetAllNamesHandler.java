package Handler;

import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.stream.Collectors;

public class GetAllNamesHandler implements HttpHandler {
    private final GreetingService greetingService;
    private final OutputWriter outputWriter;

    public GetAllNamesHandler(GreetingService greetingService, OutputWriter outputWriter) {
        this.greetingService = greetingService;
        this.outputWriter = outputWriter;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = greetingService.getAllNames();
        outputWriter.writeToOutputStream(httpExchange, 200, response);
    }
}
