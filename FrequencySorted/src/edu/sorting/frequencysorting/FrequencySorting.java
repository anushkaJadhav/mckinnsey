package edu.sorting.frequencysorting;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implies insertion sort to the element depending upon their
 * frequency.
 * 
 * @author anushka
 * 
 */

public class FrequencySorting {

	/* to maintain sorted on frequency elements */
	private static final ArrayList<Node> sortedArrayList = new ArrayList<Node>();

	/**
	 * Reads through the array . Maintains a hash map to record if the element has
	 * occured.and maintains a count in the node object which is stored as value
	 * in the hash map
	 * 
	 * @see addInSortedList
	 * @param input
	 * @return
	 */
	public ArrayList<Node> getfrequencySortedArrayList(ArrayList<Integer> input) {

		/* stores the elements as keys and thier count in the node object */
		HashMap<String, Node> map = new HashMap<String, Node>();

		for (int index = 0; index < input.size(); index++) {

			if (!map.containsKey(input.get(index).toString())) {

				Node node = new Node();
				String entry = input.get(index).toString();
				node.setElement(entry);
				node.incrementCountByOne();
				map.put(entry, node);
				addInSortedList(node);
			} else {
				Node mapNode = map.get(input.get(index).toString());
				int indexInSortedList = sortedArrayList.indexOf(mapNode);
				Node newNode = sortedArrayList.remove(indexInSortedList);
				newNode.incrementCountByOne();
				addInSortedList(newNode);
			}
		}
		return sortedArrayList;
	}

	/**
	 * get the node's count compares it to previous node's count to find
	 * appropriate pisition in the array list.
	 * 
	 * @param node
	 */
	private static void addInSortedList(Node node) {

		if (sortedArrayList.isEmpty()) {

			sortedArrayList.add(node);
		} else {

			int n = sortedArrayList.size();
			int index = (n - 1);

			int keyCount = node.getCount();
			int indexPrev = index;
			Node nodePrev = sortedArrayList.get(indexPrev);
			int prevCount = nodePrev.getCount();

			while (indexPrev > 0 && prevCount < keyCount) {
				indexPrev = indexPrev - 1;
				nodePrev = sortedArrayList.get(indexPrev);
				prevCount = nodePrev.getCount();
			}
			if (indexPrev == 0) { // when list is one.

				if (prevCount < keyCount) {

					sortedArrayList.add(indexPrev, node);
				} else {

					sortedArrayList.add(indexPrev + 1, node);
				}
			} else {

				sortedArrayList.add(indexPrev + 1, node);
			}
		}
	}

}
