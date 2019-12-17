package client;


final class TextInput {

   private static final char[] aCharArray631 = new char[100];
   private static final Stream stream = new Stream(new byte[100]);
   private static final char[] validChars = new char[]{' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'', '@', '#', '+', '=', '\u00a3', '$', '%', '\"', '[', ']'};


   public static String method525(int i, Stream stream) {
      int j = 0;
      int k = -1;

      int k1;
      for(int flag1 = 0; flag1 < i; ++flag1) {
         k1 = stream.readUnsignedByte();
         int c = k1 >> 4 & 15;
         if(k == -1) {
            if(c < 13) {
               aCharArray631[j++] = validChars[c];
            } else {
               k = c;
            }
         } else {
            aCharArray631[j++] = validChars[(k << 4) + c - 195];
            k = -1;
         }

         c = k1 & 15;
         if(k == -1) {
            if(c < 13) {
               aCharArray631[j++] = validChars[c];
            } else {
               k = c;
            }
         } else {
            aCharArray631[j++] = validChars[(k << 4) + c - 195];
            k = -1;
         }
      }

      boolean var7 = true;

      for(k1 = 0; k1 < j; ++k1) {
         char var8 = aCharArray631[k1];
         if(var7 && var8 >= 97 && var8 <= 122) {
            aCharArray631[k1] += '\uffe0';
            var7 = false;
         }

         if(var8 == 46 || var8 == 33 || var8 == 63) {
            var7 = true;
         }
      }

      return new String(aCharArray631, 0, j);
   }

   public static void method526(String s, Stream stream) {
      if(s.length() > 80) {
         s = s.substring(0, 80);
      }

      s = s.toLowerCase();
      int i = -1;
      int j = 0;

      while(j < s.length()) {
         char c = s.charAt(j);
         int k = 0;
         int l = 0;

         while(true) {
            if(l < validChars.length) {
               if(c != validChars[l]) {
                  ++l;
                  continue;
               }

               k = l;
            }

            if(k > 12) {
               k += 195;
            }

            if(i == -1) {
               if(k < 13) {
                  i = k;
               } else {
                  stream.writeWordBigEndian(k);
               }
            } else if(k < 13) {
               stream.writeWordBigEndian((i << 4) + k);
               i = -1;
            } else {
               stream.writeWordBigEndian((i << 4) + (k >> 4));
               i = k & 15;
            }

            ++j;
            break;
         }
      }

      if(i != -1) {
         stream.writeWordBigEndian(i << 4);
      }

   }

   public static String processText(String s) {
      stream.currentOffset = 0;
      method526(s, stream);
      int j = stream.currentOffset;
      stream.currentOffset = 0;
      String s1 = method525(j, stream);
      return s1;
   }

}
