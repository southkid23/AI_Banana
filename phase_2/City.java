/*
* City.java
* City object that contains a x and y coordinates 
* 
*/

package phase_2;

public class City {
    int x;
    int y;
    

    // Contructs a city at random location
    public City(){

        // The size of the domain and range: 200 and 200
        int sizeOfMap = 200;

        this.x = (int)(Math.random() * sizeOfMap);
        this.y = (int)(Math.random() * sizeOfMap);
    }
    
    // Contructs a city with given coordinates
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Returns the x position of a city
    public int getX(){
        return this.x;
    }
    
    // Returns the y position of a city
    public int getY(){
        return this.y;
    }
    
    // Returns the distance between 2 cities
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}