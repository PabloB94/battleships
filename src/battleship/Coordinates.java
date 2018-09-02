package battleship;

public class Coordinates {
	
	private int lat;
	private int lon;
	
	public Coordinates(int lat, int lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public int getLat() {
		return this.lat;
	}
	
	public int getLon() {
		return this.lon;
	}
}
