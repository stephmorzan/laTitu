package com.morzan.lexicon;

public interface ILexicon {

	public void loadLexiconData();
	public String searchInLexicon(String lemma);
	public void loadExtraLexiconData();
}
