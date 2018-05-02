package Handler;

import IO.InputReader;
import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class UpdateNameHandler implements HttpHandler {
    private final GreetingService greetingService;
    private final InputReader inputReader;
    private final OutputWriter outputWriter;

    public UpdateNameHandler(GreetingService greetingService, InputReader inputReader, OutputWriter outputWriter) {
        this.greetingService = greetingService;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String input = inputReader.readFromInputStream(httpExchange.getRequestBody());
        String oldName = input.split(",")[0];
        String newName = input.split(",")[1];

        String response = greetingService.updateName(oldName, newName);
        outputWriter.writeToOutputStream(httpExchange, 200, response);
    }
}
