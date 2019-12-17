package client;

import java.math.BigInteger;

public final class Stream extends NodeSub {

   private static final BigInteger RSA_MODULUS = new BigInteger("127781388071271697420995438797342798577038527738411377704952187192069132205125196590060096061100544373705094344556419651411803829425763820284644751473527791521582213046531077410742050195888478121492919430507221831570005598095180827548274975032806228483658829404178006178450828935661274551573366773077869159833");
   private static final BigInteger RSA_EXPONENT = new BigInteger("65537");
   public byte[] buffer;
   public int currentOffset;
   public int bitPosition;
   private static final int[] anIntArray1409 = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, '\uffff', 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1};
   public ISAACRandomGen encryption;
   private static int anInt1412;
   private static final NodeList nodeList = new NodeList();


   public static Stream create() {
      NodeList stream_1 = nodeList;
      synchronized(nodeList) {
         Stream stream = null;
         if(anInt1412 > 0) {
            --anInt1412;
            stream = (Stream)nodeList.popHead();
         }

         if(stream != null) {
            stream.currentOffset = 0;
            return stream;
         }
      }

      Stream stream_11 = new Stream();
      stream_11.currentOffset = 0;
      stream_11.buffer = new byte[5000];
      return stream_11;
   }

   private Stream() {}

   public Stream(byte[] abyte0) {
      this.buffer = abyte0;
      this.currentOffset = 0;
   }

   public void createFrame(int i) {
      this.buffer[this.currentOffset++] = (byte)(i + this.encryption.getNextKey());
   }

   public void writeWordBigEndian(int i) {
      this.buffer[this.currentOffset++] = (byte)i;
   }

   public void writeWord(int i) {
      this.buffer[this.currentOffset++] = (byte)(i >> 8);
      this.buffer[this.currentOffset++] = (byte)i;
   }

   public void method400(int i) {
      this.buffer[this.currentOffset++] = (byte)i;
      this.buffer[this.currentOffset++] = (byte)(i >> 8);
   }

   public void writeDWordBigEndian(int i) {
      this.buffer[this.currentOffset++] = (byte)(i >> 16);
      this.buffer[this.currentOffset++] = (byte)(i >> 8);
      this.buffer[this.currentOffset++] = (byte)i;
   }

   public void writeDWord(int i) {
      this.buffer[this.currentOffset++] = (byte)(i >> 24);
      this.buffer[this.currentOffset++] = (byte)(i >> 16);
      this.buffer[this.currentOffset++] = (byte)(i >> 8);
      this.buffer[this.currentOffset++] = (byte)i;
   }

   public void method403(int j) {
      this.buffer[this.currentOffset++] = (byte)j;
      this.buffer[this.currentOffset++] = (byte)(j >> 8);
      this.buffer[this.currentOffset++] = (byte)(j >> 16);
      this.buffer[this.currentOffset++] = (byte)(j >> 24);
   }

   public void writeQWord(long l) {
      try {
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 56));
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 48));
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 40));
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 32));
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 24));
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 16));
         this.buffer[this.currentOffset++] = (byte)((int)(l >> 8));
         this.buffer[this.currentOffset++] = (byte)((int)l);
      } catch (RuntimeException var4) {
         SignLink.reporterror("14395, 5, " + l + ", " + var4.toString());
         throw new RuntimeException();
      }
   }

   public void writeString(String s) {
      System.arraycopy(s.getBytes(), 0, this.buffer, this.currentOffset, s.length());
      this.currentOffset += s.length();
      this.buffer[this.currentOffset++] = 10;
   }

   public void writeBytes(byte[] abyte0, int i, int j) {
      for(int k = j; k < j + i; ++k) {
         this.buffer[this.currentOffset++] = abyte0[k];
      }

   }

   public void writeBytes(int i) {
      this.buffer[this.currentOffset - i - 1] = (byte)i;
   }

   public int readUnsignedByte() {
      return this.buffer[this.currentOffset++] & 255;
   }
   
   public void writeUnsignedByte(int i) {
		this.buffer[this.currentOffset++] = (byte) (i & 0xff);
	}

	public void writeSignedByte(int i) {
		this.buffer[this.currentOffset++] = (byte)i;
	}
	
   public byte readSignedByte() {
      return this.buffer[this.currentOffset++];
   }

   public int readUnsignedWord() {
      this.currentOffset += 2;
      return ((this.buffer[this.currentOffset - 2] & 255) << 8) + (this.buffer[this.currentOffset - 1] & 255);
   }

   public int readSignedWord() {
      this.currentOffset += 2;
      int i = ((this.buffer[this.currentOffset - 2] & 255) << 8) + (this.buffer[this.currentOffset - 1] & 255);
      if(i > 32767) {
         i -= 65536;
      }

      return i;
   }

   public int read3Bytes() {
      this.currentOffset += 3;
      return ((this.buffer[this.currentOffset - 3] & 255) << 16) + ((this.buffer[this.currentOffset - 2] & 255) << 8) + (this.buffer[this.currentOffset - 1] & 255);
   }

   public int readDWord() {
      this.currentOffset += 4;
      return ((this.buffer[this.currentOffset - 4] & 255) << 24) + ((this.buffer[this.currentOffset - 3] & 255) << 16) + ((this.buffer[this.currentOffset - 2] & 255) << 8) + (this.buffer[this.currentOffset - 1] & 255);
   }

   public long readQWord() {
      long l = (long)this.readDWord() & 4294967295L;
      long l1 = (long)this.readDWord() & 4294967295L;
      return (l << 32) + l1;
   }

   public String readString() {
      int i = this.currentOffset;

      while(this.buffer[this.currentOffset++] != 10) {
         ;
      }

      return new String(this.buffer, i, this.currentOffset - i - 1);
   }

   public byte[] readBytes() {
      int i = this.currentOffset;

      while(this.buffer[this.currentOffset++] != 10) {
         ;
      }

      byte[] abyte0 = new byte[this.currentOffset - i - 1];
      System.arraycopy(this.buffer, i, abyte0, i - i, this.currentOffset - 1 - i);
      return abyte0;
   }

   public void readBytes(int i, int j, byte[] abyte0) {
      for(int l = j; l < j + i; ++l) {
         abyte0[l] = this.buffer[this.currentOffset++];
      }

   }

   public void initBitAccess() {
      this.bitPosition = this.currentOffset * 8;
   }

   public int readBits(int i) {
      int k = this.bitPosition >> 3;
      int l = 8 - (this.bitPosition & 7);
      int i1 = 0;

      for(this.bitPosition += i; i > l; l = 8) {
         i1 += (this.buffer[k++] & anIntArray1409[l]) << i - l;
         i -= l;
      }

      if(i == l) {
         i1 += this.buffer[k] & anIntArray1409[l];
      } else {
         i1 += this.buffer[k] >> l - i & anIntArray1409[i];
      }

      return i1;
   }

   public void finishBitAccess() {
      this.currentOffset = (this.bitPosition + 7) / 8;
   }

   public int method421() {
      int i = this.buffer[this.currentOffset] & 255;
      return i < 128?this.readUnsignedByte() - 64:this.readUnsignedWord() - '\uc000';
   }

   public int method422() {
      int i = this.buffer[this.currentOffset] & 255;
      return i < 128?this.readUnsignedByte():this.readUnsignedWord() - '\u8000';
   }

   public void doKeys() {
      int i = this.currentOffset;
      this.currentOffset = 0;
      byte[] abyte0 = new byte[i];
      this.readBytes(i, 0, abyte0);
      BigInteger biginteger2 = new BigInteger(abyte0);
      byte[] abyte1 = biginteger2.toByteArray();
      this.currentOffset = 0;
      this.writeWordBigEndian(abyte1.length);
      this.writeBytes(abyte1, abyte1.length, 0);
   }

   public void method424(int i) {
      this.buffer[this.currentOffset++] = (byte)(-i);
   }

   public void method425(int j) {
      this.buffer[this.currentOffset++] = (byte)(128 - j);
   }

   public int method426() {
      return this.buffer[this.currentOffset++] - 128 & 255;
   }

   public int method427() {
      return -this.buffer[this.currentOffset++] & 255;
   }

   public int method428() {
      return 128 - this.buffer[this.currentOffset++] & 255;
   }

   public byte method429() {
      return (byte)(-this.buffer[this.currentOffset++]);
   }

   public byte method430() {
      return (byte)(128 - this.buffer[this.currentOffset++]);
   }

   public void method431(int i) {
      this.buffer[this.currentOffset++] = (byte)i;
      this.buffer[this.currentOffset++] = (byte)(i >> 8);
   }

   public void method432(int j) {
      this.buffer[this.currentOffset++] = (byte)(j >> 8);
      this.buffer[this.currentOffset++] = (byte)(j + 128);
   }

   public void method433(int j) {
      this.buffer[this.currentOffset++] = (byte)(j + 128);
      this.buffer[this.currentOffset++] = (byte)(j >> 8);
   }

   public int method434() {
      this.currentOffset += 2;
      return ((this.buffer[this.currentOffset - 1] & 255) << 8) + (this.buffer[this.currentOffset - 2] & 255);
   }

   public int method435() {
      this.currentOffset += 2;
      return ((this.buffer[this.currentOffset - 2] & 255) << 8) + (this.buffer[this.currentOffset - 1] - 128 & 255);
   }

   public int method436() {
      this.currentOffset += 2;
      return ((this.buffer[this.currentOffset - 1] & 255) << 8) + (this.buffer[this.currentOffset - 2] - 128 & 255);
   }

   public int method437() {
      this.currentOffset += 2;
      int j = ((this.buffer[this.currentOffset - 1] & 255) << 8) + (this.buffer[this.currentOffset - 2] & 255);
      if(j > 32767) {
         j -= 65536;
      }

      return j;
   }

   public int method438() {
      this.currentOffset += 2;
      int j = ((this.buffer[this.currentOffset - 1] & 255) << 8) + (this.buffer[this.currentOffset - 2] - 128 & 255);
      if(j > 32767) {
         j -= 65536;
      }

      return j;
   }

   public int method439() {
      this.currentOffset += 4;
      return ((this.buffer[this.currentOffset - 2] & 255) << 24) + ((this.buffer[this.currentOffset - 1] & 255) << 16) + ((this.buffer[this.currentOffset - 4] & 255) << 8) + (this.buffer[this.currentOffset - 3] & 255);
   }

   public int method440() {
      this.currentOffset += 4;
      return ((this.buffer[this.currentOffset - 3] & 255) << 24) + ((this.buffer[this.currentOffset - 4] & 255) << 16) + ((this.buffer[this.currentOffset - 1] & 255) << 8) + (this.buffer[this.currentOffset - 2] & 255);
   }

   public void method441(int i, byte[] abyte0, int j) {
      for(int k = i + j - 1; k >= i; --k) {
         this.buffer[this.currentOffset++] = (byte)(abyte0[k] + 128);
      }

   }

   public void method442(int i, int j, byte[] abyte0) {
      for(int k = j + i - 1; k >= j; --k) {
         abyte0[k] = this.buffer[this.currentOffset++];
      }

   }

}
