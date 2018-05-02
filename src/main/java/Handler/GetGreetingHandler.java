package Handler;

import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class GetGreetingHandler implements HttpHandler {

    private GreetingService greetingService;
    private OutputWriter outputWriter;

    public GetGreetingHandler(GreetingService greetingService, OutputWriter outputWriter) {
        this.greetingService = greetingService;
        this.outputWriter = outputWriter;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = greetingService.getDefaultGreeting();
        outputWriter.writeToOutputStream(httpExchange, 200, response);
    }
}
