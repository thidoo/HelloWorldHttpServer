import Handler.*;
import IO.InputReader;
import IO.OutputWriter;
import Service.GreetingService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    private HttpServer httpServer;

    public SimpleHttpServer(int port) {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);

            GreetingService greetingService = new GreetingService();
            InputReader inputReader = new InputReader();
            OutputWriter outputWriter = new OutputWriter();

            httpServer.createContext("/", new GetGreetingHandler(greetingService, outputWriter));
            httpServer.createContext("/add", new AddNameHandler(greetingService, inputReader, outputWriter));
            httpServer.createContext("/update", new UpdateNameHandler(greetingService, inputReader, outputWriter));
            httpServer.createContext("/delete", new DeleteNameHandler(greetingService, inputReader,outputWriter));
            httpServer.createContext("/names", new GetAllNamesHandler(greetingService,outputWriter));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        httpServer.start();
    }
}
