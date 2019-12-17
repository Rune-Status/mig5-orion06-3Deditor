package client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class SpriteLoader {

	public static void loadSprites() {
		try {
			Stream index = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "sprites.idx"));
			Stream data = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "sprites.dat"));
			DataInputStream indexFile = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(index.buffer)));
			DataInputStream dataFile = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(data.buffer)));
			int totalSprites = indexFile.readInt();
			if (cache == null) {
				cache = new SpriteLoader[totalSprites];
				sprites = new Sprite[totalSprites];
			}
			for (int i = 0; i < totalSprites; i++) {
				int id = indexFile.readInt();
				if (cache[id] == null) {
					cache[id] = new SpriteLoader();
				}
				cache[id].readValues(indexFile, dataFile);
				createSprite(cache[id]);
			}
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadInterfaceSprites() {
		try {
			Stream index = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "interfaceSprites.idx"));
			Stream data = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "interfaceSprites.dat"));
			DataInputStream indexFile = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(index.buffer)));
			DataInputStream dataFile = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(data.buffer)));
			int totalSprites = indexFile.readInt();
			if (cacheI == null) {
				cacheI = new SpriteLoader[totalSprites];
				spritesI = new Sprite[totalSprites];
			}
			for (int i = 0; i < totalSprites; i++) {
				int id = indexFile.readInt();
				if (cacheI[id] == null) {
					cacheI[id] = new SpriteLoader();
				}
				cacheI[id].readValues(indexFile, dataFile);
				createSpriteI(cacheI[id]);
			}
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the information from the index and data files.
	 * @param index holds the sprite indices
	 * @param data holds the sprite data per index
	 * @throws IOException
	 */
	public void readValues(DataInputStream index, DataInputStream data) throws IOException {
		do {
			int opCode = data.readByte();
			if (opCode == 0) {
				break;
			}
			if (opCode == 1) {
				id = data.readShort();
			} else if (opCode == 2) {
				name = data.readUTF();
			} else if (opCode == 3) {
				drawOffsetX = data.readShort();
			} else if (opCode == 4) {
				drawOffsetY = data.readShort();
			} else if (opCode == 5) {
				int indexLength = index.readInt();
				byte[] dataread = new byte[indexLength];
				data.readFully(dataread);
				spriteData = dataread;
			}
		} while (true);
	}

	/**
	 * Creates a sprite out of the spriteData.
	 * @param sprite
	 */
	public static void createSprite(SpriteLoader sprite) {
		sprites[sprite.id] = new Sprite(sprite.spriteData);
		sprites[sprite.id].anInt1442 = sprite.drawOffsetX;
		sprites[sprite.id].anInt1443 = sprite.drawOffsetY;
	}
	
	public static void createSpriteI(SpriteLoader sprite) {
		spritesI[sprite.id] = new Sprite(sprite.spriteData);
		spritesI[sprite.id].anInt1442 = sprite.drawOffsetX;
		spritesI[sprite.id].anInt1443 = sprite.drawOffsetY;
	}

	/**
	 * Gets the name of a specified sprite index.
	 * @param index
	 * @return
	 */
	public static String getName(int index) {
		if (cache[index].name != null) {
			return cache[index].name;
		} else {
			return "null";
		}
	}
	
	public static byte[] getData(int index) {
		if (cache[index].name != null) {
			return cache[index].spriteData;
		} else {
			return null;
		}
	}

	/**
	 * Gets the drawOffsetX of a specified sprite index.
	 * @param index
	 * @return
	 */
	public static int getOffsetX(int index) {
		return cache[index].drawOffsetX;
	}

	/**
	 * Gets the drawOffsetY of a specified sprite index.
	 * @param index
	 * @return
	 */
	public static int getOffsetY(int index) {
		return cache[index].drawOffsetY;
	}

	/**
	 * Sets the default values.
	 */
	public SpriteLoader() {
		name = "name";
		id = -1;
		drawOffsetX = 0;
		drawOffsetY = 0;
		spriteData = null;
	}

	public static SpriteLoader[] cache;
	public static Sprite[] sprites = null;
	public static int totalSprites;
	public String name;
	public int id;
	public int drawOffsetX;
	public int drawOffsetY;
	public byte[] spriteData;
	
	public static SpriteLoader[] cacheI;
	public static Sprite[] spritesI = null;
}