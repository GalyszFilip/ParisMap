

import java.util.HashMap;
import java.util.Map;

public class WeightedNode {
	public final double lonRadians;
	public final double latRadians;
	Map<WeightedNode, Integer> neighborsMap;

	private static double toRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

	public WeightedNode(double lon, double lat) { // (node-cstor)
		lonRadians = toRadians(lon);
		latRadians = toRadians(lat);
		neighborsMap = new HashMap<WeightedNode, Integer>();
	}

	public void addNeighbor(WeightedNode other, Integer i) {
		neighborsMap.put(other, i);
	}

	// public Node[] getNeighbors() {
	// return neighborsMap.get(new Node[neighbors.size()]);
	// }
}
