package worldmap;

/**
 * Class: Main.java
 * Originally: Applet_Sub1_Sub1.java
 * */

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;

@SuppressWarnings("serial")
public class Main extends RSApplet
{
	
	static Main applet_sub1_sub1;

	public static void main(String args[])
	{
		if(applet_sub1_sub1 == null){
			applet_sub1_sub1 = new Main();
			applet_sub1_sub1.createFrame(635, 503);
		} else {
			applet_sub1_sub1.rsFrame.setVisible(true);
		}
	}

	public void init()
	{
		initializeFrame(635, 503);
	}

	public void startUp()
	{
		Class6 class6 = method21();
		drawLoadingText(100, "Please wait... Rendering Map");
		ByteVector byteVector = new ByteVector(class6.method76("size.dat", null));
		anInt111 = byteVector.getShort();
		anInt112 = byteVector.getShort();
		anInt113 = byteVector.getShort();
		anInt114 = byteVector.getShort();
		anInt171 = 3200 - anInt111;
		anInt172 = (anInt112 + anInt114) - 3200;
		anInt152 = 180;
		anInt153 = (anInt113 * anInt152) / anInt114;
		anInt154 = 635 - anInt153 - 5;
		anInt155 = 503 - anInt152 - 20;
		byteVector = new ByteVector(class6.method76("labels.dat", null));
		anInt163 = byteVector.getShort();
		for(int i = 0; i < anInt163; i++)
		{
			aStringArray165[i] = byteVector.getString();
			anIntArray166[i] = byteVector.getShort();
			anIntArray167[i] = byteVector.getShort();
			anIntArray168[i] = byteVector.getUnsignedByte();
		}

		byteVector = new ByteVector(class6.method76("floorcol.dat", null));
		int j = byteVector.getShort();
		anIntArray115 = new int[j + 1];
		anIntArray116 = new int[j + 1];
		for(int k = 0; k < j; k++)
		{
			anIntArray115[k + 1] = byteVector.getInt();
			anIntArray116[k + 1] = byteVector.getInt();
		}

		byte abyte0[] = class6.method76("underlay.dat", null);
		byte abyte1[][] = new byte[anInt113][anInt114];
		method14(abyte0, abyte1);
		byte abyte2[] = class6.method76("overlay.dat", null);
		anIntArrayArray118 = new int[anInt113][anInt114];
		aByteArrayArray119 = new byte[anInt113][anInt114];
		method15(abyte2, anIntArrayArray118, aByteArrayArray119);
		byte abyte3[] = class6.method76("loc.dat", null);
		aByteArrayArray120 = new byte[anInt113][anInt114];
		aByteArrayArray122 = new byte[anInt113][anInt114];
		aByteArrayArray121 = new byte[anInt113][anInt114];
		method13(abyte3, aByteArrayArray120, aByteArrayArray122, aByteArrayArray121);
		try
		{
			for(int l = 0; l < 100; l++)
				aClass1_Sub1_Sub1_Sub3Array123[l] = new Class1_Sub1_Sub1_Sub3(class6, "mapscene", l);

		}
		catch(Exception _ex) { }
		try
		{
			for(int i1 = 0; i1 < 100; i1++)
				aClass1_Sub1_Sub1_Sub2Array124[i1] = new Class1_Sub1_Sub1_Sub2(class6, "mapfunction", i1);

		}
		catch(Exception _ex) { }
		aClass1_Sub1_Sub1_Sub4_125 = new Class1_Sub1_Sub1_Sub4(class6, "b12_full", false);
		aSprite_126 = new Sprite(11, true, this);
		aSprite_127 = new Sprite(12, true, this);
		aSprite_128 = new Sprite(14, true, this);
		aSprite_129 = new Sprite(17, true, this);
		aSprite_130 = new Sprite(19, true, this);
		aSprite_131 = new Sprite(22, true, this);
		aSprite_132 = new Sprite(26, true, this);
		aSprite_133 = new Sprite(30, true, this);
		anIntArrayArray117 = new int[anInt113][anInt114];
		method16(abyte1, anIntArrayArray117);
		aClass1_Sub1_Sub1_Sub2_157 = new Class1_Sub1_Sub1_Sub2(anInt153, anInt152);
		aClass1_Sub1_Sub1_Sub2_157.method45();
		method19(0, 0, anInt113, anInt114, 0, 0, anInt153, anInt152);
		DrawingArea.fillPixels(0, 0, anInt153, anInt152, 0);
		DrawingArea.fillPixels(1, 1, anInt153 - 2, anInt152 - 2, anInt103);
		super.fullScreen.initializeDrawingArea();
	}

	public void method13(byte abyte0[], byte abyte1[][], byte abyte2[][], byte abyte3[][])
	{
		for(int i = 0; i < abyte0.length;)
		{
			int k = (abyte0[i++] & 0xff) * 64 - anInt111;
			int l = (abyte0[i++] & 0xff) * 64 - anInt112;
			if(k > 0 && l > 0 && k + 64 < anInt113 && l + 64 < anInt114)
			{
				for(int i1 = 0; i1 < 64; i1++)
				{
					byte abyte4[] = abyte1[i1 + k];
					byte abyte5[] = abyte2[i1 + k];
					byte abyte6[] = abyte3[i1 + k];
					int l1 = anInt114 - l - 1;
					for(int i2 = -64; i2 < 0; i2++)
					{
						do
						{
							int j = abyte0[i++] & 0xff;
							if(j == 0)
								break;
							if(j < 29)
								abyte4[l1] = (byte)j;
							else
							if(j < 160)
							{
								abyte5[l1] = (byte)(j - 28);
							} else
							{
								abyte6[l1] = (byte)(j - 159);
								anIntArray138[anInt137] = i1 + k;
								anIntArray139[anInt137] = l1;
								anIntArray140[anInt137] = j - 160;
								anInt137++;
							}
						} while(true);
						l1--;
					}

				}

			} else
			{
				for(int j1 = 0; j1 < 64; j1++)
				{
					for(int k1 = -64; k1 < 0; k1++)
					{
						byte byte0;
						do
							byte0 = abyte0[i++];
						while(byte0 != 0);
					}

				}

			}
		}

	}

	public void method14(byte abyte0[], byte abyte1[][])
	{
		for(int i = 0; i < abyte0.length;)
		{
			int j = (abyte0[i++] & 0xff) * 64 - anInt111;
			int k = (abyte0[i++] & 0xff) * 64 - anInt112;
			if(j > 0 && k > 0 && j + 64 < anInt113 && k + 64 < anInt114)
			{
				for(int l = 0; l < 64; l++)
				{
					byte abyte2[] = abyte1[l + j];
					int i1 = anInt114 - k - 1;
					for(int j1 = -64; j1 < 0; j1++)
						abyte2[i1--] = abyte0[i++];

				}

			} else
			{
				i += 4096;
			}
		}

	}

	public void method15(byte abyte0[], int ai[][], byte abyte1[][])
	{
		for(int i = 0; i < abyte0.length;)
		{
			int j = (abyte0[i++] & 0xff) * 64 - anInt111;
			int k = (abyte0[i++] & 0xff) * 64 - anInt112;
			if(j > 0 && k > 0 && j + 64 < anInt113 && k + 64 < anInt114)
			{
				for(int l = 0; l < 64; l++)
				{
					int ai1[] = ai[l + j];
					byte abyte2[] = abyte1[l + j];
					int j1 = anInt114 - k - 1;
					for(int k1 = -64; k1 < 0; k1++)
					{
						byte byte0 = abyte0[i++];
						if(byte0 != 0)
						{
							abyte2[j1] = abyte0[i++];
							int l1 = 0;
							if(byte0 > 0)
								l1 = anIntArray116[byte0];
							ai1[j1--] = l1;
						} else
						{
							ai1[j1--] = 0;
						}
					}

				}

			} else
			{
				for(int i1 = -4096; i1 < 0; i1++)
				{
					byte byte1 = abyte0[i++];
					if(byte1 != 0)
						i++;
				}

			}
		}

	}

	public void method16(byte abyte0[][], int ai[][])
	{
		int i = anInt113;
		int j = anInt114;
		int ai1[] = new int[j];
		for(int k = 0; k < j; k++)
			ai1[k] = 0;

		for(int l = 5; l < i - 5; l++)
		{
			byte abyte1[] = abyte0[l + 5];
			byte abyte2[] = abyte0[l - 5];
			for(int i1 = 0; i1 < j; i1++)
				ai1[i1] += anIntArray115[abyte1[i1] & 0xff] - anIntArray115[abyte2[i1] & 0xff];

			if(l > 10 && l < i - 10)
			{
				int j1 = 0;
				int k1 = 0;
				int l1 = 0;
				int ai2[] = ai[l];
				for(int i2 = 5; i2 < j - 5; i2++)
				{
					int j2 = ai1[i2 - 5];
					int k2 = ai1[i2 + 5];
					j1 += (k2 >> 20) - (j2 >> 20);
					k1 += (k2 >> 10 & 0x3ff) - (j2 >> 10 & 0x3ff);
					l1 += (k2 & 0x3ff) - (j2 & 0x3ff);
					if(l1 > 0)
						ai2[i2] = method17((double)j1 / 8533D, (double)k1 / 8533D, (double)l1 / 8533D);
				}

			}
		}

	}

	public int method17(double d, double d1, double d2)
	{
		double d3 = d2;
		double d4 = d2;
		double d5 = d2;
		if(d1 != 0.0D)
		{
			double d6;
			if(d2 < 0.5D)
				d6 = d2 * (1.0D + d1);
			else
				d6 = (d2 + d1) - d2 * d1;
			double d7 = 2D * d2 - d6;
			double d8 = d + 0.33333333333333331D;
			if(d8 > 1.0D)
				d8--;
			double d9 = d;
			double d10 = d - 0.33333333333333331D;
			if(d10 < 0.0D)
				d10++;
			if(6D * d8 < 1.0D)
				d3 = d7 + (d6 - d7) * 6D * d8;
			else
			if(2D * d8 < 1.0D)
				d3 = d6;
			else
			if(3D * d8 < 2D)
				d3 = d7 + (d6 - d7) * (0.66666666666666663D - d8) * 6D;
			else
				d3 = d7;
			if(6D * d9 < 1.0D)
				d4 = d7 + (d6 - d7) * 6D * d9;
			else
			if(2D * d9 < 1.0D)
				d4 = d6;
			else
			if(3D * d9 < 2D)
				d4 = d7 + (d6 - d7) * (0.66666666666666663D - d9) * 6D;
			else
				d4 = d7;
			if(6D * d10 < 1.0D)
				d5 = d7 + (d6 - d7) * 6D * d10;
			else
			if(2D * d10 < 1.0D)
				d5 = d6;
			else
			if(3D * d10 < 2D)
				d5 = d7 + (d6 - d7) * (0.66666666666666663D - d10) * 6D;
			else
				d5 = d7;
		}
		int i = (int)(d3 * 256D);
		int j = (int)(d4 * 256D);
		int k = (int)(d5 * 256D);
		int l = (i << 16) + (j << 8) + k;
		return l;
	}

	public void cleanUpForQuit()
	{
		try
		{
			anIntArray115 = null;
			anIntArray116 = null;
			anIntArrayArray117 = null;
			anIntArrayArray118 = null;
			aByteArrayArray119 = null;
			aByteArrayArray120 = null;
			aByteArrayArray121 = null;
			aByteArrayArray122 = null;
			aClass1_Sub1_Sub1_Sub3Array123 = null;
			aClass1_Sub1_Sub1_Sub2Array124 = null;
			aClass1_Sub1_Sub1_Sub4_125 = null;
			anIntArray134 = null;
			anIntArray135 = null;
			anIntArray136 = null;
			anIntArray138 = null;
			anIntArray139 = null;
			anIntArray140 = null;
			aClass1_Sub1_Sub1_Sub2_157 = null;
			aStringArray165 = null;
			anIntArray166 = null;
			anIntArray167 = null;
			anIntArray168 = null;
			iconExplainers = null;
			System.gc();
			return;
		}
		catch(Throwable _ex)
		{
			return;
		}
	}

	public void processLoop()
	{
		if(super.keyArray[1] == 1)
		{
			anInt171 = (int)((double)anInt171 - 16D / aDouble169);
			aBoolean109 = true;
		}
		if(super.keyArray[2] == 1)
		{
			anInt171 = (int)((double)anInt171 + 16D / aDouble169);
			aBoolean109 = true;
		}
		if(super.keyArray[3] == 1)
		{
			anInt172 = (int)((double)anInt172 - 16D / aDouble169);
			aBoolean109 = true;
		}
		if(super.keyArray[4] == 1)
		{
			anInt172 = (int)((double)anInt172 + 16D / aDouble169);
			aBoolean109 = true;
		}
		for(int i = 1; i > 0;)
		{
			i = method7();
			if(i == 49)
			{
				aDouble170 = 3D;
				aBoolean109 = true;
			}
			if(i == 50)
			{
				aDouble170 = 4D;
				aBoolean109 = true;
			}
			if(i == 51)
			{
				aDouble170 = 6D;
				aBoolean109 = true;
			}
			if(i == 52)
			{
				aDouble170 = 8D;
				aBoolean109 = true;
			}
			if(i == 107 || i == 75)
			{
				aBoolean147 = !aBoolean147;
				aBoolean109 = true;
			}
			if(i == 111 || i == 79)
			{
				aBoolean156 = !aBoolean156;
				aBoolean109 = true;
			}
			if(super.rsFrame != null && i == 101)
			{
				System.out.println("Starting export...");
				Class1_Sub1_Sub1_Sub2 class1_sub1_sub1_sub2 = new Class1_Sub1_Sub1_Sub2(anInt113 * 2, anInt114 * 2);
				class1_sub1_sub1_sub2.method45();
				method19(0, 0, anInt113, anInt114, 0, 0, anInt113 * 2, anInt114 * 2);
				super.fullScreen.initializeDrawingArea();
				int i1 = class1_sub1_sub1_sub2.anIntArray202.length;
				byte abyte0[] = new byte[i1 * 3];
				int j2 = 0;
				for(int l2 = 0; l2 < i1; l2++)
				{
					int i3 = class1_sub1_sub1_sub2.anIntArray202[l2];
					abyte0[j2++] = (byte)(i3 >> 16);
					abyte0[j2++] = (byte)(i3 >> 8);
					abyte0[j2++] = (byte)i3;
				}

				System.out.println("Saving to disk");
				try
				{
					BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream("map-" + anInt113 * 2 + "-" + anInt114 * 2 + "-rgb.raw"));
					bufferedoutputstream.write(abyte0);
					bufferedoutputstream.close();
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
				System.out.println("Done export: " + anInt113 * 2 + "," + anInt114 * 2);
			}
		}

		if(super.anInt23 == 1)
		{
			anInt158 = super.anInt24;
			anInt159 = super.anInt25;
			anInt160 = anInt171;
			anInt161 = anInt172;
			if(super.anInt24 > 170 && super.anInt24 < 220 && super.anInt25 > 471 && super.anInt25 < 503)
			{
				aDouble170 = 3D;
				anInt158 = -1;
			}
			if(super.anInt24 > 230 && super.anInt24 < 280 && super.anInt25 > 471 && super.anInt25 < 503)
			{
				aDouble170 = 4D;
				anInt158 = -1;
			}
			if(super.anInt24 > 290 && super.anInt24 < 340 && super.anInt25 > 471 && super.anInt25 < 503)
			{
				aDouble170 = 6D;
				anInt158 = -1;
			}
			if(super.anInt24 > 350 && super.anInt24 < 400 && super.anInt25 > 471 && super.anInt25 < 503)
			{
				aDouble170 = 8D;
				anInt158 = -1;
			}
			if(super.anInt24 > anInt141 && super.anInt25 > anInt142 + anInt144 && super.anInt24 < anInt141 + anInt143 && super.anInt25 < 503)
			{
				aBoolean147 = !aBoolean147;
				anInt158 = -1;
			}
			if(super.anInt24 > anInt154 && super.anInt25 > anInt155 + anInt152 && super.anInt24 < anInt154 + anInt153 && super.anInt25 < 503)
			{
				aBoolean156 = !aBoolean156;
				anInt158 = -1;
			}
			if(aBoolean147)
			{
				if(super.anInt24 > anInt141 && super.anInt25 > anInt142 && super.anInt24 < anInt141 + anInt143 && super.anInt25 < anInt142 + anInt144)
					anInt158 = -1;
				if(super.anInt24 > anInt141 && super.anInt25 > anInt142 && super.anInt24 < anInt141 + anInt143 && super.anInt25 < anInt142 + 18 && anInt146 > 0)
					anInt146 -= 25;
				if(super.anInt24 > anInt141 && super.anInt25 > (anInt142 + anInt144) - 18 && super.anInt24 < anInt141 + anInt143 && super.anInt25 < anInt142 + anInt144 && anInt146 < 50)
					anInt146 += 25;
			}
			aBoolean109 = true;
		}
		if(aBoolean147)
		{
			anInt148 = -1;
			if(super.xDragged > anInt141 && super.xDragged < anInt141 + anInt143)
			{
				int j = anInt142 + 21 + 5;
				for(int j1 = 0; j1 < 25; j1++)
					if(j1 + anInt145 >= iconExplainers.length || !iconExplainers[j1 + anInt145].equals("???"))
					{
						if(super.yDragged >= j && super.yDragged < j + 17)
						{
							anInt148 = j1 + anInt145;
							if(super.anInt23 == 1)
							{
								anInt150 = j1 + anInt145;
								anInt151 = 50;
							}
						}
						j += 17;
					}

			}
			if(anInt148 != anInt149)
			{
				anInt149 = anInt148;
				aBoolean109 = true;
			}
		}
		if((super.clickMode2 == 1 || super.anInt23 == 1) && aBoolean156)
		{
			int k = super.anInt24;
			int k1 = super.anInt25;
			if(super.clickMode2 == 1)
			{
				k = super.xDragged;
				k1 = super.yDragged;
			}
			if(k > anInt154 && k1 > anInt155 && k < anInt154 + anInt153 && k1 < anInt155 + anInt152)
			{
				anInt171 = ((k - anInt154) * anInt113) / anInt153;
				anInt172 = ((k1 - anInt155) * anInt114) / anInt152;
				anInt158 = -1;
				aBoolean109 = true;
			}
		}
		if(super.clickMode2 == 1 && anInt158 != -1)
		{
			anInt171 = anInt160 + (int)(((double)(anInt158 - super.xDragged) * 2D) / aDouble170);
			anInt172 = anInt161 + (int)(((double)(anInt159 - super.yDragged) * 2D) / aDouble170);
			aBoolean109 = true;
		}
		if(aDouble169 < aDouble170)
		{
			aBoolean109 = true;
			aDouble169 += aDouble169 / 30D;
			if(aDouble169 > aDouble170)
				aDouble169 = aDouble170;
		}
		if(aDouble169 > aDouble170)
		{
			aBoolean109 = true;
			aDouble169 -= aDouble169 / 30D;
			if(aDouble169 < aDouble170)
				aDouble169 = aDouble170;
		}
		if(anInt145 < anInt146)
		{
			aBoolean109 = true;
			anInt145++;
		}
		if(anInt145 > anInt146)
		{
			aBoolean109 = true;
			anInt145--;
		}
		if(anInt151 > 0)
		{
			aBoolean109 = true;
			anInt151--;
		}
		int l = anInt171 - (int)(635D / aDouble169);
		int l1 = anInt172 - (int)(503D / aDouble169);
		int i2 = anInt171 + (int)(635D / aDouble169);
		int k2 = anInt172 + (int)(503D / aDouble169);
		if(l < 48)
			anInt171 = 48 + (int)(635D / aDouble169);
		if(l1 < 48)
			anInt172 = 48 + (int)(503D / aDouble169);
		if(i2 > anInt113 - 48)
			anInt171 = anInt113 - 48 - (int)(635D / aDouble169);
		if(k2 > anInt114 - 48)
			anInt172 = anInt114 - 48 - (int)(503D / aDouble169);
	}

	public void processDrawing()
	{
		if(aBoolean109)
		{
			aBoolean109 = false;
			anInt110 = 0;
			DrawingArea.setAllPixelsToZero();
			int i = anInt171 - (int)(635D / aDouble169);
			int j = anInt172 - (int)(503D / aDouble169);
			int k = anInt171 + (int)(635D / aDouble169);
			int l = anInt172 + (int)(503D / aDouble169);
			method19(i, j, k, l, 0, 0, 635, 503);
			if(aBoolean156)
			{
				aClass1_Sub1_Sub1_Sub2_157.method48(anInt154, anInt155);
				DrawingArea.method30(anInt154 + (anInt153 * i) / anInt113, anInt155 + (anInt152 * j) / anInt114, ((k - i) * anInt153) / anInt113, ((l - j) * anInt152) / anInt114, 0xff0000, 128);
				DrawingArea.fillPixels(anInt154 + (anInt153 * i) / anInt113, anInt155 + (anInt152 * j) / anInt114, ((k - i) * anInt153) / anInt113, ((l - j) * anInt152) / anInt114, 0xff0000);
				if(anInt151 > 0 && anInt151 % 10 < 5)
				{
					for(int i1 = 0; i1 < anInt137; i1++)
						if(anIntArray140[i1] == anInt150)
						{
							int k1 = anInt154 + (anInt153 * anIntArray138[i1]) / anInt113;
							int i2 = anInt155 + (anInt152 * anIntArray139[i1]) / anInt114;
							DrawingArea.method35(k1, i2, 2, 0xffff00, 256);
						}

				}
			}
			if(aBoolean147)
			{
				method18(anInt141, anInt142, anInt143, 18, 0x999999, 0x777777, 0x555555, "Prev page");
				method18(anInt141, anInt142 + 18, anInt143, anInt144 - 36, 0x999999, 0x777777, 0x555555, "");
				method18(anInt141, (anInt142 + anInt144) - 18, anInt143, 18, 0x999999, 0x777777, 0x555555, "Next page");
				int j1 = anInt142 + 3 + 18;
				for(int l1 = 0; l1 < 25; l1++)
				{
					if(l1 + anInt145 < aClass1_Sub1_Sub1_Sub2Array124.length && l1 + anInt145 < iconExplainers.length)
					{
						if(iconExplainers[l1 + anInt145].equals("???"))
							continue;
						aClass1_Sub1_Sub1_Sub2Array124[l1 + anInt145].method46(anInt141 + 3, j1);
						aClass1_Sub1_Sub1_Sub4_125.method53(iconExplainers[l1 + anInt145], anInt141 + 21, j1 + 14, 0);
						int j2 = 0xffffff;
						if(anInt148 == l1 + anInt145)
							j2 = 0xbbaaaa;
						if(anInt151 > 0 && anInt151 % 10 < 5 && anInt150 == l1 + anInt145)
							j2 = 0xffff00;
						aClass1_Sub1_Sub1_Sub4_125.method53(iconExplainers[l1 + anInt145], anInt141 + 20, j1 + 13, j2);
					}
					j1 += 17;
				}

			}
			method18(anInt154, anInt155 + anInt152, anInt153, 18, anInt103, anInt104, anInt105, "Overview");
			method18(anInt141, anInt142 + anInt144, anInt143, 18, anInt103, anInt104, anInt105, "Key");
			if(aDouble170 == 3D)
				method18(170, 471, 50, 30, anInt106, anInt107, anInt108, "37%");
			else
				method18(170, 471, 50, 30, anInt103, anInt104, anInt105, "37%");
			if(aDouble170 == 4D)
				method18(230, 471, 50, 30, anInt106, anInt107, anInt108, "50%");
			else
				method18(230, 471, 50, 30, anInt103, anInt104, anInt105, "50%");
			if(aDouble170 == 6D)
				method18(290, 471, 50, 30, anInt106, anInt107, anInt108, "75%");
			else
				method18(290, 471, 50, 30, anInt103, anInt104, anInt105, "75%");
			if(aDouble170 == 8D)
				method18(350, 471, 50, 30, anInt106, anInt107, anInt108, "100%");
			else
				method18(350, 471, 50, 30, anInt103, anInt104, anInt105, "100%");
		}
		anInt110--;
		if(anInt110 <= 0)
		{
			super.fullScreen.drawGraphics(super.graphics, 0, 0);
			anInt110 = 50;
		}
	}

	public void stupidMethodThatDoesNothing()
	{
		anInt110 = 0;
	}

	public void method18(int i, int j, int k, int l, int i1, int j1, int k1, 
			String s)
	{
		DrawingArea.fillPixels(i, j, k, l, 0);
		i++;
		j++;
		k -= 2;
		l -= 2;
		DrawingArea.method33(i, j, k, l, j1);
		DrawingArea.method27(i, j, k, i1);
		DrawingArea.method28(i, j, l, i1);
		DrawingArea.method27(i, (j + l) - 1, k, k1);
		DrawingArea.method28((i + k) - 1, j, l, k1);
		aClass1_Sub1_Sub1_Sub4_125.method55(s, i + k / 2 + 1, j + l / 2 + 1 + 4, 0);
		aClass1_Sub1_Sub1_Sub4_125.method55(s, i + k / 2, j + l / 2 + 4, 0xffffff);
	}

	public void method19(int i, int j, int k, int l, int i1, int j1, int k1, 
			int l1)
	{
		int i2 = k - i;
		int j2 = l - j;
		int k2 = (k1 - i1 << 16) / i2;
		int l2 = (l1 - j1 << 16) / j2;
		for(int i3 = 0; i3 < i2; i3++)
		{
			int j3 = k2 * i3 >> 16;
			int l3 = k2 * (i3 + 1) >> 16;
			int j4 = l3 - j3;
			if(j4 > 0)
			{
				j3 += i1;
				l3 += i1;
				int ai[] = anIntArrayArray117[i3 + i];
				int ai1[] = anIntArrayArray118[i3 + i];
				byte abyte0[] = aByteArrayArray119[i3 + i];
				for(int j7 = 0; j7 < j2; j7++)
				{
					int i8 = l2 * j7 >> 16;
					int l8 = l2 * (j7 + 1) >> 16;
					int l9 = l8 - i8;
					if(l9 > 0)
					{
						i8 += j1;
						l8 += j1;
						int l10 = ai1[j7 + j];
						if(l10 == 0)
						{
							DrawingArea.method33(j3, i8, l3 - j3, l8 - i8, ai[j7 + j]);
						} else
						{
							byte byte0 = abyte0[j7 + j];
							int l11 = byte0 & 0xfc;
							if(l11 == 0 || j4 <= 1 || l9 <= 1)
								DrawingArea.method33(j3, i8, j4, l9, l10);
							else
								method20(DrawingArea.pixels, i8 * DrawingArea.width + j3, ai[j7 + j], l10, j4, l9, l11 >> 2, byte0 & 3);
						}
					}
				}

			}
		}

		if(k - i > k1 - i1)
			return;
		int k3 = 0;
		for(int i4 = 0; i4 < i2; i4++)
		{
			int k4 = k2 * i4 >> 16;
			int i5 = k2 * (i4 + 1) >> 16;
			int i6 = i5 - k4;
			if(i6 > 0)
			{
				byte abyte1[] = aByteArrayArray120[i4 + i];
				byte abyte2[] = aByteArrayArray122[i4 + i];
				byte abyte3[] = aByteArrayArray121[i4 + i];
				for(int i9 = 0; i9 < j2; i9++)
				{
					int i10 = l2 * i9 >> 16;
					int i11 = l2 * (i9 + 1) >> 16;
					int k11 = i11 - i10;
					if(k11 > 0)
					{
						int i12 = abyte1[i9 + j] & 0xff;
						if(i12 != 0)
						{
							int k12;
							if(i6 == 1)
								k12 = k4;
							else
								k12 = i5 - 1;
							int j13;
							if(k11 == 1)
								j13 = i10;
							else
								j13 = i11 - 1;
							int i14 = 0xcccccc;
							if(i12 >= 5 && i12 <= 8 || i12 >= 13 && i12 <= 16 || i12 >= 21 && i12 <= 24 || i12 == 27 || i12 == 28)
							{
								i14 = 0xcc0000;
								i12 -= 4;
							}
							if(i12 == 1)
								DrawingArea.method28(k4, i10, k11, i14);
							else
							if(i12 == 2)
								DrawingArea.method27(k4, i10, i6, i14);
							else
							if(i12 == 3)
								DrawingArea.method28(k12, i10, k11, i14);
							else
							if(i12 == 4)
								DrawingArea.method27(k4, j13, i6, i14);
							else
							if(i12 == 9)
							{
								DrawingArea.method28(k4, i10, k11, 0xffffff);
								DrawingArea.method27(k4, i10, i6, i14);
							} else
							if(i12 == 10)
							{
								DrawingArea.method28(k12, i10, k11, 0xffffff);
								DrawingArea.method27(k4, i10, i6, i14);
							} else
							if(i12 == 11)
							{
								DrawingArea.method28(k12, i10, k11, 0xffffff);
								DrawingArea.method27(k4, j13, i6, i14);
							} else
							if(i12 == 12)
							{
								DrawingArea.method28(k4, i10, k11, 0xffffff);
								DrawingArea.method27(k4, j13, i6, i14);
							} else
							if(i12 == 17)
								DrawingArea.method27(k4, i10, 1, i14);
							else
							if(i12 == 18)
								DrawingArea.method27(k12, i10, 1, i14);
							else
							if(i12 == 19)
								DrawingArea.method27(k12, j13, 1, i14);
							else
							if(i12 == 20)
								DrawingArea.method27(k4, j13, 1, i14);
							else
							if(i12 == 25)
							{
								for(int j14 = 0; j14 < k11; j14++)
									DrawingArea.method27(k4 + j14, j13 - j14, 1, i14);

							} else
							if(i12 == 26)
							{
								for(int k14 = 0; k14 < k11; k14++)
									DrawingArea.method27(k4 + k14, i10 + k14, 1, i14);

							}
						}
						int l12 = abyte2[i9 + j] & 0xff;
						if(l12 != 0)
							aClass1_Sub1_Sub1_Sub3Array123[l12 - 1].method50(k4 - i6 / 2, i10 - k11 / 2, i6 * 2, k11 * 2);
						int k13 = abyte3[i9 + j] & 0xff;
						if(k13 != 0)
						{
							anIntArray136[k3] = k13 - 1;
							anIntArray134[k3] = k4 + i6 / 2;
							anIntArray135[k3] = i10 + k11 / 2;
							k3++;
						}
					}
				}

			}
		}

		for(int l4 = 0; l4 < k3; l4++)
			if(aClass1_Sub1_Sub1_Sub2Array124[anIntArray136[l4]] != null)
				aClass1_Sub1_Sub1_Sub2Array124[anIntArray136[l4]].method46(anIntArray134[l4] - 7, anIntArray135[l4] - 7);

		if(anInt151 > 0)
		{
			for(int j5 = 0; j5 < k3; j5++)
				if(anIntArray136[j5] == anInt150)
				{
					aClass1_Sub1_Sub1_Sub2Array124[anIntArray136[j5]].method46(anIntArray134[j5] - 7, anIntArray135[j5] - 7);
					if(anInt151 % 10 < 5)
					{
						DrawingArea.method35(anIntArray134[j5], anIntArray135[j5], 15, 0xffff00, 128);
						DrawingArea.method35(anIntArray134[j5], anIntArray135[j5], 7, 0xffffff, 256);
					}
				}

		}
		if(aDouble169 == aDouble170 && aBoolean162)
		{
			for(int k5 = 0; k5 < anInt163; k5++)
			{
				int j6 = anIntArray166[k5];
				int l6 = anIntArray167[k5];
				j6 -= anInt111;
				l6 = (anInt112 + anInt114) - l6;
				int k7 = i1 + ((k1 - i1) * (j6 - i)) / (k - i);
				int j8 = j1 + ((l1 - j1) * (l6 - j)) / (l - j);
				int j9 = anIntArray168[k5];
				int j10 = 0xffffff;
				Sprite class1_sub1_sub1_sub1 = null;
				if(j9 == 0)
				{
					if(aDouble169 == 3D)
						class1_sub1_sub1_sub1 = aSprite_126;
					if(aDouble169 == 4D)
						class1_sub1_sub1_sub1 = aSprite_127;
					if(aDouble169 == 6D)
						class1_sub1_sub1_sub1 = aSprite_128;
					if(aDouble169 == 8D)
						class1_sub1_sub1_sub1 = aSprite_129;
				}
				if(j9 == 1)
				{
					if(aDouble169 == 3D)
						class1_sub1_sub1_sub1 = aSprite_128;
					if(aDouble169 == 4D)
						class1_sub1_sub1_sub1 = aSprite_129;
					if(aDouble169 == 6D)
						class1_sub1_sub1_sub1 = aSprite_130;
					if(aDouble169 == 8D)
						class1_sub1_sub1_sub1 = aSprite_131;
				}
				if(j9 == 2)
				{
					j10 = 0xffaa00;
					if(aDouble169 == 3D)
						class1_sub1_sub1_sub1 = aSprite_130;
					if(aDouble169 == 4D)
						class1_sub1_sub1_sub1 = aSprite_131;
					if(aDouble169 == 6D)
						class1_sub1_sub1_sub1 = aSprite_132;
					if(aDouble169 == 8D)
						class1_sub1_sub1_sub1 = aSprite_133;
				}
				if(class1_sub1_sub1_sub1 != null)
				{
					String s = aStringArray165[k5];
					int j12 = 1;
					for(int i13 = 0; i13 < s.length(); i13++)
						if(s.charAt(i13) == '/')
							j12++;

					j8 -= (class1_sub1_sub1_sub1.method40() * (j12 - 1)) / 2;
					j8 += class1_sub1_sub1_sub1.method44() / 2;
					do
					{
						int l13 = s.indexOf("/");
						if(l13 == -1)
						{
							class1_sub1_sub1_sub1.method38(s, k7, j8, j10, true);
							break;
						}
						String s1 = s.substring(0, l13);
						class1_sub1_sub1_sub1.method38(s1, k7, j8, j10, true);
						j8 += class1_sub1_sub1_sub1.method40();
						s = s.substring(l13 + 1);
					} while(true);
				}
			}

		}
		if(aBoolean102)
		{
			for(int l5 = anInt111 / 64; l5 < (anInt111 + anInt113) / 64; l5++)
			{
				for(int k6 = anInt112 / 64; k6 < (anInt112 + anInt114) / 64; k6++)
				{
					int i7 = l5 * 64;
					int l7 = k6 * 64;
					i7 -= anInt111;
					l7 = (anInt112 + anInt114) - l7;
					int k8 = i1 + ((k1 - i1) * (i7 - i)) / (k - i);
					int k9 = j1 + ((l1 - j1) * (l7 - 64 - j)) / (l - j);
					int k10 = i1 + ((k1 - i1) * ((i7 + 64) - i)) / (k - i);
					int j11 = j1 + ((l1 - j1) * (l7 - j)) / (l - j);
					DrawingArea.fillPixels(k8, k9, k10 - k8, j11 - k9, 0xffffff);
					aClass1_Sub1_Sub1_Sub4_125.method54(l5 + "_" + k6, k10 - 5, j11 - 5, 0xffffff);
					if(l5 == 33 && k6 >= 71 && k6 <= 73)
						aClass1_Sub1_Sub1_Sub4_125.method55("u_pass", (k10 + k8) / 2, (j11 + k9) / 2, 0xff0000);
				}

			}

		}
	}

	public void method20(int ai[], int i, int j, int k, int l, int i1, int j1, 
			int k1)
	{
		int l1 = DrawingArea.width - l;
		if(j1 == 9)
		{
			j1 = 1;
			k1 = k1 + 1 & 3;
		}
		if(j1 == 10)
		{
			j1 = 1;
			k1 = k1 + 3 & 3;
		}
		if(j1 == 11)
		{
			j1 = 8;
			k1 = k1 + 3 & 3;
		}
		if(j1 == 1)
		{
			if(k1 == 0)
			{
				for(int i2 = 0; i2 < i1; i2++)
				{
					for(int i10 = 0; i10 < l; i10++)
						if(i10 <= i2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j2 = i1 - 1; j2 >= 0; j2--)
				{
					for(int j10 = 0; j10 < l; j10++)
						if(j10 <= j2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k2 = 0; k2 < i1; k2++)
				{
					for(int k10 = 0; k10 < l; k10++)
						if(k10 >= k2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l2 = i1 - 1; l2 >= 0; l2--)
				{
					for(int l10 = 0; l10 < l; l10++)
						if(l10 >= l2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			} else
			{
				return;
			}
		}
		if(j1 == 2)
		{
			if(k1 == 0)
			{
				for(int i3 = i1 - 1; i3 >= 0; i3--)
				{
					for(int i11 = 0; i11 < l; i11++)
						if(i11 <= i3 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j3 = 0; j3 < i1; j3++)
				{
					for(int j11 = 0; j11 < l; j11++)
						if(j11 >= j3 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k3 = 0; k3 < i1; k3++)
				{
					for(int k11 = l - 1; k11 >= 0; k11--)
						if(k11 <= k3 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l3 = i1 - 1; l3 >= 0; l3--)
				{
					for(int l11 = l - 1; l11 >= 0; l11--)
						if(l11 >= l3 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			} else
			{
				return;
			}
		}
		if(j1 == 3)
		{
			if(k1 == 0)
			{
				for(int i4 = i1 - 1; i4 >= 0; i4--)
				{
					for(int i12 = l - 1; i12 >= 0; i12--)
						if(i12 <= i4 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j4 = i1 - 1; j4 >= 0; j4--)
				{
					for(int j12 = 0; j12 < l; j12++)
						if(j12 >= j4 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k4 = 0; k4 < i1; k4++)
				{
					for(int k12 = 0; k12 < l; k12++)
						if(k12 <= k4 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l4 = 0; l4 < i1; l4++)
				{
					for(int l12 = l - 1; l12 >= 0; l12--)
						if(l12 >= l4 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			} else
			{
				return;
			}
		}
		if(j1 == 4)
		{
			if(k1 == 0)
			{
				for(int i5 = i1 - 1; i5 >= 0; i5--)
				{
					for(int i13 = 0; i13 < l; i13++)
						if(i13 >= i5 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j5 = 0; j5 < i1; j5++)
				{
					for(int j13 = 0; j13 < l; j13++)
						if(j13 <= j5 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k5 = 0; k5 < i1; k5++)
				{
					for(int k13 = l - 1; k13 >= 0; k13--)
						if(k13 >= k5 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l5 = i1 - 1; l5 >= 0; l5--)
				{
					for(int l13 = l - 1; l13 >= 0; l13--)
						if(l13 <= l5 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			} else
			{
				return;
			}
		}
		if(j1 == 5)
		{
			if(k1 == 0)
			{
				for(int i6 = i1 - 1; i6 >= 0; i6--)
				{
					for(int i14 = l - 1; i14 >= 0; i14--)
						if(i14 >= i6 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j6 = i1 - 1; j6 >= 0; j6--)
				{
					for(int j14 = 0; j14 < l; j14++)
						if(j14 <= j6 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k6 = 0; k6 < i1; k6++)
				{
					for(int k14 = 0; k14 < l; k14++)
						if(k14 >= k6 >> 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l6 = 0; l6 < i1; l6++)
				{
					for(int l14 = l - 1; l14 >= 0; l14--)
						if(l14 <= l6 << 1)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			} else
			{
				return;
			}
		}
		if(j1 == 6)
		{
			if(k1 == 0)
			{
				for(int i7 = 0; i7 < i1; i7++)
				{
					for(int i15 = 0; i15 < l; i15++)
						if(i15 <= l / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j7 = 0; j7 < i1; j7++)
				{
					for(int j15 = 0; j15 < l; j15++)
						if(j7 <= i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k7 = 0; k7 < i1; k7++)
				{
					for(int k15 = 0; k15 < l; k15++)
						if(k15 >= l / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l7 = 0; l7 < i1; l7++)
				{
					for(int l15 = 0; l15 < l; l15++)
						if(l7 >= i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
		}
		if(j1 == 7)
		{
			if(k1 == 0)
			{
				for(int i8 = 0; i8 < i1; i8++)
				{
					for(int i16 = 0; i16 < l; i16++)
						if(i16 <= i8 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j8 = i1 - 1; j8 >= 0; j8--)
				{
					for(int j16 = 0; j16 < l; j16++)
						if(j16 <= j8 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k8 = i1 - 1; k8 >= 0; k8--)
				{
					for(int k16 = l - 1; k16 >= 0; k16--)
						if(k16 <= k8 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l8 = 0; l8 < i1; l8++)
				{
					for(int l16 = l - 1; l16 >= 0; l16--)
						if(l16 <= l8 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
		}
		if(j1 == 8)
		{
			if(k1 == 0)
			{
				for(int i9 = 0; i9 < i1; i9++)
				{
					for(int i17 = 0; i17 < l; i17++)
						if(i17 >= i9 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 1)
			{
				for(int j9 = i1 - 1; j9 >= 0; j9--)
				{
					for(int j17 = 0; j17 < l; j17++)
						if(j17 >= j9 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 2)
			{
				for(int k9 = i1 - 1; k9 >= 0; k9--)
				{
					for(int k17 = l - 1; k17 >= 0; k17--)
						if(k17 >= k9 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

				return;
			}
			if(k1 == 3)
			{
				for(int l9 = 0; l9 < i1; l9++)
				{
					for(int l17 = l - 1; l17 >= 0; l17--)
						if(l17 >= l9 - i1 / 2)
							ai[i++] = k;
						else
							ai[i++] = j;

					i += l1;
				}

			}
		}
	}

	public Class6 method21()
	{
		byte abyte0[] = null;
		String s = null;
		try
		{
			s = client.SignLink.findcachedir();
			abyte0 = FileOperations.ReadFile(s+"worldmap.dat");
			//if(!method26(abyte0))
			//	abyte0 = null;
			//if(abyte0 != null)
			return new Class6(abyte0);
		}
		catch(Throwable _ex) { }
		abyte0 = method22();
		if(s != null && abyte0 != null)
			try
			{
				method25(s + "/worldmap.dat", abyte0);
			}
			catch(Throwable _ex) { }
		return new Class6(abyte0);
	}

	public byte[] method22()
	{
		drawLoadingText(0, "Requesting map");
		try
		{
			String s = "";
			for(int i = 0; i < 10; i++)
				s = s + Class7.anIntArray100[i];

			DataInputStream datainputstream;
			if(super.rsFrame != null)
				datainputstream = new DataInputStream(new FileInputStream("worldmap.dat"));
			else
				datainputstream = new DataInputStream((new URL(getCodeBase(), "worldmap" + s + ".dat")).openStream());
			int j = 0;
			int k = 0;
			int l = 0x53901;
			byte abyte0[] = new byte[l];
			while(k < l) 
			{
				int i1 = l - k;
				if(i1 > 1000)
					i1 = 1000;
				int j1 = datainputstream.read(abyte0, k, i1);
				if(j1 < 0)
					throw new IOException("End of file");
				k += j1;
				int k1 = (k * 100) / l;
				if(k1 != j)
					drawLoadingText(k1, "Loading map - " + k1 + "%");
				j = k1;
			}
			datainputstream.close();
			return abyte0;
		}
		catch(IOException ioexception)
		{
			System.out.println("Error loading");
			ioexception.printStackTrace();
			return null;
		}
	}

	String homeDir = System.getProperty("user.home");
	String cacheDirName = "ROrs2 Cache";
	
	public String findcachedir()
	{
		String as[] = {
			homeDir+"/"+cacheDirName+"/"
		};
		for(int i = 0; i < as.length; i++)
			try
			{
				String s1 = as[i];
				s1 = "";
				String s2 = s1+"worldmap.dat";
				if(FileOperations.FileExists(s2)){
					return s1;
				}
			}	
			catch(Exception _ex) { }	
		return null;
	}

	public byte[] method24(String s)//Decompress something?
		throws IOException
	{
		File file = new File(s);
		if(!file.exists())
		{
			return null;
		} else
		{
			int i = (int)file.length();
			byte buffer[] = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(buffer, 0, i);
			datainputstream.close();
			return buffer;
		}
	}

	public void method25(String s, byte abyte0[])
		throws IOException
	{
		FileOutputStream fileoutputstream = new FileOutputStream(s);
		fileoutputstream.write(abyte0, 0, abyte0.length);
		fileoutputstream.close();
	}

	public boolean method26(byte abyte0[])
		throws Exception
	{
		if(abyte0 == null)
			return false;
		MessageDigest messagedigest = MessageDigest.getInstance("SHA");
		messagedigest.reset();
		messagedigest.update(abyte0);
		byte abyte1[] = messagedigest.digest();
		for(int i = 0; i < 20; i++)
			if(abyte1[i] != Class7.anIntArray100[i])
				return false;

		return true;
	}

	public Main()
	{
		anInt103 = 0x887755;
		anInt104 = 0x776644;
		anInt105 = 0x665533;
		anInt106 = 0xaa0000;
		anInt107 = 0x990000;
		anInt108 = 0x880000;
		aBoolean109 = true;
		aClass1_Sub1_Sub1_Sub3Array123 = new Class1_Sub1_Sub1_Sub3[100];
		aClass1_Sub1_Sub1_Sub2Array124 = new Class1_Sub1_Sub1_Sub2[100];
		anIntArray134 = new int[2000];
		anIntArray135 = new int[2000];
		anIntArray136 = new int[2000];
		anIntArray138 = new int[2000];
		anIntArray139 = new int[2000];
		anIntArray140 = new int[2000];
		anInt141 = 5;
		anInt142 = 13;
		anInt143 = 140;
		anInt144 = 470;
		aBoolean147 = false;
		anInt148 = -1;
		anInt149 = -1;
		anInt150 = -1;
		aBoolean156 = false;
		anInt164 = 1000;
		aStringArray165 = new String[anInt164];
		anIntArray166 = new int[anInt164];
		anIntArray167 = new int[anInt164];
		anIntArray168 = new int[anInt164];
		aDouble169 = 4D;
		aDouble170 = 4D;
	}

	public static boolean aBoolean102;
	public int anInt103;
	public int anInt104;
	public int anInt105;
	public int anInt106;
	public int anInt107;
	public int anInt108;
	public boolean aBoolean109;
	public int anInt110;
	public static int anInt111;
	public static int anInt112;
	public static int anInt113;
	public static int anInt114;
	public int anIntArray115[];
	public int anIntArray116[];
	public int anIntArrayArray117[][];
	public int anIntArrayArray118[][];
	public byte aByteArrayArray119[][];
	public byte aByteArrayArray120[][];
	public byte aByteArrayArray121[][];
	public byte aByteArrayArray122[][];
	public Class1_Sub1_Sub1_Sub3 aClass1_Sub1_Sub1_Sub3Array123[];
	public Class1_Sub1_Sub1_Sub2 aClass1_Sub1_Sub1_Sub2Array124[];
	public Class1_Sub1_Sub1_Sub4 aClass1_Sub1_Sub1_Sub4_125;
	public Sprite aSprite_126;
	public Sprite aSprite_127;
	public Sprite aSprite_128;
	public Sprite aSprite_129;
	public Sprite aSprite_130;
	public Sprite aSprite_131;
	public Sprite aSprite_132;
	public Sprite aSprite_133;
	public int anIntArray134[];
	public int anIntArray135[];
	public int anIntArray136[];
	public int anInt137;
	public int anIntArray138[];
	public int anIntArray139[];
	public int anIntArray140[];
	public int anInt141;
	public int anInt142;
	public int anInt143;
	public int anInt144;
	public int anInt145;
	public int anInt146;
	public boolean aBoolean147;
	public int anInt148;
	public int anInt149;
	public int anInt150;
	public int anInt151;
	public int anInt152;
	public int anInt153;
	public int anInt154;
	public int anInt155;
	public boolean aBoolean156;
	public Class1_Sub1_Sub1_Sub2 aClass1_Sub1_Sub1_Sub2_157;
	public int anInt158;
	public int anInt159;
	public int anInt160;
	public int anInt161;
	public static boolean aBoolean162 = true;
	public int anInt163;
	public int anInt164;
	public String aStringArray165[];
	public int anIntArray166[];
	public int anIntArray167[];
	public int anIntArray168[];
	public double aDouble169;
	public double aDouble170;
	public static int anInt171;
	public static int anInt172;
	public String iconExplainers[] = {
		"General Store", "Sword Shop", "Magic Shop", "Axe Shop", "Helmet Shop", "Bank", "Quest Start", "Amulet Shop", "Mining Site", "Furnace", 
		"Anvil", "Combat Training", "Dungeon", "Staff Shop", "Platebody Shop", "Platelegs Shop", "Scimitar Shop", "Archery Shop", "Shield Shop", "Altar", 
		"Herbalist", "Jewelery", "Gem Shop", "Crafting Shop", "Candle Shop", "Fishing Shop", "Fishing Spot", "Clothes Shop", "Apothecary", "Silk Trader", 
		"Kebab Seller", "Pub/Bar", "Mace Shop", "Tannery", "Rare Trees", "Spinning Wheel", "Food Shop", "Cookery Shop", "Mini-Game", "Water Source", 
		"Cooking Range", "Skirt Shop", "Potters Wheel", "Windmill", "Mining Shop", "Chainmail Shop", "Silver Shop", "Fur Trader", "Spice Shop", "Agility Training", 
		"Vegetable Store", "Slayer Master", "Hair Dressers", "Farming patch", "Makeover Mage", "Guide", "Transportation", "???", "Farming shop", "Loom", 
		"Brewery"
	};

}
