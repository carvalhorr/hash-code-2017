package balloons.hash.competition;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class DistributeVideos {

	public static void main(String[] args) throws FileNotFoundException {
		distributeVideos("data-competition/me_at_the_zoo.in");
	}

	public static void distributeVideos(String fileName) throws FileNotFoundException {
		VideoInfo videoInfo = FileLoader.loadInfo(fileName);
		for (int cache = 0; cache < videoInfo.c; cache++) {
			Map<Integer, Integer> videosValues = computeValuesForVideos(cache, videoInfo);
			Knapsack.knapsack(videoInfo.x, videosValues, videoInfo.videoSizes);
		}
	}

	public static Map<Integer, Integer> computeValuesForVideos(int cache, VideoInfo videoInfo) {
		Map<Integer, Integer> videosValues = new HashMap<Integer, Integer>();
		for (int i = 0; i < videoInfo.v; i++) {
			Integer value = 0;
			for (int j = 0; j < videoInfo.e; j++) {
				Integer requests = videoInfo.requestsPerEndpoint.get(i)[j];
				if (requests != null) {
					Integer latDatacenter = videoInfo.endpointsLatenciesToDatacenter.get(j);
					if (latDatacenter != null) {
						Integer latCache = videoInfo.endpointsLatenciesToCaches.get(j)[cache];
						if (latCache != null) {
							value = value + (requests * (latDatacenter - latCache));
						}
					}
				}
			}
			if (value != 0) {
				videosValues.put(i, value);
			}
		}
		return videosValues;
	}
}
