package model;

public class Dictionary {

	private String word;
	private String wordType;
	private String definition;

	Dictionary(String word, String wordType, String definition) {
		this.word = word;
		this.wordType = wordType;
		this.definition = definition;

	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWordType() {
		return wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

}
