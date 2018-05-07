package Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreetingService {

    private static final String MY_NAME = "Ngoc";
    private static List<String> names;

    public GreetingService() {
        names = new ArrayList<>();
        names.add(MY_NAME);
    }

    public String getDefaultGreeting() {
        String time = getCurrentDateTime(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = getCurrentDateTime(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String names = computeNameString();
        return String.format("Hello %s - the time on the server is %s on %s", names, time, date);
    }

    public String addNameToGreeting(String name) {
        names.add(name);
        return String.format("%s added", name);
    }

    public String updateName(String oldName, String newName){
        if (names.remove(oldName)){
            names.add(newName);
            return String.format("Name %s has been changed to %s", oldName, newName);
        }
        return String.format("%s not found", oldName);
    }

    public String deleteNameFromGreeting(String name) {
        if (names.remove(name)){
            return String.format("%s removed", name);
        }

        return String.format("%s not found", name);
    }

    public String getAllNames(){
        return names.stream().collect(Collectors.joining(", "));
    }

    public static List<String> getNames() {
        return names;
    }

    private String computeNameString(){
        return names.stream().collect(Collectors.joining(","));
    }

    private String getCurrentDateTime(DateTimeFormatter dateTimeformatter) {
        ZonedDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("UTC+10:00"));
        return currentTime.format(dateTimeformatter);
    }
}
