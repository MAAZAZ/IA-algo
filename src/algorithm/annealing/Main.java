package algorithm.annealing;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Dessin plotRoute = new Dessin();
        JFrame frame = new JFrame();
        frame.add(plotRoute);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // la fen�tre est centr�e � l'�cran
        frame.setVisible(true);
        
        for(int i = 0; i < 20; i++) {
            ListTour.addCity(new City((int) (Math.random() * 1200),  (int) (Math.random() * 800)));
        }
        
        /////////////// Alogrithme de recuit simul� - simulated annealing

        // La temp�rature initiale
        double temp = 9999;

        // Le taux de refroidissement
        double coolingRate = 0.999;

        // La solution initiale
        Tour SolutionInitial = new Tour();
        SolutionInitial.generateIndividual();

        System.out.println("La solution initiale : " + SolutionInitial.getDistance());

        Tour bestSolution = new Tour(SolutionInitial.getTour());

        //  jusqu'� la refroidissement du syst�me
        while (temp > 1) {
            // Nouveau tour
            Tour newSolution = new Tour(SolutionInitial.getTour());

            // Obtener des positions al�atoires dans la tourn�e
            int tour1 = (int) (newSolution.tourSize() * Math.random());
            int tour2 = (int) (newSolution.tourSize() * Math.random());

            // Obtener les villes aux positions s�lectionn�es dans la tourn�e
            City city1 = newSolution.getCity(tour1);
            City city2 = newSolution.getCity(tour2);

            // �changer
            newSolution.setCity(tour2, city1);
            newSolution.setCity(tour1, city2);

            // Obtener l'�nergie des solutions (fitness)
            int currentEnergy = SolutionInitial.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            // D�cider si nous devons accepter le voisin ou non
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
            	SolutionInitial = new Tour(newSolution.getTour());
            }

            // Garder la meilleure solution trouv�e
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
    
    // Calculer la probabilit� d'acceptation
    public static double acceptanceProbability(int FirstEnergy, int SecondEnergy, double temperature) {
        // Si la nouvelle solution est meilleure, alors accepter la nouvelle solution
    	int D= FirstEnergy - SecondEnergy ;
        if (D < 0) {
            return 1.0;
        }
        // Si la nouvelle solution est pire, calculer la probabilit� d'acceptation
        return Math.exp( D / temperature);
    }
    
    ////////////////////////////////////////////

}
