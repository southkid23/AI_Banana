package phase_2;

import java.io.*;
import java.util.*;

public class ReadCSV {
	public static void read() throws Exception{

		String fileName = "Cities_01.csv";
        String csvFile = new File("./phase_2/resources/"+fileName).getAbsolutePath();
        BufferedReader br = null;
        String line = "";

        br = new BufferedReader(new FileReader(csvFile));
        // int i = 0;
        while ((line = br.readLine()) != null) {
            // // use comma as separator
            String[] data = line.split(",");

            City city = new City(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        	TourManager.addCity(city);

        	// Testing class functions behaviors
			// System.out.println(TourManager.numberOfCities());
			// System.out.println(TourManager.getCity(i).x + " " + TourManager.getCity(i).y);
			// i++;
        }
    }
}