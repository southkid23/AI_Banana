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
	private String best_solution;
	private int best_index;
	private float mean_fitness;
	private String offspring;
	
	public static void main(String[] args) throws Exception{

		Phase1 ph = new Phase1();

		ph.initialization();

		ph.printItems();

		ph.printPopulation();

	}

	private void initialization() throws Exception {
		System.out.println("Initializing...");
		// Read Items
		readCSV();
		// Generate Populations from given list of items
		generatePop();
		// Calculate fitness for each item
		evalPop();

		System.out.println("Best solution: " + best_solution);
		System.out.println("Best solution number: " + best_index);

		System.out.println("Mean fitness of generation: " + mean_fitness);

		System.out.println("Initialization complete.");

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
		System.out.println("\nItems in CSV");
		for(int i = 0; i < item.size(); i++){
			System.out.println("Name:"+item.get(i).name + " | Cost:"+item.get(i).cost + " | Value:"+item.get(i).value);
		}
	}

	public void printPopulation() throws Exception{

		System.out.println("\nFirst Generation: ");
		System.out.println("Population: ");

		for(int i = 0; i < pop.size(); i++){
			System.out.println(i+1 + " - " + pop.get(i) + "   [Fitness: " + fitness.get(i) + "]");

			chanceOfMutation(pop.get(i));
			
			String test = chanceOfMutation(pop.get(i));

			if (!test.equals(pop.get(i)))
				System.out.println(i+1 + " Mutated - " + test);
		}

		System.out.println("\nBest solution: " + best_solution);
		
		int best = best_index + 1;
		System.out.println("Best solution number: " + best);

		System.out.println("Mean fitness of generation: " + mean_fitness + "\n");
	}

	private void generatePop() {

		for(int i = 0; i < pop_size; i++){
			pop.add(createOrganism());
		}
	}

	private String createOrganism() {

		StringBuilder organism = new StringBuilder(item.size());

		for(int i = 0; i < item.size(); i++){

			double r = Math.random();
			if(r > 0.5) {
				organism.append("0");
			}
			else{
				organism.append("1");
			}

		}
		return organism.toString();
	}

	private void evalOrg(String organism) {

		int total_cost = 0;
		int total_value = 0;
		char c;

		for(int i = 0; i < item.size(); i++){

			c = organism.charAt(i);

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
		best_index = 0;
		int total_fitness = 0;
		// calculate the fitness for each organism
		for(int i = 0; i < pop.size(); i++){
			evalOrg(pop.get(i));
		
			// look for the best solution (best fitness) in the generation
			if(fitness.get(best_index) < fitness.get(i)){
				best_index = i;
				best_solution = pop.get(i);
			}

			total_fitness = total_fitness + fitness.get(i);
		}

		mean_fitness = (float)total_fitness / pop_size;	
	}

	private int generateMutationChances(){
		// Randomly generates a number in range (1, 200)
		int maximum = 200, minimum = 1;
		return ((int) (Math.random()*(maximum - minimum))) + minimum; 
	}

	private String chanceOfMutation(String organism){
		StringBuilder tempOrganism = new StringBuilder(organism);
		char c;
		int tempVar;

		for(int i = 0; i < item.size(); i++){

			c = organism.charAt(i);
			tempVar = generateMutationChances();

			if(tempVar == 1){
				if (c == 1) {
					tempOrganism.setCharAt(i, '0');
				} else
					tempOrganism.setCharAt(i, '1');
			}
		}

		organism = tempOrganism.toString();
		return organism;
	}

	private void makeNewGeneration() {
		// perform mutation on population: chanceOfMutation()
		for(int i = 0; i < pop.size(); i++){
			
			string mutatedOrg = chanceOfMutation(pop.get(i));
			if (!mutatedOrg.equals(pop.get(i))){
				pop.set(i, mutatedOrg);  	
			}
		}

		// Perform crossover on random parents: crossover()
		crossover();

		// Perform fitness calculation: evalPop()
		evalPop();
	}

	private void crossover() {

		
	}
}