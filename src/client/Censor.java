package client;


final class Censor {

   private static int[] anIntArray620;
   private static char[][] aCharArrayArray621;
   private static byte[][][] aByteArrayArrayArray622;
   private static char[][] aCharArrayArray623;
   private static char[][] aCharArrayArray624;
   private static int[] anIntArray625;
   private static final String[] exceptions = new String[]{"cook", "cook\'s", "cooks", "seeks", "sheet", "woop", "woops", "faq", "noob", "noobs"};


   public static void loadConfig(StreamLoader streamLoader) {
      Stream stream = new Stream(streamLoader.getDataForName("fragmentsenc.txt"));
      Stream stream_1 = new Stream(streamLoader.getDataForName("badenc.txt"));
      Stream stream_2 = new Stream(streamLoader.getDataForName("domainenc.txt"));
      Stream stream_3 = new Stream(streamLoader.getDataForName("tldlist.txt"));
      readValues(stream, stream_1, stream_2, stream_3);
   }

   private static void readValues(Stream stream, Stream stream_1, Stream stream_2, Stream stream_3) {
      readBadEnc(stream_1);
      readDomainEnc(stream_2);
      readFragmentsEnc(stream);
      readTldList(stream_3);
   }

   private static void readTldList(Stream stream) {
      int i = stream.readDWord();
      aCharArrayArray624 = new char[i][];
      anIntArray625 = new int[i];

      for(int j = 0; j < i; ++j) {
         anIntArray625[j] = stream.readUnsignedByte();
         char[] ac = new char[stream.readUnsignedByte()];

         for(int k = 0; k < ac.length; ++k) {
            ac[k] = (char)stream.readUnsignedByte();
         }

         aCharArrayArray624[j] = ac;
      }

   }

   private static void readBadEnc(Stream stream) {
      int j = stream.readDWord();
      aCharArrayArray621 = new char[j][];
      aByteArrayArrayArray622 = new byte[j][][];
      method493(stream, aCharArrayArray621, aByteArrayArrayArray622);
   }

   private static void readDomainEnc(Stream stream) {
      int i = stream.readDWord();
      aCharArrayArray623 = new char[i][];
      method494(aCharArrayArray623, stream);
   }

   private static void readFragmentsEnc(Stream stream) {
      anIntArray620 = new int[stream.readDWord()];

      for(int i = 0; i < anIntArray620.length; ++i) {
         anIntArray620[i] = stream.readUnsignedWord();
      }

   }

   private static void method493(Stream stream, char[][] ac, byte[][][] abyte0) {
      for(int j = 0; j < ac.length; ++j) {
         char[] ac1 = new char[stream.readUnsignedByte()];

         for(int abyte1 = 0; abyte1 < ac1.length; ++abyte1) {
            ac1[abyte1] = (char)stream.readUnsignedByte();
         }

         ac[j] = ac1;
         byte[][] var7 = new byte[stream.readUnsignedByte()][2];

         for(int l = 0; l < var7.length; ++l) {
            var7[l][0] = (byte)stream.readUnsignedByte();
            var7[l][1] = (byte)stream.readUnsignedByte();
         }

         if(var7.length > 0) {
            abyte0[j] = var7;
         }
      }

   }

   private static void method494(char[][] ac, Stream stream) {
      for(int j = 0; j < ac.length; ++j) {
         char[] ac1 = new char[stream.readUnsignedByte()];

         for(int k = 0; k < ac1.length; ++k) {
            ac1[k] = (char)stream.readUnsignedByte();
         }

         ac[j] = ac1;
      }

   }

   private static void method495(char[] ac) {
      int i = 0;

      int k;
      for(k = 0; k < ac.length; ++k) {
         if(method496(ac[k])) {
            ac[i] = ac[k];
         } else {
            ac[i] = 32;
         }

         if(i == 0 || ac[i] != 32 || ac[i - 1] != 32) {
            ++i;
         }
      }

      for(k = i; k < ac.length; ++k) {
         ac[k] = 32;
      }

   }

   private static boolean method496(char c) {
      return c >= 32 && c <= 127 || c == 32 || c == 10 || c == 9 || c == 163 || c == 8364;
   }

   public static String doCensor(String s) {
      System.currentTimeMillis();
      char[] ac = s.toCharArray();
      method495(ac);
      String s1 = (new String(ac)).trim();
      ac = s1.toLowerCase().toCharArray();
      String s2 = s1.toLowerCase();
      method505(ac);
      method500(ac);
      method501(ac);
      method514(ac);
      String[] arr$ = exceptions;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String exception = arr$[i$];
         int k = -1;

         while((k = s2.indexOf(exception, k + 1)) != -1) {
            char[] ac1 = exception.toCharArray();
            System.arraycopy(ac1, 0, ac, k, ac1.length);
         }
      }

      method498(s1.toCharArray(), ac);
      method499(ac);
      System.currentTimeMillis();
      return s;
   }

   private static void method498(char[] ac, char[] ac1) {
      for(int j = 0; j < ac.length; ++j) {
         if(ac1[j] != 42 && isUpperCaseLetter(ac[j])) {
            ac1[j] = ac[j];
         }
      }

   }

   private static void method499(char[] ac) {
      boolean flag = true;

      for(int j = 0; j < ac.length; ++j) {
         char c = ac[j];
         if(isLetter(c)) {
            if(flag) {
               if(isLowerCaseLetter(c)) {
                  flag = false;
               }
            } else if(isUpperCaseLetter(c)) {
               ac[j] = (char)(c + 97 - 65);
            }
         } else {
            flag = true;
         }
      }

   }

   private static void method500(char[] ac) {
      for(int i = 0; i < 2; ++i) {
         for(int j = aCharArrayArray621.length - 1; j >= 0; --j) {
            method509(aByteArrayArrayArray622[j], ac, aCharArrayArray621[j]);
         }
      }

   }

   private static void method501(char[] ac) {
      char[] ac1 = (char[])ac.clone();
      char[] ac2 = new char[]{'(', 'a', ')'};
      method509((byte[][])null, ac1, ac2);
      char[] ac3 = (char[])ac.clone();
      char[] ac4 = new char[]{'d', 'o', 't'};
      method509((byte[][])null, ac3, ac4);

      for(int i = aCharArrayArray623.length - 1; i >= 0; --i) {
         method502(ac, aCharArrayArray623[i], ac3, ac1);
      }

   }

   private static void method502(char[] ac, char[] ac1, char[] ac2, char[] ac3) {
      if(ac1.length <= ac.length) {
         int k = 0;

         while(k <= ac.length - ac1.length) {
            int l = k;
            int i1 = 0;
            int j = 1;

            while(true) {
               if(l < ac.length) {
                  char k1 = ac[l];
                  char l1 = 0;
                  if(l + 1 < ac.length) {
                     l1 = ac[l + 1];
                  }

                  int flag1;
                  if(i1 < ac1.length && (flag1 = method511(k1, ac1[i1], l1)) > 0) {
                     l += flag1;
                     ++i1;
                     continue;
                  }

                  if(i1 != 0) {
                     if((flag1 = method511(k1, ac1[i1 - 1], l1)) > 0) {
                        l += flag1;
                        if(i1 == 1) {
                           ++j;
                        }
                        continue;
                     }

                     if(i1 < ac1.length && method517(k1)) {
                        ++l;
                        continue;
                     }
                  }
               }

               if(i1 >= ac1.length) {
                  boolean var12 = false;
                  int var13 = method503(ac, ac3, k);
                  int var14 = method504(ac2, l - 1, ac);
                  if(var13 > 2 || var14 > 2) {
                     var12 = true;
                  }

                  if(var12) {
                     for(int i2 = k; i2 < l; ++i2) {
                        ac[i2] = 42;
                     }
                  }
               }

               k += j;
               break;
            }
         }

      }
   }

   private static int method503(char[] ac, char[] ac1, int j) {
      if(j == 0) {
         return 2;
      } else {
         int l;
         for(l = j - 1; l >= 0 && method517(ac[l]); --l) {
            if(ac[l] == 64) {
               return 3;
            }
         }

         l = 0;

         for(int i1 = j - 1; i1 >= 0 && method517(ac1[i1]); --i1) {
            if(ac1[i1] == 42) {
               ++l;
            }
         }

         return l >= 3?4:(!method517(ac[j - 1])?0:1);
      }
   }

   private static int method504(char[] ac, int i, char[] ac1) {
      if(i + 1 == ac1.length) {
         return 2;
      } else {
         int k = i + 1;

         while(true) {
            if(k < ac1.length && method517(ac1[k])) {
               if(ac1[k] != 46 && ac1[k] != 44) {
                  ++k;
                  continue;
               }

               return 3;
            }

            k = 0;

            for(int l = i + 1; l < ac1.length && method517(ac[l]); ++l) {
               if(ac[l] == 42) {
                  ++k;
               }
            }

            if(k >= 3) {
               return 4;
            }

            return !method517(ac1[i + 1])?0:1;
         }
      }
   }

   private static void method505(char[] ac) {
      char[] ac1 = (char[])ac.clone();
      char[] ac2 = new char[]{'d', 'o', 't'};
      method509((byte[][])null, ac1, ac2);
      char[] ac3 = (char[])ac.clone();
      char[] ac4 = new char[]{'s', 'l', 'a', 's', 'h'};
      method509((byte[][])null, ac3, ac4);

      for(int i = 0; i < aCharArrayArray624.length; ++i) {
         method506(ac3, aCharArrayArray624[i], anIntArray625[i], ac1, ac);
      }

   }

   private static void method506(char[] ac, char[] ac1, int i, char[] ac2, char[] ac3) {
      if(ac1.length <= ac3.length) {
         int k = 0;

         while(k <= ac3.length - ac1.length) {
            int l = k;
            int i1 = 0;
            int j = 1;

            while(true) {
               if(l < ac3.length) {
                  char k1 = ac3[l];
                  char l1 = 0;
                  if(l + 1 < ac3.length) {
                     l1 = ac3[l + 1];
                  }

                  int flag1;
                  if(i1 < ac1.length && (flag1 = method511(k1, ac1[i1], l1)) > 0) {
                     l += flag1;
                     ++i1;
                     continue;
                  }

                  if(i1 != 0) {
                     if((flag1 = method511(k1, ac1[i1 - 1], l1)) > 0) {
                        l += flag1;
                        if(i1 == 1) {
                           ++j;
                        }
                        continue;
                     }

                     if(i1 < ac1.length && method517(k1)) {
                        ++l;
                        continue;
                     }
                  }
               }

               if(i1 >= ac1.length) {
                  boolean var16 = false;
                  int var17 = method507(ac3, k, ac2);
                  int var18 = method508(ac3, ac, l - 1);
                  if(i == 1 && var17 > 0 && var18 > 0) {
                     var16 = true;
                  }

                  if(i == 2 && (var17 > 2 && var18 > 0 || var17 > 0 && var18 > 2)) {
                     var16 = true;
                  }

                  if(i == 3 && var17 > 0 && var18 > 2) {
                     var16 = true;
                  }

                  if(var16) {
                     int i2 = k;
                     int j2 = l - 1;
                     boolean k2;
                     int k3;
                     if(var17 > 2) {
                        if(var17 == 4) {
                           k2 = false;

                           for(k3 = k - 1; k3 >= 0; --k3) {
                              if(k2) {
                                 if(ac2[k3] != 42) {
                                    break;
                                 }

                                 i2 = k3;
                              } else if(ac2[k3] == 42) {
                                 i2 = k3;
                                 k2 = true;
                              }
                           }
                        }

                        k2 = false;

                        for(k3 = i2 - 1; k3 >= 0; --k3) {
                           if(k2) {
                              if(method517(ac3[k3])) {
                                 break;
                              }

                              i2 = k3;
                           } else if(!method517(ac3[k3])) {
                              k2 = true;
                              i2 = k3;
                           }
                        }
                     }

                     if(var18 > 2) {
                        if(var18 == 4) {
                           k2 = false;

                           for(k3 = j2 + 1; k3 < ac3.length; ++k3) {
                              if(k2) {
                                 if(ac[k3] != 42) {
                                    break;
                                 }

                                 j2 = k3;
                              } else if(ac[k3] == 42) {
                                 j2 = k3;
                                 k2 = true;
                              }
                           }
                        }

                        k2 = false;

                        for(k3 = j2 + 1; k3 < ac3.length; ++k3) {
                           if(k2) {
                              if(method517(ac3[k3])) {
                                 break;
                              }

                              j2 = k3;
                           } else if(!method517(ac3[k3])) {
                              k2 = true;
                              j2 = k3;
                           }
                        }
                     }

                     for(int var19 = i2; var19 <= j2; ++var19) {
                        ac3[var19] = 42;
                     }
                  }
               }

               k += j;
               break;
            }
         }

      }
   }

   private static int method507(char[] ac, int j, char[] ac1) {
      if(j == 0) {
         return 2;
      } else {
         int l = j - 1;

         while(true) {
            if(l >= 0 && method517(ac[l])) {
               if(ac[l] != 44 && ac[l] != 46) {
                  --l;
                  continue;
               }

               return 3;
            }

            l = 0;

            for(int i1 = j - 1; i1 >= 0 && method517(ac1[i1]); --i1) {
               if(ac1[i1] == 42) {
                  ++l;
               }
            }

            if(l >= 3) {
               return 4;
            }

            return !method517(ac[j - 1])?0:1;
         }
      }
   }

   private static int method508(char[] ac, char[] ac1, int i) {
      if(i + 1 == ac.length) {
         return 2;
      } else {
         int k = i + 1;

         while(true) {
            if(k < ac.length && method517(ac[k])) {
               if(ac[k] != 92 && ac[k] != 47) {
                  ++k;
                  continue;
               }

               return 3;
            }

            k = 0;

            for(int l = i + 1; l < ac.length && method517(ac1[l]); ++l) {
               if(ac1[l] == 42) {
                  ++k;
               }
            }

            if(k >= 5) {
               return 4;
            }

            return !method517(ac[i + 1])?0:1;
         }
      }
   }

   private static void method509(byte[][] abyte0, char[] ac, char[] ac1) {
      if(ac1.length <= ac.length) {
         int k = 0;

         while(k <= ac.length - ac1.length) {
            int l = k;
            int i1 = 0;
            int j1 = 0;
            int j = 1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            while(true) {
               char l1;
               char i2;
               if(l < ac.length && (!flag2 || !flag3)) {
                  l1 = ac[l];
                  i2 = 0;
                  if(l + 1 < ac.length) {
                     i2 = ac[l + 1];
                  }

                  int flag4;
                  if(i1 < ac1.length && (flag4 = method512(i2, l1, ac1[i1])) > 0) {
                     if(flag4 == 1 && isDigit(l1)) {
                        flag2 = true;
                     }

                     if(flag4 == 2 && (isDigit(l1) || isDigit(i2))) {
                        flag2 = true;
                     }

                     l += flag4;
                     ++i1;
                     continue;
                  }

                  if(i1 != 0) {
                     if((flag4 = method512(i2, l1, ac1[i1 - 1])) > 0) {
                        l += flag4;
                        if(i1 == 1) {
                           ++j;
                        }
                        continue;
                     }

                     if(i1 < ac1.length && method518(l1)) {
                        if(method517(l1) && l1 != 39) {
                           flag1 = true;
                        }

                        if(isDigit(l1)) {
                           flag3 = true;
                        }

                        ++l;
                        ++j1;
                        if(j1 * 100 / (l - k) <= 90) {
                           continue;
                        }
                     }
                  }
               }

               if(i1 >= ac1.length && (!flag2 || !flag3)) {
                  boolean var19 = true;
                  int var22;
                  if(!flag1) {
                     l1 = 32;
                     if(k - 1 >= 0) {
                        l1 = ac[k - 1];
                     }

                     i2 = 32;
                     if(l < ac.length) {
                        i2 = ac[l];
                     }

                     byte j2 = method513(l1);
                     byte i3 = method513(i2);
                     if(abyte0 != null && method510(j2, abyte0, i3)) {
                        var19 = false;
                     }
                  } else {
                     boolean var20 = false;
                     boolean var23 = false;
                     if(k - 1 < 0 || method517(ac[k - 1]) && ac[k - 1] != 39) {
                        var20 = true;
                     }

                     if(l >= ac.length || method517(ac[l]) && ac[l] != 39) {
                        var23 = true;
                     }

                     if(!var20 || !var23) {
                        boolean var25 = false;
                        var22 = k - 2;
                        if(var20) {
                           var22 = k;
                        }

                        for(; !var25 && var22 < l; ++var22) {
                           if(var22 >= 0 && (!method517(ac[var22]) || ac[var22] == 39)) {
                              char[] ac2 = new char[3];

                              int j3;
                              for(j3 = 0; j3 < 3 && var22 + j3 < ac.length && (!method517(ac[var22 + j3]) || ac[var22 + j3] == 39); ++j3) {
                                 ac2[j3] = ac[var22 + j3];
                              }

                              boolean flag8 = true;
                              if(j3 == 0) {
                                 flag8 = false;
                              }

                              if(j3 < 3 && var22 - 1 >= 0 && (!method517(ac[var22 - 1]) || ac[var22 - 1] == 39)) {
                                 flag8 = false;
                              }

                              if(flag8 && !method523(ac2)) {
                                 var25 = true;
                              }
                           }
                        }

                        if(!var25) {
                           var19 = false;
                        }
                     }
                  }

                  if(var19) {
                     int var24 = 0;
                     int var26 = 0;
                     int var21 = -1;

                     for(var22 = k; var22 < l; ++var22) {
                        if(isDigit(ac[var22])) {
                           ++var24;
                        } else if(isLetter(ac[var22])) {
                           ++var26;
                           var21 = var22;
                        }
                     }

                     if(var21 > -1) {
                        var24 -= l - 1 - var21;
                     }

                     if(var24 <= var26) {
                        for(var22 = k; var22 < l; ++var22) {
                           ac[var22] = 42;
                        }
                     } else {
                        j = 1;
                     }
                  }
               }

               k += j;
               break;
            }
         }

      }
   }

   private static boolean method510(byte byte0, byte[][] abyte0, byte byte2) {
      int i = 0;
      if(abyte0[i][0] == byte0 && abyte0[i][1] == byte2) {
         return true;
      } else {
         int j = abyte0.length - 1;
         if(abyte0[j][0] == byte0 && abyte0[j][1] == byte2) {
            return true;
         } else {
            do {
               int k = (i + j) / 2;
               if(abyte0[k][0] == byte0 && abyte0[k][1] == byte2) {
                  return true;
               }

               if(byte0 >= abyte0[k][0] && (byte0 != abyte0[k][0] || byte2 >= abyte0[k][1])) {
                  i = k;
               } else {
                  j = k;
               }
            } while(i != j && i + 1 != j);

            return false;
         }
      }
   }

   private static int method511(char c, char c1, char c2) {
      return c1 == c?1:(c1 == 111 && c == 48?1:(c1 == 111 && c == 40 && c2 == 41?2:(c1 == 99 && (c == 40 || c == 60 || c == 91)?1:(c1 == 101 && c == 8364?1:(c1 == 115 && c == 36?1:(c1 == 108 && c == 105?1:0))))));
   }

   private static int method512(char c, char c1, char c2) {
      if(c2 == c1) {
         return 1;
      } else {
         if(c2 >= 97 && c2 <= 109) {
            if(c2 == 97) {
               if(c1 != 52 && c1 != 64 && c1 != 94) {
                  return c1 == 47 && c == 92?2:0;
               }

               return 1;
            }

            if(c2 == 98) {
               if(c1 != 54 && c1 != 56) {
                  return (c1 != 49 || c != 51) && (c1 != 105 || c != 51)?0:2;
               }

               return 1;
            }

            if(c2 == 99) {
               return c1 != 40 && c1 != 60 && c1 != 123 && c1 != 91?0:1;
            }

            if(c2 == 100) {
               return (c1 != 91 || c != 41) && (c1 != 105 || c != 41)?0:2;
            }

            if(c2 == 101) {
               return c1 != 51 && c1 != 8364?0:1;
            }

            if(c2 == 102) {
               if(c1 == 112 && c == 104) {
                  return 2;
               }

               return c1 != 163?0:1;
            }

            if(c2 == 103) {
               return c1 != 57 && c1 != 54 && c1 != 113?0:1;
            }

            if(c2 == 104) {
               return c1 != 35?0:1;
            }

            if(c2 == 105) {
               return c1 != 121 && c1 != 108 && c1 != 106 && c1 != 49 && c1 != 33 && c1 != 58 && c1 != 59 && c1 != 124?0:1;
            }

            if(c2 == 106) {
               return 0;
            }

            if(c2 == 107) {
               return 0;
            }

            if(c2 == 108) {
               return c1 != 49 && c1 != 124 && c1 != 105?0:1;
            }

            if(c2 == 109) {
               return 0;
            }
         }

         if(c2 >= 110 && c2 <= 122) {
            if(c2 == 110) {
               return 0;
            }

            if(c2 == 111) {
               if(c1 != 48 && c1 != 42) {
                  return (c1 != 40 || c != 41) && (c1 != 91 || c != 93) && (c1 != 123 || c != 125) && (c1 != 60 || c != 62)?0:2;
               }

               return 1;
            }

            if(c2 == 112) {
               return 0;
            }

            if(c2 == 113) {
               return 0;
            }

            if(c2 == 114) {
               return 0;
            }

            if(c2 == 115) {
               return c1 != 53 && c1 != 122 && c1 != 36 && c1 != 50?0:1;
            }

            if(c2 == 116) {
               return c1 != 55 && c1 != 43?0:1;
            }

            if(c2 == 117) {
               if(c1 == 118) {
                  return 1;
               }

               return (c1 != 92 || c != 47) && (c1 != 92 || c != 124) && (c1 != 124 || c != 47)?0:2;
            }

            if(c2 == 118) {
               return (c1 != 92 || c != 47) && (c1 != 92 || c != 124) && (c1 != 124 || c != 47)?0:2;
            }

            if(c2 == 119) {
               return c1 == 118 && c == 118?2:0;
            }

            if(c2 == 120) {
               return (c1 != 41 || c != 40) && (c1 != 125 || c != 123) && (c1 != 93 || c != 91) && (c1 != 62 || c != 60)?0:2;
            }

            if(c2 == 121) {
               return 0;
            }

            if(c2 == 122) {
               return 0;
            }
         }

         return c2 >= 48 && c2 <= 57?(c2 != 48?(c2 == 49?(c1 != 108?0:1):0):(c1 != 111 && c1 != 79?((c1 != 40 || c != 41) && (c1 != 123 || c != 125) && (c1 != 91 || c != 93)?0:2):1)):(c2 == 44?(c1 != 46?0:1):(c2 == 46?(c1 != 44?0:1):(c2 == 33?(c1 != 105?0:1):0)));
      }
   }

   private static byte method513(char c) {
      return c >= 97 && c <= 122?(byte)(c - 97 + 1):(c == 39?28:(c >= 48 && c <= 57?(byte)(c - 48 + 29):27));
   }

   private static void method514(char[] ac) {
      int k = 0;
      int l = 0;
      int i1 = 0;

      int j;
      while((j = method515(ac, k)) != -1) {
         boolean flag = false;

         int k1;
         for(k1 = k; k1 >= 0 && k1 < j && !flag; ++k1) {
            if(!method517(ac[k1]) && !method518(ac[k1])) {
               flag = true;
            }
         }

         if(flag) {
            l = 0;
         }

         if(l == 0) {
            i1 = j;
         }

         k = method516(ac, j);
         k1 = 0;

         int i2;
         for(i2 = j; i2 < k; ++i2) {
            k1 = k1 * 10 + ac[i2] - 48;
         }

         if(k1 <= 255 && k - j <= 8) {
            ++l;
         } else {
            l = 0;
         }

         if(l == 4) {
            for(i2 = i1; i2 < k; ++i2) {
               ac[i2] = 42;
            }

            l = 0;
         }
      }

   }

   private static int method515(char[] ac, int i) {
      for(int k = i; k < ac.length && k >= 0; ++k) {
         if(ac[k] >= 48 && ac[k] <= 57) {
            return k;
         }
      }

      return -1;
   }

   private static int method516(char[] ac, int j) {
      int k = j;

      while(true) {
         if(k < ac.length && k >= 0) {
            if(ac[k] >= 48 && ac[k] <= 57) {
               ++k;
               continue;
            }

            return k;
         }

         return ac.length;
      }
   }

   private static boolean method517(char c) {
      return !isLetter(c) && !isDigit(c);
   }

   private static boolean method518(char c) {
      return c < 97 || c > 122 || c == 118 || c == 120 || c == 106 || c == 113 || c == 122;
   }

   private static boolean isLetter(char c) {
      return c >= 97 && c <= 122 || c >= 65 && c <= 90;
   }

   private static boolean isDigit(char c) {
      return c >= 48 && c <= 57;
   }

   private static boolean isLowerCaseLetter(char c) {
      return c >= 97 && c <= 122;
   }

   private static boolean isUpperCaseLetter(char c) {
      return c >= 65 && c <= 90;
   }

   private static boolean method523(char[] ac) {
      boolean flag = true;

      int j;
      for(j = 0; j < ac.length; ++j) {
         if(!isDigit(ac[j]) && ac[j] != 0) {
            flag = false;
         }
      }

      if(flag) {
         return true;
      } else {
         j = method524(ac);
         int k = 0;
         int l = anIntArray620.length - 1;
         if(j != anIntArray620[k] && j != anIntArray620[l]) {
            do {
               int i1 = (k + l) / 2;
               if(j == anIntArray620[i1]) {
                  return true;
               }

               if(j < anIntArray620[i1]) {
                  l = i1;
               } else {
                  k = i1;
               }
            } while(k != l && k + 1 != l);

            return false;
         } else {
            return true;
         }
      }
   }

   private static int method524(char[] ac) {
      if(ac.length > 6) {
         return 0;
      } else {
         int k = 0;

         for(int l = 0; l < ac.length; ++l) {
            char c = ac[ac.length - l - 1];
            if(c >= 97 && c <= 122) {
               k = k * 38 + c - 97 + 1;
            } else if(c == 39) {
               k = k * 38 + 27;
            } else if(c >= 48 && c <= 57) {
               k = k * 38 + c - 48 + 28;
            } else if(c != 0) {
               return 0;
            }
         }

         return k;
      }
   }

}
