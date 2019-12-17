package client;

import java.applet.Applet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl.Type;

public final class SignLink
        implements Runnable {
    private final int EXTERNAL_BUFFER_SIZE = 524288;
    private SignLink.Position curPosition;
    Sequencer musicSr = null;
    Sequence musicS = null;
    public static int uid;
    public static int storeid = 32;
    public static RandomAccessFile cache_dat = null;
    public static final RandomAccessFile[] cache_idx = new RandomAccessFile[5];
    public static boolean sunjava;
    public static Applet mainapp = null;
    private static boolean active;
    private static int threadliveid;
    private static InetAddress socketip;
    private static int socketreq;
    private static Socket socket = null;
    private static int threadreqpri = 1;
    private static Runnable threadreq = null;
    private static String dnsreq = null;
    public static String dns = null;
    private static String urlreq = null;
    private static DataInputStream urlstream = null;
    private static int savelen;
    private static String savereq = null;
    private static byte[] savebuf = null;
    private static boolean midiplay;
    private static int midipos;
    public static String midi = null;
    public static int midivol;
    public static int midifade;
    private static boolean waveplay;
    private static int wavepos;
    public static int wavevol;
    public static boolean reporterror = true;
    public static String errorname = "";

    public static final void startpriv(InetAddress inetaddress) {
        threadliveid = (int) (Math.random() * 99999999.0D);
        if (active) {
            try {
                Thread.sleep(500L);
            } catch (Exception _ex) {
            }
            active = false;
        }
        socketreq = 0;
        threadreq = null;
        dnsreq = null;
        savereq = null;
        urlreq = null;
        socketip = inetaddress;
        Thread thread = new Thread(new SignLink());
        thread.setDaemon(true);
        thread.start();
        while (!active)
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
    }

    public void run() {
        active = true;
        String s = findcachedir();
        uid = getuid(s);
        try {
            File file = new File(s + "main_file_cache.dat");
            if ((file.exists()) && (file.length() > 52428800L)) {
                file.delete();
            }
            cache_dat = new RandomAccessFile(s + "main_file_cache.dat", "rw");
            for (int j = 0; j < 5; j++) {
                cache_idx[j] = new RandomAccessFile(s + "main_file_cache.idx" + j, "rw");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        for (int i = threadliveid; threadliveid == i; ) {
            if (socketreq != 0) {
                try {
                    socket = new Socket(socketip, socketreq);
                } catch (Exception _ex) {
                    socket = null;
                }
                socketreq = 0;
            } else if (threadreq != null) {
                Thread thread = new Thread(threadreq);
                thread.setDaemon(true);
                thread.start();
                thread.setPriority(threadreqpri);
                threadreq = null;
            } else if (dnsreq != null) {
                try {
                    dns = InetAddress.getByName(dnsreq).getHostName();
                } catch (Exception _ex) {
                    dns = "unknown";
                }
                dnsreq = null;
            } else if (savereq != null) {
                if (savebuf != null)
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream(s + savereq);
                        fileoutputstream.write(savebuf, 0, savelen);
                        fileoutputstream.close();
                    } catch (Exception _ex) {
                    }
                if (waveplay) {
                    String wave = s + savereq;
                    waveplay = false;

                    System.out.println("WAVE " + wave);
                    AudioInputStream audioInputStream = null;
                    try {
                        audioInputStream = AudioSystem.getAudioInputStream(new File(wave));
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                        return;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        return;
                    }

                    AudioFormat format = audioInputStream.getFormat();
                    SourceDataLine auline = null;
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                    try {
                        auline = (SourceDataLine) AudioSystem.getLine(info);
                        auline.open(format);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }

                    if (auline.isControlSupported(FloatControl.Type.PAN)) {
                        FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
                        if (curPosition == SignLink.Position.RIGHT)
                            pan.setValue(1.0F);
                        else if (curPosition == SignLink.Position.LEFT) {
                            pan.setValue(-1.0F);
                        }
                    }

                    auline.start();
                    int nBytesRead = 0;
                    byte[] abData = new byte[524288];
                    try {
                        while (nBytesRead != -1) {
                            nBytesRead = audioInputStream.read(abData, 0, abData.length);
                            if (nBytesRead >= 0)
                                auline.write(abData, 0, nBytesRead);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    } finally {
                        auline.drain();
                        auline.close();
                    }
                }

                if (midiplay) {
                    midi = s + savereq;
                    try {
                        if (musicSr != null) {
                            musicSr.stop();
                            musicSr.close();
                        }
                        musicSr = null;
                        musicS = null;

                        File music = new File(midi);
                        if (music.exists()) {
                            musicS = MidiSystem.getSequence(music);
                        }

                        musicSr = MidiSystem.getSequencer();
                        musicSr.open();
                        musicSr.setSequence(musicS);
                        musicSr.setLoopCount(-1);
                        musicSr.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    midiplay = false;
                }

                savereq = null;
            } else if (urlreq != null) {
                try {
                    System.out.println("urlstream");
                    urlstream = new DataInputStream(new URL(mainapp.getCodeBase(), urlreq).openStream());
                } catch (Exception _ex) {
                    urlstream = null;
                }
                urlreq = null;
            }
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }

    /*public static String findcachedir() {
        File file = new File(System.getProperty("user.home") + "/.2006Scape/");
        if ((!file.exists()) &&
                (!file.mkdir())) {
            return secondDir();
        }

        return System.getProperty("user.home") + "/.2006Scape/";
    }*/
	
	public static String findcachedir() {
        File file = new File("./Orion_Cache/");
        if ((!file.exists()) &&
                (!file.mkdir())) {
            return secondDir();
        }

        return "./Orion_Cache/";
    }

    public static String secondDir() {
        File file = new File("c:/.Cache/");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString();
    }

    private static int getuid(String s) {
        try {
            File file = new File(s + "uid.dat");
            if ((!file.exists()) || (file.length() < 4L)) {
                DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(s + "uid.dat"));
                dataoutputstream.writeInt((int) (Math.random() * 99999999.0D));
                dataoutputstream.close();
            }
        } catch (Exception _ex) {
        }
        try {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(s + "uid.dat"));
            int i = datainputstream.readInt();
            datainputstream.close();
            return i + 1;
        } catch (Exception _ex) {
        }
        return 0;
    }

    public static synchronized Socket opensocket(int i) throws IOException {
        for (socketreq = i; socketreq != 0; )
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        if (socket == null) {
            throw new IOException("could not open socket");
        }
        return socket;
    }

    public static synchronized DataInputStream openurl(String s) throws IOException {
        for (urlreq = s; urlreq != null; )
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        if (urlstream == null) {
            throw new IOException("could not open: " + s);
        }
        return urlstream;
    }

    public static synchronized void dnslookup(String s) {
        dns = s;
        dnsreq = s;
    }

    public static synchronized void startthread(Runnable runnable, int i) {
        threadreqpri = i;
        threadreq = runnable;
    }

    public static synchronized boolean wavesave(byte[] abyte0, int i) {
        if (i > 2000000) {
            return false;
        }
        if (savereq != null) {
            return false;
        }
        wavepos = (wavepos + 1) % 5;
        savelen = i;
        savebuf = abyte0;
        waveplay = true;
        savereq = "sound" + wavepos + ".wav";
        return true;
    }

    public static synchronized boolean wavereplay() {
        if (savereq != null) {
            return false;
        }
        savebuf = null;
        waveplay = true;
        savereq = "sound" + wavepos + ".wav";
        return true;
    }

    public static synchronized void midisave(byte[] abyte0, int i) {
        if (i > 2000000) {
            return;
        }
        if (savereq == null) {
            midipos = (midipos + 1) % 5;
            savelen = i;
            savebuf = abyte0;
            midiplay = true;
            savereq = "jingle" + midipos + ".mid";
        }
    }

    public static void reporterror(String s) {
        System.out.println("Error: " + s);
    }

    static enum Position {
        LEFT, RIGHT, NORMAL;
    }
}