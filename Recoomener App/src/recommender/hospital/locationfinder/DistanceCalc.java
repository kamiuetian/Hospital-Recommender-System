package recommender.hospital.locationfinder;

import android.util.Log;

public class DistanceCalc {
	public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
	 public static double distFrom(Double lat1, Double lon1, Double lat2, Double lon2) {
	    	double p1 = Math.cos(lat1) * Math.cos(lon1)
					* Math.cos(lat2) * Math.cos(lon2);
			double p2 = Math.cos(lat1) * Math.sin(lon1)
					* Math.cos(lat2) * Math.sin(lon2);
			double p3 = Math.sin(lat1) * Math.sin(lat2);
			Log.d("distance", Double.toString((Math.acos(p1 + p2 + p3) * AVERAGE_RADIUS_OF_EARTH)));
			return (int) (Math.acos(p1 + p2 + p3) * AVERAGE_RADIUS_OF_EARTH);

	    }
}
