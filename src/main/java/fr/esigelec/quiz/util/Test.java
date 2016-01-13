package fr.esigelec.quiz.util;

import java.util.ArrayList;
import java.util.HashSet;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Word w = new Word();
		w.setId(15);
		
		Word w1 = new Word();
		w1.setId(12);
		
		Word w2 = new Word();
		w2.setId(17);
		
		Word w3 = new Word();
		w3.setId(13);
		
		Word w4 = new Word();
		w4.setId(85);
		
		Word w5 = new Word();
		w5.setId(42);
		
		Word w6 = new Word();
		w6.setId(29);
		
		Word w7 = new Word();
		w7.setId(16);
		
		
		ArrayList<Word> array = new ArrayList<Word>();
		HashSet<Word> set = new HashSet<Word>();
		
		set.add(w);
		set.add(w1);
		set.add(w2);
		set.add(w3);
		set.add(w4);
		set.add(w5);
		set.add(w6);
		set.add(w7);
		
		
		System.out.println(set.toString());
		SetToListConverter.SetToList(array, set);
		System.out.println(array);
		
		
	}

}
