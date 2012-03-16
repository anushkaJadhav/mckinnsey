package edu.sorting.frequencysorting;
/**
 * This class represents a single node,with fields such as element name and count.
 * 
 * @author anushka
 *
 */
public class Node implements Comparable<Node>{

	private String element;
	private int count=0;
	

	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * increments by 1 and sets the incremented value
	 * @return
	 */
	public int incrementCountByOne() {
		count+=1;
		return count;
	}
	

	
	
	@Override
	public String toString() {
		return "Node [element=" + element + ", count=" + count + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		return true;
	}
	
	public int compareTo(Node node) {
		
		return element.compareTo(node.element);
	}
	
	
}
