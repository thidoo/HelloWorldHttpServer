package IO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class InputReader {

    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())));

        int c;
        while ((c = reader.read()) != -1) {
            stringBuilder.append((char) c);
        }

        return stringBuilder.toString();
    }
}
