package com.dev.bruno.learning.test.a3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
        List<Integer> result = new ArrayList<>();
        
        Map<Integer, List<Integer>> dronesByFlightRange = new TreeMap<>();
        
        drones.forEach(drone -> {
        	if(!inMaintenanceDrones.contains(drone.getId())) {
        		int flightRange = drone.getFlightRange();
        		
	        	if(!dronesByFlightRange.containsKey(flightRange)) {
	        		dronesByFlightRange.put(flightRange, new ArrayList<>());
	        	}
	        	
	        	dronesByFlightRange.get(flightRange).add(drone.getId());
        	}
        });
        
        List<Integer> keys = new ArrayList<>(dronesByFlightRange.keySet());
        
        keys : for(int index = keys.size() - 1; index >= 0; index--) {
        	List<Integer> ids = dronesByFlightRange.get(keys.get(index));
        	
        	for(Integer id : ids) {
        		result.add(id);
        		
        		if(numberOfRequiredDrones == result.size()) {
        			break keys;
        		}
        	}
        }
        
        return result;
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

        in.close();
    }
}
