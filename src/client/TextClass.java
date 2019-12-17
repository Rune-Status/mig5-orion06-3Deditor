package client;


final class TextClass {

   private static final char[] validChars = new char[]{'_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


   public static long longForName(String s) {
      long l = 0L;

      for(int i = 0; i < s.length() && i < 12; ++i) {
         char c = s.charAt(i);
         l *= 37L;
         if(c >= 65 && c <= 90) {
            l += (long)(1 + c - 65);
         } else if(c >= 97 && c <= 122) {
            l += (long)(1 + c - 97);
         } else if(c >= 48 && c <= 57) {
            l += (long)(27 + c - 48);
         }
      }

      while(l % 37L == 0L && l != 0L) {
         l /= 37L;
      }

      return l;
   }

   public static String nameForLong(long l) {
      try {
         if(l > 0L && l < 6582952005840035281L) {
            if(l % 37L == 0L) {
               return "invalid_name";
            } else {
               int runtimeexception = 0;

               char[] ac;
               long l1;
               for(ac = new char[12]; l != 0L; ac[11 - runtimeexception++] = validChars[(int)(l1 - l * 37L)]) {
                  l1 = l;
                  l /= 37L;
               }

               return new String(ac, 12 - runtimeexception, runtimeexception);
            }
         } else {
            return "invalid_name";
         }
      } catch (RuntimeException var6) {
         SignLink.reporterror("81570, " + l + ", " + -99 + ", " + var6.toString());
         throw new RuntimeException();
      }
   }

   public static long method585(String s) {
      s = s.toUpperCase();
      long l = 0L;

      for(int i = 0; i < s.length(); ++i) {
         l = l * 61L + (long)s.charAt(i) - 32L;
         l = l + (l >> 56) & 72057594037927935L;
      }

      return l;
   }

   public static String method586(int i) {
      return (i >> 24 & 255) + "." + (i >> 16 & 255) + "." + (i >> 8 & 255) + "." + (i & 255);
   }

   public static String fixName(String s) {
      if(s.length() > 0) {
         char[] ac = s.toCharArray();

         for(int j = 0; j < ac.length; ++j) {
            if(ac[j] == 95) {
               ac[j] = 32;
               if(j + 1 < ac.length && ac[j + 1] >= 97 && ac[j + 1] <= 122) {
                  ac[j + 1] = (char)(ac[j + 1] + 65 - 97);
               }
            }
         }

         if(ac[0] >= 97 && ac[0] <= 122) {
            ac[0] = (char)(ac[0] + 65 - 97);
         }

         return new String(ac);
      } else {
         return s;
      }
   }

   public static String passwordAsterisks(String s) {
      StringBuffer stringbuffer = new StringBuffer();

      for(int j = 0; j < s.length(); ++j) {
         stringbuffer.append("*");
      }

      return stringbuffer.toString();
   }

}
