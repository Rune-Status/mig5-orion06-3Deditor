package client;


final class Class4 {

   public static int method155(int i, int j, int k) {
      i &= 3;
      return i == 0?k:(i == 1?j:(i == 2?7 - k:7 - j));
   }

   public static int method156(int i, int j, int l) {
      j &= 3;
      return j == 0?i:(j == 1?7 - l:(j == 2?7 - i:l));
   }

   public static int method157(int i, int j, int k, int l, int i1) {
      i &= 3;
      return i == 0?k:(i == 1?l:(i == 2?7 - k - (i1 - 1):7 - l - (j - 1)));
   }

   public static int method158(int j, int k, int l, int i1, int j1) {
      l &= 3;
      return l == 0?j:(l == 1?7 - j1 - (i1 - 1):(l == 2?7 - j - (k - 1):j1));
   }
}
