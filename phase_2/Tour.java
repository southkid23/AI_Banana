/*
* Tour.java
* Stores a candidate tour
*/

package phase_2;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{

    // Holds our tour of cities
    public ArrayList tour = new ArrayList<City>();
    // Cache
    private int fitness = 0;
    private int distance = 0;
    
    // Constructor: empty journey
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList tour){
        this.tour = tour;
    }

    public Tour(Tour tour) {
        for(int i = 0; i < tour.tour.size(); i++){
            this.tour.add(tour.tour.get(i));
        }
        this.fitness = tour.getFitness();
        this.fitness = tour.getDistance();
    }


    public void printTour() {

        System.out.println(fitness);
    }

    // Creates a random route
    public void generateIndividual() {
        // Loop through the world Map and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, TourManager.getCity(cityIndex));
        }

        // Random reordering of the generated tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);

        // Reset the fitness and distance when there are changes to the tour
        fitness = 0;
        distance = 0;
    }
    
    // Returns the fitness
    public int getFitness() {
            fitness = getDistance();
        return fitness;
    }
    
    // Returns the distance of the tour
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Going throught the cities in our tour
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Gets the origin city
                City fromCity = getCity(cityIndex);
                // The city we're moving to next
                City destinationCity;
                
                // Point back to the origin city if we're at the last city
                // in the world map
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // The distance between two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Returns the total size of our travel
    public int tourSize() {
        return tour.size();
    }
    
    // Checks if the tour has a city
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}