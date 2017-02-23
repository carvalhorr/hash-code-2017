package balloons.hash.competition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import balloons.hash.PizzaInfo;

public class FileLoader {

	public static void loadInfo(String fileName) throws FileNotFoundException {
		
		Scanner scan = new Scanner(new File(fileName));
		
		// Reads the parameters
		int v = scan.nextInt();
		int e = scan.nextInt();
		int r = scan.nextInt();
		int c = scan.nextInt();
		int x = scan.nextInt();
		
		// Read videos sizes
		int[] videos = new int[v];
		for (int i = 0; i < v; i ++) {
			videos[i] = scan.nextInt();
		}
		
		// Reads endpoints information
		Map<Integer, Integer> endpointsLatenciesToDatacenter = new HashMap<Integer, Integer>();
		Map<Integer, Integer[]> endpointsLatenciesToCaches = new HashMap<Integer, Integer[]>();
		for (int i = 0; i < e; i++) {
			// Endpoint latency to the datacenter
			int endpointLatencyToDatacenter = scan.nextInt();
			endpointsLatenciesToDatacenter.put(i, endpointLatencyToDatacenter);

			int numberOfCachesConnected = scan.nextInt();
			Integer[] lantenciesToCaches = new Integer[c];
			for (int j = 0; j < numberOfCachesConnected; j++) {
				int endpoint = scan.nextInt();
				int latency = scan.nextInt();
				lantenciesToCaches[endpoint] = latency;
			}
			endpointsLatenciesToCaches.put(i, lantenciesToCaches);
		}
		
		// Reads requests per video
		Map<Integer, Integer[]> requestsPerEndpoint = createInitializedRequestsPerEndpoint(v, e);
		for (int i = 0; i < r; i++) {
			int video = scan.nextInt();
			int endpoint = scan.nextInt();
			int numberOfRequests = scan.nextInt();
			requestsPerEndpoint.get(video)[endpoint] = numberOfRequests;
		}
	}
	
	
	private static Map<Integer, Integer[]> createInitializedRequestsPerEndpoint(int numberOfVideos, int numberOfEndpoints) {
		Map<Integer, Integer[]> requestsPerEndpoints =  new HashMap<Integer, Integer[]>();
		for (int i = 0; i < numberOfVideos; i ++) {
			Integer[] requestsFromEndpoints = new Integer[numberOfEndpoints];
			requestsPerEndpoints.put(i, requestsFromEndpoints);
		}
		return requestsPerEndpoints;
	}
}
