package phase_2;

public class GA {

    private static final double mutationRate = 0.0005;
    private static final int tournamentSize = 5;
    // private static final boolean elitism = true;

    private static int iterations = 0;

    // Evolves a population
    public static Population evolvePopulation(Population pop) {

        iterations++;

        // Perform mutation
        for (int i = 1; i < pop.populationSize(); i++) {
            mutate(pop.getRoute(i));
        }
        
        // Selecting the best parents
        Route parent1 = tournamentSelection(pop);
        Route parent2 = tournamentSelection(pop);

        // Crossover parents to produce child
        Route child = crossover(parent1, parent2);

        // Replaces the lowest fitness with the child
        pop.saveRoute(pop.getLowest(), child);

        pop.saveRoute(0, pop.getFittest());


        // Random Organism
        //randomOrganism(newPopulation.getRoute((int) (Math.random() * newPopulation.populationSize())));

        return pop;
    }

    public static Route crossover(Route parent1, Route parent2) {
        Route child = new Route();

        // Randomly select the start and end position
        int startPos = (int) (Math.random() * parent1.routeSize());
        int endPos = (int) (Math.random() * parent1.routeSize());

        // Adding the subsub of the parent1 to the child
        for (int i = 0; i < child.routeSize(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            }
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Adding the subsub of the parent2 to the child
        for (int i = 0; i < parent2.routeSize(); i++) {
            // Add only if the child doesn't have cities
            if (!child.containsCity(parent2.getCity(i))) {
                for (int ii = 0; ii < child.routeSize(); ii++) {
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutation technique: Swapping cities in a tour
    private static void mutate(Route tour) {

        for(int pos1=0; pos1 < tour.routeSize(); pos1++){
            if(Math.random() < mutationRate){
                // Gets the second position of the second
                int pos2 = (int) (tour.routeSize() * Math.random());

                City city1 = tour.getCity(pos1);
                City city2 = tour.getCity(pos2);

                // Swap the two cities
                tour.setCity(pos2, city1);
                tour.setCity(pos1, city2);
            }
        }
    }

    // Mutation technique: Swapping cities in a tour
    // Arguments: Route and the mutation rates
    public static void cvgMutate(Route tour, double rates) {

        for(int pos1=0; pos1 < tour.routeSize(); pos1++){

            if(Math.random() < rates){
                // Gets the second position of the second
                int pos2 = (int) (tour.routeSize() * Math.random());

                City city1 = tour.getCity(pos1);
                City city2 = tour.getCity(pos2);

                // Swap the two cities
                tour.setCity(pos2, city1);
                tour.setCity(pos1, city2);
            }
        }
    }


    private static void randomOrganism(Route tour) {
        for(int pos1=0; pos1 < tour.routeSize(); pos1++){
            if(Math.random() < 0.5){
                int pos2 = (int) (tour.routeSize() * Math.random());

                City city1 = tour.getCity(pos1);
                City city2 = tour.getCity(pos2);

                // Swap the two cities
                tour.setCity(pos2, city1);
                tour.setCity(pos1, city2);
            }
        }
    }

    private static Route tournamentSelection(Population pop) {
        
        Population tournament = new Population(tournamentSize, false);
        
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveRoute(i, pop.getRoute(randomId));
        }
        
        // Gets the fittest
        Route fittest = tournament.getFittest();
        return fittest;
    }

    public static void getIterations() {
        System.out.println("Iterations: " + Integer.toString(iterations));
    }
}