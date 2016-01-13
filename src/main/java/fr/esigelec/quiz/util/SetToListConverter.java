package fr.esigelec.quiz.util;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SetToListConverter<T> {
	
	public static <T extends Object & Comparable<? super T>> List<T> SetToList(List<T > myList, Set<T> setToConvert){
		
		myList.addAll(setToConvert);
		Collections.sort(myList);
		return myList;
	}

}
