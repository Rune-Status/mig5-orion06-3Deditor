package Editor;

import java.util.ArrayList;

import client.FileOperations;
import client.Stream;

public class ObjectSpawn {

	public static ArrayList<ObjectSpawn> listOfObjects = new ArrayList<ObjectSpawn>();
	
	private int id;
	private int face;
	private int type;
	private Position position;
	
	public ObjectSpawn(int id, int face, int type, Position position){
		this.id = id;
		this.face = face;
		this.type = type;
		this.position = position;
		listOfObjects.add(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
}
