
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Node {
	public final double lonRadians;
	public final double latRadians;
	private List<Node> neighbors;

	// Map<Node, Integer> neighborsMap;

	private static double toRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

	public Node(double lon, double lat) { // (node-cstor)
		lonRadians = toRadians(lon);
		latRadians = toRadians(lat);
		neighbors = new SingleLinkedList<Node>();
		// neighborsMap = new HashMap<Node, Integer>();
	}

	public void addNeighbor(Node other) {
		neighbors.add(other);
	}

	public Node[] getNeighbors() {
		return neighbors.toArray(new Node[neighbors.size()]);
	}
}

class Vertex {
	public double x, y;

	public Vertex(double x, double y) {
		this.x = x;
		this.y = y;
	}

}

class VertexLM extends Vertex {
	public float x, y;
	public ListMap<VertexLM, Integer> neighbours;

	public VertexLM(double x, double y) {
		super(x, y);
		neighbours = new ListMap<VertexLM, Integer>();

	}

	public void addNeighbour(VertexLM n, Integer length) {
		neighbours.put(n, length);
	}

}

class VertexAL extends Vertex {
	public List<VertexAL> neighbours;

	public VertexAL(double x, double y) {
		super(x, y);
		neighbours = new ArrayList<VertexAL>();
		// TODO Auto-generated constructor stub
	}

	public void addNeighbour(VertexAL n) {
		neighbours.add(n);
	}
}

class VertexSLL extends Vertex {
	public List<VertexSLL> neighbours;

	public VertexSLL(double d, double e) {
		super(d, e);
		neighbours = new SingleLinkedList<VertexSLL>();
		// TODO Auto-generated constructor stub
	}

	public void addNeighbourSSL(VertexSLL a) {
		neighbours.add(a);
	}
}

class VertexHM extends Vertex {

	public Map<VertexHM, Integer> neighbours;

	public VertexHM(double x, double y) {
		super(x, y);
		neighbours = new HashMap<VertexHM, Integer>();
	}

	public void addNeighbour(VertexHM n, Integer length) {
		neighbours.put(n, length);
	}
}

class Projector2D {
	public final double[] meanFactorLon;
	public final double[] meanFactorLat;

	public Projector2D(Node[] nodes) {
		meanFactorLon = new double[2];
		meanFactorLat = new double[2];
		double minLon = Double.POSITIVE_INFINITY, maxLon = Double.NEGATIVE_INFINITY;
		double minLat = Double.POSITIVE_INFINITY, maxLat = Double.NEGATIVE_INFINITY;
		for (int i = 0; i != nodes.length; ++i) {
			minLon = Math.min(minLon, nodes[i].lonRadians);
			maxLon = Math.max(maxLon, nodes[i].lonRadians);
			minLat = Math.min(minLat, nodes[i].latRadians);
			maxLat = Math.max(maxLat, nodes[i].latRadians);
		}
		meanFactorLon[0] = (minLon + maxLon) / 2.;
		meanFactorLon[1] = Math.sin(maxLon - meanFactorLon[0]);
		meanFactorLat[0] = (minLat + maxLat) / 2.;
		meanFactorLat[1] = Math.sin(maxLat - meanFactorLat[0]);
	}

	private static double scale(double r, double[] meanFactor) {
		return Math.sin(r - meanFactor[0]) / meanFactor[1];
	}

	public double[] project(Node n) {
		return new double[] { scale(n.lonRadians, meanFactorLon), scale(n.latRadians, meanFactorLat) };
	}
}

class Plotter {
	private final Projector2D proj;
	static {
		StdDraw.enableDoubleBuffering();
	}

	Plotter(Node[] nodes) {
		proj = new Projector2D(nodes);
	}

	public void plot(Node n) {
		bufferedPlot(n);
		StdDraw.show();
	}

	public void plot(Node[] n) {
		for (int i = 0; i != n.length; ++i) {
			bufferedPlot(n[i]);
		}
		StdDraw.show();
	}

	private void bufferedPlot(Node n) {
		double[] coords = proj.project(n);
		Node[] neighbors = n.getNeighbors();
		for (int i = 0; i != neighbors.length; ++i) {
			double[] tmp = proj.project(neighbors[i]);
			StdDraw.line(coords[0], coords[1], tmp[0], tmp[1]);
		}
	}
}

public class Main {
	// TODO !
	public static void main(String[] args) {
		try {
			// final String verticesFilename = args[0];
			// final String edgesFilename = args[1];
			long startTime = System.currentTimeMillis();

			VertexSLL[] nodes = readGraph("C:\\Users\\filip\\Desktop\\paris vertices.txt",
					"C:\\Users\\filip\\Desktop\\paris edges data file.txt");
			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println(elapsedTime + " miliseconds - SingleLinkedList - neighbors without edge information");
			System.err.println("returned " + nodes.length + " elts.");
			// Plotter p = new Plotter(nodes);
			// p.plot(nodes);

			long startTime2 = System.currentTimeMillis();

			VertexHM[] nodes2 = readGraphMap("C:\\Users\\filip\\Desktop\\paris vertices.txt",
					"C:\\Users\\filip\\Desktop\\paris edges data file.txt");
			long elapsedTime2 = System.currentTimeMillis() - startTime2;
			System.out.println(elapsedTime2 + " miliseconds - Map - neighbors with edge information");
			System.err.println("returned " + nodes2.length + " elts.");
			// Plotter p2 = new Plotter(nodes2);
			// p2.plot(nodes2);

			long startTime3 = System.currentTimeMillis();

			VertexAL[] nodes3 = readGraphALNoInfo("C:\\Users\\filip\\Desktop\\paris vertices.txt",
					"C:\\Users\\filip\\Desktop\\paris edges data file.txt");
			long elapsedTime3 = System.currentTimeMillis() - startTime3;
			System.out.println(elapsedTime3 + " miliseconds - ArrayList - neighbors witout edge information");
			System.err.println("returned " + nodes3.length + " elts.");

			long startTime4 = System.currentTimeMillis();

			VertexLM[] nodes4 = readGraphLM("C:\\Users\\filip\\Desktop\\paris vertices.txt",
					"C:\\Users\\filip\\Desktop\\paris edges data file.txt");
			long elapsedTime4 = System.currentTimeMillis() - startTime4;
			System.out.println(elapsedTime4 + " miliseconds - ListMap - neighbors with edge information");
			System.err.println("returned " + nodes4.length + " elts.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Methods

	private static VertexSLL[] readGraph(String verticesFilename, String edgesFilename) {
		Scanner s, e;
		ArrayList<VertexSLL> nodes = new ArrayList<VertexSLL>();
		try {
			s = new Scanner(new File(verticesFilename));

			while (s.hasNext()) {
				String line = s.nextLine();
				String[] data = line.split("[\\s]");
				nodes.add(new VertexSLL(Double.parseDouble(data[0]), Double.parseDouble(data[1])));

			}
			s.close();

			e = new Scanner(new File(edgesFilename));

			while (e.hasNext()) {
				String line = e.nextLine();
				String[] data = line.split("[\\s]");
				nodes.get(Integer.parseInt(data[0])).addNeighbourSSL(nodes.get(Integer.parseInt(data[1])));
				if (Integer.parseInt(data[2]) == 2) {
					nodes.get(Integer.parseInt(data[1])).addNeighbourSSL(nodes.get(Integer.parseInt(data[0])));
				}
			}
			e.close();

		} catch (Exception r) {
			// TODO Auto-generated catch block
			r.printStackTrace();
		}

		return nodes.toArray(new VertexSLL[nodes.size()]);
	}

//THIS IS HASH MAP WITH EDGE INFORMATION \/ \/ \/

//	private static VertexHM[] readGraphMap(String verticesFilename, String edgesFilename) {
//		Scanner s, e;
//		ArrayList<VertexHM> nodes = new ArrayList<VertexHM>();
//		try {
//			s = new Scanner(new File(verticesFilename));
//
//			while (s.hasNext()) {
//				String line = s.nextLine();
//				String[] data = line.split("[\\s]");
//				nodes.add(new VertexHM(Double.parseDouble(data[0]), Double.parseDouble(data[1])));
//
//			}
//			s.close();
//
//			e = new Scanner(new File(edgesFilename));
//
//			while (e.hasNext()) {
//				String line = e.nextLine();
//				String[] data = line.split("[\\s]");
//				nodes.get(Integer.parseInt(data[0])).addNeighbour(nodes.get(Integer.parseInt(data[1])),
//						new Integer(Integer.parseInt(data[4])));
//				if (Integer.parseInt(data[2]) == 2) {
//					nodes.get(Integer.parseInt(data[1])).addNeighbour(nodes.get(Integer.parseInt(data[0])),
//							new Integer(Integer.parseInt(data[4])));
//				}
//			}
//			e.close();
//
//		} catch (Exception r) {
//			// TODO Auto-generated catch block
//			r.printStackTrace();
//		}
//
//		return nodes.toArray(new VertexHM[nodes.size()]);
//}

	// THIS IS ARRAYLIST WITHOUT EDGE INFORMATION \/ \/ \/

	private static VertexAL[] readGraphALNoInfo(String verticesFilename, String edgesFilename) {
		Scanner s, e;
		ArrayList<VertexAL> nodes = new ArrayList<VertexAL>();

		try {
			s = new Scanner(new File(verticesFilename));

			while (s.hasNext()) {
				String line = s.nextLine();
				String[] data = line.split("[\\s]");
				nodes.add(new VertexAL(Double.parseDouble(data[0]), Double.parseDouble(data[1])));

			}
			s.close();

			e = new Scanner(new File(edgesFilename));

			while (e.hasNext()) {
				String line = e.nextLine();
				String[] data = line.split("[\\s]");
				nodes.get(Integer.parseInt(data[0])).addNeighbour(nodes.get(Integer.parseInt(data[1])));
				if (Integer.parseInt(data[2]) == 2) {
					nodes.get(Integer.parseInt(data[1])).addNeighbour(nodes.get(Integer.parseInt(data[0])));
				}
			}
			e.close();

		} catch (Exception r) {
			// TODO Auto-generated catch block
			r.printStackTrace();
		}

		return nodes.toArray(new VertexAL[nodes.size()]);
	}

	// THIS IS LISTMAP

	private static VertexLM[] readGraphLM(String verticesFilename, String edgesFilename) {
		Scanner s, e;
		ArrayList<VertexLM> nodes = new ArrayList<VertexLM>();
		try {
			s = new Scanner(new File(verticesFilename));

			while (s.hasNext()) {
				String line = s.nextLine();
				String[] data = line.split("[\\s]");
				nodes.add(new VertexLM(Double.parseDouble(data[0]), Double.parseDouble(data[1])));

			}
			s.close();

			e = new Scanner(new File(edgesFilename));

			while (e.hasNext()) {
				String line = e.nextLine();
				String[] data = line.split("[\\s]");
				nodes.get(Integer.parseInt(data[0])).addNeighbour(nodes.get(Integer.parseInt(data[1])),
						new Integer(Integer.parseInt(data[4])));
				if (Integer.parseInt(data[2]) == 2) {
					nodes.get(Integer.parseInt(data[1])).addNeighbour(nodes.get(Integer.parseInt(data[0])),
							new Integer(Integer.parseInt(data[4])));
				}
			}
			e.close();
		} catch (Exception r) {
			// TODO Auto-generated catch block
			r.printStackTrace();
		}

		return nodes.toArray(new VertexLM[nodes.size()]);
	}

	private static Node[] readGraphSet(String verticesFilename, String edgesFilename) {
		Scanner s, e;
		ArrayList<Node> nodes = new ArrayList<Node>();
		Set<Node> setNodes = new LinkedHashSet<Node>();
		try {
			s = new Scanner(new File(verticesFilename));

			while (s.hasNext()) {
				String line = s.nextLine();
				String[] data = line.split("[\\s]");
				setNodes.add(new Node(Double.parseDouble(data[0]), Double.parseDouble(data[1])));

			}
			s.close();

			e = new Scanner(new File(edgesFilename));

			while (e.hasNext()) {
				String line = e.nextLine();
				String[] data = line.split("[\\s]");
				setNodes.get(Integer.parseInt(data[0])).addNeighbor(setNodes.get(Integer.parseInt(data[1])));
				if (Integer.parseInt(data[2]) == 2) {
					setNodes.get(Integer.parseInt(data[1])).addNeighbor(setNodes.get(Integer.parseInt(data[0])));
				}
			}
			e.close();

		} catch (Exception r) {
			// TODO Auto-generated catch block
			r.printStackTrace();
		}

		return nodes.toArray(new Node[nodes.size()]);
	}

//	 public static <GeoPoint> GeoPoint[] read(String vUrl, String eUrl) throws
//	 IOException {
//	 List<GeoPoint> res = new SingleLinkedList<GeoPoint>();
//	 try (BufferedReader vBr = new BufferedReader(new InputStreamReader(new
//	 URL(vUrl).openStream()));
//	 BufferedReader eBr = new BufferedReader(new InputStreamReader(new
//	 URL(eUrl).openStream()))) {
//	 for (String line = vBr.readLine(); line != null; line = vBr.readLine()) {
//	 String[] coords = line.split(" ");// latitude, longitude
//	 res.add(new Node(Double.parseDouble(coords[0]),
//	 Double.parseDouble(coords[1])));
//	 }
//	 for (String line = eBr.readLine(); line != null; line = eBr.readLine()) {
//	 String[] data = line.split(" ");// start end nbDirs Duration Length
//	 GeoPoint start = res.get(Integer.parseInt(data[0]));
//	 GeoPoint end = res.get(Integer.parseInt(data[1]));
//	 Integer length = Integer.parseInt(data[4]);
//	 start.neighborToDistance.put(end, length);
//	 if (data[2].equals("2")) {
//	 end.neighborToDistance.put(start, length);
//	 }
//	 }
//	 } catch (Exception e) {
//	 System.err.println(e);
//	 }
//	 return res.toArray(new GeoPoint[res.size()]);
//	 }
}
