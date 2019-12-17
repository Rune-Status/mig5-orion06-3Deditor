package client;


final class Class3_Sub7_Sub2 extends Class3_Sub7 {

   private int anInt1816;
   private int anInt1817;
   private int anInt1818;
   private int anInt1819;
   private int anInt1820;
   private boolean aBoolean1821;
   private int anInt1822;
   private int anInt1823;
   private int anInt1824;
   private int anInt1825;
   private int anInt1826;


   private static final int method388(int i, int i_0_, byte[] is, int[] is_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_, Class3_Sub7_Sub2 class3_sub7_sub2, int i_8_, int i_9_) {
      if(i_8_ == 0 || (i_5_ = i_3_ + (i_7_ + 256 - i_2_ + i_8_) / i_8_) > i_6_) {
         i_5_ = i_6_;
      }

      int var10001;
      while(i_3_ < i_5_) {
         i_0_ = i_2_ >> 8;
         byte var13 = is[i_0_ - 1];
         var10001 = i_3_++;
         is_1_[var10001] += ((var13 << 8) + (is[i_0_] - var13) * (i_2_ & 255)) * i_4_;
         i_2_ += i_8_;
      }

      if(i_8_ == 0 || (i_5_ = i_3_ + (i_7_ - i_2_ + i_8_) / i_8_) > i_6_) {
         i_5_ = i_6_;
      }

      i = i_9_;

      for(i_0_ = i_8_; i_3_ < i_5_; i_2_ += i_0_) {
         var10001 = i_3_++;
         is_1_[var10001] += ((i << 8) + (is[i_2_ >> 8] - i) * (i_2_ & 255)) * i_4_;
      }

      class3_sub7_sub2.anInt1820 = i_2_;
      return i_3_;
   }

   private static final int method389(byte[] is, int[] is_10_, int i, int i_11_, int i_12_, int i_13_, int i_14_, int i_15_, Class3_Sub7_Sub2 class3_sub7_sub2) {
      i >>= 8;
      i_15_ >>= 8;
      i_12_ <<= 8;
      if((i_13_ = i_11_ + i_15_ - i) > i_14_) {
         i_13_ = i_14_;
      }

      int var10001;
      for(i_13_ -= 3; i_11_ < i_13_; is_10_[var10001] += is[i++] * i_12_) {
         var10001 = i_11_++;
         is_10_[var10001] += is[i++] * i_12_;
         var10001 = i_11_++;
         is_10_[var10001] += is[i++] * i_12_;
         var10001 = i_11_++;
         is_10_[var10001] += is[i++] * i_12_;
         var10001 = i_11_++;
      }

      for(i_13_ += 3; i_11_ < i_13_; is_10_[var10001] += is[i++] * i_12_) {
         var10001 = i_11_++;
      }

      class3_sub7_sub2.anInt1820 = i << 8;
      return i_11_;
   }

   private static final int method390(int i, int i_16_, byte[] is, int[] is_17_, int i_18_, int i_19_, int i_20_, int i_21_, int i_22_, int i_23_, int i_24_, int i_25_, Class3_Sub7_Sub2 class3_sub7_sub2, int i_26_, int i_27_) {
      if(i_26_ == 0 || (i_23_ = i_19_ + (i_25_ - i_18_ + i_26_ - 257) / i_26_) > i_24_) {
         i_23_ = i_24_;
      }

      int var10001;
      byte var15;
      while(i_19_ < i_23_) {
         i_16_ = i_18_ >> 8;
         var15 = is[i_16_];
         var10001 = i_19_++;
         is_17_[var10001] += ((var15 << 8) + (is[i_16_ + 1] - var15) * (i_18_ & 255)) * i_20_ >> i_21_;
         i_20_ += i_22_;
         i_18_ += i_26_;
      }

      if(i_26_ == 0 || (i_23_ = i_19_ + (i_25_ - i_18_ + i_26_ - 1) / i_26_) > i_24_) {
         i_23_ = i_24_;
      }

      for(i_16_ = i_27_; i_19_ < i_23_; i_18_ += i_26_) {
         var15 = is[i_18_ >> 8];
         var10001 = i_19_++;
         is_17_[var10001] += ((var15 << 8) + (i_16_ - var15) * (i_18_ & 255)) * i_20_ >> i_21_;
         i_20_ += i_22_;
      }

      class3_sub7_sub2.anInt1822 = i_20_;
      class3_sub7_sub2.anInt1820 = i_18_;
      return i_19_;
   }

   private static final int method391(int i, int i_28_, byte[] is, int[] is_29_, int i_30_, int i_31_, int i_32_, int i_33_, int i_34_, int i_35_, int i_36_, int i_37_, Class3_Sub7_Sub2 class3_sub7_sub2, int i_38_, int i_39_) {
      if(i_38_ == 0 || (i_35_ = i_31_ + (i_37_ + 256 - i_30_ + i_38_) / i_38_) > i_36_) {
         i_35_ = i_36_;
      }

      int var10001;
      while(i_31_ < i_35_) {
         i_28_ = i_30_ >> 8;
         byte var15 = is[i_28_ - 1];
         var10001 = i_31_++;
         is_29_[var10001] += ((var15 << 8) + (is[i_28_] - var15) * (i_30_ & 255)) * i_32_ >> i_33_;
         i_32_ += i_34_;
         i_30_ += i_38_;
      }

      if(i_38_ == 0 || (i_35_ = i_31_ + (i_37_ - i_30_ + i_38_) / i_38_) > i_36_) {
         i_35_ = i_36_;
      }

      i = i_39_;

      for(i_28_ = i_38_; i_31_ < i_35_; i_30_ += i_28_) {
         var10001 = i_31_++;
         is_29_[var10001] += ((i << 8) + (is[i_30_ >> 8] - i) * (i_30_ & 255)) * i_32_ >> i_33_;
         i_32_ += i_34_;
      }

      class3_sub7_sub2.anInt1822 = i_32_;
      class3_sub7_sub2.anInt1820 = i_30_;
      return i_31_;
   }

   private static final int method392(byte[] is, int[] is_40_, int i, int i_41_, int i_42_, int i_43_, int i_44_, int i_45_, int i_46_, int i_47_, Class3_Sub7_Sub2 class3_sub7_sub2) {
      i >>= 8;
      i_47_ >>= 8;
      i_42_ <<= 8;
      if((i_45_ = i_41_ + i - (i_47_ - 1)) > i_46_) {
         i_45_ = i_46_;
      }

      int var10001;
      for(i_45_ -= 3; i_41_ < i_45_; i_42_ += i_44_) {
         var10001 = i_41_++;
         is_40_[var10001] += is[i--] * i_42_ >> i_43_;
         i_42_ += i_44_;
         var10001 = i_41_++;
         is_40_[var10001] += is[i--] * i_42_ >> i_43_;
         i_42_ += i_44_;
         var10001 = i_41_++;
         is_40_[var10001] += is[i--] * i_42_ >> i_43_;
         i_42_ += i_44_;
         var10001 = i_41_++;
         is_40_[var10001] += is[i--] * i_42_ >> i_43_;
      }

      for(i_45_ += 3; i_41_ < i_45_; i_42_ += i_44_) {
         var10001 = i_41_++;
         is_40_[var10001] += is[i--] * i_42_ >> i_43_;
      }

      class3_sub7_sub2.anInt1822 = i_42_ >> 8;
      class3_sub7_sub2.anInt1820 = i << 8;
      return i_41_;
   }

   private static final int method393(int i, int i_48_, byte[] is, int[] is_49_, int i_50_, int i_51_, int i_52_, int i_53_, int i_54_, int i_55_, Class3_Sub7_Sub2 class3_sub7_sub2, int i_56_, int i_57_) {
      if(i_56_ == 0 || (i_53_ = i_51_ + (i_55_ - i_50_ + i_56_ - 257) / i_56_) > i_54_) {
         i_53_ = i_54_;
      }

      int var10001;
      byte var13;
      while(i_51_ < i_53_) {
         i_48_ = i_50_ >> 8;
         var13 = is[i_48_];
         var10001 = i_51_++;
         is_49_[var10001] += ((var13 << 8) + (is[i_48_ + 1] - var13) * (i_50_ & 255)) * i_52_;
         i_50_ += i_56_;
      }

      if(i_56_ == 0 || (i_53_ = i_51_ + (i_55_ - i_50_ + i_56_ - 1) / i_56_) > i_54_) {
         i_53_ = i_54_;
      }

      for(i_48_ = i_57_; i_51_ < i_53_; i_50_ += i_56_) {
         var13 = is[i_50_ >> 8];
         var10001 = i_51_++;
         is_49_[var10001] += ((var13 << 8) + (i_48_ - var13) * (i_50_ & 255)) * i_52_;
      }

      class3_sub7_sub2.anInt1820 = i_50_;
      return i_51_;
   }

   final int method379() {
      int i = this.anInt1822 * 3;
      i = (i ^ i >> 31) + (i >>> 31);
      if(this.anInt1816 == 0) {
         i -= i * this.anInt1820 / (((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827.length << 8);
      } else if(this.anInt1816 >= 0) {
         i -= i * this.anInt1823 / ((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827.length;
      }

      return i > 255?255:i;
   }

   final synchronized int method378(int[] is, int i, int i_58_) {
      if(this.anInt1822 == 0 && (this.anInt1819 == 0 || this.anInt1825 == 0 || this.anInt1825 == Integer.MIN_VALUE)) {
         this.method380(i_58_);
         return 0;
      } else {
         Class3_Sub9_Sub1 class3_sub9_sub1 = (Class3_Sub9_Sub1)this.aClass3_Sub9_1220;
         int i_59_ = this.anInt1823 << 8;
         int i_60_ = this.anInt1824 << 8;
         int i_61_ = class3_sub9_sub1.aByteArray1827.length << 8;
         int i_62_ = i_60_ - i_59_;
         if(i_62_ <= 0) {
            this.anInt1816 = 0;
         }

         int i_63_ = i;
         i_58_ += i;
         if(this.anInt1816 < 0) {
            if(this.aBoolean1821) {
               if(this.anInt1826 < 0) {
                  i_63_ = this.method395(is, i, i_59_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1823]);
                  if(this.anInt1820 >= i_59_) {
                     return 1;
                  }

                  this.anInt1820 = i_59_ + i_59_ - 1 - this.anInt1820;
                  this.anInt1826 = -this.anInt1826;
               }

               while(true) {
                  i_63_ = this.method398(is, i_63_, i_60_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1824 - 1]);
                  if(this.anInt1820 < i_60_) {
                     return 1;
                  }

                  this.anInt1820 = i_60_ + i_60_ - 1 - this.anInt1820;
                  this.anInt1826 = -this.anInt1826;
                  i_63_ = this.method395(is, i_63_, i_59_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1823]);
                  if(this.anInt1820 >= i_59_) {
                     return 1;
                  }

                  this.anInt1820 = i_59_ + i_59_ - 1 - this.anInt1820;
                  this.anInt1826 = -this.anInt1826;
               }
            } else if(this.anInt1826 < 0) {
               while(true) {
                  i_63_ = this.method395(is, i_63_, i_59_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1824 - 1]);
                  if(this.anInt1820 >= i_59_) {
                     return 1;
                  }

                  this.anInt1820 = i_60_ - 1 - (i_60_ - 1 - this.anInt1820) % i_62_;
               }
            } else {
               while(true) {
                  i_63_ = this.method398(is, i_63_, i_60_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1823]);
                  if(this.anInt1820 < i_60_) {
                     return 1;
                  }

                  this.anInt1820 = i_59_ + (this.anInt1820 - i_59_) % i_62_;
               }
            }
         } else {
            if(this.anInt1816 > 0) {
               if(this.aBoolean1821) {
                  label122: {
                     if(this.anInt1826 < 0) {
                        i_63_ = this.method395(is, i, i_59_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1823]);
                        if(this.anInt1820 >= i_59_) {
                           return 1;
                        }

                        this.anInt1820 = i_59_ + i_59_ - 1 - this.anInt1820;
                        this.anInt1826 = -this.anInt1826;
                        if(--this.anInt1816 == 0) {
                           break label122;
                        }
                     }

                     do {
                        i_63_ = this.method398(is, i_63_, i_60_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1824 - 1]);
                        if(this.anInt1820 < i_60_) {
                           return 1;
                        }

                        this.anInt1820 = i_60_ + i_60_ - 1 - this.anInt1820;
                        this.anInt1826 = -this.anInt1826;
                        if(--this.anInt1816 == 0) {
                           break;
                        }

                        i_63_ = this.method395(is, i_63_, i_59_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1823]);
                        if(this.anInt1820 >= i_59_) {
                           return 1;
                        }

                        this.anInt1820 = i_59_ + i_59_ - 1 - this.anInt1820;
                        this.anInt1826 = -this.anInt1826;
                     } while(--this.anInt1816 != 0);
                  }
               } else {
                  int i_65_;
                  if(this.anInt1826 < 0) {
                     while(true) {
                        i_63_ = this.method395(is, i_63_, i_59_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1824 - 1]);
                        if(this.anInt1820 >= i_59_) {
                           return 1;
                        }

                        i_65_ = (i_60_ - 1 - this.anInt1820) / i_62_;
                        if(i_65_ >= this.anInt1816) {
                           this.anInt1820 += i_62_ * this.anInt1816;
                           this.anInt1816 = 0;
                           break;
                        }

                        this.anInt1820 += i_62_ * i_65_;
                        this.anInt1816 -= i_65_;
                     }
                  } else {
                     while(true) {
                        i_63_ = this.method398(is, i_63_, i_60_, i_58_, class3_sub9_sub1.aByteArray1827[this.anInt1823]);
                        if(this.anInt1820 < i_60_) {
                           return 1;
                        }

                        i_65_ = (this.anInt1820 - i_59_) / i_62_;
                        if(i_65_ >= this.anInt1816) {
                           this.anInt1820 -= i_62_ * this.anInt1816;
                           this.anInt1816 = 0;
                           break;
                        }

                        this.anInt1820 -= i_62_ * i_65_;
                        this.anInt1816 -= i_65_;
                     }
                  }
               }
            }

            if(this.anInt1826 < 0) {
               this.method395(is, i_63_, 0, i_58_, 0);
               if(this.anInt1820 < 0) {
                  this.anInt1820 = 0;
                  this.unlink();
               }
            } else {
               this.method398(is, i_63_, i_61_, i_58_, 0);
               if(this.anInt1820 >= i_61_) {
                  this.anInt1820 = i_61_ - 1;
                  this.unlink();
               }
            }

            return 1;
         }
      }
   }

   final synchronized void method394(int i) {
      this.anInt1816 = i;
   }

   private final int method395(int[] is, int i, int i_66_, int i_67_, int i_68_) {
      if(this.anInt1819 > 0) {
         int i_69_ = i + this.anInt1819;
         if(i_69_ > i_67_) {
            i_69_ = i_67_;
         }

         this.anInt1819 += i;
         if(this.anInt1826 == -256 && (this.anInt1820 & 255) == 0) {
            i = method392(((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, this.anInt1818, this.anInt1817, 0, i_69_, i_66_, this);
         } else {
            i = method391(0, 0, ((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, this.anInt1818, this.anInt1817, 0, i_69_, i_66_, this, this.anInt1826, i_68_);
         }

         this.anInt1819 -= i;
         if(this.anInt1819 != 0) {
            return i;
         }

         if(this.anInt1825 == Integer.MIN_VALUE) {
            this.unlink();
            return i_67_;
         }

         this.anInt1822 = this.anInt1825;
      }

      return this.anInt1826 == -256 && (this.anInt1820 & 255) == 0?method397(((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, 0, i_67_, i_66_, this):method388(0, 0, ((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, 0, i_67_, i_66_, this, this.anInt1826, i_68_);
   }

   static final Class3_Sub7_Sub2 method396(Class3_Sub9_Sub1 class3_sub9_sub1, int i, int i_70_) {
      return class3_sub9_sub1.aByteArray1827 != null && class3_sub9_sub1.aByteArray1827.length != 0?new Class3_Sub7_Sub2(class3_sub9_sub1, (int)((long)class3_sub9_sub1.anInt1828 * 256L * (long)i / (long)(Client.anInt197 * 100)), i_70_):null;
   }

   private static final int method397(byte[] is, int[] is_71_, int i, int i_72_, int i_73_, int i_74_, int i_75_, int i_76_, Class3_Sub7_Sub2 class3_sub7_sub2) {
      i >>= 8;
      i_76_ >>= 8;
      i_73_ <<= 8;
      if((i_74_ = i_72_ + i - (i_76_ - 1)) > i_75_) {
         i_74_ = i_75_;
      }

      int var10001;
      for(i_74_ -= 3; i_72_ < i_74_; is_71_[var10001] += is[i--] * i_73_) {
         var10001 = i_72_++;
         is_71_[var10001] += is[i--] * i_73_;
         var10001 = i_72_++;
         is_71_[var10001] += is[i--] * i_73_;
         var10001 = i_72_++;
         is_71_[var10001] += is[i--] * i_73_;
         var10001 = i_72_++;
      }

      for(i_74_ += 3; i_72_ < i_74_; is_71_[var10001] += is[i--] * i_73_) {
         var10001 = i_72_++;
      }

      class3_sub7_sub2.anInt1820 = i << 8;
      return i_72_;
   }

   final synchronized void method380(int i) {
      if(this.anInt1819 > 0) {
         if(i >= this.anInt1819) {
            if(this.anInt1825 == Integer.MIN_VALUE) {
               this.unlink();
               i = this.anInt1819;
            } else {
               this.anInt1822 = this.anInt1825;
            }

            this.anInt1819 = 0;
         } else {
            this.anInt1822 += this.anInt1817 * i;
            this.anInt1819 -= i;
         }
      }

      this.anInt1820 += this.anInt1826 * i;
      Class3_Sub9_Sub1 class3_sub9_sub1 = (Class3_Sub9_Sub1)this.aClass3_Sub9_1220;
      int i_77_ = this.anInt1823 << 8;
      int i_78_ = this.anInt1824 << 8;
      int i_79_ = class3_sub9_sub1.aByteArray1827.length << 8;
      int i_80_ = i_78_ - i_77_;
      if(i_80_ <= 0) {
         this.anInt1816 = 0;
      }

      if(this.anInt1816 < 0) {
         if(this.aBoolean1821) {
            if(this.anInt1826 < 0) {
               if(this.anInt1820 >= i_77_) {
                  return;
               }

               this.anInt1820 = i_77_ + i_77_ - 1 - this.anInt1820;
               this.anInt1826 = -this.anInt1826;
            }

            while(this.anInt1820 >= i_78_) {
               this.anInt1820 = i_78_ + i_78_ - 1 - this.anInt1820;
               this.anInt1826 = -this.anInt1826;
               if(this.anInt1820 >= i_77_) {
                  break;
               }

               this.anInt1820 = i_77_ + i_77_ - 1 - this.anInt1820;
               this.anInt1826 = -this.anInt1826;
            }
         } else if(this.anInt1826 < 0) {
            if(this.anInt1820 < i_77_) {
               this.anInt1820 = i_78_ - 1 - (i_78_ - 1 - this.anInt1820) % i_80_;
            }
         } else if(this.anInt1820 >= i_78_) {
            this.anInt1820 = i_77_ + (this.anInt1820 - i_77_) % i_80_;
         }
      } else {
         if(this.anInt1816 > 0) {
            if(this.aBoolean1821) {
               label97: {
                  if(this.anInt1826 < 0) {
                     if(this.anInt1820 >= i_77_) {
                        return;
                     }

                     this.anInt1820 = i_77_ + i_77_ - 1 - this.anInt1820;
                     this.anInt1826 = -this.anInt1826;
                     if(--this.anInt1816 == 0) {
                        break label97;
                     }
                  }

                  do {
                     if(this.anInt1820 < i_78_) {
                        return;
                     }

                     this.anInt1820 = i_78_ + i_78_ - 1 - this.anInt1820;
                     this.anInt1826 = -this.anInt1826;
                     if(--this.anInt1816 == 0) {
                        break;
                     }

                     if(this.anInt1820 >= i_77_) {
                        return;
                     }

                     this.anInt1820 = i_77_ + i_77_ - 1 - this.anInt1820;
                     this.anInt1826 = -this.anInt1826;
                  } while(--this.anInt1816 != 0);
               }
            } else {
               int i_82_;
               if(this.anInt1826 < 0) {
                  if(this.anInt1820 >= i_77_) {
                     return;
                  }

                  i_82_ = (i_78_ - 1 - this.anInt1820) / i_80_;
                  if(i_82_ < this.anInt1816) {
                     this.anInt1820 += i_80_ * i_82_;
                     this.anInt1816 -= i_82_;
                     return;
                  }

                  this.anInt1820 += i_80_ * this.anInt1816;
                  this.anInt1816 = 0;
               } else {
                  if(this.anInt1820 < i_78_) {
                     return;
                  }

                  i_82_ = (this.anInt1820 - i_77_) / i_80_;
                  if(i_82_ < this.anInt1816) {
                     this.anInt1820 -= i_80_ * i_82_;
                     this.anInt1816 -= i_82_;
                     return;
                  }

                  this.anInt1820 -= i_80_ * this.anInt1816;
                  this.anInt1816 = 0;
               }
            }
         }

         if(this.anInt1826 < 0) {
            if(this.anInt1820 < 0) {
               this.anInt1820 = 0;
               this.unlink();
            }
         } else if(this.anInt1820 >= i_79_) {
            this.anInt1820 = i_79_ - 1;
            this.unlink();
         }
      }

   }

   private Class3_Sub7_Sub2(Class3_Sub9_Sub1 class3_sub9_sub1, int i, int i_83_) {
      this.aClass3_Sub9_1220 = class3_sub9_sub1;
      this.anInt1823 = class3_sub9_sub1.anInt1830;
      this.anInt1824 = class3_sub9_sub1.anInt1829;
      this.anInt1826 = i;
      this.anInt1822 = i_83_;
      this.anInt1820 = 0;
   }

   private final int method398(int[] is, int i, int i_84_, int i_85_, int i_86_) {
      if(this.anInt1819 > 0) {
         int i_87_ = i + this.anInt1819;
         if(i_87_ > i_85_) {
            i_87_ = i_85_;
         }

         this.anInt1819 += i;
         if(this.anInt1826 == 256 && (this.anInt1820 & 255) == 0) {
            i = method399(((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, this.anInt1818, this.anInt1817, 0, i_87_, i_84_, this);
         } else {
            i = method390(0, 0, ((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, this.anInt1818, this.anInt1817, 0, i_87_, i_84_, this, this.anInt1826, i_86_);
         }

         this.anInt1819 -= i;
         if(this.anInt1819 != 0) {
            return i;
         }

         if(this.anInt1825 == Integer.MIN_VALUE) {
            this.unlink();
            return i_85_;
         }

         this.anInt1822 = this.anInt1825;
      }

      return this.anInt1826 == 256 && (this.anInt1820 & 255) == 0?method389(((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, 0, i_85_, i_84_, this):method393(0, 0, ((Class3_Sub9_Sub1)this.aClass3_Sub9_1220).aByteArray1827, is, this.anInt1820, i, this.anInt1822, 0, i_85_, i_84_, this, this.anInt1826, i_86_);
   }

   private static final int method399(byte[] is, int[] is_88_, int i, int i_89_, int i_90_, int i_91_, int i_92_, int i_93_, int i_94_, int i_95_, Class3_Sub7_Sub2 class3_sub7_sub2) {
      i >>= 8;
      i_95_ >>= 8;
      i_90_ <<= 8;
      if((i_93_ = i_89_ + i_95_ - i) > i_94_) {
         i_93_ = i_94_;
      }

      int var10001;
      for(i_93_ -= 3; i_89_ < i_93_; i_90_ += i_92_) {
         var10001 = i_89_++;
         is_88_[var10001] += is[i++] * i_90_ >> i_91_;
         i_90_ += i_92_;
         var10001 = i_89_++;
         is_88_[var10001] += is[i++] * i_90_ >> i_91_;
         i_90_ += i_92_;
         var10001 = i_89_++;
         is_88_[var10001] += is[i++] * i_90_ >> i_91_;
         i_90_ += i_92_;
         var10001 = i_89_++;
         is_88_[var10001] += is[i++] * i_90_ >> i_91_;
      }

      for(i_93_ += 3; i_89_ < i_93_; i_90_ += i_92_) {
         var10001 = i_89_++;
         is_88_[var10001] += is[i++] * i_90_ >> i_91_;
      }

      class3_sub7_sub2.anInt1822 = i_90_ >> 8;
      class3_sub7_sub2.anInt1820 = i << 8;
      return i_89_;
   }
}
