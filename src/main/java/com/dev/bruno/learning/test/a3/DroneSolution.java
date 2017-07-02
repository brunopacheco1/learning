package com.dev.bruno.learning.test.a3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DroneSolution {

	private static class Drone {
        private int id;
        private int flightRange;

        public Drone(int id, int flightRange) {

            this.id = id;
            this.flightRange = flightRange;
        }

        public int getId() {
            return id;
        }

        public int getFlightRange() {
            return flightRange;
        }
    }
	
	static List<Integer> greatestFlightRangeDrones(Integer numberOfRequiredDrones, List<Drone> drones, List<Integer> inMaintenanceDrones) {
        // TODO: implement this function
        drones = drones.parallelStream().filter(drone -> !inMaintenanceDrones.contains(drone.getId())).collect(Collectors.toList());
        
        drones = drones.parallelStream().sorted(Comparator.comparing(Drone::getFlightRange)).collect(Collectors.toList());
        
        List<Integer> ids = new ArrayList<>();
        
        int start = Math.max(0, drones.size() - numberOfRequiredDrones); 
        for(int index = drones.size() - 1; index >= start; index--) {
        	ids.add(drones.get(index).getId());
        }
        
        return ids;
    }

	 // The first line of the input contains three decimal integers separated by space: total number of drones ('D'), number of drones to be selected ('G') and number of drones in maintenance ('M').
    // The following 'D' lines each contains two decimal integers separated by space with information about each drone: ID and flight range in kilometers.
    // The following 'M' lines each contains the numeric ID of a drone currently in maintenance.

    // Print the IDs of the 'G' selected drones to the standard output, one per line, sorted by flight range (greater first).
    public static void main(String[] args) throws IOException {
    	Scanner in = new Scanner(Paths.get("inputs/input_drones.txt"));

        int numberOfDrones = in.nextInt();
        int numberOfRequiredDrones = in.nextInt();
        int numberOfDronesInMaintenance = in.nextInt();

        List<Drone> drones = new ArrayList<>();
        List<Integer> inMaintenanceDrones = new ArrayList<>();

        for(int i=0; i< numberOfDrones; i++) {
            drones.add(new Drone(in.nextInt(), in.nextInt()));
        }

        for(int i=0; i< numberOfDronesInMaintenance; i++) {
            inMaintenanceDrones.add(in.nextInt());
        }

        List<Integer> greatestFlightRangeDrones = greatestFlightRangeDrones(numberOfRequiredDrones, drones, inMaintenanceDrones);

        for(int i=0; i < greatestFlightRangeDrones.size(); i++) {
            System.out.println(greatestFlightRangeDrones.get(i));
        }

    }
}
