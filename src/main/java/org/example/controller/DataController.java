package org.example.controller;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Model.Route;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class DataController {

    List<Route> routes = new ArrayList<>();

    public void readData () throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\ericn\\OneDrive\\Skrivbord\\SortingPuzzle1\\src\\main\\java\\org\\example\\data\\library_data (2).json"));

        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                Route route = objectMapper.convertValue(node, Route.class);
                routes.add(route);
            }
        }

    }

    public void trip (String startingLocation, String endingLocation)  {
        ArrayList<ArrayList<Route>> finalRoutes = new ArrayList<>();
        LocalDateTime deadline = LocalDateTime.parse("2024-09-03T23:59:00");

        ArrayList<Route> currentStop = getEndingLocation("Ecuador", deadline);

        for (Route route : currentStop) {
            LocalDateTime currentTime = getArrivalTime(route);

            ArrayList<Route> nextStop = getEndingLocation(route.getTravelFrom(), currentTime);

        }

        ArrayList<Route> currentRoute = new ArrayList<>();

    }





    public ArrayList<Route> getEndingLocation(String endingLocation, LocalDateTime deadline) {

        ArrayList<Route> potentialRoute = new ArrayList<>();
        String startingLocation = "Sverige";
        LocalDateTime arrivalTime;
        LocalDateTime startingTime = LocalDateTime.parse("2024-09-01T00:00:00");

        for (Route route : routes) {
            arrivalTime = getArrivalTime(route);

                if (route.getTravelTo().equals(endingLocation) && (deadline.isAfter(arrivalTime) || route.getDepartureDateTime().isEqual(arrivalTime))) {

                    potentialRoute.add(route);

                    System.out.println(getArrivalTime(route) + " from " + route.getTravelFrom() + " to " + route.getTravelTo());
                }

        }

        return potentialRoute;
    }

    public LocalDateTime getArrivalTime(Route currentLocation) {
        return currentLocation.getDepartureDateTime()
                .plusHours(currentLocation.getTravelTime().getHour())
                .plusMinutes(currentLocation.getTravelTime().getMinute())
                .plusSeconds(currentLocation.getTravelTime().getSecond());
    }


//    public void createRoute () {
//        currentStop;
//        while (!currentStop.equals("Sverige"))
//        {
 //             current time
//            currentTrip.add(currentStop);
//            currentStop = getNextStop(currentStop);
//        }
//    }
}
