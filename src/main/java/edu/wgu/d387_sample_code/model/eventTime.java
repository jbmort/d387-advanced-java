package edu.wgu.d387_sample_code.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class eventTime {

    public static List<String> getEventMessage(){
        List<ZonedDateTime> times = getDateTime();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        String utcDateEvent = times.get(0).format(dateFormatter);
        String utcTimeEvent = times.get(0).format(timeFormatter);
        String etTimeEvent = times.get(1).format(timeFormatter);
        String mtimeEvent = times.get(2).format(timeFormatter);

        String invite = "Join us for an online live presentation at the Landon Hotel on " + utcDateEvent;
        String et = etTimeEvent + " | ET";
        String m = mtimeEvent + " | MT";
        String utc = utcTimeEvent + " | UTC";

        return Arrays.asList(invite, et, m, utc);
    }

    public static List<ZonedDateTime> getDateTime() {
        LocalDateTime event = LocalDateTime.of(2025, 9, 15, 17, 0, 0, 0);
        ZonedDateTime utcEvent = event.atZone(ZoneId.of("UTC"));

        ZonedDateTime etEvent = utcEvent.withZoneSameInstant(ZoneId.of("America/New_York"));

        ZonedDateTime mtEvent = utcEvent.withZoneSameInstant(ZoneId.of("America/Denver"));

        List<ZonedDateTime> times;
        times = Arrays.asList(utcEvent, etEvent, mtEvent);

        return times;
    }

}
