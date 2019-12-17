package client;

import java.io.IOException;
import java.io.RandomAccessFile;

final class Decompressor {

   private static final byte[] buffer = new byte[520];
   private final RandomAccessFile dataFile;
   private final RandomAccessFile indexFile;
   private final int anInt311;


   public Decompressor(RandomAccessFile randomaccessfile, RandomAccessFile randomaccessfile1, int j) {
      this.anInt311 = j;
      this.dataFile = randomaccessfile;
      this.indexFile = randomaccessfile1;
   }
   
   	public int size() {
		try {
			return (int) (indexFile.length() / 6L);
		} catch (IOException ex) {
		}
		return 0;
	}

   public synchronized byte[] decompress(int i) {
      try {
         this.seekTo(this.indexFile, i * 6);

         int _ex;
         int i1;
         for(i1 = 0; i1 < 6; i1 += _ex) {
            _ex = this.indexFile.read(buffer, i1, 6 - i1);
            if(_ex == -1) {
               return null;
            }
         }

         i1 = ((buffer[0] & 255) << 16) + ((buffer[1] & 255) << 8) + (buffer[2] & 255);
         int j1 = ((buffer[3] & 255) << 16) + ((buffer[4] & 255) << 8) + (buffer[5] & 255);
         if(j1 > 0 && (long)j1 <= this.dataFile.length() / 520L) {
            byte[] abyte0 = new byte[i1];
            int k1 = 0;
            int l1 = 0;

            while(k1 < i1) {
               if(j1 == 0) {
                  return null;
               }

               this.seekTo(this.dataFile, j1 * 520);
               int k = 0;
               int i2 = i1 - k1;
               if(i2 > 512) {
                  i2 = 512;
               }

               while(k < i2 + 8) {
                  int j2 = this.dataFile.read(buffer, k, i2 + 8 - k);
                  if(j2 == -1) {
                     return null;
                  }

                  k += j2;
               }

               int k2 = ((buffer[0] & 255) << 8) + (buffer[1] & 255);
               int l2 = ((buffer[2] & 255) << 8) + (buffer[3] & 255);
               int i3 = ((buffer[4] & 255) << 16) + ((buffer[5] & 255) << 8) + (buffer[6] & 255);
               int j3 = buffer[7] & 255;
               if(k2 == i && l2 == l1 && j3 == this.anInt311) {
                  if(i3 >= 0 && (long)i3 <= this.dataFile.length() / 520L) {
                     for(int k3 = 0; k3 < i2; ++k3) {
                        abyte0[k1++] = buffer[k3 + 8];
                     }

                     j1 = i3;
                     ++l1;
                     continue;
                  }

                  return null;
               }

               return null;
            }

            return abyte0;
         } else {
            return null;
         }
      } catch (IOException var16) {
         return null;
      }
   }

   public synchronized boolean method234(int i, byte[] abyte0, int j) {
      boolean flag = this.method235(true, j, i, abyte0);
      if(!flag) {
         flag = this.method235(false, j, i, abyte0);
      }

      return flag;
   }

   private synchronized boolean method235(boolean flag, int j, int k, byte[] abyte0) {
      try {
         int _ex;
         int j1;
         int l1;
         if(flag) {
            this.seekTo(this.indexFile, j * 6);

            for(l1 = 0; l1 < 6; l1 += j1) {
               j1 = this.indexFile.read(buffer, l1, 6 - l1);
               if(j1 == -1) {
                  return false;
               }
            }

            _ex = ((buffer[3] & 255) << 16) + ((buffer[4] & 255) << 8) + (buffer[5] & 255);
            if(_ex <= 0 || (long)_ex > this.dataFile.length() / 520L) {
               return false;
            }
         } else {
            _ex = (int)((this.dataFile.length() + 519L) / 520L);
            if(_ex == 0) {
               _ex = 1;
            }
         }

         buffer[0] = (byte)(k >> 16);
         buffer[1] = (byte)(k >> 8);
         buffer[2] = (byte)k;
         buffer[3] = (byte)(_ex >> 16);
         buffer[4] = (byte)(_ex >> 8);
         buffer[5] = (byte)_ex;
         this.seekTo(this.indexFile, j * 6);
         this.indexFile.write(buffer, 0, 6);
         j1 = 0;

         for(l1 = 0; j1 < k; ++l1) {
            int i2 = 0;
            int k2;
            if(flag) {
               this.seekTo(this.dataFile, _ex * 520);

               int l2;
               for(k2 = 0; k2 < 8; k2 += l2) {
                  l2 = this.dataFile.read(buffer, k2, 8 - k2);
                  if(l2 == -1) {
                     break;
                  }
               }

               if(k2 == 8) {
                  int i3 = ((buffer[0] & 255) << 8) + (buffer[1] & 255);
                  int j3 = ((buffer[2] & 255) << 8) + (buffer[3] & 255);
                  i2 = ((buffer[4] & 255) << 16) + ((buffer[5] & 255) << 8) + (buffer[6] & 255);
                  int k3 = buffer[7] & 255;
                  if(i3 != j || j3 != l1 || k3 != this.anInt311) {
                     return false;
                  }

                  if(i2 < 0 || (long)i2 > this.dataFile.length() / 520L) {
                     return false;
                  }
               }
            }

            if(i2 == 0) {
               flag = false;
               i2 = (int)((this.dataFile.length() + 519L) / 520L);
               if(i2 == 0) {
                  ++i2;
               }

               if(i2 == _ex) {
                  ++i2;
               }
            }

            if(k - j1 <= 512) {
               i2 = 0;
            }

            buffer[0] = (byte)(j >> 8);
            buffer[1] = (byte)j;
            buffer[2] = (byte)(l1 >> 8);
            buffer[3] = (byte)l1;
            buffer[4] = (byte)(i2 >> 16);
            buffer[5] = (byte)(i2 >> 8);
            buffer[6] = (byte)i2;
            buffer[7] = (byte)this.anInt311;
            this.seekTo(this.dataFile, _ex * 520);
            this.dataFile.write(buffer, 0, 8);
            k2 = k - j1;
            if(k2 > 512) {
               k2 = 512;
            }

            this.dataFile.write(abyte0, j1, k2);
            j1 += k2;
            _ex = i2;
         }

         return true;
      } catch (IOException var14) {
         return false;
      }
   }

   private synchronized void seekTo(RandomAccessFile randomaccessfile, int j) throws IOException {
      if(j < 0 || j > 62914560) {
         System.out.println("Badseek - pos:" + j + " len:" + randomaccessfile.length());
         j = 62914560;

         try {
            Thread.sleep(1000L);
         } catch (Exception var4) {
            ;
         }
      }

      randomaccessfile.seek((long)j);
   }

}
