package mx.albo.responses;

import java.util.HashSet;
import java.util.Set;

public class ColaboratorsResponse {
	public ColaboratorsResponse() {
		this.last_sync = "-";
		this.editors = new HashSet<String>();
		this.writes = new HashSet<String>();
		this.colorists = new HashSet<String>();
	}
	private String last_sync;
	private Set<String> editors;
	private Set<String> writes;
	private Set<String> colorists;
	
	
	public String getLast_sync() {
		return last_sync;
	}
	public void setLast_sync(String last_sync) {
		this.last_sync = last_sync;
	}
	public Set<String> getEditors() {
		return editors;
	}
	public void setEditors(Set<String> editors) {
		this.editors = editors;
	}
	public Set<String> getWrites() {
		return writes;
	}
	public void setWrites(Set<String> writes) {
		this.writes = writes;
	}
	public Set<String> getColorists() {
		return colorists;
	}
	public void setColorists(Set<String> colorists) {
		this.colorists = colorists;
	}

}
