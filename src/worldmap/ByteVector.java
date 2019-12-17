package worldmap;

/**
 * Class: ByteVector.java Originally: Class1_Sub1_Sub2.java
 * */

public class ByteVector extends Class1_Sub1 {

	public int getInt() {
		currentOffset += 4;
		return ((buffer[currentOffset - 4] & 0xff) << 24)
				+ ((buffer[currentOffset - 3] & 0xff) << 16)
				+ ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public int getUnsignedByte() {
		return buffer[currentOffset++] & 0xff;
	}

	public byte getByte() {
		return buffer[currentOffset++];
	}

	public int getShort() {
		currentOffset += 2;
		return ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public String getString() {
		int i = currentOffset;
		while (buffer[currentOffset++] != 10) ;
		return new String(buffer, i, currentOffset - i - 1);
	}

	public int getShortInt() {
		currentOffset += 3;
		return ((buffer[currentOffset - 3] & 0xff) << 16)
				+ ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public ByteVector() {
	}

	public ByteVector(byte abyte0[]) {
		buffer = abyte0;
		currentOffset = 0;
	}

	public byte buffer[];
	public int currentOffset;
	public static int anIntArray188[];//Unneeded.
	public static int anIntArray189[] = { 0, 1, 3, 7, 15, 31, 63, 127, 255,//never read anywhere?
			511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff,
			0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff,
			0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff,
			0x7fffffff, -1 };
	public static int anInt190 = 0;//never read anywhere?
	public static int anInt191 = 0;//never read anywhere?
	public static int anInt192 = 0;//never read anywhere?
	public static Class2 aClass2_193 = new Class2();//never read anywhere?
	public static Class2 aClass2_194 = new Class2();//never read anywhere?
	public static Class2 aClass2_195 = new Class2();//never read anywhere?
	public static char aCharArray196[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',//never read anywhere?
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/' };
	public static boolean aBoolean197;//never read anywhere?

	static {//Unneeded.
		anIntArray188 = new int[256];
		for (int j = 0; j < 256; j++) {
			int i = j;
			for (int k = 0; k < 8; k++)
				if ((i & 1) == 1)
					i = i >>> 1 ^ 0xedb88320;
				else
					i >>>= 1;

			anIntArray188[j] = i;
		}

	}
}
