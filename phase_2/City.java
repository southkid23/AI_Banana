/*
* City.java
* City object that contains a x and y coordinates 
* 
*/

package phase_2;

public class City {
    int x;
    int y;
    
    // Constructs a randomly placed city
    public City(){

        // The size of the domain and range: 100 x 100(squares)
        int sizeOfMap = 100;

        this.x = (int)(Math.random() * sizeOfMap);
        this.y = (int)(Math.random() * sizeOfMap);
    }
    
    // Constructs a city at chosen x, y location
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }
    
    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }
    
    // Gets the distance to given city
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