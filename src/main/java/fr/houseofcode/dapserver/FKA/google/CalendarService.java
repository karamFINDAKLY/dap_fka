package fr.houseofcode.dapserver.FKA.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

@Service
public class CalendarService {

    private static final Logger LOG = LogManager.getLogger();

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws GeneralSecurityException 
     * @throws IOException If the credentials.json file cannot be found.
     */
    private Calendar getService(String userKey) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                Utils.getCredentials(HTTP_TRANSPORT, userKey)).setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    public String afficherNextEvent(String userKey) throws IOException, GeneralSecurityException {
        LOG.info("Start of the get next event method");
        String result;
        // Build a new authorized API client service.

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKey).events().list("primary").setMaxResults(10).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            result = "No upcoming events found.";
        } else {
            result = "Upcoming events";
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                result = event.getSummary() + "(" + start + ")";
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
        LOG.debug("The next event is : " + result);
        return result;
    }
}
