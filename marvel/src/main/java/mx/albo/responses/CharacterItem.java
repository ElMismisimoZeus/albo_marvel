package mx.albo.responses;

import java.util.ArrayList;
import java.util.List;

public class CharacterItem {
	
	public CharacterItem(String name) {
		this.character = name;
		this.comics = new ArrayList<String>();
	}
	private String character;
	private List<String> comics;
	
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public List<String> getComics() {
		return comics;
	}
	public void setComics(List<String> comics) {
		this.comics = comics;
	}
}
