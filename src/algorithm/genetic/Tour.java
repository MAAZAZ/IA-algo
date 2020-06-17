package algorithm.genetic;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {

    private ArrayList<City> tour = new ArrayList<>();
    private double fitness = 0;
    private int distance = 0;
    
    public Tour(){
        for (int i = 0; i < ListTour.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList<City> tour){
        this.tour = tour;
    }

    public void generateIndividual() {
        for (int i = 0; i < ListTour.numberOfCities(); i++) {
          setCity(i, ListTour.getCity(i));
        }
        Collections.shuffle(tour);
    }

    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        fitness = 0;
        distance = 0;
    }
    
   // fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    public int getDistance(){
    	City fromCity,destinationCity;
        if (distance == 0) {
            int tourDistance = 0;
            for (int i=0; i < tourSize(); i++) {
                fromCity = getCity(i);
                if(i+1 < tourSize()){
                    destinationCity = getCity(i+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }


    public int tourSize() {
        return tour.size();
    }
    
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    @Override
    public String toString() {
        String chaine = " ";
        for (int i = 0; i < tourSize(); i++) {
            chaine += getCity(i)+" -";
        }
        return chaine;
    }
}
