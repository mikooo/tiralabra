package fi.miko.tiralabra;

import java.util.List;
import java.util.Random;

import fi.miko.tiralabra.algorithms.AStar;
import fi.miko.tiralabra.algorithms.BellmanFord;
import fi.miko.tiralabra.algorithms.Dijkstra;
import fi.miko.tiralabra.algorithms.Graph;
import fi.miko.tiralabra.algorithms.Heuristic;
import fi.miko.tiralabra.algorithms.Node;
import fi.miko.tiralabra.algorithms.PathFinder;

public class Main {
	private static void benchmark() {
		char[][] graph = generateRandom(100, 100, 0.20);
		Graph g = new Graph(graph);
		// System.out.println(g);

		PathFinder f = new BellmanFord(g);

		f.findPath();
		List<Node> path = f.getShortestPath();

		if (path.isEmpty() || !isValidPath(g, path)) {
			System.out.println("No path found!");
			return;
		}

		System.out.println("Reference path length: " + path.size() + " cost: " + getPathDistance(path));
		System.out.println();

		PathFinder d = new Dijkstra(new Graph(graph));
		PathFinder a1 = new AStar(new Graph(graph), Heuristic.None);
		PathFinder a2 = new AStar(new Graph(graph), Heuristic.Euclidean);

		measure(d, "Dijkstra", path);
		measure(a1, "A* None", path);
		measure(a2, "A* Euclidean", path);
	}

	private static char[][] generateRandom(int width, int height, double freq) {
		char[][] graph = new char[height][width];
		Random rand = new Random();

		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				graph[i][j] = (rand.nextDouble() <= freq ? '#' : '.');
			}
		}

		graph[0][0] = 's';
		graph[width - 1][height - 1] = 't';

		return graph;
	}

	private static double getPathDistance(List<Node> path) {
		return path.get(path.size() - 1).getDistance();
	}

	private static boolean isValidPath(Graph graph, List<Node> path) {
		Node prev = null;

		for (Node n : path) {
			if (prev != null) {
				if (graph.getNeighbours(prev).contains(n) == false) {
					return false;
				}
			}

			prev = n;
		}

		return true;
	}

	public static void main(String[] args) {
		benchmark();
	}

	private static void measure(PathFinder f, String name, List<Node> path) {
		long start = System.nanoTime();

		for (int i = 0; i < 1000; ++i) {
			f.findPath();

			List<Node> fp = f.getShortestPath();

			double d1 = getPathDistance(path);
			double d2 = getPathDistance(fp);

			boolean valid = path.size() == fp.size() && d1 == d2;

			if (!valid) {
				System.out.println("The path is invalid!");
				System.out.println("Actual: " + fp.size() + " : " + d2);
				break;
			}
		}

		long end = System.nanoTime();
		double elapsed = (end - start) / 1E9;

		System.out.println(name + ": " + elapsed + "s");
		System.out.println();
	}

}