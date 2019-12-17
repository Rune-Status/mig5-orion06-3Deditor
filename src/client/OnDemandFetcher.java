package client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.zip.CRC32;
import java.util.zip.GZIPInputStream;
import java.io.*;

public final class OnDemandFetcher extends OnDemandFetcherParent implements Runnable {

   private int totalFiles;
   private final NodeList requested = new NodeList();
   private int anInt1332;
   public String statusString = "";
   private int writeLoopCycle;
   private long openSocketTime;
   private int[] mapIndices3;
   private final CRC32 crc32 = new CRC32();
   private final byte[] ioBuffer = new byte[500];
   public int onDemandCycle;
   //private final byte[][] fileStatus = new byte[4][];
   private Client clientInstance;
   private final NodeList aClass19_1344 = new NodeList();
   private int completedSize;
   private int expectedSize;
   private int[] anIntArray1348;
   public int anInt1349;
   private int[] mapIndices2;
   private int filesLoaded;
   private boolean running = true;
   private OutputStream outputStream;
   private int[] mapIndices4;
   private boolean waiting = false;
   private final NodeList aClass19_1358 = new NodeList();
   private final byte[] gzipInputBuffer = new byte['\ufde8'];
   private int[] anIntArray1360;
   private final NodeSubList nodeSubList = new NodeSubList();
   private InputStream inputStream;
   private Socket socket;
   //private final int[][] versions = new int[4][];
   private final int[][] crcs = new int[4][];
   private int uncompletedCount;
   private int completedCount;
   private final NodeList aClass19_1368 = new NodeList();
   private OnDemandData current;
   private final NodeList aClass19_1370 = new NodeList();
   private int[] mapIndices1;
   private byte[] modelIndices;
   private int loopCycle;


   private boolean crcMatches(int i, int j, byte[] abyte0) {
      /*if(abyte0 != null && abyte0.length >= 2) {
         int k = abyte0.length - 2;
         int l = ((abyte0[k] & 255) << 8) + (abyte0[k + 1] & 255);
         this.crc32.reset();
         this.crc32.update(abyte0, 0, k);
         int i1 = (int)this.crc32.getValue();
         return l == i && i1 == j;
      } else {
         return false;
      }*/
	  return true;
   }

   private void readData() {
      try {
         int ioexception = this.inputStream.available();
         int i1;
         int k1;
         if(this.expectedSize == 0 && ioexception >= 6) {
            this.waiting = true;

            int _ex;
            for(_ex = 0; _ex < 6; _ex += this.inputStream.read(this.ioBuffer, _ex, 6 - _ex)) {
               ;
            }

            _ex = this.ioBuffer[0] & 255;
            i1 = ((this.ioBuffer[1] & 255) << 8) + (this.ioBuffer[2] & 255);
            k1 = ((this.ioBuffer[3] & 255) << 8) + (this.ioBuffer[4] & 255);
            int i2 = this.ioBuffer[5] & 255;
            this.current = null;

            for(OnDemandData onDemandData = (OnDemandData)this.requested.reverseGetFirst(); onDemandData != null; onDemandData = (OnDemandData)this.requested.reverseGetNext()) {
               if(onDemandData.dataType == _ex && onDemandData.ID == i1) {
                  this.current = onDemandData;
               }

               if(this.current != null) {
                  onDemandData.loopCycle = 0;
               }
            }

            if(this.current != null) {
               this.loopCycle = 0;
               if(k1 == 0) {
                  SignLink.reporterror("Rej: " + _ex + "," + i1);
                  this.current.buffer = null;
                  if(this.current.incomplete) {
                     NodeList onDemandData1 = this.aClass19_1358;
                     synchronized(this.aClass19_1358) {
                        this.aClass19_1358.insertHead(this.current);
                     }
                  } else {
                     this.current.unlink();
                  }

                  this.current = null;
               } else {
                  if(this.current.buffer == null && i2 == 0) {
                     this.current.buffer = new byte[k1];
                  }

                  if(this.current.buffer == null && i2 != 0) {
                     throw new IOException("missing start of file");
                  }
               }
            }

            this.completedSize = i2 * 500;
            this.expectedSize = 500;
            if(this.expectedSize > k1 - i2 * 500) {
               this.expectedSize = k1 - i2 * 500;
            }
         }

         if(this.expectedSize > 0 && ioexception >= this.expectedSize) {
            this.waiting = true;
            byte[] _ex1 = this.ioBuffer;
            i1 = 0;
            if(this.current != null) {
               _ex1 = this.current.buffer;
               i1 = this.completedSize;
            }

            for(k1 = 0; k1 < this.expectedSize; k1 += this.inputStream.read(_ex1, k1 + i1, this.expectedSize - k1)) {
               ;
            }

            if(this.expectedSize + this.completedSize >= _ex1.length && this.current != null) {
               if(this.clientInstance.decompressors[0] != null) {
                  this.clientInstance.decompressors[this.current.dataType + 1].method234(_ex1.length, _ex1, this.current.ID);
               }

               if(!this.current.incomplete && this.current.dataType == 3) {
                  this.current.incomplete = true;
                  this.current.dataType = 93;
               }

               if(this.current.incomplete) {
                  NodeList k11 = this.aClass19_1358;
                  synchronized(this.aClass19_1358) {
                     this.aClass19_1358.insertHead(this.current);
                  }
               } else {
                  this.current.unlink();
               }
            }

            this.expectedSize = 0;
         }
      } catch (IOException var12) {
         try {
            this.socket.close();
         } catch (Exception var9) {
            ;
         }

         this.socket = null;
         this.inputStream = null;
         this.outputStream = null;
         this.expectedSize = 0;
      }

   }
   
   public void dumpMap() 
   {
		try 
		{
			for(int i = 0; i < clientInstance.decompressors[4].size(); i++)
			{
				byte[] data = clientInstance.decompressors[4].decompress(i);
				//FileOperations.WriteFile("C:\\Users\\Cody Pate\\Desktop\\RSPS\\2006Scape\\data\\world\\map\\" + i +".gz", data);
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
   }

   public void crcPack(int index, int index_length) {
		try {
		DataOutputStream crc_output = new DataOutputStream(new FileOutputStream("./"+index+"_crc"));
		DataOutputStream version_output = new DataOutputStream(new FileOutputStream("./"+index+"_version"));
		for(int j = 0; j < index_length; j++) {
			byte abyte0[];
			int crc = -1;
			int version = -1;
			try{
				abyte0 = clientInstance.decompressors[index].decompress(j); // grabs the data from the cache
				int k = abyte0.length - 2;
				version = ((abyte0[k] & 0xff) << 8) + (abyte0[k + 1] & 0xff);
				crc32.reset();
				crc32.update(abyte0, 0, k);
				crc = (int) crc32.getValue();
			}catch(Exception ex){}
			//System.out.println(j);//64921
			writeDWord(crc_output, crc); // writes the crc value
			version_output.writeShort(version); // writes the version value
			//System.out.println(version);
		}
		crc_output.close();
		version_output.close();
		} catch(Exception e) {
		}
	}
	
	public static void writeDWord(DataOutputStream dos, int i) {
		try {
			dos.writeByte((byte) (i >> 24));
			dos.writeByte((byte) (i >> 16));
			dos.writeByte((byte) (i >> 8));	
			dos.writeByte((byte) i);				
		} catch(IOException ioe){}
	}
   
   public void start(StreamLoader streamLoader, Client client1) {
      /*String[] as = new String[]{"model_version", "anim_version", "midi_version", "map_version"};

      byte[] abyte2;
      int j1;
      Stream stream2;
      int k2;
      for(int as1 = 0; as1 < 4; ++as1) {
         abyte2 = streamLoader.getDataForName(as[as1]);
         j1 = abyte2.length / 2;
         stream2 = new Stream(abyte2);
         this.versions[as1] = new int[j1];
         this.fileStatus[as1] = new byte[j1];

         for(k2 = 0; k2 < j1; ++k2) {
            this.versions[as1][k2] = stream2.readUnsignedWord();
         }
      }*/

      String[] var10 = new String[]{"model_crc", "anim_crc", "midi_crc", "map_crc"};
      int var13;
      for(int var11 = 0; var11 < 4; ++var11) {
         byte[] var12 = streamLoader.getDataForName(var10[var11]);
         var13 = var12.length / 4;
         Stream var14 = new Stream(var12);
         this.crcs[var11] = new int[var13];
         for(int l1 = 0; l1 < var13; ++l1) {
            this.crcs[var11][l1] = var14.readDWord();
         }
      }

	  byte[] abyte2;
	  int j1;
	  Stream stream2;
	  int k2;
      abyte2 = streamLoader.getDataForName("model_index");
      j1 = crcs[0].length;
      this.modelIndices = new byte[j1];

      for(var13 = 0; var13 < j1; ++var13) {
         if(var13 < abyte2.length) {
            this.modelIndices[var13] = abyte2[var13];
         } else {
            this.modelIndices[var13] = 0;
         }
      }

      abyte2 = streamLoader.getDataForName("map_index");
	 // FileOperations.WriteFile("C:\\Users\\Cody Pate\\Desktop\\RSPS\\2006Scape\\data\\world\\map_index", abyte2);
      stream2 = new Stream(abyte2);
      j1 = abyte2.length / 7;
      this.mapIndices1 = new int[j1];
      this.mapIndices2 = new int[j1];
      this.mapIndices3 = new int[j1];
      this.mapIndices4 = new int[j1];

      for(k2 = 0; k2 < j1; ++k2) {
         this.mapIndices1[k2] = stream2.readUnsignedWord();
         this.mapIndices2[k2] = stream2.readUnsignedWord();
         this.mapIndices3[k2] = stream2.readUnsignedWord();
         this.mapIndices4[k2] = stream2.readUnsignedByte();
      }

      abyte2 = streamLoader.getDataForName("anim_index");
      stream2 = new Stream(abyte2);
      j1 = abyte2.length / 2;
      this.anIntArray1360 = new int[j1];

      for(k2 = 0; k2 < j1; ++k2) {
         this.anIntArray1360[k2] = stream2.readUnsignedWord();
      }

      abyte2 = streamLoader.getDataForName("midi_index");
      stream2 = new Stream(abyte2);
      j1 = abyte2.length;
      this.anIntArray1348 = new int[j1];

      for(k2 = 0; k2 < j1; ++k2) {
         this.anIntArray1348[k2] = stream2.readUnsignedByte();
      }

      this.clientInstance = client1;
      this.running = true;
      this.clientInstance.startRunnable(this, 2);
   }

   public int getNodeCount() {
      NodeSubList var1 = this.nodeSubList;
      synchronized(this.nodeSubList) {
         return this.nodeSubList.getNodeCount();
      }
   }

   public void disable() {
      this.running = false;
   }

   public void method554(boolean flag) {
      int j = this.mapIndices1.length;

      for(int k = 0; k < j; ++k) {
         if(flag || this.mapIndices4[k] != 0) {
            this.method563((byte)2, 3, this.mapIndices3[k]);
            this.method563((byte)2, 3, this.mapIndices2[k]);
         }
      }

   }

   public int getVersionCount(int j) {//14927,19069,72,18914
	/*if(j == 0)
		return 14999;//model count
		*/
		
     // return this.versions[j].length;
	 return this.crcs[j].length;
   }

   private void closeRequest(OnDemandData onDemandData) {
      try {
         if(this.socket == null) {
            long _ex = System.currentTimeMillis();
            if(_ex - this.openSocketTime < 4000L) {
               return;
            }

            this.openSocketTime = _ex;
            this.socket = this.clientInstance.openSocket(43594 + Client.portOff);
            this.inputStream = this.socket.getInputStream();
            this.outputStream = this.socket.getOutputStream();
            this.outputStream.write(15);

            for(int j = 0; j < 8; ++j) {
               this.inputStream.read();
            }

            this.loopCycle = 0;
         }

         this.ioBuffer[0] = (byte)onDemandData.dataType;
         this.ioBuffer[1] = (byte)(onDemandData.ID >> 8);
         this.ioBuffer[2] = (byte)onDemandData.ID;
         if(onDemandData.incomplete) {
            this.ioBuffer[3] = 2;
         } else if(!this.clientInstance.loggedIn) {
            this.ioBuffer[3] = 1;
         } else {
            this.ioBuffer[3] = 0;
         }

         this.outputStream.write(this.ioBuffer, 0, 4);
         this.writeLoopCycle = 0;
         this.anInt1349 = -10000;
      } catch (IOException var6) {
         try {
            this.socket.close();
         } catch (Exception var5) {
            ;
         }

         this.socket = null;
         this.inputStream = null;
         this.outputStream = null;
         this.expectedSize = 0;
         ++this.anInt1349;
      }
   }

   public int getAnimCount() {
      return this.anIntArray1360.length;
   }

   /*public void method558(int i, int j) {
      if(i >= 0 && i <= this.versions.length && j >= 0 && j <= this.versions[i].length) {
         if(this.versions[i][j] != 0) {
            NodeSubList var3 = this.nodeSubList;
            synchronized(this.nodeSubList) {
               OnDemandData onDemandData_1;
               for(onDemandData_1 = (OnDemandData)this.nodeSubList.reverseGetFirst(); onDemandData_1 != null; onDemandData_1 = (OnDemandData)this.nodeSubList.reverseGetNext()) {
                  if(onDemandData_1.dataType == i && onDemandData_1.ID == j) {
                     return;
                  }
               }

               onDemandData_1 = new OnDemandData();
               onDemandData_1.dataType = i;
               onDemandData_1.ID = j;
               onDemandData_1.incomplete = true;
               NodeList var5 = this.aClass19_1370;
               synchronized(this.aClass19_1370) {
                  this.aClass19_1370.insertHead(onDemandData_1);
               }

               this.nodeSubList.insertHead(onDemandData_1);
            }
         }
      }
   }*/
   
   public void method558(int i, int j)
	{
		/*if(i < 0 || i > versions.length || j < 0 || j > versions[i].length)
			return;
		if(versions[i][j] == 0)
			return;*/
		synchronized(nodeSubList)
		{
			for(OnDemandData onDemandData = (OnDemandData) nodeSubList.reverseGetFirst(); onDemandData != null; onDemandData = (OnDemandData) nodeSubList.reverseGetNext())
				if(onDemandData.dataType == i && onDemandData.ID == j)
					return;

			OnDemandData onDemandData_1 = new OnDemandData();
			onDemandData_1.dataType = i;
			onDemandData_1.ID = j;
			onDemandData_1.incomplete = true;
			synchronized(aClass19_1370)
			{
				aClass19_1370.insertHead(onDemandData_1);
			}
			nodeSubList.insertHead(onDemandData_1);
		}
	}

   public int getModelIndex(int i) {
      return this.modelIndices[i] & 255;
	  //return 19069;
   }

   public void run() {
      while(true) {
         try {
            if(this.running) {
               ++this.onDemandCycle;
               byte exception = 20;
               if(this.anInt1332 == 0 && this.clientInstance.decompressors[0] != null) {
                  exception = 50;
               }

               try {
                  Thread.sleep((long)exception);
               } catch (Exception var6) {
                  ;
               }

               this.waiting = true;

               for(int flag = 0; flag < 100 && this.waiting; ++flag) {
                  this.waiting = false;
                  this.checkReceived();
                  this.handleFailed();
                  if(this.uncompletedCount == 0 && flag >= 5) {
                     break;
                  }

                  this.method568();
                  if(this.inputStream != null) {
                     this.readData();
                  }
               }

               boolean var8 = false;

               OnDemandData _ex;
               for(_ex = (OnDemandData)this.requested.reverseGetFirst(); _ex != null; _ex = (OnDemandData)this.requested.reverseGetNext()) {
                  if(_ex.incomplete) {
                     var8 = true;
                     ++_ex.loopCycle;
                     if(_ex.loopCycle > 50) {
                        _ex.loopCycle = 0;
                        this.closeRequest(_ex);
                     }
                  }
               }

               if(!var8) {
                  for(_ex = (OnDemandData)this.requested.reverseGetFirst(); _ex != null; _ex = (OnDemandData)this.requested.reverseGetNext()) {
                     var8 = true;
                     ++_ex.loopCycle;
                     if(_ex.loopCycle > 50) {
                        _ex.loopCycle = 0;
                        this.closeRequest(_ex);
                     }
                  }
               }

               if(var8) {
                  ++this.loopCycle;
                  if(this.loopCycle > 750) {
                     try {
                        this.socket.close();
                     } catch (Exception var5) {
                        ;
                     }

                     this.socket = null;
                     this.inputStream = null;
                     this.outputStream = null;
                     this.expectedSize = 0;
                  }
               } else {
                  this.loopCycle = 0;
                  this.statusString = "";
               }

               if(!this.clientInstance.loggedIn || this.socket == null || this.outputStream == null || this.anInt1332 <= 0 && this.clientInstance.decompressors[0] != null) {
                  continue;
               }

               ++this.writeLoopCycle;
               if(this.writeLoopCycle <= 500) {
                  continue;
               }

               this.writeLoopCycle = 0;
               this.ioBuffer[0] = 0;
               this.ioBuffer[1] = 0;
               this.ioBuffer[2] = 0;
               this.ioBuffer[3] = 10;

               try {
                  this.outputStream.write(this.ioBuffer, 0, 4);
               } catch (IOException var4) {
                  this.loopCycle = 5000;
               }
               continue;
            }
         } catch (Exception var7) {
            SignLink.reporterror("od_ex " + var7.getMessage());
         }

         return;
      }
   }

   /*public void method560(int i, int j) {
      if(this.clientInstance.decompressors[0] != null) {
         if(this.versions[j][i] != 0) {
            if(this.fileStatus[j][i] != 0) {
               if(this.anInt1332 != 0) {
                  OnDemandData onDemandData = new OnDemandData();
                  onDemandData.dataType = j;
                  onDemandData.ID = i;
                  onDemandData.incomplete = false;
                  NodeList var4 = this.aClass19_1344;
                  synchronized(this.aClass19_1344) {
                     this.aClass19_1344.insertHead(onDemandData);
                  }
               }
            }
         }
      }
   }*/
   
   public void method560(int i, int j)
	{
		if(clientInstance.decompressors[0] == null)
			return;
		/*if(versions[j][i] == 0)
			return;
		if(fileStatus[j][i] == 0)
			return;*/
		if(anInt1332 == 0)
			return;
		OnDemandData onDemandData = new OnDemandData();
		onDemandData.dataType = j;
		onDemandData.ID = i;
		onDemandData.incomplete = false;
		synchronized(aClass19_1344)
		{
			aClass19_1344.insertHead(onDemandData);
		}
	}

   public OnDemandData getNextNode() {
      NodeList i = this.aClass19_1358;
      OnDemandData onDemandData;
      synchronized(this.aClass19_1358) {
         onDemandData = (OnDemandData)this.aClass19_1358.popHead();
      }

      if(onDemandData == null) {
         return null;
      } else {
         NodeSubList i1 = this.nodeSubList;
         synchronized(this.nodeSubList) {
            onDemandData.unlinkSub();
         }

         if(onDemandData.buffer == null) {
            return onDemandData;
         } else {
            int i2 = 0;

            try {
               GZIPInputStream _ex = new GZIPInputStream(new ByteArrayInputStream(onDemandData.buffer));

               while(true) {
                  if(i2 == this.gzipInputBuffer.length) {
                     throw new RuntimeException("buffer overflow!");
                  }

                  int k = _ex.read(this.gzipInputBuffer, i2, this.gzipInputBuffer.length - i2);
                  if(k == -1) {
                     break;
                  }

                  i2 += k;
               }
            } catch (IOException var7) {
               throw new RuntimeException("error unzipping");
            }

            onDemandData.buffer = new byte[i2];
            System.arraycopy(this.gzipInputBuffer, 0, onDemandData.buffer, 0, i2);
            return onDemandData;
         }
      }
   }

   public int method562(int i, int k, int l) {
      int i1 = (l << 8) + k;

      for(int j1 = 0; j1 < this.mapIndices1.length; ++j1) {
         if(this.mapIndices1[j1] == i1) {
            if(i == 0) {
               return this.mapIndices2[j1];
            }

            return this.mapIndices3[j1];
         }
      }

      return -1;
   }

   public void method548(int i) {
      this.method558(0, i);
   }

   /*public void method563(byte byte0, int i, int j) {
      if(this.clientInstance.decompressors[0] != null) {
         if(this.versions[i][j] != 0) {
            byte[] abyte0 = this.clientInstance.decompressors[i + 1].decompress(j);
            if(!this.crcMatches(this.versions[i][j], this.crcs[i][j], abyte0)) {
               this.fileStatus[i][j] = byte0;
               if(byte0 > this.anInt1332) {
                  this.anInt1332 = byte0;
               }

               ++this.totalFiles;
            }
         }
      }
   }*/
   
   public void method563(byte byte0, int i, int j)
	{
		if(clientInstance.decompressors[0] == null)
			return;
		/*if(versions[i][j] == 0)
			return;*/
		byte abyte0[] = clientInstance.decompressors[i + 1].decompress(j);
		//if(crcMatches(versions[i][j], crcs[i][j], abyte0))
			return;
		//fileStatus[i][j] = byte0;
		/*if(byte0 > anInt1332)
			anInt1332 = byte0;
		totalFiles++;*/
	}

   public boolean method564(int i) {
      for(int k = 0; k < this.mapIndices1.length; ++k) {
         if(this.mapIndices3[k] == i) {
            return true;
         }
      }

      return false;
   }

   private void handleFailed() {
      this.uncompletedCount = 0;
      this.completedCount = 0;

      OnDemandData onDemandData_1;
      for(onDemandData_1 = (OnDemandData)this.requested.reverseGetFirst(); onDemandData_1 != null; onDemandData_1 = (OnDemandData)this.requested.reverseGetNext()) {
         if(onDemandData_1.incomplete) {
            ++this.uncompletedCount;
         } else {
            ++this.completedCount;
         }
      }

      while(this.uncompletedCount < 10) {
         onDemandData_1 = (OnDemandData)this.aClass19_1368.popHead();
         if(onDemandData_1 == null) {
            break;
         }

         /*if(this.fileStatus[onDemandData_1.dataType][onDemandData_1.ID] != 0) {
            ++this.filesLoaded;
         }

         this.fileStatus[onDemandData_1.dataType][onDemandData_1.ID] = 0;*/
         this.requested.insertHead(onDemandData_1);
         ++this.uncompletedCount;
         this.closeRequest(onDemandData_1);
         this.waiting = true;
      }

   }

   public void method566() {
      NodeList var1 = this.aClass19_1344;
      synchronized(this.aClass19_1344) {
         this.aClass19_1344.removeAll();
      }
   }

   private void checkReceived() {
      NodeList abyte0 = this.aClass19_1370;
      OnDemandData onDemandData;
      synchronized(this.aClass19_1370) {
         onDemandData = (OnDemandData)this.aClass19_1370.popHead();
      }

      while(onDemandData != null) {
         this.waiting = true;
         byte[] abyte01 = null;
         if(this.clientInstance.decompressors[0] != null) {
            abyte01 = this.clientInstance.decompressors[onDemandData.dataType + 1].decompress(onDemandData.ID);
         }

         /*if(!this.crcMatches(this.versions[onDemandData.dataType][onDemandData.ID], this.crcs[onDemandData.dataType][onDemandData.ID], abyte01)) {
            abyte01 = null;
         }*/

         NodeList var3 = this.aClass19_1370;
         synchronized(this.aClass19_1370) {
            if(abyte01 == null) {
               this.aClass19_1368.insertHead(onDemandData);
            } else {
               onDemandData.buffer = abyte01;
               NodeList var4 = this.aClass19_1358;
               synchronized(this.aClass19_1358) {
                  this.aClass19_1358.insertHead(onDemandData);
               }
            }

            onDemandData = (OnDemandData)this.aClass19_1370.popHead();
         }
      }

   }

   /*private void method568() {
      while(this.uncompletedCount == 0 && this.completedCount < 10 && this.anInt1332 != 0) {
         NodeList j = this.aClass19_1344;
         OnDemandData onDemandData;
         synchronized(this.aClass19_1344) {
            onDemandData = (OnDemandData)this.aClass19_1344.popHead();
         }

         while(onDemandData != null) {
            if(this.fileStatus[onDemandData.dataType][onDemandData.ID] != 0) {
               this.fileStatus[onDemandData.dataType][onDemandData.ID] = 0;
               this.requested.insertHead(onDemandData);
               this.closeRequest(onDemandData);
               this.waiting = true;
               if(this.filesLoaded < this.totalFiles) {
                  ++this.filesLoaded;
               }

               this.statusString = "Loading extra files - " + this.filesLoaded * 100 / this.totalFiles + "%";
               ++this.completedCount;
               if(this.completedCount == 10) {
                  return;
               }
            }

            j = this.aClass19_1344;
            synchronized(this.aClass19_1344) {
               onDemandData = (OnDemandData)this.aClass19_1344.popHead();
            }
         }

         for(int var9 = 0; var9 < 4; ++var9) {
            byte[] abyte0 = this.fileStatus[var9];
            int k = abyte0.length;

            for(int l = 0; l < k; ++l) {
               if(abyte0[l] == this.anInt1332) {
                  abyte0[l] = 0;
                  OnDemandData onDemandData_1 = new OnDemandData();
                  onDemandData_1.dataType = var9;
                  onDemandData_1.ID = l;
                  onDemandData_1.incomplete = false;
                  this.requested.insertHead(onDemandData_1);
                  this.closeRequest(onDemandData_1);
                  this.waiting = true;
                  if(this.filesLoaded < this.totalFiles) {
                     ++this.filesLoaded;
                  }

                  this.statusString = "Loading extra files - " + this.filesLoaded * 100 / this.totalFiles + "%";
                  ++this.completedCount;
                  if(this.completedCount == 10) {
                     return;
                  }
               }
            }
         }

         --this.anInt1332;
      }

   }*/
   
   private void method568()
	{
		while(uncompletedCount == 0 && completedCount < 10)
		{
			if(anInt1332 == 0)
				break;
			OnDemandData onDemandData;
			synchronized(aClass19_1344)
			{
				onDemandData = (OnDemandData)aClass19_1344.popHead();
			}
			while(onDemandData != null)
			{
				/*if(fileStatus[onDemandData.dataType][onDemandData.ID] != 0)
				{
					fileStatus[onDemandData.dataType][onDemandData.ID] = 0;
					requested.insertHead(onDemandData);
					closeRequest(onDemandData);
					waiting = true;
					if(filesLoaded < totalFiles)
						filesLoaded++;
					statusString = "Loading extra files - " + (filesLoaded * 100) / totalFiles + "%";
					completedCount++;
					if(completedCount == 10)
						return;
				}*/
				synchronized(aClass19_1344)
				{
					onDemandData = (OnDemandData)aClass19_1344.popHead();
				}
			}
			/*for(int j = 0; j < 4; j++)
			{
				byte abyte0[] = fileStatus[j];
				int k = abyte0.length;
				for(int l = 0; l < k; l++)
					if(abyte0[l] == anInt1332)
					{
						abyte0[l] = 0;
						OnDemandData onDemandData_1 = new OnDemandData();
						onDemandData_1.dataType = j;
						onDemandData_1.ID = l;
						onDemandData_1.incomplete = false;
						requested.insertHead(onDemandData_1);
						closeRequest(onDemandData_1);
						waiting = true;
						if(filesLoaded < totalFiles)
							filesLoaded++;
						statusString = "Loading extra files - " + (filesLoaded * 100) / totalFiles + "%";
						completedCount++;
						if(completedCount == 10)
							return;
					}

			}*/

			anInt1332--;
		}
	}

   public boolean method569(int i) {
      return this.anIntArray1348[i] == 1;
   }

}
