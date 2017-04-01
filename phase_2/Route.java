/*
* Route.java
* Stores a candidate route
*/

package phase_2;

import java.util.ArrayList;
import java.util.Collections;

public class Route{

    public ArrayList route = new ArrayList<City>();
    private int fitness = 0;
    private int distance = 0;
    
    // Constructor: empty journey
    public Route(){
        for (int i = 0; i < RouteManager.numberOfCities(); i++) {
            route.add(null);
        }
    }
    
    public Route(ArrayList route){
        this.route = route;
    }

    public Route(Route route) {
        for(int i = 0; i < route.route.size(); i++){
            this.route.add(route.route.get(i));
        }
        this.fitness = route.getFitness();
        this.fitness = route.getDistance();
    }

    public void printRoute() {

        System.out.println(fitness);
    }

    // Creates a random route
    public void generateIndividual() {
        // Loop through the world Map and add them to our route
        for (int cityIndex = 0; cityIndex < RouteManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, RouteManager.getCity(cityIndex));
        }

        // Random reordering of the generated route
        Collections.shuffle(route);
    }

    // Gets a city from the route
    public City getCity(int routePosition) {
        return (City)route.get(routePosition);
    }

    // Sets a city in a certain position within a route
    public void setCity(int routePosition, City city) {
        route.set(routePosition, city);

        // Reset the fitness and distance when there are changes to the route
        fitness = 0;
        distance = 0;
    }
    
    // Returns the fitness
    public int getFitness() {
            fitness = getDistance();
        return fitness;
    }
    
    // Returns the distance of the route
    public int getDistance(){
        if (distance == 0) {
            int routeDistance = 0;
            // Going throught the cities in our route
            for (int cityIndex=0; cityIndex < routeSize(); cityIndex++) {
                // Gets the origin city
                City fromCity = getCity(cityIndex);
                // The city we're moving to next
                City destinationCity;
                
                // Point back to the origin city if we're at the last city
                // in the world map
                if(cityIndex+1 < routeSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // The distance between two cities
                routeDistance += fromCity.distanceTo(destinationCity);
            }
            distance = routeDistance;
        }
        return distance;
    }

    // Returns the total size of our travel
    public int routeSize() {
        return route.size();
    }
    
    // Checks if the route has a city
    public boolean containsCity(City city){
        return route.contains(city);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < routeSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}