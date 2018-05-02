package Handler;

import IO.InputReader;
import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class DeleteNameHandler implements HttpHandler {
    private GreetingService greetingService;
    private final InputReader inputReader;
    private final OutputWriter outputWriter;

    public DeleteNameHandler(GreetingService greetingService, InputReader inputReader, OutputWriter outputWriter) {
        this.greetingService = greetingService;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String inputName = inputReader.readFromInputStream(httpExchange.getRequestBody());
        String response = greetingService.deleteNameFromGreeting(inputName);
        outputWriter.writeToOutputStream(httpExchange, 200, response);
    }
}
