package phase_2;

import java.io.*;
import java.util.*;

public class ReadCSV {

    private static List<String> originalList = new ArrayList<String>();

	public static void read() throws Exception{
        
		String fileName = "Cities_20.csv";
        String csvFile = new File("./phase_2/resources/"+fileName).getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line = "";

        // int i = 0;
        while ((line = br.readLine()) != null) {
            
            /* 
            	Values are seperated by comma,
        		pos[0] is the x position,
            	pos[1] is the y position 
            */

            String[] pos = line.split(",");

            originalList.add(pos[0]);
            originalList.add(pos[1]);

            City city = new City(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        	RouteManager.addCity(city);

        	// Testing class functions behaviors
			// System.out.println(TourManager.numberOfCities());
			// System.out.println(TourManager.getCity(i).x + " " + TourManager.getCity(i).y);
			// i++;
        }
    }

    public static List<String> getList() {
        return originalList;
    }
}