package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CacheDownloader {

   private Client client;
   private final int BUFFER = 1024;
   private final int VERSION = 1;
   private String cacheLink = "http://www.2006scapestuff.com/.2006Scape.zip";
   private String fileToExtract = this.getCacheDir() + this.getArchivedName();


   public CacheDownloader(Client client) {
      this.client = client;
   }

   private void drawLoadingText(String text) {
      this.client.drawLoadingText(35, text);
      System.out.println(text);
   }

   private void drawLoadingText(int amount, String text) {
      this.client.drawLoadingText(amount, text);
      System.out.println(text);
   }

   private String getCacheDir() {
      return SignLink.findcachedir();
   }

   private String getCacheLink() {
      return this.cacheLink;
   }

   private int getCacheVersion() {
      return 1;
   }

   public CacheDownloader downloadCache() {
      try {
         File e = new File(this.getCacheDir());
         File version = new File(this.getCacheDir() + "/cacheVersion" + this.getCacheVersion() + ".dat");
         BufferedWriter versionFile;
         if(!e.exists()) {
            this.downloadFile(this.getCacheLink(), this.getArchivedName());
            this.unZip();
            System.out.println("UNZIP");
            versionFile = new BufferedWriter(new FileWriter(this.getCacheDir() + "/cacheVersion" + this.getCacheVersion() + ".dat"));
            versionFile.close();
         } else {
            if(version.exists()) {
               return null;
            }

            this.downloadFile(this.getCacheLink(), this.getArchivedName());
            this.unZip();
            System.out.println("UNZIP");
            versionFile = new BufferedWriter(new FileWriter(this.getCacheDir() + "/cacheVersion" + this.getCacheVersion() + ".dat"));
            versionFile.close();
         }
      } catch (Exception var4) {
         ;
      }

      return null;
   }

   private void downloadFile(String adress, String localFileName) {
      BufferedOutputStream out = null;
      InputStream in = null;

      try {
         URL ioe = new URL(adress);
         out = new BufferedOutputStream(new FileOutputStream(this.getCacheDir() + "/" + localFileName));
         URLConnection conn = ioe.openConnection();
         in = conn.getInputStream();
         byte[] data = new byte[1024];
         long numWritten = 0L;
         int length = conn.getContentLength();

         int numRead;
         while((numRead = in.read(data)) != -1) {
            out.write(data, 0, numRead);
            numWritten += (long)numRead;
            int percentage = (int)((double)numWritten / (double)length * 100.0D);
            this.drawLoadingText(percentage, "Downloading Cache " + percentage + "%");
         }

         System.out.println(localFileName + "\t" + numWritten);
         this.drawLoadingText("Finished downloading " + this.getArchivedName() + "!");
      } catch (Exception var21) {
         var21.printStackTrace();
      } finally {
         try {
            if(in != null) {
               in.close();
            }

            if(out != null) {
               out.close();
            }
         } catch (IOException var20) {
            ;
         }

      }

   }

   private String getArchivedName() {
      int lastSlashIndex = this.getCacheLink().lastIndexOf(47);
      if(lastSlashIndex >= 0 && lastSlashIndex < this.getCacheLink().length() - 1) {
         return this.getCacheLink().substring(lastSlashIndex + 1);
      } else {
         System.err.println("Add cache download link here.");
         return "";
      }
   }

   private void unZip() {
      try {
         BufferedInputStream e = new BufferedInputStream(new FileInputStream(this.fileToExtract));

         ZipInputStream zin;
         ZipEntry e1;
         for(zin = new ZipInputStream(e); (e1 = zin.getNextEntry()) != null; System.out.println("unzipping2 " + e1.getName())) {
            if(e1.isDirectory()) {
               (new File(this.getCacheDir() + e1.getName())).mkdir();
            } else {
               if(e1.getName().equals(this.fileToExtract)) {
                  this.unzip(zin, this.fileToExtract);
                  break;
               }

               this.unzip(zin, this.getCacheDir() + e1.getName());
            }
         }

         zin.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void unzip(ZipInputStream zin, String s) throws IOException {
      FileOutputStream out = new FileOutputStream(s);
      byte[] b = new byte[1024];
      boolean len = false;

      int len1;
      while((len1 = zin.read(b)) != -1) {
         out.write(b, 0, len1);
      }

      out.close();
   }
}
