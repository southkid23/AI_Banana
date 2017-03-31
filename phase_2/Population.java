package phase_2;

public class Population {

    Route[] tours;
    int lowestIndex;

    // Generate population
    public Population(int populationSize, boolean initialize) {
        tours = new Route[populationSize];

        // Only used for initialization
        if (initialize) {
             // Generate individuals
            for (int i = 0; i < populationSize(); i++) {
                Route newRoute = new Route();
                newRoute.generateIndividual();
                saveRoute(i, newRoute);
            }
        }
    }

    public Population(Population pop) {
        tours = new Route[pop.populationSize()];

        for(int i = 0; i < populationSize(); i++) {
            // Route newRoute =  new Route(pop.getRoute(i));
            Route newRoute = pop.getRoute(i);
            saveRoute(i, newRoute);
        }
    }
    
    // Saves a tour
    public void saveRoute(int index, Route tour) { tours[index] = tour; }
    
    // Gets a tour from population
    public Route getRoute(int index) { return tours[index]; }

    // Returns the size of the population
    public int populationSize() { return tours.length; }

    // Returns the index of the lowest fitness 
    public int getLowest() { return lowestIndex; }

    // Finds the fittest among all
    public Route getFittest() {
        Route fittest = tours[0];
        Route lowest = tours[0];

        // Finding the fittest among the list
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() > getRoute(i).getFitness()) {
                fittest = getRoute(i);
            }
            if (lowest.getFitness() < getRoute(i).getFitness()) {
                lowest = getRoute(i);
                lowestIndex = i;
            }
        }
        fittest = new Route(fittest);

        return fittest;
    }

    public boolean isIdentical(){

        Route fitness = tours[0];
        boolean flag = false;

        for (int i = 1; i < populationSize(); i++) {
            if (fitness.getFitness() != getRoute(i).getFitness()) { return flag; }
        }

        return !flag;
    }

    public void printPop() {

        for(int i = 0; i < populationSize(); i++) {

            System.out.print(i + " - ");
            tours[i].printRoute();
        }
    }
}