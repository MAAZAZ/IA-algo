package algorithm.annealing;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Dessin plotRoute = new Dessin();
        JFrame frame = new JFrame();
        frame.add(plotRoute);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // la fenêtre est centrée à l'écran
        frame.setVisible(true);
        
        for(int i = 0; i < 20; i++) {
            ListTour.addCity(new City((int) (Math.random() * 1200),  (int) (Math.random() * 800)));
        }
        
        /////////////// Alogrithme de recuit simulé - simulated annealing

        // La température initiale
        double temp = 9999;

        // Le taux de refroidissement
        double coolingRate = 0.999;

        // La solution initiale
        Tour SolutionInitial = new Tour();
        SolutionInitial.generateIndividual();

        System.out.println("La solution initiale : " + SolutionInitial.getDistance());

        Tour bestSolution = new Tour(SolutionInitial.getTour());

        //  jusqu'à la refroidissement du système
        while (temp > 1) {
            // Nouveau tour
            Tour newSolution = new Tour(SolutionInitial.getTour());

            // Obtener des positions aléatoires dans la tournée
            int tour1 = (int) (newSolution.tourSize() * Math.random());
            int tour2 = (int) (newSolution.tourSize() * Math.random());

            // Obtener les villes aux positions sélectionnées dans la tournée
            City city1 = newSolution.getCity(tour1);
            City city2 = newSolution.getCity(tour2);

            // Échanger
            newSolution.setCity(tour2, city1);
            newSolution.setCity(tour1, city2);

            // Obtener l'énergie des solutions (fitness)
            int currentEnergy = SolutionInitial.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            // Décider si nous devons accepter le voisin ou non
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
            	SolutionInitial = new Tour(newSolution.getTour());
            }

            // Garder la meilleure solution trouvée
            int diff= SolutionInitial.getDistance() - bestSolution.getDistance();
            if (diff<0) {
            	bestSolution = new Tour(SolutionInitial.getTour());
                Thread.sleep(100); // pour voir mieux la graphe
                plotRoute.drawTour(bestSolution);
            }

            temp *= coolingRate; // 9989.001
            //System.out.println(temp);
        }

        System.out.println("La solution optimale : " + bestSolution.getDistance());
        //System.out.println("Le tour: " + bestSolution);
    }
    
    // Calculer la probabilité d'acceptation
    public static double acceptanceProbability(int FirstEnergy, int SecondEnergy, double temperature) {
        // Si la nouvelle solution est meilleure, alors accepter la nouvelle solution
    	int D= FirstEnergy - SecondEnergy ;
        if (D < 0) {
            return 1.0;
        }
        // Si la nouvelle solution est pire, calculer la probabilité d'acceptation
        return Math.exp( D / temperature);
    }
    
    ////////////////////////////////////////////

}
