package algorithm.annealing;

import java.util.ArrayList;

public class ListTour {

    // les villes de tour
    private static ArrayList destinationCities = new ArrayList<>();

    // ajouter une ville
    public static void addCity(City city) {
        destinationCities.add(city);
    }

    // recuperer une ville
    public static City getCity(int index) {
        return (City) destinationCities.get(index);
    }

    // le nombre de ville dans le tour
    public static int numberOfCities() {
        return destinationCities.size();
    }

}
