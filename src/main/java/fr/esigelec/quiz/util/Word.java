package fr.esigelec.quiz.util;

public class Word implements Comparable<Word>{
	private int id;

	@Override
	public int compareTo(Word o) {
		if(this.getId() < o.getId())
			return -1;
		else if (this.getId() < o.getId())
			return 0;
		else 
			return 1;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Word [id=" + id + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
}
