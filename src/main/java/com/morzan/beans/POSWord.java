package com.morzan.beans;

public class POSWord {

	public String word;
	public String posTag;
	public String lemma;
	
	public POSWord(String word, String posTag) {
		this.word=word;
		this.posTag=posTag;
	}
	
	public POSWord() {
		// TODO Auto-generated constructor stub
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPosTag() {
		return posTag;
	}

	public void setPosTag(String posTag) {
		this.posTag = posTag;
	}

	public String getLemma() {
		return lemma;
	}
	
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
}
