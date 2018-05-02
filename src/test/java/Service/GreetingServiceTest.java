package Service;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GreetingServiceTest {

    @Test
    public void getDefaultGreeting_ShouldReturnDefaultGreetingWithTimeDate(){
        GreetingService greetingService = new GreetingService();

        LocalDateTime currentDateTime = LocalDateTime.now();
        String expectedResponse = String.format("Hello Thi - the time on the server is %s on %s",
                currentDateTime.format(DateTimeFormatter.ofPattern("hh:mm a")),
                currentDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        String actualResponse = greetingService.getDefaultGreeting();

        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    public void addNameToGreeting_ShouldReturnCustomGreeting_WithInputName(){
        GreetingService greetingService = new GreetingService();
        List<String> expectedNameList = Arrays.asList(new String[]{"Thi", "Bob"});

        String inputName = "Bob";
        String expectedResponse = String.format("%s added", inputName);

        String actualResponse = greetingService.addNameToGreeting(inputName);
        assertThat(actualResponse, equalTo(expectedResponse));
        assertThat(greetingService.getNames(), equalTo(expectedNameList));
    }

    @Test
    public void deleteNameFromGreeting_ShouldDeleteNameFromNameList(){
        GreetingService greetingService = new GreetingService();

        greetingService.addNameToGreeting("Bob");
        greetingService.addNameToGreeting("Tina");
        greetingService.addNameToGreeting("Tom");

        String deleteResponse = greetingService.deleteNameFromGreeting("Tina");
        assertThat(deleteResponse, equalTo("Tina removed"));
        assertThat(greetingService.getNames(), equalTo(Arrays.asList(new String[]{"Thi", "Bob", "Tom"})));

        String notFoundResponse = greetingService.deleteNameFromGreeting("Ash");
        assertThat(notFoundResponse, equalTo("Ash not found"));
        assertThat(greetingService.getNames(), equalTo(Arrays.asList(new String[]{"Thi", "Bob", "Tom"})));
    }
}