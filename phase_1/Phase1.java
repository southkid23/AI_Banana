package phase_1;

import java.io.*;
import java.util.*;


public class Phase1 {

	private String fileName = "k05.csv";
	private int costLimit;
	private List<Node> item;
	private int pop_size = 100;
	private List<String> pop = new ArrayList<String>();
	private List<Integer> fitness = new ArrayList<Integer>();
	

	public static void main(String[] args) throws Exception{

		Phase1 ph = new Phase1();

		ph.readCSV();

		ph.generatePop();

		System.out.println("\nFirst Generation: ");
		System.out.println("Population: ");

		ph.printPopulation();

		ph.evalPop();

		System.out.println("Fitness: ");

		ph.printFitness();
	}

	public void readCSV() throws Exception{

		item = new ArrayList<Node>();

		String csvFile = new File("resources/"+fileName).getAbsolutePath();
        BufferedReader br = null;
        String line = "";

        br = new BufferedReader(new FileReader(csvFile));

        costLimit = Integer.parseInt(br.readLine());
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String hold = "";
            String[] data = line.split(",");

            String name = data[0];
            int cost = Integer.parseInt(data[1]);
            int value = Integer.parseInt(data[2]);

            item.add(new Node(name,cost,value));

        }
	}

	public void printItems() throws Exception{

		for(int i = 0; i < item.size(); i++){
			System.out.println("Name:"+item.get(i).name + " | Cost:"+item.get(i).cost + " | Value:"+item.get(i).value);
		}
	}

	public void printPopulation() throws Exception{

		for(int i = 0; i < pop.size(); i++){
			System.out.println(i + " - " + pop.get(i));
		}
	}

	public void printFitness() throws Exception{

		for(int i = 0; i < pop.size(); i++){
			System.out.println(i + " - " + fitness.get(i));
		}
	}


	private void generatePop() {

		for(int i = 0; i < pop_size; i++){
			pop.add(createOrg());
		}
	}

	private String createOrg() {

		StringBuilder org = new StringBuilder(item.size());

		for(int i = 0; i < item.size(); i++){

			double r = Math.random();
			if(r > 0.5) {
				org.append("0");
			}
			else{
				org.append("1");
			}

		}

		return org.toString();
	}

	private void evalOrg(String org) {

		int total_cost = 0;
		int total_value = 0;
		char c;

		for(int i = 0; i < item.size(); i++){

			c = org.charAt(i);

			if(c == '1'){
				total_cost = total_cost + item.get(i).cost;
				total_value = total_value + item.get(i).value;
			}
		}

		if(total_cost <= costLimit){
			fitness.add(total_value);
		}
		else{
			fitness.add(0);
		}
	}

	private void evalPop() {

		for(int i = 0; i < pop.size(); i++){
			evalOrg(pop.get(i));
		}
	}
}