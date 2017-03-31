/*
* RouteManager.java
* Holds the cities of a tour
*/

package phase_2;

import java.util.ArrayList;

public class RouteManager {

    // A world that contains all of the cities
    private static ArrayList worldMap = new ArrayList<City>();

    // Adds a new city to the world map
    public static void addCity(City city) {
        worldMap.add(city);
    }
    
    // Get a city from the world map
    public static City getCity(int index){
        return (City)worldMap.get(index);
    }
    
    // Get the total count of cities that are in the world map
    public static int numberOfCities(){
        return worldMap.size();
    }
}