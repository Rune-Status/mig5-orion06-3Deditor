package Editor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import client.FileOperations;
import client.Stream;

public class ItemSpawn {

	//public ItemSpawn[] itemSpawns;
	public static ArrayList<ItemSpawn> listOfItems = new ArrayList<ItemSpawn>();
	
	public static void loadSpawns() {
        int drops = 0;
		try {
			byte abyte2[] = FileOperations.ReadFile("./spawns/Item spawn.dat");
			Stream stream2 = new Stream(abyte2);
			drops = stream2.readUnsignedWord();
			for (int i2 = 0; i2 < drops; i2++) {
				int id = stream2.readUnsignedWord();
				int amount = stream2.readUnsignedWord();
				int type = stream2.readUnsignedByte();
				int x = stream2.readUnsignedWord();
				int y = stream2.readUnsignedWord();
				int z = stream2.readUnsignedByte();
				new ItemSpawn(id, amount, new Position(x, y, z), type == 1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Loaded " + drops + " global drops.");
	}
	
	public static void saveSpawns(){
		File f = new File("./spawns/Item spawn.dat");
		f.delete();
		try {
			Packer stream2 = new Packer(new FileOutputStream("./spawns/Item spawn.dat"));
			stream2.writeShort(listOfItems.size());
			for (ItemSpawn item : listOfItems) {
				if(item != null) {
					stream2.writeShort(item.getId());
					stream2.writeShort(item.getAmount());
					stream2.writeBoolean(item.isMembersOnly());
					stream2.writeShort(item.getPosition().getX());
					stream2.writeShort(item.getPosition().getY());
					stream2.writeUnsignedByte(item.getPosition().getZ());
				}
			}	
			stream2.close();
        } catch (Exception e) {

        }
		System.out.println("Items packed.");
	}
	
	
	private int id;
	private boolean membersOnly;
	private int amount;
	private Position position;
	
	public ItemSpawn(int id, int amount, Position position, boolean membersOnly){
		this.id = id;
		this.amount = amount;
		this.position = position;
		this.membersOnly = membersOnly;
		listOfItems.add(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
