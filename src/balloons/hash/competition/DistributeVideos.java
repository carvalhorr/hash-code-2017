package balloons.hash.competition;

import java.io.FileNotFoundException;

public class DistributeVideos {

	public static void main(String[] args) throws FileNotFoundException {
		FileLoader.loadInfo("data-competition/me_at_the_zoo.in");
	}
	
	public static void distributeVideos(String fileName) throws FileNotFoundException {
		VideoInfo videoInfo = FileLoader.loadInfo(fileName);
		for (int cache = 0; cache < videoInfo.c; cache++) {
			 
		}
	}
	
}
