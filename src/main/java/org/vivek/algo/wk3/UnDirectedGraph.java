package org.vivek.algo.wk3;

import com.google.common.collect.ArrayListMultimap;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Logger;

public class UnDirectedGraph {

	private static Logger logger = Logger.getLogger(UnDirectedGraph.class.getName());
	private ArrayListMultimap vertices = ArrayListMultimap.create();
	private Random random = new Random();

	public UnDirectedGraph(String dataFileName) throws IOException {
		readDataFileToMap(dataFileName);
	}

	public static void main(String args[]) throws IOException {
		System.out.println(new Date().toString());
		Set<Integer> resultSet = new TreeSet<>();
		for (int i = 0; i < 10000; i++) {
			resultSet.add(new UnDirectedGraph("kargerMinCut.txt").getMinCuts());
		}
		//System.out.println(resultSet);
		System.out.println("Answer: " + (int) resultSet.toArray()[0]);
		System.out.println(new Date().toString());
	}

	private void readDataFileToMap(String dataFileName) throws IOException {
		vertices.clear();
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(UnDirectedGraph.class.getClassLoader().getResourceAsStream(dataFileName)));
		while (fileReader.ready()) {
			Scanner scanner = new Scanner(fileReader.readLine());

			String key = StringUtils.EMPTY;

			if (scanner.hasNext())
				key = scanner.next();

			if (StringUtils.isNotBlank(key))
				while (scanner.hasNext()) {
					vertices.put(key, scanner.next());
				}
		}
		fileReader.close();
	}

	private void contractEdges(final String vertexA, final String vertexB) {

		//logger.info("A = " + vertexA + ", B = " + vertexB);

		String newKey = vertexA;
		List<String> newValueSet = new ArrayList<>();
		List<String> newKeySet = new ArrayList<>();

		Iterator<Map.Entry> mapEntryIterator = vertices.entries().iterator();

		while (mapEntryIterator.hasNext()) {
			Map.Entry entry = mapEntryIterator.next();
			if (vertexB.equals(entry.getKey())) {
				String value = (String) entry.getValue();
				mapEntryIterator.remove();
				newValueSet.add(value);
			} else {
				String value = (String) entry.getValue();
				if (value.equals(vertexB)) {
					mapEntryIterator.remove();
					newKeySet.add((String) entry.getKey());
				}
			}
		}

		// Copy edges form B to A
		for (String value : newValueSet) {
			vertices.put(newKey, value);
		}

		// Link other edges to A
		for (String value : newKeySet) {
			vertices.put(value, newKey);
		}

		mapEntryIterator = vertices.entries().iterator();

		// Remove Self Loops
		while (mapEntryIterator.hasNext()) {
			Map.Entry entry = mapEntryIterator.next();
			if(entry.getKey().equals(entry.getValue()))
				mapEntryIterator.remove();
		}
	}

	public int getMinCuts() {
		while (vertices.keySet().size() > 2) {

			//logger.info(vertices.toString());

			int randomNum = random.nextInt(vertices.keySet().size());
			Object[] keyArray = vertices.keySet().toArray();
			String vertexA = (String) keyArray[randomNum];
			List values = vertices.get(vertexA);

			randomNum = random.nextInt(values.size());
			String vertexB = (String) values.toArray()[randomNum];

			contractEdges(vertexA, vertexB);


		}
		//logger.info(vertices.toString());

		return vertices.entries().size() / 2;
	}
}
