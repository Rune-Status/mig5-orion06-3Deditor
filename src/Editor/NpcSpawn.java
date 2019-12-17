package Editor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import client.FileOperations;
import client.Stream;

public class NpcSpawn {
	
	//public NpcSpawn[] npcSpawns;
	public static ArrayList<NpcSpawn> listOfNpcs = new ArrayList<NpcSpawn>();
	
	
	public static void loadSpawns() {
        int spawns = 0;
		try {
			byte abyte2[] = FileOperations.ReadFile("./spawns/Npc spawn.dat");
			Stream stream2 = new Stream(abyte2);
			spawns = stream2.readUnsignedWord();
			for (int i2 = 0; i2 < spawns; i2++) {
				int id = stream2.readUnsignedWord();
				int type = stream2.readUnsignedByte();
				int face = stream2.readUnsignedByte();
				/*if(face == 3)
					face = 0;*/
				int x = stream2.readUnsignedWord();
				int y = stream2.readUnsignedWord();
				int z = stream2.readUnsignedByte();
				/*if(face > 5){
					System.out.println("check "+face+" "+x+" "+y+" "+z);
					continue;
				}*/
				new NpcSpawn(id, face, new Position(x, y, z), type == 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Loaded " + spawns + " npc spawns.");
	}
	
	public static void saveSpawns(){
		File f = new File("./spawns/Npc spawn.dat");
		f.delete();
        try {
			Packer stream2 = new Packer(new FileOutputStream("./spawns/Npc spawn.dat"));
			stream2.writeShort(listOfNpcs.size());
			for (NpcSpawn npc : listOfNpcs) {
				if(npc != null) {
					stream2.writeShort(npc.getId());
					stream2.writeBoolean(npc.isMembersOnly());
					stream2.writeUnsignedByte(npc.getWalkType());
					stream2.writeShort(npc.getPosition().getX());
					stream2.writeShort(npc.getPosition().getY());
					stream2.writeUnsignedByte(npc.getPosition().getZ());
				}
			}
			stream2.close();
        } catch (Exception e) {

        }
		System.out.println("Npcs packed.");
	}
	
	
	private int id;
	private boolean membersOnly;
	private int walkType;
	private Position position;
	
	public NpcSpawn(int id, int walkType, Position position, boolean membersOnly){
		this.id = id;
		this.walkType = walkType;
		this.position = position;
		this.membersOnly = membersOnly;
		listOfNpcs.add(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWalkType() {
		return walkType;
	}

	public void setWalkType(int walkType) {
		this.walkType = walkType;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isMembersOnly() {
		return membersOnly;
	}

	public void setMembersOnly(boolean membersOnly) {
		this.membersOnly = membersOnly;
	}

}
