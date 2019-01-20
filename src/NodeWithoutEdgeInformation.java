//import java.util.HashMap;
//
//public class NodeWithoutEdgeInformation {
//	public final double lonRadians;
//	public final double latRadians;
//	List<Vertex> neighborsMap;
//
//	private static double toRadians(double degrees) {
//		return degrees * Math.PI / 180;
//	}
//
//	public WeightedNode(double lon, double lat) { // (node-cstor)
//		lonRadians = toRadians(lon);
//		latRadians = toRadians(lat);
//		neighborsMap = new HashMap<WeightedNode, Integer>();
//	}
//
//	public void addNeighbor(WeightedNode other, Integer i) {
//		neighborsMap.put(other, i);
//	}

	// public Node[] getNeighbors() {
	// return neighborsMap.get(new Node[neighbors.size()]);
	// }
//}