package balloons.hash.competition;

import java.util.Map;

public class VideoInfo {
	public int v = 0;
	public int e = 0;
	public int r = 0;
	public int c = 0;
	public int x = 0;
	public int[] videoSizes;
	public Map<Integer, Integer> endpointsLatenciesToDatacenter = null;
	public Map<Integer, Integer[]> endpointsLatenciesToCaches = null;
	public Map<Integer, Integer[]> requestsPerEndpoint = null;

	public VideoInfo(int v, int e, int r, int c, int x, int[] videoSizes,
			Map<Integer, Integer> endpointsLatenciesToDatacenter, Map<Integer, Integer[]> endpointsLatenciesToCaches,
			Map<Integer, Integer[]> requestsPerEndpoint) {
		this.v = v;
		this.e = e;
		this.r = r;
		this.c = c;
		this.x = x;
		this.videoSizes = videoSizes;
		this.endpointsLatenciesToDatacenter = endpointsLatenciesToDatacenter;
		this.endpointsLatenciesToCaches = endpointsLatenciesToCaches;
		this.requestsPerEndpoint = requestsPerEndpoint;
	}

}
