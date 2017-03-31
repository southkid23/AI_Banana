package phase_2;

public class Population {

    // Holds population
    Tour[] tours;
    int lowestIndex;

    // Generate population
    public Population(int populationSize, boolean initialize) {
        tours = new Tour[populationSize];

        // Only used for initialization
        if (initialize) {
             // Generate individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }

    public Population(Population pop) {
        tours = new Tour[pop.populationSize()];

        for(int i = 0; i < populationSize(); i++) {
            // Tour newTour =  new Tour(pop.getTour(i));
            Tour newTour = pop.getTour(i);
            saveTour(i, newTour);
        }
    }
    
    // Saves a tour
    public void saveTour(int index, Tour tour) { tours[index] = tour; }
    
    // Gets a tour from population
    public Tour getTour(int index) { return tours[index]; }

    // Gets population size 
    public int populationSize() { return tours.length; }

    // Returns the index of the lowest fitness 
    public int getLowest() { return lowestIndex; }

    // Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = tours[0];
        Tour lowest = tours[0];

        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() > getTour(i).getFitness()) {
                fittest = getTour(i);
            }
            if (lowest.getFitness() < getTour(i).getFitness()) {
                lowest = getTour(i);
                lowestIndex = i;
            }
        }
        fittest = new Tour(fittest);
        System.out.println(lowestIndex);

        return fittest;
    }

    public boolean isIdentical(){

        Tour fitness = tours[0];
        boolean flag = false;

        for (int i = 1; i < populationSize(); i++) {
            if (fitness.getFitness() != getTour(i).getFitness()) { return flag; }
        }

        return !flag;
    }

    public void printPop() {

        for(int i = 0; i < populationSize(); i++) {

            System.out.print(i + " - ");
            tours[i].printTour();
        }
    }
}