package algorithm.annealing;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {

    // Les villes
    private ArrayList<City> tour = new ArrayList<>();
    private int distance = 0;

    // cr�er une tour
    public Tour() {
        for (int i = 0; i < ListTour.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    // creer un tour a partir d'un autre
    public Tour(ArrayList<City> tour) {
        this.tour = tour;
    	/*for(Object t:tour) {
    		System.out.println(t.toString());
    	}*/
    }

    // retourne un tour
    public ArrayList<City> getTour() {
        return tour;
    }
    
    // Obtenir une ville de la tourn�e
    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    //poser une ville dans une postition dans le tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // si le tour est modifier, alors r�initialiser la fonction de fitness - la distance
        distance = 0;
    }

    // Cr�er un individu al�atoire
    public void generateIndividual() {
        // Parcourez toutes nos villes et les ajouter � notre visite
        for (int i = 0; i < ListTour.numberOfCities(); i++) {
            setCity(i, ListTour.getCity(i));
        }
        // R�organiser au hasard la tourn�e
        Collections.shuffle(tour);
    }


    // Obtenir la distance totale de tour
    public int getDistance() {
    	City fromCity, destinationCity;
        if (distance == 0) {
            int tourDistance = 0;
            for (int i = 0; i < tour.size(); i++) {
                fromCity = getCity(i);
                if (i + 1 < tour.size()) {
                    destinationCity = getCity(i + 1);
                } else {
                    destinationCity = getCity(0);
                }
                tourDistance += fromCity.distanceBetween(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    @Override
    public String toString() {
        String chaine = "";
        for (int i = 0; i < tour.size(); i++) {
            chaine += getCity(i) + "--";
        }
        return chaine;
    }
    

    public int tourSize() {
    	return tour.size();
    }
}
