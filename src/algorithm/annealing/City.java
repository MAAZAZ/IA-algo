package algorithm.annealing;

public class City {

    int x;
    int y;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	// la distance
    public double distanceBetween(City city) {
        return Math.sqrt(Math.pow(city.getX() - this.getX(), 2) + Math.pow(city.getY() - this.getY(), 2));
    }

    @Override
    public String toString() {
        return "("+getX() + "," + getY()+")";
    }
}
