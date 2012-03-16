package edu.sorting.frequencysorting.test;


import java.util.ArrayList;
import java.util.Iterator;

import edu.sorting.frequencysorting.FrequencySorting;


public class RunFrequencySorting {
public static void main(String agrs[]){
	ArrayList list =new ArrayList();
	list.add(3);
	list.add(4);
	list.add(4);
	list.add(4);
	list.add(6);
	list.add(6);
	list.add(7);
	list.add(7);
	list.add(8);
	list.add(8);
	list.add(8);
	list.add(8);
	list.add(8);
	list.add(8);
	list.add(8);
	list.add(8);
	list.add(7);
	list.add(8);
	list.add(8);
	list.add(9);
	list.add(8);
	list.add(8);
	
	
	
	FrequencySorting fs=new FrequencySorting();
	ArrayList sorted=fs.getfrequencySortedArrayList(list);
	Iterator itr=sorted.iterator();
	while(itr.hasNext()){
		System.out.println(" | "+itr.next().toString());
		
	}
}
}
