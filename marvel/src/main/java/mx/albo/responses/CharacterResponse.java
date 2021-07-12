package mx.albo.responses;

import java.util.ArrayList;
import java.util.List;

public class CharacterResponse {
	
	
	public CharacterResponse() {
		this.characters = new ArrayList<CharacterItem>();
		this.last_sync = "-";
	}

	private List<CharacterItem> characters;
	private String last_sync;
	
	public String getLast_sync() {
		return last_sync;
	}

	public void setLast_sync(String last_sync) {
		this.last_sync = last_sync;
	}

	public List<CharacterItem> getCharacters() {
		return characters;
	}

	public void setCharacters(List<CharacterItem> characters) {
		this.characters = characters;
	}

	

}
