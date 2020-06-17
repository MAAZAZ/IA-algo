package algorithm.genetic;


public class Main {

    
    public static void main(String[] args) {
        
        double totalFitness = 0, cumulative = 0;       
        
        ListTour.addCity(new City("A"));
        ListTour.addCity(new City("B"));
        ListTour.addCity(new City("C"));
        ListTour.addCity(new City("D"));
        ListTour.addCity(new City("E"));
        ListTour.addCity(new City("F"));

        // Initialize population
        Population pop = new Population(6, true);
        System.out.println("La distance initale : " + pop.getFittest().getDistance());
        for (int i = 0; i < pop.populationSize(); i++) {
            System.out.println(pop.getTour(i) + "Total: " + pop.getTour(i).getDistance());
        }

        // Chromosome selection
        System.out.println("\nChromosome selection :");
        for (int i = 0; i < pop.populationSize(); i++) {
            totalFitness += pop.getTour(i).getFitness();
            System.out.println("Q[" + (i+1) + "] = " + pop.getTour(i).getFitness());
        }
        /*
        // Roulette wheel
        System.out.println("\nProbabilities :");
        for (int i = 0; i < pop.populationSize(); i++) {
            System.out.println("P[" + (i+1) + "] = " + (pop.getTour(i).getFitness()/totalFitness));
        }
        
        // Cumulatives
        System.out.println("\nCumulatives :");
        for (int i = 0; i < pop.populationSize(); i++) {
            cumulative += pop.getTour(i).getFitness()/totalFitness;
            System.out.println("C[" + (i+1) + "] = " + cumulative);
        }
        
        
        // Crossover
        System.out.println("\nCrossover candidates :");
        for (int i = 0; i < pop.populationSize(); i++) {
            System.out.println(GA.Selection(pop));
        }
        */
        
        // Number of generation
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 600; i++) {
            pop = GA.evolvePopulation(pop);
        }

        System.out.println("\n La distance finale : " + pop.getFittest().getDistance());
        System.out.println("\n Solution : " + pop.getFittest());
    } 
    
}
