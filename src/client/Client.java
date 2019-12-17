package client;

import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import Editor.ItemSpawn;
import Editor.NpcSpawn;
import Editor.ObjectSpawn;
import Editor.Packer;
import Editor.Position;

public class Client extends RSApplet {

	public boolean editMode = true;
	public static final int CLIENT_VERSION = 14;//increase this, when client update is reguired
	public boolean developMode = true;//show ids for stuff etc. (admin accounts)
	static boolean isEasyOrionClient = false;//changes port to connect to other server
	boolean isLocalClient = true;//connects to localhost if true!!!
	boolean dumpItemImages = false;//dump item images
	boolean dumpNpcImages = false;//dump npc images
	public static boolean isWinter = false;//changes grass to snow for xmas event (handled automatically)
	public static boolean isHween = false;//changes grass to dark wildy-like and water to swamp water for hween event (handled automatically)
	TimeZone timeZone = TimeZone.getTimeZone("Europe/Helsinki");//change to timezone of the server!
	
	int yellNameColor = 0xffffff;
	int yellChatColor = 0x000000;
	
	public static int gameframeVersion = 317;
	
	//theme musics:
		int RS_THEME = 0;
		int OLD_THEME = 400;
		int HWEEN_THEME = 321;
		int XMAS_THEME = 547;
		int FARMING_THEME = 466;
		//int HUNTER_THEME = 207;
		//int SUMMON_THEME = 457;
	//
	
	public int defaultThemeSong = OLD_THEME;
	public int loginscreenBackground = 0;
	
	
	public int themeSong = 0;
	
	public void sendCheatDetectionToServer(int type){//1 = Autoclick
		if(!loggedIn)
			return;
		this.stream.createFrame(11);
        this.stream.writeWordBigEndian(type);
	}
	
	public String splitToMultiLines(String string, int limit, TextDrawingArea textDrawingArea){
		int Width = textDrawingArea.getTextWidth(string);
		if(Width > limit){
			String[] stringParts = string.split(" ");
			String tempString = "";
			String finalString = "";
			int tempWidth = 0;
			for(int i = 0; i < stringParts.length; i++){
				tempString = stringParts[i]+" ";
				tempWidth += textDrawingArea.getTextWidth(tempString);
				if(tempWidth <= limit){
					finalString += tempString;
					continue;
				}
				finalString += "\\n";
				tempString = stringParts[i]+" ";
				finalString += tempString;
				tempWidth = textDrawingArea.getTextWidth(tempString);
			}
			return finalString;
		}
		return string;
	}
	
	public byte[] getMACaddress(){
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			return network.getHardwareAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void checkSpecialTheme(){
		themeSong = defaultThemeSong;
		isWinter = false;
		isHween = false;
		loginscreenBackground = 0;
		if(getDaysToXmas() <= 7){
			themeSong = XMAS_THEME;
			isWinter = true;
			loginscreenBackground = 1;
		}
		if(getDaysToHween() <= 7){
			themeSong = HWEEN_THEME;
			isHween = true;
			//loginscreenBackground = 2;
		}
	}
	
	public int getDaysToXmas(){
		Calendar now = Calendar.getInstance();
		Calendar xmass = Calendar.getInstance();
		xmass.set(Calendar.MONTH, Calendar.DECEMBER);
		xmass.set(Calendar.DAY_OF_MONTH, 24);
		now.setTimeZone(timeZone);
		xmass.setTimeZone(timeZone);
		int withDAY = xmass.get(Calendar.DAY_OF_YEAR);
		int toDAY = now.get(Calendar.DAY_OF_YEAR);
		int diffDay =  (int) Math.abs(toDAY  - withDAY);
		return diffDay;
	}
	
	public int getDaysToHween(){
		Calendar now = Calendar.getInstance();
		Calendar hween = Calendar.getInstance();
		hween.set(Calendar.MONTH, Calendar.OCTOBER);
		hween.set(Calendar.DAY_OF_MONTH, 31);
		now.setTimeZone(timeZone);
		hween.setTimeZone(timeZone);
		int withDAY = hween.get(Calendar.DAY_OF_YEAR);
		int toDAY = now.get(Calendar.DAY_OF_YEAR);
		int diffDay =  (int) Math.abs(toDAY  - withDAY);
		return diffDay;
	}
	
	int lastGESearchItem = -1;
	
	public boolean displayScrollbar;
	
	public void displayItemSearch() {
		try {
			if (amountOrNameInput != "") {
				itemSearch(amountOrNameInput);
			}
			TextDrawingArea textDrawingArea = this.aTextDrawingArea_1271;
			
			int limit_1 = 8;
			int limit_2 = 126;
			int limit_3 = 30;
			int limit_4 = 114;
			int limit_5 = 489;
			int limit_6 = 495;
			int limit_7 = 345;
			if(gameframeVersion != 474){
				limit_1 = 5;
				limit_2 = 90;
				limit_3 = 10;
				limit_4 = 77;
				limit_5 = 463;
				limit_6 = 478;
				limit_7 = 357;
			}
			cacheSprite[51].drawSprite(18, 18);
			for (int j = 0; j < totalItemResults; j++) {
				int x = super.mouseX;
				int y = super.mouseY;
				final int yPos = j * 14 - itemResultScrollPos + 14;
				if (yPos > 0 && yPos < (limit_4+1)) {
					textDrawingArea.method385(0xA05A00, capitalizeFirstChar(itemResultNames[j]), yPos, 77);
					if(x >= 83 && x <= limit_6 && y > limit_7+yPos-13 && y < limit_7+yPos+2 && !menuOpen) {
						DrawingArea.method335(0x807660, yPos - 12, 431, 15, 60, 75);
						Sprite itemImg = ItemDef.getSprite(itemResultIDs[j], 1, 0);
						lastGESearchItem = itemResultIDs[j];
						if(itemImg != null)
							itemImg.drawSprite(22, 20);
						if(myPrivilege >= 2)
							textDrawingArea.method385(0, "Id: "+itemResultIDs[j], limit_2-16, 10);
					}
				}
				if(menuOpen && lastGESearchItem != -1){
					Sprite itemImg = ItemDef.getSprite(lastGESearchItem, 1, 0);
					if(itemImg != null)
						itemImg.drawSprite(22, 20);
					if(myPrivilege >= 2)
						textDrawingArea.method385(0, "Id: "+lastGESearchItem, limit_2-16, 10);
				}
			}
			DrawingArea.method336(limit_2-12, 0, 74, 0x807660, 2);//line
			DrawingArea.defaultDrawingAreaSize();
			if (totalItemResults > limit_1) {
				displayScrollbar = true;
				this.method30(limit_4, itemResultScrollPos, 0, limit_5, (totalItemResults) * 14);
			} else {
				displayScrollbar = false;
			}
			if (amountOrNameInput.length() == 0) {
				this.chatTextDrawingArea.drawText(0xA05A00, "Grand Exchange Item Search", limit_3, 280);
				this.aTextDrawingArea_1270.method382(0xA05A00, 280, "To search for an item, start by typing part of it's name.", limit_3+35, false);
				this.aTextDrawingArea_1270.method382(0xA05A00, 280, "Then, simply select the item you want from the results on display.", limit_3+50, false);
			} else if (totalItemResults == 0) {
				this.aTextDrawingArea_1270.method382(0xA05A00, 280, "No matching items found!", limit_3+35, false);
			}
			DrawingArea.method335(0x807660, limit_2-12, 506, 15, 120, 4);//box
			
			textDrawingArea.method389(true, 25, 0xffffff, amountOrNameInput + "*", limit_2);
			cacheSprite[52].drawSprite(4, limit_2-11);
			DrawingArea.method339(limit_2-12, 0x807660, 506, 0);//line
			
		} catch (Exception e) {
		}
	}
	
	private void buildItemSearch(int mouseY) {
		int y = 0;
		int limit_4 = 114;
		int limit_6 = 495;
		int limit_7 = 345;
		if(gameframeVersion != 474){
			limit_4 = 77;
			limit_6 = 478;
			limit_7 = 357;
		}
		for(int idx = 0; idx < 100; idx++) {
			if (amountOrNameInput.length() == 0 || amountOrNameInput == "" || totalItemResults == 0 )
				return;
			String name = capitalizeFirstChar(itemResultNames[idx]);
			int textY = y * 14 - itemResultScrollPos;
			if (textY > 0 && textY < (limit_4+1)) {
			if(mouseY > limit_7+textY - 13 && mouseY < limit_7+textY+2 && super.mouseX >= 83 && super.mouseX <= limit_6) {
				/*if(myPrivilege >= 2) {
					menuActionName[menuActionRow] = "Spawn";
					menuActionID[menuActionRow] = 1251;
					menuActionRow++;
				}*/
				menuActionName[menuActionRow] = "Select";
				menuActionID[menuActionRow] = 1250;
				menuActionRow++;
			}
			}
			y++;
		}
	}
	
	public static String capitalizeFirstChar(String s) {
		try {
			String n;
			if(s != "")
				return n = (s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase()).trim();
		} catch (Exception e) {}
		return s;
	}
	
	public int totalItemResults;
	public String itemResultNames[] = new String[100];
	public int itemResultIDs[] = new int[100];
	public int itemResultScrollPos;
	
	public void itemSearch(String itemName) {
		if (itemName == null || itemName.length() == 0) {
			totalItemResults = 0;
			return;
		}
		String searchName = itemName;
		String searchParts[] = new String[100];
		int totalResults = 0;
		do {
			int regex = searchName.indexOf(" ");
			if (regex == -1) {
				break;
			}
			String part = searchName.substring(0, regex).trim();
			if (part.length() > 0) {
				searchParts[totalResults++] = part.toLowerCase();
			}
			searchName = searchName.substring(regex + 1);
		} while (true);
		searchName = searchName.trim();
		if (searchName.length() > 0) {
			searchParts[totalResults++] = searchName.toLowerCase();
		}
		totalItemResults = 0;
		label0: for (int id = 0; id < ItemDef.totalItems; id++) {
			ItemDef item = ItemDef.forID(id);
			if(!item.isOnGe)
				continue;
			/*if (item.certTemplateID != -1 || item.name == null) {
				continue;
			}
			if(item.name.equals("PLACE HOLDER"))
				continue;*/
			//new checks below - does not include stackable items
			/*if(item.certID == -1)
				continue;
			item = ItemDef.forID(item.certID);
			if (item.certTemplateID != -1 || item.name == null) {
				continue;
			}*/
			String resultName = item.name.toLowerCase();
			for (int index = 0; index < totalResults; index++) {
				if (resultName.indexOf(searchParts[index]) == -1) {
					continue label0;
				}
			}
			itemResultNames[totalItemResults] = resultName;
			itemResultIDs[totalItemResults] = item.id;
			totalItemResults++;
			if (totalItemResults >= itemResultNames.length) {
				return;
			}
		}
	}
	
	public static void writeSettings() throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(SignLink.findcachedir() + "settings.dat")));
		out.writeInt(gameframeVersion);
		out.writeInt(musicVolume);
		out.writeBoolean(isEasyOrionClient);
		out.close();
	}
	
	public static void readSettings() throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(SignLink.findcachedir() + "settings.dat")));
		gameframeVersion = in.readInt();
		musicVolume = in.readInt();
		isEasyOrionClient = in.readBoolean();
		in.close();
	}

	public String indexLocation(int cacheIndex, int index) {
		return SignLink.findcachedir() + "index" + cacheIndex + "/" + (index != -1 ? index + ".gz" : "");
	}

	public void repackCacheIndex(int cacheIndex) {
		System.out.println("Started repacking index " + cacheIndex + ".");
		int indexLength = new File(indexLocation(cacheIndex, -1)).listFiles().length;
		File[] file = new File(indexLocation(cacheIndex, -1)).listFiles();
		try {
			for (int index = 0; index < indexLength; index++) {
				int fileIndex = Integer.parseInt(getFileNameWithoutExtension(file[index].toString()));
				byte[] data = fileToByteArray(cacheIndex, fileIndex);
				if(data != null && data.length > 0) {
					decompressors[cacheIndex].method234(data.length, data, fileIndex);
					System.out.println("Repacked " + fileIndex + ".");
				} else {
					System.out.println("Unable to locate index " + fileIndex + ".");
				}
			}
		} catch(Exception e) {
			System.out.println("Error packing cache index " + cacheIndex + ".");
		}
		System.out.println("Finished repacking " + cacheIndex + ".");
	}

	public byte[] fileToByteArray(int cacheIndex, int index) {
		try {
			if (indexLocation(cacheIndex, index).length() <= 0 || indexLocation(cacheIndex, index) == null) {
				return null;
			}
			File file = new File(indexLocation(cacheIndex, index));
			byte[] fileData = new byte[(int)file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(fileData);
			fis.close();
			return fileData;
		} catch(Exception e) {
			return null;
		}
	}
	
	public static String getFileNameWithoutExtension(String fileName) {
		File tmpFile = new File(fileName);
		tmpFile.getName();
		int whereDot = tmpFile.getName().lastIndexOf('.');
		if (0 < whereDot && whereDot <= tmpFile.getName().length() - 2) {
			return tmpFile.getName().substring(0, whereDot);
		}
		return "";
	}
	
	public static Sprite cacheSprite[];
	public static Sprite cacheSpriteI[];
	
   public static final String NAME = "Orion";
   public static Client instance;
   public static String server;
   public static String serverIp2;
   public static int chatTypeView;
   public int ignoreCount;
   public long aLong824;
   public int[][] anIntArrayArray825;
   public int[] friendsNodeIDs;
   public NodeList[][][] groundArray;
   public int[] anIntArray828;
   public int[] anIntArray829;
   public volatile boolean aBoolean831;
   public Socket aSocket832;
   public int loginScreenState;
   public Stream aStream_834;
   public NPC[] npcArray;
   public int npcCount;
   public int[] npcIndices;
   public int anInt839;
   public int[] anIntArray840;
   public int anInt841;
   public int anInt842;
   public int anInt843;
   public String aString844;
   public int publicChatMode;
   public int privateChatMode;
   public Stream aStream_847;
   public boolean aBoolean848;
   public static int anInt849;
   public int[] anIntArray850;
   public int[] anIntArray851;
   public int[] anIntArray852;
   public int[] anIntArray853;
   public static int anInt854;
   public int anInt855;
   public int openInterfaceID;
   public int xCameraPos;
   public int zCameraPos;
   public int yCameraPos;
   public int yCameraCurve;
   public int xCameraCurve;
   public int myPrivilege;
   public boolean myDonor = false;
   public final int[] currentExp;
   public Background redStone1_3;
   public Background redStone2_3;
   public Background redStone3_2;
   public Background redStone1_4;
   public Background redStone2_4;
   public Sprite multiOverlay;
   public Sprite mapFlag;
   public Sprite mapMarker;
   public boolean aBoolean872;
   public final int[] anIntArray873;
   public int anInt874;
   public final boolean[] aBooleanArray876;
   public int weight;
   public MouseDetection mouseDetection;
   public volatile boolean drawFlames;
   public String reportAbuseInput;
   public int unknownInt10;
   public boolean menuOpen;
   public int anInt886;
   public String inputString;
   public final int maxPlayers;
   public static byte[] musicData;
   public final int myPlayerIndex;
   public Player[] playerArray;
   public int playerCount;
   public int[] playerIndices;
   public int anInt893;
   public int[] anIntArray894;
   public Stream[] aStreamArray895s;
   public int anInt896;
   public int anInt897;
   public int friendsCount;
   public int anInt900;
   public int[][] anIntArrayArray901;
   public final int anInt902;
   public RSImageProducer backLeftIP1;
   public RSImageProducer backLeftIP2;
   public RSImageProducer backRightIP1;
   public RSImageProducer backRightIP2;
   public RSImageProducer backTopIP1;
   public RSImageProducer backVmidIP1;
   public RSImageProducer backVmidIP2;
   public RSImageProducer backVmidIP3;
   public RSImageProducer backVmidIP2_2;
   public byte[] aByteArray912;
   public int anInt913;
   public int crossX;
   public int crossY;
   public int crossIndex;
   public int crossType;
   public int plane;
   public final int[] currentStats;
   public static int anInt924;
   public final long[] ignoreListAsLongs;
   public boolean loadingError;
   public final int anInt927;
   public final int[] anIntArray928;
   public int[][] anIntArrayArray929;
   public Sprite aClass30_Sub2_Sub1_Sub1_931;
   public Sprite aClass30_Sub2_Sub1_Sub1_932;
   public int anInt933;
   public int anInt934;
   public int anInt935;
   public int anInt936;
   public int anInt937;
   public int anInt938;
   public static int anInt940;
   public final int[] chatTypes;
   public final String[] chatNames;
   public final String[] chatMessages;
   public int anInt945;
   public WorldController worldController;
   public Background[] sideIcons;
   public int menuScreenArea;
   public int menuOffsetX;
   public int menuOffsetY;
   public int menuWidth;
   public int anInt952;
   public long aLong953;
   public boolean aBoolean954;
   public long[] friendsListAsLongs;
   public int currentSong;
   public static int nodeID = 10;
   static int portOff;
   public static boolean isMembers = true;
   public static boolean lowMem;
   public volatile boolean drawingFlames;
   public int spriteDrawX;
   public int spriteDrawY;
   public final int[] anIntArray965 = new int[]{16776960, 16711680, '\uff00', '\uffff', 16711935, 16777215};
   public Background aBackground_966;
   public Background aBackground_967;
   public static int[] anIntArray968;
   public final int[] anIntArray969;
   final Decompressor[] decompressors;
   public int[] variousSettings;
   public static int spellID = 0;
   public boolean aBoolean972;
   public final int anInt975;
   public final int[] anIntArray976;
   public final int[] anIntArray977;
   public final int[] anIntArray978;
   public final int[] anIntArray979;
   public final int[] anIntArray980;
   public final int[] anIntArray981;
   public final int[] anIntArray982;
   public final String[] aStringArray983;
   public int anInt984;
   public int anInt985;
   public static int anInt986;
   public Sprite[] hitMarks;
   public int anInt988;
   public int anInt989;
   public final int[] anIntArray990;
   public static boolean aBoolean993;
   public final boolean aBoolean994;
   public int anInt995;
   public int anInt996;
   public int anInt997;
   public int anInt998;
   public int anInt999;
   public ISAACRandomGen encryption;
   public Sprite mapEdge;
   public final int anInt1002;
   static final int[][] anIntArrayArray1003 = new int[][]{{6798, 107, 10283, 16, 4797, 7744, 5799, 4634, '\u83a1', 22433, 2983, '\ud3b1'}, {8741, 12, '\ufa1e', '\ua89a', 7735, 8404, 1701, '\u961e', 24094, 10153, '\udd2d', 4783, 1341, 16578, '\u88bb', 25239}, {25238, 8742, 12, '\ufa1e', '\ua89a', 7735, 8404, 1701, '\u961e', 24094, 10153, '\udd2d', 4783, 1341, 16578, '\u88bb'}, {4626, 11146, 6439, 12, 4758, 10270}, {4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574}};
   public String amountOrNameInput;
   public static int anInt1005;
   public int daysSinceLastLogin;
   public int donatorPoints;
   public int pktSize;
   public int pktType;
   public int anInt1009;
   public int anInt1010;
   public int anInt1011;
   public NodeList aClass19_1013;
   public int anInt1014;
   public int anInt1015;
   public int anInt1016;
   public boolean aBoolean1017;
   public int anInt1018;
   public static final int[] anIntArray1019 = new int[99];
   public int anInt1021;
   public int anInt1022;
   public int loadingStage;
   public Background scrollBar1;
   public Background scrollBar2;
   public int anInt1026;
   public Background backBase1;
   public Background backBase2;
   public Background backHmid1;
   public final int[] anIntArray1030;
   public boolean aBoolean1031;
   public Sprite[] mapFunctions;
   public int baseX;
   public int baseY;
   public int anInt1036;
   public int anInt1037;
   public int loginFailures;
   public int anInt1039;
   public int anInt1040;
   public int anInt1041;
   public int dialogID;
   public final int[] maxStats;
   public int[] anIntArray1045;
   public int anInt1046;
   public boolean aBoolean1047;
   public int anInt1048;
   public String aString1049;
   public static int anInt1051;
   public static int[] anIntArray1052;
   public StreamLoader titleStreamLoader;
   public int anInt1054;
   public int anInt1055;
   public NodeList aClass19_1056;
   public static int[] anIntArray1057;
   public final RSInterface aClass9_1059;
   public Background[] mapScenes;
   public static int anInt1061;
   public int soundCount;
   public final int anInt1063;
   public int friendsListAction;
   public final int[] anIntArray1065;
   public int mouseInvInterfaceIndex;
   public int lastActiveInvInterface;
   public OnDemandFetcher onDemandFetcher;
   public int anInt1069;
   public int anInt1070;
   public int anInt1071;
   public int[] anIntArray1072;
   public int[] anIntArray1073;
   public Sprite mapDotItem;
   public Sprite mapDotNPC;
   public Sprite mapDotPlayer;
   public Sprite mapDotFriend;
   public Sprite mapDotTeam;
   public Sprite mapDotStaff;
   public int anInt1079;
   public boolean aBoolean1080;
   public String[] friendsList;
   public Stream inStream;
   public int anInt1084;
   public int anInt1085;
   public int activeInterfaceType;
   public int anInt1087;
   public int anInt1088;
   public int anInt1089;
   public final int[] expectedCRCs;
   public int[] menuActionCmd2;
   public int[] menuActionCmd3;
   public int[] menuActionID;
   public int[] menuActionCmd1;
   public Sprite[] headIcons;
   public Sprite[] skullIcons;
   public Sprite[] headIconsHint;
   public static int anInt1097;
   public int anInt1098;
   public int anInt1099;
   public int anInt1100;
   public int anInt1101;
   public int anInt1102;
   public boolean tabAreaAltered;
   public int anInt1104;
   public RSImageProducer aRSImageProducer_1107;
   public RSImageProducer aRSImageProducer_1108;
   public RSImageProducer aRSImageProducer_1109;
   public RSImageProducer aRSImageProducer_1110;
   public RSImageProducer aRSImageProducer_1111;
   public RSImageProducer aRSImageProducer_1112;
   public RSImageProducer aRSImageProducer_1113;
   public RSImageProducer aRSImageProducer_1114;
   public RSImageProducer aRSImageProducer_1115;
   public static int anInt1117;
   public int membersInt;
   public String aString1121;
   public static Sprite compass;
   public static Sprite compass_a;
   public RSImageProducer aRSImageProducer_1123;
   public RSImageProducer aRSImageProducer_1124;
   public RSImageProducer aRSImageProducer_1125;
   public RSImageProducer aRSImageProducer_oma1;
   public RSImageProducer aRSImageProducer_oma2;
   public RSImageProducer aRSImageProducer_oma3;
   public RSImageProducer aRSImageProducer_oma4;
   public RSImageProducer aRSImageProducer_oma5;
   public RSImageProducer aRSImageProducer_oma6;
   public RSImageProducer aRSImageProducer_oma7;
   public RSImageProducer aRSImageProducer_oma8;
   public static Player myPlayer;
   public final String[] atPlayerActions;
   public final boolean[] atPlayerArray;
   public final int[][][] anIntArrayArrayArray1129;
   public final int[] tabInterfaceIDs = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   public int anInt1131;
   public int anInt1132;
   public int menuActionRow;
   public static int anInt1134;
   public int spellSelected;
   public int anInt1137;
   public int spellUsableOn;
   public String spellTooltip;
   public Sprite[] aClass30_Sub2_Sub1_Sub1Array1140;
   public boolean aBoolean1141;
   public static int anInt1142;
   public Background redStone1;
   public Background redStone2;
   public Background redStone3;
   public Background redStone1_2;
   public Background redStone2_2;
   public int energy;
   public boolean aBoolean1149;
   public Sprite[] crosses;
   public boolean musicEnabled;
   public Background[] aBackgroundArray1152s;
   public boolean needDrawTabArea;
   public static int anInt1401 = 256;
   public static int[] anIntArray385 = new int[]{12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800, 12800};
   public static int anInt720 = 0;
   public static Class56 aClass56_749;
   public static boolean fetchMusic = false;
   public static int musicVolume2;
   public static int anInt478 = -1;
   public static byte[] aByteArray347;
   public static int anInt155 = 0;
   public static int anInt2200 = 0;
   public static int anInt1478;
   public static boolean aBoolean475;
   public static int anInt116;
   public static boolean aBoolean995;
   public static int anInt139;
   public static int musicVolume = 255;
   public int[] fullGameScreenOffsets;
   public int anInt1170;
   public int anInt1215;
   public int anInt1083;
   public int anInt992;
   public int anInt1273;
   public int anInt1075;
   public int anInt1034;
   public static int anInt197;
   public static Class5 aClass5_932;
   public static long aLong1432;
   public static Class aClass1418;
   public static int anInt1526;
   public static Class3_Sub7 aClass3_Sub7_1345;
   public static int anInt1408;
   public static Class3_Sub7_Sub1 aClass3_Sub7_Sub1_1493;
   public static int soundEffectVolume = 127;
   public static Sound[] aClass26Array1468 = new Sound[50];
   public static Class25 aClass25_1948;
   public int unreadMessages;
   public static int anInt1155;
   public static boolean fpsOn;
   public static boolean loggedIn;
   public boolean canMute;
   public boolean aBoolean1159;
   public boolean aBoolean1160;
   static int loopCycle;
   public static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:\'@#~,<.>/?\\| ";
   public RSImageProducer aRSImageProducer_1163;
   public RSImageProducer aRSImageProducer_1164;
   public RSImageProducer aRSImageProducer_1165;
   public RSImageProducer aRSImageProducer_1166;
   public int daysSinceRecovChange;
   public RSSocket socketStream;
   public int minimapInt3;
   public int anInt1171;
   public long aLong1172;
   public String myUsername;
   public String myPassword;
   public static int anInt1175;
   public boolean genericLoadingError;
   public final int[] anIntArray1177 = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
   public int reportAbuseInterfaceID;
   public NodeList aClass19_1179;
   public static int[] anIntArray1180;
   public static int[] anIntArray1181;
   public static int[] anIntArray1182;
   public byte[][] aByteArrayArray1183;
   public int anInt1184;
   public int minimapInt1;
   public int anInt1186;
   public int anInt1187;
   public static int anInt1188;
   public int invOverlayInterfaceID;
   public int[] anIntArray1190;
   public int[] anIntArray1191;
   public Stream stream;
   public int anInt1193;
   public int splitpublicChat;
   public Background invBack;
   public static Background mapBack;
   public static Background[] mapBacks;
   public Background chatBack;
   public String[] menuActionName;
   public Sprite aClass30_Sub2_Sub1_Sub1_1201;
   public Sprite aClass30_Sub2_Sub1_Sub1_1202;
   public final int[] anIntArray1203;
   static final int[] anIntArray1204 = new int[]{9104, 10275, 7595, 3610, 7975, 8526, 918, '\u9792', 24466, 10145, '\ue51e', 5027, 1457, 16565, '\u88af', 25486};
   public static boolean flagged;
   public final int[] sound;
   public int anInt1208;
   public int minimapInt2;
   public int anInt1210;
   public int anInt1211;
   public String promptInput;
   public int anInt1213;
   public int[][][] intGroundArray;
   public long aLong1215;
   public int loginScreenCursorPos;
   public final Background[] modIcons;
   public long aLong1220;
   public int tabID;
   public int anInt1222;
   public boolean inputTaken;
   public int inputDialogState;
   public static int anInt1226;
   public int nextSong;
   public boolean songChanging;
   public static int[] anIntArray1229;
   public CollisionMap[] aClass11Array1230;
   public static int[] anIntArray1232;
   public boolean aBoolean1233;
   public int[] anIntArray1234;
   public int[] anIntArray1235;
   public int[] anIntArray1236;
   public int anInt1237;
   public int anInt1238;
   public final int anInt1239 = 100;
   public final int[] soundType;
   public boolean aBoolean1242;
   public int atInventoryLoopCycle;
   public int atInventoryInterface;
   public int atInventoryIndex;
   public int atInventoryInterfaceType;
   public byte[][] aByteArrayArray1247;
   public int tradeMode;
   public int anInt1249;
   public final int[] soundDelay;
   public int anInt1251;
   public final boolean rsAlreadyLoaded;
   public int anInt1253;
   public int anInt1254;
   public boolean welcomeScreenRaised;
   public boolean messagePromptRaised;
   public int anInt1257;
   public byte[][][] byteGroundArray;
   public int previousSong;
   public int destX;
   public int destY;
   public Sprite aClass30_Sub2_Sub1_Sub1_1263;
   public int anInt1264;
   public int anInt1265;
   public String loginMessage1;
   public String loginMessage2;
   public int anInt1268;
   public int anInt1269;
   public TextDrawingArea aTextDrawingArea_1270;
   public TextDrawingArea aTextDrawingArea_1271;
   public TextDrawingArea chatTextDrawingArea;
   public int anInt1275;
   public int backDialogID;
   public int anInt1278;
   public int anInt1279;
   public int[] bigX;
   public int[] bigY;
   public int itemSelected;
   public int anInt1283;
   public int anInt1284;
   public int anInt1285;
   public String selectedItemName;
   public static int anInt1288;
   public int anInt1289;
   public static int tiara;
   public static int anInt1290;


   public static String intToKOrMilLongName(int i) {
      String s = String.valueOf(i);

      for(int k = s.length() - 3; k > 0; k -= 3) {
         s = s.substring(0, k) + "," + s.substring(k);
      }

      if(s.length() > 8) {
         s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
      } else if(s.length() > 4) {
         s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
      }

      return " " + s;
   }

	public int drawCount;
	public int fullscreenInterfaceID;
	public int anInt1044;//377
	public int anInt1129;//377
	public int anInt1315;//377
	public int anInt1500;//377
	public int anInt1501;//377
	public static int[] fullScreenTextureArray;
	
	public void resetAllImageProducers() {
		if (super.fullGameScreen != null)
			return;
		aRSImageProducer_1166 = null;
		aRSImageProducer_1164 = null;
		aRSImageProducer_1163 = null;
		aRSImageProducer_1165 = null;
		aRSImageProducer_1123 = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		aRSImageProducer_oma1 = null;
		aRSImageProducer_oma2 = null;
		aRSImageProducer_oma3 = null;
		aRSImageProducer_oma4 = null;
		aRSImageProducer_oma5 = null;
		aRSImageProducer_oma6 = null;
		aRSImageProducer_oma7 = null;
		aRSImageProducer_oma8 = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		aRSImageProducer_1109 = null;
		aRSImageProducer_1110 = null;
		aRSImageProducer_1111 = null;
		aRSImageProducer_1112 = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		super.fullGameScreen = new RSImageProducer(765, 503, getGameComponent());
		welcomeScreenRaised = true;
    }
   
   public void stopMidi() {
      SignLink.midifade = 0;
      SignLink.midi = "stop";
   }

   public boolean menuHasAddFriend(int j) {
      if(j < 0) {
         return false;
      } else {
         int k = this.menuActionID[j];
         if(k >= 2000) {
            k -= 2000;
         }

         return k == 337;
      }
   }

   public void drawChannelButtons() {
		String text[] = { "On", "Friends", "Off", "Hide" };
		int textColor[] = { 65280, 0xffff00, 0xff0000, 65535 };
		switch(cButtonCPos) {
			case 0:
				cacheSprite[34].drawSprite(5, 4);
				break;
			case 1:
				cacheSprite[34].drawSprite(71, 4);
				break;
			case 2:
				cacheSprite[34].drawSprite(137, 4);
				break;
			case 3:
				cacheSprite[34].drawSprite(203, 4);
				break;
			case 4:
				cacheSprite[34].drawSprite(269, 4);
				break;
			case 5:
				cacheSprite[34].drawSprite(335, 4);
				break;
		}
		if(cButtonHPos == cButtonCPos) {
			switch(cButtonHPos) {
				case 0:
					cacheSprite[35].drawSprite(5, 4);
					break;
				case 1:
					cacheSprite[35].drawSprite(71, 4);
					break;
				case 2:
					cacheSprite[35].drawSprite(137, 4);
					break;
				case 3:
					cacheSprite[35].drawSprite(203, 4);
					break;
				case 4:
					cacheSprite[35].drawSprite(269, 4);
					break;
				case 5:
					cacheSprite[35].drawSprite(335, 4);
					break;
				case 6:
					cacheSprite[36].drawSprite(404, 4);
					break;
			}
		} else {
			switch(cButtonHPos) {
				case 0:
					cacheSprite[33].drawSprite(5, 4);
					break;
				case 1:
					cacheSprite[33].drawSprite(71, 4);
					break;
				case 2:
					cacheSprite[33].drawSprite(137, 4);
					break;
				case 3:
					cacheSprite[33].drawSprite(203, 4);
					break;
				case 4:
					cacheSprite[33].drawSprite(269, 4);
					break;
				case 5:
					cacheSprite[33].drawSprite(335, 4);
					break;
				case 6:
					cacheSprite[36].drawSprite(404, 4);
					break;
			}
		}
		this.aTextDrawingArea_1270.method389(true, 425, 0xffffff, "Report Abuse", 19);
		this.aTextDrawingArea_1270.method389(true, 26, 0xffffff, "All", 19);
		this.aTextDrawingArea_1270.method389(true, 86, 0xffffff, "Game", 19);
		this.aTextDrawingArea_1270.method389(true, 150, 0xffffff, "Public", 14);
		this.aTextDrawingArea_1270.method389(true, 212, 0xffffff, "Private", 14);
		this.aTextDrawingArea_1270.method389(true, 286, 0xffffff, "Clan", 14);
		this.aTextDrawingArea_1270.method389(true, 349, 0xffffff, "Trade", 14);
		this.aTextDrawingArea_1270.method382(textColor[publicChatMode], 164, text[publicChatMode], 25, true);
		this.aTextDrawingArea_1270.method382(textColor[privateChatMode], 230, text[privateChatMode], 25, true);
		//this.aTextDrawingArea_1270.method382(textColor[clanChatMode], 296, text[clanChatMode], 163, true);
		this.aTextDrawingArea_1270.method382(textColor[tradeMode], 362, text[tradeMode], 25, true);
	}
   
   public void drawChatArea() {
      this.aRSImageProducer_1166.initDrawingArea();
      Texture.anIntArray1472 = this.anIntArray1180;
	  if(gameframeVersion != 474)
			this.chatBack.method361(0, 0);
		else
			this.cacheSprite[15].drawSprite(0, 0);
		int off = 0;
		if(gameframeVersion == 474)
			off = 15;
		TextDrawingArea textDrawingArea = this.aTextDrawingArea_1271;
      if(this.messagePromptRaised) {
         this.chatTextDrawingArea.drawText(0, this.aString1121, 40+off, 239+off);
         this.chatTextDrawingArea.drawText(128, this.promptInput + "*", 60+off, 239+off);
      } else if(this.inputDialogState == 1) {
         this.chatTextDrawingArea.drawText(0, "Enter amount:", 40+off, 239+off);
         this.chatTextDrawingArea.drawText(128, this.amountOrNameInput + "*", 60+off, 239+off);
      } else if(this.inputDialogState == 2) {
		if(openInterfaceID == 5292){//bank search
			this.chatTextDrawingArea.drawText(0, "Enter the name of the item you wish to search for:", 40+off, 239+off);
		} else {
         this.chatTextDrawingArea.drawText(0, "Enter name:", 40+off, 239+off);
		}
         this.chatTextDrawingArea.drawText(128, this.amountOrNameInput + "*", 60+off, 239+off);
         
      } else if (inputDialogState == 3) {
    	  		displayItemSearch();		
      } else if(this.aString844 != null) {
         this.chatTextDrawingArea.drawText(0, this.aString844, 40+off, 239+off);
         this.chatTextDrawingArea.drawText(128, "Click to continue", 60+off, 239+off);
      } else if(this.backDialogID != -1) {
         this.drawInterface(0, 0+off, RSInterface.interfaceCache[this.backDialogID], 0+off);
      } else if(this.dialogID != -1) {
         this.drawInterface(0, 0+off, RSInterface.interfaceCache[this.dialogID], 0+off);
      } else {
         int j = 0;
		 int j77 = -3;
		 int limit = 110;
		 if(gameframeVersion != 474){
			DrawingArea.setDrawingArea(77, 0, 463, 0);
		 }else{
			DrawingArea.setDrawingArea(115, 0, 463, 0);
			limit = 210;
		 }

         for(int s = 0; s < 100; ++s) {
            if(this.chatMessages[s] != null) {
               int l = this.chatTypes[s];
			   int i1 = 70 - j * 14 + this.anInt1089;
			   if(gameframeVersion == 474)
				i1 = 70 - j77 * 14 + this.anInt1089 - 2;
               String s1 = this.chatNames[s];
               byte byte0 = 0;
               if(s1 != null && s1.startsWith("@cr1@")) {
                  s1 = s1.substring(5);
                  byte0 = 1;
               }

               if(s1 != null && s1.startsWith("@cr2@")) {
                  s1 = s1.substring(5);
                  byte0 = 2;
               }

			   if(s1 != null && s1.startsWith("@don1@")) {
                  s1 = s1.substring(6);
                  byte0 = 3;
               }
			   
               if(l == 0) {
				if (chatTypeView == 5 || chatTypeView == 0) {
                  if(i1 > 0 && i1 < limit) {
                	textDrawingArea.method385(0, this.chatMessages[s], i1, 4);
					 //textDrawingArea.method389(false, 4, 0, chatMessages[s], i1);//supports color tags
                  }
				++j77;
                  ++j;
				 }
               }

               int k1;
               if((l == 1 || l == 2 || l == 98) && (l == 1 || this.publicChatMode == 0 || this.publicChatMode == 1 && this.isFriendOrSelf(s1))) {
				if (chatTypeView == 1 || chatTypeView == 0) {
                  if(i1 > 0 && i1 < limit) {
                     k1 = 4;
                     if(byte0 == 1) {
                        this.modIcons[0].method361(k1, i1 - 12);
                        k1 += 14;
                     }

                     if(byte0 == 2) {
                        this.modIcons[1].method361(k1, i1 - 12);
                        k1 += 14;
                     }
					 
					 if(byte0 == 3) {
                        this.modIcons[2].method361(k1, i1 - 12);
                        k1 += 14;
                     }
					 
					 if(l != 98){
						 textDrawingArea.method385(0, s1 + ":", i1, k1);
						 k1 += textDrawingArea.getTextWidth(s1) + 8;
						 textDrawingArea.method385(255, this.chatMessages[s], i1, k1);
					 }else{//yell
						 textDrawingArea.method385(yellNameColor, s1 + ":", i1, k1);
	                     k1 += textDrawingArea.getTextWidth(s1) + 8;
	                     textDrawingArea.method385(yellChatColor, this.chatMessages[s], i1, k1);
					 }
                  }
					++j77;
                  ++j;
               }
			   }

               if((l == 3 || l == 7) && (l == 7 || this.privateChatMode == 0 || this.privateChatMode == 1 && this.isFriendOrSelf(s1))) {
			   if (chatTypeView == 2 || (chatTypeView == 0 && this.splitpublicChat == 0)) {
                  if(i1 > 0 && i1 < limit) {
                     byte var10 = 4;
                     textDrawingArea.method385(0, "From", i1, var10);
                     k1 = var10 + textDrawingArea.getTextWidth("From ");
                     if(byte0 == 1) {
                        this.modIcons[0].method361(k1, i1 - 12);
                        k1 += 14;
                     }

                     if(byte0 == 2) {
                        this.modIcons[1].method361(k1, i1 - 12);
                        k1 += 14;
                     }

					 if(byte0 == 3) {
                        this.modIcons[2].method361(k1, i1 - 12);
                        k1 += 14;
                     }
					 
                     textDrawingArea.method385(0, s1 + ":", i1, k1);
                     k1 += textDrawingArea.getTextWidth(s1) + 8;
                     textDrawingArea.method385(8388608, this.chatMessages[s], i1, k1);
                  }
					++j77;
                  ++j;
               }
			   }

               if(l == 4 && (this.tradeMode == 0 || this.tradeMode == 1 && this.isFriendOrSelf(s1))) {
				if (chatTypeView == 3 || chatTypeView == 0) {
                  if(i1 > 0 && i1 < limit) {
                     textDrawingArea.method385(8388736, s1 + " " + this.chatMessages[s], i1, 4);
                  }
				++j77;
                  ++j;
               }
			   }

               if(l == 5 && this.privateChatMode < 2) {
				if (chatTypeView == 2 || (chatTypeView == 0 && this.splitpublicChat == 0)) {
                  if(i1 > 0 && i1 < limit){
                     textDrawingArea.method385(8388608, this.chatMessages[s], i1, 4);
                  }
				++j77;
                  ++j;
				  }
               }

               if(l == 6 && this.privateChatMode < 2) {
				if (chatTypeView == 2 || (chatTypeView == 0 && this.splitpublicChat == 0)) {
                  if(i1 > 0 && i1 < limit) {
                     textDrawingArea.method385(0, "To " + s1 + ":", i1, 4);
                     textDrawingArea.method385(8388608, this.chatMessages[s], i1, 12 + textDrawingArea.getTextWidth("To " + s1));
                  }
					++j77;
                  ++j;
               }
			   }

               if(l == 8 && (this.tradeMode == 0 || this.tradeMode == 1 && this.isFriendOrSelf(s1))) {
				if (chatTypeView == 3 || chatTypeView == 0) {
                  if(i1 > 0 && i1 < limit){
                     textDrawingArea.method385(8270336, s1 + " " + this.chatMessages[s], i1, 4);
                  }
					++j77;
                  ++j;
               }
			   }
            }
         }

         DrawingArea.defaultDrawingAreaSize();
		 if(gameframeVersion != 474){
			this.anInt1211 = j * 14 + 7;
			if(this.anInt1211 < 78) {
				this.anInt1211 = 78;
			}
			this.method30(77, this.anInt1211 - this.anInt1089 - 77, 0, 463, this.anInt1211);
		}else{
			this.anInt1211 = j * 14 + 7 - 10;
			if(this.anInt1211 < 111) {
				this.anInt1211 = 111;
			}
			this.method30(114, this.anInt1211 - this.anInt1089 - 113, 0, 489, this.anInt1211);
			//drawScrollbar(class9_1.height, class9_1.scrollPosition, l2, k2 + class9_1.width, class9_1.scrollMax);
		}
         String var9;
		 int offC = 0;
		 int offY = 78;
		 if(gameframeVersion == 474)
			offY = 114;
         if(myPlayer != null && myPlayer.name != null) {
            var9 = myPlayer.name;
         } else {
            var9 = TextClass.fixName(this.myUsername);
         }
		 if(myPrivilege == 1) {
			this.modIcons[0].method361(4, offY);
			offC = 14;
		} else if(myPrivilege == 2) {
			this.modIcons[1].method361(4, offY);
			offC = 14;
		} else if(myDonor) {
			this.modIcons[2].method361(4, offY);
			offC = 14;
		}
		 if(gameframeVersion != 474){
			textDrawingArea.method385(0, var9 + ":", 90, 4+offC);
			textDrawingArea.method385(255, this.inputString + "*", 90, 6 + textDrawingArea.getTextWidth(var9 + ": ")+offC);
			DrawingArea.method339(77, 0, chatBackWidth317, 0);
		}else{
			textDrawingArea.method385(0, var9 + ":", 126, 4+offC);
			textDrawingArea.method385(255, this.inputString + "*", 126, 6 + textDrawingArea.getTextWidth(var9 + ": ")+offC);
			DrawingArea.method339(114, 0x807660, 506, 0); //chatbox line above name
		}
      }

      if(this.menuOpen && this.menuScreenArea == 2) {
         this.drawMenu();
      }
		if(gameframeVersion != 474)
			this.aRSImageProducer_1166.drawGraphics(357, super.graphics, 17);
		else
			this.aRSImageProducer_1166.drawGraphics(345, super.graphics, 7);
      this.aRSImageProducer_1165.initDrawingArea();
      Texture.anIntArray1472 = this.anIntArray1182;
   }

   public void init() {
      try {
         nodeID = 10;
         portOff = 0;
         setHighMem();
         isMembers = true;
         SignLink.storeid = 32;
         SignLink.startpriv(InetAddress.getLocalHost());
         this.initClientFrame(503, 765);
         instance = this;
		 try {
			readSettings();
		} catch(IOException e) {
		}
      } catch (Exception var2) {
         ;
      }
   }

   public void startRunnable(Runnable runnable, int i) {
      if(i > 10) {
         i = 10;
      }

      if(SignLink.mainapp != null) {
         SignLink.startthread(runnable, i);
      } else {
         super.startRunnable(runnable, i);
      }

   }

   public Socket openSocket(int i) throws IOException {
      return SignLink.mainapp != null?SignLink.opensocket(i):new Socket(InetAddress.getByName(this.getCodeBase().getHost()), i);
   }

   public boolean processMenuClick() {
      if(activeInterfaceType != 0)
			return false;
         int j = super.clickMode3;
         if(this.spellSelected == 1 && super.saveClickX >= 516 && super.saveClickY >= 160 && super.saveClickX <= 765 && super.saveClickY <= 205) {
            j = 0;
         }

         int i1;
         int l1;
         int j2;
         if(this.menuOpen) {
            if(j != 1) {
               i1 = super.mouseX;
               l1 = super.mouseY;
               if(this.menuScreenArea == 0) {
                  i1 -= 4;
                  l1 -= 4;
               }

               if(this.menuScreenArea == 1) {
                  i1 -= 553;
                  l1 -= 205;
               }

               if(this.menuScreenArea == 2) {
					if(gameframeVersion != 474){
						i1 -= 17;
						l1 -= 357;
					}else{
						i1 -= 7;
						l1 -= 345;
						if(l1 > 141)//cheap hax to stop right click menu from closing (#474 chat buttons)
							l1 = 141;
					}
               }
			   
               if(i1 < this.menuOffsetX - 10 || i1 > this.menuOffsetX + this.menuWidth + 10 || l1 < this.menuOffsetY - 10 || l1 > this.menuOffsetY + this.anInt952 + 10) {
                  this.menuOpen = false;
                  if(this.menuScreenArea == 1) {
                     this.needDrawTabArea = true;
                  }

                  if(this.menuScreenArea == 2) {
                     this.inputTaken = true;
                  }
               }
            }

            if(j == 1) {
               i1 = this.menuOffsetX;
               l1 = this.menuOffsetY;
               j2 = this.menuWidth;
               int class9 = super.saveClickX;
               int l2 = super.saveClickY;
               if(this.menuScreenArea == 0) {
                  class9 -= 4;
                  l2 -= 4;
               }

               if(this.menuScreenArea == 1) {
                  class9 -= 553;
                  l2 -= 205;
               }

               if(this.menuScreenArea == 2) {
					if(gameframeVersion != 474){
						class9 -= 17;
						l2 -= 357;
					}else{
						class9 -= 7;
						l2 -= 345;
					}
               }

               int i3 = -1;

               for(int j3 = 0; j3 < this.menuActionRow; ++j3) {
                  int k3 = l1 + 31 + (this.menuActionRow - 1 - j3) * 15;
                  if(class9 > i1 && class9 < i1 + j2 && l2 > k3 - 13 && l2 < k3 + 3) {
                     i3 = j3;
                  }
               }

               if(i3 != -1) {
                  this.doAction(i3);
               }

               this.menuOpen = false;
               if(this.menuScreenArea == 1) {
                  this.needDrawTabArea = true;
               }

               if(this.menuScreenArea == 2) {
                  this.inputTaken = true;
               }
            }
			return true;
         } else {
            if(j == 1 && this.menuActionRow > 0) {
               i1 = this.menuActionID[this.menuActionRow - 1];
               if(i1 == 632 || i1 == 78 || i1 == 867 || i1 == 431 || i1 == 53 || i1 == 74 || i1 == 454 || i1 == 539 || i1 == 493 || i1 == 847 || i1 == 447 || i1 == 1125) {
                  l1 = this.menuActionCmd2[this.menuActionRow - 1];
                  j2 = this.menuActionCmd3[this.menuActionRow - 1];
                  RSInterface var10 = RSInterface.interfaceCache[j2];
                  if(var10.aBoolean259 || var10.aBoolean235) {
                     this.aBoolean1242 = false;
                     this.anInt989 = 0;
                     this.anInt1084 = j2;
                     this.anInt1085 = l1;
                     this.activeInterfaceType = 2;
                     this.anInt1087 = super.saveClickX;
                     this.anInt1088 = super.saveClickY;
                     if(RSInterface.interfaceCache[j2].parentID == this.openInterfaceID) {
                        this.activeInterfaceType = 1;
                     }

                     if(RSInterface.interfaceCache[j2].parentID == this.backDialogID) {
                        this.activeInterfaceType = 3;
                     }

                     return true;
                  }
               }
            }

            if(j == 1 && (this.anInt1253 == 1 || this.menuHasAddFriend(this.menuActionRow - 1)) && this.menuActionRow > 2) {
               j = 2;
            }

            if(j == 1 && this.menuActionRow > 0) {
               this.doAction(this.menuActionRow - 1);
            }

            if(j == 2 && this.menuActionRow > 0) {
               this.determineMenuSize();
            }
			return false;
         }

     // }
   }

   public void saveMidi(boolean flag, byte[] abyte0) {
      SignLink.midifade = flag?1:0;
      SignLink.midisave(abyte0, abyte0.length);
   }

   public void method22() {
      int k;
      int j1;
      int i2;
      int l2;
      int l3;
      int j7;
      int k8;
      int var16;
      try {
         this.anInt985 = -1;
         this.aClass19_1056.removeAll();
         this.aClass19_1013.removeAll();
         Texture.method366();
         this.unlinkMRUNodes();
         this.worldController.initToNull();
         System.gc();

         for(k = 0; k < 4; ++k) {
            this.aClass11Array1230[k].method210();
         }

         for(k = 0; k < 4; ++k) {
            for(j1 = 0; j1 < 104; ++j1) {
               for(i2 = 0; i2 < 104; ++i2) {
                  this.byteGroundArray[k][j1][i2] = 0;
               }
            }
         }

         ObjectManager var14 = new ObjectManager(this.byteGroundArray, this.intGroundArray);
         j1 = this.aByteArrayArray1183.length;
         if(!editMode)
         this.stream.createFrame(0);
         if(!this.aBoolean1159) {
            byte[] j5;
            for(i2 = 0; i2 < j1; ++i2) {
               l2 = (this.anIntArray1234[i2] >> 8) * 64 - this.baseX;
               l3 = (this.anIntArray1234[i2] & 255) * 64 - this.baseY;
               j5 = this.aByteArrayArray1183[i2];
               if(j5 != null) {
                  var14.method180(j5, l3, l2, (this.anInt1069 - 6) * 8, (this.anInt1070 - 6) * 8, this.aClass11Array1230);
               }
            }

            for(i2 = 0; i2 < j1; ++i2) {
               l2 = (this.anIntArray1234[i2] >> 8) * 64 - this.baseX;
               l3 = (this.anIntArray1234[i2] & 255) * 64 - this.baseY;
               j5 = this.aByteArrayArray1183[i2];
               if(j5 == null && this.anInt1070 < 800) {
                  var14.method174(l3, 64, 64, l2);
               }
            }

				if (!editMode) {
					++anInt1097;
					if (anInt1097 > 160) {
						anInt1097 = 0;
						this.stream.createFrame(238);
						this.stream.writeWordBigEndian(96);
					}

					this.stream.createFrame(0);
				}

            for(i2 = 0; i2 < j1; ++i2) {
               byte[] var15 = this.aByteArrayArray1247[i2];
               if(var15 != null) {
                  l3 = (this.anIntArray1234[i2] >> 8) * 64 - this.baseX;
                  var16 = (this.anIntArray1234[i2] & 255) * 64 - this.baseY;
                  var14.method190(l3, this.aClass11Array1230, var16, this.worldController, var15);
               }
            }
         }

         if(this.aBoolean1159) {
            int k11;
            int i12;
            int j12;
            int k12;
            for(i2 = 0; i2 < 4; ++i2) {
               for(l2 = 0; l2 < 13; ++l2) {
                  for(l3 = 0; l3 < 13; ++l3) {
                     var16 = this.anIntArrayArrayArray1129[i2][l2][l3];
                     if(var16 != -1) {
                        j7 = var16 >> 24 & 3;
                        k8 = var16 >> 1 & 3;
                        k11 = var16 >> 14 & 1023;
                        i12 = var16 >> 3 & 2047;
                        j12 = (k11 / 8 << 8) + i12 / 8;

                        for(k12 = 0; k12 < this.anIntArray1234.length; ++k12) {
                           if(this.anIntArray1234[k12] == j12 && this.aByteArrayArray1183[k12] != null) {
                              var14.method179(j7, k8, this.aClass11Array1230, l2 * 8, (k11 & 7) * 8, this.aByteArrayArray1183[k12], (i12 & 7) * 8, i2, l3 * 8);
                              break;
                           }
                        }
                     }
                  }
               }
            }

            for(i2 = 0; i2 < 13; ++i2) {
               for(l2 = 0; l2 < 13; ++l2) {
                  l3 = this.anIntArrayArrayArray1129[0][i2][l2];
                  if(l3 == -1) {
                     var14.method174(l2 * 8, 8, 8, i2 * 8);
                  }
               }
            }

            if(!editMode)
            this.stream.createFrame(0);

            for(i2 = 0; i2 < 4; ++i2) {
               for(l2 = 0; l2 < 13; ++l2) {
                  for(l3 = 0; l3 < 13; ++l3) {
                     var16 = this.anIntArrayArrayArray1129[i2][l2][l3];
                     if(var16 != -1) {
                        j7 = var16 >> 24 & 3;
                        k8 = var16 >> 1 & 3;
                        k11 = var16 >> 14 & 1023;
                        i12 = var16 >> 3 & 2047;
                        j12 = (k11 / 8 << 8) + i12 / 8;

                        for(k12 = 0; k12 < this.anIntArray1234.length; ++k12) {
                           if(this.anIntArray1234[k12] == j12 && this.aByteArrayArray1247[k12] != null) {
                              var14.method183(this.aClass11Array1230, this.worldController, j7, l2 * 8, (i12 & 7) * 8, i2, this.aByteArrayArray1247[k12], (k11 & 7) * 8, k8, l3 * 8);
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }

         if(!editMode)
         this.stream.createFrame(0);
         var14.method171(this.aClass11Array1230, this.worldController);
         this.aRSImageProducer_1165.initDrawingArea();
         if(!editMode)
         this.stream.createFrame(0);
         i2 = ObjectManager.anInt145;
         if(i2 > this.plane) {
            i2 = this.plane;
         }

         if(i2 < this.plane - 1) {
            i2 = this.plane - 1;
         }

         if(lowMem) {
            this.worldController.method275(ObjectManager.anInt145);
         } else {
            this.worldController.method275(0);
         }

         for(l2 = 0; l2 < 104; ++l2) {
            for(l3 = 0; l3 < 104; ++l3) {
               this.spawnGroundItem(l2, l3);
            }
         }

			if (!editMode) {
				++anInt1051;
				if (anInt1051 > 98) {
					anInt1051 = 0;
					this.stream.createFrame(150);
				}
			}

         this.method63();
      } catch (Exception var13) {
         ;
      }

      ObjectDef.mruNodes1.unlinkAll();
      if(super.gameFrame != null && !editMode) {
         this.stream.createFrame(210);
         this.stream.writeDWord(1057001181);
      }

      if(lowMem && SignLink.cache_dat != null) {
         k = this.onDemandFetcher.getVersionCount(0);

         for(j1 = 0; j1 < k; ++j1) {
            i2 = this.onDemandFetcher.getModelIndex(j1);
            if((i2 & 121) == 0) {
               Model.method461(j1);
            }
         }
      }

      System.gc();
      Texture.method367();
      this.onDemandFetcher.method566();
      k = (this.anInt1069 - 6) / 8 - 1;
      j1 = (this.anInt1069 + 6) / 8 + 1;
      i2 = (this.anInt1070 - 6) / 8 - 1;
      l2 = (this.anInt1070 + 6) / 8 + 1;
      if(this.aBoolean1141) {
         k = 49;
         j1 = 50;
         i2 = 49;
         l2 = 50;
      }

      for(l3 = k; l3 <= j1; ++l3) {
         for(var16 = i2; var16 <= l2; ++var16) {
            if(l3 == k || l3 == j1 || var16 == i2 || var16 == l2) {
               j7 = this.onDemandFetcher.method562(0, var16, l3);
               if(j7 != -1) {
                  this.onDemandFetcher.method560(j7, 3);
               }

               k8 = this.onDemandFetcher.method562(1, var16, l3);
               if(k8 != -1) {
                  this.onDemandFetcher.method560(k8, 3);
               }
            }
         }
      }

   }

   public void unlinkMRUNodes() {
      ObjectDef.mruNodes1.unlinkAll();
      ObjectDef.mruNodes2.unlinkAll();
      EntityDef.mruNodes.unlinkAll();
      ItemDef.mruNodes2.unlinkAll();
      ItemDef.mruNodes1.unlinkAll();
      Player.mruNodes.unlinkAll();
      SpotAnim.aMRUNodes_415.unlinkAll();
   }

   public void method24(int i) {
      int[] ai = this.aClass30_Sub2_Sub1_Sub1_1263.myPixels;
      int j = ai.length;

      int j1;
      for(j1 = 0; j1 < j; ++j1) {
         ai[j1] = 0;
      }

      int l1;
      int k2;
      for(j1 = 1; j1 < 103; ++j1) {
         l1 = 24628 + (103 - j1) * 512 * 4;

         for(k2 = 1; k2 < 103; ++k2) {
            if((this.byteGroundArray[i][k2][j1] & 24) == 0) {
               this.worldController.method309(ai, l1, i, k2, j1);
            }

            if(i < 3 && (this.byteGroundArray[i + 1][k2][j1] & 8) != 0) {
               this.worldController.method309(ai, l1, i + 1, k2, j1);
            }

            l1 += 4;
         }
      }

      j1 = (238 + (int)(Math.random() * 20.0D) - 10 << 16) + (238 + (int)(Math.random() * 20.0D) - 10 << 8) + 238 + (int)(Math.random() * 20.0D) - 10;
      l1 = 238 + (int)(Math.random() * 20.0D) - 10 << 16;
      this.aClass30_Sub2_Sub1_Sub1_1263.method343();

      int l2;
      for(k2 = 1; k2 < 103; ++k2) {
         for(l2 = 1; l2 < 103; ++l2) {
            if((this.byteGroundArray[i][l2][k2] & 24) == 0) {
               this.method50(k2, j1, l2, l1, i);
            }

            if(i < 3 && (this.byteGroundArray[i + 1][l2][k2] & 8) != 0) {
               this.method50(k2, j1, l2, l1, i + 1);
            }
         }
      }

      this.aRSImageProducer_1165.initDrawingArea();
      this.anInt1071 = 0;

      for(k2 = 0; k2 < 104; ++k2) {
         for(l2 = 0; l2 < 104; ++l2) {
            int i3 = this.worldController.method303(this.plane, k2, l2);
            if(i3 != 0) {
               i3 = i3 >> 14 & 32767;
               int j3 = ObjectDef.forID(i3).anInt746;
               if(j3 >= 0) {
                  int k3 = k2;
                  int l3 = l2;
                  if(j3 != 22 && j3 != 29 && j3 != 34 && j3 != 36 && j3 != 46 && j3 != 47 && j3 != 48) {
                     byte byte0 = 104;
                     byte byte1 = 104;
                     int[][] ai1 = this.aClass11Array1230[this.plane].anIntArrayArray294;

                     for(int i4 = 0; i4 < 10; ++i4) {
                        int j4 = (int)(Math.random() * 4.0D);
                        if(j4 == 0 && k3 > 0 && k3 > k2 - 3 && (ai1[k3 - 1][l3] & 19398920) == 0) {
                           --k3;
                        }

                        if(j4 == 1 && k3 < byte0 - 1 && k3 < k2 + 3 && (ai1[k3 + 1][l3] & 19399040) == 0) {
                           ++k3;
                        }

                        if(j4 == 2 && l3 > 0 && l3 > l2 - 3 && (ai1[k3][l3 - 1] & 19398914) == 0) {
                           --l3;
                        }

                        if(j4 == 3 && l3 < byte1 - 1 && l3 < l2 + 3 && (ai1[k3][l3 + 1] & 19398944) == 0) {
                           ++l3;
                        }
                     }
                  }

                  this.aClass30_Sub2_Sub1_Sub1Array1140[this.anInt1071] = this.mapFunctions[j3];
                  this.anIntArray1072[this.anInt1071] = k3;
                  this.anIntArray1073[this.anInt1071] = l3;
                  ++this.anInt1071;
               }
            }
         }
      }

   }

   public void spawnGroundItem(int i, int j) {
      NodeList class19 = this.groundArray[this.plane][i][j];
      if(class19 == null) {
         this.worldController.method295(this.plane, i, j);
      } else {
         int k = -99999999;
         Item obj = null;

         Item obj1;
         int i1;
         for(obj1 = (Item)class19.reverseGetFirst(); obj1 != null; obj1 = (Item)class19.reverseGetNext()) {
            ItemDef obj2 = ItemDef.forID(obj1.ID);
            i1 = obj2.value;
            if(obj2.stackable) {
               i1 *= obj1.anInt1559 + 1;
            }

            if(i1 > k) {
               k = i1;
               obj = obj1;
            }
         }

         class19.insertTail((Node)obj);
         obj1 = null;
         Item obj21 = null;

         for(Item i11 = (Item)class19.reverseGetFirst(); i11 != null; i11 = (Item)class19.reverseGetNext()) {
            if(i11.ID != ((Item)obj).ID && obj1 == null) {
               obj1 = i11;
            }

            if(i11.ID != ((Item)obj).ID && i11.ID != ((Item)obj1).ID && obj21 == null) {
               obj21 = i11;
            }
         }

         i1 = i + (j << 7) + 1610612736;
         this.worldController.method281(i, i1, (Animable)obj1, this.method42(this.plane, j * 128 + 64, i * 128 + 64), (Animable)obj21, (Animable)obj, this.plane, j);
      }
   }

   public void method26(boolean flag) {
      for(int j = 0; j < this.npcCount; ++j) {
         NPC npc = this.npcArray[this.npcIndices[j]];
         int k = 536870912 + (this.npcIndices[j] << 14);
         if(npc != null && npc.isVisible() && npc.desc.aBoolean93 == flag) {
            int l = npc.x >> 7;
            int i1 = npc.y >> 7;
            if(l >= 0 && l < 104 && i1 >= 0 && i1 < 104) {
               if(npc.anInt1540 == 1 && (npc.x & 127) == 64 && (npc.y & 127) == 64) {
                  if(this.anIntArrayArray929[l][i1] == this.anInt1265) {
                     continue;
                  }

                  this.anIntArrayArray929[l][i1] = this.anInt1265;
               }

               if(!npc.desc.aBoolean84) {
                  k -= Integer.MIN_VALUE;
               }

               this.worldController.method285(this.plane, npc.anInt1552, this.method42(this.plane, npc.y, npc.x), k, npc.y, (npc.anInt1540 - 1) * 64 + 60, npc.x, npc, npc.aBoolean1541);
            }
         }
      }

   }

   static final void method493(int i) {
      if(aClass3_Sub7_1345 != null) {
         aClass3_Sub7_1345.method380(i);
      }

      method689(i);
   }

   public boolean replayWave() {
      return SignLink.wavereplay();
   }

   public void loadError() {
      String s = "ondemand";
      System.out.println(s);

      try {
         this.getAppletContext().showDocument(new URL(this.getCodeBase(), "loaderror_" + s + ".html"));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      while(true) {
         try {
            while(true) {
               Thread.sleep(1000L);
            }
         } catch (Exception var4) {
            ;
         }
      }
   }

   public void buildInterfaceMenu(int i, RSInterface class9, int k, int l, int i1, int j1) {
      if(class9.type == 0 && class9.children != null && !class9.aBoolean266) {
         if(k >= i && i1 >= l && k <= i + class9.width && i1 <= l + class9.height) {
            int k1 = class9.children.length;

            for(int l1 = 0; l1 < k1; ++l1) {
               int i2 = class9.childX[l1] + i;
               int j2 = class9.childY[l1] + l - j1;
               RSInterface class9_1 = RSInterface.interfaceCache[class9.children[l1]];
               i2 += class9_1.anInt263;
               j2 += class9_1.anInt265;
               if((class9_1.anInt230 >= 0 || class9_1.anInt216 != 0) && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                  if(class9_1.anInt230 >= 0) {
                     this.anInt886 = class9_1.anInt230;
                  } else {
                     this.anInt886 = class9_1.id;
                  }
               }
			    if (class9_1.type == 8 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                anInt1315 = class9_1.id;
            }

               if(class9_1.type == 0) {
                  this.buildInterfaceMenu(i2, class9_1, k, j2, i1, class9_1.scrollPosition);
                  if(class9_1.scrollMax > class9_1.height) {
                     this.method65(i2 + class9_1.width, class9_1.height, k, i1, class9_1, j2, true, class9_1.scrollMax);
                  }
               } else {
                  if(class9_1.atActionType == 1 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                     boolean k2 = false;
                     if(class9_1.anInt214 != 0) {
                        k2 = this.buildFriendsListMenu(class9_1);
                     }

                     if(!k2 && (class9_1.id != 5985 || myPrivilege >= 1)) {
                        if(myPrivilege >= 2 && developMode){
							this.menuActionName[this.menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
						}else{
							this.menuActionName[this.menuActionRow] = class9_1.tooltip;
						}
						this.menuActionID[this.menuActionRow] = 315;
						this.menuActionCmd3[this.menuActionRow] = class9_1.id;
						this.menuActionRow++;
                     }
                  }

                  if(class9_1.atActionType == 2 && this.spellSelected == 0 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                     String var20 = class9_1.selectedActionName;
                     if(var20.indexOf(" ") != -1) {
                        var20 = var20.substring(0, var20.indexOf(" "));
                     }

					 if(myPrivilege >= 2 && developMode){
						this.menuActionName[this.menuActionRow] = var20 + " @gre@" + class9_1.spellName +", " + class9_1.id;
					}else{
						this.menuActionName[this.menuActionRow] = var20 + " @gre@" + class9_1.spellName;
					}
                     this.menuActionID[this.menuActionRow] = 626;
                     this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                     ++this.menuActionRow;
                  }

                  if(class9_1.atActionType == 3 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                     this.menuActionName[this.menuActionRow] = "Close";
                     this.menuActionID[this.menuActionRow] = 200;
                     this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                     ++this.menuActionRow;
                  }

                  if(class9_1.atActionType == 4 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                     if(myPrivilege >= 2 && developMode){
						this.menuActionName[this.menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					}else{
						this.menuActionName[this.menuActionRow] = class9_1.tooltip;
					}
                     this.menuActionID[this.menuActionRow] = 169;
                     this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                     ++this.menuActionRow;
                  }

                  if(class9_1.atActionType == 5 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                     if(myPrivilege >= 2 && developMode){
						this.menuActionName[this.menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					}else{
						this.menuActionName[this.menuActionRow] = class9_1.tooltip;
					}
                     this.menuActionID[this.menuActionRow] = 646;
                     this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                     ++this.menuActionRow;
                  }

                  if(class9_1.atActionType == 6 && !this.aBoolean1149 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                     if(myPrivilege >= 2 && developMode){
						this.menuActionName[this.menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					}else{
						this.menuActionName[this.menuActionRow] = class9_1.tooltip;
					}
                     this.menuActionID[this.menuActionRow] = 679;
                     this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                     ++this.menuActionRow;
                  }

                  if(class9_1.type == 2) {
                     int var19 = 0;

                     for(int l2 = 0; l2 < class9_1.height; ++l2) {
                        for(int i3 = 0; i3 < class9_1.width; ++i3) {
                           int j3 = i2 + i3 * (32 + class9_1.invSpritePadX);
                           int k3 = j2 + l2 * (32 + class9_1.invSpritePadY);
                           if(var19 < 20) {
                              j3 += class9_1.spritesX[var19];
                              k3 += class9_1.spritesY[var19];
                           }

                           if(k >= j3 && i1 >= k3 && k < j3 + 32 && i1 < k3 + 32) {
                              this.mouseInvInterfaceIndex = var19;
                              this.lastActiveInvInterface = class9_1.id;
                              if(class9_1.inv[var19] > 0) {
                                 ItemDef itemDef = ItemDef.forID(class9_1.inv[var19] - 1);
                                 if(this.itemSelected == 1 && class9_1.isInventoryInterface) {
                                    if(class9_1.id != this.anInt1284 || var19 != this.anInt1283) {
                                       this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @lre@" + itemDef.name;
                                       this.menuActionID[this.menuActionRow] = 870;
                                       this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                       this.menuActionCmd2[this.menuActionRow] = var19;
                                       this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                       ++this.menuActionRow;
                                    }
                                 } else if(this.spellSelected == 1 && class9_1.isInventoryInterface) {
                                    if((this.spellUsableOn & 16) == 16) {
                                       this.menuActionName[this.menuActionRow] = this.spellTooltip + " @lre@" + itemDef.name;
                                       this.menuActionID[this.menuActionRow] = 543;
                                       this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                       this.menuActionCmd2[this.menuActionRow] = var19;
                                       this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                       ++this.menuActionRow;
                                    }
                                 } else {
                                    int j4;
                                    if(class9_1.isInventoryInterface) {
                                       for(j4 = 4; j4 >= 3; --j4) {
                                          if(itemDef.actions != null && itemDef.actions[j4] != null) {
                                             this.menuActionName[this.menuActionRow] = itemDef.actions[j4] + " @lre@" + itemDef.name;
                                             if(j4 == 3) {
                                                this.menuActionID[this.menuActionRow] = 493;
                                             }

                                             if(j4 == 4) {
                                                this.menuActionID[this.menuActionRow] = 847;
                                             }

                                             this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                             this.menuActionCmd2[this.menuActionRow] = var19;
                                             this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                             ++this.menuActionRow;
                                          } else if(j4 == 4) {
                                             this.menuActionName[this.menuActionRow] = "Drop @lre@" + itemDef.name;
                                             this.menuActionID[this.menuActionRow] = 847;
                                             this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                             this.menuActionCmd2[this.menuActionRow] = var19;
                                             this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                             ++this.menuActionRow;
                                          }
                                       }
                                    }

                                    if(class9_1.usableItemInterface) {
                                       this.menuActionName[this.menuActionRow] = "Use @lre@" + itemDef.name;
                                       this.menuActionID[this.menuActionRow] = 447;
                                       this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                       this.menuActionCmd2[this.menuActionRow] = var19;
                                       this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                       ++this.menuActionRow;
                                    }

                                    if(class9_1.isInventoryInterface && itemDef.actions != null) {
                                       for(j4 = 2; j4 >= 0; --j4) {
                                          if(itemDef.actions[j4] != null) {
                                             this.menuActionName[this.menuActionRow] = itemDef.actions[j4] + " @lre@" + itemDef.name;
                                             if(j4 == 0) {
                                                this.menuActionID[this.menuActionRow] = 74;
                                             }

                                             if(j4 == 1) {
                                                this.menuActionID[this.menuActionRow] = 454;
                                             }

                                             if(j4 == 2) {
                                                this.menuActionID[this.menuActionRow] = 539;
                                             }

                                             this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                             this.menuActionCmd2[this.menuActionRow] = var19;
                                             this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                             ++this.menuActionRow;
                                          }
                                       }
                                    }

                                    if(class9_1.actions != null) {
                                       for(j4 = 4; j4 >= 0; --j4) {
                                          if(class9_1.actions[j4] != null) {
                                             this.menuActionName[this.menuActionRow] = class9_1.actions[j4] + " @lre@" + itemDef.name;
                                             if(j4 == 0) {
                                                this.menuActionID[this.menuActionRow] = 632;
                                             }

                                             if(j4 == 1) {
                                                this.menuActionID[this.menuActionRow] = 78;
                                             }

                                             if(j4 == 2) {
                                                this.menuActionID[this.menuActionRow] = 867;
                                             }

                                             if(j4 == 3) {
                                                this.menuActionID[this.menuActionRow] = 431;
                                             }

                                             if(j4 == 4) {
                                                this.menuActionID[this.menuActionRow] = 53;
                                             }

                                             this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                             this.menuActionCmd2[this.menuActionRow] = var19;
                                             this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                             ++this.menuActionRow;
                                          }
                                       }
                                    }

                                    if(myPrivilege >= 2 && developMode){
											this.menuActionName[this.menuActionRow] = "Examine @lre@" + itemDef.name + " @gre@(@whi@" + (class9_1.inv[var19] - 1) + "@gre@)";
										}else{
											this.menuActionName[this.menuActionRow] = "Examine @lre@" + itemDef.name;
										}
                                    this.menuActionID[this.menuActionRow] = 1125;
                                    this.menuActionCmd1[this.menuActionRow] = itemDef.id;
                                    this.menuActionCmd2[this.menuActionRow] = var19;
                                    this.menuActionCmd3[this.menuActionRow] = class9_1.id;
                                    ++this.menuActionRow;
                                 }
                              }
                           }

                           ++var19;
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public void method30(int j, int k, int l, int i1, int j1) {
	  if(gameframeVersion != 474){
		this.scrollBar1.method361(i1, l);
		this.scrollBar2.method361(i1, l + j - 16);
		DrawingArea.method336(j - 32, l + 16, i1, this.anInt1002, 16);
		int k1 = (j - 32) * j / j1;
		if(k1 < 8) {
			k1 = 8;
		}

		int l1 = (j - 32 - k1) * k / (j1 - j);
		DrawingArea.method336(k1, l + 16 + l1, i1, this.anInt1063, 16);
		DrawingArea.method341(l + 16 + l1, this.anInt902, k1, i1);
		DrawingArea.method341(l + 16 + l1, this.anInt902, k1, i1 + 1);
		DrawingArea.method339(l + 16 + l1, this.anInt902, 16, i1);
		DrawingArea.method339(l + 17 + l1, this.anInt902, 16, i1);
		DrawingArea.method341(l + 16 + l1, this.anInt927, k1, i1 + 15);
		DrawingArea.method341(l + 17 + l1, this.anInt927, k1 - 1, i1 + 14);
		DrawingArea.method339(l + 15 + l1 + k1, this.anInt927, 16, i1);
		DrawingArea.method339(l + 14 + l1 + k1, this.anInt927, 15, i1 + 1);
		}else{
			int barHeight = j;
			int scrollPos = k;
			int yPos = l;
			int xPos = i1;
			int contentHeight = j1;
			int backingAmount = (j - 32) / 5;
			int scrollPartHeight = ((j - 32) * j) / j1;
			if (scrollPartHeight < 10)
				scrollPartHeight = 10;
			int scrollPartAmount = (scrollPartHeight / 5) - 2;
			int scrollPartPos = ((barHeight - 32 - scrollPartHeight) * scrollPos)
					/ (contentHeight - barHeight) + 16 + yPos;
			/* Bar fill */
			for (int i = 0, yyPos = yPos + 14; i <= backingAmount; i++, yyPos += 5) {
				cacheSprite[32].drawSprite(xPos, yyPos);
			}
			/* Top of bar */
			cacheSprite[30].drawSprite(xPos, scrollPartPos);
			int topY = scrollPartPos;
			scrollPartPos += 5;
			/* Middle of bar */
			for (int i = 0; i <= scrollPartAmount; i++) {
				cacheSprite[31].drawSprite(xPos, scrollPartPos);
				scrollPartPos += 5;
			}
			scrollPartPos = ((barHeight - 32 - scrollPartHeight) * scrollPos)
					/ (contentHeight - barHeight) + 16 + yPos
					+ (scrollPartHeight - 5);
			/* Bottom of bar */
			cacheSprite[29].drawSprite(xPos, scrollPartPos);
			int bottomY = scrollPartPos;
			cacheSprite[27].drawSprite(xPos, yPos);
			cacheSprite[28].drawSprite(xPos, (yPos + barHeight) - 16);
		}
   }

   static final boolean constructMusic() {
      anInt720 = 20;

      try {
         aClass56_749 = (Class56)Class.forName("client.Class56_Sub1_Sub1").newInstance();
         return true;
      } catch (Throwable var1) {
         return false;
      }
   }

   public void updateNPCs(Stream stream, int i) {
      this.anInt839 = 0;
      this.anInt893 = 0;
      this.method139(stream);
      this.method46(i, stream);
      this.method86(stream);

      int i1;
      for(i1 = 0; i1 < this.anInt839; ++i1) {
         int l = this.anIntArray840[i1];
         if(this.npcArray[l].anInt1537 != loopCycle) {
            this.npcArray[l].desc = null;
            this.npcArray[l] = null;
         }
      }

      if(stream.currentOffset != i) {
         SignLink.reporterror(this.myUsername + " size mismatch in getnpcpos - pos:" + stream.currentOffset + " psize:" + i);
         throw new RuntimeException("eek");
      } else {
         for(i1 = 0; i1 < this.npcCount; ++i1) {
            if(this.npcArray[this.npcIndices[i1]] == null) {
               SignLink.reporterror(this.myUsername + " null entry in npc list - pos:" + i1 + " size:" + this.npcCount);
               throw new RuntimeException("eek");
            }
         }

      }
   }

   static final void setVolume(int i) {
      if(musicIsntNull()) {
         if(fetchMusic) {
            musicVolume2 = i;
         } else {
            method900(i);
         }
      }

   }

   private int cButtonHPos;
	private static int cButtonCPos;
   
   public void processHovers(){
		if(super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 0;
			inputTaken = true;
		} else if(super.mouseX >= 71 && super.mouseX <= 127 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 1;
			inputTaken = true;
		} else if(super.mouseX >= 137 && super.mouseX <= 193 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 2;
			inputTaken = true;
		} else if(super.mouseX >= 203 && super.mouseX <= 259 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 3;
			inputTaken = true;
		/*} else if(super.mouseX >= 269 && super.mouseX <= 325 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 4;
			inputTaken = true;*/
		} else if(super.mouseX >= 335 && super.mouseX <= 391 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 5;
			inputTaken = true;
		} else if(super.mouseX >= 404 && super.mouseX <= 515 && super.mouseY >= 482 && super.mouseY <= 503) {
			cButtonHPos = 6;
			inputTaken = true;
		} else {
			cButtonHPos = -1;
			inputTaken = true;
		}
   }
   
   public void processChatModeClick() {
	   if(fullscreenInterfaceID != -1)
		   return;
      if(super.clickMode3 == 1) {
		if(gameframeVersion != 474){
         if(super.saveClickX >= 6 && super.saveClickX <= 106 && super.saveClickY >= 467 && super.saveClickY <= 499) {
            this.publicChatMode = (this.publicChatMode + 1) % 4;
            this.aBoolean1233 = true;
            this.inputTaken = true;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
         }

         if(super.saveClickX >= 135 && super.saveClickX <= 235 && super.saveClickY >= 467 && super.saveClickY <= 499) {
            this.privateChatMode = (this.privateChatMode + 1) % 3;
            this.aBoolean1233 = true;
            this.inputTaken = true;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
         }

         if(super.saveClickX >= 273 && super.saveClickX <= 373 && super.saveClickY >= 467 && super.saveClickY <= 499) {
            this.tradeMode = (this.tradeMode + 1) % 3;
            this.aBoolean1233 = true;
            this.inputTaken = true;
            this.stream.createFrame(95);
            this.stream.writeWordBigEndian(this.publicChatMode);
            this.stream.writeWordBigEndian(this.privateChatMode);
            this.stream.writeWordBigEndian(this.tradeMode);
         }

         if(super.saveClickX >= 412 && super.saveClickX <= 512 && super.saveClickY >= 467 && super.saveClickY <= 499) {
            if(this.openInterfaceID == -1 && this.fullscreenInterfaceID == -1) {
               this.clearTopInterfaces();
               this.reportAbuseInput = "";
               this.canMute = false;
               RSInterface[] j = RSInterface.interfaceCache;
               int len$ = j.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  RSInterface element = j[i$];
                  if(element != null && element.anInt214 == 600) {
                     this.reportAbuseInterfaceID = this.openInterfaceID = element.parentID;
                     break;
                  }
               }
            } else {
               this.pushMessage("Please close the interface you have open before using \'report abuse\'", 0, "");
            }
         }
		 }else{
			if(super.saveClickX >= 5 && super.saveClickX <= 61 && super.saveClickY >= 482 && super.saveClickY <= 505) {
				cButtonCPos = 0;
				chatTypeView = 0;
				inputTaken = true;
         }

         if(super.saveClickX >= 71 && super.saveClickX <= 127 && super.saveClickY >= 482 && super.saveClickY <= 505) {
				cButtonCPos = 1;
				chatTypeView = 5;
				inputTaken = true;
         }

         if(super.saveClickX >= 137 && super.saveClickX <= 193 && super.saveClickY >= 482 && super.saveClickY <= 505) {
				cButtonCPos = 2;
				chatTypeView = 1;
				inputTaken = true;
         }
		if(super.saveClickX >= 203 && super.saveClickX <= 259 && super.saveClickY >= 482 && super.saveClickY <= 505) {
				cButtonCPos = 3;
				chatTypeView = 2;
				inputTaken = true;
		}
		/*if(super.saveClickX >= 269 && super.saveClickX <= 325 && super.saveClickY >= 482 && super.saveClickY <= 505) {
				cButtonCPos = 4;
				chatTypeView = 11;
				inputTaken = true;
		}*/
		if(super.saveClickX >= 335 && super.saveClickX <= 391 && super.saveClickY >= 482 && super.saveClickY <= 505) {
				cButtonCPos = 5;
				chatTypeView = 3;
				inputTaken = true;
		 }
         if(super.saveClickX >= 404 && super.saveClickX <= 515 && super.saveClickY >= 482 && super.saveClickY <= 505) {
            if(this.openInterfaceID == -1 && this.fullscreenInterfaceID == -1) {
               this.clearTopInterfaces();
               this.reportAbuseInput = "";
               this.canMute = false;
               RSInterface[] j = RSInterface.interfaceCache;
               int len$ = j.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  RSInterface element = j[i$];
                  if(element != null && element.anInt214 == 600) {
                     this.reportAbuseInterfaceID = this.openInterfaceID = element.parentID;
                     break;
                  }
               }
            } else {
               this.pushMessage("Please close the interface you have open before using \'report abuse\'", 0, "");
            }
         }
		 }

         ++anInt940;
         if(anInt940 > 1386) {
            anInt940 = 0;
            this.stream.createFrame(165);
            this.stream.writeWordBigEndian(0);
            int var5 = this.stream.currentOffset;
            this.stream.writeWordBigEndian(139);
            this.stream.writeWordBigEndian(150);
            this.stream.writeWord(32131);
            this.stream.writeWordBigEndian((int)(Math.random() * 256.0D));
            this.stream.writeWord(3250);
            this.stream.writeWordBigEndian(177);
            this.stream.writeWord(24859);
            this.stream.writeWordBigEndian(119);
            if((int)(Math.random() * 2.0D) == 0) {
               this.stream.writeWord('\ub882');
            }

            if((int)(Math.random() * 2.0D) == 0) {
               this.stream.writeWordBigEndian(21);
            }

            this.stream.writeBytes(this.stream.currentOffset - var5);
         }
      }

   }

   static final void method486(int[] is, int i) {
      int i_2_ = 0;

      for(i -= 7; i_2_ < i; is[i_2_++] = 0) {
         is[i_2_++] = 0;
         is[i_2_++] = 0;
         is[i_2_++] = 0;
         is[i_2_++] = 0;
         is[i_2_++] = 0;
         is[i_2_++] = 0;
         is[i_2_++] = 0;
      }

      for(i += 7; i_2_ < i; is[i_2_++] = 0) {
         ;
      }

      if(aClass3_Sub7_1345 != null) {
         aClass3_Sub7_1345.method378(is, 0, i);
      }

      method689(i);
   }

   public void method33(int i) {
      int action = Varp.cache[i].anInt709;
      if(action != 0) {
         int config = this.variousSettings[i];
         if(action == 1) {
            if(config == 1) {
               Texture.method372(0.9D);
            }

            if(config == 2) {
               Texture.method372(0.8D);
            }

            if(config == 3) {
               Texture.method372(0.7D);
            }

            if(config == 4) {
               Texture.method372(0.6D);
            }

            ItemDef.mruNodes1.unlinkAll();
            this.welcomeScreenRaised = true;
         }

         if(action == 3) {
            short volume = 0;
            if(config == 0) {
               volume = 255;
            }

            if(config == 1) {
               volume = 192;
            }

            if(config == 2) {
               volume = 128;
            }

            if(config == 3) {
               volume = 64;
            }

            if(config == 4) {
               volume = 0;
            }

            if(volume != musicVolume) {
               if(musicVolume == 0 && this.currentSong != -1) {
                  this.method56(volume, false, this.currentSong);
                  this.previousSong = 0;
               } else if(volume != 0) {
                  setVolume(volume);
               } else {
                  method55(false);
                  this.previousSong = 0;
               }

               musicVolume = volume;
               try {
            	   writeSettings();
               } catch (IOException e) {
            	   e.printStackTrace();
               }
            }
         }

         if(action == 4) {
            if(config == 0) {
               soundEffectVolume = 127;
            }

            if(config == 1) {
               soundEffectVolume = 96;
            }

            if(config == 2) {
               soundEffectVolume = 64;
            }

            if(config == 3) {
               soundEffectVolume = 32;
            }

            if(config == 4) {
               soundEffectVolume = 0;
            }
         }

         if(action == 5) {
            this.anInt1253 = config;//mouse buttons (one/two)
         }

         if(action == 6) {
            this.anInt1249 = config;
         }

         if(action == 8) {
            this.splitpublicChat = config;
            this.inputTaken = true;
         }

         if(action == 9) {
            this.anInt913 = config;
         }
      }
   }

   static final void sleep(long time) {
      if(time > 0L) {
         if(time % 10L != 0L) {
            threadSleep(time);
         } else {
            threadSleep(time - 1L);
            threadSleep(1L);
         }
      }

   }

   static Class method868(String string) {
      try {
         Class var_class = Class.forName(string);
         return var_class;
      } catch (ClassNotFoundException var3) {
         throw new NoClassDefFoundError(var3.getMessage());
      }
   }

   public void updateEntities() {
      try {
         int e = 0;

         if (editMode)
        	 itemScreenPos();
         
         int k;
         int var16;
         for(k = -1; k < this.playerCount + this.npcCount; ++k) {
            Object k1;
            if(k == -1) {
               k1 = myPlayer;
            } else if(k < this.playerCount) {
               k1 = this.playerArray[this.playerIndices[k]];
            } else {
               k1 = this.npcArray[this.npcIndices[k - this.playerCount]];
            }

            if(k1 != null && ((Entity)k1).isVisible()) {
               EntityDef l1;
               if(k1 instanceof NPC) {
                  l1 = ((NPC)k1).desc;
                  if(l1.childrenIDs != null) {
                     l1 = l1.method161();
                  }

                  if(l1 == null) {
                     continue;
                  }
               }

               if(k < this.playerCount) {
                  var16 = 30;
                  Player j2 = (Player)k1;
                  if(j2.headIcon >= 0) {
                     this.npcScreenPos((Entity)k1, ((Entity)k1).height + 15);
                     if(this.spriteDrawX > -1) {
                        if(j2.skullIcon < 2) {
                           this.skullIcons[j2.skullIcon].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - var16);
                           var16 += 25;
                        }

                        if(j2.headIcon < 7) {
                           this.headIcons[j2.headIcon].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - var16);
                           var16 += 25;//18
                        }
                     }
                  }

                  if(k >= 0 && this.anInt855 == 10 && this.anInt933 == this.playerIndices[k]) {
                     this.npcScreenPos((Entity)k1, ((Entity)k1).height + 15);
                     if(this.spriteDrawX > -1) {
                        this.headIconsHint[j2.hintIcon].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - var16);
						var16 += 25;
                     }
                  }
               } else {
                  l1 = ((NPC)k1).desc;
				  int l = 30;
                  if(l1.anInt75 >= 0 && l1.anInt75 < this.headIcons.length) {
                     this.npcScreenPos((Entity)k1, ((Entity)k1).height + 15);
                     if(this.spriteDrawX > -1) {
                        this.headIcons[l1.anInt75].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - l);
						l += 25;
                     }
                  }

                  if(this.anInt855 == 1 && this.anInt1222 == this.npcIndices[k - this.playerCount] && loopCycle % 20 < 10) {
                     this.npcScreenPos((Entity)k1, ((Entity)k1).height + 15);
                     if(this.spriteDrawX > -1) {
                        this.headIconsHint[0].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - l);
						l += 25;
                     }
                  }
               }

					if (editMode) {
						l1 = ((NPC)k1).desc;
						String s1 = l1.name;
						if(l1.combatLevel != 0) {
			                  s1 = s1 + " (level-" + l1.combatLevel + ")";
			               }
						this.npcScreenPos((Entity) k1, ((Entity) k1).height + 70);
						this.chatTextDrawingArea.drawText(0, s1, this.spriteDrawY + 5, this.spriteDrawX);
						this.chatTextDrawingArea.drawText(16776960, s1, this.spriteDrawY + 4, this.spriteDrawX);
						this.npcScreenPos((Entity) k1, ((Entity) k1).height + 20);
						NpcSpawn npcSpawn = ((Entity)k1).npcSpawn;
						String s = "";
						if(npcSpawn.getWalkType() == WALK)
							s = "WALK - ";
						s += npcSpawn.isMembersOnly() ? "P2P" : "F2P";
						this.chatTextDrawingArea.drawText(0, s, this.spriteDrawY + 5, this.spriteDrawX);
						this.chatTextDrawingArea.drawText(16777215, s, this.spriteDrawY + 4, this.spriteDrawX);
					}
               
               if(((Entity)k1).textSpoken != null && (k >= this.playerCount || this.publicChatMode == 0 || this.publicChatMode == 3 || this.publicChatMode == 1 && this.isFriendOrSelf(((Player)k1).name))) {
                  this.npcScreenPos((Entity)k1, ((Entity)k1).height);
                  if(this.spriteDrawX > -1 && e < this.anInt975) {
                     this.anIntArray979[e] = this.chatTextDrawingArea.method384(((Entity)k1).textSpoken) / 2;
                     this.anIntArray978[e] = this.chatTextDrawingArea.anInt1497;
                     this.anIntArray976[e] = this.spriteDrawX;
                     this.anIntArray977[e] = this.spriteDrawY;
                     this.anIntArray980[e] = ((Entity)k1).anInt1513;
                     this.anIntArray981[e] = ((Entity)k1).anInt1531;
                     this.anIntArray982[e] = ((Entity)k1).textCycle;
                     this.aStringArray983[e++] = ((Entity)k1).textSpoken;
                     if(this.anInt1249 == 0 && ((Entity)k1).anInt1531 >= 1 && ((Entity)k1).anInt1531 <= 3) {
                        this.anIntArray978[e] += 10;
                        this.anIntArray977[e] += 5;
                     }

                     if(this.anInt1249 == 0 && ((Entity)k1).anInt1531 == 4) {
                        this.anIntArray979[e] = 60;
                     }

                     if(this.anInt1249 == 0 && ((Entity)k1).anInt1531 == 5) {
                        this.anIntArray978[e] += 5;
                     }
                  }
               }

               if(((Entity)k1).loopCycleStatus > loopCycle) {
                  try {
                     this.npcScreenPos((Entity)k1, ((Entity)k1).height + 15);
                     if(this.spriteDrawX > -1) {
                        var16 = ((Entity)k1).currentHealth * 30 / ((Entity)k1).maxHealth;
                        if(var16 > 30) {
                           var16 = 30;
                        }

                        DrawingArea.method336(5, this.spriteDrawY - 3, this.spriteDrawX - 15, '\uff00', var16);
                        DrawingArea.method336(5, this.spriteDrawY - 3, this.spriteDrawX - 15 + var16, 16711680, 30 - var16);
                     }
                  } catch (Exception var12) {
                     ;
                  }
               }

               for(var16 = 0; var16 < 4; ++var16) {
                  if(((Entity)k1).hitsLoopCycle[var16] > loopCycle) {
                     this.npcScreenPos((Entity)k1, ((Entity)k1).height / 2);
                     if(this.spriteDrawX > -1) {
                        if(var16 == 1) {
                           this.spriteDrawY -= 20;
                        }

                        if(var16 == 2) {
                           this.spriteDrawX -= 15;
                           this.spriteDrawY -= 10;
                        }

                        if(var16 == 3) {
                           this.spriteDrawX += 15;
                           this.spriteDrawY -= 10;
                        }

                        this.hitMarks[((Entity)k1).hitMarkTypes[var16]].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - 12);
                        this.aTextDrawingArea_1270.drawText(0, String.valueOf(((Entity)k1).hitArray[var16]), this.spriteDrawY + 4, this.spriteDrawX);
                        this.aTextDrawingArea_1270.drawText(16777215, String.valueOf(((Entity)k1).hitArray[var16]), this.spriteDrawY + 3, this.spriteDrawX - 1);
                     }
                  }
               }
            }
         }

         for(k = 0; k < e; ++k) {
            int var14 = this.anIntArray976[k];
            var16 = this.anIntArray977[k];
            int var15 = this.anIntArray979[k];
            int k2 = this.anIntArray978[k];
            boolean flag = true;

            while(flag) {
               flag = false;

               for(int s = 0; s < k; ++s) {
                  if(var16 + 2 > this.anIntArray977[s] - this.anIntArray978[s] && var16 - k2 < this.anIntArray977[s] + 2 && var14 - var15 < this.anIntArray976[s] + this.anIntArray979[s] && var14 + var15 > this.anIntArray976[s] - this.anIntArray979[s] && this.anIntArray977[s] - this.anIntArray978[s] < var16) {
                     var16 = this.anIntArray977[s] - this.anIntArray978[s];
                     flag = true;
                  }
               }
            }

            this.spriteDrawX = this.anIntArray976[k];
            this.spriteDrawY = this.anIntArray977[k] = var16;
            String var17 = this.aStringArray983[k];
            if(this.anInt1249 == 0) {
               int i3 = 16776960;
               if(this.anIntArray980[k] < 6) {
                  i3 = this.anIntArray965[this.anIntArray980[k]];
               }

               if(this.anIntArray980[k] == 6) {
                  i3 = this.anInt1265 % 20 >= 10?16776960:16711680;
               }

               if(this.anIntArray980[k] == 7) {
                  i3 = this.anInt1265 % 20 >= 10?'\uffff':255;
               }

               if(this.anIntArray980[k] == 8) {
                  i3 = this.anInt1265 % 20 >= 10?8454016:'\ub000';
               }

               int j4;
               if(this.anIntArray980[k] == 9) {
                  j4 = 150 - this.anIntArray982[k];
                  if(j4 < 50) {
                     i3 = 16711680 + 1280 * j4;
                  } else if(j4 < 100) {
                     i3 = 16776960 - 327680 * (j4 - 50);
                  } else if(j4 < 150) {
                     i3 = '\uff00' + 5 * (j4 - 100);
                  }
               }

               if(this.anIntArray980[k] == 10) {
                  j4 = 150 - this.anIntArray982[k];
                  if(j4 < 50) {
                     i3 = 16711680 + 5 * j4;
                  } else if(j4 < 100) {
                     i3 = 16711935 - 327680 * (j4 - 50);
                  } else if(j4 < 150) {
                     i3 = 255 + 327680 * (j4 - 100) - 5 * (j4 - 100);
                  }
               }

               if(this.anIntArray980[k] == 11) {
                  j4 = 150 - this.anIntArray982[k];
                  if(j4 < 50) {
                     i3 = 16777215 - 327685 * j4;
                  } else if(j4 < 100) {
                     i3 = '\uff00' + 327685 * (j4 - 50);
                  } else if(j4 < 150) {
                     i3 = 16777215 - 327680 * (j4 - 100);
                  }
               }

               if(this.anIntArray981[k] == 0) {
                  this.chatTextDrawingArea.drawText(0, var17, this.spriteDrawY + 1, this.spriteDrawX);
                  this.chatTextDrawingArea.drawText(i3, var17, this.spriteDrawY, this.spriteDrawX);
               }

               if(this.anIntArray981[k] == 1) {
                  this.chatTextDrawingArea.method386(0, var17, this.spriteDrawX, this.anInt1265, this.spriteDrawY + 1);
                  this.chatTextDrawingArea.method386(i3, var17, this.spriteDrawX, this.anInt1265, this.spriteDrawY);
               }

               if(this.anIntArray981[k] == 2) {
                  this.chatTextDrawingArea.method387(this.spriteDrawX, var17, this.anInt1265, this.spriteDrawY + 1, 0);
                  this.chatTextDrawingArea.method387(this.spriteDrawX, var17, this.anInt1265, this.spriteDrawY, i3);
               }

               if(this.anIntArray981[k] == 3) {
                  this.chatTextDrawingArea.method388(150 - this.anIntArray982[k], var17, this.anInt1265, this.spriteDrawY + 1, this.spriteDrawX, 0);
                  this.chatTextDrawingArea.method388(150 - this.anIntArray982[k], var17, this.anInt1265, this.spriteDrawY, this.spriteDrawX, i3);
               }

               int l4;
               if(this.anIntArray981[k] == 4) {
                  j4 = this.chatTextDrawingArea.method384(var17);
                  l4 = (150 - this.anIntArray982[k]) * (j4 + 100) / 150;
                  DrawingArea.setDrawingArea(334, this.spriteDrawX - 50, this.spriteDrawX + 50, 0);
                  this.chatTextDrawingArea.method385(0, var17, this.spriteDrawY + 1, this.spriteDrawX + 50 - l4);
                  this.chatTextDrawingArea.method385(i3, var17, this.spriteDrawY, this.spriteDrawX + 50 - l4);
                  DrawingArea.defaultDrawingAreaSize();
               }

               if(this.anIntArray981[k] == 5) {
                  j4 = 150 - this.anIntArray982[k];
                  l4 = 0;
                  if(j4 < 25) {
                     l4 = j4 - 25;
                  } else if(j4 > 125) {
                     l4 = j4 - 125;
                  }

                  DrawingArea.setDrawingArea(this.spriteDrawY + 5, 0, 512, this.spriteDrawY - this.chatTextDrawingArea.anInt1497 - 1);
                  this.chatTextDrawingArea.drawText(0, var17, this.spriteDrawY + 1 + l4, this.spriteDrawX);
                  this.chatTextDrawingArea.drawText(i3, var17, this.spriteDrawY + l4, this.spriteDrawX);
                  DrawingArea.defaultDrawingAreaSize();
               }
            } else {
               this.chatTextDrawingArea.drawText(0, var17, this.spriteDrawY + 1, this.spriteDrawX);
               this.chatTextDrawingArea.drawText(16776960, var17, this.spriteDrawY, this.spriteDrawX);
            }
         }
      } catch (Exception var13) {
         ;
      }

   }

   public void delFriend(long l) {
      try {
         if(l != 0L) {
            for(int runtimeexception = 0; runtimeexception < this.friendsCount; ++runtimeexception) {
               if(this.friendsListAsLongs[runtimeexception] == l) {
                  --this.friendsCount;
                  this.needDrawTabArea = true;

                  for(int j = runtimeexception; j < this.friendsCount; ++j) {
                     this.friendsList[j] = this.friendsList[j + 1];
                     this.friendsNodeIDs[j] = this.friendsNodeIDs[j + 1];
                     this.friendsListAsLongs[j] = this.friendsListAsLongs[j + 1];
                  }

                  this.stream.createFrame(215);
                  this.stream.writeQWord(l);
                  break;
               }
            }

         }
      } catch (RuntimeException var5) {
         SignLink.reporterror("18622, false, " + l + ", " + var5.toString());
         throw new RuntimeException();
      }
   }

   public void drawTabArea() {
      this.aRSImageProducer_1163.initDrawingArea();
      Texture.anIntArray1472 = this.anIntArray1181;
	  if(gameframeVersion != 474)
			this.invBack.method361(0, 0);
		else
			this.cacheSprite[23].drawSprite(0, 0);
      if(this.invOverlayInterfaceID != -1) {
         this.drawInterface(0, 0, RSInterface.interfaceCache[this.invOverlayInterfaceID], 0);
      } else if(this.tabInterfaceIDs[this.tabID] != -1) {
         this.drawInterface(0, 0, RSInterface.interfaceCache[this.tabInterfaceIDs[this.tabID]], 0);
      }

      if(this.menuOpen && this.menuScreenArea == 1) {
         this.drawMenu();
      }

	  if(gameframeVersion != 474)
		this.aRSImageProducer_1163.drawGraphics(205, super.graphics, 553);
	else
		this.aRSImageProducer_1163.drawGraphics(205, super.graphics, 547);
      this.aRSImageProducer_1165.initDrawingArea();
      Texture.anIntArray1472 = this.anIntArray1182;
   }

   static final void handleSounds() {
      if(aClass5_932 != null) {
         long l = System.currentTimeMillis();
         if(l > aLong1432) {
            aClass5_932.method489(l);
            int i_0_ = (int)(-aLong1432 + l);
            aLong1432 = l;
            synchronized(aClass1418 != null?aClass1418:(aClass1418 = method868("client.Class5"))) {
               anInt1526 += anInt197 * i_0_;
               int i_1_ = (anInt1526 - anInt197 * 2000) / 1000;
               if(i_1_ > 0) {
                  if(aClass3_Sub7_1345 != null) {
                     aClass3_Sub7_1345.method380(i_1_);
                  }

                  anInt1526 -= i_1_ * 1000;
               }
            }
         }
      }

   }

   public void method37(int j) {
      if(!lowMem) {
         Background background_2;
         int i1;
         int l1;
         byte[] abyte2;
         byte[] abyte5;
         int k2;
         if(Texture.anIntArray1480[17] >= j) {
            background_2 = Texture.aBackgroundArray1474s[17];
            i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
            l1 = background_2.anInt1452 * this.anInt945 * 2;
            abyte2 = background_2.aByteArray1450;
            abyte5 = this.aByteArray912;

            for(k2 = 0; k2 <= i1; ++k2) {
               abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_2.aByteArray1450 = abyte5;
            this.aByteArray912 = abyte2;
            Texture.method370(17);
				if (!editMode) {
					++anInt854;
					if (anInt854 > 1235) {
						anInt854 = 0;
						this.stream.createFrame(226);
						this.stream.writeWordBigEndian(0);
						k2 = this.stream.currentOffset;
						this.stream.writeWord('\ue562');
						this.stream.writeWordBigEndian(240);
						this.stream.writeWord((int) (Math.random() * 65536.0D));
						this.stream
								.writeWordBigEndian((int) (Math.random() * 256.0D));
						if ((int) (Math.random() * 2.0D) == 0) {
							this.stream.writeWord('\uca71');
						}

						this.stream
								.writeWordBigEndian((int) (Math.random() * 256.0D));
						this.stream.writeWord((int) (Math.random() * 65536.0D));
						this.stream.writeWord(7130);
						this.stream.writeWord((int) (Math.random() * 65536.0D));
						this.stream.writeWord('\uf0d9');
						this.stream.writeBytes(this.stream.currentOffset - k2);
					}
				}
         }

         if(Texture.anIntArray1480[24] >= j) {
            background_2 = Texture.aBackgroundArray1474s[24];
            i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
            l1 = background_2.anInt1452 * this.anInt945 * 2;
            abyte2 = background_2.aByteArray1450;
            abyte5 = this.aByteArray912;

            for(k2 = 0; k2 <= i1; ++k2) {
               abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_2.aByteArray1450 = abyte5;
            this.aByteArray912 = abyte2;
            Texture.method370(24);
         }

         if(Texture.anIntArray1480[34] >= j) {
            background_2 = Texture.aBackgroundArray1474s[34];
            i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
            l1 = background_2.anInt1452 * this.anInt945 * 2;
            abyte2 = background_2.aByteArray1450;
            abyte5 = this.aByteArray912;

            for(k2 = 0; k2 <= i1; ++k2) {
               abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_2.aByteArray1450 = abyte5;
            this.aByteArray912 = abyte2;
            Texture.method370(34);
         }

         if(Texture.anIntArray1480[40] >= j) {
            background_2 = Texture.aBackgroundArray1474s[40];
            i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
            l1 = background_2.anInt1452 * this.anInt945 * 2;
            abyte2 = background_2.aByteArray1450;
            abyte5 = this.aByteArray912;

            for(k2 = 0; k2 <= i1; ++k2) {
               abyte5[k2] = abyte2[k2 - l1 & i1];
            }

            background_2.aByteArray1450 = abyte5;
            this.aByteArray912 = abyte2;
            Texture.method370(40);
         }
      }

   }

   public void method38() {
      int k;
      int l;
      for(k = -1; k < this.playerCount; ++k) {
         if(k == -1) {
            l = this.myPlayerIndex;
         } else {
            l = this.playerIndices[k];
         }

         Player npc = this.playerArray[l];
         if(npc != null && npc.textCycle > 0) {
            --npc.textCycle;
            if(npc.textCycle == 0) {
               npc.textSpoken = null;
            }
         }
      }

      for(k = 0; k < this.npcCount; ++k) {
         l = this.npcIndices[k];
         NPC var4 = this.npcArray[l];
         if(var4 != null && var4.textCycle > 0) {
            --var4.textCycle;
            if(var4.textCycle == 0) {
               var4.textSpoken = null;
            }
         }
      }

   }

   public void calcCameraPos() {
      int i = this.anInt1098 * 128 + 64;
      int j = this.anInt1099 * 128 + 64;
      int k = this.method42(this.plane, j, i) - this.anInt1100;
      if(this.xCameraPos < i) {
         this.xCameraPos += this.anInt1101 + (i - this.xCameraPos) * this.anInt1102 / 1000;
         if(this.xCameraPos > i) {
            this.xCameraPos = i;
         }
      }

      if(this.xCameraPos > i) {
         this.xCameraPos -= this.anInt1101 + (this.xCameraPos - i) * this.anInt1102 / 1000;
         if(this.xCameraPos < i) {
            this.xCameraPos = i;
         }
      }

      if(this.zCameraPos < k) {
         this.zCameraPos += this.anInt1101 + (k - this.zCameraPos) * this.anInt1102 / 1000;
         if(this.zCameraPos > k) {
            this.zCameraPos = k;
         }
      }

      if(this.zCameraPos > k) {
         this.zCameraPos -= this.anInt1101 + (this.zCameraPos - k) * this.anInt1102 / 1000;
         if(this.zCameraPos < k) {
            this.zCameraPos = k;
         }
      }

      if(this.yCameraPos < j) {
         this.yCameraPos += this.anInt1101 + (j - this.yCameraPos) * this.anInt1102 / 1000;
         if(this.yCameraPos > j) {
            this.yCameraPos = j;
         }
      }

      if(this.yCameraPos > j) {
         this.yCameraPos -= this.anInt1101 + (this.yCameraPos - j) * this.anInt1102 / 1000;
         if(this.yCameraPos < j) {
            this.yCameraPos = j;
         }
      }

      i = this.anInt995 * 128 + 64;
      j = this.anInt996 * 128 + 64;
      k = this.method42(this.plane, j, i) - this.anInt997;
      int l = i - this.xCameraPos;
      int i1 = k - this.zCameraPos;
      int j1 = j - this.yCameraPos;
      int k1 = (int)Math.sqrt((double)(l * l + j1 * j1));
      int l1 = (int)(Math.atan2((double)i1, (double)k1) * 325.949D) & 2047;
      int i2 = (int)(Math.atan2((double)l, (double)j1) * -325.949D) & 2047;
      if(l1 < 128) {
         l1 = 128;
      }

      if(l1 > 383) {
         l1 = 383;
      }

      if(this.yCameraCurve < l1) {
         this.yCameraCurve += this.anInt998 + (l1 - this.yCameraCurve) * this.anInt999 / 1000;
         if(this.yCameraCurve > l1) {
            this.yCameraCurve = l1;
         }
      }

      if(this.yCameraCurve > l1) {
         this.yCameraCurve -= this.anInt998 + (this.yCameraCurve - l1) * this.anInt999 / 1000;
         if(this.yCameraCurve < l1) {
            this.yCameraCurve = l1;
         }
      }

      int j2 = i2 - this.xCameraCurve;
      if(j2 > 1024) {
         j2 -= 2048;
      }

      if(j2 < -1024) {
         j2 += 2048;
      }

      if(j2 > 0) {
         this.xCameraCurve += this.anInt998 + j2 * this.anInt999 / 1000;
         this.xCameraCurve &= 2047;
      }

      if(j2 < 0) {
         this.xCameraCurve -= this.anInt998 + -j2 * this.anInt999 / 1000;
         this.xCameraCurve &= 2047;
      }

      int k2 = i2 - this.xCameraCurve;
      if(k2 > 1024) {
         k2 -= 2048;
      }

      if(k2 < -1024) {
         k2 += 2048;
      }

      if(k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0) {
         this.xCameraCurve = i2;
      }

   }

   public void drawMenu() {
      int i = this.menuOffsetX;
      int j = this.menuOffsetY;
      int k = this.menuWidth;
      int l = this.anInt952;
      int i1 = 6116423;
      DrawingArea.method336(l, j, i, i1, k);
      DrawingArea.method336(16, j + 1, i + 1, 0, k - 2);
      DrawingArea.fillPixels(i + 1, k - 2, l - 19, 0, j + 18);
      this.chatTextDrawingArea.method385(i1, "Choose Option", j + 14, i + 3);
      int j1 = super.mouseX;
      int k1 = super.mouseY;
      if(this.menuScreenArea == 0) {
         j1 -= 4;
         k1 -= 4;
      }

      if(this.menuScreenArea == 1) {
         j1 -= 553;
         k1 -= 205;
      }

      if(this.menuScreenArea == 2) {
		if(gameframeVersion != 474){
			j1 -= 17;
			k1 -= 357;
		}else{
			j1 -= 7;
			k1 -= 345;
		}
      }

      for(int l1 = 0; l1 < this.menuActionRow; ++l1) {
         int i2 = j + 31 + (this.menuActionRow - 1 - l1) * 15;
         int j2 = 16777215;
         if(j1 > i && j1 < i + k && k1 > i2 - 13 && k1 < i2 + 3) {
            j2 = 16776960;
         }

         this.chatTextDrawingArea.method389(true, i + 3, j2, this.menuActionName[l1], i2);
      }

   }

   public void addFriend(long l) {
      try {
         if(l != 0L) {
            if(this.friendsCount >= 100 && this.anInt1046 != 1) {
               this.pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
            } else if(this.friendsCount >= 200) {
               this.pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
            } else {
               String runtimeexception = TextClass.fixName(TextClass.nameForLong(l));

               int j;
               for(j = 0; j < this.friendsCount; ++j) {
                  if(this.friendsListAsLongs[j] == l) {
                     this.pushMessage(runtimeexception + " is already on your friend list", 0, "");
                     return;
                  }
               }

               for(j = 0; j < this.ignoreCount; ++j) {
                  if(this.ignoreListAsLongs[j] == l) {
                     this.pushMessage("Please remove " + runtimeexception + " from your ignore list first", 0, "");
                     return;
                  }
               }

               if(!runtimeexception.equals(myPlayer.name)) {
                  this.friendsList[this.friendsCount] = runtimeexception;
                  this.friendsListAsLongs[this.friendsCount] = l;
                  this.friendsNodeIDs[this.friendsCount] = 0;
                  ++this.friendsCount;
                  this.needDrawTabArea = true;
                  this.stream.createFrame(188);
                  this.stream.writeQWord(l);
               }
            }
         }
      } catch (RuntimeException var5) {
         SignLink.reporterror("15283, 68, " + l + ", " + var5.toString());
         throw new RuntimeException();
      }
   }

   public int method42(int i, int j, int k) {
      int l = k >> 7;
      int i1 = j >> 7;
      if(l >= 0 && i1 >= 0 && l <= 103 && i1 <= 103) {
         int j1 = i;
         if(i < 3 && (this.byteGroundArray[1][l][i1] & 2) == 2) {
            j1 = i + 1;
         }

         int k1 = k & 127;
         int l1 = j & 127;
         int i2 = this.intGroundArray[j1][l][i1] * (128 - k1) + this.intGroundArray[j1][l + 1][i1] * k1 >> 7;
         int j2 = this.intGroundArray[j1][l][i1 + 1] * (128 - k1) + this.intGroundArray[j1][l + 1][i1 + 1] * k1 >> 7;
         return i2 * (128 - l1) + j2 * l1 >> 7;
      } else {
         return 0;
      }
   }

   public static String intToKOrMil(int j) {
      return j < 100000?String.valueOf(j):(j < 10000000?j / 1000 + "K":j / 1000000 + "M");
   }

   static final boolean musicIsntNull() {
      return aClass56_749 != null;
   }

   public void resetConfigs(){
	  this.anIntArray1045 = new int[2000];
	  for(int var20 = 0; var20 < this.variousSettings.length; ++var20) {
		  if(var20 == 168)//music volume
			  continue;
                  if(this.variousSettings[var20] != this.anIntArray1045[var20]) {
                     this.variousSettings[var20] = this.anIntArray1045[var20];
                     this.method33(var20);
                     this.needDrawTabArea = true;
                  }
               }
   }
   
   public void resetLogout() {
      try {
         if(this.socketStream != null) {
            this.socketStream.close();
         }
      } catch (Exception var2) {
         ;
      }

      this.socketStream = null;
      this.loggedIn = false;
	  resetConfigs();//resets configs on logout
      this.loginScreenState = 0;
      this.unlinkMRUNodes();
      this.worldController.initToNull();

      for(int i = 0; i < 4; ++i) {
         this.aClass11Array1230[i].method210();
      }

      System.gc();
      this.stopMidi();
      this.currentSong = -1;
      this.nextSong = -1;
      this.previousSong = 0;
	  checkSpecialTheme();
      this.method58(18, musicVolume, false, themeSong);
   }

   public void method45() {
      this.aBoolean1031 = true;
      int j = 0;

      while(j < 7) {
         this.anIntArray1065[j] = -1;
         int k = 0;

         while(true) {
            if(k < IDK.length) {
               if(IDK.cache[k].aBoolean662 || IDK.cache[k].anInt657 != j + (this.aBoolean1047?0:7)) {
                  ++k;
                  continue;
               }

               this.anIntArray1065[j] = k;
            }

            ++j;
            break;
         }
      }

   }

   static final void method49() {
      if(musicIsntNull()) {
         if(fetchMusic) {
            byte[] is = musicData;
            if(is != null) {
               if(anInt116 >= 0) {
                  method684(aBoolean995, anInt116, musicVolume2, is);
               } else if(anInt139 >= 0) {
                  method899(anInt139, -1, aBoolean995, is, musicVolume2);
               } else {
                  method853(musicVolume2, is, aBoolean995);
               }

               fetchMusic = false;
            }
         }

         method368(0);
      }

   }

   public void method46(int i, Stream stream) {
      while(true) {
         if(stream.bitPosition + 21 < i * 8) {
            int k = stream.readBits(14);
            if(k != 16383) {
               if(this.npcArray[k] == null) {
                  this.npcArray[k] = new NPC();
               }

               NPC npc = this.npcArray[k];
               this.npcIndices[this.npcCount++] = k;
               npc.anInt1537 = loopCycle;
               int l = stream.readBits(5);
               if(l > 15) {
                  l -= 32;
               }

               int i1 = stream.readBits(5);
               if(i1 > 15) {
                  i1 -= 32;
               }

               int j1 = stream.readBits(1);
               npc.desc = EntityDef.forID(stream.readBits(12));
               int k1 = stream.readBits(1);
               if(k1 == 1) {
                  this.anIntArray894[this.anInt893++] = k;
               }

               npc.anInt1540 = npc.desc.aByte68;
               npc.anInt1504 = npc.desc.anInt79;
               npc.anInt1554 = npc.desc.anInt67;
               npc.anInt1555 = npc.desc.anInt58;
               npc.anInt1556 = npc.desc.anInt83;
               npc.anInt1557 = npc.desc.anInt55;
               npc.anInt1511 = npc.desc.anInt77;
               npc.setPos(myPlayer.smallX[0] + i1, myPlayer.smallY[0] + l, j1 == 1);
               continue;
            }
         }

         stream.finishBitAccess();
         return;
      }
   }

   public void processGameLoop() {
      if(!this.rsAlreadyLoaded && !this.loadingError && !this.genericLoadingError) {
         ++loopCycle;
         method49();
         handleSounds();
         if(!this.loggedIn) {
            this.processLoginScreenInput();
         } else {
            this.mainGameProcessor();
         }

         this.processOnDemandQueue();
      }
   }

   public void method47(boolean flag) {
      if(myPlayer.x >> 7 == this.destX && myPlayer.y >> 7 == this.destY) {
         this.destX = 0;
      }

      int j = this.playerCount;
      if(flag) {
         j = 1;
      }

      for(int l = 0; l < j; ++l) {
         Player player;
         int i1;
         if(flag) {
            player = myPlayer;
            i1 = this.myPlayerIndex << 14;
         } else {
            player = this.playerArray[this.playerIndices[l]];
            i1 = this.playerIndices[l] << 14;
         }

         if(player != null && player.isVisible()) {
            player.aBoolean1699 = (lowMem && this.playerCount > 50 || this.playerCount > 200) && !flag && player.anInt1517 == player.anInt1511;
            int j1 = player.x >> 7;
            int k1 = player.y >> 7;
            if(j1 >= 0 && j1 < 104 && k1 >= 0 && k1 < 104) {
               if(player.aModel_1714 != null && loopCycle >= player.anInt1707 && loopCycle < player.anInt1708) {
                  player.aBoolean1699 = false;
                  player.anInt1709 = this.method42(this.plane, player.y, player.x);
                  this.worldController.method286(this.plane, player.y, player, player.anInt1552, player.anInt1722, player.x, player.anInt1709, player.anInt1719, player.anInt1721, i1, player.anInt1720);
               } else {
                  if((player.x & 127) == 64 && (player.y & 127) == 64) {
                     if(this.anIntArrayArray929[j1][k1] == this.anInt1265) {
                        continue;
                     }

                     this.anIntArrayArray929[j1][k1] = this.anInt1265;
                  }

                  player.anInt1709 = this.method42(this.plane, player.y, player.x);
                  this.worldController.method285(this.plane, player.anInt1552, player.anInt1709, i1, player.y, 60, player.x, player, player.aBoolean1541);
               }
            }
         }
      }

   }

   public boolean promptUserForInput(RSInterface class9) {
      int j = class9.anInt214;
      if(this.anInt900 == 2) {
         if(j == 201) {
            this.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 1;
            this.aString1121 = "Enter name of friend to add to list";
         }

         if(j == 202) {
            this.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 2;
            this.aString1121 = "Enter name of friend to delete from list";
         }
      }

      if(j == 205) {
         this.anInt1011 = 250;
         return true;
      } else {
         if(j == 501) {
            this.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 4;
            this.aString1121 = "Enter name of player to add to list";
         }

         if(j == 502) {
            this.inputTaken = true;
            this.inputDialogState = 0;
            this.messagePromptRaised = true;
            this.promptInput = "";
            this.friendsListAction = 5;
            this.aString1121 = "Enter name of player to delete from list";
         }

         int l1;
         int k1;
         int j2;
         if(j >= 300 && j <= 313) {
            l1 = (j - 300) / 2;
            k1 = j & 1;
            j2 = this.anIntArray1065[l1];
            if(j2 != -1) {
               do {
                  do {
                     if(k1 == 0) {
                        --j2;
                        if(j2 < 0) {
                           j2 = IDK.length - 1;
                        }
                     }

                     if(k1 == 1) {
                        ++j2;
                        if(j2 >= IDK.length) {
                           j2 = 0;
                        }
                     }
                  } while(IDK.cache[j2].aBoolean662);
               } while(IDK.cache[j2].anInt657 != l1 + (this.aBoolean1047?0:7));

               this.anIntArray1065[l1] = j2;
               this.aBoolean1031 = true;
            }
         }

         if(j >= 314 && j <= 323) {
            l1 = (j - 314) / 2;
            k1 = j & 1;
            j2 = this.anIntArray990[l1];
            if(k1 == 0) {
               --j2;
               if(j2 < 0) {
                  j2 = anIntArrayArray1003[l1].length - 1;
               }
            }

            if(k1 == 1) {
               ++j2;
               if(j2 >= anIntArrayArray1003[l1].length) {
                  j2 = 0;
               }
            }

            this.anIntArray990[l1] = j2;
            this.aBoolean1031 = true;
         }

         if(j == 324 && !this.aBoolean1047) {
            this.aBoolean1047 = true;
            this.method45();
         }

         if(j == 325 && this.aBoolean1047) {
            this.aBoolean1047 = false;
            this.method45();
         }

         if(j != 326) {
            if(j == 620) {
               this.canMute = !this.canMute;
            }

            if(j >= 601 && j <= 613) {
               this.clearTopInterfaces();
               if(this.reportAbuseInput.length() > 0) {
                  this.stream.createFrame(218);
                  this.stream.writeQWord(TextClass.longForName(this.reportAbuseInput));
                  this.stream.writeWordBigEndian(j - 601);
                  this.stream.writeWordBigEndian(this.canMute?1:0);
               }
            }

            return false;
         } else {
            this.stream.createFrame(101);
            this.stream.writeWordBigEndian(this.aBoolean1047?0:1);

            for(l1 = 0; l1 < 7; ++l1) {
               this.stream.writeWordBigEndian(this.anIntArray1065[l1]);
            }

            for(l1 = 0; l1 < 5; ++l1) {
               this.stream.writeWordBigEndian(this.anIntArray990[l1]);
            }

            return true;
         }
      }
   }

   public void method49(Stream stream) {
      for(int j = 0; j < this.anInt893; ++j) {
         int k = this.anIntArray894[j];
         Player player = this.playerArray[k];
         int l = stream.readUnsignedByte();
         if((l & 64) != 0) {
            l += stream.readUnsignedByte() << 8;
         }

         this.method107(l, k, stream, player);
      }

   }

   public void method50(int i, int k, int l, int i1, int j1) {
      int k1 = this.worldController.method300(j1, l, i);
      int j2;
      int class46;
      int background;
      int i4;
      int l4;
      int ai1;
      if(k1 != 0) {
         j2 = this.worldController.method304(j1, l, i, k1);
         class46 = j2 >> 6 & 3;
         background = j2 & 31;
         i4 = k;
         if(k1 > 0) {
            i4 = i1;
         }

         int[] j4 = this.aClass30_Sub2_Sub1_Sub1_1263.myPixels;
         l4 = 24624 + l * 4 + (103 - i) * 512 * 4;
         ai1 = k1 >> 14 & 32767;
         ObjectDef l5 = ObjectDef.forID(ai1);
         if(l5.anInt758 != -1) {
            Background background_2 = this.mapScenes[l5.anInt758];
            if(background_2 != null) {
               int i6 = (l5.anInt744 * 4 - background_2.anInt1452) / 2;
               int j6 = (l5.anInt761 * 4 - background_2.anInt1453) / 2;
               background_2.method361(48 + l * 4 + i6, 48 + (104 - i - l5.anInt761) * 4 + j6);
            }
         } else {
            if(background == 0 || background == 2) {
               if(class46 == 0) {
                  j4[l4] = i4;
                  j4[l4 + 512] = i4;
                  j4[l4 + 1024] = i4;
                  j4[l4 + 1536] = i4;
               } else if(class46 == 1) {
                  j4[l4] = i4;
                  j4[l4 + 1] = i4;
                  j4[l4 + 2] = i4;
                  j4[l4 + 3] = i4;
               } else if(class46 == 2) {
                  j4[l4 + 3] = i4;
                  j4[l4 + 3 + 512] = i4;
                  j4[l4 + 3 + 1024] = i4;
                  j4[l4 + 3 + 1536] = i4;
               } else if(class46 == 3) {
                  j4[l4 + 1536] = i4;
                  j4[l4 + 1536 + 1] = i4;
                  j4[l4 + 1536 + 2] = i4;
                  j4[l4 + 1536 + 3] = i4;
               }
            }

            if(background == 3) {
               if(class46 == 0) {
                  j4[l4] = i4;
               } else if(class46 == 1) {
                  j4[l4 + 3] = i4;
               } else if(class46 == 2) {
                  j4[l4 + 3 + 1536] = i4;
               } else if(class46 == 3) {
                  j4[l4 + 1536] = i4;
               }
            }

            if(background == 2) {
               if(class46 == 3) {
                  j4[l4] = i4;
                  j4[l4 + 512] = i4;
                  j4[l4 + 1024] = i4;
                  j4[l4 + 1536] = i4;
               } else if(class46 == 0) {
                  j4[l4] = i4;
                  j4[l4 + 1] = i4;
                  j4[l4 + 2] = i4;
                  j4[l4 + 3] = i4;
               } else if(class46 == 1) {
                  j4[l4 + 3] = i4;
                  j4[l4 + 3 + 512] = i4;
                  j4[l4 + 3 + 1024] = i4;
                  j4[l4 + 3 + 1536] = i4;
               } else if(class46 == 2) {
                  j4[l4 + 1536] = i4;
                  j4[l4 + 1536 + 1] = i4;
                  j4[l4 + 1536 + 2] = i4;
                  j4[l4 + 1536 + 3] = i4;
               }
            }
         }
      }

      k1 = this.worldController.method302(j1, l, i);
      if(k1 != 0) {
         j2 = this.worldController.method304(j1, l, i, k1);
         class46 = j2 >> 6 & 3;
         background = j2 & 31;
         i4 = k1 >> 14 & 32767;
         ObjectDef j41 = ObjectDef.forID(i4);
         int l51;
         if(j41.anInt758 != -1) {
            Background l41 = this.mapScenes[j41.anInt758];
            if(l41 != null) {
               ai1 = (j41.anInt744 * 4 - l41.anInt1452) / 2;
               l51 = (j41.anInt761 * 4 - l41.anInt1453) / 2;
               l41.method361(48 + l * 4 + ai1, 48 + (104 - i - j41.anInt761) * 4 + l51);
            }
         } else if(background == 9) {
            l4 = 15658734;
            if(k1 > 0) {
               l4 = 15597568;
            }

            int[] ai11 = this.aClass30_Sub2_Sub1_Sub1_1263.myPixels;
            l51 = 24624 + l * 4 + (103 - i) * 512 * 4;
            if(class46 != 0 && class46 != 2) {
               ai11[l51] = l4;
               ai11[l51 + 512 + 1] = l4;
               ai11[l51 + 1024 + 2] = l4;
               ai11[l51 + 1536 + 3] = l4;
            } else {
               ai11[l51 + 1536] = l4;
               ai11[l51 + 1024 + 1] = l4;
               ai11[l51 + 512 + 2] = l4;
               ai11[l51 + 3] = l4;
            }
         }
      }

      k1 = this.worldController.method303(j1, l, i);
      if(k1 != 0) {
         j2 = k1 >> 14 & 32767;
         ObjectDef class461 = ObjectDef.forID(j2);
         if(class461.anInt758 != -1) {
            Background background1 = this.mapScenes[class461.anInt758];
            if(background1 != null) {
               i4 = (class461.anInt744 * 4 - background1.anInt1452) / 2;
               int j42 = (class461.anInt761 * 4 - background1.anInt1453) / 2;
               background1.method361(48 + l * 4 + i4, 48 + (104 - i - class461.anInt761) * 4 + j42);
            }
         }
      }

   }

   static final void method55(boolean bool) {
      if(musicIsntNull()) {
         method891(bool);
         fetchMusic = false;
      }
   }

   public void loadTitleScreen() {
      this.aBackground_966 = new Background(this.titleStreamLoader, "titlebox", 0);
      this.aBackground_967 = new Background(this.titleStreamLoader, "titlebutton", 0);
      this.aBackgroundArray1152s = new Background[12];
      int j = 0;

      try {
         j = Integer.parseInt(this.getParameter("fl_icon"));
      } catch (Exception var3) {
         ;
      }

      int j4;
      if(j == 0) {
         for(j4 = 0; j4 < 12; ++j4) {
            this.aBackgroundArray1152s[j4] = new Background(this.titleStreamLoader, "runes", j4);
         }
      } else {
         for(j4 = 0; j4 < 12; ++j4) {
            this.aBackgroundArray1152s[j4] = new Background(this.titleStreamLoader, "runes", 12 + (j4 & 3));
         }
      }

      this.aClass30_Sub2_Sub1_Sub1_1201 = new Sprite(128, 265);
      this.aClass30_Sub2_Sub1_Sub1_1202 = new Sprite(128, 265);
      System.arraycopy(this.aRSImageProducer_1110.anIntArray315, 0, this.aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, '\u8480');
      System.arraycopy(this.aRSImageProducer_1111.anIntArray315, 0, this.aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, '\u8480');
      this.anIntArray851 = new int[256];

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray851[j4] = j4 * 262144;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray851[j4 + 64] = 16711680 + 1024 * j4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray851[j4 + 128] = 16776960 + 4 * j4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray851[j4 + 192] = 16777215;
      }

      this.anIntArray852 = new int[256];

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray852[j4] = j4 * 1024;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray852[j4 + 64] = '\uff00' + 4 * j4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray852[j4 + 128] = '\uffff' + 262144 * j4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray852[j4 + 192] = 16777215;
      }

      this.anIntArray853 = new int[256];

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray853[j4] = j4 * 4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray853[j4 + 64] = 255 + 262144 * j4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray853[j4 + 128] = 16711935 + 1024 * j4;
      }

      for(j4 = 0; j4 < 64; ++j4) {
         this.anIntArray853[j4 + 192] = 16777215;
      }

      this.anIntArray850 = new int[256];
      this.anIntArray1190 = new int['\u8000'];
      this.anIntArray1191 = new int['\u8000'];
      this.randomizeBackground((Background)null);
      this.anIntArray828 = new int['\u8000'];
      this.anIntArray829 = new int['\u8000'];
      this.drawLoadingText(10, "Connecting to fileserver");
      if(!this.aBoolean831) {
         this.drawFlames = true;
         this.aBoolean831 = true;
         this.startRunnable(this, 2);
      }

   }

   static final void threadSleep(long time) {
      try {
         Thread.sleep(time);
      } catch (InterruptedException var3) {
         ;
      }

   }

   public static void setHighMem() {
      WorldController.lowMem = false;
      Texture.lowMem = false;
      lowMem = false;
      ObjectManager.lowMem = false;
      ObjectDef.lowMem = false;
   }

   static final void method900(int i) {
      if(aClass56_749 != null) {
         if(anInt720 == 0) {
            if(anInt478 >= 0) {
               anInt478 = i;
               aClass56_749.method830(i, 0);
            }
         } else if(aByteArray347 != null) {
            anInt1478 = i;
         }
      }

   }

   public static void main(String[] args) {
      try {
         nodeID = 10;
         portOff = 0;
         setHighMem();
         isMembers = true;
         SignLink.storeid = 32;
         SignLink.startpriv(InetAddress.getLocalHost());
         instance = new Jframe(args);
      } catch (Exception var2) {
         ;
      }

   }
   
   public void loadingStages() {
      if(lowMem && this.loadingStage == 2 && ObjectManager.anInt131 != this.plane) {
         this.aRSImageProducer_1165.initDrawingArea();
         this.aTextDrawingArea_1271.drawText(0, "Loading - please wait.", 151, 257);
         this.aTextDrawingArea_1271.drawText(16777215, "Loading - please wait.", 150, 256);
         this.aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
         this.loadingStage = 1;
         this.aLong824 = System.currentTimeMillis();
      }

      if(this.loadingStage == 1) {
         int j = this.method54();
         if(j != 0 && System.currentTimeMillis() - this.aLong824 > 360000L) {
            SignLink.reporterror(this.myUsername + " glcfb " + this.aLong1215 + "," + j + "," + lowMem + "," + this.decompressors[0] + "," + this.onDemandFetcher.getNodeCount() + "," + this.plane + "," + this.anInt1069 + "," + this.anInt1070);
            this.aLong824 = System.currentTimeMillis();
         }
      }

      if(this.loadingStage == 2 && this.plane != this.anInt985) {
         this.anInt985 = this.plane;
         this.method24(this.plane);
      }

   }

   public int method54() {
      for(int flag = 0; flag < this.aByteArrayArray1183.length; ++flag) {
         if(this.aByteArrayArray1183[flag] == null && this.anIntArray1235[flag] != -1) {
            return -1;
         }

         if(this.aByteArrayArray1247[flag] == null && this.anIntArray1236[flag] != -1) {
            return -2;
         }
      }

      boolean var6 = true;

      for(int j = 0; j < this.aByteArrayArray1183.length; ++j) {
         byte[] abyte0 = this.aByteArrayArray1247[j];
         if(abyte0 != null) {
            int k = (this.anIntArray1234[j] >> 8) * 64 - this.baseX;
            int l = (this.anIntArray1234[j] & 255) * 64 - this.baseY;
            if(this.aBoolean1159) {
               k = 10;
               l = 10;
            }

            var6 &= ObjectManager.method189(k, abyte0, l);
         }
      }

      if(!var6) {
         return -3;
      } else if(this.aBoolean1080) {
         return -4;
      } else {
         this.loadingStage = 2;
         ObjectManager.anInt131 = this.plane;
         this.method22();
         if(!editMode)
         this.stream.createFrame(121);
         return 0;
      }
   }

   public void method55() {
      for(Animable_Sub4 class30_sub2_sub4_sub4 = (Animable_Sub4)this.aClass19_1013.reverseGetFirst(); class30_sub2_sub4_sub4 != null; class30_sub2_sub4_sub4 = (Animable_Sub4)this.aClass19_1013.reverseGetNext()) {
         if(class30_sub2_sub4_sub4.anInt1597 == this.plane && loopCycle <= class30_sub2_sub4_sub4.anInt1572) {
            if(loopCycle >= class30_sub2_sub4_sub4.anInt1571) {
               if(class30_sub2_sub4_sub4.anInt1590 > 0) {
                  NPC j = this.npcArray[class30_sub2_sub4_sub4.anInt1590 - 1];
                  if(j != null && j.x >= 0 && j.x < 13312 && j.y >= 0 && j.y < 13312) {
                     class30_sub2_sub4_sub4.method455(loopCycle, j.y, this.method42(class30_sub2_sub4_sub4.anInt1597, j.y, j.x) - class30_sub2_sub4_sub4.anInt1583, j.x);
                  }
               }

               if(class30_sub2_sub4_sub4.anInt1590 < 0) {
                  int j1 = -class30_sub2_sub4_sub4.anInt1590 - 1;
                  Player player;
                  if(j1 == this.unknownInt10) {
                     player = myPlayer;
                  } else {
                     player = this.playerArray[j1];
                  }

                  if(player != null && player.x >= 0 && player.x < 13312 && player.y >= 0 && player.y < 13312) {
                     class30_sub2_sub4_sub4.method455(loopCycle, player.y, this.method42(class30_sub2_sub4_sub4.anInt1597, player.y, player.x) - class30_sub2_sub4_sub4.anInt1583, player.x);
                  }
               }

               class30_sub2_sub4_sub4.method456(this.anInt945);
               this.worldController.method285(this.plane, class30_sub2_sub4_sub4.anInt1595, (int)class30_sub2_sub4_sub4.aDouble1587, -1, (int)class30_sub2_sub4_sub4.aDouble1586, 60, (int)class30_sub2_sub4_sub4.aDouble1585, class30_sub2_sub4_sub4, false);
            }
         } else {
            class30_sub2_sub4_sub4.unlink();
         }
      }

   }

   public AppletContext getAppletContext() {
      return SignLink.mainapp != null?SignLink.mainapp.getAppletContext():super.getAppletContext();
   }

   public void drawLogo() {
	checkSpecialTheme();
	 String image = "title.dat";
	 if(loginscreenBackground > 0){
		image = "title"+loginscreenBackground+".dat";
	 }
      byte[] abyte0 = this.titleStreamLoader.getDataForName(image);
      Sprite sprite = new Sprite(abyte0, this);
      this.aRSImageProducer_1110.initDrawingArea();
      sprite.method346(0, 0);
      this.aRSImageProducer_1111.initDrawingArea();
      sprite.method346(-637, 0);
      this.aRSImageProducer_1107.initDrawingArea();
      sprite.method346(-128, 0);
      this.aRSImageProducer_1108.initDrawingArea();
      sprite.method346(-202, -371);
      this.aRSImageProducer_1109.initDrawingArea();
      sprite.method346(-202, -171);
      this.aRSImageProducer_1112.initDrawingArea();
      sprite.method346(0, -265);
      this.aRSImageProducer_1113.initDrawingArea();
      sprite.method346(-562, -265);
      this.aRSImageProducer_1114.initDrawingArea();
      sprite.method346(-128, -171);
      this.aRSImageProducer_1115.initDrawingArea();
      sprite.method346(-562, -171);
      int[] ai = new int[sprite.myWidth];

      for(int j = 0; j < sprite.myHeight; ++j) {
         for(int k = 0; k < sprite.myWidth; ++k) {
            ai[k] = sprite.myPixels[sprite.myWidth - k - 1 + sprite.myWidth * j];
         }

         System.arraycopy(ai, 0, sprite.myPixels, sprite.myWidth * j, sprite.myWidth);
      }

      this.aRSImageProducer_1110.initDrawingArea();
      sprite.method346(382, 0);
      this.aRSImageProducer_1111.initDrawingArea();
      sprite.method346(-255, 0);
      this.aRSImageProducer_1107.initDrawingArea();
      sprite.method346(254, 0);
      this.aRSImageProducer_1108.initDrawingArea();
      sprite.method346(180, -371);
      this.aRSImageProducer_1109.initDrawingArea();
      sprite.method346(180, -171);
      this.aRSImageProducer_1112.initDrawingArea();
      sprite.method346(382, -265);
      this.aRSImageProducer_1113.initDrawingArea();
      sprite.method346(-180, -265);
      this.aRSImageProducer_1114.initDrawingArea();
      sprite.method346(254, -171);
      this.aRSImageProducer_1115.initDrawingArea();
      sprite.method346(-180, -171);
      sprite = new Sprite(this.titleStreamLoader, "logo", 0);
      this.aRSImageProducer_1107.initDrawingArea();
      sprite.drawSprite(382 - sprite.myWidth / 2 - 128, 18);
      aRSImageProducer_1113.initDrawingArea();
      cacheSprite[musicVolume > 0 && !lowMem ? 43 : 44].drawSprite(725-562, 463-265);
      sprite = null;
      System.gc();
   }

   public void processOnDemandQueue() {
      while(true) {
         OnDemandData onDemandData = this.onDemandFetcher.getNextNode();
         if(onDemandData == null) {
            return;
         }

         if(onDemandData.dataType == 0) {
            Model.method460(onDemandData.buffer, onDemandData.ID);
            if((this.onDemandFetcher.getModelIndex(onDemandData.ID) & 98) != 0) {
               this.needDrawTabArea = true;
               if(this.backDialogID != -1) {
                  this.inputTaken = true;
               }
            }
         }

         if(onDemandData.dataType == 1 && onDemandData.buffer != null) {
            Class36.method529(onDemandData.buffer);
         }

         if(onDemandData.dataType == 2 && onDemandData.ID == this.nextSong && onDemandData.buffer != null) {
            musicData = new byte[onDemandData.buffer.length];
            ArrayUtils.arraycopy(onDemandData.buffer, 0, musicData, 0, musicData.length);
            fetchMusic = true;
         }

         if(onDemandData.dataType == 3 && this.loadingStage == 1) {
            for(int i = 0; i < this.aByteArrayArray1183.length; ++i) {
               if(this.anIntArray1235[i] == onDemandData.ID) {
                  this.aByteArrayArray1183[i] = onDemandData.buffer;
                  if(onDemandData.buffer == null) {
                     this.anIntArray1235[i] = -1;
                  }
                  break;
               }

               if(this.anIntArray1236[i] == onDemandData.ID) {
                  this.aByteArrayArray1247[i] = onDemandData.buffer;
                  if(onDemandData.buffer == null) {
                     this.anIntArray1236[i] = -1;
                  }
                  break;
               }
            }
         }

         if(onDemandData.dataType == 93 && this.onDemandFetcher.method564(onDemandData.ID)) {
            ObjectManager.method173(new Stream(onDemandData.buffer), this.onDemandFetcher);
         }
      }
   }

   public void calcFlamesPosition() {
      short c = 256;

      int l3;
      int i3;
      for(l3 = 10; l3 < 117; ++l3) {
         i3 = (int)(Math.random() * 100.0D);
         if(i3 < 50) {
            this.anIntArray828[l3 + (c - 2 << 7)] = 255;
         }
      }

      int k3;
      int i4;
      for(l3 = 0; l3 < 100; ++l3) {
         i3 = (int)(Math.random() * 124.0D) + 2;
         k3 = (int)(Math.random() * 128.0D) + 128;
         i4 = i3 + (k3 << 7);
         this.anIntArray828[i4] = 192;
      }

      for(l3 = 1; l3 < c - 1; ++l3) {
         for(i3 = 1; i3 < 127; ++i3) {
            k3 = i3 + (l3 << 7);
            this.anIntArray829[k3] = (this.anIntArray828[k3 - 1] + this.anIntArray828[k3 + 1] + this.anIntArray828[k3 - 128] + this.anIntArray828[k3 + 128]) / 4;
         }
      }

      this.anInt1275 += 128;
      if(this.anInt1275 > this.anIntArray1190.length) {
         this.anInt1275 -= this.anIntArray1190.length;
         l3 = (int)(Math.random() * 12.0D);
         this.randomizeBackground(this.aBackgroundArray1152s[l3]);
      }

      for(l3 = 1; l3 < c - 1; ++l3) {
         for(i3 = 1; i3 < 127; ++i3) {
            k3 = i3 + (l3 << 7);
            i4 = this.anIntArray829[k3 + 128] - this.anIntArray1190[k3 + this.anInt1275 & this.anIntArray1190.length - 1] / 5;
            if(i4 < 0) {
               i4 = 0;
            }

            this.anIntArray828[k3] = i4;
         }
      }

      System.arraycopy(this.anIntArray969, 1, this.anIntArray969, 0, c - 1);
      this.anIntArray969[c - 1] = (int)(Math.sin((double)loopCycle / 14.0D) * 16.0D + Math.sin((double)loopCycle / 15.0D) * 14.0D + Math.sin((double)loopCycle / 16.0D) * 12.0D);
      if(this.anInt1040 > 0) {
         this.anInt1040 -= 4;
      }

      if(this.anInt1041 > 0) {
         this.anInt1041 -= 4;
      }

      if(this.anInt1040 == 0 && this.anInt1041 == 0) {
         l3 = (int)(Math.random() * 2000.0D);
         if(l3 == 0) {
            this.anInt1040 = 1024;
         }

         if(l3 == 1) {
            this.anInt1041 = 1024;
         }
      }

   }

   public boolean saveWave(byte[] abyte0, int i) {
      return abyte0 == null || SignLink.wavesave(abyte0, i);
   }

   public void method60(int i) {
      RSInterface class9 = RSInterface.interfaceCache[i];
      int[] arr$ = class9.children;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int element = arr$[i$];
         if(element == -1) {
            break;
         }

         RSInterface class9_1 = RSInterface.interfaceCache[element];
         if(class9_1.type == 1) {
            this.method60(class9_1.id);
         }

         class9_1.anInt246 = 0;
         class9_1.anInt208 = 0;
      }

   }

   public void drawHeadIcon() {
      if(this.anInt855 == 2) {
         this.calcEntityScreenPos((this.anInt934 - this.baseX << 7) + this.anInt937, this.anInt936 * 2, (this.anInt935 - this.baseY << 7) + this.anInt938);
         if(this.spriteDrawX > -1 && loopCycle % 20 < 10) {
            this.headIconsHint[0].drawSprite(this.spriteDrawX - 12, this.spriteDrawY - 28);
         }

      }
   }
   
   public void mainGameProcessor() {
      if(this.anInt1104 > 1) {
         --this.anInt1104;
      }

      if(this.anInt1011 > 0) {
         --this.anInt1011;
      }

      int exception;
      for(exception = 0; exception < 5 && this.parsePacket(); ++exception) {
         ;
      }

      if(this.loggedIn) {
         Object var14 = this.mouseDetection.syncObject;
         int k1;
         int flag;
         int i4;
         int k4;
         int l5;
         synchronized(this.mouseDetection.syncObject) {
            if(flagged) {
               if(super.clickMode3 != 0 || this.mouseDetection.coordsIndex >= 40) {
                  this.stream.createFrame(45);
                  this.stream.writeWordBigEndian(0);
                  k1 = this.stream.currentOffset;
                  flag = 0;

                  for(i4 = 0; i4 < this.mouseDetection.coordsIndex && k1 - this.stream.currentOffset < 240; ++i4) {
                     ++flag;
                     k4 = this.mouseDetection.coordsY[i4];
                     if(k4 < 0) {
                        k4 = 0;
                     } else if(k4 > 502) {
                        k4 = 502;
                     }

                     int j5 = this.mouseDetection.coordsX[i4];
                     if(j5 < 0) {
                        j5 = 0;
                     } else if(j5 > 764) {
                        j5 = 764;
                     }

                     l5 = k4 * 765 + j5;
                     if(this.mouseDetection.coordsY[i4] == -1 && this.mouseDetection.coordsX[i4] == -1) {
                        j5 = -1;
                        k4 = -1;
                        l5 = 524287;
                     }

                     if(j5 == this.anInt1237 && k4 == this.anInt1238) {
                        if(this.anInt1022 < 2047) {
                           ++this.anInt1022;
                        }
                     } else {
                        int j6 = j5 - this.anInt1237;
                        this.anInt1237 = j5;
                        int k6 = k4 - this.anInt1238;
                        this.anInt1238 = k4;
                        if(this.anInt1022 < 8 && j6 >= -32 && j6 <= 31 && k6 >= -32 && k6 <= 31) {
                           j6 += 32;
                           k6 += 32;
                           this.stream.writeWord((this.anInt1022 << 12) + (j6 << 6) + k6);
                           this.anInt1022 = 0;
                        } else if(this.anInt1022 < 8) {
                           this.stream.writeDWordBigEndian(8388608 + (this.anInt1022 << 19) + l5);
                           this.anInt1022 = 0;
                        } else {
                           this.stream.writeDWord(-1073741824 + (this.anInt1022 << 19) + l5);
                           this.anInt1022 = 0;
                        }
                     }
                  }

                  this.stream.writeBytes(this.stream.currentOffset - k1);
                  if(flag >= this.mouseDetection.coordsIndex) {
                     this.mouseDetection.coordsIndex = 0;
                  } else {
                     this.mouseDetection.coordsIndex -= flag;

                     for(i4 = 0; i4 < this.mouseDetection.coordsIndex; ++i4) {
                        this.mouseDetection.coordsX[i4] = this.mouseDetection.coordsX[i4 + flag];
                        this.mouseDetection.coordsY[i4] = this.mouseDetection.coordsY[i4 + flag];
                     }
                  }
               }
            } else {
               this.mouseDetection.coordsIndex = 0;
            }
         }

         if(super.clickMode3 != 0) {
            long var15 = (super.aLong29 - this.aLong1220) / 50L;
            if(var15 > 4095L) {
               var15 = 4095L;
            }

            this.aLong1220 = super.aLong29;
            flag = super.saveClickY;
            if(flag < 0) {
               flag = 0;
            } else if(flag > 502) {
               flag = 502;
            }

            i4 = super.saveClickX;
            if(i4 < 0) {
               i4 = 0;
            } else if(i4 > 764) {
               i4 = 764;
            }

            k4 = flag * 765 + i4;
            byte var19 = 0;
            if(super.clickMode3 == 2) {
               var19 = 1;
            }

            l5 = (int)var15;
            if(!editMode){
            	this.stream.createFrame(241);
            	this.stream.writeDWord((l5 << 20) + (var19 << 19) + k4);
            }
         }

         if(this.anInt1016 > 0) {
            --this.anInt1016;
         }

         if(super.keyArray[1] == 1 || super.keyArray[2] == 1 || super.keyArray[3] == 1 || super.keyArray[4] == 1) {
            this.aBoolean1017 = true;
         }

         if(this.aBoolean1017 && this.anInt1016 <= 0 && !editMode) {
            this.anInt1016 = 20;
            this.aBoolean1017 = false;
            this.stream.createFrame(86);
            this.stream.writeWord(this.anInt1184);
            this.stream.method432(this.minimapInt1);
         }

         if(super.awtFocus && !this.aBoolean954) {
            this.aBoolean954 = true;
            this.stream.createFrame(3);
            this.stream.writeWordBigEndian(1);
         }

         if(!super.awtFocus && this.aBoolean954 && !editMode) {
            this.aBoolean954 = false;
            this.stream.createFrame(3);
            this.stream.writeWordBigEndian(0);
         }
         this.loadingStages();
         this.method115();
         this.method90();
			if (!editMode) {
				++this.anInt1009;
				if (this.anInt1009 > 750) {
					this.dropClient();
				}
			}

         this.method114();
         this.method95();
         this.method38();
         ++this.anInt945;
         if(this.crossType != 0) {
            this.crossIndex += 20;
            if(this.crossIndex >= 400) {
               this.crossType = 0;
            }
         }

         if(this.atInventoryInterfaceType != 0) {
            ++this.atInventoryLoopCycle;
            if(this.atInventoryLoopCycle >= 15) {
               if(this.atInventoryInterfaceType == 2) {
                  this.needDrawTabArea = true;
               }

               if(this.atInventoryInterfaceType == 3) {
                  this.inputTaken = true;
               }

               this.atInventoryInterfaceType = 0;
            }
         }

         if(this.activeInterfaceType != 0) {
            ++this.anInt989;
            if(super.mouseX > this.anInt1087 + 5 || super.mouseX < this.anInt1087 - 5 || super.mouseY > this.anInt1088 + 5 || super.mouseY < this.anInt1088 - 5) {
               this.aBoolean1242 = true;
            }

            if(super.clickMode2 == 0) {
               if(this.activeInterfaceType == 2) {
                  this.needDrawTabArea = true;
               }

               if(this.activeInterfaceType == 3) {
                  this.inputTaken = true;
               }

               this.activeInterfaceType = 0;
               if(this.aBoolean1242 && this.anInt989 >= 5) {
                  this.lastActiveInvInterface = -1;
                  this.processRightClick();
                  if(this.lastActiveInvInterface == this.anInt1084 && this.mouseInvInterfaceIndex != this.anInt1085) {
                     RSInterface var17 = RSInterface.interfaceCache[this.anInt1084];
                     byte var16 = 0;
                     if(this.anInt913 == 1 && var17.anInt214 == 206) {
                        var16 = 1;
                     }

                     if(var17.inv[this.mouseInvInterfaceIndex] <= 0) {
                        var16 = 0;
                     }

                     if(var17.aBoolean235) {
                        flag = this.anInt1085;
                        i4 = this.mouseInvInterfaceIndex;
                        var17.inv[i4] = var17.inv[flag];
                        var17.invStackSizes[i4] = var17.invStackSizes[flag];
                        var17.inv[flag] = -1;
                        var17.invStackSizes[flag] = 0;
                     } else if(var16 == 1) {
                        flag = this.anInt1085;
                        i4 = this.mouseInvInterfaceIndex;

                        while(flag != i4) {
                           if(flag > i4) {
                              var17.swapInventoryItems(flag, flag - 1);
                              --flag;
                           } else if(flag < i4) {
                              var17.swapInventoryItems(flag, flag + 1);
                              ++flag;
                           }
                        }
                     } else {
                        var17.swapInventoryItems(this.anInt1085, this.mouseInvInterfaceIndex);
                     }

                     this.stream.createFrame(214);
                     this.stream.method433(this.anInt1084);
                     this.stream.method424(var16);
                     this.stream.method433(this.anInt1085);
                     this.stream.method431(this.mouseInvInterfaceIndex);
                  }
               } else if((this.anInt1253 == 1 || this.menuHasAddFriend(this.menuActionRow - 1)) && this.menuActionRow > 2) {
                  this.determineMenuSize();
               } else if(this.menuActionRow > 0) {
                  this.doAction(this.menuActionRow - 1);
               }

               this.atInventoryLoopCycle = 10;
               super.clickMode3 = 0;
            }
         }

         if(WorldController.anInt470 != -1) {
            exception = WorldController.anInt470;
            k1 = WorldController.anInt471;
            boolean var18 = this.doWalkTo(0, 0, 0, 0, myPlayer.smallY[0], 0, 0, k1, myPlayer.smallX[0], true, exception);
            WorldController.anInt470 = -1;
            if(var18) {
               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 1;
               this.crossIndex = 0;
            }
         }

         if(super.clickMode3 == 1 && this.aString844 != null) {
            this.aString844 = null;
            this.inputTaken = true;
            super.clickMode3 = 0;
         }

		 if(gameframeVersion == 474)
			processHovers();
		 
		 if(!this.processMenuClick()) {
			 if(!fullscreenInterfaceClosed){
				 this.processMainScreenClick();
				 this.processTabClick();
				 this.processChatModeClick();
			 }else{
				 fullscreenInterfaceClosed = false;
			 }
		}

         if(super.clickMode2 == 1 || super.clickMode3 == 1) {
            ++this.anInt1213;
         }
		 
		 if (anInt1500 != 0 || anInt1044 != 0 || anInt1129 != 0) {
			if(currentMenu != anInt1129){
				currentMenu = anInt1129;
				anInt1501 = 0;
			}
           if (anInt1501 < tooltipDelay && !menuOpen) {
                anInt1501++;
                if (anInt1501 == tooltipDelay) {
                    if (anInt1500 != 0) {
                        inputTaken = true;
                    }
                    if (anInt1044 != 0) {
                        needDrawTabArea = true;
                    }
                }
            }
        } else if (anInt1501 > 0) {
            anInt1501--;
        }

         if(this.loadingStage == 2) {
            this.method108();
         }

         if(this.loadingStage == 2 && this.aBoolean1160) {
            this.calcCameraPos();
         }

         for(exception = 0; exception < 5; ++exception) {
            ++this.anIntArray1030[exception];
         }

         this.method73();
			if (!editMode) {
				++super.idleTime;
				if (super.idleTime > 4500) {
					this.anInt1011 = 250;
					super.idleTime -= 500;
					this.stream.createFrame(202);
				}
			}

         ++this.anInt988;
         if(this.anInt988 > 500) {
            this.anInt988 = 0;
            exception = (int)(Math.random() * 8.0D);
            if((exception & 1) == 1) {
               this.anInt1278 += this.anInt1279;
            }

            if((exception & 2) == 2) {
               this.anInt1131 += this.anInt1132;
            }

            if((exception & 4) == 4) {
               this.anInt896 += this.anInt897;
            }
         }

         if(this.anInt1278 < -50) {
            this.anInt1279 = 2;
         }

         if(this.anInt1278 > 50) {
            this.anInt1279 = -2;
         }

         if(this.anInt1131 < -55) {
            this.anInt1132 = 2;
         }

         if(this.anInt1131 > 55) {
            this.anInt1132 = -2;
         }

         if(this.anInt896 < -40) {
            this.anInt897 = 1;
         }

         if(this.anInt896 > 40) {
            this.anInt897 = -1;
         }

         ++this.anInt1254;
         if(this.anInt1254 > 500) {
            this.anInt1254 = 0;
            exception = (int)(Math.random() * 8.0D);
            if((exception & 1) == 1) {
               this.minimapInt2 += this.anInt1210;
            }

            if((exception & 2) == 2) {
               this.minimapInt3 += this.anInt1171;
            }
         }

         if(this.minimapInt2 < -60) {
            this.anInt1210 = 2;
         }

         if(this.minimapInt2 > 60) {
            this.anInt1210 = -2;
         }

         if(this.minimapInt3 < -20) {
            this.anInt1171 = 1;
         }

         if(this.minimapInt3 > 10) {
            this.anInt1171 = -1;
         }

			if (!editMode) {
				++this.anInt1010;
				if (this.anInt1010 > 50) {
					this.stream.createFrame(0);
				}
				try {
					if (this.socketStream != null
							&& this.stream.currentOffset > 0) {
						this.socketStream.queueBytes(this.stream.currentOffset,
								this.stream.buffer);
						this.stream.currentOffset = 0;
						this.anInt1010 = 0;
					}
				} catch (IOException var11) {
					this.dropClient();
				} catch (Exception var12) {
					this.resetLogout();
				}
			}
         
      }
   }
   
   public int currentMenu = 0;
	public int tooltipDelay = 50;

   public void method63() {
      for(Class30_Sub1 class30_sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
         if(class30_sub1.anInt1294 == -1) {
            class30_sub1.anInt1302 = 0;
            this.method89(class30_sub1);
         } else {
            class30_sub1.unlink();
         }
      }

   }

   public void resetImageProducers() {
      if(this.aRSImageProducer_1107 == null) {
         super.fullGameScreen = null;
         this.aRSImageProducer_1166 = null;
         this.aRSImageProducer_1164 = null;
         this.aRSImageProducer_1163 = null;
         this.aRSImageProducer_1165 = null;
         this.aRSImageProducer_1123 = null;
         this.aRSImageProducer_1124 = null;
         this.aRSImageProducer_1125 = null;
		 this.aRSImageProducer_oma1 = null;
		 this.aRSImageProducer_oma2 = null;
		 this.aRSImageProducer_oma3 = null;
		 this.aRSImageProducer_oma4 = null;
		 this.aRSImageProducer_oma5 = null;
		 this.aRSImageProducer_oma6 = null;
		 this.aRSImageProducer_oma7 = null;
		 this.aRSImageProducer_oma8 = null;
         this.aRSImageProducer_1110 = new RSImageProducer(128, 265, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1111 = new RSImageProducer(128, 265, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1107 = new RSImageProducer(509, 171, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1108 = new RSImageProducer(360, 132, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1109 = new RSImageProducer(360, 200, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1112 = new RSImageProducer(202, 238, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1113 = new RSImageProducer(203, 238, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1114 = new RSImageProducer(74, 94, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1115 = new RSImageProducer(75, 94, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         if(this.titleStreamLoader != null) {
            this.drawLogo();
            this.loadTitleScreen();
         }

         this.welcomeScreenRaised = true;
      }
   }

   void drawLoadingText(int i, String s) {
      this.anInt1079 = i;
      this.aString1049 = s;
      this.resetImageProducers();
      if(this.titleStreamLoader == null) {
         super.drawLoadingText(i, s);
      } else {
         this.aRSImageProducer_1109.initDrawingArea();
         short c = 360;
         short c1 = 200;
         byte byte1 = 20;
         this.chatTextDrawingArea.drawText(16777215, NAME+" is loading - please wait...", c1 / 2 - 26 - byte1, c / 2);
         int j = c1 / 2 - 18 - byte1;
         DrawingArea.fillPixels(c / 2 - 152, 304, 34, 9179409, j);
         DrawingArea.fillPixels(c / 2 - 151, 302, 32, 0, j + 1);
         DrawingArea.method336(30, j + 2, c / 2 - 150, 9179409, i * 3);
         DrawingArea.method336(30, j + 2, c / 2 - 150 + i * 3, 0, 300 - i * 3);
         this.chatTextDrawingArea.drawText(16777215, s, c1 / 2 + 5 - byte1, c / 2);
         this.aRSImageProducer_1109.drawGraphics(171, super.graphics, 202);
         if(this.welcomeScreenRaised) {
            this.welcomeScreenRaised = false;
            if(!this.aBoolean831) {
               this.aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
               this.aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
            }

            this.aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
            this.aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
            this.aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
            this.aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
            this.aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
            this.aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
         }

      }
   }

   public void method65(int i, int j, int k, int l, RSInterface class9, int i1, boolean flag, int j1) {
      byte anInt992;
      if(this.aBoolean972) {
         anInt992 = 32;
      } else {
         anInt992 = 0;
      }

      this.aBoolean972 = false;
      if(k >= i && k < i + 16 && l >= i1 && l < i1 + 16) {
         class9.scrollPosition -= this.anInt1213 * 4;
         if(flag) {
            this.needDrawTabArea = true;
         }
      } else if(k >= i && k < i + 16 && l >= i1 + j - 16 && l < i1 + j) {
         class9.scrollPosition += this.anInt1213 * 4;
         if(flag) {
            this.needDrawTabArea = true;
         }
      } else if(k >= i - anInt992 && k < i + 16 + anInt992 && l >= i1 + 16 && l < i1 + j - 16 && this.anInt1213 > 0) {
         int l1 = (j - 32) * j / j1;
         if(l1 < 8) {
            l1 = 8;
         }

         int i2 = l - i1 - 16 - l1 / 2;
         int j2 = j - 32 - l1;
         class9.scrollPosition = (j1 - j) * i2 / j2;
         if(flag) {
            this.needDrawTabArea = true;
         }

         this.aBoolean972 = true;
      }

   }

   final void method58(int i_30_, int volume, boolean bool, int music) {
      if(musicIsntNull()) {
         this.nextSong = music;
         this.onDemandFetcher.method558(2, this.nextSong);
         musicVolume2 = volume;
         anInt139 = -1;
         aBoolean995 = true;
         anInt116 = i_30_;
      }
   }

   public boolean method66(int i, int j, int k) {
      int i1 = i >> 14 & 32767;
      int j1 = this.worldController.method304(this.plane, k, j, i);
      if(j1 == -1) {
         return false;
      } else {
         int k1 = j1 & 31;
         int l1 = j1 >> 6 & 3;
         if(k1 != 10 && k1 != 11 && k1 != 22) {
            this.doWalkTo(2, l1, 0, k1 + 1, myPlayer.smallY[0], 0, 0, j, myPlayer.smallX[0], false, k);
         } else {
            ObjectDef class46 = ObjectDef.forID(i1);
            int i2;
            int j2;
            if(l1 != 0 && l1 != 2) {
               i2 = class46.anInt761;
               j2 = class46.anInt744;
            } else {
               i2 = class46.anInt744;
               j2 = class46.anInt761;
            }

            int k2 = class46.anInt768;
            if(l1 != 0) {
               k2 = (k2 << l1 & 15) + (k2 >> 4 - l1);
            }

            this.doWalkTo(2, 0, j2, 0, myPlayer.smallY[0], i2, k2, j, myPlayer.smallX[0], false, k);
         }

         this.crossX = super.saveClickX;
         this.crossY = super.saveClickY;
         this.crossType = 2;
         this.crossIndex = 0;
         return true;
      }
   }

   static final void method509(Component component) {
      try {
         Class5_Sub2 throwable = (Class5_Sub2)Class.forName("client.Class5_Sub2_Sub2").newInstance();
         throwable.method502(2048);
         aClass5_932 = throwable;
      } catch (Throwable var6) {
         try {
            aClass5_932 = new Class5_Sub2_Sub1(component);
         } catch (Throwable var5) {
            if(System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") >= 0) {
               try {
                  aClass5_932 = new Class5_Sub1();
                  return;
               } catch (Throwable var4) {
                  ;
               }
            }

            aClass5_932 = new Class5(8000);
         }
      }

   }

   public StreamLoader streamLoaderForName(int i, String s, String s1, int j, int k) {
      byte[] abyte0 = null;
      int l = 5;

      try {
         if(this.decompressors[0] != null) {
            abyte0 = this.decompressors[0].decompress(i);
         }
      } catch (Exception var21) {
         ;
      }

      if(abyte0 != null) {
         ;
      }

      if(abyte0 != null) {
         StreamLoader var26 = new StreamLoader(abyte0);
         return var26;
      } else {
         byte j1 = 0;

         while(abyte0 == null) {
            String streamLoader_1 = "Unknown error";
            this.drawLoadingText(k, "Requesting " + s);

            int l1;
            try {
               l1 = 0;
               DataInputStream _ex = this.openJagGrabInputStream(s1 + j);
               byte[] abyte1 = new byte[6];
               _ex.readFully(abyte1, 0, 6);
               Stream stream = new Stream(abyte1);
               stream.currentOffset = 3;
               int i2 = stream.read3Bytes() + 6;
               int j2 = 6;
               abyte0 = new byte[i2];
               System.arraycopy(abyte1, 0, abyte0, 0, 6);

               int k3;
               for(; j2 < i2; l1 = k3) {
                  int _ex1 = i2 - j2;
                  if(_ex1 > 1000) {
                     _ex1 = 1000;
                  }

                  int j3 = _ex.read(abyte0, j2, _ex1);
                  if(j3 < 0) {
                      streamLoader_1 =  "Length error: " + j2 + "/" + i2;
                     throw new IOException("EOF");
                  }

                  j2 += j3;
                  k3 = j2 * 100 / i2;
                  if(k3 != l1) {
                     this.drawLoadingText(k, "Loading " + s + " - " + k3 + "%");
                  }
               }

               _ex.close();

               try {
                  if(this.decompressors[0] != null) {
                     this.decompressors[0].method234(abyte0.length, abyte0, i);
                  }
               } catch (Exception var20) {
                  this.decompressors[0] = null;
               }
            } catch (IOException var22) {
               if(streamLoader_1.equals("Unknown error")) {
                  streamLoader_1 = "Connection error";
               }

               abyte0 = null;
            } catch (NullPointerException var23) {
               streamLoader_1 = "Null error";
               abyte0 = null;
               if(!SignLink.reporterror) {
                  return null;
               }
            } catch (ArrayIndexOutOfBoundsException var24) {
               streamLoader_1 = "Bounds error";
               abyte0 = null;
               if(!SignLink.reporterror) {
                  return null;
               }
            } catch (Exception var25) {
               streamLoader_1 = "Unexpected error";
               abyte0 = null;
               if(!SignLink.reporterror) {
                  return null;
               }
            }

            if(abyte0 == null) {
               for(l1 = l; l1 > 0; --l1) {
                  if(j1 >= 3) {
                     this.drawLoadingText(k, "Game updated - please reload page");
                     l1 = 10;
                  } else {
                     this.drawLoadingText(k, streamLoader_1 + " - Retrying in " + l1);
                  }

                  try {
                     Thread.sleep(1000L);
                  } catch (Exception var19) {
                     ;
                  }
               }

               l *= 2;
               if(l > 60) {
                  l = 60;
               }

               this.aBoolean872 = !this.aBoolean872;
            }
         }

         StreamLoader var27 = new StreamLoader(abyte0);
         return var27;
      }
   }

   public void dropClient() {
      if(this.anInt1011 > 0) {
         this.resetLogout();
      } else {
    	  if(fullscreenInterfaceID >= 1) {//fixes nullpointer error, if disconnecting while viewing fullscreen interface.
  			fullscreenInterfaceID = -1;
  			return;
  		}
         this.aRSImageProducer_1165.initDrawingArea();
         this.aTextDrawingArea_1271.drawText(0, "Connection lost", 144, 257);
         this.aTextDrawingArea_1271.drawText(16777215, "Connection lost", 143, 256);
         this.aTextDrawingArea_1271.drawText(0, "Please wait - attempting to reestablish", 159, 257);
         this.aTextDrawingArea_1271.drawText(16777215, "Please wait - attempting to reestablish", 158, 256);
         this.aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
         this.anInt1021 = 0;
         this.destX = 0;
         RSSocket rsSocket = this.socketStream;
         this.loggedIn = false;
         this.loginFailures = 0;
         this.login(this.myUsername, this.myPassword, true);
         if(!this.loggedIn) {
            this.resetLogout();
         }

         try {
            rsSocket.close();
         } catch (Exception var3) {
            ;
         }

      }
   }

   static final void method484(Class3_Sub7 class3_sub7) {
      aClass3_Sub7_1345 = class3_sub7;
   }

   public void doAction(int i) {
      if(i >= 0) {
         if(this.inputDialogState != 0 && inputDialogState != 3) {
			if(openInterfaceID == 5292 && this.inputDialogState == 2){//bank search
			} else {
				this.inputDialogState = 0;
				this.inputTaken = true;
			}
         }

         int j = this.menuActionCmd2[i];
         int k = this.menuActionCmd3[i];
         int l = this.menuActionID[i];
         int i1 = this.menuActionCmd1[i];
         if(l >= 2000) {
            l -= 2000;
         }

         NPC itemDef_1;
         if(l == 582) {
            itemDef_1 = this.npcArray[i1];
            if(itemDef_1 != null) {
               this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               this.stream.createFrame(57);
               this.stream.method432(this.anInt1285);
               this.stream.method432(i1);
               this.stream.method431(this.anInt1283);
               this.stream.method432(this.anInt1284);
            }
         }

         boolean var12;
         if(l == 234) {
            var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
            if(!var12) {
               this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
            }

            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            if(!editMode){
            	this.stream.createFrame(236);
            	this.stream.method431(k + this.baseY);
            	this.stream.writeWord(i1);
            	this.stream.method431(j + this.baseX);
            } else {
            	System.out.println("item!");
                NodeList var18 = this.groundArray[this.plane][j][k];
                if(var18 != null) {
                	for(Item var17 = (Item)var18.getFirst(); var17 != null; var17 = (Item)var18.getNext()) {
                        //ItemDef var15 = ItemDef.forID(var17.ID);
                		Jframe.itemId.setText(""+var17.ID);
                		Jframe.itemAmount.setText(""+var17.anInt1559);
                		Jframe.itemPosition.setText((j + this.baseX)+","+(k + this.baseY)+","+this.plane);
                		Jframe.isP2PItemButton.setSelected(var17.itemSpawn.isMembersOnly());
                		selectedItem = var17;
                	}
                }
            }
         }

         if(l == 62 && this.method66(i1, k, j)) {
            this.stream.createFrame(192);
            this.stream.writeWord(this.anInt1284);
            this.stream.method431(i1 >> 14 & 32767);
            this.stream.method433(k + this.baseY);
            this.stream.method431(this.anInt1283);
            this.stream.method433(j + this.baseX);
            this.stream.writeWord(this.anInt1285);
         }

         if(l == 511) {
            var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
            if(!var12) {
               this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
            }

            this.crossX = super.saveClickX;
            this.crossY = super.saveClickY;
            this.crossType = 2;
            this.crossIndex = 0;
            this.stream.createFrame(25);
            this.stream.method431(this.anInt1284);
            this.stream.method432(this.anInt1285);
            this.stream.writeWord(i1);
            this.stream.method432(k + this.baseY);
            this.stream.method433(this.anInt1283);
            this.stream.writeWord(j + this.baseX);
         }

         if(l == 74) {
            this.stream.createFrame(122);
            this.stream.method433(k);
            this.stream.method432(j);
            this.stream.method431(i1);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = k;
            this.atInventoryIndex = j;
            this.atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
               this.atInventoryInterfaceType = 1;
            }

            if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
               this.atInventoryInterfaceType = 3;
            }
         }

         RSInterface var14;
         if(l == 315) {
            var14 = RSInterface.interfaceCache[k];
            boolean s6 = true;
            if(var14.anInt214 > 0) {
               s6 = this.promptUserForInput(var14);
            }

            if(s6) {
               this.stream.createFrame(185);
               this.stream.writeWord(k);
            }
         }

         Player var13;
         if(l == 561) {
            var13 = this.playerArray[i1];
            if(var13 != null) {
               this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               anInt1188 += i1;
               if(anInt1188 >= 90) {
                  this.stream.createFrame(136);
                  anInt1188 = 0;
               }

               this.stream.createFrame(128);
               this.stream.writeWord(i1);
            }
         }

         if(l == 20) {
            itemDef_1 = this.npcArray[i1];
            if(itemDef_1 != null) {
               this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               if(!editMode){
            	   	this.stream.createFrame(155);
               		this.stream.method431(i1);
               } else {
            	   System.out.println("npc!");
            	   Jframe.npcId.setText(""+itemDef_1.desc.type);
           			Jframe.npcPosition.setText((itemDef_1.smallX[0] + this.baseX)+","+(itemDef_1.smallY[0] + this.baseY)+","+this.plane);
           			Jframe.isP2PNpcButton.setSelected(itemDef_1.npcSpawn.isMembersOnly());
           			Jframe.setFacing(itemDef_1.npcSpawn.getWalkType());
            	   selectedNpc = itemDef_1;
               }
            }
         }

         if(l == 779) {
            var13 = this.playerArray[i1];
            if(var13 != null) {
               this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               this.stream.createFrame(153);
               this.stream.method431(i1);
            }
         }

         if(l == 516) {
            if(!this.menuOpen) {
               this.worldController.method312(super.saveClickY - 4, super.saveClickX - 4);
            } else {
               this.worldController.method312(k - 4, j - 4);
            }
         }

         if(l == 1062) {
            anInt924 += this.baseX;
            if(anInt924 >= 113) {
               this.stream.createFrame(183);
               this.stream.writeDWordBigEndian(15086193);
               anInt924 = 0;
            }

            this.method66(i1, k, j);
            this.stream.createFrame(228);
            this.stream.method432(i1 >> 14 & 32767);
            this.stream.method432(k + this.baseY);
            this.stream.writeWord(j + this.baseX);
         }

         if(l == 679 && !this.aBoolean1149) {
            this.stream.createFrame(40);
            this.stream.writeWord(k);
            this.aBoolean1149 = true;
         }

         if(l == 431) {
            this.stream.createFrame(129);
			int index = j;
			if(k == 5382){//bank search
				if(indexedBankItems.size() != 0){
					index = indexedBankItems.get(j);
				}
			}
            this.stream.method432(index);
            this.stream.writeWord(k);
            this.stream.method432(i1);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = k;
            this.atInventoryIndex = j;
            this.atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
               this.atInventoryInterfaceType = 1;
            }

            if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
               this.atInventoryInterfaceType = 3;
            }
         }

         long s10;
         int var15;
         String var17;
         if(l == 337 || l == 42 || l == 792 || l == 322) {
            var17 = this.menuActionName[i];
            var15 = var17.indexOf("@whi@");
            if(var15 != -1) {
               s10 = TextClass.longForName(var17.substring(var15 + 5).trim());
               if(l == 337) {
                  this.addFriend(s10);
               }

               if(l == 42) {
                  this.addIgnore(s10);
               }

               if(l == 792) {
                  this.delFriend(s10);
               }

               if(l == 322) {
                  this.delIgnore(s10);
               }
            }
         }

         if(l == 53) {
            this.stream.createFrame(135);
			int index = j;
			if(k == 5382){//bank search
				if(indexedBankItems.size() != 0){
					index = indexedBankItems.get(j);
				}
			}
            this.stream.method431(index);
            this.stream.method432(k);
            this.stream.method431(i1);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = k;
            this.atInventoryIndex = j;
            this.atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
               this.atInventoryInterfaceType = 1;
            }

            if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
               this.atInventoryInterfaceType = 3;
            }
         }
		switch(k) {//k = Interface Button ID
			case 18786://bank search
				searchingBank = "";
				previousBankScrollPosition = RSInterface.interfaceCache[5385].scrollPosition;
				this.messagePromptRaised = false;
				this.inputDialogState = 2;
				this.amountOrNameInput = "";
				this.inputTaken = true;
				updateBank();
			break;
			
			case 18934://ge search
			case 18937:
				amountOrNameInput = "";
     		   	totalItemResults = 0;
     		   	this.inputDialogState = 3;
			break;
			
		}
         if(l == 539) {
            this.stream.createFrame(16);
            this.stream.method432(i1);
            this.stream.method433(j);
            this.stream.method433(k);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = k;
            this.atInventoryIndex = j;
            this.atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
               this.atInventoryInterfaceType = 1;
            }

            if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
               this.atInventoryInterfaceType = 3;
            }
         }

         int k3;
         String var16;
         if(l == 484 || l == 6) {
            var17 = this.menuActionName[i];
            var15 = var17.indexOf("@whi@");
            if(var15 != -1) {
               var17 = var17.substring(var15 + 5).trim();
               var16 = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(var17)));
               boolean len$ = false;

               for(k3 = 0; k3 < this.playerCount; ++k3) {
                  Player i4 = this.playerArray[this.playerIndices[k3]];
                  if(i4 != null && i4.name != null && i4.name.equalsIgnoreCase(var16)) {
                     this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, i4.smallY[0], myPlayer.smallX[0], false, i4.smallX[0]);
                     if(l == 484) {
                        this.stream.createFrame(139);
                        this.stream.method431(this.playerIndices[k3]);
                     }

                     if(l == 6) {
                        anInt1188 += i1;
                        if(anInt1188 >= 90) {
                           this.stream.createFrame(136);
                           anInt1188 = 0;
                        }

                        this.stream.createFrame(128);
                        this.stream.writeWord(this.playerIndices[k3]);
                     }

                     len$ = true;
                     break;
                  }
               }

               if(!len$) {
                  this.pushMessage("Unable to find " + var16, 0, "");
               }
            }
         }

         if(l == 870) {
            this.stream.createFrame(53);
            this.stream.writeWord(j);
            this.stream.method432(this.anInt1283);
            this.stream.method433(i1);
            this.stream.writeWord(this.anInt1284);
            this.stream.method431(this.anInt1285);
            this.stream.writeWord(k);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = k;
            this.atInventoryIndex = j;
            this.atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
               this.atInventoryInterfaceType = 1;
            }

            if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
               this.atInventoryInterfaceType = 3;
            }
         }

         if(l == 847) {
            this.stream.createFrame(87);
            this.stream.method432(i1);
            this.stream.writeWord(k);
            this.stream.method432(j);
            this.atInventoryLoopCycle = 0;
            this.atInventoryInterface = k;
            this.atInventoryIndex = j;
            this.atInventoryInterfaceType = 2;
            if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
               this.atInventoryInterfaceType = 1;
            }

            if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
               this.atInventoryInterfaceType = 3;
            }
         }

         String var25;
         if(l == 626) {
            var14 = RSInterface.interfaceCache[k];
            this.spellSelected = 1;
			spellID = var14.id;
            this.anInt1137 = k;
            this.spellUsableOn = var14.spellUsableOn;
            this.itemSelected = 0;
            this.needDrawTabArea = true;
            var25 = var14.selectedActionName;
            if(var25.indexOf(" ") != -1) {
               var25 = var25.substring(0, var25.indexOf(" "));
            }

            var16 = var14.selectedActionName;
            if(var16.indexOf(" ") != -1) {
               var16 = var16.substring(var16.indexOf(" ") + 1);
            }

            this.spellTooltip = var25 + " " + var14.spellName + " " + var16;
            if(this.spellUsableOn == 16) {
               this.needDrawTabArea = true;
               this.tabID = 3;
               this.tabAreaAltered = true;
            }

         } else {
            if(l == 78) {
               this.stream.createFrame(117);
			   int index = j;
			   if(k == 5382){//bank search
					if(indexedBankItems.size() != 0){
						index = indexedBankItems.get(j);
					}
			   }
               this.stream.method433(k);
               this.stream.method433(i1);
               this.stream.method431(index);
               this.atInventoryLoopCycle = 0;
               this.atInventoryInterface = k;
               this.atInventoryIndex = j;
               this.atInventoryInterfaceType = 2;
               if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
                  this.atInventoryInterfaceType = 1;
               }

               if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
                  this.atInventoryInterfaceType = 3;
               }
            }

            if(l == 27) {
               var13 = this.playerArray[i1];
               if(var13 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  anInt986 += i1;
                  if(anInt986 >= 54) {
                     this.stream.createFrame(189);
                     this.stream.writeWordBigEndian(234);
                     anInt986 = 0;
                  }

                  this.stream.createFrame(73);
                  this.stream.method431(i1);
               }
            }

            if(l == 213) {
               var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
               if(!var12) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
               }

               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               this.stream.createFrame(79);
               this.stream.method431(k + this.baseY);
               this.stream.writeWord(i1);
               this.stream.method432(j + this.baseX);
            }

			/*if(l == 1000) {
				cButtonCPos = 4;
				chatTypeView = 11;
				inputTaken = true;
			}*/
			if(l == 999) {
				cButtonCPos = 0;
				chatTypeView = 0;
				inputTaken = true;
			}
			if(l == 998) {
				cButtonCPos = 1;
				chatTypeView = 5;
				inputTaken = true;
			}
			if(l == 997) {
				publicChatMode = 3;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 996) {
				publicChatMode = 2;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 995) {
				publicChatMode = 1;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 994) {
				publicChatMode = 0;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 993) {
				cButtonCPos = 2;
				chatTypeView = 1;
				inputTaken = true;
			}
			if(l == 992) {
				privateChatMode = 2;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 991) {
				privateChatMode = 1;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 990) {
				privateChatMode = 0;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 989) {
				cButtonCPos = 3;
				chatTypeView = 2;
				inputTaken = true;
			}
			if(l == 987) {
				tradeMode = 2;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 986) {
				tradeMode = 1;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 985) {
				tradeMode = 0;
				inputTaken = true;
				this.stream.createFrame(95);
	            this.stream.writeWordBigEndian(this.publicChatMode);
	            this.stream.writeWordBigEndian(this.privateChatMode);
	            this.stream.writeWordBigEndian(this.tradeMode);
			}
			if(l == 984) {
				cButtonCPos = 5;
				chatTypeView = 3;
				inputTaken = true;
			}
			/*if(l == 980) {
				cButtonCPos = 6;
				chatTypeView = 4;
				inputTaken = true;
			}*/
			
            if(l == 632) {
               this.stream.createFrame(145);
			   int index = j;
			   if(k == 5382){//bank search
					if(indexedBankItems.size() != 0){
						index = indexedBankItems.get(j);
					}
			   }
               this.stream.method432(k);
               this.stream.method432(index);
               this.stream.method432(i1);
               this.atInventoryLoopCycle = 0;
               this.atInventoryInterface = k;
               this.atInventoryIndex = j;
               this.atInventoryInterfaceType = 2;
               if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
                  this.atInventoryInterfaceType = 1;
               }

               if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
                  this.atInventoryInterfaceType = 3;
               }
            }

            if(l == 493) {
               this.stream.createFrame(75);
               this.stream.method433(k);
               this.stream.method431(j);
               this.stream.method432(i1);
               this.atInventoryLoopCycle = 0;
               this.atInventoryInterface = k;
               this.atInventoryIndex = j;
               this.atInventoryInterfaceType = 2;
               if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
                  this.atInventoryInterfaceType = 1;
               }

               if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
                  this.atInventoryInterfaceType = 3;
               }
            }

            if(l == 652) {
               var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
               if(!var12) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
               }

               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               this.stream.createFrame(156);
               this.stream.method432(j + this.baseX);
               this.stream.method431(k + this.baseY);
               this.stream.method433(i1);
            }

            if(l == 94) {
               var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
               if(!var12) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
               }

               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               this.stream.createFrame(181);
               this.stream.method431(k + this.baseY);
               this.stream.writeWord(i1);
               this.stream.method431(j + this.baseX);
               this.stream.method432(this.anInt1137);
            }

            if(l == 646) {
               this.stream.createFrame(185);
               this.stream.writeWord(k);
               var14 = RSInterface.interfaceCache[k];
               if(var14.valueIndexArray != null && var14.valueIndexArray[0][0] == 5) {
                  var15 = var14.valueIndexArray[0][1];
                  if(this.variousSettings[var15] != var14.anIntArray212[0]) {
                     this.variousSettings[var15] = var14.anIntArray212[0];
                     this.method33(var15);
                     this.needDrawTabArea = true;
                  }
               }
            }

            if(l == 225) {
               itemDef_1 = this.npcArray[i1];
               if(itemDef_1 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  anInt1226 += i1;
                  if(anInt1226 >= 85) {
                     this.stream.createFrame(230);
                     this.stream.writeWordBigEndian(239);
                     anInt1226 = 0;
                  }

                  this.stream.createFrame(17);
                  this.stream.method433(i1);
               }
            }

            if(l == 965) {
               itemDef_1 = this.npcArray[i1];
               if(itemDef_1 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  ++anInt1134;
                  if(anInt1134 >= 96) {
                     this.stream.createFrame(152);
                     this.stream.writeWordBigEndian(88);
                     anInt1134 = 0;
                  }

                  this.stream.createFrame(21);
                  this.stream.writeWord(i1);
               }
            }

            if(l == 413) {
               itemDef_1 = this.npcArray[i1];
               if(itemDef_1 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  this.stream.createFrame(131);
                  this.stream.method433(i1);
                  this.stream.method432(this.anInt1137);
               }
            }

            if(l == 200) {
               this.clearTopInterfaces();
            }

            if(l == 1025) {
               itemDef_1 = this.npcArray[i1];
               if(itemDef_1 != null) {
                  EntityDef var18 = itemDef_1.desc;
                  if(var18.childrenIDs != null) {
                     var18 = var18.method161();
                  }

                  if(var18 != null) {
                     if(var18.description != null) {
                        var16 = new String(var18.description);
                     } else {
                        var16 = "It\'s a " + var18.name + ".";
                     }

                     this.pushMessage(var16, 0, "");
                  }
               }
            }

            if(l == 900) {
               this.method66(i1, k, j);
               this.stream.createFrame(252);
               this.stream.method433(i1 >> 14 & 32767);
               this.stream.method431(k + this.baseY);
               this.stream.method432(j + this.baseX);
            }

            if(l == 412) {
               itemDef_1 = this.npcArray[i1];
               if(itemDef_1 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  if(!editMode){
                	  this.stream.createFrame(72);
                	  this.stream.method432(i1);
                  }else{
                	  System.out.println("npcA!");
                	  Jframe.npcId.setText(""+itemDef_1.desc.type);
             			Jframe.npcPosition.setText((itemDef_1.smallX[0] + this.baseX)+","+(itemDef_1.smallY[0] + this.baseY)+","+this.plane);
             			Jframe.isP2PNpcButton.setSelected(itemDef_1.npcSpawn.isMembersOnly());
             			Jframe.setFacing(itemDef_1.npcSpawn.getWalkType());
                	  selectedNpc = itemDef_1;
                  }
               }
            }

            if(l == 365) {
               var13 = this.playerArray[i1];
               if(var13 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  this.stream.createFrame(249);
                  this.stream.method432(i1);
                  this.stream.method431(this.anInt1137);
               }
            }

            if(l == 729) {
               var13 = this.playerArray[i1];
               if(var13 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  this.stream.createFrame(39);
                  this.stream.method431(i1);
               }
            }

            if(l == 577) {
               var13 = this.playerArray[i1];
               if(var13 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  this.stream.createFrame(139);
                  this.stream.method431(i1);
               }
            }

            if(l == 956 && this.method66(i1, k, j)) {
               this.stream.createFrame(35);
               this.stream.method431(j + this.baseX);
               this.stream.method432(this.anInt1137);
               this.stream.method432(k + this.baseY);
               this.stream.method431(i1 >> 14 & 32767);
            }

            if(l == 567) {
               var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
               if(!var12) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
               }

               this.crossX = super.saveClickX;
               this.crossY = super.saveClickY;
               this.crossType = 2;
               this.crossIndex = 0;
               this.stream.createFrame(23);
               this.stream.method431(k + this.baseY);
               this.stream.method431(i1);
               this.stream.method431(j + this.baseX);
            }

            if(l == 867) {
               if((i1 & 3) == 0) {
                  ++anInt1175;
               }

               if(anInt1175 >= 59) {
                  this.stream.createFrame(200);
                  this.stream.writeWord(25501);
                  anInt1175 = 0;
               }

               this.stream.createFrame(43);
			   int index = j;
			   if(k == 5382){//bank search
					if(indexedBankItems.size() != 0){
						index = indexedBankItems.get(j);
					}
			   }
               this.stream.method431(k);
               this.stream.method432(i1);
               this.stream.method432(index);
               this.atInventoryLoopCycle = 0;
               this.atInventoryInterface = k;
               this.atInventoryIndex = j;
               this.atInventoryInterfaceType = 2;
               if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
                  this.atInventoryInterfaceType = 1;
               }

               if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
                  this.atInventoryInterfaceType = 3;
               }
            }

            if(l == 543) {
               this.stream.createFrame(237);
               this.stream.writeWord(j);
               this.stream.method432(i1);
               this.stream.writeWord(k);
               this.stream.method432(this.anInt1137);
               this.atInventoryLoopCycle = 0;
               this.atInventoryInterface = k;
               this.atInventoryIndex = j;
               this.atInventoryInterfaceType = 2;
               if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
                  this.atInventoryInterfaceType = 1;
               }

               if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
                  this.atInventoryInterfaceType = 3;
               }
            }
            
            if(l == 1250) {//ge
            	inputDialogState = 0;
				inputTaken = true;
				this.stream.createFrame(19);
				this.stream.writeWord(lastGESearchItem);
             }
            
            if(l == 1251) {//ge spawn for mods
            	this.stream.createFrame(103);
                String s = "item "+lastGESearchItem;
                this.stream.writeWordBigEndian(s.length()+1);
                this.stream.writeString(s);
                inputDialogState = 0;
				inputTaken = true;
             }
            
            if(l == 606) {
               var17 = this.menuActionName[i];
               var15 = var17.indexOf("@whi@");
               if(var15 != -1) {
                  if(this.openInterfaceID == -1 && this.fullscreenInterfaceID == -1) {
                     this.clearTopInterfaces();
                     this.reportAbuseInput = var17.substring(var15 + 5).trim();
                     this.canMute = false;
                     RSInterface[] var20 = RSInterface.interfaceCache;
                     int var19 = var20.length;

                     for(k3 = 0; k3 < var19; ++k3) {
                        RSInterface var21 = var20[k3];
                        if(var21 != null && var21.anInt214 == 600) {
                           this.reportAbuseInterfaceID = this.openInterfaceID = var21.parentID;
                           break;
                        }
                     }
                  } else {
                     this.pushMessage("Please close the interface you have open before using \'report abuse\'", 0, "");
                  }
               }
            }

            if(l == 491) {
               var13 = this.playerArray[i1];
               if(var13 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, var13.smallY[0], myPlayer.smallX[0], false, var13.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  this.stream.createFrame(14);
                  this.stream.writeWord(i1);
                  this.stream.method431(this.anInt1283);
               }
            }

            if(l == 639) {
               var17 = this.menuActionName[i];
               var15 = var17.indexOf("@whi@");
               if(var15 != -1) {
                  s10 = TextClass.longForName(var17.substring(var15 + 5).trim());
                  k3 = -1;

                  for(int var24 = 0; var24 < this.friendsCount; ++var24) {
                     if(this.friendsListAsLongs[var24] == s10) {
                        k3 = var24;
                        break;
                     }
                  }

                  if(k3 != -1 && this.friendsNodeIDs[k3] > 9) {
                     this.inputTaken = true;
                     this.inputDialogState = 0;
                     this.messagePromptRaised = true;
                     this.promptInput = "";
                     this.friendsListAction = 3;
                     this.aLong953 = this.friendsListAsLongs[k3];
                     this.aString1121 = "Enter message to send to " + this.friendsList[k3];
                  } else {
                     this.pushMessage("That player is currently offline.", 0, "");
                  }
               }
            }

            if(l == 454) {
               this.stream.createFrame(41);
               this.stream.writeWord(i1);
               this.stream.method432(j);
               this.stream.method432(k);
               this.atInventoryLoopCycle = 0;
               this.atInventoryInterface = k;
               this.atInventoryIndex = j;
               this.atInventoryInterfaceType = 2;
               if(RSInterface.interfaceCache[k].parentID == this.openInterfaceID) {
                  this.atInventoryInterfaceType = 1;
               }

               if(RSInterface.interfaceCache[k].parentID == this.backDialogID) {
                  this.atInventoryInterfaceType = 3;
               }
            }

            if(l == 478) {
               itemDef_1 = this.npcArray[i1];
               if(itemDef_1 != null) {
                  this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, itemDef_1.smallY[0], myPlayer.smallX[0], false, itemDef_1.smallX[0]);
                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  if((i1 & 3) == 0) {
                     ++anInt1155;
                  }

                  if(anInt1155 >= 53) {
                     this.stream.createFrame(85);
                     this.stream.writeWordBigEndian(66);
                     anInt1155 = 0;
                  }

                  this.stream.createFrame(18);
                  this.stream.method431(i1);
               }
            }

            if(l == 113) {
               this.method66(i1, k, j);
               this.stream.createFrame(70);
               this.stream.method431(j + this.baseX);
               this.stream.writeWord(k + this.baseY);
               this.stream.method433(i1 >> 14 & 32767);
            }

            if(l == 872) {
               this.method66(i1, k, j);
               this.stream.createFrame(234);
               this.stream.method433(j + this.baseX);
               this.stream.method432(i1 >> 14 & 32767);
               this.stream.method433(k + this.baseY);
            }

            if(l == 502 && !editMode) {
               this.method66(i1, k, j);
               this.stream.createFrame(132);
               this.stream.method433(j + this.baseX);
               this.stream.writeWord(i1 >> 14 & 32767);
               this.stream.method432(k + this.baseY);
            }

            ItemDef var26;
            if(l == 1125) {
				atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
               var26 = ItemDef.forID(i1);
               RSInterface var23 = RSInterface.interfaceCache[k];
               if(var23 != null && var23.invStackSizes[j] >= 100000) {
                  var16 = var23.invStackSizes[j] + " x " + var26.name;
               } else if(var26.description != null) {
                  var16 = new String(var26.description);
               } else {
                  var16 = "It\'s a " + var26.name + ".";
               }

               this.pushMessage(var16, 0, "");
            }

            if(l == 169) {
               this.stream.createFrame(185);
               this.stream.writeWord(k);
               var14 = RSInterface.interfaceCache[k];
               if(var14.valueIndexArray != null && var14.valueIndexArray[0][0] == 5) {
                  var15 = var14.valueIndexArray[0][1];
                  this.variousSettings[var15] = 1 - this.variousSettings[var15];
                  this.method33(var15);
                  this.needDrawTabArea = true;
               }
            }

            if(l == 447) {
               this.itemSelected = 1;
               this.anInt1283 = j;
               this.anInt1284 = k;
               this.anInt1285 = i1;
               this.selectedItemName = ItemDef.forID(i1).name;
               this.spellSelected = 0;
               this.needDrawTabArea = true;
            } else {
               if(l == 1226) {
                  int var27 = i1 >> 14 & 32767;
                  ObjectDef var22 = ObjectDef.forID(var27);
                  if(var22.description != null) {
                     var16 = new String(var22.description);
                  } else {
                     var16 = "It\'s a " + var22.name + ".";
                  }

                  this.pushMessage(var16, 0, "");
               }

               if(l == 244) {
                  var12 = this.doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
                  if(!var12) {
                     this.doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
                  }

                  this.crossX = super.saveClickX;
                  this.crossY = super.saveClickY;
                  this.crossType = 2;
                  this.crossIndex = 0;
                  this.stream.createFrame(253);
                  this.stream.method431(j + this.baseX);
                  this.stream.method433(k + this.baseY);
                  this.stream.method432(i1);
               }

               if(l == 1448) {
                  var26 = ItemDef.forID(i1);
                  if(var26.description != null) {
                     var25 = new String(var26.description);
                  } else {
                     var25 = "It\'s a " + var26.name + ".";
                  }

                  this.pushMessage(var25, 0, "");
               }

               this.itemSelected = 0;
               this.spellSelected = 0;
               this.needDrawTabArea = true;
            }
         }
      }
   }

   public void method70() {
      this.anInt1251 = 0;
      int j = (myPlayer.x >> 7) + this.baseX;
      int k = (myPlayer.y >> 7) + this.baseY;
      if(j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136) {
         this.anInt1251 = 1;
      }

      if(j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535) {
         this.anInt1251 = 1;
      }

      if(this.anInt1251 == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062) {
         this.anInt1251 = 0;
      }

   }

   public void run() {
      if(this.drawFlames) {
         this.drawFlames();
      } else {
         super.run();
      }

   }

   static final Class3_Sub7_Sub1 method407(Component component) {
      method509(component);
      Class3_Sub7_Sub1 class3_sub7_sub1 = new Class3_Sub7_Sub1();
      method484(class3_sub7_sub1);
      return class3_sub7_sub1;
   }

   public void build3dScreenMenu() {
      if(this.itemSelected == 0 && this.spellSelected == 0) {
         this.menuActionName[this.menuActionRow] = "Walk here";
         this.menuActionID[this.menuActionRow] = 516;
         this.menuActionCmd2[this.menuActionRow] = super.mouseX;
         this.menuActionCmd3[this.menuActionRow] = super.mouseY;
         ++this.menuActionRow;
      }

      int j = -1;

      for(int k = 0; k < Model.anInt1687; ++k) {
         int l = Model.anIntArray1688[k];
         int i1 = l & 127;
         int j1 = l >> 7 & 127;
         int k1 = l >> 29 & 3;
         int l1 = l >> 14 & 32767;
         if(l != j) {
            j = l;
            int item;
            if(k1 == 2 && this.worldController.method304(this.plane, i1, j1, l) >= 0) {
               ObjectDef class19 = ObjectDef.forID(l1);
               if(class19.childrenIDs != null) {
                  class19 = class19.method580();
               }

               if(class19 == null) {
                  continue;
               }

               if(this.itemSelected == 1) {
                  this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @cya@" + class19.name;
                  this.menuActionID[this.menuActionRow] = 62;
                  this.menuActionCmd1[this.menuActionRow] = l;
                  this.menuActionCmd2[this.menuActionRow] = i1;
                  this.menuActionCmd3[this.menuActionRow] = j1;
                  ++this.menuActionRow;
               } else if(this.spellSelected == 1) {
                  if((this.spellUsableOn & 4) == 4) {
                     this.menuActionName[this.menuActionRow] = this.spellTooltip + " @cya@" + class19.name;
                     this.menuActionID[this.menuActionRow] = 956;
                     this.menuActionCmd1[this.menuActionRow] = l;
                     this.menuActionCmd2[this.menuActionRow] = i1;
                     this.menuActionCmd3[this.menuActionRow] = j1;
                     ++this.menuActionRow;
                  }
               } else {
                  if(class19.actions != null) {
                     for(item = 4; item >= 0; --item) {
                        if(class19.actions[item] != null) {
                           this.menuActionName[this.menuActionRow] = class19.actions[item] + " @cya@" + class19.name;
                           if(item == 0) {
                              this.menuActionID[this.menuActionRow] = 502;
                           }

                           if(item == 1) {
                              this.menuActionID[this.menuActionRow] = 900;
                           }

                           if(item == 2) {
                              this.menuActionID[this.menuActionRow] = 113;
                           }

                           if(item == 3) {
                              this.menuActionID[this.menuActionRow] = 872;
                           }

                           if(item == 4) {
                              this.menuActionID[this.menuActionRow] = 1062;
                           }

                           this.menuActionCmd1[this.menuActionRow] = l;
                           this.menuActionCmd2[this.menuActionRow] = i1;
                           this.menuActionCmd3[this.menuActionRow] = j1;
                           ++this.menuActionRow;
                        }
                     }
                  }

                  if(myPrivilege >= 2 && developMode){
						this.menuActionName[this.menuActionRow] = "Examine @cya@" + class19.name + " @gre@(@whi@" + l1 + "@gre@) (@whi@" + (i1 + this.baseX) + "," + (j1 + this.baseY) + "@gre@)";
					}else{
						this.menuActionName[this.menuActionRow] = "Examine @cya@" + class19.name;
					}
                  this.menuActionID[this.menuActionRow] = 1226;
                  this.menuActionCmd1[this.menuActionRow] = class19.type << 14;
                  this.menuActionCmd2[this.menuActionRow] = i1;
                  this.menuActionCmd3[this.menuActionRow] = j1;
                  ++this.menuActionRow;
               }
            }

            NPC npc;
            Player var16;
            if(k1 == 1) {
               NPC var13 = this.npcArray[l1];
               if(var13.desc.aByte68 == 1 && (var13.x & 127) == 64 && (var13.y & 127) == 64) {
                  for(item = 0; item < this.npcCount; ++item) {
                     npc = this.npcArray[this.npcIndices[item]];
                     if(npc.desc == null)
                    	 continue;
                     if(npc != null && npc != var13 && npc.desc.aByte68 == 1 && npc.x == var13.x && npc.y == var13.y) {
                        this.buildAtNPCMenu(npc.desc, this.npcIndices[item], j1, i1);
                     }
                  }

                  for(item = 0; item < this.playerCount; ++item) {
                     var16 = this.playerArray[this.playerIndices[item]];
                     if(var16 != null && var16.x == var13.x && var16.y == var13.y) {
                        this.buildAtPlayerMenu(i1, this.playerIndices[item], var16, j1);
                     }
                  }
               }
               //hover npc
               //var13.desc.test();
               this.buildAtNPCMenu(var13.desc, l1, j1, i1);
            }

            if(k1 == 0) {
               Player var12 = this.playerArray[l1];
               if((var12.x & 127) == 64 && (var12.y & 127) == 64) {
                  for(item = 0; item < this.npcCount; ++item) {
                     npc = this.npcArray[this.npcIndices[item]];
                     if(npc != null && npc.desc.aByte68 == 1 && npc.x == var12.x && npc.y == var12.y) {
                        this.buildAtNPCMenu(npc.desc, this.npcIndices[item], j1, i1);
                     }
                  }

                  for(item = 0; item < this.playerCount; ++item) {
                     var16 = this.playerArray[this.playerIndices[item]];
                     if(var16 != null && var16 != var12 && var16.x == var12.x && var16.y == var12.y) {
                        this.buildAtPlayerMenu(i1, this.playerIndices[item], var16, j1);
                     }
                  }
               }

               this.buildAtPlayerMenu(i1, l1, var12, j1);
            }

            if(k1 == 3) {
               NodeList var14 = this.groundArray[this.plane][i1][j1];
               if(var14 != null) {
                  for(Item var17 = (Item)var14.getFirst(); var17 != null; var17 = (Item)var14.getNext()) {
                     ItemDef var15 = ItemDef.forID(var17.ID);
                     if(this.itemSelected == 1) {
                        this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @lre@" + var15.name;
                        this.menuActionID[this.menuActionRow] = 511;
                        this.menuActionCmd1[this.menuActionRow] = var17.ID;
                        this.menuActionCmd2[this.menuActionRow] = i1;
                        this.menuActionCmd3[this.menuActionRow] = j1;
                        ++this.menuActionRow;
                     } else if(this.spellSelected == 1) {
                        if((this.spellUsableOn & 1) == 1) {
                           this.menuActionName[this.menuActionRow] = this.spellTooltip + " @lre@" + var15.name;
                           this.menuActionID[this.menuActionRow] = 94;
                           this.menuActionCmd1[this.menuActionRow] = var17.ID;
                           this.menuActionCmd2[this.menuActionRow] = i1;
                           this.menuActionCmd3[this.menuActionRow] = j1;
                           ++this.menuActionRow;
                        }
                     } else {
                        for(int j3 = 4; j3 >= 0; --j3) {
                           if(var15.groundActions != null && var15.groundActions[j3] != null) {
                              this.menuActionName[this.menuActionRow] = var15.groundActions[j3] + " @lre@" + var15.name;
                              if(j3 == 0) {
                                 this.menuActionID[this.menuActionRow] = 652;
                              }

                              if(j3 == 1) {
                                 this.menuActionID[this.menuActionRow] = 567;
                              }

                              if(j3 == 2) {
                                 this.menuActionID[this.menuActionRow] = 234;
                              }

                              if(j3 == 3) {
                                 this.menuActionID[this.menuActionRow] = 244;
                              }

                              if(j3 == 4) {
                                 this.menuActionID[this.menuActionRow] = 213;
                              }

                              this.menuActionCmd1[this.menuActionRow] = var17.ID;
                              this.menuActionCmd2[this.menuActionRow] = i1;
                              this.menuActionCmd3[this.menuActionRow] = j1;
                              ++this.menuActionRow;
                           } else if(j3 == 2) {
                              this.menuActionName[this.menuActionRow] = "Take @lre@" + var15.name;
                              this.menuActionID[this.menuActionRow] = 234;
                              this.menuActionCmd1[this.menuActionRow] = var17.ID;
                              this.menuActionCmd2[this.menuActionRow] = i1;
                              this.menuActionCmd3[this.menuActionRow] = j1;
                              ++this.menuActionRow;
                           }
                        }

                        if(myPrivilege >= 2 && developMode){
								this.menuActionName[this.menuActionRow] = "Examine @lre@" + var15.name + " @gre@(@whi@" + var17.ID + "@gre@)";
							}else{
								this.menuActionName[this.menuActionRow] = "Examine @lre@" + var15.name;
							}
                        this.menuActionID[this.menuActionRow] = 1448;
                        this.menuActionCmd1[this.menuActionRow] = var17.ID;
                        this.menuActionCmd2[this.menuActionRow] = i1;
                        this.menuActionCmd3[this.menuActionRow] = j1;
                        ++this.menuActionRow;
                     }
                  }
               }
            }
         }
      }

   }
   
   
	public void method237(int paramInt)
	{	
		if (paramInt < 0)
			paramInt = 0;
		else if (paramInt > 253)
			paramInt = 253;
		this.stream.createFrame(4);
		this.stream.writeWordBigEndian(2 + paramInt);
		this.stream.method425(method247(6));
		this.stream.method425(method247(12));
		for (int i = 0; i < paramInt; i++)
			stream.buffer[(stream.currentOffset + i)] = 0;
		stream.currentOffset += paramInt;
	}
	
	public int method247(int paramInt)
	{
		return (int)(Math.random() * paramInt);
	}

   public void cleanUpForQuit() {
      SignLink.reporterror = false;

      try {
         if(this.socketStream != null) {
            this.socketStream.close();
         }
      } catch (Exception var2) {
         ;
      }

      this.socketStream = null;
      this.stopMidi();
      if(this.mouseDetection != null) {
         this.mouseDetection.running = false;
      }

      this.mouseDetection = null;
      this.onDemandFetcher.disable();
      this.onDemandFetcher = null;
      this.aStream_834 = null;
      this.stream = null;
      this.aStream_847 = null;
      this.inStream = null;
      this.anIntArray1234 = null;
      this.aByteArrayArray1183 = (byte[][])null;
      this.aByteArrayArray1247 = (byte[][])null;
      this.anIntArray1235 = null;
      this.anIntArray1236 = null;
      this.intGroundArray = (int[][][])null;
      this.byteGroundArray = (byte[][][])null;
      this.worldController = null;
      this.aClass11Array1230 = null;
      this.anIntArrayArray901 = (int[][])null;
      this.anIntArrayArray825 = (int[][])null;
      this.bigX = null;
      this.bigY = null;
      this.aByteArray912 = null;
      this.aRSImageProducer_1163 = null;
      this.aRSImageProducer_1164 = null;
      this.aRSImageProducer_1165 = null;
      this.aRSImageProducer_1166 = null;
      this.aRSImageProducer_1123 = null;
      this.aRSImageProducer_1124 = null;
      this.aRSImageProducer_1125 = null;
	  this.aRSImageProducer_oma1 = null;
	  this.aRSImageProducer_oma2 = null;
	  this.aRSImageProducer_oma3 = null;
	  this.aRSImageProducer_oma4 = null;
	  this.aRSImageProducer_oma5 = null;
	  this.aRSImageProducer_oma6 = null;
	  this.aRSImageProducer_oma7 = null;
	  this.aRSImageProducer_oma8 = null;
      this.backLeftIP1 = null;
      this.backLeftIP2 = null;
      this.backRightIP1 = null;
      this.backRightIP2 = null;
      this.backTopIP1 = null;
      this.backVmidIP1 = null;
      this.backVmidIP2 = null;
      this.backVmidIP3 = null;
      this.backVmidIP2_2 = null;
      this.invBack = null;
      mapBack = null;
	  mapBacks = null;
      this.chatBack = null;
      this.backBase1 = null;
      this.backBase2 = null;
      this.backHmid1 = null;
      this.sideIcons = null;
      this.redStone1 = null;
      this.redStone2 = null;
      this.redStone3 = null;
      this.redStone1_2 = null;
      this.redStone2_2 = null;
      this.redStone1_3 = null;
      this.redStone2_3 = null;
      this.redStone3_2 = null;
      this.redStone1_4 = null;
      this.redStone2_4 = null;
      compass = null;
	  compass_a = null;
      this.hitMarks = null;
      this.headIcons = null;
      this.skullIcons = null;
      this.headIconsHint = null;
      this.crosses = null;
      this.mapDotItem = null;
      this.mapDotNPC = null;
      this.mapDotPlayer = null;
      this.mapDotFriend = null;
      this.mapDotTeam = null;
	  this.mapDotStaff = null;
      this.mapScenes = null;
      this.mapFunctions = null;
      this.anIntArrayArray929 = (int[][])null;
      this.playerArray = null;
      this.playerIndices = null;
      this.anIntArray894 = null;
      this.aStreamArray895s = null;
      this.anIntArray840 = null;
      this.npcArray = null;
      this.npcIndices = null;
      this.groundArray = (NodeList[][][])null;
      this.aClass19_1179 = null;
      this.aClass19_1013 = null;
      this.aClass19_1056 = null;
      this.menuActionCmd2 = null;
      this.menuActionCmd3 = null;
      this.menuActionID = null;
      this.menuActionCmd1 = null;
      this.menuActionName = null;
      this.variousSettings = null;
      this.anIntArray1072 = null;
      this.anIntArray1073 = null;
      this.aClass30_Sub2_Sub1_Sub1Array1140 = null;
      this.aClass30_Sub2_Sub1_Sub1_1263 = null;
      this.friendsList = null;
      this.friendsListAsLongs = null;
      this.friendsNodeIDs = null;
      this.aRSImageProducer_1110 = null;
      this.aRSImageProducer_1111 = null;
      this.aRSImageProducer_1107 = null;
      this.aRSImageProducer_1108 = null;
      this.aRSImageProducer_1109 = null;
      this.aRSImageProducer_1112 = null;
      this.aRSImageProducer_1113 = null;
      this.aRSImageProducer_1114 = null;
      this.aRSImageProducer_1115 = null;
      this.multiOverlay = null;
      this.nullLoader();
      ObjectDef.nullLoader();
      EntityDef.nullLoader();
      ItemDef.nullLoader();
      Flo.cache = null;
      IDK.cache = null;
      RSInterface.interfaceCache = null;
      DummyClass.cache = null;
      Animation.anims = null;
      SpotAnim.cache = null;
      SpotAnim.aMRUNodes_415 = null;
      Varp.cache = null;
      super.fullGameScreen = null;
      Player.mruNodes = null;
      Texture.nullLoader();
      WorldController.nullLoader();
      Model.nullLoader();
      Class36.nullLoader();
      System.gc();
   }

   public void printDebug() {
      System.out.println("============");
      System.out.println("flame-cycle:" + this.anInt1208);
      if(this.onDemandFetcher != null) {
         System.out.println("Od-cycle:" + this.onDemandFetcher.onDemandCycle);
      }

      System.out.println("loop-cycle:" + loopCycle);
      System.out.println("draw-cycle:" + anInt1061);
      System.out.println("ptype:" + this.pktType);
      System.out.println("psize:" + this.pktSize);
      if(this.socketStream != null) {
         this.socketStream.printDebug();
      }

      super.shouldDebug = true;
   }

   static final int method670(int i, int i_0_) {
      int i_3_;
      if(i > i_0_) {
         i_3_ = i_0_;
         i_0_ = i;
         i = i_3_;
      }

      while(i != 0) {
         i_3_ = i_0_ % i;
         i_0_ = i;
         i = i_3_;
      }

      return i_0_;
   }

   Component getGameComponent() {
      return (Component)(SignLink.mainapp != null?SignLink.mainapp:(super.gameFrame != null?super.gameFrame:this));
   }

   static final void method684(boolean bool, int i, int i_2_, byte[] is) {
      if(aClass56_749 != null) {
         if(anInt478 >= 0) {
            anInt2200 = i;
            if(anInt478 != 0) {
               int i_4_ = method1004(anInt478);
               i_4_ -= anInt155;
               anInt720 = (i_4_ + 3600) / i;
               if(anInt720 < 1) {
                  anInt720 = 1;
               }
            } else {
               anInt720 = 1;
            }

            aByteArray347 = is;
            anInt1478 = i_2_;
            aBoolean475 = bool;
         } else if(anInt720 == 0) {
            method853(i_2_, is, bool);
         } else {
            anInt1478 = i_2_;
            aBoolean475 = bool;
            aByteArray347 = is;
         }
      }

   }
   
   int tester = 0;
   
   List<Integer> indexedBankItems = new ArrayList<>();
   int realBankItemIds[];
   int realBankItemAmounts[];
   String realBankTitle = "";
   String searchingBank = "";
   int previousBankScrollPosition = 0;
   
	void searchBank(){
		if(searchingBank == null)
			return;
		int[] inv = realBankItemIds;
		int[] stack = realBankItemAmounts;
		List<Integer> filteredItemIds = new ArrayList<>();
		List<Integer> filteredItemAmounts = new ArrayList<>();
		for(int i = 0; i < inv.length; i++) {
			int realId = inv[i]-1;
			if(inv[i] > 0) {
				String name = ItemDef.forID(realId).name;
				if(name != null){
					if(name.toLowerCase().contains(searchingBank.toLowerCase())){
						filteredItemIds.add(inv[i]);
						filteredItemAmounts.add(stack[i]);
						indexedBankItems.add(i);
					}
				}
			}
		}
		//String btitle = RSInterface.interfaceCache[5383].message;
		RSInterface.interfaceCache[5383].message = "Bank of Orion (search: '"+searchingBank+"')";
		RSInterface.interfaceCache[5385].scrollPosition = 0;
		if(searchingBank == ""){
			RSInterface.interfaceCache[5383].message = "Bank of Orion (no search entered)";
			RSInterface.interfaceCache[18787].message = "No search term entered!";
		}else{
			//RSInterface.interfaceCache[5383].message = "Bank of Orion (search: '"+TextClass.fixName(searchingBank)+"')";
			if(filteredItemIds.size() == 0)
				RSInterface.interfaceCache[18787].message = "No matches found!";
			else
				RSInterface.interfaceCache[18787].message = "";
		}
		RSInterface.interfaceCache[5382].inv = new int[inv.length];
		RSInterface.interfaceCache[5382].invStackSizes = new int[stack.length];
		for(int i = 0; i < filteredItemIds.size(); i++) {
			if(searchingBank == ""){
				RSInterface.interfaceCache[5382].inv[i] = 0;
				RSInterface.interfaceCache[5382].invStackSizes[i] = 0;
			} else {
				RSInterface.interfaceCache[5382].inv[i] = filteredItemIds.get(i);
				RSInterface.interfaceCache[5382].invStackSizes[i] = filteredItemAmounts.get(i);
			}
		}
	}
	
	void updateBank(){
		indexedBankItems.clear();
		if(this.inputDialogState == 2) {
			searchBank();
		} else {
			RSInterface.interfaceCache[18787].message = "";
			RSInterface.interfaceCache[5382].inv = realBankItemIds;
			RSInterface.interfaceCache[5382].invStackSizes = realBankItemAmounts;
			RSInterface.interfaceCache[5383].message = realBankTitle;
			if(previousBankScrollPosition != 0){
				RSInterface.interfaceCache[5385].scrollPosition = previousBankScrollPosition;
				previousBankScrollPosition = 0;
			}
		}
	}
	
   public void method73() {
      while(true) {
         int j = this.readChar(-796);
         if(j == -1) {
            return;
         }
         
         if(editMode){
        	 int move = 3;
        	 System.out.println("Pressed key: "+j);
        	 if(j == 119) {
        	       //w
        	       myPlayer.setPos(myPlayer.smallX[0], myPlayer.smallY[0]+move, false);
        	       if (myPlayer.smallX[0] < 16 || myPlayer.smallX[0] >= 88 || myPlayer.smallY[0] < 16 || myPlayer.smallY[0] > 88) {
        	    	   loadPos(myPlayer.smallX[0]+this.baseX, myPlayer.smallY[0]+this.baseY, this.plane);
        			}
        	       return;
        	   } else if (j == 115) {
        	       //s
        		   myPlayer.setPos(myPlayer.smallX[0], myPlayer.smallY[0]-move, false);
        	       if (myPlayer.smallX[0] < 16 || myPlayer.smallX[0] >= 88 || myPlayer.smallY[0] < 16 || myPlayer.smallY[0] > 88) {
        	    	   loadPos(myPlayer.smallX[0]+this.baseX, myPlayer.smallY[0]+this.baseY, this.plane);
        			}
        	       return;
        	   } else if (j == 100) {
        	       //d
        		   myPlayer.setPos(myPlayer.smallX[0]+move, myPlayer.smallY[0], false);
        	       if (myPlayer.smallX[0] < 16 || myPlayer.smallX[0] >= 88 || myPlayer.smallY[0] < 16 || myPlayer.smallY[0] > 88) {
        	    	   loadPos(myPlayer.smallX[0]+this.baseX, myPlayer.smallY[0]+this.baseY, this.plane);
        			}
        	    return;
        	   } else if (j == 97) {
        	       //a
        		   myPlayer.setPos(myPlayer.smallX[0]-move, myPlayer.smallY[0], false);
        	       if (myPlayer.smallX[0] < 16 || myPlayer.smallX[0] >= 88 || myPlayer.smallY[0] < 16 || myPlayer.smallY[0] > 88) {
        	    	   loadPos(myPlayer.smallX[0]+this.baseX, myPlayer.smallY[0]+this.baseY, this.plane);
        			}
        	       return;
        	   } else if (j == 43) {
        	       //+
        		   if(this.plane < 3){
        			   loadPos(myPlayer.smallX[0]+this.baseX, myPlayer.smallY[0]+this.baseY, this.plane+1);
        		   }
        	       return;
        	   } else if (j == 45) {
        	       //-
        		   if(this.plane > 0){
        			   loadPos(myPlayer.smallX[0]+this.baseX, myPlayer.smallY[0]+this.baseY, this.plane-1);
        		   }
        	       return;
        	   }
         }

         if(this.openInterfaceID != -1 && this.openInterfaceID == this.reportAbuseInterfaceID) {
            if(j == 8 && this.reportAbuseInput.length() > 0) {
               this.reportAbuseInput = this.reportAbuseInput.substring(0, this.reportAbuseInput.length() - 1);
            }

            if((j >= 97 && j <= 122 || j >= 65 && j <= 90 || j >= 48 && j <= 57 || j == 32) && this.reportAbuseInput.length() < 12) {
               this.reportAbuseInput = this.reportAbuseInput + (char)j;
            }
         } else {
            long var7;
            if(this.messagePromptRaised) {
               if(j >= 32 && j <= 122 && this.promptInput.length() < 80) {
                  this.promptInput = this.promptInput + (char)j;
                  this.inputTaken = true;
               }

               if(j == 8 && this.promptInput.length() > 0) {
                  this.promptInput = this.promptInput.substring(0, this.promptInput.length() - 1);
                  this.inputTaken = true;
               }

               if(j == 13 || j == 10) {
                  this.messagePromptRaised = false;
                  this.inputTaken = true;
                  if(this.friendsListAction == 1) {
                     var7 = TextClass.longForName(this.promptInput);
                     this.addFriend(var7);
                  }

                  if(this.friendsListAction == 2 && this.friendsCount > 0) {
                     var7 = TextClass.longForName(this.promptInput);
                     this.delFriend(var7);
                  }

                  if(this.friendsListAction == 3 && this.promptInput.length() > 0) {
                     this.stream.createFrame(126);
                     this.stream.writeWordBigEndian(0);
                     int var8 = this.stream.currentOffset;
                     this.stream.writeQWord(this.aLong953);
                     TextInput.method526(this.promptInput, this.stream);
                     this.stream.writeBytes(this.stream.currentOffset - var8);
                     this.promptInput = TextInput.processText(this.promptInput);
                     this.promptInput = Censor.doCensor(this.promptInput);
                     this.pushMessage(this.promptInput, 6, TextClass.fixName(TextClass.nameForLong(this.aLong953)));
                     if(this.privateChatMode == 2) {
                        this.privateChatMode = 1;
                        this.aBoolean1233 = true;
                        this.stream.createFrame(95);
                        this.stream.writeWordBigEndian(this.publicChatMode);
                        this.stream.writeWordBigEndian(this.privateChatMode);
                        this.stream.writeWordBigEndian(this.tradeMode);
                     }
                  }

                  if(this.friendsListAction == 4 && this.ignoreCount < 100) {
                     var7 = TextClass.longForName(this.promptInput);
                     this.addIgnore(var7);
                  }

                  if(this.friendsListAction == 5 && this.ignoreCount > 0) {
                     var7 = TextClass.longForName(this.promptInput);
                     this.delIgnore(var7);
                  }
               }
            } else if(this.inputDialogState == 1) {
               if(j >= 48 && j <= 57 && this.amountOrNameInput.length() < 10) {
                  this.amountOrNameInput = this.amountOrNameInput + (char)j;
                  this.inputTaken = true;
               }
               if ((!amountOrNameInput.toLowerCase().contains("k") && !amountOrNameInput.toLowerCase().contains("m") && !amountOrNameInput.toLowerCase().contains("b")) && (j == 107 || j == 109) || j == 98) {
					amountOrNameInput += (char) j;
					inputTaken = true;
				}
               if(j == 8 && this.amountOrNameInput.length() > 0) {
                  this.amountOrNameInput = this.amountOrNameInput.substring(0, this.amountOrNameInput.length() - 1);
                  this.inputTaken = true;
               }

               if(j == 13 || j == 10) {
                  if(this.amountOrNameInput.length() > 0) {
                	  if (amountOrNameInput.toLowerCase().contains("k")) {
							amountOrNameInput = amountOrNameInput.replaceAll("k", "000");
						} else if (amountOrNameInput.toLowerCase().contains("m")) {
							amountOrNameInput = amountOrNameInput.replaceAll("m", "000000");
						} else if (amountOrNameInput.toLowerCase().contains("b")) {
							amountOrNameInput = amountOrNameInput.replaceAll("b", "000000000");
						}
                     var7 = 0L;

                     try {
                        var7 = Long.parseLong(this.amountOrNameInput);
                     } catch (Exception var6) {
                        ;
                     }

                     int var9 = var7 > 2147483647L?Integer.MAX_VALUE:(int)var7;
                     this.stream.createFrame(208);
                     this.stream.writeDWord(var9);
                  }

                  this.inputDialogState = 0;
                  this.inputTaken = true;
               }
            } else if(this.inputDialogState == 2) {
               if(j >= 32 && j <= 122 && this.amountOrNameInput.length() < 12) {
                  this.amountOrNameInput = this.amountOrNameInput + (char)j;
                  this.inputTaken = true;
               }

               if(j == 8 && this.amountOrNameInput.length() > 0) {
                  this.amountOrNameInput = this.amountOrNameInput.substring(0, this.amountOrNameInput.length() - 1);
                  this.inputTaken = true;
               }

               if(j == 13 || j == 10) {
                  if(this.amountOrNameInput.length() > 0) {
                     this.stream.createFrame(60);
                     this.stream.writeQWord(TextClass.longForName(this.amountOrNameInput));
                  }

                  this.inputDialogState = 0;
                  this.inputTaken = true;
               }
			   if(openInterfaceID == 5292){//bank search
				if(this.amountOrNameInput.length() <= 0)
					searchingBank = "";
				else
					searchingBank = this.amountOrNameInput;
				updateBank();
			   }
            } else if (inputDialogState == 3) {
					if (j == 10) {
						inputDialogState = 0;
						inputTaken = true;
					}
					if (j >= 32 && j <= 122 && amountOrNameInput.length() < 40) {
						amountOrNameInput += (char) j;
						inputTaken = true;
					}
					if (j == 8 && amountOrNameInput.length() > 0) {
						amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
						inputTaken = true;
					}
            } else if(this.backDialogID == -1) {
               if(j >= 32 && j <= 122 && this.inputString.length() < 80) {
                  this.inputString = this.inputString + (char)j;
                  this.inputTaken = true;
               }

               if(j == 8 && this.inputString.length() > 0) {
                  this.inputString = this.inputString.substring(0, this.inputString.length() - 1);
                  this.inputTaken = true;
               }

               if((j == 13 || j == 10) && this.inputString.length() > 0) {
                  /*if(this.inputString.equals("::sound")) {
                     this.sound[this.soundCount] = 3;
                     this.soundType[this.soundCount] = 1;
                     this.soundDelay[this.soundCount] = 0;
                     aClass26Array1468[this.soundCount] = null;
                     ++this.soundCount;
                  }
				  if(this.inputString.equals("::cam")) {
					System.out.println("*****");
					System.out.println("Current CAM:");
					System.out.println("CAM POS: "+this.xCameraPos+" "+this.yCameraPos+" "+this.zCameraPos);
					System.out.println("CAM CURVE: "+this.xCameraCurve+" "+this.yCameraCurve);
					System.out.println("METHOD42: "+this.method42(this.plane, this.yCameraPos, this.xCameraPos));
                  }*/
				  /*if(this.inputString.startsWith("::bts")) {
					String[] ncol = this.inputString.split(" ");
					int id23 = Integer.valueOf(ncol[1]);
					for(int id = (0+(1000*(id23-1))); id < (1000*(id23)); id++){
						this.stream.createFrame(185);
						this.stream.writeWord(id);
					}
					System.out.println("fds");
                  }*/
					/*if(this.inputString.startsWith("1")) {		
						method237(tester++ % 254);		  
						return;
					}*/
					/*if (inputString.startsWith("::full")) {
                        try {
                            String[] args = inputString.split(" ");
                            int id1 = Integer.parseInt(args[1]);
                            int id2 = Integer.parseInt(args[2]);
                            fullscreenInterfaceID = id1;
                            openInterfaceID = id2;
                            pushMessage("Opened Interface", 0, "");
                        } catch (Exception e) {
                            pushMessage("Interface Failed to load", 0, "");
                        }
                    }*/
				if(this.inputString.equals("::fps")) {
					fpsOn = !fpsOn;
                  }
				
				if(this.inputString.startsWith("/")) {
                    this.stream.createFrame(103);
                    String s = "yell "+this.inputString.substring(1);
                    this.stream.writeWordBigEndian(s.length()+1);
                    this.stream.writeString(s);
                 } else
                  if(this.inputString.startsWith("::")) {
                     this.stream.createFrame(103);
                     this.stream.writeWordBigEndian(this.inputString.length() - 1);
                     this.stream.writeString(this.inputString.substring(2));
                  } else {
                     String s = this.inputString.toLowerCase();
                     byte j2 = 0;
                     if(s.startsWith("yellow:")) {
                        j2 = 0;
                        this.inputString = this.inputString.substring(7);
                     } else if(s.startsWith("red:")) {
                        j2 = 1;
                        this.inputString = this.inputString.substring(4);
                     } else if(s.startsWith("green:")) {
                        j2 = 2;
                        this.inputString = this.inputString.substring(6);
                     } else if(s.startsWith("cyan:")) {
                        j2 = 3;
                        this.inputString = this.inputString.substring(5);
                     } else if(s.startsWith("purple:")) {
                        j2 = 4;
                        this.inputString = this.inputString.substring(7);
                     } else if(s.startsWith("white:")) {
                        j2 = 5;
                        this.inputString = this.inputString.substring(6);
                     } else if(s.startsWith("flash1:")) {
                        j2 = 6;
                        this.inputString = this.inputString.substring(7);
                     } else if(s.startsWith("flash2:")) {
                        j2 = 7;
                        this.inputString = this.inputString.substring(7);
                     } else if(s.startsWith("flash3:")) {
                        j2 = 8;
                        this.inputString = this.inputString.substring(7);
                     } else if(s.startsWith("glow1:")) {
                        j2 = 9;
                        this.inputString = this.inputString.substring(6);
                     } else if(s.startsWith("glow2:")) {
                        j2 = 10;
                        this.inputString = this.inputString.substring(6);
                     } else if(s.startsWith("glow3:")) {
                        j2 = 11;
                        this.inputString = this.inputString.substring(6);
                     }

                     s = this.inputString.toLowerCase();
                     byte i3 = 0;
                     if(s.startsWith("wave:")) {
                        i3 = 1;
                        this.inputString = this.inputString.substring(5);
                     } else if(s.startsWith("wave2:")) {
                        i3 = 2;
                        this.inputString = this.inputString.substring(6);
                     } else if(s.startsWith("shake:")) {
                        i3 = 3;
                        this.inputString = this.inputString.substring(6);
                     } else if(s.startsWith("scroll:")) {
                        i3 = 4;
                        this.inputString = this.inputString.substring(7);
                     } else if(s.startsWith("slide:")) {
                        i3 = 5;
                        this.inputString = this.inputString.substring(6);
                     }

                     this.stream.createFrame(4);
                     this.stream.writeWordBigEndian(0);
                     int j3 = this.stream.currentOffset;
                     this.stream.method425(i3);
                     this.stream.method425(j2);
                     this.aStream_834.currentOffset = 0;
                     TextInput.method526(this.inputString, this.aStream_834);
                     this.stream.method441(0, this.aStream_834.buffer, this.aStream_834.currentOffset);
                     this.stream.writeBytes(this.stream.currentOffset - j3);
                     this.inputString = TextInput.processText(this.inputString);
                     this.inputString = Censor.doCensor(this.inputString);
                     myPlayer.textSpoken = this.inputString;
                     myPlayer.anInt1513 = j2;
                     myPlayer.anInt1531 = i3;
                     myPlayer.textCycle = 150;
                     if(this.myPrivilege != 2 && this.myPrivilege != 3) {
                        if(this.myPrivilege == 1) {
                           this.pushMessage(myPlayer.textSpoken, 2, "@cr1@" + myPlayer.name);
                        } else {
							if(this.myDonor)
								this.pushMessage(myPlayer.textSpoken, 2, "@don1@" + myPlayer.name);
							else
								this.pushMessage(myPlayer.textSpoken, 2, myPlayer.name);
                        }
                     } else {
                        this.pushMessage(myPlayer.textSpoken, 2, "@cr2@" + myPlayer.name);
                     }

                     if(this.publicChatMode == 2) {
                        this.publicChatMode = 3;
                        this.aBoolean1233 = true;
                        this.stream.createFrame(95);
                        this.stream.writeWordBigEndian(this.publicChatMode);
                        this.stream.writeWordBigEndian(this.privateChatMode);
                        this.stream.writeWordBigEndian(this.tradeMode);
                     }
                  }

                  this.inputString = "";
                  this.inputTaken = true;
               }
            }
         }
      }
   }
   
   private void buildPublicChat(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 100; i1++)
		{
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 1)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			/*int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;*/
			int k1 = 70 - l * 14 + this.anInt1089 + 4;
            if(k1 < -20) {
               break;
            }
			if(s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@don1@"))
				s = s.substring(6);
			if((j1 == 1 || j1 == 2 || j1 == 98) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s))) {
			if(j > k1 - 14 && j <= k1 && !s.equals(myPlayer.name)) {
				//if(myPrivilege >= 1) {
					menuActionName[menuActionRow] = "Report abuse @whi@" + s;
					menuActionID[menuActionRow] = 606;
					menuActionRow++;
				//}
				menuActionName[menuActionRow] = "Add ignore @whi@" + s;
				menuActionID[menuActionRow] = 42;
				menuActionRow++;
				menuActionName[menuActionRow] = "Add friend @whi@" + s;
				menuActionID[menuActionRow] = 337;
				menuActionRow++;
			}
			l++;
			}
		}
	}

	private void buildFriendChat(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 100; i1++) {
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 2)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			/*int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;*/
			int k1 = 70 - l * 14 + this.anInt1089 + 4;
            if(k1 < -20) {
               break;
            }
			if(s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@don1@"))
				s = s.substring(6);
			if((j1 == 5 || j1 == 6) && (splitpublicChat == 0 || chatTypeView == 2) && (j1 == 6 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
				l++;
			if((j1 == 3 || j1 == 7) && (splitpublicChat == 0 || chatTypeView == 2) && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1) {
					//if(myPrivilege >= 1) {
						menuActionName[menuActionRow] = "Report abuse @whi@" + s;
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					//}
					menuActionName[menuActionRow] = "Add ignore @whi@" + s;
					menuActionID[menuActionRow] = 42;
					menuActionRow++;
					menuActionName[menuActionRow] = "Add friend @whi@" + s;
					menuActionID[menuActionRow] = 337;
					menuActionRow++;
				}
			l++;
			}
		}
	}

	private void buildDuelorTrade(int j) {
		int l = 0;
		for(int i1 = 0; i1 < 100; i1++) {
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 3 && chatTypeView != 4)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			/*int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;*/
			int k1 = 70 - l * 14 + this.anInt1089 + 4;
            if(k1 < -20) {
               break;
            }
			if(s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@don1@"))
				s = s.substring(6);
			if(chatTypeView == 3 && j1 == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept trade @whi@" + s;
					menuActionID[menuActionRow] = 484;
					menuActionRow++;
				}
				l++;
			}
			if(chatTypeView == 4 && j1 == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept challenge @whi@" + s;
					menuActionID[menuActionRow] = 6;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 12) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Go-to @blu@" + s;
					menuActionID[menuActionRow] = 915;
					menuActionRow++;
				}
				l++;
			}
		}
	}

   public void buildChatAreaMenu(int j) {
      int l = 0;

      for(int i1 = 0; i1 < 100; ++i1) {
         if(this.chatMessages[i1] != null) {
            int j1 = this.chatTypes[i1];
            int k1 = 70 - l * 14 + this.anInt1089 + 4;
            if(k1 < -20) {
               break;
            }
            String s = this.chatNames[i1];
			if(chatTypeView == 1) {
				buildPublicChat(j);
				break;
			}
			if(chatTypeView == 2) {
				buildFriendChat(j);
				break;
			}
			if(chatTypeView == 3 || chatTypeView == 4) {
				buildDuelorTrade(j);
				break;
			}
			if(chatTypeView == 5) {
				break;
			}
            if(s != null && s.startsWith("@cr1@")) {
               s = s.substring(5);
            }

            if(s != null && s.startsWith("@cr2@")) {
               s = s.substring(5);
            }
			
			if(s != null && s.startsWith("@don1@")) {
               s = s.substring(6);
            }

            if(j1 == 0) {
               ++l;
            }

            if((j1 == 1 || j1 == 2 || j1 == 98) && (j1 == 1 || this.publicChatMode == 0 || this.publicChatMode == 1 && this.isFriendOrSelf(s))) {
               if(j > k1 - 14 && j <= k1 && !s.equals(myPlayer.name)) {
                  //if(this.myPrivilege >= 1 && this.myPrivilege <= 3) {//right click report for every1
                     this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                     this.menuActionID[this.menuActionRow] = 606;
                     ++this.menuActionRow;
                  //}

                  this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 42;
                  ++this.menuActionRow;
                  this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 337;
                  ++this.menuActionRow;
               }

               ++l;
            }

            if((j1 == 3 || j1 == 7) && this.splitpublicChat == 0 && (j1 == 7 || this.privateChatMode == 0 || this.publicChatMode == 1 && this.isFriendOrSelf(s))) {
               if(j > k1 - 14 && j <= k1) {
                  //if(this.myPrivilege >= 1 && this.myPrivilege <= 3) {//right click report for every1
                     this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                     this.menuActionID[this.menuActionRow] = 606;
                     ++this.menuActionRow;
                  //}

                  this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 42;
                  ++this.menuActionRow;
                  this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 337;
                  ++this.menuActionRow;
               }

               ++l;
            }

            if(j1 == 4 && (this.tradeMode == 0 || this.tradeMode == 1 && this.isFriendOrSelf(s))) {
               if(j > k1 - 14 && j <= k1) {
                  this.menuActionName[this.menuActionRow] = "Accept trade @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 484;
                  ++this.menuActionRow;
               }

               ++l;
            }

            if((j1 == 5 || j1 == 6) && this.splitpublicChat == 0 && this.privateChatMode < 2) {
               ++l;
            }

            if(j1 == 8 && (this.tradeMode == 0 || this.tradeMode == 1 && this.isFriendOrSelf(s))) {
               if(j > k1 - 14 && j <= k1) {
                  this.menuActionName[this.menuActionRow] = "Accept challenge @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 6;
                  ++this.menuActionRow;
               }

               ++l;
            }
         }
      }

   }

   public void drawFriendsListOrWelcomeScreen(RSInterface class9) {
      int j = class9.anInt214;
      int s1;
      if((j < 1 || j > 100) && (j < 701 || j > 800)) {
         if((j < 101 || j > 200) && (j < 801 || j > 900)) {
            if(j == 203) {
               s1 = this.friendsCount;
               if(this.anInt900 != 2) {
                  s1 = 0;
               }

               class9.scrollMax = s1 * 15 + 20;
               if(class9.scrollMax <= class9.height) {
                  class9.scrollMax = class9.height + 1;
               }

            } else if(j >= 401 && j <= 500) {
               j -= 401;
               if(j == 0 && this.anInt900 == 0) {
                  class9.message = "Loading ignore list";
                  class9.atActionType = 0;
               } else if(j == 1 && this.anInt900 == 0) {
                  class9.message = "Please wait...";
                  class9.atActionType = 0;
               } else {
                  s1 = this.ignoreCount;
                  if(this.anInt900 == 0) {
                     s1 = 0;
                  }

                  if(j >= s1) {
                     class9.message = "";
                     class9.atActionType = 0;
                  } else {
                     class9.message = TextClass.fixName(TextClass.nameForLong(this.ignoreListAsLongs[j]));
                     class9.atActionType = 1;
                  }
               }
            } else if(j == 503) {
               class9.scrollMax = this.ignoreCount * 15 + 20;
               if(class9.scrollMax <= class9.height) {
                  class9.scrollMax = class9.height + 1;
               }

            } else if(j == 327) {
               class9.anInt270 = 150;
               class9.anInt271 = (int)(Math.sin((double)loopCycle / 40.0D) * 256.0D) & 2047;
               if(this.aBoolean1031) {
                  int i2;
                  for(s1 = 0; s1 < 7; ++s1) {
                     i2 = this.anIntArray1065[s1];
                     if(i2 >= 0 && !IDK.cache[i2].method537()) {
                        return;
                     }
                  }

                  this.aBoolean1031 = false;
                  Model[] var9 = new Model[7];
                  i2 = 0;

                  int l2;
                  for(int model = 0; model < 7; ++model) {
                     l2 = this.anIntArray1065[model];
                     if(l2 >= 0) {
                        var9[i2++] = IDK.cache[l2].method538();
                     }
                  }

                  Model var8 = new Model(i2, var9);

                  for(l2 = 0; l2 < 5; ++l2) {
                     if(this.anIntArray990[l2] != 0) {
                        var8.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][this.anIntArray990[l2]]);
                        if(l2 == 1) {
                           var8.method476(anIntArray1204[0], anIntArray1204[this.anIntArray990[l2]]);
                        }
                     }
                  }

                  var8.method469();
                  var8.method470(Animation.anims[myPlayer.anInt1511].anIntArray353[0]);
                  var8.method479(64, 850, -30, -50, -30, true);
                  class9.anInt233 = 5;
                  class9.mediaID = 0;
                  RSInterface.method208(this.aBoolean994, var8);
               }

            } else if(j == 324) {
               if(this.aClass30_Sub2_Sub1_Sub1_931 == null) {
                  this.aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
                  this.aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
               }

               if(this.aBoolean1047) {
                  class9.sprite1 = this.aClass30_Sub2_Sub1_Sub1_932;
               } else {
                  class9.sprite1 = this.aClass30_Sub2_Sub1_Sub1_931;
               }
            } else if(j == 325) {
               if(this.aClass30_Sub2_Sub1_Sub1_931 == null) {
                  this.aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
                  this.aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
               }

               if(this.aBoolean1047) {
                  class9.sprite1 = this.aClass30_Sub2_Sub1_Sub1_931;
               } else {
                  class9.sprite1 = this.aClass30_Sub2_Sub1_Sub1_932;
               }
            } else if(j == 600) {
               class9.message = this.reportAbuseInput;
               if(loopCycle % 20 < 10) {
                  class9.message = class9.message + "|";
               } else {
                  class9.message = class9.message + " ";
               }
            } else {
               if(j == 620) {
                  if(this.myPrivilege >= 1 && this.myPrivilege <= 3) {
                     if(this.canMute) {
                        class9.textColor = 16711680;
                        class9.message = "Moderator option: Mute player for 48 hours: <ON>";
                     } else {
                        class9.textColor = 16777215;
                        class9.message = "Moderator option: Mute player for 48 hours: <OFF>";
                     }
                  } else {
                     class9.message = "";
                  }
               }

               String var7;
				//377 Welcome screen below
				if(j == 660) {
                  if(this.anInt1193 != 0) {
                     if(this.daysSinceLastLogin == 0) {
                        var7 = "earlier today";
                     } else if(this.daysSinceLastLogin == 1) {
                        var7 = "yesterday";
                     } else {
                        var7 = this.daysSinceLastLogin + " days ago";
                     }

                     class9.message = "You last logged in @red@" + var7 + "@bla@ from: @red@" + SignLink.dns;
                  } else {
                     class9.message = "";
                  }
               }
				if (j == 661){
					if(this.daysSinceRecovChange == 30000) {
						class9.message = "You have not yet set any recovery questions.\\nIt is @lre@strongly@yel@ recommended that you do so.\\n\\nIf you don't you will be @lre@unable to recover your\\n@lre@password@yel@ if you forget it, or it is stolen.";
					} else {
                     if(this.daysSinceRecovChange == 0) {
                        var7 = "Earlier today";
                     } else if(this.daysSinceRecovChange == 1) {
                        var7 = "Yesterday";
                     } else {
                        var7 = this.daysSinceRecovChange + " days ago";
                     }

                     class9.message = "\\n\\nRecovery Questions Last Set:\\n"+var7;
                  }
				}
				if (j == 663)
					class9.message = ""+j;
				if (j == 665){
					String s = "Normal player";
					if(this.membersInt == 1)
						s = "Donator";
					class9.message = "Player status: "+s+"\\n\\nYou have @gre@" + this.donatorPoints + "@yel@ Donator points remaining.";
				}
			   
			   if(j == 662) {
					String s = "";
                  if(this.unreadMessages == 0) {
                     s = "@yel@0 unread messages";
                  }

                  if(this.unreadMessages == 1) {
                     s = "@gre@1 unread message";
                  }

                  if(this.unreadMessages > 1) {
                     s = "@gre@"+this.unreadMessages + " unread messages";
                  }
				  class9.message = "You have " + s + "\\nin your message centre.";
               }
				//377 Welcome screen above
             /*  if(j == 650 || j == 655) {
                  if(this.anInt1193 != 0) {
                     if(this.daysSinceLastLogin == 0) {
                        var7 = "earlier today";
                     } else if(this.daysSinceLastLogin == 1) {
                        var7 = "yesterday";
                     } else {
                        var7 = this.daysSinceLastLogin + " days ago";
                     }

                     class9.message = "You last logged in " + var7 + " from: " + SignLink.dns;
                  } else {
                     class9.message = "";
                  }
               }

               if(j == 651) {
                  if(this.unreadMessages == 0) {
                     class9.message = "0 unread messages";
                     class9.textColor = 16776960;
                  }

                  if(this.unreadMessages == 1) {
                     class9.message = "1 unread message";
                     class9.textColor = '\uff00';
                  }

                  if(this.unreadMessages > 1) {
                     class9.message = this.unreadMessages + " unread messages";
                     class9.textColor = '\uff00';
                  }
               }

               if(j == 652) {
                  if(this.daysSinceRecovChange == 201) {
                     if(this.membersInt == 1) {
                        class9.message = "@yel@This is a non-members world: @whi@Since you are a member we";
                     } else {
                        class9.message = "";
                     }
                  } else if(this.daysSinceRecovChange == 200) {
                     class9.message = "You have not yet set any password recovery questions.";
                  } else {
                     if(this.daysSinceRecovChange == 0) {
                        var7 = "Earlier today";
                     } else if(this.daysSinceRecovChange == 1) {
                        var7 = "Yesterday";
                     } else {
                        var7 = this.daysSinceRecovChange + " days ago";
                     }

                     class9.message = var7 + " you changed your recovery questions";
                  }
               }

               if(j == 653) {
                  if(this.daysSinceRecovChange == 201) {
                     if(this.membersInt == 1) {
                        class9.message = "@whi@recommend you use a members world instead. You may use";
                     } else {
                        class9.message = "";
                     }
                  } else if(this.daysSinceRecovChange == 200) {
                     class9.message = "We strongly recommend you do so now to secure your account.";
                  } else {
                     class9.message = "If you do not remember making this change then cancel it immediately";
                  }
               }

               if(j == 654) {
                  if(this.daysSinceRecovChange == 201) {
                     if(this.membersInt == 1) {
                        class9.message = "@whi@this world but member benefits are unavailable whilst here.";
                        return;
                     }

                     class9.message = "";
                     return;
                  }

                  if(this.daysSinceRecovChange == 200) {
                     class9.message = "Do this from the \'account management\' area on our front webpage";
                     return;
                  }

                  class9.message = "Do this from the \'account management\' area on our front webpage";
               }*/

            }
         } else {
            s1 = this.friendsCount;
            if(this.anInt900 != 2) {
               s1 = 0;
            }

            if(j > 800) {
               j -= 701;
            } else {
               j -= 101;
            }

            if(j >= s1) {
               class9.message = "";
               class9.atActionType = 0;
            } else {
               if(this.friendsNodeIDs[j] == 0) {
                  class9.message = "@red@Offline";
               } else if(this.friendsNodeIDs[j] == nodeID) {
                  class9.message = "@gre@World-1";
               } else {
                  class9.message = "@red@Offline";
               }

               class9.atActionType = 1;
            }
         }
      } else if(j == 1 && this.anInt900 == 0) {
         class9.message = "Loading friend list";
         class9.atActionType = 0;
      } else if(j == 1 && this.anInt900 == 1) {
         class9.message = "Connecting to friendserver";
         class9.atActionType = 0;
      } else if(j == 2 && this.anInt900 != 2) {
         class9.message = "Please wait...";
         class9.atActionType = 0;
      } else {
         s1 = this.friendsCount;
         if(this.anInt900 != 2) {
            s1 = 0;
         }

         if(j > 700) {
            j -= 601;
         } else {
            --j;
         }

         if(j >= s1) {
            class9.message = "";
            class9.atActionType = 0;
         } else {
            class9.message = this.friendsList[j];
            class9.atActionType = 1;
         }
      }
   }

   public void drawSplitpublicChat() {
      if(this.splitpublicChat != 0) {
         TextDrawingArea textDrawingArea = this.aTextDrawingArea_1271;
         int i = 0;
         if(this.anInt1104 != 0) {
            i = 1;
         }

         for(int j = 0; j < 100; ++j) {
            if(this.chatMessages[j] != null) {
               int k = this.chatTypes[j];
               String s = this.chatNames[j];
               byte byte1 = 0;
               if(s != null && s.startsWith("@cr1@")) {
                  s = s.substring(5);
                  byte1 = 1;
               }

               if(s != null && s.startsWith("@cr2@")) {
                  s = s.substring(5);
                  byte1 = 2;
               }
			   
			   if(s != null && s.startsWith("@don1@")) {
                  s = s.substring(6);
                  byte1 = 3;
               }

               int j1;
               if((k == 3 || k == 7) && (k == 7 || this.privateChatMode == 0 || this.privateChatMode == 1 && this.isFriendOrSelf(s))) {
                  j1 = 329 - i * 13;
                  byte k1 = 4;
                  textDrawingArea.method385(0, "From", j1, k1);
                  textDrawingArea.method385('\uffff', "From", j1 - 1, k1);
                  int var9 = k1 + textDrawingArea.getTextWidth("From ");
                  if(byte1 == 1) {
                     this.modIcons[0].method361(var9, j1 - 12);
                     var9 += 14;
                  }

                  if(byte1 == 2) {
                     this.modIcons[1].method361(var9, j1 - 12);
                     var9 += 14;
                  }
				  
				  if(byte1 == 3) {
                     this.modIcons[2].method361(var9, j1 - 12);
                     var9 += 14;
                  }

                  textDrawingArea.method385(0, s + ": " + this.chatMessages[j], j1, var9);
                  textDrawingArea.method385('\uffff', s + ": " + this.chatMessages[j], j1 - 1, var9);
                  ++i;
                  if(i >= 5) {
                     return;
                  }
               }

               if(k == 5 && this.privateChatMode < 2) {
                  j1 = 329 - i * 13;
                  textDrawingArea.method385(0, this.chatMessages[j], j1, 4);
                  textDrawingArea.method385('\uffff', this.chatMessages[j], j1 - 1, 4);
                  ++i;
                  if(i >= 5) {
                     return;
                  }
               }

               if(k == 6 && this.privateChatMode < 2) {
                  j1 = 329 - i * 13;
                  textDrawingArea.method385(0, "To " + s + ": " + this.chatMessages[j], j1, 4);
                  textDrawingArea.method385('\uffff', "To " + s + ": " + this.chatMessages[j], j1 - 1, 4);
                  ++i;
                  if(i >= 5) {
                     return;
                  }
               }
            }
         }

      }
   }

   public void pushMessage(String s, int i, String s1) {
      if(i == 0 && this.dialogID != -1) {
         this.aString844 = s;
         super.clickMode3 = 0;
      }

      if(this.backDialogID == -1) {
         this.inputTaken = true;
      }

      for(int j = 99; j > 0; --j) {
         this.chatTypes[j] = this.chatTypes[j - 1];
         this.chatNames[j] = this.chatNames[j - 1];
         this.chatMessages[j] = this.chatMessages[j - 1];
      }

      this.chatTypes[0] = i;
      this.chatNames[0] = s1;
      this.chatMessages[0] = s;
   }

   public void processTabClick() {
	   if(fullscreenInterfaceID != -1)
		   return;
      if(super.clickMode3 == 1) {
		if(gameframeVersion != 474){
         if(super.saveClickX >= 539 && super.saveClickX <= 573 && super.saveClickY >= 169 && super.saveClickY < 205 && this.tabInterfaceIDs[0] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 0;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 569 && super.saveClickX <= 599 && super.saveClickY >= 168 && super.saveClickY < 205 && this.tabInterfaceIDs[1] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 1;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 597 && super.saveClickX <= 627 && super.saveClickY >= 168 && super.saveClickY < 205 && this.tabInterfaceIDs[2] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 2;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 625 && super.saveClickX <= 669 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[3] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 3;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 666 && super.saveClickX <= 696 && super.saveClickY >= 168 && super.saveClickY < 205 && this.tabInterfaceIDs[4] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 4;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 694 && super.saveClickX <= 724 && super.saveClickY >= 168 && super.saveClickY < 205 && this.tabInterfaceIDs[5] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 5;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 722 && super.saveClickX <= 756 && super.saveClickY >= 169 && super.saveClickY < 205 && this.tabInterfaceIDs[6] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 6;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 540 && super.saveClickX <= 574 && super.saveClickY >= 466 && super.saveClickY < 502 && this.tabInterfaceIDs[7] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 7;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 572 && super.saveClickX <= 602 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[8] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 8;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 599 && super.saveClickX <= 629 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[9] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 9;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 627 && super.saveClickX <= 671 && super.saveClickY >= 467 && super.saveClickY < 502 && this.tabInterfaceIDs[10] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 10;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 669 && super.saveClickX <= 699 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[11] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 11;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 696 && super.saveClickX <= 726 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[12] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 12;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 724 && super.saveClickX <= 758 && super.saveClickY >= 466 && super.saveClickY < 502 && this.tabInterfaceIDs[13] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 13;
            this.tabAreaAltered = true;
         }
		}else{
		if(super.saveClickX >= 522 && super.saveClickX <= 559 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[0] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 0;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 560 && super.saveClickX <= 592 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[1] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 1;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 593 && super.saveClickX <= 625 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[2] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 2;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 626 && super.saveClickX <= 658 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[3] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 3;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 659 && super.saveClickX <= 691 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[4] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 4;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 692 && super.saveClickX <= 724 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[5] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 5;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 725 && super.saveClickX <= 762 && super.saveClickY >= 168 && super.saveClickY < 203 && this.tabInterfaceIDs[6] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 6;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 522 && super.saveClickX <= 559 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[7] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 7;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 560 && super.saveClickX <= 592 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[8] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 8;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 593 && super.saveClickX <= 625 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[9] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 9;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 626 && super.saveClickX <= 658 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[10] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 10;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 659 && super.saveClickX <= 691 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[11] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 11;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 692 && super.saveClickX <= 724 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[12] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 12;
            this.tabAreaAltered = true;
         }

         if(super.saveClickX >= 725 && super.saveClickX <= 762 && super.saveClickY >= 466 && super.saveClickY < 503 && this.tabInterfaceIDs[13] != -1) {
            this.needDrawTabArea = true;
            this.tabID = 13;
            this.tabAreaAltered = true;
         }
		}

         if(this.anInt1054 == this.tabID) {
            this.stream.createFrame(152);
            this.stream.writeWordBigEndian(this.tabID);
         }
      }

   }

   public void resetImageProducers2() {
      if(this.aRSImageProducer_1166 == null) {
         this.nullLoader();
         super.fullGameScreen = null;
         this.aRSImageProducer_1107 = null;
         this.aRSImageProducer_1108 = null;
         this.aRSImageProducer_1109 = null;
         this.aRSImageProducer_1110 = null;
         this.aRSImageProducer_1111 = null;
         this.aRSImageProducer_1112 = null;
         this.aRSImageProducer_1113 = null;
         this.aRSImageProducer_1114 = null;
         this.aRSImageProducer_1115 = null;
         this.aRSImageProducer_1166 = new RSImageProducer(chatBackWidth317, chatBackHeight317, this.getGameComponent());//479,96
		 if(gameframeVersion == 474)
			this.aRSImageProducer_1166 = new RSImageProducer(chatBackWidth474, chatBackHeight474, this.getGameComponent());
         this.aRSImageProducer_1164 = new RSImageProducer(172, 156, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         mapBack.method361(0, 0);
         this.aRSImageProducer_1163 = new RSImageProducer(invBackWidth317, 261, this.getGameComponent());
		 if(gameframeVersion == 474)
			this.aRSImageProducer_1163 = new RSImageProducer(invBackWidth474, 261, this.getGameComponent());
         this.aRSImageProducer_1165 = new RSImageProducer(512, 334, this.getGameComponent());
         DrawingArea.setAllPixelsToZero();
         this.aRSImageProducer_1123 = new RSImageProducer(496, 50, this.getGameComponent());//496,50
         this.aRSImageProducer_1124 = new RSImageProducer(269, 37, this.getGameComponent());
         this.aRSImageProducer_1125 = new RSImageProducer(249, 45, this.getGameComponent());
		 this.aRSImageProducer_oma1 = new RSImageProducer(22, 261, this.getGameComponent());
		 this.aRSImageProducer_oma2 = new RSImageProducer(37, 133, this.getGameComponent());
		 this.aRSImageProducer_oma3 = new RSImageProducer(57, 109, this.getGameComponent());
		 this.aRSImageProducer_oma4 = new RSImageProducer(553, 19, this.getGameComponent());
		 this.aRSImageProducer_oma5 = new RSImageProducer(17, 96, this.getGameComponent());
		 this.aRSImageProducer_oma6 = new RSImageProducer(48, 156, this.getGameComponent());//43,156
		 this.aRSImageProducer_oma7 = new RSImageProducer(34, 156, this.getGameComponent());//34,156
		 this.aRSImageProducer_oma8 = new RSImageProducer(765, 4, this.getGameComponent());
		 if(gameframeVersion == 474){
			this.aRSImageProducer_oma1 = new RSImageProducer(28, 261, this.getGameComponent());
			this.aRSImageProducer_1123 = new RSImageProducer(520, 27, this.getGameComponent());
			this.aRSImageProducer_oma5 = new RSImageProducer(7, 131, this.getGameComponent());
			this.aRSImageProducer_oma3 = new RSImageProducer(34, 121, this.getGameComponent());
			this.aRSImageProducer_oma4 = new RSImageProducer(547, 7, this.getGameComponent());
			this.aRSImageProducer_oma7 = new RSImageProducer(29, 156, this.getGameComponent());
			this.aRSImageProducer_oma2 = new RSImageProducer(31, 133, this.getGameComponent());
		}
         this.welcomeScreenRaised = true;
      }
   }

   public void method81(Sprite sprite, int j, int k) {
      int l = k * k + j * j;
      if(l > 4225 && l < 90000) {
         int i1 = this.minimapInt1 + this.minimapInt2 & 2047;
         int j1 = Model.modelIntArray1[i1];
         int k1 = Model.modelIntArray2[i1];
         j1 = j1 * 256 / (this.minimapInt3 + 256);
         k1 = k1 * 256 / (this.minimapInt3 + 256);
         int l1 = j * j1 + k * k1 >> 16;
         int i2 = j * k1 - k * j1 >> 16;
         double d = Math.atan2((double)l1, (double)i2);
         int j2 = (int)(Math.sin(d) * 63.0D);
         int k2 = (int)(Math.cos(d) * 57.0D);
         this.mapEdge.method353(83 - k2 - 20, d, 94 + j2 + 4 - 10);
      } else {
         this.markMinimap(sprite, k, j);
      }

   }
   
   private void rightClickChatButtons() {
		if(super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= 482 && super.mouseY <= 503) {
			menuActionName[1] = "View All";
			menuActionID[1] = 999;
			menuActionRow = 2;
		} else if(super.mouseX >= 71 && super.mouseX <= 127 && super.mouseY >= 482 && super.mouseY <= 503) {
			menuActionName[1] = "View Game";
			menuActionID[1] = 998;
			menuActionRow = 2;
		} else if(super.mouseX >= 137 && super.mouseX <= 193 && super.mouseY >= 482 && super.mouseY <= 503) {
			menuActionName[1] = "Hide Public";
			menuActionID[1] = 997;
			menuActionName[2] = "Off Public";
			menuActionID[2] = 996;
			menuActionName[3] = "Friends Public";
			menuActionID[3] = 995;
			menuActionName[4] = "On Public";
			menuActionID[4] = 994;
			menuActionName[5] = "View Public";
			menuActionID[5] = 993;
			menuActionRow = 6;
		} else if(super.mouseX >= 203 && super.mouseX <= 259 && super.mouseY >= 482 && super.mouseY <= 503) {
			menuActionName[1] = "Off Private";
			menuActionID[1] = 992;
			menuActionName[2] = "Friends Private";
			menuActionID[2] = 991;
			menuActionName[3] = "On Private";
			menuActionID[3] = 990;
			menuActionName[4] = "View Private";
			menuActionID[4] = 989;
			menuActionRow = 5;
		/*} else if(super.mouseX >= 269 && super.mouseX <= 325 && super.mouseY >= 482 && super.mouseY <= 503) {
			menuActionName[1] = "Off clan chat";
			menuActionID[1] = 1003;
			menuActionName[2] = "Friends clan chat";
			menuActionID[2] = 1002;
			menuActionName[3] = "On clan chat";
			menuActionID[3] = 1001;
			menuActionName[4] = "View clan chat";
			menuActionID[4] = 1000;
			menuActionRow = 5;*/
		} else if(super.mouseX >= 335 && super.mouseX <= 391 && super.mouseY >= 482 && super.mouseY <= 503) {
			menuActionName[1] = "Off Trade";
			menuActionID[1] = 987;
			menuActionName[2] = "Friends Trade";
			menuActionID[2] = 986;
			menuActionName[3] = "On Trade";
			menuActionID[3] = 985;
			menuActionName[4] = "View Trade";
			menuActionID[4] = 984;
			menuActionRow = 5;
		}
	}

   public void processRightClick() {
		if (activeInterfaceType != 0) {
            return;
        }
         this.menuActionName[0] = "Cancel";
         this.menuActionID[0] = 1107;
         this.menuActionRow = 1;
		 if (fullscreenInterfaceID != -1) {
            anInt886 = 0;
            anInt1315 = 0;
            buildInterfaceMenu(0, RSInterface.interfaceCache[fullscreenInterfaceID], super.mouseX, 0, super.mouseY, 0);
            if (anInt886 != anInt1026) {
                anInt1026 = anInt886;
            }
            if (anInt1315 != anInt1129) {
                anInt1129 = anInt1315;
            }
            return;
        }
         this.buildSplitPrivateChatMenu();
         this.anInt886 = 0;
		 anInt1315 = 0;
         if(super.mouseX > 4 && super.mouseY > 4 && super.mouseX < 516 && super.mouseY < 338) {
            if(this.openInterfaceID != -1) {
               this.buildInterfaceMenu(4, RSInterface.interfaceCache[this.openInterfaceID], super.mouseX, 4, super.mouseY, 0);
            } else {
               this.build3dScreenMenu();
            }
         }

         if(this.anInt886 != this.anInt1026) {
            this.anInt1026 = this.anInt886;
         }
		if (anInt1315 != anInt1129) {
            anInt1129 = anInt1315;
        }
         this.anInt886 = 0;
		 anInt1315 = 0;
		 if(gameframeVersion != 474){
         if(super.mouseX > 553 && super.mouseY > 205 && super.mouseX < 743 && super.mouseY < 466) {
            if(this.invOverlayInterfaceID != -1) {
               this.buildInterfaceMenu(553, RSInterface.interfaceCache[this.invOverlayInterfaceID], super.mouseX, 205, super.mouseY, 0);
            } else if(this.tabInterfaceIDs[this.tabID] != -1) {
               this.buildInterfaceMenu(553, RSInterface.interfaceCache[this.tabInterfaceIDs[this.tabID]], super.mouseX, 205, super.mouseY, 0);
            }
         }
		 }else{
		 if(super.mouseX > 553 && super.mouseY > 205 && super.mouseX < 743 && super.mouseY < 466) {
            if(this.invOverlayInterfaceID != -1) {
               this.buildInterfaceMenu(547, RSInterface.interfaceCache[this.invOverlayInterfaceID], super.mouseX, 205, super.mouseY, 0);
            } else if(this.tabInterfaceIDs[this.tabID] != -1) {
               this.buildInterfaceMenu(547, RSInterface.interfaceCache[this.tabInterfaceIDs[this.tabID]], super.mouseX, 205, super.mouseY, 0);
            }
         }
		 }

         if(this.anInt886 != this.anInt1048) {
            this.needDrawTabArea = true;
			tabAreaAltered = true;
            this.anInt1048 = this.anInt886;
         }
		 if (anInt1315 != anInt1044) {
            needDrawTabArea = true;
            tabAreaAltered = true;
            anInt1044 = anInt1315;
        }

         this.anInt886 = 0;
		 anInt1315 = 0;
		 if(gameframeVersion != 474){
         if(super.mouseX > 17 && super.mouseY > 357 && super.mouseX < 496 && super.mouseY < 453) {
            if(this.backDialogID != -1) {
               this.buildInterfaceMenu(17, RSInterface.interfaceCache[this.backDialogID], super.mouseX, 357, super.mouseY, 0);
            } else if(super.mouseY < 434 && super.mouseX < 426 && this.inputDialogState != 3) {
               this.buildChatAreaMenu(super.mouseY - 357);
            }
         }
		 }else{
		 if(super.mouseX > 7 && super.mouseY > 345 && super.mouseX < 512 && super.mouseY < 473) {
            if(this.backDialogID != -1) {
               this.buildInterfaceMenu(7, RSInterface.interfaceCache[this.backDialogID], super.mouseX, 360, super.mouseY, 0);
            } else if(super.mouseY < 459 && super.mouseX < 446 && this.inputDialogState != 3) {
               this.buildChatAreaMenu(super.mouseY - 380);
            }
         }
		 }

         if(this.backDialogID != -1 && this.anInt886 != this.anInt1039) {
            this.inputTaken = true;
            this.anInt1039 = this.anInt886;
         }
		 if (backDialogID != -1 && anInt1315 != anInt1500) {
            inputTaken = true;
            anInt1500 = anInt1315;
        }
		if(gameframeVersion == 474){
			if(super.mouseX > 4 && super.mouseY > 480 && super.mouseX < 516 && super.mouseY < 503)
				rightClickChatButtons();
		}

		if(!menuOpen)
			buildItemSearch(super.mouseY);
		
         boolean flag = false;

         while(!flag) {
            flag = true;

            for(int j = 0; j < this.menuActionRow - 1; ++j) {
               if(this.menuActionID[j] < 1000 && this.menuActionID[j + 1] > 1000) {
                  String s = this.menuActionName[j];
                  this.menuActionName[j] = this.menuActionName[j + 1];
                  this.menuActionName[j + 1] = s;
                  int k = this.menuActionID[j];
                  this.menuActionID[j] = this.menuActionID[j + 1];
                  this.menuActionID[j + 1] = k;
                  k = this.menuActionCmd2[j];
                  this.menuActionCmd2[j] = this.menuActionCmd2[j + 1];
                  this.menuActionCmd2[j + 1] = k;
                  k = this.menuActionCmd3[j];
                  this.menuActionCmd3[j] = this.menuActionCmd3[j + 1];
                  this.menuActionCmd3[j + 1] = k;
                  k = this.menuActionCmd1[j];
                  this.menuActionCmd1[j] = this.menuActionCmd1[j + 1];
                  this.menuActionCmd1[j + 1] = k;
                  flag = false;
               }
            }

      }
   }

   final void method56(int i, boolean bool, int music) {
      if(musicIsntNull() && music != this.nextSong) {
         this.nextSong = music;
         this.onDemandFetcher.method558(2, this.nextSong);
         musicVolume2 = i;
         anInt139 = -1;
         aBoolean995 = true;
         anInt116 = -1;
      }
   }

   public int method83(int i, int j, int k) {
      int l = 256 - k;
      return ((i & 16711935) * l + (j & 16711935) * k & -16711936) + ((i & '\uff00') * l + (j & '\uff00') * k & 16711680) >> 8;
   }

   /*
    j20 = this.baseX - this.anInt1036;
               i23 = this.baseY - this.anInt1037;
               this.anInt1036 = this.baseX;
               this.anInt1037 = this.baseY;

               for(l25 = 0; l25 < 16384; ++l25) {
                  NPC var43 = this.npcArray[l25];
                  if(var43 != null) {
                     for(i30 = 0; i30 < 10; ++i30) {
                        var43.smallX[i30] -= j20;
                        var43.smallY[i30] -= i23;
                     }

                     var43.x -= j20 * 128;
                     var43.y -= i23 * 128;
                  }
               }

               for(l25 = 0; l25 < this.maxPlayers; ++l25) {
                  Player var44 = this.playerArray[l25];
                  if(var44 != null) {
                     for(i30 = 0; i30 < 10; ++i30) {
                        var44.smallX[i30] -= j20;
                        var44.smallY[i30] -= i23;
                     }

                     var44.x -= j20 * 128;
                     var44.y -= i23 * 128;
                  }
               }

               this.aBoolean1080 = true;
               byte var46 = 0;
               byte var42 = 104;
               byte var36 = 1;
               if(j20 < 0) {
                  var46 = 103;
                  var42 = -1;
                  var36 = -1;
               }

               byte var33 = 0;
               byte var35 = 104;
               byte var40 = 1;
               if(i23 < 0) {
                  var33 = 103;
                  var35 = -1;
                  var40 = -1;
               }

               for(class30_sub1_1 = var46; class30_sub1_1 != var42; class30_sub1_1 += var36) {
                  for(l33 = var33; l33 != var35; l33 += var40) {
                     int i34 = class30_sub1_1 + j20;
                     int j34 = l33 + i23;

                     for(int k34 = 0; k34 < 4; ++k34) {
                        if(i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104) {
                           this.groundArray[k34][class30_sub1_1][l33] = this.groundArray[k34][i34][j34];
                        } else {
                           this.groundArray[k34][class30_sub1_1][l33] = null;
                        }
                     }
                  }
               }

               for(Class30_Sub1 var41 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); var41 != null; var41 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
                  var41.anInt1297 -= j20;
                  var41.anInt1298 -= i23;
                  if(var41.anInt1297 < 0 || var41.anInt1298 < 0 || var41.anInt1297 >= 104 || var41.anInt1298 >= 104) {
                     var41.unlink();
                  }
               }
    */
   
   public void loadMap_Fake(int var20, int j15) {
	   int j20, i23, l25, j28, i30;
	   
	   for(int var16 = 0; var16 < 4; ++var16) {
           for(int _ex2 = 0; _ex2 < 104; ++_ex2) {
              for(int k3 = 0; k3 < 104; ++k3) {
                 this.groundArray[var16][_ex2][k3] = null;
              }
           }
        }
	   
	   
	   this.npcCount = 0;
	   for(int var16 = 0; var16 < 16384; ++var16) {
           this.npcArray[var16] = null;
        }
	   
	   j20 = this.baseX - this.anInt1036;
       i23 = this.baseY - this.anInt1037;
	   for(Class30_Sub1 var41 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); var41 != null; var41 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
           var41.anInt1297 -= j20;
           var41.anInt1298 -= i23;
           if(var41.anInt1297 < 0 || var41.anInt1298 < 0 || var41.anInt1297 >= 104 || var41.anInt1298 >= 104) {
              var41.unlink();
           }
        }
	   
	   this.anInt1069 = var20;
       this.anInt1070 = j15;
       this.baseX = (this.anInt1069 - 6) * 8;
       this.baseY = (this.anInt1070 - 6) * 8;
       this.aBoolean1141 = (this.anInt1069 / 8 == 48 || this.anInt1069 / 8 == 49) && this.anInt1070 / 8 == 48;
       if(this.anInt1069 / 8 == 48 && this.anInt1070 / 8 == 148) {
          this.aBoolean1141 = true;
       }

       this.loadingStage = 1;
       this.aLong824 = System.currentTimeMillis();
       this.aRSImageProducer_1165.initDrawingArea();
       this.aTextDrawingArea_1271.drawText(0, "Loading - please wait.", 151, 257);
       this.aTextDrawingArea_1271.drawText(16777215, "Loading - please wait.", 150, 256);
       this.aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
          j20 = 0;

          for(i23 = (this.anInt1069 - 6) / 8; i23 <= (this.anInt1069 + 6) / 8; ++i23) {
             for(l25 = (this.anInt1070 - 6) / 8; l25 <= (this.anInt1070 + 6) / 8; ++l25) {
                ++j20;
             }
          }

          this.aByteArrayArray1183 = new byte[j20][];
          this.aByteArrayArray1247 = new byte[j20][];
          this.anIntArray1234 = new int[j20];
          this.anIntArray1235 = new int[j20];
          this.anIntArray1236 = new int[j20];
          j20 = 0;

          for(i23 = (this.anInt1069 - 6) / 8; i23 <= (this.anInt1069 + 6) / 8; ++i23) {
             for(l25 = (this.anInt1070 - 6) / 8; l25 <= (this.anInt1070 + 6) / 8; ++l25) {
                this.anIntArray1234[j20] = (i23 << 8) + l25;
                if(this.aBoolean1141 && (l25 == 49 || l25 == 149 || l25 == 147 || i23 == 50 || i23 == 49 && l25 == 47)) {
                   this.anIntArray1235[j20] = -1;
                   this.anIntArray1236[j20] = -1;
                   ++j20;
                } else {
                   j28 = this.anIntArray1235[j20] = this.onDemandFetcher.method562(0, l25, i23);
                   if(j28 != -1) {
                      this.onDemandFetcher.method558(3, j28);
                   }

                   i30 = this.anIntArray1236[j20] = this.onDemandFetcher.method562(1, l25, i23);
                   if(i30 != -1) {
                      this.onDemandFetcher.method558(3, i30);
                   }

                   ++j20;
                }
             }
       }
   }
   
   public void login_Fake(){
	   
	   ItemSpawn.loadSpawns();
	   NpcSpawn.loadSpawns();
	   
	   int var16;
	   int _ex2;
	   int rights = 12;
	   if(rights >= 10){
			this.myDonor = true;
			rights -= 10;
	   }else
			this.myDonor = false;
	   this.myPrivilege = rights;
	   boolean allowDonorMenu = (this.myDonor || this.myPrivilege > 0) ? true : false;
	   Jframe.donatorMenu.setEnabled(allowDonorMenu);
	   if(!allowDonorMenu){
			gameframeVersion = 317;
			updateGameframeSprites();
		}
       flagged = false;
       this.aLong1220 = 0L;
       this.anInt1022 = 0;
       this.mouseDetection.coordsIndex = 0;
       super.awtFocus = true;
       this.aBoolean954 = true;
       this.loggedIn = true;
       this.stream.currentOffset = 0;
       this.inStream.currentOffset = 0;
       this.pktType = -1;
       this.anInt841 = -1;
       this.anInt842 = -1;
       this.anInt843 = -1;
       this.pktSize = 0;
       this.anInt1009 = 0;
       this.anInt1104 = 0;
       this.anInt1011 = 0;
       this.anInt855 = 0;
       this.menuActionRow = 0;
       this.menuOpen = false;
       super.idleTime = 0;

       for(var16 = 0; var16 < 100; ++var16) {
          this.chatMessages[var16] = null;
       }

       this.itemSelected = 0;
       this.spellSelected = 0;
       this.loadingStage = 0;
       this.soundCount = 0;
       this.anInt1278 = (int)(Math.random() * 100.0D) - 50;
       this.anInt1131 = (int)(Math.random() * 110.0D) - 55;
       this.anInt896 = (int)(Math.random() * 80.0D) - 40;
       this.minimapInt2 = (int)(Math.random() * 120.0D) - 60;
       this.minimapInt3 = (int)(Math.random() * 30.0D) - 20;
       this.minimapInt1 = (int)(Math.random() * 20.0D) - 10 & 2047;
       this.anInt1021 = 0;
       this.anInt985 = -1;
       this.destX = 0;
       this.destY = 0;
       this.playerCount = 0;
       this.npcCount = 0;

       for(var16 = 0; var16 < this.maxPlayers; ++var16) {
          this.playerArray[var16] = null;
          this.aStreamArray895s[var16] = null;
       }

       for(var16 = 0; var16 < 16384; ++var16) {
          this.npcArray[var16] = null;
       }

       myPlayer = this.playerArray[this.myPlayerIndex] = new Player();
       this.aClass19_1013.removeAll();
       this.aClass19_1056.removeAll();

       this.aClass19_1179 = new NodeList();
       this.anInt900 = 0;
       this.friendsCount = 0;
       this.dialogID = -1;
	   fullscreenInterfaceID = -1;
       this.backDialogID = -1;
       this.openInterfaceID = -1;
       this.invOverlayInterfaceID = -1;
       this.anInt1018 = -1;
       this.aBoolean1149 = false;
       this.tabID = 3;
       this.inputDialogState = 0;
       this.menuOpen = false;
       this.messagePromptRaised = false;
       this.aString844 = null;
       this.anInt1055 = 0;
       this.anInt1054 = -1;
       this.aBoolean1047 = true;
       this.method45();

       for(var16 = 0; var16 < 5; ++var16) {
          this.anIntArray990[var16] = 0;
       }

       for(var16 = 0; var16 < 5; ++var16) {
          this.atPlayerActions[var16] = null;
          this.atPlayerArray[var16] = false;
       }

       anInt1175 = 0;
       anInt1134 = 0;
       anInt986 = 0;
       anInt1288 = 0;
       anInt924 = 0;
       anInt1188 = 0;
       anInt1155 = 0;
       anInt1226 = 0;
       this.resetImageProducers2();
       this.anIntArray894[0] = 2047;
       
       loadLastSaveLocation();
       
       myPlayer.combatLevel = 9999;
   }
   
   int startX = 0;
   int startY = 0;
   
   NPC selectedNpc = null;
   Item selectedItem = null;
   
   public void loadPos(int x, int y, int z){
	   this.plane = z;
	   loadMap_Fake((x >> 3), (y >> 3));
       myPlayer.setPos(x-this.baseX, y-this.baseY, false);
       startX = myPlayer.smallX[0];
       startY = myPlayer.smallY[0];
       //spawnFakeItem(1038, 1, 3217, 3218, this.plane);
       //spawnFakeItem(3140, 1, 3217, 3219, this.plane);
       //spawnFakeNpc(0, 3217, 3218, this.plane, EAST);
       //spawnFakeNpc(9, 3217, 3219, this.plane);
       
       for(ItemSpawn item : ItemSpawn.listOfItems){
    	   if(item == null)
    		   continue;
    	   spawnFakeItem(item.getId(), item.getAmount(), item.getPosition().getX(), item.getPosition().getY(), item.getPosition().getZ(), item);
       }
       
       for(NpcSpawn npc : NpcSpawn.listOfNpcs){
    	   if(npc == null)
    		   continue;
    	   spawnFakeNpc(npc.getId(), npc.getPosition().getX(), npc.getPosition().getY(), npc.getPosition().getZ(), npc.getWalkType(), npc);
       }
       
       for(ObjectSpawn object : ObjectSpawn.listOfObjects){
    	   if(object == null)
    		   continue;
    	   spawnFakeObject(object.getPosition().getX(), object.getPosition().getY(), object.getId(), object.getFace(), object.getType(), object.getPosition().getZ(), object);
       }
       
   }
   
   public void removeItem(){
	   if(selectedItem == null)
		   return;
	   ItemSpawn iSpawn = selectedItem.itemSpawn;
	   this.anInt1268 = iSpawn.getPosition().getX() - 8 * (((startX+this.baseX) >> 3)-6);
	   this.anInt1269 = iSpawn.getPosition().getY() - 8 * (((startY+this.baseY) >> 3)-6);
	   int j11 = this.anInt1268 + (0 >> 4 & 7);
       int k13 = this.anInt1269 + (0 & 7);
       if (!(j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104)){
    	   this.pushMessage("Can't remove item: out of range!", 0, "");
    	   return;
       }
	   selectedItem.unlink();
	   this.groundArray[iSpawn.getPosition().getZ()][iSpawn.getPosition().getX()-this.baseX][iSpawn.getPosition().getY()-this.baseY] = null;
       if(this.plane == iSpawn.getPosition().getZ())
    	   this.spawnGroundItem(iSpawn.getPosition().getX()-this.baseX, iSpawn.getPosition().getY()-this.baseY);
	   ItemSpawn.listOfItems.remove(iSpawn);
	   selectedItem = null;
   }
   
   public void removeNpc(){
	   if(selectedNpc == null)
		   return;
	   NpcSpawn nSpawn = selectedNpc.npcSpawn;
	   this.anInt1268 = nSpawn.getPosition().getX() - 8 * (((startX+this.baseX) >> 3)-6);
	   this.anInt1269 = nSpawn.getPosition().getY() - 8 * (((startY+this.baseY) >> 3)-6);
	   int j11 = this.anInt1268 + (0 >> 4 & 7);
       int k13 = this.anInt1269 + (0 & 7);
       if (!(j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104)){
    	   this.pushMessage("Can't remove npc: out of range!", 0, "");
    	   return;
       }
	   selectedNpc.desc = null;
	   NpcSpawn.listOfNpcs.remove(nSpawn);
	   selectedNpc = null;
   }
   
   public void spawnFakeObject(int x, int y, int objectId, int face, int type, int height, ObjectSpawn objectS) {		
	   this.anInt1268 = x - 8 * (((startX+this.baseX) >> 3)-6);
		this.anInt1269 = y - 8 * (((startY+this.baseY) >> 3)-6);
	    int x2 = this.anInt1268 + (0 >> 4 & 7);
	    int y2 = this.anInt1269 + (0 & 7);
		int i15 = 40 >> 2;
		int l17 = anIntArray1177[i15];
		if(y2 > 0 && y2 < 103 && x2 > 0 && x2 < 103) {
			method130(-1, objectId, face, l17, y2, type, height, x2, 0);
		}
	}
   
	public Item spawnFakeItem(int id, int amount, int x, int y, int z, ItemSpawn itemS) {
		this.anInt1268 = x - 8 * (((startX+this.baseX) >> 3)-6);
		this.anInt1269 = y - 8 * (((startY+this.baseY) >> 3)-6);
		int j11 = this.anInt1268 + (0 >> 4 & 7);
        int k13 = this.anInt1269 + (0 & 7);
		int i3 = id;
		int l5 = amount;
		Item var30 = null;
		if (j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104) {
			var30 = new Item();
			var30.ID = i3;
			var30.anInt1559 = l5;
			var30.itemSpawn = itemS;
			if (this.groundArray[z][j11][k13] == null) {
				this.groundArray[z][j11][k13] = new NodeList();
			}
			this.groundArray[z][j11][k13].insertHead(var30);
			this.spawnGroundItem(j11, k13);
		}
		return var30;
	}
	
	int NORTH = 2;
	int SOUTH = 0;
	int WALK = 1;
	int WEST = 5;
	int EAST = 4;
	
	public NPC spawnFakeNpc(int id, int x , int y, int z, int face, NpcSpawn npcS){
		if(z != this.plane)
			return null;
		
		this.anInt1268 = x - 8 * (((startX+this.baseX) >> 3)-6);
		this.anInt1269 = y - 8 * (((startY+this.baseY) >> 3)-6);
		int j11 = this.anInt1268 + (0 >> 4 & 7);
        int k13 = this.anInt1269 + (0 & 7);
        
        if (!(j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104))
        	return null;
		
		int k = this.npcCount;
        if(k != 16383) {
           if(this.npcArray[k] == null) {
              this.npcArray[k] = new NPC();
           }

           NPC npc = this.npcArray[k];
           this.npcIndices[this.npcCount++] = k;
           npc.npcSpawn = npcS;
           npc.anInt1537 = loopCycle;
           /*int l = stream.readBits(5);
           if(l > 15) {
              l -= 32;
           }

           int i1 = stream.readBits(5);
           if(i1 > 15) {
              i1 -= 32;
           }

           int j1 = stream.readBits(1);*/
           npc.desc = EntityDef.forID(id);
           int k1 = 1;
           if(k1 == 1) {
              this.anIntArray894[this.anInt893++] = k;
           }

           npc.anInt1540 = npc.desc.aByte68;
           npc.anInt1504 = npc.desc.anInt79;
           npc.anInt1554 = npc.desc.anInt67;
           npc.anInt1555 = npc.desc.anInt58;
           npc.anInt1556 = npc.desc.anInt83;
           npc.anInt1557 = npc.desc.anInt55;
           npc.anInt1511 = npc.desc.anInt77;

           npc.setPos(j11, k13, false);
           
           int yO = 0;
           int xO = 0 + (npc.anInt1540/2);
           if(face == SOUTH)
        	   yO = -1;
           if(face == NORTH)
        	   yO = 1;
           if(face == WEST)
        	   xO = -1;
           if(face == EAST)
        	   xO = 1;
           npc.anInt1538 = x * 2 + 1 + xO;
           npc.anInt1539 = y * 2 + 1 + yO;
           return npc;
        }
        return null;
	}
	
	public void loadLastSaveLocation() {
		int x = 3217;
		int y = 3218;
		int z = 0;
		try {
			if(FileOperations.FileExists("./Position.dat")){
				byte abyte2[] = FileOperations.ReadFile("./Position.dat");
				Stream stream2 = new Stream(abyte2);
				x = stream2.readUnsignedWord();
				y = stream2.readUnsignedWord();
				z = stream2.readUnsignedByte();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadPos(x, y, z);
	}
	
	public void saveLocation() {
		File f = new File("./Position.dat");
		f.delete();
        try {
			Packer stream2 = new Packer(new FileOutputStream("./Position.dat"));
			stream2.writeShort(myPlayer.smallX[0] + this.baseX);
			stream2.writeShort(myPlayer.smallY[0] + this.baseY);
			stream2.writeUnsignedByte(this.plane);
			stream2.close();
        } catch (Exception e) {

        }
	}
   
   public void login(String s, String s1, boolean flag) {
      SignLink.errorname = s;

      try {
         if(!flag) {
            this.loginMessage1 = "";
            this.loginMessage2 = "Connecting to server...";
            this.drawLoginScreen(true);
         }

         this.socketStream = new RSSocket(this, this.openSocket((isEasyOrionClient ? 5555 : 43594) + portOff));
         long _ex = TextClass.longForName(s);
         int i = (int)(_ex >> 16 & 31L);
         this.stream.currentOffset = 0;
         this.stream.writeWordBigEndian(14);
         this.stream.writeWordBigEndian(i);
         this.socketStream.queueBytes(2, this.stream.buffer);

         int k;
         for(k = 0; k < 8; ++k) {
            this.socketStream.read();
         }

         k = this.socketStream.read();
         int i1 = k;
         int _ex2;
         if(k == 0) {
            this.socketStream.flushInputStream(this.inStream.buffer, 8);
            this.inStream.currentOffset = 0;
            this.aLong1215 = this.inStream.readQWord();
            int[] _ex1 = new int[]{(int)(Math.random() * 99999999D), (int)(Math.random() * 99999999D), (int)(this.aLong1215 >> 32), (int)this.aLong1215};
            this.stream.currentOffset = 0;
            this.stream.writeWordBigEndian(10);
            this.stream.writeDWord(_ex1[0]);
            this.stream.writeDWord(_ex1[1]);
            this.stream.writeDWord(_ex1[2]);
            this.stream.writeDWord(_ex1[3]);
            this.stream.writeDWord(SignLink.uid);
            this.stream.writeString(s);
            this.stream.writeString(s1);
            this.stream.doKeys();
            this.aStream_847.currentOffset = 0;
            if(flag) {
               this.aStream_847.writeWordBigEndian(18);
            } else {
               this.aStream_847.writeWordBigEndian(16);
            }

            this.aStream_847.writeWordBigEndian(this.stream.currentOffset + 36 + 1 + 1 + 2 + 6);
            this.aStream_847.writeWordBigEndian(255);
            this.aStream_847.writeWord(CLIENT_VERSION);
            this.aStream_847.writeWordBigEndian(lowMem?1:0);
			for(int mac = 0; mac < 6; mac++) {
				byte[] MACa = getMACaddress();
               this.aStream_847.writeSignedByte(MACa[mac]);
            }
            for(_ex2 = 0; _ex2 < 9; ++_ex2) {
               this.aStream_847.writeDWord(this.expectedCRCs[_ex2]);
            }

            this.aStream_847.writeBytes(this.stream.buffer, this.stream.currentOffset, 0);
            this.stream.encryption = new ISAACRandomGen(_ex1);

            for(_ex2 = 0; _ex2 < 4; ++_ex2) {
               _ex1[_ex2] += 50;
            }

            this.encryption = new ISAACRandomGen(_ex1);
            this.socketStream.queueBytes(this.aStream_847.currentOffset, this.aStream_847.buffer);
            k = this.socketStream.read();
         }

         if(k == 1) {
            try {
               Thread.sleep(2000L);
            } catch (Exception var12) {
               ;
            }

            this.login(s, s1, flag);
         } else {
            int var16;
            if(k == 2) {
			   int rights = this.socketStream.read();
			   if(rights >= 10){
					this.myDonor = true;
					rights -= 10;
			   }else
					this.myDonor = false;
			   this.myPrivilege = rights;
			   boolean allowDonorMenu = (this.myDonor || this.myPrivilege > 0) ? true : false;
			   Jframe.donatorMenu.setEnabled(allowDonorMenu);
			   if(!allowDonorMenu){
					gameframeVersion = 317;
					updateGameframeSprites();
				}
               flagged = this.socketStream.read() == 1;
               this.aLong1220 = 0L;
               this.anInt1022 = 0;
               this.mouseDetection.coordsIndex = 0;
               super.awtFocus = true;
               this.aBoolean954 = true;
               this.loggedIn = true;
               this.stream.currentOffset = 0;
               this.inStream.currentOffset = 0;
               this.pktType = -1;
               this.anInt841 = -1;
               this.anInt842 = -1;
               this.anInt843 = -1;
               this.pktSize = 0;
               this.anInt1009 = 0;
               this.anInt1104 = 0;
               this.anInt1011 = 0;
               this.anInt855 = 0;
               this.menuActionRow = 0;
               this.menuOpen = false;
               super.idleTime = 0;

               for(var16 = 0; var16 < 100; ++var16) {
                  this.chatMessages[var16] = null;
               }

               this.itemSelected = 0;
               this.spellSelected = 0;
               this.loadingStage = 0;
               this.soundCount = 0;
               this.anInt1278 = (int)(Math.random() * 100.0D) - 50;
               this.anInt1131 = (int)(Math.random() * 110.0D) - 55;
               this.anInt896 = (int)(Math.random() * 80.0D) - 40;
               this.minimapInt2 = (int)(Math.random() * 120.0D) - 60;
               this.minimapInt3 = (int)(Math.random() * 30.0D) - 20;
               this.minimapInt1 = (int)(Math.random() * 20.0D) - 10 & 2047;
               this.anInt1021 = 0;
               this.anInt985 = -1;
               this.destX = 0;
               this.destY = 0;
               this.playerCount = 0;
               this.npcCount = 0;

               for(var16 = 0; var16 < this.maxPlayers; ++var16) {
                  this.playerArray[var16] = null;
                  this.aStreamArray895s[var16] = null;
               }

               for(var16 = 0; var16 < 16384; ++var16) {
                  this.npcArray[var16] = null;
               }

               myPlayer = this.playerArray[this.myPlayerIndex] = new Player();
               this.aClass19_1013.removeAll();
               this.aClass19_1056.removeAll();

               for(var16 = 0; var16 < 4; ++var16) {
                  for(_ex2 = 0; _ex2 < 104; ++_ex2) {
                     for(int k3 = 0; k3 < 104; ++k3) {
                        this.groundArray[var16][_ex2][k3] = null;
                     }
                  }
               }

               this.aClass19_1179 = new NodeList();
               this.anInt900 = 0;
               this.friendsCount = 0;
               this.dialogID = -1;
			   fullscreenInterfaceID = -1;
               this.backDialogID = -1;
               this.openInterfaceID = -1;
               this.invOverlayInterfaceID = -1;
               this.anInt1018 = -1;
               this.aBoolean1149 = false;
               this.tabID = 3;
               this.inputDialogState = 0;
               this.menuOpen = false;
               this.messagePromptRaised = false;
               this.aString844 = null;
               this.anInt1055 = 0;
               this.anInt1054 = -1;
               this.aBoolean1047 = true;
               this.method45();

               for(var16 = 0; var16 < 5; ++var16) {
                  this.anIntArray990[var16] = 0;
               }

               for(var16 = 0; var16 < 5; ++var16) {
                  this.atPlayerActions[var16] = null;
                  this.atPlayerArray[var16] = false;
               }

               anInt1175 = 0;
               anInt1134 = 0;
               anInt986 = 0;
               anInt1288 = 0;
               anInt924 = 0;
               anInt1188 = 0;
               anInt1155 = 0;
               anInt1226 = 0;
               this.resetImageProducers2();
            } else if(k == 3) {
               this.loginMessage1 = "";
               this.loginMessage2 = "Invalid username or password.";
            /*} else if(k == 4) {
               this.loginMessage1 = "Your account has been disabled.";
               this.loginMessage2 = "Please check your message-center for details.";*/
			} else if(k == 4) {
			//this.inStream.readUnsignedWord();
			   this.inStream.currentOffset = 0;
               this.socketStream.flushInputStream(this.inStream.buffer, 2);
			   int hours = this.inStream.readUnsignedWord();
               this.loginMessage1 = "Your account has been disabled.";
			   if(hours > 0)
					this.loginMessage2 = "Your ban will be lifted in "+hours+" hours.";
				else
					this.loginMessage2 = "Your ban will last forever.";
            } else if(k == 5) {
               this.loginMessage1 = "Your account is already logged in.";
               this.loginMessage2 = "Try again in 60 secs...";
            } else if(k == 6) {
               this.loginMessage1 = NAME+" has been updated!";
			   this.loginMessage2 = "Please download the latest client.";
               //this.loginMessage2 = "Please reload this page.";
            } else if(k == 7) {
               this.loginMessage1 = "This world is full.";
               this.loginMessage2 = "Please use a different world.";
            } else if(k == 8) {
               this.loginMessage1 = "Unable to connect.";
               this.loginMessage2 = "Login server offline.";
            } else if(k == 9) {
               this.loginMessage1 = "Login limit exceeded.";
               this.loginMessage2 = "Too many connections from your address.";
            } else if(k == 10) {
               this.loginMessage1 = "Unable to connect.";
               this.loginMessage2 = "Bad session id.";
            } else if(k == 11) {
               this.loginMessage1 = "Login server rejected session.";
               this.loginMessage2 = "Please try again.";
			} else if(k == 12) {
               this.loginMessage1 = "You need a donator account to login to this world.";
               this.loginMessage2 = "Please donate, or use a different world.";
			} else if(k == 29) {
               this.loginMessage1 = "Your account has been created.";
               this.loginMessage2 = "Only donators are able to play currently.";   
            } else if(k == 30) {
               this.loginMessage1 = "Only staff are allowed to play right now.";
               this.loginMessage2 = "Please login using a staff account.";
            } else if(k == 13) {
               this.loginMessage1 = "Could not complete login.";
               this.loginMessage2 = "Please try using a different world.";
            } else if(k == 14) {
               this.loginMessage1 = "The server is being updated.";
               this.loginMessage2 = "Please wait 1 minute and try again.";
            } else if(k == 15) {
               this.loggedIn = true;
               this.stream.currentOffset = 0;
               this.inStream.currentOffset = 0;
               this.pktType = -1;
               this.anInt841 = -1;
               this.anInt842 = -1;
               this.anInt843 = -1;
               this.pktSize = 0;
               this.anInt1009 = 0;
               this.anInt1104 = 0;
               this.menuActionRow = 0;
               this.menuOpen = false;
               this.aLong824 = System.currentTimeMillis();
            } else if(k == 16) {
               this.loginMessage1 = "Login attempts exceeded.";
               this.loginMessage2 = "Please wait 1 minute and try again.";
            } else if(k == 17) {
               this.loginMessage1 = "You are standing in a members-only area.";
               this.loginMessage2 = "To play on this world move to a free area first";
            } else if(k == 20) {
               this.loginMessage1 = "Invalid loginserver requested";
               this.loginMessage2 = "Please try using a different world.";
            } else if(k != 21) {
               if(k == -1) {
                  if(i1 == 0) {
                     if(this.loginFailures < 2) {
                        try {
                           Thread.sleep(2000L);
                        } catch (Exception var14) {
                           ;
                        }

                        ++this.loginFailures;
                        this.login(s, s1, flag);
                     } else {
                        this.loginMessage1 = "Error connecting to server.";
                        this.loginMessage2 = "Please try again in a little while.";
                     }
                  } else {
                     this.loginMessage1 = "No response from server";
                     this.loginMessage2 = "Please try using a different world.";
                  }
               } else {
                  //System.out.println("response:" + k);
                  this.loginMessage1 = "Unexpected server response";
                  this.loginMessage2 = "Please try using a different world.";
               }
            } else {
               for(var16 = this.socketStream.read(); var16 >= 0; --var16) {
                  this.loginMessage1 = "You have only just left another world";
                  this.loginMessage2 = "Your profile will be transferred in: " + var16 + " seconds";
                  this.drawLoginScreen(true);

                  try {
                     Thread.sleep(1000L);
                  } catch (Exception var13) {
                     ;
                  }
               }

               this.login(s, s1, flag);
            }
         }
      } catch (IOException var15) {
          var15.printStackTrace();
         this.loginMessage1 = "";
         this.loginMessage2 = "Error connecting to server.";
      }
   }

   public boolean doWalkTo(int i, int j, int k, int i1, int j1, int k1, int l1, int i2, int j2, boolean flag, int k2) {
      byte byte0 = 104;
      byte byte1 = 104;

      if(editMode){
    	  if (i == 0) {//click on ground
				int x = k2+this.baseX;
				int y = i2+this.baseY;
				this.pushMessage("Position: "+x+","+y+","+this.plane, 0, "");
				//removeNpc();
				//removeItem();
				Jframe.itemPosition.setText(x+","+y+","+this.plane);
				Jframe.npcPosition.setText(x+","+y+","+this.plane);
				//spawnFakeItem(3140, 1, x, y, this.plane, new ItemSpawn(3140, 1, new Position(x, y, this.plane), false));
			    //spawnFakeNpc(9, x, y, this.plane, WALK, new NpcSpawn(9, WALK, new Position(x, y, this.plane), false));
				//spawnFakeObject(x, y, 2213, 0, 10, this.plane, new ObjectSpawn(2213, 0, 10, new Position(x, y, this.plane)));
			}
      }
      
      int j3;
      int k3;
      for(j3 = 0; j3 < byte0; ++j3) {
         for(k3 = 0; k3 < byte1; ++k3) {
            this.anIntArrayArray901[j3][k3] = 0;
            this.anIntArrayArray825[j3][k3] = 99999999;
         }
      }

      j3 = j2;
      k3 = j1;
      this.anIntArrayArray901[j2][j1] = 99;
      this.anIntArrayArray825[j2][j1] = 0;
      byte l3 = 0;
      int i4 = 0;
      this.bigX[l3] = j2;
      int var26 = l3 + 1;
      this.bigY[l3] = j1;
      boolean flag1 = false;
      int j4 = this.bigX.length;
      int[][] ai = this.aClass11Array1230[this.plane].anIntArrayArray294;

      int l5;
      while(i4 != var26) {
         j3 = this.bigX[i4];
         k3 = this.bigY[i4];
         i4 = (i4 + 1) % j4;
         if(j3 == k2 && k3 == i2) {
            flag1 = true;
            break;
         }

         if(i1 != 0) {
            if((i1 < 5 || i1 == 10) && this.aClass11Array1230[this.plane].method219(k2, j3, k3, j, i1 - 1, i2)) {
               flag1 = true;
               break;
            }

            if(i1 < 10 && this.aClass11Array1230[this.plane].method220(k2, i2, k3, i1 - 1, j, j3)) {
               flag1 = true;
               break;
            }
         }

         if(k1 != 0 && k != 0 && this.aClass11Array1230[this.plane].method221(i2, k2, j3, k, l1, k1, k3)) {
            flag1 = true;
            break;
         }

         l5 = this.anIntArrayArray825[j3][k3] + 1;
         if(j3 > 0 && this.anIntArrayArray901[j3 - 1][k3] == 0 && (ai[j3 - 1][k3] & 19398920) == 0) {
            this.bigX[var26] = j3 - 1;
            this.bigY[var26] = k3;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3 - 1][k3] = 2;
            this.anIntArrayArray825[j3 - 1][k3] = l5;
         }

         if(j3 < byte0 - 1 && this.anIntArrayArray901[j3 + 1][k3] == 0 && (ai[j3 + 1][k3] & 19399040) == 0) {
            this.bigX[var26] = j3 + 1;
            this.bigY[var26] = k3;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3 + 1][k3] = 8;
            this.anIntArrayArray825[j3 + 1][k3] = l5;
         }

         if(k3 > 0 && this.anIntArrayArray901[j3][k3 - 1] == 0 && (ai[j3][k3 - 1] & 19398914) == 0) {
            this.bigX[var26] = j3;
            this.bigY[var26] = k3 - 1;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3][k3 - 1] = 1;
            this.anIntArrayArray825[j3][k3 - 1] = l5;
         }

         if(k3 < byte1 - 1 && this.anIntArrayArray901[j3][k3 + 1] == 0 && (ai[j3][k3 + 1] & 19398944) == 0) {
            this.bigX[var26] = j3;
            this.bigY[var26] = k3 + 1;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3][k3 + 1] = 4;
            this.anIntArrayArray825[j3][k3 + 1] = l5;
         }

         if(j3 > 0 && k3 > 0 && this.anIntArrayArray901[j3 - 1][k3 - 1] == 0 && (ai[j3 - 1][k3 - 1] & 19398926) == 0 && (ai[j3 - 1][k3] & 19398920) == 0 && (ai[j3][k3 - 1] & 19398914) == 0) {
            this.bigX[var26] = j3 - 1;
            this.bigY[var26] = k3 - 1;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3 - 1][k3 - 1] = 3;
            this.anIntArrayArray825[j3 - 1][k3 - 1] = l5;
         }

         if(j3 < byte0 - 1 && k3 > 0 && this.anIntArrayArray901[j3 + 1][k3 - 1] == 0 && (ai[j3 + 1][k3 - 1] & 19399043) == 0 && (ai[j3 + 1][k3] & 19399040) == 0 && (ai[j3][k3 - 1] & 19398914) == 0) {
            this.bigX[var26] = j3 + 1;
            this.bigY[var26] = k3 - 1;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3 + 1][k3 - 1] = 9;
            this.anIntArrayArray825[j3 + 1][k3 - 1] = l5;
         }

         if(j3 > 0 && k3 < byte1 - 1 && this.anIntArrayArray901[j3 - 1][k3 + 1] == 0 && (ai[j3 - 1][k3 + 1] & 19398968) == 0 && (ai[j3 - 1][k3] & 19398920) == 0 && (ai[j3][k3 + 1] & 19398944) == 0) {
            this.bigX[var26] = j3 - 1;
            this.bigY[var26] = k3 + 1;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3 - 1][k3 + 1] = 6;
            this.anIntArrayArray825[j3 - 1][k3 + 1] = l5;
         }

         if(j3 < byte0 - 1 && k3 < byte1 - 1 && this.anIntArrayArray901[j3 + 1][k3 + 1] == 0 && (ai[j3 + 1][k3 + 1] & 19399136) == 0 && (ai[j3 + 1][k3] & 19399040) == 0 && (ai[j3][k3 + 1] & 19398944) == 0) {
            this.bigX[var26] = j3 + 1;
            this.bigY[var26] = k3 + 1;
            var26 = (var26 + 1) % j4;
            this.anIntArrayArray901[j3 + 1][k3 + 1] = 12;
            this.anIntArrayArray825[j3 + 1][k3 + 1] = l5;
         }
      }

      this.anInt1264 = 0;
      int k6;
      int k4;
      int i7;
      if(!flag1) {
         if(flag) {
            l5 = 100;

            for(k4 = 1; k4 < 2; ++k4) {
               for(k6 = k2 - k4; k6 <= k2 + k4; ++k6) {
                  for(i7 = i2 - k4; i7 <= i2 + k4; ++i7) {
                     if(k6 >= 0 && i7 >= 0 && k6 < 104 && i7 < 104 && this.anIntArrayArray825[k6][i7] < l5) {
                        l5 = this.anIntArrayArray825[k6][i7];
                        j3 = k6;
                        k3 = i7;
                        this.anInt1264 = 1;
                        flag1 = true;
                     }
                  }
               }

               if(flag1) {
                  break;
               }
            }
         }

         if(!flag1) {
            return false;
         }
      }

      byte var27 = 0;
      this.bigX[var27] = j3;
      i4 = var27 + 1;
      this.bigY[var27] = k3;

      for(k4 = l5 = this.anIntArrayArray901[j3][k3]; j3 != j2 || k3 != j1; k4 = this.anIntArrayArray901[j3][k3]) {
         if(k4 != l5) {
            l5 = k4;
            this.bigX[i4] = j3;
            this.bigY[i4++] = k3;
         }

         if((k4 & 2) != 0) {
            ++j3;
         } else if((k4 & 8) != 0) {
            --j3;
         }

         if((k4 & 1) != 0) {
            ++k3;
         } else if((k4 & 4) != 0) {
            --k3;
         }
      }

      if(i4 <= 0) {
         return i != 1;
      } else {
         k4 = i4;
         if(i4 > 25) {
            k4 = 25;
         }

         --i4;
         k6 = this.bigX[i4];
         i7 = this.bigY[i4];
			if (!editMode) {
				anInt1288 += k4;
				if (anInt1288 >= 92) {
					this.stream.createFrame(36);
					this.stream.writeDWord(0);
					anInt1288 = 0;
				}

				if (i == 0) {
					this.stream.createFrame(164);
					this.stream.writeWordBigEndian(k4 + k4 + 3);
				}

				if (i == 1) {
					this.stream.createFrame(248);
					this.stream.writeWordBigEndian(k4 + k4 + 3 + 14);
				}

				if (i == 2) {
					this.stream.createFrame(98);
					this.stream.writeWordBigEndian(k4 + k4 + 3);
				}
			}

         this.stream.method433(k6 + this.baseX);
         this.destX = this.bigX[0];
         this.destY = this.bigY[0];

			if (!editMode) {
				for (int j7 = 1; j7 < k4; ++j7) {
					--i4;
					this.stream.writeWordBigEndian(this.bigX[i4] - k6);
					this.stream.writeWordBigEndian(this.bigY[i4] - i7);
				}
			}

         this.stream.method431(i7 + this.baseY);
         this.stream.method424(super.keyArray[5] != 1?0:1);
         return true;
      }
   }

   static final int method1004(int i) {
      return (int)(Math.log((double)i * 0.00390625D) * 868.5889638065036D + 0.5D);
   }

   public void method86(Stream stream) {
      for(int j = 0; j < this.anInt893; ++j) {
         int k = this.anIntArray894[j];
         NPC npc = this.npcArray[k];
         int l = stream.readUnsignedByte();
         int l1;
         int k2;
         if((l & 16) != 0) {
            l1 = stream.method434();
            if(l1 == '\uffff') {
               l1 = -1;
            }

            k2 = stream.readUnsignedByte();
            if(l1 == npc.anim && l1 != -1) {
               int l2 = Animation.anims[l1].anInt365;
               if(l2 == 1) {
                  npc.anInt1527 = 0;
                  npc.anInt1528 = 0;
                  npc.anInt1529 = k2;
                  npc.anInt1530 = 0;
               }

               if(l2 == 2) {
                  npc.anInt1530 = 0;
               }
            } else if(l1 == -1 || npc.anim == -1 || Animation.anims[l1].anInt359 >= Animation.anims[npc.anim].anInt359) {
               npc.anim = l1;
               npc.anInt1527 = 0;
               npc.anInt1528 = 0;
               npc.anInt1529 = k2;
               npc.anInt1530 = 0;
               npc.anInt1542 = npc.smallXYIndex;
            }
         }

         if((l & 8) != 0) {
            l1 = stream.method426();
            k2 = stream.method427();
            npc.updateHitData(k2, l1, loopCycle);
            npc.loopCycleStatus = loopCycle + 300;
            npc.currentHealth = stream.method426();
            npc.maxHealth = stream.readUnsignedByte();
         }

         if((l & 128) != 0) {
            npc.anInt1520 = stream.readUnsignedWord();
            l1 = stream.readDWord();
            npc.anInt1524 = l1 >> 16;
            npc.anInt1523 = loopCycle + (l1 & '\uffff');
            npc.anInt1521 = 0;
            npc.anInt1522 = 0;
            if(npc.anInt1523 > loopCycle) {
               npc.anInt1521 = -1;
            }

            if(npc.anInt1520 == '\uffff') {
               npc.anInt1520 = -1;
            }
         }

         if((l & 32) != 0) {
            npc.interactingEntity = stream.readUnsignedWord();
            if(npc.interactingEntity == '\uffff') {
               npc.interactingEntity = -1;
            }
         }

         if((l & 1) != 0) {
            npc.textSpoken = stream.readString();
            npc.textCycle = 100;
         }

         if((l & 64) != 0) {
            l1 = stream.method427();
            k2 = stream.method428();
            npc.updateHitData(k2, l1, loopCycle);
            npc.loopCycleStatus = loopCycle + 300;
            npc.currentHealth = stream.method428();
            npc.maxHealth = stream.method427();
         }

         if((l & 2) != 0) {
            npc.desc = EntityDef.forID(stream.method436());
            npc.anInt1540 = npc.desc.aByte68;
            npc.anInt1504 = npc.desc.anInt79;
            npc.anInt1554 = npc.desc.anInt67;
            npc.anInt1555 = npc.desc.anInt58;
            npc.anInt1556 = npc.desc.anInt83;
            npc.anInt1557 = npc.desc.anInt55;
            npc.anInt1511 = npc.desc.anInt77;
         }

         if((l & 4) != 0) {
            npc.anInt1538 = stream.method434();
            npc.anInt1539 = stream.method434();
         }
      }

   }

   public void buildAtNPCMenu(EntityDef entityDef, int i, int j, int k) {
      if(this.menuActionRow < 400) {
         if(entityDef.childrenIDs != null) {
            entityDef = entityDef.method161();
         }

         if(entityDef != null) {
            if(entityDef.aBoolean84) {
               String s = entityDef.name;
               if(entityDef.combatLevel != 0) {
                  s = s + combatDiffColor(myPlayer.combatLevel, entityDef.combatLevel) + " (level-" + entityDef.combatLevel + ")";
               }

               if(this.itemSelected == 1) {
                  this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @yel@" + s;
                  this.menuActionID[this.menuActionRow] = 582;
                  this.menuActionCmd1[this.menuActionRow] = i;
                  this.menuActionCmd2[this.menuActionRow] = k;
                  this.menuActionCmd3[this.menuActionRow] = j;
                  ++this.menuActionRow;
               } else {
                  if(this.spellSelected == 1) {
                     if((this.spellUsableOn & 2) == 2) {
                        this.menuActionName[this.menuActionRow] = this.spellTooltip + " @yel@" + s;
                        this.menuActionID[this.menuActionRow] = 413;
                        this.menuActionCmd1[this.menuActionRow] = i;
                        this.menuActionCmd2[this.menuActionRow] = k;
                        this.menuActionCmd3[this.menuActionRow] = j;
                        ++this.menuActionRow;
                     }
                  } else {
                     int i1;
                     if(entityDef.actions != null) {
                        for(i1 = 4; i1 >= 0; --i1) {
                           if(entityDef.actions[i1] != null && !entityDef.actions[i1].equalsIgnoreCase("attack")) {
                              this.menuActionName[this.menuActionRow] = entityDef.actions[i1] + " @yel@" + s;
                              if(i1 == 0) {
                                 this.menuActionID[this.menuActionRow] = 20;
                              }

                              if(i1 == 1) {
                                 this.menuActionID[this.menuActionRow] = 412;
                              }

                              if(i1 == 2) {
                                 this.menuActionID[this.menuActionRow] = 225;
                              }

                              if(i1 == 3) {
                                 this.menuActionID[this.menuActionRow] = 965;
                              }

                              if(i1 == 4) {
                                 this.menuActionID[this.menuActionRow] = 478;
                              }

                              this.menuActionCmd1[this.menuActionRow] = i;
                              this.menuActionCmd2[this.menuActionRow] = k;
                              this.menuActionCmd3[this.menuActionRow] = j;
                              ++this.menuActionRow;
                           }
                        }
                     }

                     if(entityDef.actions != null) {
                        for(i1 = 4; i1 >= 0; --i1) {
                           if(entityDef.actions[i1] != null && entityDef.actions[i1].equalsIgnoreCase("attack")) {
                              short c = 0;
                              if(entityDef.combatLevel > myPlayer.combatLevel) {
                                 c = 2000;
                              }

                              this.menuActionName[this.menuActionRow] = entityDef.actions[i1] + " @yel@" + s;
                              if(i1 == 0) {
                                 this.menuActionID[this.menuActionRow] = 20 + c;
                              }

                              if(i1 == 1) {
                                 this.menuActionID[this.menuActionRow] = 412 + c;
                              }

                              if(i1 == 2) {
                                 this.menuActionID[this.menuActionRow] = 225 + c;
                              }

                              if(i1 == 3) {
                                 this.menuActionID[this.menuActionRow] = 965 + c;
                              }

                              if(i1 == 4) {
                                 this.menuActionID[this.menuActionRow] = 478 + c;
                              }

                              this.menuActionCmd1[this.menuActionRow] = i;
                              this.menuActionCmd2[this.menuActionRow] = k;
                              this.menuActionCmd3[this.menuActionRow] = j;
                              ++this.menuActionRow;
                           }
                        }
                     }

                     if(myPrivilege >= 2 && developMode){
						this.menuActionName[this.menuActionRow] = "Examine @yel@" + s + " @gre@(@whi@" + entityDef.type + "@gre@)";
					}else{
						this.menuActionName[this.menuActionRow] = "Examine @yel@" + s;
					}
                     this.menuActionID[this.menuActionRow] = 1025;
                     this.menuActionCmd1[this.menuActionRow] = i;
                     this.menuActionCmd2[this.menuActionRow] = k;
                     this.menuActionCmd3[this.menuActionRow] = j;
                     ++this.menuActionRow;
                  }

               }
            }
         }
      }
   }

   public void buildAtPlayerMenu(int i, int j, Player player, int k) {
      if(player != myPlayer) {
         if(this.menuActionRow < 400) {
            String s;
            if(player.skill == 0) {
               s = player.name + combatDiffColor(myPlayer.combatLevel, player.combatLevel) + " (level-" + player.combatLevel + ")";
            } else {
               s = player.name + " (skill-" + player.skill + ")";
            }

            int i1;
            if(this.itemSelected == 1) {
               this.menuActionName[this.menuActionRow] = "Use " + this.selectedItemName + " with @whi@" + s;
               this.menuActionID[this.menuActionRow] = 491;
               this.menuActionCmd1[this.menuActionRow] = j;
               this.menuActionCmd2[this.menuActionRow] = i;
               this.menuActionCmd3[this.menuActionRow] = k;
               ++this.menuActionRow;
            } else if(this.spellSelected == 1) {
               if((this.spellUsableOn & 8) == 8) {
                  this.menuActionName[this.menuActionRow] = this.spellTooltip + " @whi@" + s;
                  this.menuActionID[this.menuActionRow] = 365;
                  this.menuActionCmd1[this.menuActionRow] = j;
                  this.menuActionCmd2[this.menuActionRow] = i;
                  this.menuActionCmd3[this.menuActionRow] = k;
                  ++this.menuActionRow;
               }
            } else {
               for(i1 = 4; i1 >= 0; --i1) {
                  if(this.atPlayerActions[i1] != null) {
                     this.menuActionName[this.menuActionRow] = this.atPlayerActions[i1] + " @whi@" + s;
                     short c = 0;
                     if(this.atPlayerActions[i1].equalsIgnoreCase("attack")) {
                        if(player.combatLevel > myPlayer.combatLevel) {
                           c = 2000;
                        }

                        if(myPlayer.team != 0 && player.team != 0) {
                           if(myPlayer.team == player.team) {
                              c = 2000;
                           } else {
                              c = 0;
                           }
                        }
                     } else if(this.atPlayerArray[i1]) {
                        c = 2000;
                     }

                     if(i1 == 0) {
                        this.menuActionID[this.menuActionRow] = 561 + c;
                     }

                     if(i1 == 1) {
                        this.menuActionID[this.menuActionRow] = 779 + c;
                     }

                     if(i1 == 2) {
                        this.menuActionID[this.menuActionRow] = 27 + c;
                     }

                     if(i1 == 3) {
                        this.menuActionID[this.menuActionRow] = 577 + c;
                     }

                     if(i1 == 4) {
                        this.menuActionID[this.menuActionRow] = 729 + c;
                     }

                     this.menuActionCmd1[this.menuActionRow] = j;
                     this.menuActionCmd2[this.menuActionRow] = i;
                     this.menuActionCmd3[this.menuActionRow] = k;
                     ++this.menuActionRow;
                  }
               }
            }

            for(i1 = 0; i1 < this.menuActionRow; ++i1) {
               if(this.menuActionID[i1] == 516) {
                  this.menuActionName[i1] = "Walk here @whi@" + s;
                  return;
               }
            }

         }
      }
   }

   public void method89(Class30_Sub1 class30_sub1) {
      int i = 0;
      int j = -1;
      int k = 0;
      int l = 0;
      if(class30_sub1.anInt1296 == 0) {
         i = this.worldController.method300(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
      }

      if(class30_sub1.anInt1296 == 1) {
         i = this.worldController.method301(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
      }

      if(class30_sub1.anInt1296 == 2) {
         i = this.worldController.method302(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
      }

      if(class30_sub1.anInt1296 == 3) {
         i = this.worldController.method303(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
      }

      if(i != 0) {
         int i1 = this.worldController.method304(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298, i);
         j = i >> 14 & 32767;
         k = i1 & 31;
         l = i1 >> 6;
      }

      class30_sub1.anInt1299 = j;
      class30_sub1.anInt1301 = k;
      class30_sub1.anInt1300 = l;
   }

   public void method90() {
      for(int index = 0; this.soundCount > index; ++index) {
         --this.soundDelay[index];
         if(this.soundDelay[index] < -10) {
            --this.soundCount;

            for(int class26 = index; this.soundCount > class26; ++class26) {
               this.sound[class26] = this.sound[class26 + 1];
               aClass26Array1468[class26] = aClass26Array1468[class26 + 1];
               this.soundType[class26] = this.soundType[class26 + 1];
               this.soundDelay[class26] = this.soundDelay[class26 + 1];
            }

            --index;
         } else {
            Sound var5 = aClass26Array1468[index];
            if(var5 == null) {
               var5 = Sound.cache[this.sound[index]];
               if(var5 == null) {
                  continue;
               }

               this.soundDelay[index] += var5.method652();
               aClass26Array1468[index] = var5;
            }

            if(this.soundDelay[index] < 0) {
               Class3_Sub9_Sub1 class3_sub9_sub1 = var5.method651().method405(aClass25_1948);
               Class3_Sub7_Sub2 class3_sub7_sub2 = Class3_Sub7_Sub2.method396(class3_sub9_sub1, 100, soundEffectVolume);
               class3_sub7_sub2.method394(this.soundType[index] - 1);
               aClass3_Sub7_Sub1_1493.method384(class3_sub7_sub2);
               this.soundDelay[index] = -100;
            }
         }
      }

      if(this.previousSong > 0) {
         this.previousSong -= 20;
         if(this.previousSong < 0) {
            this.previousSong = 0;
         }

         if(this.previousSong == 0 && musicVolume != 0 && this.currentSong != -1) {
            this.method56(musicVolume, false, this.currentSong);
         }
      }

   }

   static Sprite oma1s;
   static Sprite oma2s;
   static Sprite oma3s;
   static Sprite oma4s;
   static Sprite oma5s;
   static Sprite oma6s;
   static Sprite oma7s;
   static Sprite oma8s;
   
   void startUp() {
      this.drawLoadingText(20, "Starting up");
      //(new CacheDownloader(this)).downloadCache();
      if(SignLink.sunjava) {
         super.minDelay = 5;
      }

      if(aBoolean993) {
         ;
      }

      aBoolean993 = true;
      boolean flag = true;
      if(!flag) {
         this.genericLoadingError = true;
      } else {
         if(SignLink.cache_dat != null) {
            for(int exception = 0; exception < 5; ++exception) {
               this.decompressors[exception] = new Decompressor(SignLink.cache_dat, SignLink.cache_idx[exception], exception + 1);
            }
         }

         try {
            this.titleStreamLoader = this.streamLoaderForName(1, "title screen", "title", this.expectedCRCs[1], 25);
            this.aTextDrawingArea_1270 = new TextDrawingArea(false, "p11_full", this.titleStreamLoader);
            this.aTextDrawingArea_1271 = new TextDrawingArea(false, "p12_full", this.titleStreamLoader);
            this.chatTextDrawingArea = new TextDrawingArea(false, "b12_full", this.titleStreamLoader);
            TextDrawingArea var33 = new TextDrawingArea(true, "q8_full", this.titleStreamLoader);
            this.drawLogo();
            this.loadTitleScreen();
            constructMusic();
            aClass3_Sub7_Sub1_1493 = method407(instance);
            aClass25_1948 = new Class25(22050, anInt197);
            StreamLoader streamLoader = this.streamLoaderForName(2, "config", "config", this.expectedCRCs[2], 30);
            StreamLoader streamLoader_1 = this.streamLoaderForName(3, "interface", "interface", this.expectedCRCs[3], 35);
            StreamLoader streamLoader_2 = this.streamLoaderForName(4, "2d graphics", "media", this.expectedCRCs[4], 40);
            StreamLoader streamLoader_3 = this.streamLoaderForName(6, "textures", "textures", this.expectedCRCs[6], 45);
            StreamLoader streamLoader_4 = this.streamLoaderForName(7, "chat system", "wordenc", this.expectedCRCs[7], 50);
            StreamLoader streamLoader_5 = this.streamLoaderForName(8, "sound effects", "sounds", this.expectedCRCs[8], 55);
            this.byteGroundArray = new byte[4][104][104];
            this.intGroundArray = new int[4][105][105];
            this.worldController = new WorldController(this.intGroundArray);

            for(int streamLoader_6 = 0; streamLoader_6 < 4; ++streamLoader_6) {
               this.aClass11Array1230[streamLoader_6] = new CollisionMap();
            }

            this.aClass30_Sub2_Sub1_Sub1_1263 = new Sprite(512, 512);
            StreamLoader var34 = this.streamLoaderForName(5, "update list", "versionlist", this.expectedCRCs[5], 60);
            this.drawLoadingText(60, "Connecting to update server");
            this.onDemandFetcher = new OnDemandFetcher();
            this.onDemandFetcher.start(var34, this);
            Class36.method528(this.onDemandFetcher.getAnimCount());
            Model.method459(this.onDemandFetcher.getVersionCount(0), this.onDemandFetcher);
            if(!lowMem) {
				checkSpecialTheme();
               this.method58(18, musicVolume, false, themeSong);

               while(this.onDemandFetcher.getNodeCount() > 0) {
                  this.processOnDemandQueue();

                  try {
                     Thread.sleep(100L);
                  } catch (Exception var27) {
                     ;
                  }

                  if(this.onDemandFetcher.anInt1349 > 3) {
                     this.loadError();
                     return;
                  }
               }
            }

            this.drawLoadingText(65, "Requesting animations");
            int k = this.onDemandFetcher.getVersionCount(1);

            int sprite;
            for(sprite = 0; sprite < k; ++sprite) {
               this.onDemandFetcher.method558(1, sprite);
            }

            while(this.onDemandFetcher.getNodeCount() > 0) {
               sprite = k - this.onDemandFetcher.getNodeCount();
               if(sprite > 0) {
                  this.drawLoadingText(65, "Loading animations - " + sprite * 100 / k + "%");
               }

               this.processOnDemandQueue();

               try {
                  Thread.sleep(100L);
               } catch (Exception var26) {
                  ;
               }

               if(this.onDemandFetcher.anInt1349 > 3) {
                  this.loadError();
                  return;
               }
            }

            this.drawLoadingText(70, "Requesting models");
            k = this.onDemandFetcher.getVersionCount(0);

            int i5;
            for(sprite = 0; sprite < k; ++sprite) {
               i5 = this.onDemandFetcher.getModelIndex(sprite);
               if((i5 & 1) != 0) {
                  this.onDemandFetcher.method558(0, sprite);
               }
            }

            k = this.onDemandFetcher.getNodeCount();

            while(this.onDemandFetcher.getNodeCount() > 0) {
               sprite = k - this.onDemandFetcher.getNodeCount();
               if(sprite > 0) {
                  this.drawLoadingText(70, "Loading models - " + sprite * 100 / k + "%");
               }

               this.processOnDemandQueue();

               try {
                  Thread.sleep(100L);
               } catch (Exception var25) {
                  ;
               }
            }

            if(this.decompressors[0] != null) {
               this.drawLoadingText(75, "Requesting maps");
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(0, 48, 47));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(1, 48, 47));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(0, 48, 48));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(1, 48, 48));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(0, 48, 49));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(1, 48, 49));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(0, 47, 47));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(1, 47, 47));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(0, 47, 48));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(1, 47, 48));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(0, 148, 48));
               this.onDemandFetcher.method558(3, this.onDemandFetcher.method562(1, 148, 48));
               k = this.onDemandFetcher.getNodeCount();

               while(this.onDemandFetcher.getNodeCount() > 0) {
                  sprite = k - this.onDemandFetcher.getNodeCount();
                  if(sprite > 0) {
                     this.drawLoadingText(75, "Loading maps - " + sprite * 100 / k + "%");
                  }

                  this.processOnDemandQueue();

                  try {
                     Thread.sleep(100L);
                  } catch (Exception var24) {
                     ;
                  }
               }
            }

            k = this.onDemandFetcher.getVersionCount(0);

            for(sprite = 0; sprite < k; ++sprite) {
               i5 = this.onDemandFetcher.getModelIndex(sprite);
               byte j5 = 0;
               if((i5 & 8) != 0) {
                  j5 = 10;
               } else if((i5 & 32) != 0) {
                  j5 = 9;
               } else if((i5 & 16) != 0) {
                  j5 = 8;
               } else if((i5 & 64) != 0) {
                  j5 = 7;
               } else if((i5 & 128) != 0) {
                  j5 = 6;
               } else if((i5 & 2) != 0) {
                  j5 = 5;
               } else if((i5 & 4) != 0) {
                  j5 = 4;
               }

               if((i5 & 1) != 0) {
                  j5 = 3;
               }

               if(j5 != 0) {
                  this.onDemandFetcher.method563(j5, 0, sprite);
               }
            }

            this.onDemandFetcher.method554(isMembers);
            if(!lowMem) {
               sprite = this.onDemandFetcher.getVersionCount(2);

               for(i5 = 1; i5 < sprite; ++i5) {
                  if(this.onDemandFetcher.method569(i5)) {
                     this.onDemandFetcher.method563((byte)1, 2, i5);
                  }
               }
            }
			 //onDemandFetcher.dumpMap();
            this.drawLoadingText(80, "Unpacking media");
            this.invBack = new Background(streamLoader_2, "invback", 0);
            this.chatBack = new Background(streamLoader_2, "chatback", 0);
			for(sprite = 0; sprite < 2; ++sprite) {
				mapBacks[sprite] = new Background(streamLoader_2, "mapback", sprite);
			}
            mapBack = mapBacks[0];
            this.backBase1 = new Background(streamLoader_2, "backbase1", 0);
            this.backBase2 = new Background(streamLoader_2, "backbase2", 0);
            this.backHmid1 = new Background(streamLoader_2, "backhmid1", 0);

            for(sprite = 0; sprite < 13; ++sprite) {
               this.sideIcons[sprite] = new Background(streamLoader_2, "sideicons", sprite);
            }

            compass = new Sprite(streamLoader_2, "compass", 0);
			compass_a = compass;
			
            this.mapEdge = new Sprite(streamLoader_2, "mapedge", 0);
            this.mapEdge.method345();
            this.multiOverlay = new Sprite(streamLoader_2, "overlay_multiway", 0);

            try {
               for(sprite = 0; sprite < 100; ++sprite) {
                  this.mapScenes[sprite] = new Background(streamLoader_2, "mapscene", sprite);
               }
            } catch (Exception var31) {
               ;
            }

            try {
               for(sprite = 0; sprite < 100; ++sprite) {
                  this.mapFunctions[sprite] = new Sprite(streamLoader_2, "mapfunction", sprite);
               }
            } catch (Exception var30) {
               ;
            }

            try {
               for(sprite = 0; sprite < 20; ++sprite) {
                  this.hitMarks[sprite] = new Sprite(streamLoader_2, "hitmarks", sprite);
               }
            } catch (Exception var29) {
               ;
            }

            try {
               for(sprite = 0; sprite < 6; ++sprite) {
                  this.headIconsHint[sprite] = new Sprite(streamLoader_2, "headicons_hint", sprite);
               }

               for(sprite = 0; sprite < 8; ++sprite) {
                  this.headIcons[sprite] = new Sprite(streamLoader_2, "headicons_prayer", sprite);
               }

               for(sprite = 0; sprite < 3; ++sprite) {
                  this.skullIcons[sprite] = new Sprite(streamLoader_2, "headicons_pk", sprite);
               }
            } catch (Exception var28) {
               ;
            }

            this.mapFlag = new Sprite(streamLoader_2, "mapmarker", 0);
            this.mapMarker = new Sprite(streamLoader_2, "mapmarker", 1);

            for(sprite = 0; sprite < 8; ++sprite) {
               this.crosses[sprite] = new Sprite(streamLoader_2, "cross", sprite);
            }

            this.mapDotItem = new Sprite(streamLoader_2, "mapdots", 0);
            this.mapDotNPC = new Sprite(streamLoader_2, "mapdots", 1);
            this.mapDotPlayer = new Sprite(streamLoader_2, "mapdots", 2);
            this.mapDotFriend = new Sprite(streamLoader_2, "mapdots", 3);
            this.mapDotTeam = new Sprite(streamLoader_2, "mapdots", 4);
			this.mapDotStaff = new Sprite(streamLoader_2, "mod_icons", 1);
            this.scrollBar1 = new Background(streamLoader_2, "scrollbar", 0);
            this.scrollBar2 = new Background(streamLoader_2, "scrollbar", 1);
            this.redStone1 = new Background(streamLoader_2, "redstone1", 0);
            this.redStone2 = new Background(streamLoader_2, "redstone2", 0);
            this.redStone3 = new Background(streamLoader_2, "redstone3", 0);
            this.redStone1_2 = new Background(streamLoader_2, "redstone1", 0);
            this.redStone1_2.method358();
            this.redStone2_2 = new Background(streamLoader_2, "redstone2", 0);
            this.redStone2_2.method358();
            this.redStone1_3 = new Background(streamLoader_2, "redstone1", 0);
            this.redStone1_3.method359();
            this.redStone2_3 = new Background(streamLoader_2, "redstone2", 0);
            this.redStone2_3.method359();
            this.redStone3_2 = new Background(streamLoader_2, "redstone3", 0);
            this.redStone3_2.method359();
            this.redStone1_4 = new Background(streamLoader_2, "redstone1", 0);
            this.redStone1_4.method358();
            this.redStone1_4.method359();
            this.redStone2_4 = new Background(streamLoader_2, "redstone2", 0);
            this.redStone2_4.method358();
            this.redStone2_4.method359();

            for(sprite = 0; sprite < 3; ++sprite) {
               this.modIcons[sprite] = new Background(streamLoader_2, "mod_icons", sprite);
            }

            Sprite var38 = new Sprite(streamLoader_2, "backleft1", 0);
            this.backLeftIP1 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
            var38.method346(0, 0);
            oma5s = new Sprite(streamLoader_2, "backleft2", 0);
            //this.backLeftIP2 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
            //var38.method346(0, 0);
            oma6s = new Sprite(streamLoader_2, "backright1", 0);
            //this.backRightIP1 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
            //var38.method346(0, 0);
            oma1s = new Sprite(streamLoader_2, "backright2", 0);//20
            //this.backRightIP2 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
			//var38.method346(0, 0);
            oma8s = new Sprite(streamLoader_2, "backtop1", 0);
            //this.backTopIP1 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
            //var38.method346(0, 0);
            oma7s = new Sprite(streamLoader_2, "backvmid1", 0);
            //this.backVmidIP1 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
            //var38.method346(0, 0);
            oma2s = new Sprite(streamLoader_2, "backvmid2", 0);//22
            //this.backVmidIP2 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
			//System.out.println(var38.myWidth+" "+var38.myHeight);//37,133
			//var38.method346(0, 0);
            oma3s = new Sprite(streamLoader_2, "backvmid3", 0);
            //this.backVmidIP3 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
            //var38.method346(0, 0);
            oma4s = new Sprite(streamLoader_2, "backhmid2", 0);//18
            //this.backVmidIP2_2 = new RSImageProducer(var38.myWidth, var38.myHeight, this.getGameComponent());
			//var38.method346(0, 0);
            i5 = (int)(Math.random() * 21.0D) - 10;
            int var36 = (int)(Math.random() * 21.0D) - 10;
            int k5 = (int)(Math.random() * 21.0D) - 10;
            int l5 = (int)(Math.random() * 41.0D) - 20;

            for(int abyte0 = 0; abyte0 < 100; ++abyte0) {
               if(this.mapFunctions[abyte0] != null) {
                  this.mapFunctions[abyte0].method344(i5 + l5, var36 + l5, k5 + l5);
               }

               if(this.mapScenes[abyte0] != null) {
                  this.mapScenes[abyte0].method360(i5 + l5, var36 + l5, k5 + l5);
               }
            }

            this.drawLoadingText(83, "Unpacking textures");
            Texture.method368(streamLoader_3);
            Texture.method372(0.8D);
            Texture.method367();
            this.drawLoadingText(86, "Unpacking config");
            Animation.unpackConfig(streamLoader);
            ObjectDef.unpackConfig(streamLoader);
            Flo.unpackConfig(streamLoader);
            ItemDef.unpackConfig(streamLoader);
            EntityDef.unpackConfig(streamLoader);
            IDK.unpackConfig(streamLoader);
            SpotAnim.unpackConfig(streamLoader);
            Varp.unpackConfig(streamLoader);
            VarBit.unpackConfig(streamLoader);
            ItemDef.isMembers = isMembers;
            this.drawLoadingText(90, "Unpacking sounds");
            byte[] var35 = streamLoader_5.getDataForName("sounds.dat");
            Stream stream = new Stream(var35);
            Sound.unpack(stream);
            this.drawLoadingText(95, "Unpacking interfaces");
            TextDrawingArea[] aclass30_sub2_sub1_sub4s = new TextDrawingArea[]{this.aTextDrawingArea_1270, this.aTextDrawingArea_1271, this.chatTextDrawingArea, var33};
            RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, streamLoader_2);
            this.drawLoadingText(100, "Preparing game engine");
			updateMinimapStuff();
            /*int ai = 0;

            int k8;
            int i8;
            int l8;
            while(ai < 33) {
               i8 = 999;
               k8 = 0;
			   
               l8 = 0;

               while(true) {
                  if(l8 < 34) {
                     label414: {
                        if(mapBack.aByteArray1450[l8 + ai * mapBack.anInt1452] == 0) {
                           if(i8 == 999) {
                              i8 = l8;
                           }
                        } else if(i8 != 999) {
                           k8 = l8;
                           break label414;
                        }

                        ++l8;
                        continue;
                     }
                  }

                  this.anIntArray968[ai] = i8;
                  this.anIntArray1057[ai] = k8 - i8;
                  ++ai;
                  break;
               }
            }

            ai = 5;

            while(ai < 156) {
               i8 = 999;
               k8 = 0;
               l8 = 25;

               while(true) {
                  if(l8 < 172) {
                     label415: {
                        if(mapBack.aByteArray1450[l8 + ai * mapBack.anInt1452] == 0 && (l8 > 34 || ai > 34)) {
                           if(i8 == 999) {
                              i8 = l8;
                           }
                        } else if(i8 != 999) {
                           k8 = l8;
                           break label415;
                        }

                        ++l8;
                        continue;
                     }
                  }

                  this.anIntArray1052[ai - 5] = i8 - 25;
                  this.anIntArray1229[ai - 5] = k8 - i8;
                  ++ai;
                  break;
               }
            }*/
			updateDrawingAreaStuff();

            int[] var37 = new int[9];

			int k8;
            int i8;
            int l8;
            for(i8 = 0; i8 < 9; ++i8) {
               k8 = 128 + i8 * 32 + 15;
               l8 = 600 + k8 * 3;
               int i9 = Texture.anIntArray1470[k8];
               var37[i8] = l8 * i9 >> 16;
            }

            WorldController.method310(500, 800, 512, 334, var37);
            Censor.loadConfig(streamLoader_4);
            this.mouseDetection = new MouseDetection(this);
            this.startRunnable(this.mouseDetection, 10);
            Animable_Sub5.clientInstance = this;
            ObjectDef.clientInstance = this;
            EntityDef.clientInstance = this;
			//repackCacheIndex(4);
			//onDemandFetcher.crcPack(4, 1394);
			updateGameframeSprites();
			if(editMode)
				login_Fake();
         } catch (Exception var32) {
            SignLink.reporterror("loaderror " + this.aString1049 + " " + this.anInt1079);
            this.loadingError = true;
         }
      }
   }
   
   static int chatBackWidth = 479;
   static int chatBackHeight = 96;
   static int chatBackWidth317 = 479;
   static int chatBackHeight317 = 96;
   static int chatBackWidth474 = 506;
   static int chatBackHeight474 = 131;
   static int invBackWidth = 190;
   static int invBackWidth317 = 190;
   static int invBackWidth474 = 190;
   
   static void updateDrawingAreaStuff(){
		Texture.method365(765, 503);
            fullScreenTextureArray = Texture.anIntArray1472;
			if(gameframeVersion != 474)
				Texture.method365(chatBackWidth317, chatBackHeight317);
			else
				Texture.method365(chatBackWidth474, chatBackHeight474);
            anIntArray1180 = Texture.anIntArray1472;
			if(gameframeVersion != 474)
				Texture.method365(invBackWidth317, 261);
			else
				Texture.method365(invBackWidth474, 261);
            anIntArray1181 = Texture.anIntArray1472;
            Texture.method365(512, 334);
            anIntArray1182 = Texture.anIntArray1472;
			if(gameframeVersion == 474){
				chatBackWidth = chatBackWidth474;
				chatBackHeight = chatBackHeight474;
				invBackWidth = invBackWidth474;
			}else{
				chatBackWidth = chatBackWidth317;
				chatBackHeight = chatBackHeight317;
				invBackWidth = invBackWidth317;
			}
   }
   
   static void updateMinimapStuff(){
   int ai = 0;

            int k8;
            int i8;
            int l8;
            while(ai < 33) {
               i8 = 999;
               k8 = 0;
			   
               l8 = 0;

               while(true) {
                  if(l8 < 34) {
                     label414: {
                        if(mapBack.aByteArray1450[l8 + ai * mapBack.anInt1452] == 0) {
                           if(i8 == 999) {
                              i8 = l8;
                           }
                        } else if(i8 != 999) {
                           k8 = l8;
                           break label414;
                        }

                        ++l8;
                        continue;
                     }
                  }

                  anIntArray968[ai] = i8;
                  anIntArray1057[ai] = k8 - i8;
                  ++ai;
                  break;
               }
            }

            ai = 5;

            while(ai < 156) {
               i8 = 999;
               k8 = 0;
               l8 = 25;

               while(true) {
                  if(l8 < 172) {
                     label415: {
                        if(mapBack.aByteArray1450[l8 + ai * mapBack.anInt1452] == 0 && (l8 > 34 || ai > 34)) {
                           if(i8 == 999) {
                              i8 = l8;
                           }
                        } else if(i8 != 999) {
                           k8 = l8;
                           break label415;
                        }

                        ++l8;
                        continue;
                     }
                  }

                  anIntArray1052[ai - 5] = i8 - 25;
                  anIntArray1229[ai - 5] = k8 - i8;
                  ++ai;
                  break;
               }
            }
   }
   
   public static void updateGameframeSprites(){
		if(gameframeVersion == 317)
			compass = compass_a;
		else
			compass = cacheSprite[0];
		if(gameframeVersion == 474){
			mapBack = mapBacks[1];
		}else
			mapBack = mapBacks[0];
		if(gameframeVersion != 474){
			chatTypeView = 0;
			cButtonCPos = 0;
		}
		updateMinimapStuff();
		updateDrawingAreaStuff();
		try {
			writeSettings();
		} catch(IOException e) {
		}
   }

   public void method91(Stream stream, int i) {
      while(true) {
         if(stream.bitPosition + 10 < i * 8) {
            int j = stream.readBits(11);
            if(j != 2047) {
               if(this.playerArray[j] == null) {
                  this.playerArray[j] = new Player();
                  if(this.aStreamArray895s[j] != null) {
                     this.playerArray[j].updatePlayer(this.aStreamArray895s[j]);
                  }
               }

               this.playerIndices[this.playerCount++] = j;
               Player player = this.playerArray[j];
               player.anInt1537 = loopCycle;
               int k = stream.readBits(1);
               if(k == 1) {
                  this.anIntArray894[this.anInt893++] = j;
               }

               int l = stream.readBits(1);
               int i1 = stream.readBits(5);
               if(i1 > 15) {
                  i1 -= 32;
               }

               int j1 = stream.readBits(5);
               if(j1 > 15) {
                  j1 -= 32;
               }

               player.setPos(myPlayer.smallX[0] + j1, myPlayer.smallY[0] + i1, l == 1);
               continue;
            }
         }

         stream.finishBitAccess();
         return;
      }
   }

   public void processMainScreenClick() {
      if(anInt1021 != 0 || fullscreenInterfaceID != -1)
			return;
         if(super.clickMode3 == 1) {
			int off = 550;
			if(gameframeVersion == 474)
				off = 545;
            int i = super.saveClickX - 25 - off;
            int j = super.saveClickY - 5 - 4;
            int l;
            if(i >= 0 && j >= 0 && i < 146 && j < 151) {
               i -= 73;
               j -= 75;
               l = this.minimapInt1 + this.minimapInt2 & 2047;
               int i1 = Texture.anIntArray1470[l];
               int j1 = Texture.anIntArray1471[l];
               i1 = i1 * (this.minimapInt3 + 256) >> 8;
               j1 = j1 * (this.minimapInt3 + 256) >> 8;
               int k1 = j * i1 + i * j1 >> 11;
               int l1 = j * j1 - i * i1 >> 11;
               int i2 = myPlayer.x + k1 >> 7;
               int j2 = myPlayer.y - l1 >> 7;
               boolean flag1 = this.doWalkTo(1, 0, 0, 0, myPlayer.smallY[0], 0, 0, j2, myPlayer.smallX[0], true, i2);
               if(flag1) {
                  this.stream.writeWordBigEndian(i);
                  this.stream.writeWordBigEndian(j);
                  this.stream.writeWord(this.minimapInt1);
                  this.stream.writeWordBigEndian(57);
                  this.stream.writeWordBigEndian(this.minimapInt2);
                  this.stream.writeWordBigEndian(this.minimapInt3);
                  this.stream.writeWordBigEndian(89);
                  this.stream.writeWord(myPlayer.x);
                  this.stream.writeWord(myPlayer.y);
                  this.stream.writeWordBigEndian(this.anInt1264);
                  this.stream.writeWordBigEndian(63);
               }
            }

            ++anInt1117;
            if(anInt1117 > 1151) {
               anInt1117 = 0;
               this.stream.createFrame(246);
               this.stream.writeWordBigEndian(0);
               l = this.stream.currentOffset;
               if((int)(Math.random() * 2.0D) == 0) {
                  this.stream.writeWordBigEndian(101);
               }

               this.stream.writeWordBigEndian(197);
               this.stream.writeWord((int)(Math.random() * 65536.0D));
               this.stream.writeWordBigEndian((int)(Math.random() * 256.0D));
               this.stream.writeWordBigEndian(67);
               this.stream.writeWord(14214);
               if((int)(Math.random() * 2.0D) == 0) {
                  this.stream.writeWord(29487);
               }

               this.stream.writeWord((int)(Math.random() * 65536.0D));
               if((int)(Math.random() * 2.0D) == 0) {
                  this.stream.writeWordBigEndian(220);
               }

               this.stream.writeWordBigEndian(180);
               this.stream.writeBytes(this.stream.currentOffset - l);
            }
         }

   }

   public String interfaceIntToString(int j) {
      return j < 999999999?String.valueOf(j):"*";
   }

   static final void method899(int i, int i_29_, boolean bool, byte[] is, int i_30_) {
      if(aClass56_749 != null) {
         if(i_29_ >= ~anInt478) {
            i -= 20;
            if(i < 1) {
               i = 1;
            }

            anInt720 = i;
            if(anInt478 == 0) {
               anInt2200 = 0;
            } else {
               int i_31_ = method1004(anInt478);
               i_31_ -= anInt155;
               anInt2200 = (anInt2200 - 1 + i_31_ + 3600) / anInt2200;
            }

            aBoolean475 = bool;
            aByteArray347 = is;
            anInt1478 = i_30_;
         } else if(anInt720 != 0) {
            aBoolean475 = bool;
            aByteArray347 = is;
            anInt1478 = i_30_;
         } else {
            method853(i_30_, is, bool);
         }
      }

   }

   public void showErrorScreen() {
      Graphics g = this.getGameComponent().getGraphics();
      g.setColor(Color.black);
      g.fillRect(0, 0, 765, 503);
      this.method4(1);
      byte l;
      int l1;
      if(this.loadingError) {
         this.aBoolean831 = false;
         g.setFont(new Font("Helvetica", 1, 16));
         g.setColor(Color.yellow);
         l = 35;
         g.drawString("Sorry, an error has occured whilst loading "+NAME, 30, l);
         l1 = l + 50;
         g.setColor(Color.white);
         g.drawString("To fix this try the following (in order):", 30, l1);
         l1 += 50;
         g.setColor(Color.white);
         g.setFont(new Font("Helvetica", 1, 12));
         g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, l1);
         l1 += 30;
         g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, l1);
         l1 += 30;
         g.drawString("3: Try using a different game-world", 30, l1);
         l1 += 30;
         g.drawString("4: Try rebooting your computer", 30, l1);
         l1 += 30;
         g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, l1);
      }

      if(this.genericLoadingError) {
         this.aBoolean831 = false;
         g.setFont(new Font("Helvetica", 1, 20));
         g.setColor(Color.white);
         g.drawString("Error - unable to load game!", 50, 50);
         g.drawString("To play "+NAME+" make sure you play from", 50, 100);
         g.drawString("http://www."+NAME+".com", 50, 150);
      }

      if(this.rsAlreadyLoaded) {
         this.aBoolean831 = false;
         g.setColor(Color.yellow);
         l = 35;
         g.drawString("Error a copy of "+NAME+" already appears to be loaded", 30, l);
         l1 = l + 50;
         g.setColor(Color.white);
         g.drawString("To fix this try the following (in order):", 30, l1);
         l1 += 50;
         g.setColor(Color.white);
         g.setFont(new Font("Helvetica", 1, 12));
         g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, l1);
         l1 += 30;
         g.drawString("2: Try rebooting your computer, and reloading", 30, l1);
         l1 += 30;
      }

   }

   public URL getCodeBase() {
      try {
         return new URL("http://" + server + ":" + (80 + portOff));
      } catch (Exception var2) {
         return null;
      }
   }

   public void method95() {
      for(int j = 0; j < this.npcCount; ++j) {
         int k = this.npcIndices[j];
         NPC npc = this.npcArray[k];
         if(npc != null) {
            this.method96(npc);
         }
      }

   }

   public void method96(Entity entity) {
      if(entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184) {
         entity.anim = -1;
         entity.anInt1520 = -1;
         entity.anInt1547 = 0;
         entity.anInt1548 = 0;
         entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
         entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
         entity.method446();
      }

      if(entity == myPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776)) {
         entity.anim = -1;
         entity.anInt1520 = -1;
         entity.anInt1547 = 0;
         entity.anInt1548 = 0;
         entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
         entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
         entity.method446();
      }

      if(entity.anInt1547 > loopCycle) {
         this.method97(entity);
      } else if(entity.anInt1548 >= loopCycle) {
         this.method98(entity);
      } else {
         this.method99(entity);
      }

      this.method100(entity);
      this.method101(entity);
   }

   static final void method689(int i) {
      for(anInt1408 += i; anInt1408 >= anInt197; anInt1526 -= anInt1526 >> 2) {
         anInt1408 -= anInt197;
      }

      anInt1526 -= i * 1000;
      if(anInt1526 < 0) {
         anInt1526 = 0;
      }

   }

   public void method97(Entity entity) {
      int i = entity.anInt1547 - loopCycle;
      int j = entity.anInt1543 * 128 + entity.anInt1540 * 64;
      int k = entity.anInt1545 * 128 + entity.anInt1540 * 64;
      entity.x += (j - entity.x) / i;
      entity.y += (k - entity.y) / i;
      entity.anInt1503 = 0;
      if(entity.anInt1549 == 0) {
         entity.turnDirection = 1024;
      }

      if(entity.anInt1549 == 1) {
         entity.turnDirection = 1536;
      }

      if(entity.anInt1549 == 2) {
         entity.turnDirection = 0;
      }

      if(entity.anInt1549 == 3) {
         entity.turnDirection = 512;
      }

   }

   public void method98(Entity entity) {
      if(entity.anInt1548 == loopCycle || entity.anim == -1 || entity.anInt1529 != 0 || entity.anInt1528 + 1 > Animation.anims[entity.anim].method258(entity.anInt1527)) {
         int i = entity.anInt1548 - entity.anInt1547;
         int j = loopCycle - entity.anInt1547;
         int k = entity.anInt1543 * 128 + entity.anInt1540 * 64;
         int l = entity.anInt1545 * 128 + entity.anInt1540 * 64;
         int i1 = entity.anInt1544 * 128 + entity.anInt1540 * 64;
         int j1 = entity.anInt1546 * 128 + entity.anInt1540 * 64;
         entity.x = (k * (i - j) + i1 * j) / i;
         entity.y = (l * (i - j) + j1 * j) / i;
      }

      entity.anInt1503 = 0;
      if(entity.anInt1549 == 0) {
         entity.turnDirection = 1024;
      }

      if(entity.anInt1549 == 1) {
         entity.turnDirection = 1536;
      }

      if(entity.anInt1549 == 2) {
         entity.turnDirection = 0;
      }

      if(entity.anInt1549 == 3) {
         entity.turnDirection = 512;
      }

      entity.anInt1552 = entity.turnDirection;
   }

   public void method99(Entity entity) {
      entity.anInt1517 = entity.anInt1511;
      if(entity.smallXYIndex == 0) {
         entity.anInt1503 = 0;
      } else {
         if(entity.anim != -1 && entity.anInt1529 == 0) {
            Animation i = Animation.anims[entity.anim];
            if(entity.anInt1542 > 0 && i.anInt363 == 0) {
               ++entity.anInt1503;
               return;
            }

            if(entity.anInt1542 <= 0 && i.anInt364 == 0) {
               ++entity.anInt1503;
               return;
            }
         }

         int var9 = entity.x;
         int j = entity.y;
         int k = entity.smallX[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
         int l = entity.smallY[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
         if(k - var9 <= 256 && k - var9 >= -256 && l - j <= 256 && l - j >= -256) {
            if(var9 < k) {
               if(j < l) {
                  entity.turnDirection = 1280;
               } else if(j > l) {
                  entity.turnDirection = 1792;
               } else {
                  entity.turnDirection = 1536;
               }
            } else if(var9 > k) {
               if(j < l) {
                  entity.turnDirection = 768;
               } else if(j > l) {
                  entity.turnDirection = 256;
               } else {
                  entity.turnDirection = 512;
               }
            } else if(j < l) {
               entity.turnDirection = 1024;
            } else {
               entity.turnDirection = 0;
            }

            int i1 = entity.turnDirection - entity.anInt1552 & 2047;
            if(i1 > 1024) {
               i1 -= 2048;
            }

            int j1 = entity.anInt1555;
            if(i1 >= -256 && i1 <= 256) {
               j1 = entity.anInt1554;
            } else if(i1 >= 256 && i1 < 768) {
               j1 = entity.anInt1557;
            } else if(i1 >= -768 && i1 <= -256) {
               j1 = entity.anInt1556;
            }

            if(j1 == -1) {
               j1 = entity.anInt1554;
            }

            entity.anInt1517 = j1;
            int k1 = 4;
            if(entity.anInt1552 != entity.turnDirection && entity.interactingEntity == -1 && entity.anInt1504 != 0) {
               k1 = 2;
            }

            if(entity.smallXYIndex > 2) {
               k1 = 6;
            }

            if(entity.smallXYIndex > 3) {
               k1 = 8;
            }

            if(entity.anInt1503 > 0 && entity.smallXYIndex > 1) {
               k1 = 8;
               --entity.anInt1503;
            }

            if(entity.aBooleanArray1553[entity.smallXYIndex - 1]) {
               k1 <<= 1;
            }

            if(k1 >= 8 && entity.anInt1517 == entity.anInt1554 && entity.anInt1505 != -1) {
               entity.anInt1517 = entity.anInt1505;
            }

            if(var9 < k) {
               entity.x += k1;
               if(entity.x > k) {
                  entity.x = k;
               }
            } else if(var9 > k) {
               entity.x -= k1;
               if(entity.x < k) {
                  entity.x = k;
               }
            }

            if(j < l) {
               entity.y += k1;
               if(entity.y > l) {
                  entity.y = l;
               }
            } else if(j > l) {
               entity.y -= k1;
               if(entity.y < l) {
                  entity.y = l;
               }
            }

            if(entity.x == k && entity.y == l) {
               --entity.smallXYIndex;
               if(entity.anInt1542 > 0) {
                  --entity.anInt1542;
               }
            }

         } else {
            entity.x = k;
            entity.y = l;
         }
      }
   }

    public void method100(Entity entity) {
        if (entity.anInt1504 == 0) {
            return;
        }
        if ((entity.interactingEntity != -1) && (entity.interactingEntity < 32768)) {
            NPC npc = npcArray[entity.interactingEntity];
            if (npc != null) {
                int i1 = entity.x - npc.x;
                int k1 = entity.y - npc.y;
                if ((i1 != 0) || (k1 != 0)) {
                    entity.turnDirection = ((int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7FF);
                }
            }
        }
        if (entity.interactingEntity >= 32768) {
            int j = entity.interactingEntity - 32768;
            if (j == unknownInt10) {
                j = myPlayerIndex;
            }
            Player player = playerArray[j];
            if (player != null) {
                int l1 = entity.x - player.x;
                int i2 = entity.y - player.y;
                if ((l1 != 0) || (i2 != 0)) {
                    entity.turnDirection = ((int) (Math.atan2(l1, i2) * 325.94900000000001D) & 0x7FF);
                }
            }
        }
        if (((entity.anInt1538 != 0) || (entity.anInt1539 != 0)) && ((entity.smallXYIndex == 0) || (entity.anInt1503 > 0))) {
            int k = entity.x - (entity.anInt1538 - baseX - baseX) * 64;
            int j1 = entity.y - (entity.anInt1539 - baseY - baseY) * 64;
            if ((k != 0) || (j1 != 0)) {
                entity.turnDirection = ((int) (Math.atan2(k, j1) * 325.94900000000001D) & 0x7FF);
            }
            entity.anInt1538 = 0;
            entity.anInt1539 = 0;
        }
        int l = entity.turnDirection - entity.anInt1552 & 0x7FF;
        if (l != 0) {
            if ((l < entity.anInt1504) || (l > 2048 - entity.anInt1504))
                entity.anInt1552 = entity.turnDirection;
            else if (l > 1024)
                entity.anInt1552 -= entity.anInt1504;
            else {
                entity.anInt1552 += entity.anInt1504;
            }
            entity.anInt1552 &= 2047;
            if ((entity.anInt1517 == entity.anInt1511) && (entity.anInt1552 != entity.turnDirection)) {
                if (entity.anInt1512 != -1) {
                    entity.anInt1517 = entity.anInt1512;
                    return;
                }
                entity.anInt1517 = entity.anInt1554;
            }
        }
    }

   public void method101(Entity entity) {
      entity.aBoolean1541 = false;
      Animation animation_3;
      if(entity.anInt1517 != -1) {
         animation_3 = Animation.anims[entity.anInt1517];
         ++entity.anInt1519;
         if(entity.anInt1518 < animation_3.anInt352 && entity.anInt1519 > animation_3.method258(entity.anInt1518)) {
            entity.anInt1519 = 0;
            ++entity.anInt1518;
         }

         if(entity.anInt1518 >= animation_3.anInt352) {
            entity.anInt1519 = 0;
            entity.anInt1518 = 0;
         }
      }

      if(entity.anInt1520 != -1 && loopCycle >= entity.anInt1523) {
         if(entity.anInt1521 < 0) {
            entity.anInt1521 = 0;
         }

         animation_3 = SpotAnim.cache[entity.anInt1520].aAnimation_407;
         ++entity.anInt1522;

         while(entity.anInt1521 < animation_3.anInt352 && entity.anInt1522 > animation_3.method258(entity.anInt1521)) {
            entity.anInt1522 -= animation_3.method258(entity.anInt1521);
            ++entity.anInt1521;
         }

         if(entity.anInt1521 >= animation_3.anInt352 && (entity.anInt1521 < 0 || entity.anInt1521 >= animation_3.anInt352)) {
            entity.anInt1520 = -1;
         }
      }

      if(entity.anim != -1 && entity.anInt1529 <= 1) {
         animation_3 = Animation.anims[entity.anim];
         if(animation_3.anInt363 == 1 && entity.anInt1542 > 0 && entity.anInt1547 <= loopCycle && entity.anInt1548 < loopCycle) {
            entity.anInt1529 = 1;
            return;
         }
      }

      if(entity.anim != -1 && entity.anInt1529 == 0) {
         animation_3 = Animation.anims[entity.anim];
         ++entity.anInt1528;

         while(entity.anInt1527 < animation_3.anInt352 && entity.anInt1528 > animation_3.method258(entity.anInt1527)) {
            entity.anInt1528 -= animation_3.method258(entity.anInt1527);
            ++entity.anInt1527;
         }

         if(entity.anInt1527 >= animation_3.anInt352) {
            entity.anInt1527 -= animation_3.anInt356;
            ++entity.anInt1530;
            if(entity.anInt1530 >= animation_3.anInt362) {
               entity.anim = -1;
            }

            if(entity.anInt1527 < 0 || entity.anInt1527 >= animation_3.anInt352) {
               entity.anim = -1;
            }
         }

         entity.aBoolean1541 = animation_3.aBoolean358;
      }

      if(entity.anInt1529 > 0) {
         --entity.anInt1529;
      }

   }
   
   int drawingareaVersion = 317;
   
	public void drawGameScreen() {
		if (fullscreenInterfaceID != -1 && (loadingStage == 2 || super.fullGameScreen != null)) {
			if (loadingStage == 2) {
				method119(anInt945, fullscreenInterfaceID);
				if (openInterfaceID != -1) {
					method119(anInt945, openInterfaceID);
				}
                anInt945 = 0;
                resetAllImageProducers();
                super.fullGameScreen.initDrawingArea();
                Texture.anIntArray1472 = fullScreenTextureArray;
                DrawingArea.setAllPixelsToZero();
                welcomeScreenRaised = true;
                if (openInterfaceID != -1) {
                    RSInterface rsInterface_1 = RSInterface.interfaceCache[openInterfaceID];
                    if (rsInterface_1.width == 512 && rsInterface_1.height == 334 && rsInterface_1.type == 0) {
                        rsInterface_1.width = 765;
                        rsInterface_1.height = 503;
                    }
                    drawInterface(0, 0, rsInterface_1, 8);
                }
                RSInterface rsInterface = RSInterface.interfaceCache[fullscreenInterfaceID];
                if (rsInterface.width == 512 && rsInterface.height == 334 && rsInterface.type == 0) {
                    rsInterface.width = 765;
                    rsInterface.height = 503;
                }
                drawInterface(0, 1, rsInterface, 0);

                if (!menuOpen) {
                    processRightClick();
                    drawTooltip();
                } else {
                    drawMenu();
                }
            }
            drawCount++;
            super.fullGameScreen.drawGraphics(0, super.graphics, 0);
            return;
        } else {
            if (drawCount != 0) {
                resetImageProducers2();
            }
        }
      if(this.welcomeScreenRaised) {
         this.welcomeScreenRaised = false;
         this.backLeftIP1.drawGraphics(4, super.graphics, 0);
         //this.backLeftIP2.drawGraphics(357, super.graphics, 0);
         //this.backRightIP1.drawGraphics(4, super.graphics, 722);
         //this.backRightIP2.drawGraphics(205, super.graphics, 743);
         //this.backTopIP1.drawGraphics(0, super.graphics, 0);
         //this.backVmidIP1.drawGraphics(4, super.graphics, 516);
         //this.backVmidIP2.drawGraphics(205, super.graphics, 516);
         //this.backVmidIP3.drawGraphics(357, super.graphics, 496);
         //this.backVmidIP2_2.drawGraphics(338, super.graphics, 0);
         this.needDrawTabArea = true;
         this.inputTaken = true;
         this.tabAreaAltered = true;
         this.aBoolean1233 = true;
         if(this.loadingStage != 2) {
            this.aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
            this.aRSImageProducer_1164.drawGraphics(4, super.graphics, gameframeVersion == 474 ? 545 : 550);
         }
      }

      if(this.menuOpen && this.menuScreenArea == 1) {
         this.needDrawTabArea = true;
      }

      boolean flag2;
      if(this.invOverlayInterfaceID != -1) {
         flag2 = this.method119(this.anInt945, this.invOverlayInterfaceID);
         if(flag2) {
            this.needDrawTabArea = true;
         }
      }

      if(this.atInventoryInterfaceType == 2) {
         this.needDrawTabArea = true;
      }

      if(this.activeInterfaceType == 2) {
         this.needDrawTabArea = true;
      }

      if(this.needDrawTabArea) {
         this.drawTabArea();
         this.needDrawTabArea = false;
      }
		
      if (backDialogID == -1 && inputDialogState == 3) {
    	  
    	  if(gameframeVersion != 474){
    		int position = (totalItemResults) * 14;
  			this.aClass9_1059.scrollPosition = itemResultScrollPos;
  			if(super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332) {
  				method65(463, 77, super.mouseX - 17, super.mouseY - 357, this.aClass9_1059, 0, false, position);
  			}
  			int scrollPosition = this.aClass9_1059.scrollPosition;
  			if (scrollPosition < 0) {
  				scrollPosition = 0;
  			}
  			if (scrollPosition > position - 77) {
  				scrollPosition = position - 77;
  			}
  			if (itemResultScrollPos != scrollPosition) {
  				itemResultScrollPos = scrollPosition;
  				inputTaken = true;
  			}
    	  } else {
			int position = (totalItemResults) * 14;
			this.aClass9_1059.scrollPosition = itemResultScrollPos;
			if(super.mouseX > 478 && super.mouseX < 580 && super.mouseY > 342) {
				method65(494, 110, super.mouseX - 0, super.mouseY - 345, this.aClass9_1059, 0, false, position);
			}
			int scrollPosition = this.aClass9_1059.scrollPosition;
			if (scrollPosition < 0) {
				scrollPosition = 0;
			}
			if (scrollPosition > position - 110) {
				scrollPosition = position - 110;
			}
			if (itemResultScrollPos != scrollPosition) {
				itemResultScrollPos = scrollPosition;
				inputTaken = true;
			}
    	  }
		}
      if(this.backDialogID == -1 && inputDialogState != 3) {//scrollbar
		if(gameframeVersion != 474){
         this.aClass9_1059.scrollPosition = this.anInt1211 - this.anInt1089 - 77;
         if(super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332) {
            this.method65(463, 77, super.mouseX - 17, super.mouseY - 357, this.aClass9_1059, 0, false, this.anInt1211);
         }

         int flag21 = this.anInt1211 - 77 - this.aClass9_1059.scrollPosition;
         if(flag21 < 0) {
            flag21 = 0;
         }

         if(flag21 > this.anInt1211 - 77) {
            flag21 = this.anInt1211 - 77;
         }

         if(this.anInt1089 != flag21) {
            this.anInt1089 = flag21;
            this.inputTaken = true;
         }
		 }else{
			this.aClass9_1059.scrollPosition = this.anInt1211 - this.anInt1089 - 110;
         if(super.mouseX > 478 && super.mouseX < 580 && super.mouseY > 342) {
            this.method65(494, 110, super.mouseX - 0, super.mouseY - 345, this.aClass9_1059, 0, false, this.anInt1211);
         }

         int flag21 = this.anInt1211 - 110 - this.aClass9_1059.scrollPosition;
         if(flag21 < 0) {
            flag21 = 0;
         }

         if(flag21 > this.anInt1211 - 110) {
            flag21 = this.anInt1211 - 110;
         }

         if(this.anInt1089 != flag21) {
            this.anInt1089 = flag21;
            this.inputTaken = true;
         }
		 }
      }

      if(this.backDialogID != -1) {
         flag2 = this.method119(this.anInt945, this.backDialogID);
         if(flag2) {
            this.inputTaken = true;
         }
      }

      if(this.atInventoryInterfaceType == 3) {
         this.inputTaken = true;
      }

      if(this.activeInterfaceType == 3) {
         this.inputTaken = true;
      }
      
      if(inputDialogState == 3 && gameframeVersion != 474)//repainting chatbox are for ge item search on 317/459 gameframes
    	  this.inputTaken = true;

      if(this.aString844 != null) {
         this.inputTaken = true;
      }

      if(this.menuOpen && this.menuScreenArea == 2) {
         this.inputTaken = true;
      }

      if(this.inputTaken) {
         this.drawChatArea();
         this.inputTaken = false;
		 if(gameframeVersion == 474){
			this.aRSImageProducer_1123.initDrawingArea();
			this.cacheSprite[14].drawSprite(0, 0);
			drawChannelButtons();
			this.aRSImageProducer_1123.drawGraphics(476, super.graphics, 0);
			this.aRSImageProducer_1165.initDrawingArea();
		}
      }
	  
	  if(this.loadingStage == 2) {
         this.method146();
      }

      if(this.loadingStage == 2) {
         this.drawMinimap();
         this.aRSImageProducer_1164.drawGraphics(4, super.graphics, gameframeVersion == 474 ? 545 : 550);
      }

      if(this.anInt1054 != -1) {
         this.tabAreaAltered = true;
      }

      if(this.tabAreaAltered) {
         if(this.anInt1054 != -1 && this.anInt1054 == this.tabID) {
            this.anInt1054 = -1;
            this.stream.createFrame(120);
            this.stream.writeWordBigEndian(this.tabID);
         }

         this.tabAreaAltered = false;

		 if(gameframeVersion == 474 && drawingareaVersion != 474){
			drawingareaVersion = 474;
			this.aRSImageProducer_oma7 = null;
			this.aRSImageProducer_oma7 = new RSImageProducer(29, 156, this.getGameComponent());//34,156
			this.aRSImageProducer_1166 = null;
			this.aRSImageProducer_1166 = new RSImageProducer(chatBackWidth474, chatBackHeight474, this.getGameComponent());
			this.aRSImageProducer_1123 = null;
			this.aRSImageProducer_1123 = new RSImageProducer(520, 27, this.getGameComponent());
			this.aRSImageProducer_oma5 = null;
			this.aRSImageProducer_oma5 = new RSImageProducer(7, 131, this.getGameComponent());
			this.aRSImageProducer_oma3 = null;
			this.aRSImageProducer_oma3 = new RSImageProducer(34, 121, this.getGameComponent());
			this.aRSImageProducer_oma4 = null;
			this.aRSImageProducer_oma4 = new RSImageProducer(547, 7, this.getGameComponent());
			this.aRSImageProducer_oma2 = null;
			this.aRSImageProducer_oma2 = new RSImageProducer(31, 133, this.getGameComponent());
			this.aRSImageProducer_oma1 = null;
			this.aRSImageProducer_oma1 = new RSImageProducer(28, 261, this.getGameComponent());
			this.aRSImageProducer_1163 = null;
			this.aRSImageProducer_1163 = new RSImageProducer(invBackWidth474, 261, this.getGameComponent());
			this.drawTabArea();
			this.drawChatArea();
			this.aRSImageProducer_1164.initDrawingArea();
			mapBack.method361(0, 0);
			this.aRSImageProducer_1164.drawGraphics(4, super.graphics, gameframeVersion == 474 ? 545 : 550);
		 }
		 if(gameframeVersion != 474 && drawingareaVersion == 474){
			drawingareaVersion = 317;
			this.aRSImageProducer_oma7 = null;
			this.aRSImageProducer_oma7 = new RSImageProducer(34, 156, this.getGameComponent());//34,156
			this.aRSImageProducer_1166 = null;
			this.aRSImageProducer_1166 = new RSImageProducer(chatBackWidth317, chatBackHeight317, this.getGameComponent());
			this.aRSImageProducer_1123 = null;
			this.aRSImageProducer_1123 = new RSImageProducer(496, 50, this.getGameComponent());
			this.aRSImageProducer_oma5 = null;
			this.aRSImageProducer_oma5 = new RSImageProducer(17, 96, this.getGameComponent());
			this.aRSImageProducer_oma3 = null;
			this.aRSImageProducer_oma3 = new RSImageProducer(57, 109, this.getGameComponent());
			this.aRSImageProducer_oma4 = null;
			this.aRSImageProducer_oma4 = new RSImageProducer(553, 19, this.getGameComponent());
			this.aRSImageProducer_oma2 = null;
			this.aRSImageProducer_oma2 = new RSImageProducer(37, 133, this.getGameComponent());
			this.aRSImageProducer_oma1 = null;
			this.aRSImageProducer_oma1 = new RSImageProducer(22, 261, this.getGameComponent());
			this.aRSImageProducer_1163 = null;
			this.aRSImageProducer_1163 = new RSImageProducer(invBackWidth317, 261, this.getGameComponent());
			this.drawTabArea();
			this.drawChatArea();
			this.aRSImageProducer_1164.initDrawingArea();
			mapBack.method361(0, 0);
			this.aRSImageProducer_1164.drawGraphics(4, super.graphics, gameframeVersion == 474 ? 545 : 550);
		 }
		 
		 this.aRSImageProducer_oma1.initDrawingArea();
		 if(gameframeVersion != 474){
				this.oma1s.drawSprite(0, 0);
				this.aRSImageProducer_oma1.drawGraphics(205, super.graphics, 743);
		 }else{
				this.cacheSprite[20].drawSprite(0, 0);
				this.aRSImageProducer_oma1.drawGraphics(205, super.graphics, 737);
		 }
		 
		 this.aRSImageProducer_oma2.initDrawingArea();
		 if(gameframeVersion != 474)
				this.oma2s.drawSprite(0, 0);
			else
				this.cacheSprite[22].drawSprite(0, 0);
		 this.aRSImageProducer_oma2.drawGraphics(205, super.graphics, 516);
		 
		 this.aRSImageProducer_oma3.initDrawingArea();
		 if(gameframeVersion != 474){
				this.oma3s.drawSprite(0, 0);
				this.aRSImageProducer_oma3.drawGraphics(357, super.graphics, 496);
			}else{
				this.cacheSprite[17].drawSprite(0, 0);
				this.aRSImageProducer_oma3.drawGraphics(345, super.graphics, 513);
			}
		 
		 this.aRSImageProducer_oma4.initDrawingArea();
		 if(gameframeVersion != 474)
				this.oma4s.drawSprite(0, 0);
			else
				this.cacheSprite[18].drawSprite(0, 0);
		 this.aRSImageProducer_oma4.drawGraphics(338, super.graphics, 0);
		 
		 this.aRSImageProducer_oma5.initDrawingArea();
		 if(gameframeVersion != 474){
				this.oma5s.drawSprite(0, 0);
				this.aRSImageProducer_oma5.drawGraphics(357, super.graphics, 0);
			}else{
				this.cacheSprite[16].drawSprite(0, 0);
				this.aRSImageProducer_oma5.drawGraphics(345, super.graphics, 0);
			}
		 
		 this.aRSImageProducer_oma6.initDrawingArea();
		 if(gameframeVersion != 474)
				this.oma6s.drawSprite(0, 0);
			else
				this.cacheSprite[24].drawSprite(0, 0);
		 this.aRSImageProducer_oma6.drawGraphics(4, super.graphics, gameframeVersion == 474 ? 717 : 722);
		 
		 this.aRSImageProducer_oma7.initDrawingArea();
		 if(gameframeVersion != 474)
				this.oma7s.drawSprite(0, 0);
			else
				this.cacheSprite[25].drawSprite(0, 0);
		 this.aRSImageProducer_oma7.drawGraphics(4, super.graphics, 516);
		 
		 this.aRSImageProducer_oma8.initDrawingArea();
		 if(gameframeVersion != 474)
				this.oma8s.drawSprite(0, 0);
			else
				this.cacheSprite[26].drawSprite(0, 0);
		 this.aRSImageProducer_oma8.drawGraphics(0, super.graphics, 0);
		 
         this.aRSImageProducer_1125.initDrawingArea();
		 if(gameframeVersion != 474)
				this.backHmid1.method361(0, 0);
			else
				this.cacheSprite[21].drawSprite(0, 0);
         if(this.invOverlayInterfaceID == -1) {
            if(this.tabInterfaceIDs[this.tabID] != -1) {
				if(gameframeVersion != 474){
					if(this.tabID == 0) {
						this.redStone1.method361(22, 10);
					}

					if(this.tabID == 1) {
						this.redStone2.method361(54, 8);
					}

					if(this.tabID == 2) {
						this.redStone2.method361(82, 8);
					}

					if(this.tabID == 3) {
						this.redStone3.method361(110, 8);
					}

					if(this.tabID == 4) {
						this.redStone2_2.method361(153, 8);
					}

					if(this.tabID == 5) {
						this.redStone2_2.method361(181, 8);
					}

					if(this.tabID == 6) {
						this.redStone1_2.method361(209, 9);
					}
				}else{
					if(this.tabID == 0) {
						this.cacheSprite[37].drawSprite(6, 8);
					}

					if(this.tabID == 1) {
						this.cacheSprite[41].drawSprite(44, 8);
					}

					if(this.tabID == 2) {
						this.cacheSprite[41].drawSprite(77, 8);
					}

					if(this.tabID == 3) {
						this.cacheSprite[41].drawSprite(110, 8);
					}

					if(this.tabID == 4) {
						this.cacheSprite[41].drawSprite(143, 8);
					}

					if(this.tabID == 5) {
						this.cacheSprite[41].drawSprite(176, 8);
					}

					if(this.tabID == 6) {
						this.cacheSprite[38].drawSprite(209, 8);
					}
				}
            }

			if(gameframeVersion == 317){
				if(this.tabInterfaceIDs[0] != -1 && (this.anInt1054 != 0 || loopCycle % 20 < 10)) {
					this.sideIcons[0].method361(29, 13);
				}

				if(this.tabInterfaceIDs[1] != -1 && (this.anInt1054 != 1 || loopCycle % 20 < 10)) {
					this.sideIcons[1].method361(53, 11);
				}

				if(this.tabInterfaceIDs[2] != -1 && (this.anInt1054 != 2 || loopCycle % 20 < 10)) {
					this.sideIcons[2].method361(82, 11);
				}

				if(this.tabInterfaceIDs[3] != -1 && (this.anInt1054 != 3 || loopCycle % 20 < 10)) {
					this.sideIcons[3].method361(115, 12);
				}

				if(this.tabInterfaceIDs[4] != -1 && (this.anInt1054 != 4 || loopCycle % 20 < 10)) {
					this.sideIcons[4].method361(153, 13);
				}

				if(this.tabInterfaceIDs[5] != -1 && (this.anInt1054 != 5 || loopCycle % 20 < 10)) {
					this.sideIcons[5].method361(180, 11);
				}

				if(this.tabInterfaceIDs[6] != -1 && (this.anInt1054 != 6 || loopCycle % 20 < 10)) {
					this.sideIcons[6].method361(208, 13);
				}
			}else if(gameframeVersion == 459 || gameframeVersion == 474){
				if(this.tabInterfaceIDs[0] != -1 && (this.anInt1054 != 0 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -17;
						offY = -5;
					}
					this.cacheSprite[1].drawSprite(33+offX, 21+offY);
				}

				if(this.tabInterfaceIDs[1] != -1 && (this.anInt1054 != 1 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -10;
						offY = -4;
					}
					this.cacheSprite[2].drawSprite(58+offX, 18+offY);
				}

				if(this.tabInterfaceIDs[2] != -1 && (this.anInt1054 != 2 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -3;
						offY = -2;
					}
					this.cacheSprite[3].drawSprite(86+offX, 17+offY);
				}

				if(this.tabInterfaceIDs[3] != -1 && (this.anInt1054 != 3 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					int id = 4;
					if(gameframeVersion == 474){
						offX = -2;
						offY = 0;
						id = 42;
					}
					this.cacheSprite[id].drawSprite(116+offX, 13+offY);
				}

				if(this.tabInterfaceIDs[4] != -1 && (this.anInt1054 != 4 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -10;
						offY = -3;
					}
					this.cacheSprite[5].drawSprite(156+offX, 13+offY);
				}

				if(this.tabInterfaceIDs[5] != -1 && (this.anInt1054 != 5 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -5;
						offY = -3;
					}
					this.cacheSprite[6].drawSprite(184+offX, 14+offY);
				}

				if(this.tabInterfaceIDs[6] != -1 && (this.anInt1054 != 6 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = 2;
						offY = -4;
					}
					this.cacheSprite[7].drawSprite(212+offX, 19+offY);
				}
			}
         }

         this.aRSImageProducer_1125.drawGraphics(160, super.graphics, 516);
         this.aRSImageProducer_1124.initDrawingArea();
		 if(gameframeVersion != 474)
				this.backBase2.method361(0, 0);
			else
				this.cacheSprite[19].drawSprite(0, 0);
         if(this.invOverlayInterfaceID == -1) {
            if(this.tabInterfaceIDs[this.tabID] != -1) {
				if(gameframeVersion != 474){
					if(this.tabID == 7) {
						this.redStone1_3.method361(42, 0);
					}

					if(this.tabID == 8) {
						this.redStone2_3.method361(74, 0);
					}

					if(this.tabID == 9) {
						this.redStone2_3.method361(102, 0);
					}

					if(this.tabID == 10) {
						this.redStone3_2.method361(130, 1);
					}

					if(this.tabID == 11) {
						this.redStone2_4.method361(173, 0);
					}

					if(this.tabID == 12) {
						this.redStone2_4.method361(201, 0);
					}

					if(this.tabID == 13) {
						this.redStone1_4.method361(229, 0);
					}
			   }else{
					if(this.tabID == 7) {
						this.cacheSprite[39].drawSprite(26, 0);
					}

					if(this.tabID == 8) {
						this.cacheSprite[41].drawSprite(64, 0);
					}

					if(this.tabID == 9) {
						this.cacheSprite[41].drawSprite(97, 0);
					}

					if(this.tabID == 10) {
						this.cacheSprite[41].drawSprite(130, 0);
					}

					if(this.tabID == 11) {
						this.cacheSprite[41].drawSprite(163, 0);
					}

					if(this.tabID == 12) {
						this.cacheSprite[41].drawSprite(196, 0);
					}

					if(this.tabID == 13) {
						this.cacheSprite[40].drawSprite(229, 0);
					}
			   }
            }

			if(gameframeVersion == 317){
				if(this.tabInterfaceIDs[8] != -1 && (this.anInt1054 != 8 || loopCycle % 20 < 10)) {
					this.sideIcons[7].method361(74, 2);
				}

				if(this.tabInterfaceIDs[9] != -1 && (this.anInt1054 != 9 || loopCycle % 20 < 10)) {
					this.sideIcons[8].method361(102, 3);
				}

				if(this.tabInterfaceIDs[10] != -1 && (this.anInt1054 != 10 || loopCycle % 20 < 10)) {
					this.sideIcons[9].method361(137, 4);
				}

				if(this.tabInterfaceIDs[11] != -1 && (this.anInt1054 != 11 || loopCycle % 20 < 10)) {
					this.sideIcons[10].method361(174, 2);
				}

				if(this.tabInterfaceIDs[12] != -1 && (this.anInt1054 != 12 || loopCycle % 20 < 10)) {
					this.sideIcons[11].method361(201, 2);
				}

				if(this.tabInterfaceIDs[13] != -1 && (this.anInt1054 != 13 || loopCycle % 20 < 10)) {
					this.sideIcons[12].method361(226, 2);
				}
			}else if(gameframeVersion == 459 || gameframeVersion == 474){
				if(this.tabInterfaceIDs[8] != -1 && (this.anInt1054 != 8 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -9;
						offY = 0;
					}
					this.cacheSprite[8].drawSprite(78+offX, 8+offY);
				}

				if(this.tabInterfaceIDs[9] != -1 && (this.anInt1054 != 9 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -4;
						offY = -1;
					}
					this.cacheSprite[9].drawSprite(106+offX, 9+offY);
				}

				if(this.tabInterfaceIDs[10] != -1 && (this.anInt1054 != 10 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -6;
						offY = 0;
					}
					this.cacheSprite[10].drawSprite(142+offX, 4+offY);
				}

				if(this.tabInterfaceIDs[11] != -1 && (this.anInt1054 != 11 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -9;
						offY = -1;
					}
					this.cacheSprite[11].drawSprite(177+offX, 7+offY);
				}

				if(this.tabInterfaceIDs[12] != -1 && (this.anInt1054 != 12 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = -3;
						offY = 1;
					}
					this.cacheSprite[12].drawSprite(207+offX, 3+offY);
				}

				if(this.tabInterfaceIDs[13] != -1 && (this.anInt1054 != 13 || loopCycle % 20 < 10)) {
					int offX = 0;
					int offY = 0;
					if(gameframeVersion == 474){
						offX = 5;
						offY = 1;
					}
					this.cacheSprite[13].drawSprite(231+offX, 4+offY);
				}
			}
         }

         this.aRSImageProducer_1124.drawGraphics(466, super.graphics, 496);
         this.aRSImageProducer_1165.initDrawingArea();
      }

      if(this.aBoolean1233) {
         this.aBoolean1233 = false;
         this.aRSImageProducer_1123.initDrawingArea();
		 if(gameframeVersion != 474)
				this.backBase1.method361(0, 0);
			else
				this.cacheSprite[14].drawSprite(0, 0);
		if(gameframeVersion != 474){		
			this.aTextDrawingArea_1271.method382(16777215, 55, "Public chat", 28, true);
			if(this.publicChatMode == 0) {
				this.aTextDrawingArea_1271.method382('\uff00', 55, "On", 41, true);
			}

			if(this.publicChatMode == 1) {
				this.aTextDrawingArea_1271.method382(16776960, 55, "Friends", 41, true);
			}

			if(this.publicChatMode == 2) {
				this.aTextDrawingArea_1271.method382(16711680, 55, "Off", 41, true);
			}

			if(this.publicChatMode == 3) {
				this.aTextDrawingArea_1271.method382('\uffff', 55, "Hide", 41, true);
			}

			this.aTextDrawingArea_1271.method382(16777215, 184, "Private chat", 28, true);
			if(this.privateChatMode == 0) {
				this.aTextDrawingArea_1271.method382('\uff00', 184, "On", 41, true);
			}

			if(this.privateChatMode == 1) {
				this.aTextDrawingArea_1271.method382(16776960, 184, "Friends", 41, true);
			}

			if(this.privateChatMode == 2) {
				this.aTextDrawingArea_1271.method382(16711680, 184, "Off", 41, true);
			}

			this.aTextDrawingArea_1271.method382(16777215, 324, "Trade/compete", 28, true);
			if(this.tradeMode == 0) {
				this.aTextDrawingArea_1271.method382('\uff00', 324, "On", 41, true);
			}

			if(this.tradeMode == 1) {
				this.aTextDrawingArea_1271.method382(16776960, 324, "Friends", 41, true);
			}

			if(this.tradeMode == 2) {
				this.aTextDrawingArea_1271.method382(16711680, 324, "Off", 41, true);
			}

			this.aTextDrawingArea_1271.method382(16777215, 458, "Report abuse", 33, true);
		}else{
			drawChannelButtons();
		}
		 if(gameframeVersion != 474)
			this.aRSImageProducer_1123.drawGraphics(453, super.graphics, 0);
		else
			this.aRSImageProducer_1123.drawGraphics(476, super.graphics, 0);
         this.aRSImageProducer_1165.initDrawingArea();
      }

      this.anInt945 = 0;
   }

   public boolean buildFriendsListMenu(RSInterface class9) {
      int i = class9.anInt214;
      if((i < 1 || i > 200) && (i < 701 || i > 900)) {
         if(i >= 401 && i <= 500) {
            this.menuActionName[this.menuActionRow] = "Remove @whi@" + class9.message;
            this.menuActionID[this.menuActionRow] = 322;
            ++this.menuActionRow;
            return true;
         } else {
            return false;
         }
      } else {
         if(i >= 801) {
            i -= 701;
         } else if(i >= 701) {
            i -= 601;
         } else if(i >= 101) {
            i -= 101;
         } else {
            --i;
         }

         this.menuActionName[this.menuActionRow] = "Remove @whi@" + this.friendsList[i];
         this.menuActionID[this.menuActionRow] = 792;
         ++this.menuActionRow;
         this.menuActionName[this.menuActionRow] = "Message @whi@" + this.friendsList[i];
         this.menuActionID[this.menuActionRow] = 639;
         ++this.menuActionRow;
         return true;
      }
   }

   public void method104() {
      for(Animable_Sub3 class30_sub2_sub4_sub3 = (Animable_Sub3)this.aClass19_1056.reverseGetFirst(); class30_sub2_sub4_sub3 != null; class30_sub2_sub4_sub3 = (Animable_Sub3)this.aClass19_1056.reverseGetNext()) {
         if(class30_sub2_sub4_sub3.anInt1560 == this.plane && !class30_sub2_sub4_sub3.aBoolean1567) {
            if(loopCycle >= class30_sub2_sub4_sub3.anInt1564) {
               class30_sub2_sub4_sub3.method454(this.anInt945);
               if(class30_sub2_sub4_sub3.aBoolean1567) {
                  class30_sub2_sub4_sub3.unlink();
               } else {
                  this.worldController.method285(class30_sub2_sub4_sub3.anInt1560, 0, class30_sub2_sub4_sub3.anInt1563, -1, class30_sub2_sub4_sub3.anInt1562, 60, class30_sub2_sub4_sub3.anInt1561, class30_sub2_sub4_sub3, false);
               }
            }
         } else {
            class30_sub2_sub4_sub3.unlink();
         }
      }

   }

   public void drawInterface(int j, int k, RSInterface class9, int l) {
      if(class9.type == 0 && class9.children != null) {
         if(!class9.aBoolean266 || this.anInt1026 == class9.id || this.anInt1048 == class9.id || this.anInt1039 == class9.id) {
            int i1 = DrawingArea.topX;
            int j1 = DrawingArea.topY;
            int k1 = DrawingArea.bottomX;
            int l1 = DrawingArea.bottomY;
            DrawingArea.setDrawingArea(l + class9.height, k, k + class9.width, l);
            int i2 = class9.children.length;

            for(int j2 = 0; j2 < i2; ++j2) {
               int k2 = class9.childX[j2] + k;
               int l2 = class9.childY[j2] + l - j;
               RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j2]];
               k2 += class9_1.anInt263;
               l2 += class9_1.anInt265;
               if(class9_1.anInt214 > 0) {
                  this.drawFriendsListOrWelcomeScreen(class9_1);
               }

               if(class9_1.type == 0) {
                  if(class9_1.scrollPosition > class9_1.scrollMax - class9_1.height) {
                     class9_1.scrollPosition = class9_1.scrollMax - class9_1.height;
                  }

                  if(class9_1.scrollPosition < 0) {
                     class9_1.scrollPosition = 0;
                  }

                  this.drawInterface(class9_1.scrollPosition, k2, class9_1, l2);
                  if(class9_1.scrollMax > class9_1.height) {
                     this.method30(class9_1.height, class9_1.scrollPosition, l2, k2 + class9_1.width, class9_1.scrollMax);
                  }
               } else if(class9_1.type != 1) {
                  int textDrawingArea_1;
                  int k4;
                  int i6;
                  int j5;
                  int s2;
                  int itemDef;
                  int k9;
                  int i9;
                  if(class9_1.type == 2) {
                     textDrawingArea_1 = 0;

                     for(k4 = 0; k4 < class9_1.height; ++k4) {
                        for(j5 = 0; j5 < class9_1.width; ++j5) {
                           i6 = k2 + j5 * (32 + class9_1.invSpritePadX);
                           itemDef = l2 + k4 * (32 + class9_1.invSpritePadY);
                           if(textDrawingArea_1 < 20) {
                              i6 += class9_1.spritesX[textDrawingArea_1];
                              itemDef += class9_1.spritesY[textDrawingArea_1];
                           }

                           if(class9_1.inv[textDrawingArea_1] > 0) {
                              s2 = 0;
                              i9 = 0;
                              k9 = class9_1.inv[textDrawingArea_1] - 1;
                              if(i6 > DrawingArea.topX - 32 && i6 < DrawingArea.bottomX && itemDef > DrawingArea.topY - 32 && itemDef < DrawingArea.bottomY || this.activeInterfaceType != 0 && this.anInt1085 == textDrawingArea_1) {
                                 int l9 = 0;
                                 if(this.itemSelected == 1 && this.anInt1283 == textDrawingArea_1 && this.anInt1284 == class9_1.id) {
                                    l9 = 16777215;
                                 }

                                 Sprite class30_sub2_sub1_sub1_2 = ItemDef.getSprite(k9, class9_1.invStackSizes[textDrawingArea_1], l9);
                                 if(class30_sub2_sub1_sub1_2 != null) {
                                    int k10;
                                    if(this.activeInterfaceType != 0 && this.anInt1085 == textDrawingArea_1 && this.anInt1084 == class9_1.id) {
                                       s2 = super.mouseX - this.anInt1087;
                                       i9 = super.mouseY - this.anInt1088;
                                       if(s2 < 5 && s2 > -5) {
                                          s2 = 0;
                                       }

                                       if(i9 < 5 && i9 > -5) {
                                          i9 = 0;
                                       }

                                       if(this.anInt989 < 5) {
                                          s2 = 0;
                                          i9 = 0;
                                       }

                                       class30_sub2_sub1_sub1_2.drawSprite1(i6 + s2, itemDef + i9);
                                       if(itemDef + i9 < DrawingArea.topY && class9.scrollPosition > 0) {
                                          k10 = this.anInt945 * (DrawingArea.topY - itemDef - i9) / 3;
                                          if(k10 > this.anInt945 * 10) {
                                             k10 = this.anInt945 * 10;
                                          }

                                          if(k10 > class9.scrollPosition) {
                                             k10 = class9.scrollPosition;
                                          }

                                          class9.scrollPosition -= k10;
                                          this.anInt1088 += k10;
                                       }

                                       if(itemDef + i9 + 32 > DrawingArea.bottomY && class9.scrollPosition < class9.scrollMax - class9.height) {
                                          k10 = this.anInt945 * (itemDef + i9 + 32 - DrawingArea.bottomY) / 3;
                                          if(k10 > this.anInt945 * 10) {
                                             k10 = this.anInt945 * 10;
                                          }

                                          if(k10 > class9.scrollMax - class9.height - class9.scrollPosition) {
                                             k10 = class9.scrollMax - class9.height - class9.scrollPosition;
                                          }

                                          class9.scrollPosition += k10;
                                          this.anInt1088 -= k10;
                                       }
                                    } else if(this.atInventoryInterfaceType != 0 && this.atInventoryIndex == textDrawingArea_1 && this.atInventoryInterface == class9_1.id) {
                                       class30_sub2_sub1_sub1_2.drawSprite1(i6, itemDef);
                                    } else {
                                       class30_sub2_sub1_sub1_2.drawSprite(i6, itemDef);
                                    }

                                    if(class30_sub2_sub1_sub1_2.anInt1444 == 33 || class9_1.invStackSizes[textDrawingArea_1] != 1) {
                                       k10 = class9_1.invStackSizes[textDrawingArea_1];
									   int color = 0;
											if (k10 >= 1) {
												color = 0xFFFF00;
											}
											if (k10 >= 100000) {
												color = 0xFFFFFF;
											} 
											if (k10 >= 10000000) {
												color = 0x00FF80;
											}
                                       this.aTextDrawingArea_1270.method385(0, intToKOrMil(k10), itemDef + 10 + i9, i6 + 1 + s2);
                                       this.aTextDrawingArea_1270.method385(color, intToKOrMil(k10), itemDef + 9 + i9, i6 + s2);
                                    }
                                 }
                              }
                           } else if(class9_1.sprites != null && textDrawingArea_1 < 20) {
                              Sprite var33 = class9_1.sprites[textDrawingArea_1];
                              if(var33 != null) {
                                 var33.drawSprite(i6, itemDef);
                              }
                           }

                           ++textDrawingArea_1;
                        }
                     }
                  } else if(class9_1.type == 3) {
                     boolean var25 = false;
                     if(this.anInt1039 == class9_1.id || this.anInt1048 == class9_1.id || this.anInt1026 == class9_1.id) {
                        var25 = true;
                     }

                     if(this.interfaceIsSelected(class9_1)) {
                        k4 = class9_1.anInt219;
                        if(var25 && class9_1.anInt239 != 0) {
                           k4 = class9_1.anInt239;
                        }
                     } else {
                        k4 = class9_1.textColor;
                        if(var25 && class9_1.anInt216 != 0) {
                           k4 = class9_1.anInt216;
                        }
                     }

                     if(class9_1.aByte254 == 0) {
                        if(class9_1.aBoolean227) {
                           DrawingArea.method336(class9_1.height, l2, k2, k4, class9_1.width);
                        } else {
                           DrawingArea.fillPixels(k2, class9_1.width, class9_1.height, k4, l2);
                        }
                     } else if(class9_1.aBoolean227) {
                        DrawingArea.method335(k4, l2, class9_1.width, class9_1.height, 256 - (class9_1.aByte254 & 255), k2);
                     } else {
                        DrawingArea.method338(l2, class9_1.height, 256 - (class9_1.aByte254 & 255), k4, class9_1.width, k2);
                     }
                  } else {
                     TextDrawingArea var28;
                     if(class9_1.type == 4) {
                        var28 = class9_1.textDrawingAreas;
                        String var29 = class9_1.message;
                        boolean var26 = false;
                        if(this.anInt1039 == class9_1.id || this.anInt1048 == class9_1.id || this.anInt1026 == class9_1.id) {
                           var26 = true;
                        }

                        if(this.interfaceIsSelected(class9_1)) {
                           i6 = class9_1.anInt219;
                           if(var26 && class9_1.anInt239 != 0) {
                              i6 = class9_1.anInt239;
                           }

                           if(class9_1.aString228.length() > 0) {
                              var29 = class9_1.aString228;
                           }
                        } else {
                           i6 = class9_1.textColor;
                           if(var26 && class9_1.anInt216 != 0) {
                              i6 = class9_1.anInt216;
                           }
                        }

                        if(class9_1.atActionType == 6 && this.aBoolean1149) {
                           var29 = "Please wait...";
                           i6 = class9_1.textColor;
                        }

                        //if((DrawingArea.width == 479 && gameframeVersion != 474) || (DrawingArea.width == 506 && gameframeVersion == 474)) {
						if(DrawingArea.width == chatBackWidth) {
                           if(i6 == 16776960) {
                              i6 = 255;
                           }

                           if(i6 == '\uc000') {
                              i6 = 16777215;
                           }
                        }

                        for(itemDef = l2 + var28.anInt1497; var29.length() > 0; itemDef += var28.anInt1497) {
                           if(var29.indexOf("%") != -1) {
                              label315:
                              while(true) {
                                 s2 = var29.indexOf("%1");
                                 if(s2 == -1) {
                                    while(true) {
                                       s2 = var29.indexOf("%2");
                                       if(s2 == -1) {
                                          while(true) {
                                             s2 = var29.indexOf("%3");
                                             if(s2 == -1) {
                                                while(true) {
                                                   s2 = var29.indexOf("%4");
                                                   if(s2 == -1) {
                                                      while(true) {
                                                         s2 = var29.indexOf("%5");
                                                         if(s2 == -1) {
                                                            break label315;
                                                         }

                                                         var29 = var29.substring(0, s2) + this.interfaceIntToString(this.extractInterfaceValues(class9_1, 4)) + var29.substring(s2 + 2);
                                                      }
                                                   }

                                                   var29 = var29.substring(0, s2) + this.interfaceIntToString(this.extractInterfaceValues(class9_1, 3)) + var29.substring(s2 + 2);
                                                }
                                             }

                                             var29 = var29.substring(0, s2) + this.interfaceIntToString(this.extractInterfaceValues(class9_1, 2)) + var29.substring(s2 + 2);
                                          }
                                       }

                                       var29 = var29.substring(0, s2) + this.interfaceIntToString(this.extractInterfaceValues(class9_1, 1)) + var29.substring(s2 + 2);
                                    }
                                 }

                                 var29 = var29.substring(0, s2) + this.interfaceIntToString(this.extractInterfaceValues(class9_1, 0)) + var29.substring(s2 + 2);
                              }
                           }

                           s2 = var29.indexOf("\\n");
                           String var35;
                           if(s2 != -1) {
                              var35 = var29.substring(0, s2);
                              var29 = var29.substring(s2 + 2);
                           } else {
                              var35 = var29;
                              var29 = "";
                           }

                           if(class9_1.aBoolean223) {
                              var28.method382(i6, k2 + class9_1.width / 2, var35, itemDef, class9_1.aBoolean268);
                           } else {
                              var28.method389(class9_1.aBoolean268, k2, i6, var35, itemDef);
                           }
                        }
                     } else if(class9_1.type == 5 || class9_1.type == 17) {
                        /*Sprite var27;
                        if(this.interfaceIsSelected(class9_1)) {
                           var27 = class9_1.sprite2;
                        } else {
                           var27 = class9_1.sprite1;
                        }

                        if(var27 != null) {
                           var27.drawSprite(k2, l2);
                        }*/
						Sprite sprite;
                    if(interfaceIsSelected(class9_1))
                        sprite = class9_1.sprite2;
                    else
                        sprite = class9_1.sprite1;
					if (sprite != null) {	
						if(spellSelected == 1 && class9_1.id == spellID && spellID != 0)
							sprite.drawSprite(k2, l2, 0xffffff);
						else 
							sprite.drawSprite(k2, l2);	
					}
					
					Sprite hover;
					if (interfaceIsSelected(class9_1))
						hover = class9_1.hoverSprite2;
					else
						hover = class9_1.hoverSprite1;
					if (hover != null)
						if (super.mouseX >= 4 + k2
								&& super.mouseX <= 4 + k2 + hover.myWidth
								&& super.mouseY >= 4 + l2
								&& super.mouseY <= 4 + l2 + hover.myHeight)
							hover.drawSprite(k2, l2);
					
                     } else if(class9_1.type == 6) {
                        textDrawingArea_1 = Texture.textureInt1;
                        k4 = Texture.textureInt2;
                        Texture.textureInt1 = k2 + class9_1.width / 2;
                        Texture.textureInt2 = l2 + class9_1.height / 2;
                        j5 = Texture.anIntArray1470[class9_1.anInt270] * class9_1.anInt269 >> 16;
                        i6 = Texture.anIntArray1471[class9_1.anInt270] * class9_1.anInt269 >> 16;
                        boolean var31 = this.interfaceIsSelected(class9_1);
                        if(var31) {
                           s2 = class9_1.anInt258;
                        } else {
                           s2 = class9_1.anInt257;
                        }

                        Model var32;
                        if(s2 == -1) {
                           var32 = class9_1.method209(-1, -1, var31);
                        } else {
                           Animation var34 = Animation.anims[s2];
                           var32 = class9_1.method209(var34.anIntArray354[class9_1.anInt246], var34.anIntArray353[class9_1.anInt246], var31);
                        }

                        if(var32 != null) {
                           var32.method482(class9_1.anInt271, 0, class9_1.anInt270, 0, j5, i6);
                        }

                        Texture.textureInt1 = textDrawingArea_1;
                        Texture.textureInt2 = k4;
                     } else if(class9_1.type == 7) {
                        var28 = class9_1.textDrawingAreas;
                        k4 = 0;

                        for(j5 = 0; j5 < class9_1.height; ++j5) {
                           for(i6 = 0; i6 < class9_1.width; ++i6) {
                              if(class9_1.inv[k4] > 0) {
                                 ItemDef var30 = ItemDef.forID(class9_1.inv[k4] - 1);
                                 String var36 = var30.name;
                                 if(var30.stackable || class9_1.invStackSizes[k4] != 1) {
                                    var36 = var36 + " x" + intToKOrMilLongName(class9_1.invStackSizes[k4]);
                                 }

                                 i9 = k2 + i6 * (115 + class9_1.invSpritePadX);
                                 k9 = l2 + j5 * (12 + class9_1.invSpritePadY);
                                 if(class9_1.aBoolean223) {
                                    var28.method382(class9_1.textColor, i9 + class9_1.width / 2, var36, k9, class9_1.aBoolean268);
                                 } else {
                                    var28.method389(class9_1.aBoolean268, i9, class9_1.textColor, var36, k9);
                                 }
                              }

                              ++k4;
                           }
                        }
                     }
					 else if (class9_1.type == 8
						&& (anInt1500 == class9_1.id
								|| anInt1044 == class9_1.id || anInt1129 == class9_1.id)
						&& anInt1501 == tooltipDelay && !menuOpen) {
                    int boxWidth = 0;
                    int boxHeight = 0;
                    TextDrawingArea textDrawingArea_2 = aTextDrawingArea_1271;
                    for (String s1 = class9_1.message; s1.length() > 0;) {
					if (s1.indexOf("%") != -1) {
							do {
								int k7 = s1.indexOf("%1");
								if (k7 == -1)
									break;
								s1 = s1.substring(0, k7)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 0))
										+ s1.substring(k7 + 2);
							} while (true);
							do {
								int l7 = s1.indexOf("%2");
								if (l7 == -1)
									break;
								s1 = s1.substring(0, l7)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 1))
										+ s1.substring(l7 + 2);
							} while (true);
							do {
								int i8 = s1.indexOf("%3");
								if (i8 == -1)
									break;
								s1 = s1.substring(0, i8)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 2))
										+ s1.substring(i8 + 2);
							} while (true);
							do {
								int j8 = s1.indexOf("%4");
								if (j8 == -1)
									break;
								s1 = s1.substring(0, j8)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 3))
										+ s1.substring(j8 + 2);
							} while (true);
							do {
								int k8 = s1.indexOf("%5");
								if (k8 == -1)
									break;
								s1 = s1.substring(0, k8)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 4))
										+ s1.substring(k8 + 2);
							} while (true);
						}
                        int l7 = s1.indexOf("\\n");
                        String s4;
                        if (l7 != -1) {
                            s4 = s1.substring(0, l7);
                            s1 = s1.substring(l7 + 2);
                        } else {
                            s4 = s1;
                            s1 = "";
                        }
                        int j10 = textDrawingArea_2.getTextWidth(s4);
                        if (j10 > boxWidth) {
                            boxWidth = j10;
                        }
                        boxHeight += textDrawingArea_2.anInt1497 + 1;
                    }
                    boxWidth += 6;
					boxHeight += 7;
					int xPos = (k2 + class9_1.width) - 5 - boxWidth;
					int yPos = l2 + class9_1.height + 5;
					if (xPos < k2 + 5)
						xPos = k2 + 5;
					if (xPos + boxWidth > k + class9.width)
						xPos = (k + class9.width) - boxWidth;
					if (yPos + boxHeight > l + class9.height)
						yPos = (l2 - boxHeight);
					DrawingArea.method336(boxHeight, yPos, xPos, 0xFFFFA0,
							boxWidth);
					DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
                    String s23 = class9_1.message;
                    for (int j11 = yPos + textDrawingArea_2.anInt1497 + 2; s23.length() > 0; j11 += textDrawingArea_2.anInt1497 + 1) {//anInt1497
					if (s23.indexOf("%") != -1) {
							do {
								int k7 = s23.indexOf("%1");
								if (k7 == -1)
									break;
								s23 = s23.substring(0, k7)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 0))
										+ s23.substring(k7 + 2);
							} while (true);
							do {
								int l7 = s23.indexOf("%2");
								if (l7 == -1)
									break;
								s23 = s23.substring(0, l7)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 1))
										+ s23.substring(l7 + 2);
							} while (true);
							do {
								int i8 = s23.indexOf("%3");
								if (i8 == -1)
									break;
								s23 = s23.substring(0, i8)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 2))
										+ s23.substring(i8 + 2);
							} while (true);
							do {
								int j8 = s23.indexOf("%4");
								if (j8 == -1)
									break;
								s23 = s23.substring(0, j8)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 3))
										+ s23.substring(j8 + 2);
							} while (true);
							do {
								int k8 = s23.indexOf("%5");
								if (k8 == -1)
									break;
								s23 = s23.substring(0, k8)
										+ interfaceIntToString(extractInterfaceValues(
												class9_1, 4))
										+ s23.substring(k8 + 2);
							} while (true);
						}
                        int l11 = s23.indexOf("\\n");
                        String s5;
                        if (l11 != -1) {
                            s5 = s23.substring(0, l11);
                            s23 = s23.substring(l11 + 2);
                        } else {
                            s5 = s23;
                            s23 = "";
                        }
                        if (class9_1.aBoolean223) {
							textDrawingArea_2.method382(yPos, xPos
									+ class9_1.width / 2, s5, j11, false);
						} else {
							if (s5.contains("\\r")) {
								String text = s5
										.substring(0, s5.indexOf("\\r"));
								String text2 = s5
										.substring(s5.indexOf("\\r") + 2);
								textDrawingArea_2.method389(false, xPos + 3, 0,
										text, j11);
								int rightX = boxWidth + xPos
										- textDrawingArea_2.getTextWidth(text2)
										- 2;
								textDrawingArea_2.method389(false, rightX, 0,
										text2, j11);
								System.out.println("Box: " + boxWidth + "");
							} else
								textDrawingArea_2.method389(false, xPos + 3, 0,
										s5, j11);
						}
                    }
                }
                  }
               }
            }

            DrawingArea.setDrawingArea(l1, i1, k1, j1);
         }
      }
   }

   public void randomizeBackground(Background background) {
      short j = 256;

      int l1;
      for(l1 = 0; l1 < this.anIntArray1190.length; ++l1) {
         this.anIntArray1190[l1] = 0;
      }

      int j2;
      for(l1 = 0; l1 < 5000; ++l1) {
         j2 = (int)(Math.random() * 128.0D * (double)j);
         this.anIntArray1190[j2] = (int)(Math.random() * 256.0D);
      }

      int l2;
      int i3;
      for(l1 = 0; l1 < 20; ++l1) {
         for(j2 = 1; j2 < j - 1; ++j2) {
            for(l2 = 1; l2 < 127; ++l2) {
               i3 = l2 + (j2 << 7);
               this.anIntArray1191[i3] = (this.anIntArray1190[i3 - 1] + this.anIntArray1190[i3 + 1] + this.anIntArray1190[i3 - 128] + this.anIntArray1190[i3 + 128]) / 4;
            }
         }

         int[] var9 = this.anIntArray1190;
         this.anIntArray1190 = this.anIntArray1191;
         this.anIntArray1191 = var9;
      }

      if(background != null) {
         l1 = 0;

         for(j2 = 0; j2 < background.anInt1453; ++j2) {
            for(l2 = 0; l2 < background.anInt1452; ++l2) {
               if(background.aByteArray1450[l1++] != 0) {
                  i3 = l2 + 16 + background.anInt1454;
                  int j3 = j2 + 16 + background.anInt1455;
                  int k3 = i3 + (j3 << 7);
                  this.anIntArray1190[k3] = 0;
               }
            }
         }
      }

   }

   public void method107(int i, int j, Stream stream, Player player) {
      if((i & 1024) != 0) {
         player.anInt1543 = stream.method428();
         player.anInt1545 = stream.method428();
         player.anInt1544 = stream.method428();
         player.anInt1546 = stream.method428();
         player.anInt1547 = stream.method436() + loopCycle;
         player.anInt1548 = stream.method435() + loopCycle;
         player.anInt1549 = stream.method428();
         player.method446();
      }

      int l1;
      if((i & 256) != 0) {
         player.anInt1520 = stream.method434();
         l1 = stream.readDWord();
         player.anInt1524 = l1 >> 16;
         player.anInt1523 = loopCycle + (l1 & '\uffff');
         player.anInt1521 = 0;
         player.anInt1522 = 0;
         if(player.anInt1523 > loopCycle) {
            player.anInt1521 = -1;
         }

         if(player.anInt1520 == '\uffff') {
            player.anInt1520 = -1;
         }
      }

      int l2;
      int stream_1;
      if((i & 8) != 0) {
         l1 = stream.method434();
         if(l1 == '\uffff') {
            l1 = -1;
         }

         l2 = stream.method427();
         if(l1 == player.anim && l1 != -1) {
            stream_1 = Animation.anims[l1].anInt365;
            if(stream_1 == 1) {
               player.anInt1527 = 0;
               player.anInt1528 = 0;
               player.anInt1529 = l2;
               player.anInt1530 = 0;
            }

            if(stream_1 == 2) {
               player.anInt1530 = 0;
            }
         } else if(l1 == -1 || player.anim == -1 || Animation.anims[l1].anInt359 >= Animation.anims[player.anim].anInt359) {
            player.anim = l1;
            player.anInt1527 = 0;
            player.anInt1528 = 0;
            player.anInt1529 = l2;
            player.anInt1530 = 0;
            player.anInt1542 = player.smallXYIndex;
         }
      }

      if((i & 4) != 0) {
         player.textSpoken = stream.readString();
         if(player.textSpoken.charAt(0) == 126) {
            player.textSpoken = player.textSpoken.substring(1);
            this.pushMessage(player.textSpoken, 2, player.name);
         } else if(player == myPlayer) {
            this.pushMessage(player.textSpoken, 2, player.name);
         }

         player.anInt1513 = 0;
         player.anInt1531 = 0;
         player.textCycle = 150;
      }

      if((i & 128) != 0) {
         l1 = stream.method434();
         l2 = stream.readUnsignedByte();
         stream_1 = stream.method427();
         int k3 = stream.currentOffset;
         if(player.name != null && player.visible) {
            long l3 = TextClass.longForName(player.name);
            boolean flag = false;
            if(l2 <= 1 && l2 >= 10) {
               for(int exception = 0; exception < this.ignoreCount; ++exception) {
                  if(this.ignoreListAsLongs[exception] == l3) {
                     flag = true;
                     break;
                  }
               }
            }

            if(!flag && this.anInt1251 == 0) {
               try {
                  this.aStream_834.currentOffset = 0;
                  stream.method442(stream_1, 0, this.aStream_834.buffer);
                  this.aStream_834.currentOffset = 0;
                  String var16 = TextInput.method525(stream_1, this.aStream_834);
                  var16 = Censor.doCensor(var16);
                  player.textSpoken = var16;
                  player.anInt1513 = l1 >> 8;
				  int rights = l2;
				  if(l2 >= 10){
					player.isDonor = true;
					rights -= 10;
				  }
				  player.privelage = rights;
                  player.anInt1531 = l1 & 255;
                  player.textCycle = 150;
                  if(l2 != 2 && l2 != 3) {
                     if(l2 == 1) {
                        this.pushMessage(var16, 1, "@cr1@" + player.name);
                     } else {
						if(player.isDonor)
							this.pushMessage(var16, 2, "@don1@" + player.name);
						else
							this.pushMessage(var16, 2, player.name);
                     }
                  } else {
                     this.pushMessage(var16, 1, "@cr2@" + player.name);
                  }
               } catch (Exception var13) {
                  SignLink.reporterror("cde2");
               }
            }
         }

         stream.currentOffset = k3 + stream_1;
      }

      if((i & 1) != 0) {
         player.interactingEntity = stream.method434();
         if(player.interactingEntity == '\uffff') {
            player.interactingEntity = -1;
         }
      }

      if((i & 16) != 0) {
         l1 = stream.method427();
         byte[] var14 = new byte[l1];
         Stream var15 = new Stream(var14);
         stream.readBytes(l1, 0, var14);
         this.aStreamArray895s[j] = var15;
         player.updatePlayer(var15);
      }

      if((i & 2) != 0) {
         player.anInt1538 = stream.method436();
         player.anInt1539 = stream.method434();
      }

      if((i & 32) != 0) {
         l1 = stream.readUnsignedByte();
         l2 = stream.method426();
         player.updateHitData(l2, l1, loopCycle);
         player.loopCycleStatus = loopCycle + 300;
         player.currentHealth = stream.method427();
         player.maxHealth = stream.readUnsignedByte();
      }

      if((i & 512) != 0) {
         l1 = stream.readUnsignedByte();
         l2 = stream.method428();
         player.updateHitData(l2, l1, loopCycle);
         player.loopCycleStatus = loopCycle + 300;
         player.currentHealth = stream.readUnsignedByte();
         player.maxHealth = stream.method427();
      }

   }

   public void method108() {
      try {
         int _ex = myPlayer.x + this.anInt1278;
         int k = myPlayer.y + this.anInt1131;
         if(this.anInt1014 - _ex < -500 || this.anInt1014 - _ex > 500 || this.anInt1015 - k < -500 || this.anInt1015 - k > 500) {
            this.anInt1014 = _ex;
            this.anInt1015 = k;
         }

         if(this.anInt1014 != _ex) {
            this.anInt1014 += (_ex - this.anInt1014) / 16;
         }

         if(this.anInt1015 != k) {
            this.anInt1015 += (k - this.anInt1015) / 16;
         }

         if(super.keyArray[1] == 1) {
            this.anInt1186 += (-24 - this.anInt1186) / 2;
         } else if(super.keyArray[2] == 1) {
            this.anInt1186 += (24 - this.anInt1186) / 2;
         } else {
            this.anInt1186 /= 2;
         }

         if(super.keyArray[3] == 1) {
            this.anInt1187 += (12 - this.anInt1187) / 2;
         } else if(super.keyArray[4] == 1) {
            this.anInt1187 += (-12 - this.anInt1187) / 2;
         } else {
            this.anInt1187 /= 2;
         }

         this.minimapInt1 = this.minimapInt1 + this.anInt1186 / 2 & 2047;
         this.anInt1184 += this.anInt1187 / 2;
         if(this.anInt1184 < 128) {
            this.anInt1184 = 128;
         }

         if(this.anInt1184 > 383) {
            this.anInt1184 = 383;
         }

         int l = this.anInt1014 >> 7;
         int i1 = this.anInt1015 >> 7;
         int j1 = this.method42(this.plane, this.anInt1015, this.anInt1014);
         int k1 = 0;
         int j2;
         if(l > 3 && i1 > 3 && l < 100 && i1 < 100) {
            for(j2 = l - 4; j2 <= l + 4; ++j2) {
               for(int k2 = i1 - 4; k2 <= i1 + 4; ++k2) {
                  int l2 = this.plane;
                  if(l2 < 3 && (this.byteGroundArray[1][j2][k2] & 2) == 2) {
                     ++l2;
                  }

                  int i3 = j1 - this.intGroundArray[l2][j2][k2];
                  if(i3 > k1) {
                     k1 = i3;
                  }
               }
            }
         }

			if (!editMode) {
				++anInt1005;
				if (anInt1005 > 1512) {
					anInt1005 = 0;
					this.stream.createFrame(77);
					this.stream.writeWordBigEndian(0);
					j2 = this.stream.currentOffset;
					this.stream
							.writeWordBigEndian((int) (Math.random() * 256.0D));
					this.stream.writeWordBigEndian(101);
					this.stream.writeWordBigEndian(233);
					this.stream.writeWord('\ub024');
					if ((int) (Math.random() * 2.0D) == 0) {
						this.stream.writeWord('\u8bc8');
					}

					this.stream
							.writeWordBigEndian((int) (Math.random() * 256.0D));
					this.stream.writeWordBigEndian(64);
					this.stream.writeWordBigEndian(38);
					this.stream.writeWord((int) (Math.random() * 65536.0D));
					this.stream.writeWord((int) (Math.random() * 65536.0D));
					this.stream.writeBytes(this.stream.currentOffset - j2);
				}
			}

         j2 = k1 * 192;
         if(j2 > 98048) {
            j2 = 98048;
         }

         if(j2 < '\u8000') {
            j2 = '\u8000';
         }

         if(j2 > this.anInt984) {
            this.anInt984 += (j2 - this.anInt984) / 24;
         } else {
            if(j2 < this.anInt984) {
               this.anInt984 += (j2 - this.anInt984) / 80;
            }

         }
      } catch (Exception var11) {
         SignLink.reporterror("glfc_ex " + myPlayer.x + "," + myPlayer.y + "," + this.anInt1014 + "," + this.anInt1015 + "," + this.anInt1069 + "," + this.anInt1070 + "," + this.baseX + "," + this.baseY);
         throw new RuntimeException("eek");
      }
   }

   public void processDrawing() {
      if(!this.rsAlreadyLoaded && !this.loadingError && !this.genericLoadingError) {
         ++anInt1061;
         if(!this.loggedIn) {
            this.drawLoginScreen(false);
         } else {
            this.drawGameScreen();
         }

         this.anInt1213 = 0;
      } else {
         this.showErrorScreen();
      }
   }

   public boolean isFriendOrSelf(String s) {
      if(s == null) {
         return false;
      } else {
         for(int i = 0; i < this.friendsCount; ++i) {
            if(s.equalsIgnoreCase(this.friendsList[i])) {
               return true;
            }
         }

         return s.equalsIgnoreCase(myPlayer.name);
      }
   }

   public static String combatDiffColor(int i, int j) {
      int k = i - j;
      return k < -9?"@red@":(k < -6?"@or3@":(k < -3?"@or2@":(k < 0?"@or1@":(k > 9?"@gre@":(k > 6?"@gr3@":(k > 3?"@gr2@":(k > 0?"@gr1@":"@yel@")))))));
   }

   public void setWaveVolume(int i) {
      SignLink.wavevol = i;
   }

   public void draw3dScreen() {
      this.drawSplitpublicChat();
      if(this.crossType == 1) {
         this.crosses[this.crossIndex / 100].drawSprite(this.crossX - 8 - 4, this.crossY - 8 - 4);
			if (!editMode) {
				++anInt1142;
				if (anInt1142 > 67) {
					anInt1142 = 0;
					this.stream.createFrame(78);
				}
			}
      }

      if(this.crossType == 2) {
         this.crosses[4 + this.crossIndex / 100].drawSprite(this.crossX - 8 - 4, this.crossY - 8 - 4);
      }

      if(this.anInt1018 != -1) {
         this.method119(this.anInt945, this.anInt1018);
         this.drawInterface(0, 0, RSInterface.interfaceCache[this.anInt1018], 0);
      }

      if(this.openInterfaceID != -1) {
         this.method119(this.anInt945, this.openInterfaceID);
         this.drawInterface(0, 0, RSInterface.interfaceCache[this.openInterfaceID], 0);
      }

      this.method70();
      if(!this.menuOpen) {
         this.processRightClick();
         this.drawTooltip();
      } else if(this.menuScreenArea == 0) {
         this.drawMenu();
      }

      if(this.anInt1055 == 1) {
         this.multiOverlay.drawSprite(472, 296);
      }

      int var7;
      if(fpsOn) {
         short j = 507;
         byte l = 20;
         int i1 = 16776960;
         if(super.fps < 15) {
            i1 = 16711680;
         }

         this.aTextDrawingArea_1271.method380("Fps:" + super.fps, j, i1, l);
         var7 = l + 15;
         Runtime runtime = Runtime.getRuntime();
         int j1 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
         i1 = 16776960;
         if(j1 > 33554432 && lowMem) {
            i1 = 16711680;
         }
		 
		 Calendar now = Calendar.getInstance();
		now.setTimeZone(timeZone);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
			/*System.out.println(hour+":"+minute);
			loopCycle % 20 < 10)*/

         this.aTextDrawingArea_1271.method380("Mem:" + j1 + "k", j, 16776960, var7);
         var7 += 15;
		 String hs = hour < 10 ? "0"+hour : ""+hour;
		 String ms = minute < 10 ? "0"+minute : ""+minute;
		 this.aTextDrawingArea_1271.method380("Server time: "+hs+":"+ms, j, 16776960, var7);
         var7 += 15;
      }

      if(this.anInt1104 != 0) {
         int var6 = this.anInt1104 / 50;
         var7 = var6 / 60;
         var6 %= 60;
         if(var6 < 10) {
            this.aTextDrawingArea_1271.method385(16776960, "System update in: " + var7 + ":0" + var6, 329, 4);
         } else {
            this.aTextDrawingArea_1271.method385(16776960, "System update in: " + var7 + ":" + var6, 329, 4);
         }

         ++anInt849;
         if(anInt849 > 75) {
            anInt849 = 0;
            this.stream.createFrame(148);
         }
      }

   }

   public void addIgnore(long l) {
      try {
         if(l != 0L) {
            if(this.ignoreCount >= 100) {
               this.pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
            } else {
               String runtimeexception = TextClass.fixName(TextClass.nameForLong(l));

               int k;
               for(k = 0; k < this.ignoreCount; ++k) {
                  if(this.ignoreListAsLongs[k] == l) {
                     this.pushMessage(runtimeexception + " is already on your ignore list", 0, "");
                     return;
                  }
               }

               for(k = 0; k < this.friendsCount; ++k) {
                  if(this.friendsListAsLongs[k] == l) {
                     this.pushMessage("Please remove " + runtimeexception + " from your friend list first", 0, "");
                     return;
                  }
               }

               this.ignoreListAsLongs[this.ignoreCount++] = l;
               this.needDrawTabArea = true;
               this.stream.createFrame(133);
               this.stream.writeQWord(l);
            }
         }
      } catch (RuntimeException var5) {
         SignLink.reporterror("45688, " + l + ", " + 4 + ", " + var5.toString());
         throw new RuntimeException();
      }
   }

   public void method114() {
      for(int i = -1; i < this.playerCount; ++i) {
         int j;
         if(i == -1) {
            j = this.myPlayerIndex;
         } else {
            j = this.playerIndices[i];
         }

         Player player = this.playerArray[j];
         if(player != null) {
            this.method96(player);
         }
      }

   }

   public void method115() {
      if(this.loadingStage == 2) {
         for(Class30_Sub1 class30_sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
            if(class30_sub1.anInt1294 > 0) {
               --class30_sub1.anInt1294;
            }

            if(class30_sub1.anInt1294 == 0) {
               if(class30_sub1.anInt1299 < 0 || ObjectManager.method178(class30_sub1.anInt1299, class30_sub1.anInt1301)) {
                  this.method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1300, class30_sub1.anInt1301, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1299);
                  class30_sub1.unlink();
               }
            } else {
               if(class30_sub1.anInt1302 > 0) {
                  --class30_sub1.anInt1302;
               }

               if(class30_sub1.anInt1302 == 0 && class30_sub1.anInt1297 >= 1 && class30_sub1.anInt1298 >= 1 && class30_sub1.anInt1297 <= 102 && class30_sub1.anInt1298 <= 102 && (class30_sub1.anInt1291 < 0 || ObjectManager.method178(class30_sub1.anInt1291, class30_sub1.anInt1293))) {
                  this.method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1292, class30_sub1.anInt1293, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1291);
                  class30_sub1.anInt1302 = -1;
                  if(class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1299 == -1) {
                     class30_sub1.unlink();
                  } else if(class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1292 == class30_sub1.anInt1300 && class30_sub1.anInt1293 == class30_sub1.anInt1301) {
                     class30_sub1.unlink();
                  }
               }
            }
         }
      }

   }

   public void determineMenuSize() {
      int i = this.chatTextDrawingArea.getTextWidth("Choose Option");

      int l;
      int k1;
      for(l = 0; l < this.menuActionRow; ++l) {
         k1 = this.chatTextDrawingArea.getTextWidth(this.menuActionName[l]);
         if(k1 > i) {
            i = k1;
         }
      }

      i += 8;
      l = 15 * this.menuActionRow + 21;
      int j2;
	  if (fullscreenInterfaceID == -1) {
      if(super.saveClickX > 4 && super.saveClickY > 4 && super.saveClickX < 516 && super.saveClickY < 338) {
         k1 = super.saveClickX - 4 - i / 2;
         if(k1 + i > 512) {
            k1 = 512 - i;
         }

         if(k1 < 0) {
            k1 = 0;
         }

         j2 = super.saveClickY - 4;
         if(j2 + l > 334) {
            j2 = 334 - l;
         }

         if(j2 < 0) {
            j2 = 0;
         }

         this.menuOpen = true;
         this.menuScreenArea = 0;
         this.menuOffsetX = k1;
         this.menuOffsetY = j2;
         this.menuWidth = i;
         this.anInt952 = 15 * this.menuActionRow + 22;
      }
	if(gameframeVersion != 474){
      if(super.saveClickX > 553 && super.saveClickY > 205 && super.saveClickX < 743 && super.saveClickY < 466) {
         k1 = super.saveClickX - 553 - i / 2;
         if(k1 < 0) {
            k1 = 0;
         } else if(k1 + i > invBackWidth) {
            k1 = invBackWidth - i;
         }

         j2 = super.saveClickY - 205;
         if(j2 < 0) {
            j2 = 0;
         } else if(j2 + l > 261) {
            j2 = 261 - l;
         }

         this.menuOpen = true;
         this.menuScreenArea = 1;
         this.menuOffsetX = k1;
         this.menuOffsetY = j2;
         this.menuWidth = i;
         this.anInt952 = 15 * this.menuActionRow + 22;
      }
	}else{
		if(super.saveClickX > 547 && super.saveClickY > 205 && super.saveClickX < 743 && super.saveClickY < 466) {
         k1 = super.saveClickX - 547 - i / 2;
         if(k1 < 0) {
            k1 = 0;
         } else if(k1 + i > invBackWidth) {
            k1 = invBackWidth - i;
         }

         j2 = super.saveClickY - 205;
         if(j2 < 0) {
            j2 = 0;
         } else if(j2 + l > 261) {
            j2 = 261 - l;
         }

         this.menuOpen = true;
         this.menuScreenArea = 1;
         this.menuOffsetX = k1;
         this.menuOffsetY = j2;
         this.menuWidth = i;
         this.anInt952 = 15 * this.menuActionRow + 22;
      }
	}
	  if(gameframeVersion != 474){
      if(super.saveClickX > 17 && super.saveClickY > 357 && super.saveClickX < 496 && super.saveClickY < 453) {
         k1 = super.saveClickX - 17 - i / 2;
         if(k1 < 0) {
            k1 = 0;
         } else if(k1 + i > chatBackWidth) {
            k1 = chatBackWidth - i;
         }

         j2 = super.saveClickY - 357;
         if(j2 < 0) {
            j2 = 0;
         } else if(j2 + l > chatBackHeight) {
            j2 = chatBackHeight - l;
         }

         this.menuOpen = true;
         this.menuScreenArea = 2;
         this.menuOffsetX = k1;
         this.menuOffsetY = j2;
         this.menuWidth = i;
         this.anInt952 = 15 * this.menuActionRow + 22;
      }
	  }else{
	  if(super.saveClickX > 7 && super.saveClickY > 345 && super.saveClickX < 512 && super.saveClickY < 500) {
         k1 = super.saveClickX - 7 - i / 2;
         if(k1 < 0) {
            k1 = 0;
         } else if(k1 + i > chatBackWidth) {
            k1 = chatBackWidth - i;
         }

         j2 = super.saveClickY - 345;
         if(j2 < 0) {
            j2 = 0;
         } else if(j2 + l > chatBackHeight) {
            j2 = chatBackHeight - l;
         }

         this.menuOpen = true;
         this.menuScreenArea = 2;
         this.menuOffsetX = k1;
         this.menuOffsetY = j2;
         this.menuWidth = i;
         this.anInt952 = 15 * this.menuActionRow + 22;
      }
	  }
	  } else {//context menus for fullscreen interfaces
			if (super.saveClickX > 0 && super.saveClickY > 0
					&& super.saveClickX < 765
					&& super.saveClickY < 503) {
				int i1 = super.saveClickX - 0 - i / 2;
				if (i1 + i > 765)
					i1 = 765 - i;
				if (i1 < 0)
					i1 = 0;
				int l1 = super.saveClickY - 0;
				if (l1 + l > 503)
					l1 = 503 - l;
				if (l1 < 0)
					l1 = 0;
				menuOpen = true;
				menuScreenArea = 0;
				menuOffsetX = i1;
				menuOffsetY = l1;
				menuWidth = i;
				anInt952 = 15 * menuActionRow + 22;
			}		
		}

   }

   public void method117(Stream stream) {
      stream.initBitAccess();
      int j = stream.readBits(1);
      if(j != 0) {
         int k = stream.readBits(2);
         if(k == 0) {
            this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
         } else {
            int j1;
            int i2;
            if(k == 1) {
               j1 = stream.readBits(3);
               myPlayer.moveInDir(false, j1);
               i2 = stream.readBits(1);
               if(i2 == 1) {
                  this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
               }

            } else {
               int k2;
               if(k == 2) {
                  j1 = stream.readBits(3);
                  myPlayer.moveInDir(true, j1);
                  i2 = stream.readBits(3);
                  myPlayer.moveInDir(true, i2);
                  k2 = stream.readBits(1);
                  if(k2 == 1) {
                     this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
                  }

               } else {
                  if(k == 3) {
                     this.plane = stream.readBits(2);
                     j1 = stream.readBits(1);
                     i2 = stream.readBits(1);
                     if(i2 == 1) {
                        this.anIntArray894[this.anInt893++] = this.myPlayerIndex;
                     }

                     k2 = stream.readBits(7);
                     int l2 = stream.readBits(7);
                     myPlayer.setPos(l2, k2, j1 == 1);
                  }

               }
            }
         }
      }
   }

   public void nullLoader() {
      this.aBoolean831 = false;

      while(this.drawingFlames) {
         this.aBoolean831 = false;

         try {
            Thread.sleep(50L);
         } catch (Exception var2) {
            ;
         }
      }

      this.aBackground_966 = null;
      this.aBackground_967 = null;
      this.aBackgroundArray1152s = null;
      this.anIntArray850 = null;
      this.anIntArray851 = null;
      this.anIntArray852 = null;
      this.anIntArray853 = null;
      this.anIntArray1190 = null;
      this.anIntArray1191 = null;
      this.anIntArray828 = null;
      this.anIntArray829 = null;
      this.aClass30_Sub2_Sub1_Sub1_1201 = null;
      this.aClass30_Sub2_Sub1_Sub1_1202 = null;
   }

   public boolean method119(int i, int j) {
      boolean flag1 = false;
      RSInterface class9 = RSInterface.interfaceCache[j];
      int[] arr$ = class9.children;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int element = arr$[i$];
         if(element == -1) {
            break;
         }

         RSInterface class9_1 = RSInterface.interfaceCache[element];
         if(class9_1.type == 1) {
            flag1 |= this.method119(i, class9_1.id);
         }

         if(class9_1.type == 6 && (class9_1.anInt257 != -1 || class9_1.anInt258 != -1)) {
            boolean flag2 = this.interfaceIsSelected(class9_1);
            int l;
            if(flag2) {
               l = class9_1.anInt258;
            } else {
               l = class9_1.anInt257;
            }

            if(l != -1) {
               Animation animation = Animation.anims[l];

               for(class9_1.anInt208 += i; class9_1.anInt208 > animation.method258(class9_1.anInt246); flag1 = true) {
                  class9_1.anInt208 -= animation.method258(class9_1.anInt246) + 1;
                  ++class9_1.anInt246;
                  if(class9_1.anInt246 >= animation.anInt352) {
                     class9_1.anInt246 -= animation.anInt356;
                     if(class9_1.anInt246 < 0 || class9_1.anInt246 >= animation.anInt352) {
                        class9_1.anInt246 = 0;
                     }
                  }
               }
            }
         }
      }

      return flag1;
   }

   public int method120() {
      int j = 3;
      if(this.yCameraCurve < 310) {
         int k = this.xCameraPos >> 7;
         int l = this.yCameraPos >> 7;
         int i1 = myPlayer.x >> 7;
         int j1 = myPlayer.y >> 7;
         if((this.byteGroundArray[this.plane][k][l] & 4) != 0) {
            j = this.plane;
         }

         int k1;
         if(i1 > k) {
            k1 = i1 - k;
         } else {
            k1 = k - i1;
         }

         int l1;
         if(j1 > l) {
            l1 = j1 - l;
         } else {
            l1 = l - j1;
         }

         int j2;
         int l2;
         if(k1 > l1) {
            j2 = l1 * 65536 / k1;
            l2 = '\u8000';

            while(k != i1) {
               if(k < i1) {
                  ++k;
               } else if(k > i1) {
                  --k;
               }

               if((this.byteGroundArray[this.plane][k][l] & 4) != 0) {
                  j = this.plane;
               }

               l2 += j2;
               if(l2 >= 65536) {
                  l2 -= 65536;
                  if(l < j1) {
                     ++l;
                  } else if(l > j1) {
                     --l;
                  }

                  if((this.byteGroundArray[this.plane][k][l] & 4) != 0) {
                     j = this.plane;
                  }
               }
            }
         } else {
            j2 = k1 * 65536 / l1;
            l2 = '\u8000';

            while(l != j1) {
               if(l < j1) {
                  ++l;
               } else if(l > j1) {
                  --l;
               }

               if((this.byteGroundArray[this.plane][k][l] & 4) != 0) {
                  j = this.plane;
               }

               l2 += j2;
               if(l2 >= 65536) {
                  l2 -= 65536;
                  if(k < i1) {
                     ++k;
                  } else if(k > i1) {
                     --k;
                  }

                  if((this.byteGroundArray[this.plane][k][l] & 4) != 0) {
                     j = this.plane;
                  }
               }
            }
         }
      }

      if((this.byteGroundArray[this.plane][myPlayer.x >> 7][myPlayer.y >> 7] & 4) != 0) {
         j = this.plane;
      }

      return j;
   }

   public int method121() {
      int j = this.method42(this.plane, this.yCameraPos, this.xCameraPos);
      return j - this.zCameraPos < 800 && (this.byteGroundArray[this.plane][this.xCameraPos >> 7][this.yCameraPos >> 7] & 4) != 0?this.plane:3;
   }

   public void delIgnore(long l) {
      try {
         if(l != 0L) {
            for(int runtimeexception = 0; runtimeexception < this.ignoreCount; ++runtimeexception) {
               if(this.ignoreListAsLongs[runtimeexception] == l) {
                  --this.ignoreCount;
                  this.needDrawTabArea = true;
                  System.arraycopy(this.ignoreListAsLongs, runtimeexception + 1, this.ignoreListAsLongs, runtimeexception, this.ignoreCount - runtimeexception);
                  this.stream.createFrame(74);
                  this.stream.writeQWord(l);
                  return;
               }
            }

         }
      } catch (RuntimeException var4) {
         SignLink.reporterror("47229, 3, " + l + ", " + var4.toString());
         throw new RuntimeException();
      }
   }

   public String getParameter(String s) {
      return SignLink.mainapp != null?SignLink.mainapp.getParameter(s):super.getParameter(s);
   }

   public void adjustVolume(boolean flag, int i) {
      SignLink.midivol = i;
      if(flag) {
         SignLink.midi = "voladjust";
      }

   }

   public int extractInterfaceValues(RSInterface class9, int j) {
      if(class9.valueIndexArray != null && j < class9.valueIndexArray.length) {
         try {
            int[] _ex = class9.valueIndexArray[j];
            int k = 0;
            int l = 0;
            byte i1 = 0;

            while(true) {
               int j1 = _ex[l++];
               int k1 = 0;
               byte byte0 = 0;
               if(j1 == 0) {
                  return k;
               }

               if(j1 == 1) {
                  k1 = this.currentStats[_ex[l++]];
               }

               if(j1 == 2) {
                  k1 = this.maxStats[_ex[l++]];
               }

               if(j1 == 3) {
                  k1 = this.currentExp[_ex[l++]];
               }

               RSInterface j2;
               int varBit;
               int l3;
               if(j1 == 4) {
                  j2 = RSInterface.interfaceCache[_ex[l++]];
                  varBit = _ex[l++];
                  if(varBit >= 0 && varBit < ItemDef.totalItems && (!ItemDef.forID(varBit).membersObject || isMembers)) {
                     for(l3 = 0; l3 < j2.inv.length; ++l3) {
                        if(j2.inv[l3] == varBit + 1) {
                           k1 += j2.invStackSizes[l3];
                        }
                     }
                  }
               }

               if(j1 == 5) {
                  k1 = this.variousSettings[_ex[l++]];
               }

               if(j1 == 6) {
                  k1 = anIntArray1019[this.maxStats[_ex[l++]] - 1];
               }

               if(j1 == 7) {
                  k1 = this.variousSettings[_ex[l++]] * 100 / '\ub71b';
               }

               if(j1 == 8) {
                  k1 = myPlayer.combatLevel;
               }

               int var17;
               if(j1 == 9) {
                  for(var17 = 0; var17 < 25; ++var17) {
                     if(Skills.skillEnabled[var17]) {
                        k1 += this.maxStats[var17];
                     }
                  }
               }

               int i4;
               int j4;
               int k4;
               if(j1 == 10) {
                  j2 = RSInterface.interfaceCache[_ex[l++]];
                  varBit = _ex[l++] + 1;
                  if(varBit >= 0 && varBit < ItemDef.totalItems && (!ItemDef.forID(varBit).membersObject || isMembers)) {
                     int[] var18 = j2.inv;
                     i4 = var18.length;

                     for(j4 = 0; j4 < i4; ++j4) {
                        k4 = var18[j4];
                        if(k4 == varBit) {
                           k1 = 999999999;
                           break;
                        }
                     }
                  }
               }

               if(j1 == 11) {
                  k1 = this.energy;
               }

               if(j1 == 12) {
                  k1 = this.weight;
               }

               if(j1 == 13) {
                  var17 = this.variousSettings[_ex[l++]];
                  varBit = _ex[l++];
                  k1 = (var17 & 1 << varBit) == 0?0:1;
               }

               if(j1 == 14) {
                  var17 = _ex[l++];
                  VarBit var19 = VarBit.cache[var17];
                  l3 = var19.anInt648;
                  i4 = var19.anInt649;
                  j4 = var19.anInt650;
                  k4 = anIntArray1232[j4 - i4];
                  k1 = this.variousSettings[l3] >> i4 & k4;
               }

               if(j1 == 15) {
                  byte0 = 1;
               }

               if(j1 == 16) {
                  byte0 = 2;
               }

               if(j1 == 17) {
                  byte0 = 3;
               }

               if(j1 == 18) {
                  k1 = (myPlayer.x >> 7) + this.baseX;
               }

               if(j1 == 19) {
                  k1 = (myPlayer.y >> 7) + this.baseY;
               }

               if(j1 == 20) {
                  k1 = _ex[l++];
               }

               if(byte0 == 0) {
                  if(i1 == 0) {
                     k += k1;
                  }

                  if(i1 == 1) {
                     k -= k1;
                  }

                  if(i1 == 2 && k1 != 0) {
                     k /= k1;
                  }

                  if(i1 == 3) {
                     k *= k1;
                  }

                  i1 = 0;
               } else {
                  i1 = byte0;
               }
            }
         } catch (Exception var16) {
            return -1;
         }
      } else {
         return -2;
      }
   }

   public void drawTooltip() {
      if(this.menuActionRow >= 2 || this.itemSelected != 0 || this.spellSelected != 0) {
         String s;
         if(this.itemSelected == 1 && this.menuActionRow < 2) {
            s = "Use " + this.selectedItemName + " with...";
         } else if(this.spellSelected == 1 && this.menuActionRow < 2) {
            s = this.spellTooltip + "...";
         } else {
            s = this.menuActionName[this.menuActionRow - 1];
         }

         if(this.menuActionRow > 2) {
            s = s + "@whi@ / " + (this.menuActionRow - 2) + " more options";
         }

         this.chatTextDrawingArea.method390(4, 16777215, s, loopCycle / 1000, 15);
      }
   }

   static final void method368(int i) {
      if(aClass56_749 != null) {
         if(anInt478 < i) {
            if(anInt720 > 0) {
               --anInt720;
               if(anInt720 == 0) {
                  if(aByteArray347 == null) {
                     aClass56_749.method831(256);
                  } else {
                     aClass56_749.method831(anInt1478);
                     anInt478 = anInt1478;
                     aClass56_749.method827(anInt1478, aByteArray347, 0, aBoolean475);
                     aByteArray347 = null;
                  }

                  anInt155 = 0;
               }
            }
         } else if(anInt720 > 0) {
            anInt155 += anInt2200;
            aClass56_749.method830(anInt478, anInt155);
            --anInt720;
            if(anInt720 == 0) {
               aClass56_749.method833();
               anInt720 = 20;
               anInt478 = -1;
            }
         }

         aClass56_749.method832(i - 122);
      }

   }

   public void drawMinimap() {
      this.aRSImageProducer_1164.initDrawingArea();
      int l2;
      int j2;
      if(this.anInt1021 == 2) {
         byte[] var12 = mapBack.aByteArray1450;
         int[] var13 = DrawingArea.pixels;
         l2 = var12.length;

         for(j2 = 0; j2 < l2; ++j2) {
            if(var12[j2] == 0) {
               var13[j2] = 0;
            }
         }

         compass.method352(33, this.minimapInt1, this.anIntArray1057, 256, this.anIntArray968, 25, 0, 0, 33, 25);
         this.aRSImageProducer_1165.initDrawingArea();
      } else {
         int i = this.minimapInt1 + this.minimapInt2 & 2047;
         int j = 48 + myPlayer.x / 32;
         l2 = 464 - myPlayer.y / 32;
         this.aClass30_Sub2_Sub1_Sub1_1263.method352(151, i, this.anIntArray1229, 256 + this.minimapInt3, this.anIntArray1052, l2, 5, 25, 146, j);
         compass.method352(33, this.minimapInt1, this.anIntArray1057, 256, this.anIntArray968, 25, 0, 0, 33, 25);

         int l4;
         int k4;
         for(j2 = 0; j2 < this.anInt1071; ++j2) {
            l4 = this.anIntArray1072[j2] * 4 + 2 - myPlayer.x / 32;
            k4 = this.anIntArray1073[j2] * 4 + 2 - myPlayer.y / 32;
            this.markMinimap(this.aClass30_Sub2_Sub1_Sub1Array1140[j2], l4, k4);
         }

         int l3;
         int flag1;
         for(j2 = 0; j2 < 104; ++j2) {
            for(l4 = 0; l4 < 104; ++l4) {
               NodeList var18 = this.groundArray[this.plane][j2][l4];
               if(var18 != null) {
                  l3 = j2 * 4 + 2 - myPlayer.x / 32;
                  flag1 = l4 * 4 + 2 - myPlayer.y / 32;
                  this.markMinimap(this.mapDotItem, l3, flag1);
               }
            }
         }

         for(j2 = 0; j2 < this.npcCount; ++j2) {
            NPC var14 = this.npcArray[this.npcIndices[j2]];
            if(var14 != null && var14.isVisible()) {
               EntityDef var15 = var14.desc;
               if(var15.childrenIDs != null) {
                  var15 = var15.method161();
               }

               if(var15 != null && var15.aBoolean87 && var15.aBoolean84) {
                  l3 = var14.x / 32 - myPlayer.x / 32;
                  flag1 = var14.y / 32 - myPlayer.y / 32;
                  this.markMinimap(this.mapDotNPC, l3, flag1);
               }
            }
         }

         for(j2 = 0; j2 < this.playerCount; ++j2) {
            Player var17 = this.playerArray[this.playerIndices[j2]];
            if(var17 != null && var17.isVisible()) {
               k4 = var17.x / 32 - myPlayer.x / 32;
               l3 = var17.y / 32 - myPlayer.y / 32;
               boolean var20 = false;
               long l6 = TextClass.longForName(var17.name);
			   boolean isStaff = false;
			   
			   if(var17.name.toLowerCase().startsWith("mod "))
					isStaff = true;

               for(int flag2 = 0; flag2 < this.friendsCount; ++flag2) {
                  if(l6 == this.friendsListAsLongs[flag2] && this.friendsNodeIDs[flag2] != 0) {
                     var20 = true;
                     break;
                  }
               }

               boolean var21 = false;
               if(myPlayer.team != 0 && var17.team != 0 && myPlayer.team == var17.team) {
                  var21 = true;
               }

			   if(!isStaff){
					if(var20) {
						this.markMinimap(this.mapDotFriend, k4, l3);
					} else if(var21) {
						this.markMinimap(this.mapDotTeam, k4, l3);
					} else {
						this.markMinimap(this.mapDotPlayer, k4, l3);
					}
				}else
					this.markMinimap(this.mapDotStaff, k4, l3);
            }
         }

         if(this.anInt855 != 0 && loopCycle % 20 < 10) {
            if(this.anInt855 == 1 && this.anInt1222 >= 0 && this.anInt1222 < this.npcArray.length) {
               NPC var16 = this.npcArray[this.anInt1222];
               if(var16 != null) {
                  l4 = var16.x / 32 - myPlayer.x / 32;
                  k4 = var16.y / 32 - myPlayer.y / 32;
                  this.method81(this.mapMarker, k4, l4);
               }
            }

            if(this.anInt855 == 2) {
               j2 = (this.anInt934 - this.baseX) * 4 + 2 - myPlayer.x / 32;
               l4 = (this.anInt935 - this.baseY) * 4 + 2 - myPlayer.y / 32;
               this.method81(this.mapMarker, l4, j2);
            }

            if(this.anInt855 == 10 && this.anInt933 >= 0 && this.anInt933 < this.playerArray.length) {
               Player var19 = this.playerArray[this.anInt933];
               if(var19 != null) {
                  l4 = var19.x / 32 - myPlayer.x / 32;
                  k4 = var19.y / 32 - myPlayer.y / 32;
                  this.method81(this.mapMarker, k4, l4);
               }
            }
         }

         if(this.destX != 0) {
            j2 = this.destX * 4 + 2 - myPlayer.x / 32;
            l4 = this.destY * 4 + 2 - myPlayer.y / 32;
            this.markMinimap(this.mapFlag, j2, l4);
         }

         DrawingArea.method336(3, 78, 97, 16777215, 3);
         this.aRSImageProducer_1165.initDrawingArea();
      }
   }

   public void npcScreenPos(Entity entity, int i) {
      this.calcEntityScreenPos(entity.x, i, entity.y);
   }
   
   public void itemScreenPos() {
	   for(int j2 = 0; j2 < 104; ++j2) {
           for(int l4 = 0; l4 < 104; ++l4) {
              NodeList var18 = this.groundArray[this.plane][j2][l4];
              if(var18 != null) {
            	  Item var30;
            	  for(var30 = (Item)var18.reverseGetFirst(); var30 != null; var30 = (Item)var18.reverseGetNext()) {
            	
            	ItemDef var33 = ItemDef.forID(var30.ID);
            	  int x = j2 * 128 + 1 * 64;
            	  int y = l4 * 128 + 1 * 64;
            	  this.calcEntityScreenPos(x, 100, y);
            	  String s = var33.name;
            	  if(var30.anInt1559 > 1)
            		  s += " ("+var30.anInt1559+")";
            	  ItemSpawn itemSpawn = var30.itemSpawn;
					s += itemSpawn.isMembersOnly() ? " - P2P" : " - F2P";
					this.chatTextDrawingArea.drawText(0, s, this.spriteDrawY + 5, this.spriteDrawX);
					this.chatTextDrawingArea.drawText(16748608, s, this.spriteDrawY + 4, this.spriteDrawX);
            	  }
              }
           }
        }
	   }

   static final void method853(int i_2_, byte[] is, boolean bool) {
      if(aClass56_749 != null) {
         if(anInt478 >= 0) {
            aClass56_749.method833();
            anInt478 = -1;
            aByteArray347 = null;
            anInt720 = 20;
            anInt155 = 0;
         }

         if(is != null) {
            if(anInt720 > 0) {
               aClass56_749.method831(i_2_);
               anInt720 = 0;
            }

            anInt478 = i_2_;
            aClass56_749.method827(i_2_, is, 0, bool);
         }
      }

   }

   static final void method891(boolean bool) {
      method853(0, (byte[])null, bool);
   }

   static final void method790() {
      if(aClass56_749 != null) {
         method891(false);
         if(anInt720 > 0) {
            aClass56_749.method831(256);
            anInt720 = 0;
         }

         aClass56_749.method828();
         aClass56_749 = null;
      }

   }

   public void calcEntityScreenPos(int i, int j, int l) {
      if(i >= 128 && l >= 128 && i <= 13056 && l <= 13056) {
         int i1 = this.method42(this.plane, l, i) - j;
         i -= this.xCameraPos;
         i1 -= this.zCameraPos;
         l -= this.yCameraPos;
         int j1 = Model.modelIntArray1[this.yCameraCurve];
         int k1 = Model.modelIntArray2[this.yCameraCurve];
         int l1 = Model.modelIntArray1[this.xCameraCurve];
         int i2 = Model.modelIntArray2[this.xCameraCurve];
         int j2 = l * l1 + i * i2 >> 16;
         l = l * i2 - i * l1 >> 16;
         i = j2;
         j2 = i1 * k1 - l * j1 >> 16;
         l = i1 * j1 + l * k1 >> 16;
         if(l >= 50) {
            this.spriteDrawX = Texture.textureInt1 + (i << 9) / l;
            this.spriteDrawY = Texture.textureInt2 + (j2 << 9) / l;
         } else {
            this.spriteDrawX = -1;
            this.spriteDrawY = -1;
         }

      } else {
         this.spriteDrawX = -1;
         this.spriteDrawY = -1;
      }
   }

   public void buildSplitPrivateChatMenu() {
      if(this.splitpublicChat != 0) {
         int i = 0;
         if(this.anInt1104 != 0) {
            i = 1;
         }

         for(int j = 0; j < 100; ++j) {
            if(this.chatMessages[j] != null) {
               int k = this.chatTypes[j];
               String s = this.chatNames[j];
               if(s != null && s.startsWith("@cr1@")) {
                  s = s.substring(5);
               }

               if(s != null && s.startsWith("@cr2@")) {
                  s = s.substring(5);
               }
			   
			   if(s != null && s.startsWith("@don1@")) {
                  s = s.substring(6);
               }

               if((k == 3 || k == 7) && (k == 7 || this.privateChatMode == 0 || this.privateChatMode == 1 && this.isFriendOrSelf(s))) {
                  int l = 329 - i * 13;
                  if(super.mouseX > 4 && super.mouseY - 4 > l - 10 && super.mouseY - 4 <= l + 3) {
                     int i1 = this.aTextDrawingArea_1271.getTextWidth("From:  " + s + this.chatMessages[j]) + 25;
                     if(i1 > 450) {
                        i1 = 450;
                     }

                     if(super.mouseX < 4 + i1) {
                        //if(this.myPrivilege >= 1 && this.myPrivilege <= 3) {//right click report for every1
                           this.menuActionName[this.menuActionRow] = "Report abuse @whi@" + s;
                           this.menuActionID[this.menuActionRow] = 2606;
                           ++this.menuActionRow;
                        //}

                        this.menuActionName[this.menuActionRow] = "Add ignore @whi@" + s;
                        this.menuActionID[this.menuActionRow] = 2042;
                        ++this.menuActionRow;
                        this.menuActionName[this.menuActionRow] = "Add friend @whi@" + s;
                        this.menuActionID[this.menuActionRow] = 2337;
                        ++this.menuActionRow;
                     }
                  }

                  ++i;
                  if(i >= 5) {
                     return;
                  }
               }

               if((k == 5 || k == 6) && this.privateChatMode < 2) {
                  ++i;
                  if(i >= 5) {
                     return;
                  }
               }
            }
         }

      }
   }

   public void method130(int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2) {
      Class30_Sub1 class30_sub1 = null;

      for(Class30_Sub1 class30_sub1_1 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
         if(class30_sub1_1.anInt1295 == l1 && class30_sub1_1.anInt1297 == i2 && class30_sub1_1.anInt1298 == j1 && class30_sub1_1.anInt1296 == i1) {
            class30_sub1 = class30_sub1_1;
            break;
         }
      }

      if(class30_sub1 == null) {
         class30_sub1 = new Class30_Sub1();
         class30_sub1.anInt1295 = l1;
         class30_sub1.anInt1296 = i1;
         class30_sub1.anInt1297 = i2;
         class30_sub1.anInt1298 = j1;
         this.method89(class30_sub1);
         this.aClass19_1179.insertHead(class30_sub1);
      }

      class30_sub1.anInt1291 = k;
      class30_sub1.anInt1293 = k1;
      class30_sub1.anInt1292 = l;
      class30_sub1.anInt1302 = j2;
      class30_sub1.anInt1294 = j;
   }

   static int Red, Green, Blue;
   
   public static void col2rgb(int color){
		int RGBint = color;
		Red = (RGBint >> 16) & 255;
		Green = (RGBint >> 8) & 255;
		Blue = RGBint & 255;
	}
	
	public static int rgb2col(int r, int g, int b){
		int value = r << 16 | g << 8 | b;
		return value;
	}
   
   public boolean interfaceIsSelected(RSInterface class9) {
      if(class9.anIntArray245 == null) {
         return false;
      } else {
         for(int i = 0; i < class9.anIntArray245.length; ++i) {
            int j = this.extractInterfaceValues(class9, i);
            int k = class9.anIntArray212[i];
            if(class9.anIntArray245[i] == 2) {
               if(j >= k) {
                  return false;
               }
            } else if(class9.anIntArray245[i] == 3) {
               if(j <= k) {
                  return false;
               }
            } else if(class9.anIntArray245[i] == 4) {
               if(j == k) {
                  return false;
               }
            } else if(j != k) {
               return false;
            }
         }

         return true;
      }
   }

   public DataInputStream openJagGrabInputStream(String s) throws IOException {
      if(this.aSocket832 != null) {
         try {
            this.aSocket832.close();
         } catch (Exception var4) {
            ;
         }

         this.aSocket832 = null;
      }

      this.aSocket832 = this.openSocket('\uaa4b');
      this.aSocket832.setSoTimeout(10000);
      InputStream inputstream = this.aSocket832.getInputStream();
      OutputStream outputstream = this.aSocket832.getOutputStream();
      outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
      return new DataInputStream(inputstream);
   }

   public void doFlamesDrawing() {
      short c = 256;
      int i1;
      if(this.anInt1040 > 0) {
         for(i1 = 0; i1 < 256; ++i1) {
            if(this.anInt1040 > 768) {
               this.anIntArray850[i1] = this.method83(this.anIntArray851[i1], this.anIntArray852[i1], 1024 - this.anInt1040);
            } else if(this.anInt1040 > 256) {
               this.anIntArray850[i1] = this.anIntArray852[i1];
            } else {
               this.anIntArray850[i1] = this.method83(this.anIntArray852[i1], this.anIntArray851[i1], 256 - this.anInt1040);
            }
         }
      } else if(this.anInt1041 > 0) {
         for(i1 = 0; i1 < 256; ++i1) {
            if(this.anInt1041 > 768) {
               this.anIntArray850[i1] = this.method83(this.anIntArray851[i1], this.anIntArray853[i1], 1024 - this.anInt1041);
            } else if(this.anInt1041 > 256) {
               this.anIntArray850[i1] = this.anIntArray853[i1];
            } else {
               this.anIntArray850[i1] = this.method83(this.anIntArray853[i1], this.anIntArray851[i1], 256 - this.anInt1041);
            }
         }
      } else {
         System.arraycopy(this.anIntArray851, 0, this.anIntArray850, 0, 256);
      }

      System.arraycopy(this.aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, this.aRSImageProducer_1110.anIntArray315, 0, '\u8480');
      i1 = 0;
      int j1 = 1152;

      int k2;
      int i3;
      int k3;
      int i4;
      int k4;
      int i5;
      int j5;
      int k5;
      for(k2 = 1; k2 < c - 1; ++k2) {
         i3 = this.anIntArray969[k2] * (c - k2) / c;
         k3 = 22 + i3;
         if(k3 < 0) {
            k3 = 0;
         }

         i1 += k3;

         for(i4 = k3; i4 < 128; ++i4) {
            k4 = this.anIntArray828[i1++];
            if(k4 != 0) {
               i5 = k4;
               j5 = 256 - k4;
               k4 = this.anIntArray850[k4];
               k5 = this.aRSImageProducer_1110.anIntArray315[j1];
               this.aRSImageProducer_1110.anIntArray315[j1++] = ((k4 & 16711935) * i5 + (k5 & 16711935) * j5 & -16711936) + ((k4 & '\uff00') * i5 + (k5 & '\uff00') * j5 & 16711680) >> 8;
            } else {
               ++j1;
            }
         }

         j1 += k3;
      }

      this.aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
      System.arraycopy(this.aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, this.aRSImageProducer_1111.anIntArray315, 0, '\u8480');
      i1 = 0;
      j1 = 1176;

      for(k2 = 1; k2 < c - 1; ++k2) {
         i3 = this.anIntArray969[k2] * (c - k2) / c;
         k3 = 103 - i3;
         j1 += i3;

         for(i4 = 0; i4 < k3; ++i4) {
            k4 = this.anIntArray828[i1++];
            if(k4 != 0) {
               i5 = k4;
               j5 = 256 - k4;
               k4 = this.anIntArray850[k4];
               k5 = this.aRSImageProducer_1111.anIntArray315[j1];
               this.aRSImageProducer_1111.anIntArray315[j1++] = ((k4 & 16711935) * i5 + (k5 & 16711935) * j5 & -16711936) + ((k4 & '\uff00') * i5 + (k5 & '\uff00') * j5 & 16711680) >> 8;
            } else {
               ++j1;
            }
         }

         i1 += 128 - k3;
         j1 += 128 - k3 - i3;
      }

      this.aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
   }

   public void method134(Stream stream) {
      int j = stream.readBits(8);
      int l;
      if(j < this.playerCount) {
         for(l = j; l < this.playerCount; ++l) {
            this.anIntArray840[this.anInt839++] = this.playerIndices[l];
         }
      }

      if(j > this.playerCount) {
         SignLink.reporterror(this.myUsername + " Too many players");
         throw new RuntimeException("eek");
      } else {
         this.playerCount = 0;

         for(l = 0; l < j; ++l) {
            int i1 = this.playerIndices[l];
            Player player = this.playerArray[i1];
            int j1 = stream.readBits(1);
            if(j1 == 0) {
               this.playerIndices[this.playerCount++] = i1;
               player.anInt1537 = loopCycle;
            } else {
               int k1 = stream.readBits(2);
               if(k1 == 0) {
                  this.playerIndices[this.playerCount++] = i1;
                  player.anInt1537 = loopCycle;
                  this.anIntArray894[this.anInt893++] = i1;
               } else {
                  int i2;
                  int k2;
                  if(k1 == 1) {
                     this.playerIndices[this.playerCount++] = i1;
                     player.anInt1537 = loopCycle;
                     i2 = stream.readBits(3);
                     player.moveInDir(false, i2);
                     k2 = stream.readBits(1);
                     if(k2 == 1) {
                        this.anIntArray894[this.anInt893++] = i1;
                     }
                  } else if(k1 == 2) {
                     this.playerIndices[this.playerCount++] = i1;
                     player.anInt1537 = loopCycle;
                     i2 = stream.readBits(3);
                     player.moveInDir(true, i2);
                     k2 = stream.readBits(3);
                     player.moveInDir(true, k2);
                     int l2 = stream.readBits(1);
                     if(l2 == 1) {
                        this.anIntArray894[this.anInt893++] = i1;
                     }
                  } else if(k1 == 3) {
                     this.anIntArray840[this.anInt839++] = i1;
                  }
               }
            }
         }

      }
   }
   
   public void drawLoginScreen(boolean flag) {
      this.resetImageProducers();
      aRSImageProducer_1113.initDrawingArea();
      cacheSprite[musicVolume > 0 && !lowMem ? 43 : 44].drawSprite(725-562, 463-265);
      this.aRSImageProducer_1109.initDrawingArea();
      this.aBackground_966.method361(0, 0);
      short c = 360;
      short c1 = 200;
      int k;
      int j1;
      int i2;
      if(this.loginScreenState == 0) {
         k = c1 / 2 + 80;
         this.aTextDrawingArea_1270.method382(7711145, c / 2, this.onDemandFetcher.statusString, k, true);
         k = c1 / 2 - 20;
         this.chatTextDrawingArea.method382(16776960, c / 2, "Welcome to "+NAME, k, true);
         k += 30;
         j1 = c / 2 - 80;
         i2 = c1 / 2 + 20;
         this.aBackground_967.method361(j1 - 73, i2 - 20);
         this.chatTextDrawingArea.method382(16777215, j1, "New User", i2 + 5, true);
         j1 = c / 2 + 80;
         this.aBackground_967.method361(j1 - 73, i2 - 20);
         this.chatTextDrawingArea.method382(16777215, j1, "Existing User", i2 + 5, true);
		 if(dumpItemImages){
			for (int id = 0; id < ItemDef.totalItems; id++) {
				Sprite image = ItemDef.getSprite(id, 1, 0);
				if(image != null && !FileOperations.FileExists("./377 item images/"+id+".png")) {
					image.dumpImage("./377 item images/", ""+id, image, true);
					System.out.println("dumped: "+id);
				}
			}
		}
		 if(dumpNpcImages){
				for (int id = 0; id < EntityDef.totalNPCs; id++) {
					Sprite image = EntityDef.getSprite2(id, id, 0, 250);//Last Number is how you change the size of the image i.e. 250x250
					if(image != null && !FileOperations.FileExists("./377 npc images/"+id+".png")) {
						image.dumpImage("./377 npc images/", ""+id, image, true);
						System.out.println("dumped: "+id);
					}
				}
			}
      }

      if(this.loginScreenState == 2) {
         k = c1 / 2 - 40;
         if(this.loginMessage1.length() > 0) {
            this.chatTextDrawingArea.method382(16776960, c / 2, this.loginMessage1, k - 15, true);
            this.chatTextDrawingArea.method382(16776960, c / 2, this.loginMessage2, k, true);
            k += 30;
         } else {
            this.chatTextDrawingArea.method382(16776960, c / 2, this.loginMessage2, k - 7, true);
            k += 30;
         }

         this.chatTextDrawingArea.method389(true, c / 2 - 90, 16777215, "Username: " + this.myUsername + (this.loginScreenCursorPos == 0 & loopCycle % 40 < 20?"@yel@|":""), k);
         k += 15;
         this.chatTextDrawingArea.method389(true, c / 2 - 88, 16777215, "Password: " + TextClass.passwordAsterisks(this.myPassword) + (this.loginScreenCursorPos == 1 & loopCycle % 40 < 20?"@yel@|":""), k);
         k += 15;
         if(!flag) {
            j1 = c / 2 - 80;
            i2 = c1 / 2 + 50;
            this.aBackground_967.method361(j1 - 73, i2 - 20);
            this.chatTextDrawingArea.method382(16777215, j1, "Login", i2 + 5, true);
            j1 = c / 2 + 80;
            this.aBackground_967.method361(j1 - 73, i2 - 20);
            this.chatTextDrawingArea.method382(16777215, j1, "Cancel", i2 + 5, true);
         }
      }

      if(this.loginScreenState == 3) {
         this.chatTextDrawingArea.method382(16776960, c / 2, "Create a free account", c1 / 2 - 60, true);
         k = c1 / 2 - 35;
         this.chatTextDrawingArea.method382(16777215, c / 2, "To create a new account you need to", k, true);
         k += 15;
         this.chatTextDrawingArea.method382(16777215, c / 2, "go back to the main "+NAME+" webpage", k, true);
         k += 15;
         this.chatTextDrawingArea.method382(16777215, c / 2, "and choose the red \'create account\'", k, true);
         k += 15;
         this.chatTextDrawingArea.method382(16777215, c / 2, "button at the top right of that page.", k, true);
         k += 15;
         j1 = c / 2;
         i2 = c1 / 2 + 50;
         this.aBackground_967.method361(j1 - 73, i2 - 20);
         this.chatTextDrawingArea.method382(16777215, j1, "Cancel", i2 + 5, true);
      }

      this.aRSImageProducer_1109.drawGraphics(171, super.graphics, 202);
      if(this.welcomeScreenRaised) {
         this.welcomeScreenRaised = false;
         this.aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
         this.aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
         this.aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
         this.aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
         this.aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
         this.aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
      }

   }

   public void drawFlames() {
      this.drawingFlames = true;

      try {
         long _ex = System.currentTimeMillis();
         int i = 0;
         int j = 20;

         while(this.aBoolean831) {
            ++this.anInt1208;
            this.calcFlamesPosition();
            this.calcFlamesPosition();
            this.doFlamesDrawing();
            ++i;
            if(i > 10) {
               long _ex1 = System.currentTimeMillis();
               int k = (int)(_ex1 - _ex) / 10 - j;
               j = 40 - k;
               if(j < 5) {
                  j = 5;
               }

               i = 0;
               _ex = _ex1;
            }

            try {
               Thread.sleep((long)j);
            } catch (Exception var8) {
               ;
            }
         }
      } catch (Exception var9) {
         ;
      }

      this.drawingFlames = false;
   }

   public void raiseWelcomeScreen() {
      this.welcomeScreenRaised = true;
   }

   public void method137(Stream stream, int j) {
      int i3;
      int l5;
      int k8;
      int j11;
      int k13;
      int l15;
      if(j == 84) {
         i3 = stream.readUnsignedByte();
         l5 = this.anInt1268 + (i3 >> 4 & 7);
         k8 = this.anInt1269 + (i3 & 7);
         j11 = stream.readUnsignedWord();
         k13 = stream.readUnsignedWord();
         l15 = stream.readUnsignedWord();
         if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104) {
            NodeList var34 = this.groundArray[this.plane][l5][k8];
            if(var34 != null) {
               for(Item var32 = (Item)var34.reverseGetFirst(); var32 != null; var32 = (Item)var34.reverseGetNext()) {
                  if(var32.ID == (j11 & 32767) && var32.anInt1559 == k13) {
                     var32.anInt1559 = l15;
                     break;
                  }
               }

               this.spawnGroundItem(l5, k8);
            }
         }

      } else {
         int i17;
         if(j == 105) {
            i3 = stream.readUnsignedByte();
            l5 = this.anInt1268 + (i3 >> 4 & 7);
            k8 = this.anInt1269 + (i3 & 7);
            j11 = stream.readUnsignedWord();
            k13 = stream.readUnsignedByte();
            l15 = k13 >> 4 & 15;
            i17 = k13 & 7;
            if(myPlayer.smallX[0] >= l5 - l15 && myPlayer.smallX[0] <= l5 + l15 && myPlayer.smallY[0] >= k8 - l15 && myPlayer.smallY[0] <= k8 + l15 && soundEffectVolume != 0 && i17 > 0 && this.soundCount < 50) {
               this.sound[this.soundCount] = j11;
               this.soundType[this.soundCount] = i17;
               this.soundDelay[this.soundCount] = 0;
			   //System.out.println("sound: "+j11+" "+i17);
               aClass26Array1468[this.soundCount] = null;
               ++this.soundCount;
            }
         }

         if(j == 215) {
            i3 = stream.method435();
            l5 = stream.method428();
            k8 = this.anInt1268 + (l5 >> 4 & 7);
            j11 = this.anInt1269 + (l5 & 7);
            k13 = stream.method435();
            l15 = stream.readUnsignedWord();
            if(k8 >= 0 && j11 >= 0 && k8 < 104 && j11 < 104 && k13 != this.unknownInt10) {
               Item var35 = new Item();
               var35.ID = i3;
               var35.anInt1559 = l15;
               if(this.groundArray[this.plane][k8][j11] == null) {
                  this.groundArray[this.plane][k8][j11] = new NodeList();
               }

               this.groundArray[this.plane][k8][j11].insertHead(var35);
               this.spawnGroundItem(k8, j11);
            }

         } else {
            Item var30;
            if(j == 156) {
               i3 = stream.method426();
               l5 = this.anInt1268 + (i3 >> 4 & 7);
               k8 = this.anInt1269 + (i3 & 7);
               j11 = stream.readUnsignedWord();
               if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104) {
                  NodeList var33 = this.groundArray[this.plane][l5][k8];
                  if(var33 != null) {
                     for(var30 = (Item)var33.reverseGetFirst(); var30 != null; var30 = (Item)var33.reverseGetNext()) {
                        if(var30.ID == (j11 & 32767)) {
                           var30.unlink();
                           break;
                        }
                     }

                     if(var33.reverseGetFirst() == null) {
                        this.groundArray[this.plane][l5][k8] = null;
                     }

                     this.spawnGroundItem(l5, k8);
                  }
               }

            } else {
               int i18;
               int l18;
               int k19;
               int j20;
               int i21;
               int class30_sub2_sub4_sub4;
               if(j == 160) {
                  i3 = stream.method428();
                  l5 = this.anInt1268 + (i3 >> 4 & 7);
                  k8 = this.anInt1269 + (i3 & 7);
                  j11 = stream.method428();
                  k13 = j11 >> 2;
                  l15 = j11 & 3;
                  i17 = this.anIntArray1177[k13];
                  i18 = stream.method435();
                  if(l5 >= 0 && k8 >= 0 && l5 < 103 && k8 < 103) {
                     l18 = this.intGroundArray[this.plane][l5][k8];
                     k19 = this.intGroundArray[this.plane][l5 + 1][k8];
                     j20 = this.intGroundArray[this.plane][l5 + 1][k8 + 1];
                     i21 = this.intGroundArray[this.plane][l5][k8 + 1];
                     if(i17 == 0) {
                        Object1 var37 = this.worldController.method296(this.plane, l5, k8);
                        if(var37 != null) {
                           class30_sub2_sub4_sub4 = var37.uid >> 14 & 32767;
                           if(k13 == 2) {
                              var37.aClass30_Sub2_Sub4_278 = new Animable_Sub5(class30_sub2_sub4_sub4, 4 + l15, 2, k19, j20, l18, i21, i18, false);
                              var37.aClass30_Sub2_Sub4_279 = new Animable_Sub5(class30_sub2_sub4_sub4, l15 + 1 & 3, 2, k19, j20, l18, i21, i18, false);
                           } else {
                              var37.aClass30_Sub2_Sub4_278 = new Animable_Sub5(class30_sub2_sub4_sub4, l15, k13, k19, j20, l18, i21, i18, false);
                           }
                        }
                     }

                     if(i17 == 1) {
                        Object2 var36 = this.worldController.method297(l5, k8, this.plane);
                        if(var36 != null) {
                           var36.aClass30_Sub2_Sub4_504 = new Animable_Sub5(var36.uid >> 14 & 32767, 0, 4, k19, j20, l18, i21, i18, false);
                        }
                     }

                     if(i17 == 2) {
                        Object5 var41 = this.worldController.method298(l5, k8, this.plane);
                        if(k13 == 11) {
                           k13 = 10;
                        }

                        if(var41 != null) {
                           var41.aClass30_Sub2_Sub4_521 = new Animable_Sub5(var41.uid >> 14 & 32767, l15, k13, k19, j20, l18, i21, i18, false);
                        }
                     }

                     if(i17 == 3) {
                        Object3 var40 = this.worldController.method299(k8, l5, this.plane);
                        if(var40 != null) {
                           var40.aClass30_Sub2_Sub4_814 = new Animable_Sub5(var40.uid >> 14 & 32767, l15, 22, k19, j20, l18, i21, i18, false);
                        }
                     }
                  }

               } else {
                  if(j == 147) {
                     i3 = stream.method428();
                     l5 = this.anInt1268 + (i3 >> 4 & 7);
                     k8 = this.anInt1269 + (i3 & 7);
                     j11 = stream.readUnsignedWord();
                     byte var28 = stream.method430();
                     l15 = stream.method434();
                     byte var29 = stream.method429();
                     i18 = stream.readUnsignedWord();
                     l18 = stream.method428();
                     k19 = l18 >> 2;
                     j20 = l18 & 3;
                     i21 = this.anIntArray1177[k19];
                     byte j21 = stream.readSignedByte();
                     class30_sub2_sub4_sub4 = stream.readUnsignedWord();
                     byte byte3 = stream.method429();
                     Player player;
                     if(j11 == this.unknownInt10) {
                        player = myPlayer;
                     } else {
                        player = this.playerArray[j11];
                     }

                     if(player != null) {
                        ObjectDef class46 = ObjectDef.forID(class30_sub2_sub4_sub4);
                        int i22 = this.intGroundArray[this.plane][l5][k8];
                        int j22 = this.intGroundArray[this.plane][l5 + 1][k8];
                        int k22 = this.intGroundArray[this.plane][l5 + 1][k8 + 1];
                        int l22 = this.intGroundArray[this.plane][l5][k8 + 1];
                        Model model = class46.method578(k19, j20, i22, j22, k22, l22, -1);
                        if(model != null) {
                           this.method130(i18 + 1, -1, 0, i21, k8, 0, this.plane, l5, l15 + 1);
                           player.anInt1707 = l15 + loopCycle;
                           player.anInt1708 = i18 + loopCycle;
                           player.aModel_1714 = model;
                           int i23 = class46.anInt744;
                           int j23 = class46.anInt761;
                           if(j20 == 1 || j20 == 3) {
                              i23 = class46.anInt761;
                              j23 = class46.anInt744;
                           }

                           player.anInt1711 = l5 * 128 + i23 * 64;
                           player.anInt1713 = k8 * 128 + j23 * 64;
                           player.anInt1712 = this.method42(this.plane, player.anInt1713, player.anInt1711);
                           byte byte5;
                           if(j21 > var28) {
                              byte5 = j21;
                              j21 = var28;
                              var28 = byte5;
                           }

                           if(byte3 > var29) {
                              byte5 = byte3;
                              byte3 = var29;
                              var29 = byte5;
                           }

                           player.anInt1719 = l5 + j21;
                           player.anInt1721 = l5 + var28;
                           player.anInt1720 = k8 + byte3;
                           player.anInt1722 = k8 + var29;
                        }
                     }
                  }

                  if(j == 151) {
                     i3 = stream.method426();
                     l5 = this.anInt1268 + (i3 >> 4 & 7);
                     k8 = this.anInt1269 + (i3 & 7);
                     j11 = stream.method434();
                     k13 = stream.method428();
                     l15 = k13 >> 2;
                     i17 = k13 & 3;
                     i18 = this.anIntArray1177[l15];
                     
                     this.baseX = (this.anInt1069 - 6) * 8;
                     this.baseY = (this.anInt1070 - 6) * 8;
                     /*Calendar now = Calendar.getInstance();
             		 now.setTimeZone(timeZone);
                     int hour = now.get(Calendar.HOUR_OF_DAY);
             		 int minute = now.get(Calendar.MINUTE);
             		 int second = now.get(Calendar.SECOND);
                     System.out.println("["+hour+":"+minute+"."+second+"] ObjectId: "+j11+" face: "+i17+" type: "+l15+" Pos: "+(this.baseX+l5)+","+(this.baseY+k8));
                     */
                     
                     if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104) {
                        this.method130(-1, j11, i17, i18, k8, l15, this.plane, l5, 0);
                     }

                  } else if(j == 4) {
                     i3 = stream.readUnsignedByte();
                     l5 = this.anInt1268 + (i3 >> 4 & 7);
                     k8 = this.anInt1269 + (i3 & 7);
                     j11 = stream.readUnsignedWord();
                     k13 = stream.readUnsignedByte();
                     l15 = stream.readUnsignedWord();
                     if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104) {
                        l5 = l5 * 128 + 64;
                        k8 = k8 * 128 + 64;
                        Animable_Sub3 var31 = new Animable_Sub3(this.plane, loopCycle, l15, j11, this.method42(this.plane, k8, l5) - k13, k8, l5);
                        this.aClass19_1056.insertHead(var31);
                     }

                  } else if(j == 44) {
                     i3 = stream.method436();
                     l5 = stream.readUnsignedWord();
                     k8 = stream.readUnsignedByte();
                     j11 = this.anInt1268 + (k8 >> 4 & 7);
                     k13 = this.anInt1269 + (k8 & 7);
                     if(j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104) {
                        var30 = new Item();
                        var30.ID = i3;
                        var30.anInt1559 = l5;
                        if(this.groundArray[this.plane][j11][k13] == null) {
                           this.groundArray[this.plane][j11][k13] = new NodeList();
                        }

                        this.groundArray[this.plane][j11][k13].insertHead(var30);
                        this.spawnGroundItem(j11, k13);
                     }

                  } else if(j == 101) {
                     i3 = stream.method427();
                     l5 = i3 >> 2;
                     k8 = i3 & 3;
                     j11 = this.anIntArray1177[l5];
                     k13 = stream.readUnsignedByte();
                     l15 = this.anInt1268 + (k13 >> 4 & 7);
                     i17 = this.anInt1269 + (k13 & 7);
                     if(l15 >= 0 && i17 >= 0 && l15 < 104 && i17 < 104) {
                        this.method130(-1, -1, k8, j11, i17, l5, this.plane, l15, 0);
                     }

                  } else {
                     if(j == 117) {
                        i3 = stream.readUnsignedByte();
                        l5 = this.anInt1268 + (i3 >> 4 & 7);
                        k8 = this.anInt1269 + (i3 & 7);
                        j11 = l5 + stream.readSignedByte();
                        k13 = k8 + stream.readSignedByte();
                        l15 = stream.readSignedWord();
                        i17 = stream.readUnsignedWord();
                        i18 = stream.readUnsignedByte() * 4;
                        l18 = stream.readUnsignedByte() * 4;
                        k19 = stream.readUnsignedWord();
                        j20 = stream.readUnsignedWord();
                        i21 = stream.readUnsignedByte();
                        int var39 = stream.readUnsignedByte();
                        if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104 && j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104 && i17 != '\uffff') {
                           l5 = l5 * 128 + 64;
                           k8 = k8 * 128 + 64;
                           j11 = j11 * 128 + 64;
                           k13 = k13 * 128 + 64;
                           Animable_Sub4 var38 = new Animable_Sub4(i21, l18, k19 + loopCycle, j20 + loopCycle, var39, this.plane, this.method42(this.plane, k8, l5) - i18, k8, l5, l15, i17);
                           var38.method455(k19 + loopCycle, k13, this.method42(this.plane, k13, j11) - l18, j11);
                           this.aClass19_1013.insertHead(var38);
                        }
                     }

                  }
               }
            }
         }
      }
   }

   public static void setLowMem() {
      WorldController.lowMem = true;
      Texture.lowMem = true;
      lowMem = true;
      ObjectManager.lowMem = true;
      ObjectDef.lowMem = true;
   }

   public void method139(Stream stream) {
      stream.initBitAccess();
      int k = stream.readBits(8);
      int i1;
      if(k < this.npcCount) {
         for(i1 = k; i1 < this.npcCount; ++i1) {
            this.anIntArray840[this.anInt839++] = this.npcIndices[i1];
         }
      }

      if(k > this.npcCount) {
         SignLink.reporterror(this.myUsername + " Too many npcs");
         throw new RuntimeException("eek");
      } else {
         this.npcCount = 0;

         for(i1 = 0; i1 < k; ++i1) {
            int j1 = this.npcIndices[i1];
            NPC npc = this.npcArray[j1];
            int k1 = stream.readBits(1);
            if(k1 == 0) {
               this.npcIndices[this.npcCount++] = j1;
               npc.anInt1537 = loopCycle;
            } else {
               int l1 = stream.readBits(2);
               if(l1 == 0) {
                  this.npcIndices[this.npcCount++] = j1;
                  npc.anInt1537 = loopCycle;
                  this.anIntArray894[this.anInt893++] = j1;
               } else {
                  int j2;
                  int l2;
                  if(l1 == 1) {
                     this.npcIndices[this.npcCount++] = j1;
                     npc.anInt1537 = loopCycle;
                     j2 = stream.readBits(3);
                     npc.moveInDir(false, j2);
                     l2 = stream.readBits(1);
                     if(l2 == 1) {
                        this.anIntArray894[this.anInt893++] = j1;
                     }
                  } else if(l1 == 2) {
                     this.npcIndices[this.npcCount++] = j1;
                     npc.anInt1537 = loopCycle;
                     j2 = stream.readBits(3);
                     npc.moveInDir(true, j2);
                     l2 = stream.readBits(3);
                     npc.moveInDir(true, l2);
                     int i3 = stream.readBits(1);
                     if(i3 == 1) {
                        this.anIntArray894[this.anInt893++] = j1;
                     }
                  } else if(l1 == 3) {
                     this.anIntArray840[this.anInt839++] = j1;
                  }
               }
            }
         }

      }
   }

   public void processLoginScreenInput() {
      int k;
      int j1;
      if(super.clickMode3 == 1 && super.saveClickX >= 725 && super.saveClickX <= 760 && super.saveClickY >= 463 && super.saveClickY <= 499) {//music button
    	  if(!lowMem){
    		  if(musicVolume != 0){
    			  method55(false);
                  this.previousSong = 0;
                  this.variousSettings[168] = 4;
                  this.method33(168);
    		  } else {
    			  method55(false);
                  this.previousSong = 0;
    			  this.variousSettings[168] = 0;
                  this.method33(168);
    			  checkSpecialTheme();
    			  this.method58(18, musicVolume, false, themeSong);
    		  }
    		  raiseWelcomeScreen();
    	  }
      }
      if(this.loginScreenState == 0) {
         k = super.myWidth / 2 - 80;
         j1 = super.myHeight / 2 + 20;
         j1 += 20;
         if(super.clickMode3 == 1 && super.saveClickX >= k - 75 && super.saveClickX <= k + 75 && super.saveClickY >= j1 - 20 && super.saveClickY <= j1 + 20) {
            this.loginScreenState = 3;
            this.loginScreenCursorPos = 0;
         }

         k = super.myWidth / 2 + 80;
         if(super.clickMode3 == 1 && super.saveClickX >= k - 75 && super.saveClickX <= k + 75 && super.saveClickY >= j1 - 20 && super.saveClickY <= j1 + 20) {
            this.loginMessage1 = "";
            this.loginMessage2 = "Enter your username & password.";
            this.loginScreenState = 2;
            this.loginScreenCursorPos = 0;
         }
      } else {
         if(this.loginScreenState == 2) {
            k = super.myHeight / 2 - 40;
            k += 30;
            k += 25;
            if(super.clickMode3 == 1 && super.saveClickY >= k - 15 && super.saveClickY < k) {
               this.loginScreenCursorPos = 0;
            }

            k += 15;
            if(super.clickMode3 == 1 && super.saveClickY >= k - 15 && super.saveClickY < k) {
               this.loginScreenCursorPos = 1;
            }

            k += 15;
            j1 = super.myWidth / 2 - 80;
            int k1 = super.myHeight / 2 + 50;
            k1 += 20;
            if(super.clickMode3 == 1 && super.saveClickX >= j1 - 75 && super.saveClickX <= j1 + 75 && super.saveClickY >= k1 - 20 && super.saveClickY <= k1 + 20) {
               this.loginFailures = 0;
               this.login(this.myUsername, this.myPassword, false);
               if(this.loggedIn) {
                  return;
               }
            }

            j1 = super.myWidth / 2 + 80;
            if(super.clickMode3 == 1 && super.saveClickX >= j1 - 75 && super.saveClickX <= j1 + 75 && super.saveClickY >= k1 - 20 && super.saveClickY <= k1 + 20) {
               this.loginScreenState = 0;
            }

            while(true) {
               int l1 = this.readChar(-796);
               if(l1 == -1) {
                  return;
               }

               boolean flag1 = false;
               int i2 = 0;

               while(true) {
                  if(i2 < "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:\'@#~,<.>/?\\| ".length()) {
                     if(l1 != "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:\'@#~,<.>/?\\| ".charAt(i2)) {
                        ++i2;
                        continue;
                     }

                     flag1 = true;
                  }

                  if(this.loginScreenCursorPos == 0) {
                     if(l1 == 8 && this.myUsername.length() > 0) {
                        this.myUsername = this.myUsername.substring(0, this.myUsername.length() - 1);
                     }

                     if(l1 == 9 || l1 == 10 || l1 == 13) {
                        this.loginScreenCursorPos = 1;
                     }

                     if(flag1) {
                        this.myUsername = this.myUsername + (char)l1;
                     }

                     if(this.myUsername.length() > 12) {
                        this.myUsername = this.myUsername.substring(0, 12);
                     }
                     break;
                  }

                  if(this.loginScreenCursorPos != 1) {
                     break;
                  }

                  if(l1 == 8 && this.myPassword.length() > 0) {
                     this.myPassword = this.myPassword.substring(0, this.myPassword.length() - 1);
                  }

                  if(l1 == 9 || l1 == 10 || l1 == 13) {
                     this.loginScreenCursorPos = 0;
                  }

                  if(flag1) {
                     this.myPassword = this.myPassword + (char)l1;
                  }

                  if(this.myPassword.length() > 20) {
                     this.myPassword = this.myPassword.substring(0, 20);
                  }
                  break;
               }
            }
         }

         if(this.loginScreenState == 3) {
            k = super.myWidth / 2;
            j1 = super.myHeight / 2 + 50;
            j1 += 20;
            if(super.clickMode3 == 1 && super.saveClickX >= k - 75 && super.saveClickX <= k + 75 && super.saveClickY >= j1 - 20 && super.saveClickY <= j1 + 20) {
               this.loginScreenState = 0;
            }
         }
      }

   }

   public void markMinimap(Sprite sprite, int i, int j) {
      int k = this.minimapInt1 + this.minimapInt2 & 2047;
      int l = i * i + j * j;
      if(l <= 6400) {
         int i1 = Model.modelIntArray1[k];
         int j1 = Model.modelIntArray2[k];
         i1 = i1 * 256 / (this.minimapInt3 + 256);
         j1 = j1 * 256 / (this.minimapInt3 + 256);
         int k1 = j * i1 + i * j1 >> 16;
         int l1 = j * j1 - i * i1 >> 16;
         if(l > 2500) {
            sprite.method354(mapBack, 83 - l1 - sprite.anInt1445 / 2 - 4, 94 + k1 - sprite.anInt1444 / 2 + 4);
         } else {
            sprite.drawSprite(94 + k1 - sprite.anInt1444 / 2 + 4, 83 - l1 - sprite.anInt1445 / 2 - 4);
         }

      }
   }

   public void method142(int i, int j, int k, int l, int i1, int j1, int k1) {
      if(i1 >= 1 && i >= 1 && i1 <= 102 && i <= 102) {
         if(lowMem && j != this.plane) {
            return;
         }

         int i2 = 0;
         if(j1 == 0) {
            i2 = this.worldController.method300(j, i1, i);
         }

         if(j1 == 1) {
            i2 = this.worldController.method301(j, i1, i);
         }

         if(j1 == 2) {
            i2 = this.worldController.method302(j, i1, i);
         }

         if(j1 == 3) {
            i2 = this.worldController.method303(j, i1, i);
         }

         int j3;
         if(i2 != 0) {
            j3 = this.worldController.method304(j, i1, i, i2);
            int j2 = i2 >> 14 & 32767;
            int k2 = j3 & 31;
            int l2 = j3 >> 6;
            ObjectDef class46_2;
            if(j1 == 0) {
               this.worldController.method291(i1, j, i, (byte)-119);
               class46_2 = ObjectDef.forID(j2);
               if(class46_2.aBoolean767) {
                  this.aClass11Array1230[j].method215(l2, k2, class46_2.aBoolean757, i1, i);
               }
            }

            if(j1 == 1) {
               this.worldController.method292(i, j, i1);
            }

            if(j1 == 2) {
               this.worldController.method293(j, i1, i);
               class46_2 = ObjectDef.forID(j2);
               if(i1 + class46_2.anInt744 > 103 || i + class46_2.anInt744 > 103 || i1 + class46_2.anInt761 > 103 || i + class46_2.anInt761 > 103) {
                  return;
               }

               if(class46_2.aBoolean767) {
                  this.aClass11Array1230[j].method216(l2, class46_2.anInt744, i1, i, class46_2.anInt761, class46_2.aBoolean757);
               }
            }

            if(j1 == 3) {
               this.worldController.method294(j, i, i1);
               class46_2 = ObjectDef.forID(j2);
               if(class46_2.aBoolean767 && class46_2.hasActions) {
                  this.aClass11Array1230[j].method218(i, i1);
               }
            }
         }

         if(k1 >= 0) {
            j3 = j;
            if(j < 3 && (this.byteGroundArray[1][i1][i] & 2) == 2) {
               j3 = j + 1;
            }

            ObjectManager.method188(this.worldController, k, i, l, j3, this.aClass11Array1230[j], this.intGroundArray, i1, k1, j);
         }
      }

   }

   public void updatePlayers(int i, Stream stream) {
      this.anInt839 = 0;
      this.anInt893 = 0;
      this.method117(stream);
      this.method134(stream);
      this.method91(stream, i);
      this.method49(stream);

      int i1;
      for(i1 = 0; i1 < this.anInt839; ++i1) {
         int l = this.anIntArray840[i1];
         if(this.playerArray[l].anInt1537 != loopCycle) {
            this.playerArray[l] = null;
         }
      }

      if(stream.currentOffset != i) {
         SignLink.reporterror("Error packet size mismatch in getplayer pos:" + stream.currentOffset + " psize:" + i);
         throw new RuntimeException("eek");
      } else {
         for(i1 = 0; i1 < this.playerCount; ++i1) {
            if(this.playerArray[this.playerIndices[i1]] == null) {
               SignLink.reporterror(this.myUsername + " null entry in pl list - pos:" + i1 + " size:" + this.playerCount);
               throw new RuntimeException("eek");
            }
         }

      }
   }

   public void setCameraPos(int j, int k, int l, int i1, int j1, int k1) {
      int l1 = 2048 - k & 2047;
      int i2 = 2048 - j1 & 2047;
      int j2 = 0;
      int k2 = 0;
		if (editMode) {
			double j_t = j;
			double j_t2 = j_t * 1.55;
			j = (int) j_t2;
		}
      int l2 = j;
      int j3;
      int l3;
      int j4;
      if(l1 != 0) {
         j3 = Model.modelIntArray1[l1];
         l3 = Model.modelIntArray2[l1];
         j4 = k2 * l3 - j * j3 >> 16;
         l2 = k2 * j3 + j * l3 >> 16;
         k2 = j4;
      }

      if(i2 != 0) {
         j3 = Model.modelIntArray1[i2];
         l3 = Model.modelIntArray2[i2];
         j4 = l2 * j3 + j2 * l3 >> 16;
         l2 = l2 * l3 - j2 * j3 >> 16;
         j2 = j4;
      }

      this.xCameraPos = l - j2;
      this.zCameraPos = i1 - k2;
      this.yCameraPos = k1 - l2;
      this.yCameraCurve = k;
      this.xCameraCurve = j1;
   }

   public boolean parsePacket() {
      if(this.socketStream == null) {
         return false;
      } else {
         String s2;
         int j15;
         try {
            int exception = this.socketStream.available();
            if(exception == 0) {
               return false;
            }

            if(this.pktType == -1) {
               this.socketStream.flushInputStream(this.inStream.buffer, 1);
               this.pktType = this.inStream.buffer[0] & 255;
               if(this.encryption != null) {
                  this.pktType = this.pktType - this.encryption.getNextKey() & 255;
               }
               this.pktSize = SizeConstants.packetSizes[this.pktType];
               --exception;
            }

            if(this.pktSize == -1) {
               if(exception <= 0) {
                  return false;
               }

               this.socketStream.flushInputStream(this.inStream.buffer, 1);
               this.pktSize = this.inStream.buffer[0] & 255;
               --exception;
            }

            if(this.pktSize == -2) {
               if(exception <= 1) {
                  return false;
               }

               this.socketStream.flushInputStream(this.inStream.buffer, 2);
               this.inStream.currentOffset = 0;
               this.pktSize = this.inStream.readUnsignedWord();
               exception -= 2;
            }

            if(exception < this.pktSize) {
               return false;
            }

            this.inStream.currentOffset = 0;
            this.socketStream.flushInputStream(this.inStream.buffer, this.pktSize);
            this.anInt1009 = 0;
            this.anInt843 = this.anInt842;
            this.anInt842 = this.anInt841;
            this.anInt841 = this.pktType;
            //System.out.println(this.pktType);//print received packets
            if(this.pktType == 81) {
               this.updatePlayers(this.pktSize, this.inStream);
               this.aBoolean1080 = false;
               this.pktType = -1;
               return true;
            }

            int j20;
            int i23;
			/*
			if(this.pktType == 69) {
               var20 = this.inStream.readUnsignedWord();
			   int fullScreenInterface = this.inStream.readUnsignedWord();
               this.method60(var20);
               this.openInterfaceID = var20;
			   this.fullscreenInterfaceID = fullScreenInterface;
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }
			original:
			if(this.pktType == 176) {
               this.daysSinceRecovChange = this.inStream.method427();
               this.unreadMessages = this.inStream.method435();
               this.membersInt = this.inStream.readUnsignedByte();
               this.anInt1193 = this.inStream.method440();
               this.daysSinceLastLogin = this.inStream.readUnsignedWord();
               if(this.anInt1193 != 0 && this.openInterfaceID == -1) {
                  SignLink.dnslookup(TextClass.method586(this.anInt1193));
                  this.clearTopInterfaces();
                  short var49 = 650;
                  if(this.daysSinceRecovChange != 201 || this.membersInt == 1) {
                     var49 = 655;
                  }

                  this.reportAbuseInput = "";
                  this.canMute = false;
                  RSInterface[] var45 = RSInterface.interfaceCache;
                  j20 = var45.length;

                  for(i23 = 0; i23 < j20; ++i23) {
                     RSInterface var47 = var45[i23];
                     if(var47 != null && var47.anInt214 == var49) {
                        this.openInterfaceID = var47.parentID;
                        break;
                     }
                  }
               }

               this.pktType = -1;
               return true;
            }
			*/
			
            if(this.pktType == 176) {
               this.daysSinceRecovChange = this.inStream.readUnsignedWord();
               this.unreadMessages = this.inStream.method435();
               this.membersInt = this.inStream.readUnsignedByte();
               this.anInt1193 = this.inStream.method440();
               this.daysSinceLastLogin = this.inStream.readUnsignedWord();
			   this.donatorPoints = this.inStream.readUnsignedWord();
			   int messageOfTheWeek = this.inStream.readUnsignedWord();
               if(this.anInt1193 != 0 && this.openInterfaceID == -1) {
                  SignLink.dnslookup(TextClass.method586(this.anInt1193));
                  this.clearTopInterfaces();
                  short var49 = 660;

                  this.reportAbuseInput = "";
                  this.canMute = false;
				  this.openInterfaceID = messageOfTheWeek;
				  this.fullscreenInterfaceID = 15244;
                  /*RSInterface[] var45 = RSInterface.interfaceCache;
                  j20 = var45.length;

                  for(i23 = 0; i23 < j20; ++i23) {
                     RSInterface var47 = var45[i23];
                     if(var47 != null && var47.anInt214 == var49) {
                        this.openInterfaceID = var47.parentID;
                        break;
                     }
                  }*/
               }

               this.pktType = -1;
               return true;
            }

            int var20;
            if(this.pktType == 64) {
               this.anInt1268 = this.inStream.method427();
               this.anInt1269 = this.inStream.method428();

               for(var20 = this.anInt1268; var20 < this.anInt1268 + 8; ++var20) {
                  for(j15 = this.anInt1269; j15 < this.anInt1269 + 8; ++j15) {
                     if(this.groundArray[this.plane][var20][j15] != null) {
                        this.groundArray[this.plane][var20][j15] = null;
                        this.spawnGroundItem(var20, j15);
                     }
                  }
               }

               for(Class30_Sub1 var48 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); var48 != null; var48 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
                  if(var48.anInt1297 >= this.anInt1268 && var48.anInt1297 < this.anInt1268 + 8 && var48.anInt1298 >= this.anInt1269 && var48.anInt1298 < this.anInt1269 + 8 && var48.anInt1295 == this.plane) {
                     var48.anInt1294 = 0;
                  }
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 185) {
               var20 = this.inStream.method436();
               RSInterface.interfaceCache[var20].anInt233 = 3;
               if(myPlayer.desc == null) {
                  RSInterface.interfaceCache[var20].mediaID = (myPlayer.anIntArray1700[0] << 25) + (myPlayer.anIntArray1700[4] << 20) + (myPlayer.equipment[0] << 15) + (myPlayer.equipment[8] << 10) + (myPlayer.equipment[11] << 5) + myPlayer.equipment[1];
               } else {
                  RSInterface.interfaceCache[var20].mediaID = (int)(305419896L + myPlayer.desc.type);
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 107) {
               this.aBoolean1160 = false;

               for(var20 = 0; var20 < 5; ++var20) {
                  this.aBooleanArray876[var20] = false;
               }

               this.pktType = -1;
               return true;
            }

            RSInterface var21;
            if(this.pktType == 72) {
               var20 = this.inStream.method434();
               var21 = RSInterface.interfaceCache[var20];

               for(j20 = 0; j20 < var21.inv.length; ++j20) {
                  var21.inv[j20] = -1;
                  var21.inv[j20] = 0;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 214) {
               this.ignoreCount = this.pktSize / 8;

               for(var20 = 0; var20 < this.ignoreCount; ++var20) {
                  this.ignoreListAsLongs[var20] = this.inStream.readQWord();
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 166) {
               this.aBoolean1160 = true;
               this.anInt1098 = this.inStream.readUnsignedByte();
               this.anInt1099 = this.inStream.readUnsignedByte();
               this.anInt1100 = this.inStream.readUnsignedWord();
               this.anInt1101 = this.inStream.readUnsignedByte();
               this.anInt1102 = this.inStream.readUnsignedByte();
               if(this.anInt1102 >= 100) {
                  this.xCameraPos = this.anInt1098 * 128 + 64;
                  this.yCameraPos = this.anInt1099 * 128 + 64;
                  this.zCameraPos = this.method42(this.plane, this.yCameraPos, this.xCameraPos) - this.anInt1100;
               }
				//System.out.println("NEW CAM 166: "+this.xCameraPos+" "+this.yCameraPos+" "+this.zCameraPos);
			   
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 134) {
               this.needDrawTabArea = true;
               var20 = this.inStream.readUnsignedByte();
               j15 = this.inStream.method439();
               j20 = this.inStream.readUnsignedByte();
               this.currentExp[var20] = j15;
               this.currentStats[var20] = j20;
               this.maxStats[var20] = 1;

               for(i23 = 0; i23 < 98; ++i23) {
                  if(j15 >= anIntArray1019[i23]) {
                     this.maxStats[var20] = i23 + 2;
                  }
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 71) {
               var20 = this.inStream.readUnsignedWord();
               j15 = this.inStream.method426();
               if(var20 == '\uffff') {
                  var20 = -1;
               }

               this.tabInterfaceIDs[j15] = var20;
               this.needDrawTabArea = true;
               this.tabAreaAltered = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 74) {
               var20 = this.inStream.method434();
               if(var20 == '\uffff') {
                  var20 = -1;
               }

               if(var20 == -1 && this.previousSong == 0) {
                  method55(false);
               } else if(var20 != -1 && this.currentSong != var20 && musicVolume != 0 && this.previousSong == 0) {
                  this.method58(18, musicVolume, false, var20);
               }

               this.currentSong = var20;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 121) {
               var20 = this.inStream.method436();
               j15 = this.inStream.method435();
               if(var20 == '\uffff') {
                  var20 = -1;
               }

               if(musicVolume != 0 && j15 != -1) {
                  this.method56(musicVolume, false, var20);
                  this.previousSong = j15 * 20;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 109) {
               this.resetLogout();
               this.pktType = -1;
               return false;
            }

            if(this.pktType == 70) {
               var20 = this.inStream.readSignedWord();
               j15 = this.inStream.method437();
               j20 = this.inStream.method434();
               RSInterface var38 = RSInterface.interfaceCache[j20];
               var38.anInt263 = var20;
               var38.anInt265 = j15;
               this.pktType = -1;
               return true;
            }

            int l25;
            int j28;
            int i30;
            if(this.pktType == 73 || this.pktType == 241) {
               var20 = this.anInt1069;
               j15 = this.anInt1070;
               if(this.pktType == 73) {
                  var20 = this.inStream.method435();
                  j15 = this.inStream.readUnsignedWord();
                  this.aBoolean1159 = false;
               }

               if(this.pktType == 241) {
                  j15 = this.inStream.method435();
                  this.inStream.initBitAccess();

                  for(j20 = 0; j20 < 4; ++j20) {
                     for(i23 = 0; i23 < 13; ++i23) {
                        for(l25 = 0; l25 < 13; ++l25) {
                           j28 = this.inStream.readBits(1);
                           if(j28 == 1) {
                              this.anIntArrayArrayArray1129[j20][i23][l25] = this.inStream.readBits(26);
                           } else {
                              this.anIntArrayArrayArray1129[j20][i23][l25] = -1;
                           }
                        }
                     }
                  }

                  this.inStream.finishBitAccess();
                  var20 = this.inStream.readUnsignedWord();
                  this.aBoolean1159 = true;
               }

               if(this.anInt1069 == var20 && this.anInt1070 == j15 && this.loadingStage == 2) {
                  this.pktType = -1;
                  return true;
               }

               this.anInt1069 = var20;
               this.anInt1070 = j15;
               this.baseX = (this.anInt1069 - 6) * 8;
               this.baseY = (this.anInt1070 - 6) * 8;
               this.aBoolean1141 = (this.anInt1069 / 8 == 48 || this.anInt1069 / 8 == 49) && this.anInt1070 / 8 == 48;
               if(this.anInt1069 / 8 == 48 && this.anInt1070 / 8 == 148) {
                  this.aBoolean1141 = true;
               }

               this.loadingStage = 1;
               this.aLong824 = System.currentTimeMillis();
               this.aRSImageProducer_1165.initDrawingArea();
               this.aTextDrawingArea_1271.drawText(0, "Loading - please wait.", 151, 257);
               this.aTextDrawingArea_1271.drawText(16777215, "Loading - please wait.", 150, 256);
               this.aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
               if(this.pktType == 73) {
                  j20 = 0;

                  for(i23 = (this.anInt1069 - 6) / 8; i23 <= (this.anInt1069 + 6) / 8; ++i23) {
                     for(l25 = (this.anInt1070 - 6) / 8; l25 <= (this.anInt1070 + 6) / 8; ++l25) {
                        ++j20;
                     }
                  }

                  this.aByteArrayArray1183 = new byte[j20][];
                  this.aByteArrayArray1247 = new byte[j20][];
                  this.anIntArray1234 = new int[j20];
                  this.anIntArray1235 = new int[j20];
                  this.anIntArray1236 = new int[j20];
                  j20 = 0;

                  for(i23 = (this.anInt1069 - 6) / 8; i23 <= (this.anInt1069 + 6) / 8; ++i23) {
                     for(l25 = (this.anInt1070 - 6) / 8; l25 <= (this.anInt1070 + 6) / 8; ++l25) {
                        this.anIntArray1234[j20] = (i23 << 8) + l25;
                        if(this.aBoolean1141 && (l25 == 49 || l25 == 149 || l25 == 147 || i23 == 50 || i23 == 49 && l25 == 47)) {
                           this.anIntArray1235[j20] = -1;
                           this.anIntArray1236[j20] = -1;
                           ++j20;
                        } else {
                           j28 = this.anIntArray1235[j20] = this.onDemandFetcher.method562(0, l25, i23);
                           if(j28 != -1) {
                              this.onDemandFetcher.method558(3, j28);
                           }

                           i30 = this.anIntArray1236[j20] = this.onDemandFetcher.method562(1, l25, i23);
                           if(i30 != -1) {
                              this.onDemandFetcher.method558(3, i30);
                           }

                           ++j20;
                        }
                     }
                  }
               }

               int class30_sub1_1;
               int l33;
               if(this.pktType == 241) {
                  j20 = 0;
                  int[] var39 = new int[676];

                  int byte6;
                  int var32;
                  int var37;
                  for(l25 = 0; l25 < 4; ++l25) {
                     for(j28 = 0; j28 < 13; ++j28) {
                        for(i30 = 0; i30 < 13; ++i30) {
                           var32 = this.anIntArrayArrayArray1129[l25][j28][i30];
                           if(var32 != -1) {
                              var37 = var32 >> 14 & 1023;
                              byte6 = var32 >> 3 & 2047;
                              class30_sub1_1 = (var37 / 8 << 8) + byte6 / 8;

                              for(l33 = 0; l33 < j20; ++l33) {
                                 if(var39[l33] == class30_sub1_1) {
                                    class30_sub1_1 = -1;
                                    break;
                                 }
                              }

                              if(class30_sub1_1 != -1) {
                                 var39[j20++] = class30_sub1_1;
                              }
                           }
                        }
                     }
                  }

                  this.aByteArrayArray1183 = new byte[j20][];
                  this.aByteArrayArray1247 = new byte[j20][];
                  this.anIntArray1234 = new int[j20];
                  this.anIntArray1235 = new int[j20];
                  this.anIntArray1236 = new int[j20];

                  for(l25 = 0; l25 < j20; ++l25) {
                     j28 = this.anIntArray1234[l25] = var39[l25];
                     i30 = j28 >> 8 & 255;
                     var32 = j28 & 255;
                     var37 = this.anIntArray1235[l25] = this.onDemandFetcher.method562(0, var32, i30);
                     if(var37 != -1) {
                        this.onDemandFetcher.method558(3, var37);
                     }

                     byte6 = this.anIntArray1236[l25] = this.onDemandFetcher.method562(1, var32, i30);
                     if(byte6 != -1) {
                        this.onDemandFetcher.method558(3, byte6);
                     }
                  }
               }

               j20 = this.baseX - this.anInt1036;
               i23 = this.baseY - this.anInt1037;
               this.anInt1036 = this.baseX;
               this.anInt1037 = this.baseY;

               for(l25 = 0; l25 < 16384; ++l25) {
                  NPC var43 = this.npcArray[l25];
                  if(var43 != null) {
                     for(i30 = 0; i30 < 10; ++i30) {
                        var43.smallX[i30] -= j20;
                        var43.smallY[i30] -= i23;
                     }

                     var43.x -= j20 * 128;
                     var43.y -= i23 * 128;
                  }
               }

               for(l25 = 0; l25 < this.maxPlayers; ++l25) {
                  Player var44 = this.playerArray[l25];
                  if(var44 != null) {
                     for(i30 = 0; i30 < 10; ++i30) {
                        var44.smallX[i30] -= j20;
                        var44.smallY[i30] -= i23;
                     }

                     var44.x -= j20 * 128;
                     var44.y -= i23 * 128;
                  }
               }

               this.aBoolean1080 = true;
               byte var46 = 0;
               byte var42 = 104;
               byte var36 = 1;
               if(j20 < 0) {
                  var46 = 103;
                  var42 = -1;
                  var36 = -1;
               }

               byte var33 = 0;
               byte var35 = 104;
               byte var40 = 1;
               if(i23 < 0) {
                  var33 = 103;
                  var35 = -1;
                  var40 = -1;
               }

               for(class30_sub1_1 = var46; class30_sub1_1 != var42; class30_sub1_1 += var36) {
                  for(l33 = var33; l33 != var35; l33 += var40) {
                     int i34 = class30_sub1_1 + j20;
                     int j34 = l33 + i23;

                     for(int k34 = 0; k34 < 4; ++k34) {
                        if(i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104) {
                           this.groundArray[k34][class30_sub1_1][l33] = this.groundArray[k34][i34][j34];
                        } else {
                           this.groundArray[k34][class30_sub1_1][l33] = null;
                        }
                     }
                  }
               }

               for(Class30_Sub1 var41 = (Class30_Sub1)this.aClass19_1179.reverseGetFirst(); var41 != null; var41 = (Class30_Sub1)this.aClass19_1179.reverseGetNext()) {
                  var41.anInt1297 -= j20;
                  var41.anInt1298 -= i23;
                  if(var41.anInt1297 < 0 || var41.anInt1298 < 0 || var41.anInt1297 >= 104 || var41.anInt1298 >= 104) {
                     var41.unlink();
                  }
               }

               if(this.destX != 0) {
                  this.destX -= j20;
                  this.destY -= i23;
               }

               this.aBoolean1160 = false;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 208) {
               var20 = this.inStream.method437();
               if(var20 >= 0) {
                  this.method60(var20);
               }

               this.anInt1018 = var20;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 98) {//yell
            	int type = this.inStream.readUnsignedByte();//unused currently
            	long sender = this.inStream.readQWord();
                int rights = this.inStream.readUnsignedByte();
                String name = TextClass.fixName(TextClass.nameForLong(sender));
            	String message = this.inStream.readString();
            	String icon = "";
            	if(rights == 1)
            		icon = "@cr1@";
            	if(rights == 2)
            		icon = "@cr2@";
            	if(rights == 10)
            		icon = "@don1@";
            	this.pushMessage(message, 98, icon+""+name);
                this.pktType = -1;
                return true;
             }
            
            if(this.pktType == 99) {
               this.anInt1021 = this.inStream.readUnsignedByte();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 75) {
               var20 = this.inStream.method436();
               j15 = this.inStream.method436();
               RSInterface.interfaceCache[j15].anInt233 = 2;
               RSInterface.interfaceCache[j15].mediaID = var20;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 114) {
               this.anInt1104 = this.inStream.method434() * 30;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 60) {
               this.anInt1269 = this.inStream.readUnsignedByte();
               this.anInt1268 = this.inStream.method427();

               while(this.inStream.currentOffset < this.pktSize) {
                  var20 = this.inStream.readUnsignedByte();
                  this.method137(this.inStream, var20);
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 35) {
               var20 = this.inStream.readUnsignedByte();
               j15 = this.inStream.readUnsignedByte();
               j20 = this.inStream.readUnsignedByte();
               i23 = this.inStream.readUnsignedByte();
               this.aBooleanArray876[var20] = true;
               this.anIntArray873[var20] = j15;
               this.anIntArray1203[var20] = j20;
               this.anIntArray928[var20] = i23;
               this.anIntArray1030[var20] = 0;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 174) {
               var20 = this.inStream.readUnsignedWord();
               j15 = this.inStream.readUnsignedByte();
               j20 = this.inStream.readUnsignedWord();
               if(soundEffectVolume != 0 && j15 != 0 && this.soundCount < 50) {
                  //System.out.println("sound sent: " + var20 + ":" + j15 + ":" + j20);
                  this.sound[this.soundCount] = var20;
                  this.soundType[this.soundCount] = j15;
                  this.soundDelay[this.soundCount] = j20;
                  aClass26Array1468[this.soundCount] = null;
                  ++this.soundCount;
               }
               //++this.soundCount;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 104) {
               var20 = this.inStream.method427();
               j15 = this.inStream.method426();
               String var31 = this.inStream.readString();
               if(var20 >= 1 && var20 <= 5) {
                  if(var31.equalsIgnoreCase("null")) {
                     var31 = null;
                  }

                  this.atPlayerActions[var20 - 1] = var31;
                  this.atPlayerArray[var20 - 1] = j15 == 0;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 78) {
               this.destX = 0;
               this.pktType = -1;
               return true;
            }

            String var26;
            boolean var29;
            if(this.pktType == 253) {
               s2 = this.inStream.readString();
               long var25;
               String var34;
               if(s2.endsWith(":tradereq:")) {
                  var34 = s2.substring(0, s2.indexOf(":"));
                  var25 = TextClass.longForName(var34);
                  var29 = false;

                  for(j28 = 0; j28 < this.ignoreCount; ++j28) {
                     if(this.ignoreListAsLongs[j28] == var25) {
                        var29 = true;
                        break;
                     }
                  }

                  if(!var29 && this.anInt1251 == 0) {
                     this.pushMessage("wishes to trade with you.", 4, var34);
                  }
               } else if(s2.endsWith(":duelreq:")) {
                  var34 = s2.substring(0, s2.indexOf(":"));
                  var25 = TextClass.longForName(var34);
                  var29 = false;

                  for(j28 = 0; j28 < this.ignoreCount; ++j28) {
                     if(this.ignoreListAsLongs[j28] == var25) {
                        var29 = true;
                        break;
                     }
                  }

                  if(!var29 && this.anInt1251 == 0) {
                     this.pushMessage("wishes to duel with you.", 8, var34);
                  }
               } else if(s2.endsWith(":chalreq:")) {
                  var34 = s2.substring(0, s2.indexOf(":"));
                  var25 = TextClass.longForName(var34);
                  var29 = false;

                  for(j28 = 0; j28 < this.ignoreCount; ++j28) {
                     if(this.ignoreListAsLongs[j28] == var25) {
                        var29 = true;
                        break;
                     }
                  }

                  if(!var29 && this.anInt1251 == 0) {
                     var26 = s2.substring(s2.indexOf(":") + 1, s2.length() - 9);
                     this.pushMessage(var26, 8, var34);
                  }
               } else if(s2.endsWith(":airtiara:")) {
                  tiara = 1;
               } else if(s2.endsWith(":watertiara:")) {
                  tiara = 2;
               } else if(s2.endsWith(":earthtiara:")) {
                  tiara = 3;
               } else if(s2.endsWith(":firetiara:")) {
                  tiara = 4;
               } else if(s2.endsWith(":mindtiara:")) {
                  tiara = 5;
               } else if(s2.endsWith(":bodytiara:")) {
                  tiara = 6;
               } else if(s2.endsWith(":cosmictiara:")) {
                  tiara = 7;
               } else if(s2.endsWith(":chaostiara:")) {
                  tiara = 8;
               } else if(s2.endsWith(":naturetiara:")) {
                  tiara = 9;
               } else if(s2.endsWith(":lawtiara:")) {
                  tiara = 10;
               } else if(s2.endsWith(":deathtiara:")) {
                  tiara = 11;
               } else if(s2.endsWith(":notiara:")) {
                  tiara = 0;
               } else {
                  this.pushMessage(s2, 0, "");
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 1) {
               for(var20 = 0; var20 < this.playerArray.length; ++var20) {
                  if(this.playerArray[var20] != null) {
                     this.playerArray[var20].anim = -1;
                  }
               }

               for(var20 = 0; var20 < this.npcArray.length; ++var20) {
                  if(this.npcArray[var20] != null) {
                     this.npcArray[var20].anim = -1;
                  }
               }

               this.pktType = -1;
               return true;
            }
			
            long var27;
            if(this.pktType == 50) {
               var27 = this.inStream.readQWord();
               j20 = this.inStream.readUnsignedByte();
               String var30 = TextClass.fixName(TextClass.nameForLong(var27));

               for(l25 = 0; l25 < this.friendsCount; ++l25) {
                  if(var27 == this.friendsListAsLongs[l25]) {
                     if(this.friendsNodeIDs[l25] != j20) {
                        this.friendsNodeIDs[l25] = j20;
                        this.needDrawTabArea = true;
                        if(j20 >= 2) {
                           this.pushMessage(var30 + " has logged in.", 5, "");
                        }

                        if(j20 <= 1) {
                           this.pushMessage(var30 + " has logged out.", 5, "");
                        }
                     }

                     var30 = null;
                     break;
                  }
               }

               if(var30 != null && this.friendsCount < 200) {
                  this.friendsListAsLongs[this.friendsCount] = var27;
                  this.friendsList[this.friendsCount] = var30;
                  this.friendsNodeIDs[this.friendsCount] = j20;
                  ++this.friendsCount;
                  this.needDrawTabArea = true;
               }

               var29 = false;

               while(!var29) {
                  var29 = true;

                  for(j28 = 0; j28 < this.friendsCount - 1; ++j28) {
                     if(this.friendsNodeIDs[j28] != nodeID && this.friendsNodeIDs[j28 + 1] == nodeID || this.friendsNodeIDs[j28] == 0 && this.friendsNodeIDs[j28 + 1] != 0) {
                        i30 = this.friendsNodeIDs[j28];
                        this.friendsNodeIDs[j28] = this.friendsNodeIDs[j28 + 1];
                        this.friendsNodeIDs[j28 + 1] = i30;
                        String s10 = this.friendsList[j28];
                        this.friendsList[j28] = this.friendsList[j28 + 1];
                        this.friendsList[j28 + 1] = s10;
                        long l32 = this.friendsListAsLongs[j28];
                        this.friendsListAsLongs[j28] = this.friendsListAsLongs[j28 + 1];
                        this.friendsListAsLongs[j28 + 1] = l32;
                        this.needDrawTabArea = true;
                        var29 = false;
                     }
                  }
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 110) {
               if(this.tabID == 12) {
                  this.needDrawTabArea = true;
               }

               this.energy = this.inStream.readUnsignedByte();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 254) {
               this.anInt855 = this.inStream.readUnsignedByte();
               if(this.anInt855 == 1) {
                  this.anInt1222 = this.inStream.readUnsignedWord();
               }

               if(this.anInt855 >= 2 && this.anInt855 <= 6) {
                  if(this.anInt855 == 2) {
                     this.anInt937 = 64;
                     this.anInt938 = 64;
                  }

                  if(this.anInt855 == 3) {
                     this.anInt937 = 0;
                     this.anInt938 = 64;
                  }

                  if(this.anInt855 == 4) {
                     this.anInt937 = 128;
                     this.anInt938 = 64;
                  }

                  if(this.anInt855 == 5) {
                     this.anInt937 = 64;
                     this.anInt938 = 0;
                  }

                  if(this.anInt855 == 6) {
                     this.anInt937 = 64;
                     this.anInt938 = 128;
                  }

                  this.anInt855 = 2;
                  this.anInt934 = this.inStream.readUnsignedWord();
                  this.anInt935 = this.inStream.readUnsignedWord();
                  this.anInt936 = this.inStream.readUnsignedByte();
               }

               if(this.anInt855 == 10) {
                  this.anInt933 = this.inStream.readUnsignedWord();
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 248) {
               var20 = this.inStream.method435();
               if(var20 == 18939){//ge sell interface reset
				   RSInterface.interfaceCache[18968].message = "N/A";
				   RSInterface.interfaceCache[18969].message = "0";
				   RSInterface.interfaceCache[18970].message = "1 coins";
				   RSInterface.interfaceCache[18971].message = "0 coins";
				   RSInterface.interfaceCache[18963].message = "Choose an item...";
				   RSInterface.interfaceCache[18967].message = "Choose an item from your inventory to sell.";
				   RSInterface.interfaceCache[18983].sprite1 = null;
			   }
               j15 = this.inStream.readUnsignedWord();
               if(this.backDialogID != -1) {
                  this.backDialogID = -1;
                  this.inputTaken = true;
               }

               if(this.inputDialogState != 0) {
                  this.inputDialogState = 0;
                  this.inputTaken = true;
               }

               this.openInterfaceID = var20;
               this.invOverlayInterfaceID = j15;
               this.needDrawTabArea = true;
               this.tabAreaAltered = true;
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }

            RSInterface var23;
            if(this.pktType == 79) {
               var20 = this.inStream.method434();
               j15 = this.inStream.method435();
               var23 = RSInterface.interfaceCache[var20];
               if(var23 != null && var23.type == 0) {
                  if(j15 < 0) {
                     j15 = 0;
                  }

                  if(j15 > var23.scrollMax - var23.height) {
                     j15 = var23.scrollMax - var23.height;
                  }

                  var23.scrollPosition = j15;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 68) {
               for(var20 = 0; var20 < this.variousSettings.length; ++var20) {
                  if(this.variousSettings[var20] != this.anIntArray1045[var20]) {
                     this.variousSettings[var20] = this.anIntArray1045[var20];
                     this.method33(var20);
                     this.needDrawTabArea = true;
                  }
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 196) {
               var27 = this.inStream.readQWord();
               j20 = this.inStream.readDWord();
               i23 = this.inStream.readUnsignedByte();
               var29 = false;
               if(i23 <= 1 && i23 >= 10) {
                  for(j28 = 0; j28 < this.ignoreCount; ++j28) {
                     if(this.ignoreListAsLongs[j28] == var27) {
                        var29 = true;
                     }
                  }
               }

               if(!var29 && this.anInt1251 == 0) {
                  try {
                     var26 = TextInput.method525(this.pktSize - 13, this.inStream);
                     if(i23 != 2 && i23 != 3) {
                        if(i23 == 1) {
                           this.pushMessage(var26, 7, "@cr1@" + TextClass.fixName(TextClass.nameForLong(var27)));
                        } else {
							if(i23 == 10)
								this.pushMessage(var26, 3, "@don1@" + TextClass.fixName(TextClass.nameForLong(var27)));
							else
								this.pushMessage(var26, 3, TextClass.fixName(TextClass.nameForLong(var27)));
                        }
                     } else {
                        this.pushMessage(var26, 7, "@cr2@" + TextClass.fixName(TextClass.nameForLong(var27)));
                     }
                  } catch (Exception var17) {
                     SignLink.reporterror("cde1");
                  }
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 85) {
               this.anInt1269 = this.inStream.method427();
               this.anInt1268 = this.inStream.method427();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 24) {
               this.anInt1054 = this.inStream.method428();
               if(this.anInt1054 == this.tabID) {
                  if(this.anInt1054 == 3) {
                     this.tabID = 1;
                  } else {
                     this.tabID = 3;
                  }

                  this.needDrawTabArea = true;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 246) {
               var20 = this.inStream.method434();
               j15 = this.inStream.readUnsignedWord();
               j20 = this.inStream.readUnsignedWord();
               if(j20 == '\uffff') {
                  RSInterface.interfaceCache[var20].anInt233 = 0;
                  this.pktType = -1;
                  return true;
               }

               ItemDef var28 = ItemDef.forID(j20);
               RSInterface.interfaceCache[var20].anInt233 = 4;
               RSInterface.interfaceCache[var20].mediaID = j20;
               RSInterface.interfaceCache[var20].anInt270 = var28.modelRotation1;
               RSInterface.interfaceCache[var20].anInt271 = var28.modelRotation2;
               RSInterface.interfaceCache[var20].anInt269 = var28.modelZoom * 100 / j15;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 171) {
               boolean var24 = this.inStream.readUnsignedByte() == 1;
               j15 = this.inStream.readUnsignedWord();
               RSInterface.interfaceCache[j15].aBoolean266 = var24;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 142) {
               var20 = this.inStream.method434();
               this.method60(var20);
               if(this.backDialogID != -1) {
                  this.backDialogID = -1;
                  this.inputTaken = true;
               }

               if(this.inputDialogState != 0) {
                  this.inputDialogState = 0;
                  this.inputTaken = true;
               }

               this.invOverlayInterfaceID = var20;
               this.needDrawTabArea = true;
               this.tabAreaAltered = true;
               this.openInterfaceID = -1;
			   fullscreenInterfaceID = -1;
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 126) {
               s2 = this.inStream.readString();
               j15 = this.inStream.method435();
               
               if(j15 == 19045 || j15 == 19054 || j15 == 19063 || j15 == 19072 || j15 == 19081 || j15 == 19090)//ge offer boxes item names
                s2 = splitToMultiLines(s2, 85, this.aTextDrawingArea_1270);
			   if(j15 == 5383){//bank search
					realBankTitle = s2;
					updateBank();
			   }else{
					RSInterface.interfaceCache[j15].message = s2;
			   }
               if(RSInterface.interfaceCache[j15].parentID == this.tabInterfaceIDs[this.tabID]) {
                  this.needDrawTabArea = true;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 206) {
               this.publicChatMode = this.inStream.readUnsignedByte();
               this.privateChatMode = this.inStream.readUnsignedByte();
               this.tradeMode = this.inStream.readUnsignedByte();
               this.aBoolean1233 = true;
               this.inputTaken = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 240) {
               if(this.tabID == 12) {
                  this.needDrawTabArea = true;
               }

               this.weight = this.inStream.readSignedWord();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 8) {
               var20 = this.inStream.method436();
               j15 = this.inStream.readUnsignedWord();
               RSInterface.interfaceCache[var20].anInt233 = 1;
               RSInterface.interfaceCache[var20].mediaID = j15;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 122) {
               var20 = this.inStream.method436();
               j15 = this.inStream.method436();
               j20 = j15 >> 10 & 31;
               i23 = j15 >> 5 & 31;
               l25 = j15 & 31;
               RSInterface.interfaceCache[var20].textColor = (j20 << 19) + (i23 << 11) + (l25 << 3);
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 53) {
               this.needDrawTabArea = true;
               var20 = this.inStream.readUnsignedWord();
               var21 = RSInterface.interfaceCache[var20];
               j20 = this.inStream.readUnsignedWord();
				if(var20 == 5382){//bank search
					realBankItemIds = new int[j20];
					realBankItemAmounts = new int[j20];
				}
               for(i23 = 0; i23 < j20; ++i23) {
                  l25 = this.inStream.readUnsignedByte();
                  if(l25 == 255) {
                     l25 = this.inStream.method440();
                  }
				  int val = this.inStream.method436();
				  if(var20 == 5382){//bank search
					realBankItemIds[i23] = val;
					realBankItemAmounts[i23] = l25;
				  } else {
					var21.inv[i23] = val;
					var21.invStackSizes[i23] = l25;
				  }
               }

               for(i23 = j20; i23 < var21.inv.length; ++i23) {
				  if(var20 == 5382){//bank search
					realBankItemIds[i23] = 0;
					realBankItemAmounts[i23] = 0;
				  } else {
					var21.inv[i23] = 0;
					var21.invStackSizes[i23] = 0;
				  }
               }
				if(var20 == 5382)//bank search
					updateBank();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 230) {
               var20 = this.inStream.method435();
               j15 = this.inStream.readUnsignedWord();
               j20 = this.inStream.readUnsignedWord();
               i23 = this.inStream.method436();
               RSInterface.interfaceCache[j15].anInt270 = j20;
               RSInterface.interfaceCache[j15].anInt271 = i23;
               RSInterface.interfaceCache[j15].anInt269 = var20;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 221) {
               this.anInt900 = this.inStream.readUnsignedByte();
               this.needDrawTabArea = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 177) {
               this.aBoolean1160 = true;
               this.anInt995 = this.inStream.readUnsignedByte();
               this.anInt996 = this.inStream.readUnsignedByte();
               this.anInt997 = this.inStream.readUnsignedWord();
               this.anInt998 = this.inStream.readUnsignedByte();
               this.anInt999 = this.inStream.readUnsignedByte();
               if(this.anInt999 >= 100) {
                  var20 = this.anInt995 * 128 + 64;
                  j15 = this.anInt996 * 128 + 64;
                  j20 = this.method42(this.plane, j15, var20) - this.anInt997;
                  i23 = var20 - this.xCameraPos;
                  l25 = j20 - this.zCameraPos;
                  j28 = j15 - this.yCameraPos;
                  i30 = (int)Math.sqrt((double)(i23 * i23 + j28 * j28));
                  this.yCameraCurve = (int)(Math.atan2((double)l25, (double)i30) * 325.949D) & 2047;
                  this.xCameraCurve = (int)(Math.atan2((double)i23, (double)j28) * -325.949D) & 2047;
                  if(this.yCameraCurve < 128) {
                     this.yCameraCurve = 128;
                  }

                  if(this.yCameraCurve > 383) {
                     this.yCameraCurve = 383;
                  }
               }
			   //System.out.println("NEW CAM 177: "+this.xCameraCurve+" "+this.yCameraCurve);

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 249) {
               this.anInt1046 = this.inStream.method426();
               this.unknownInt10 = this.inStream.method436();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 65) {
               this.updateNPCs(this.inStream, this.pktSize);
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 27) {
               this.messagePromptRaised = false;
               this.inputDialogState = 1;
               this.amountOrNameInput = "";
               this.inputTaken = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 187) {
               this.messagePromptRaised = false;
               this.inputDialogState = 2;
               this.amountOrNameInput = "";
               this.inputTaken = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 97) {
               var20 = this.inStream.readUnsignedWord();
			   if(var20 == 18890){//ge buy interface reset
				   RSInterface.interfaceCache[18919].message = "N/A";
				   RSInterface.interfaceCache[18920].message = "0";
				   RSInterface.interfaceCache[18921].message = "1 coins";
				   RSInterface.interfaceCache[18922].message = "0 coins";
				   RSInterface.interfaceCache[18914].message = "Choose an item to exchange";
				   RSInterface.interfaceCache[18918].message = "Click the icon to the left to search for items.";
				   RSInterface.interfaceCache[18938].sprite1 = null;
				   RSInterface.interfaceCache[18933].aBoolean266 = false;
				   RSInterface.interfaceCache[18936].aBoolean266 = true;
			   }
               this.method60(var20);
               if(this.invOverlayInterfaceID != -1) {
                  this.invOverlayInterfaceID = -1;
                  this.needDrawTabArea = true;
                  this.tabAreaAltered = true;
               }

               if(this.backDialogID != -1) {
                  this.backDialogID = -1;
                  this.inputTaken = true;
               }

               if(this.inputDialogState != 0) {
                  this.inputDialogState = 0;
                  this.inputTaken = true;
               }

			   if(fullscreenInterfaceID == -1){
				   if(var20 == 18788)
					   this.fullscreenInterfaceID = var20;
				   else
					   this.openInterfaceID = var20;
			   }
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }
			
			if(this.pktType == 69) {
               var20 = this.inStream.readUnsignedWord();
			   int fullScreenInterface = this.inStream.readUnsignedWord();
               this.method60(var20);
               this.openInterfaceID = var20;
			   this.fullscreenInterfaceID = fullScreenInterface;
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 218) {
               var20 = this.inStream.method438();
               this.dialogID = var20;
               this.inputTaken = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 87) {
               var20 = this.inStream.method434();
               j15 = this.inStream.method439();
               this.anIntArray1045[var20] = j15;
               if(this.variousSettings[var20] != j15) {
                  this.variousSettings[var20] = j15;
                  this.method33(var20);
                  this.needDrawTabArea = true;
                  if(this.dialogID != -1) {
                     this.inputTaken = true;
                  }
               }

               this.pktType = -1;
               return true;
            }
			
            if(this.pktType == 36) {
               var20 = this.inStream.method434();
               byte var22 = this.inStream.readSignedByte();
               this.anIntArray1045[var20] = var22;
               if(this.variousSettings[var20] != var22) {
                  this.variousSettings[var20] = var22;
                  this.method33(var20);
                  this.needDrawTabArea = true;
                  if(this.dialogID != -1) {
                     this.inputTaken = true;
                  }
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 61) {
               this.anInt1055 = this.inStream.readUnsignedByte();
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 200) {
               var20 = this.inStream.readUnsignedWord();
               j15 = this.inStream.readSignedWord();
               var23 = RSInterface.interfaceCache[var20];
               var23.anInt257 = j15;
               if(j15 == -1) {
                  var23.anInt246 = 0;
                  var23.anInt208 = 0;
               }

               this.pktType = -1;
               return true;
            }

            if(this.pktType == 219) {
               if(this.invOverlayInterfaceID != -1) {
                  this.invOverlayInterfaceID = -1;
                  this.needDrawTabArea = true;
                  this.tabAreaAltered = true;
               }

               if(this.backDialogID != -1) {
                  this.backDialogID = -1;
                  this.inputTaken = true;
               }

               if(this.inputDialogState != 0) {
                  this.inputDialogState = 0;
                  this.inputTaken = true;
               }

               this.openInterfaceID = -1;
			   fullscreenInterfaceID = -1;
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 34) {
               this.needDrawTabArea = true;
               var20 = this.inStream.readUnsignedWord();
               var21 = RSInterface.interfaceCache[var20];

               while(this.inStream.currentOffset < this.pktSize) {
                  j20 = this.inStream.method422();
                  i23 = this.inStream.readUnsignedWord();
                  l25 = this.inStream.readUnsignedByte();
				  
                  if(l25 == 255) {
                     l25 = this.inStream.readDWord();
                  }

                  if(j20 >= 0 && j20 < var21.inv.length) {
                     var21.inv[j20] = i23;
                     var21.invStackSizes[j20] = l25;
                  }
               }

               this.pktType = -1;
               return true;
            }
            
            if(this.pktType == 16) {//selected item (ge)
                this.needDrawTabArea = true;
                int childId = this.inStream.readUnsignedWord();
                int itemId = this.inStream.readUnsignedWord();
                RSInterface rsi = RSInterface.interfaceCache[childId];
            	Sprite itemImg = ItemDef.getSprite(itemId, 1, 0);
            	rsi.sprite1 = itemImg;
            	ItemDef item = ItemDef.forID(itemId);
				String examine;
				if(item.description != null) {
                    examine = new String(item.description);
                 } else {
                    examine = "It\'s a " + item.name + ".";
                 }
				examine = splitToMultiLines(examine, 280, this.aTextDrawingArea_1270);
            	if(childId == 18983){
    				RSInterface.interfaceCache[18963].message = item.name;
    				RSInterface.interfaceCache[18967].message = examine;
            	}
            	if(childId == 19008){
    				RSInterface.interfaceCache[18993].message = item.name;
    				RSInterface.interfaceCache[18996].message = examine;
            	}
            	if(childId == 18938){
            		RSInterface.interfaceCache[18914].message = item.name;
    				RSInterface.interfaceCache[18918].message = examine;
    				RSInterface.interfaceCache[18933].aBoolean266 = true;
    				RSInterface.interfaceCache[18936].aBoolean266 = false;
            	}
                this.pktType = -1;
                return true;
             }
            
            if(this.pktType == 18) {//ge progress bar
                this.needDrawTabArea = true;
                var20 = this.inStream.readUnsignedWord();
                var21 = RSInterface.interfaceCache[var20];
                int p1 = this.inStream.readUnsignedByte();
                if(p1 == 250){
                	var21.textColor = rgb2col(138, 0, 16);
                	p1 = 100;
                }else if(p1 < 100)
                	var21.textColor = rgb2col(198, 139, 1);
                else
                	var21.textColor = rgb2col(0, 95, 0);
                double p1d = p1;
                double p2 = p1d/100;
                double p3 = var21.originalWidth*p2;
                int newWidth = (int) p3;
                var21.width = newWidth;
                this.pktType = -1;
                return true;
             }

            if(this.pktType == 105 || this.pktType == 84 || this.pktType == 147 || this.pktType == 215 || this.pktType == 4 || this.pktType == 117 || this.pktType == 156 || this.pktType == 44 || this.pktType == 160 || this.pktType == 101 || this.pktType == 151) {
               this.method137(this.inStream, this.pktType);
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 106) {
               this.tabID = this.inStream.method427();
               this.needDrawTabArea = true;
               this.tabAreaAltered = true;
               this.pktType = -1;
               return true;
            }

            if(this.pktType == 164) {
               var20 = this.inStream.method434();
               this.method60(var20);
               if(this.invOverlayInterfaceID != -1) {
                  this.invOverlayInterfaceID = -1;
                  this.needDrawTabArea = true;
                  this.tabAreaAltered = true;
               }

               this.backDialogID = var20;
               this.inputTaken = true;
               this.openInterfaceID = -1;
			   fullscreenInterfaceID = -1;
               this.aBoolean1149 = false;
               this.pktType = -1;
               return true;
            }

            SignLink.reporterror("T1 - " + this.pktType + "," + this.pktSize + " - " + this.anInt842 + "," + this.anInt843);
            this.resetLogout();
         } catch (IOException var18) {
            this.dropClient();
         } catch (Exception var19) {
            s2 = "T2 - " + this.pktType + "," + this.anInt842 + "," + this.anInt843 + " - " + this.pktSize + "," + (this.baseX + myPlayer.smallX[0]) + "," + (this.baseY + myPlayer.smallY[0]) + " - ";

            for(j15 = 0; j15 < this.pktSize && j15 < 50; ++j15) {
               s2 = s2 + this.inStream.buffer[j15] + ",";
            }

            SignLink.reporterror(s2);
            this.resetLogout();
         }

         return true;
      }
   }

   public void method146() {
      ++this.anInt1265;
      this.method47(true);
      this.method26(true);
      this.method47(false);
      this.method26(false);
      this.method55();
      this.method104();
      int j;
      int l;
      if(!this.aBoolean1160) {
         j = this.anInt1184;
         if(this.anInt984 / 256 > j) {
            j = this.anInt984 / 256;
         }

         if(this.aBooleanArray876[4] && this.anIntArray1203[4] + 128 > j) {
            j = this.anIntArray1203[4] + 128;
         }

         l = this.minimapInt1 + this.anInt896 & 2047;
         this.setCameraPos(600 + j * 3, j, this.anInt1014, this.method42(this.plane, myPlayer.y, myPlayer.x) - 50, l, this.anInt1015);
      }

      if(!this.aBoolean1160) {
         j = this.method120();
      } else {
         j = this.method121();
      }

      l = this.xCameraPos;
      int i1 = this.zCameraPos;
      int j1 = this.yCameraPos;
      int k1 = this.yCameraCurve;
      int l1 = this.xCameraCurve;

      int k2;
      for(k2 = 0; k2 < 5; ++k2) {
         if(this.aBooleanArray876[k2]) {
            int j2 = (int)(Math.random() * (double)(this.anIntArray873[k2] * 2 + 1) - (double)this.anIntArray873[k2] + Math.sin((double)this.anIntArray1030[k2] * (double)this.anIntArray928[k2] / 100.0D) * (double)this.anIntArray1203[k2]);
            if(k2 == 0) {
               this.xCameraPos += j2;
            }

            if(k2 == 1) {
               this.zCameraPos += j2;
            }

            if(k2 == 2) {
               this.yCameraPos += j2;
            }

            if(k2 == 3) {
               this.xCameraCurve = this.xCameraCurve + j2 & 2047;
            }

            if(k2 == 4) {
               this.yCameraCurve += j2;
               if(this.yCameraCurve < 128) {
                  this.yCameraCurve = 128;
               }

               if(this.yCameraCurve > 383) {
                  this.yCameraCurve = 383;
               }
            }
         }
      }

      k2 = Texture.anInt1481;
      Model.aBoolean1684 = true;
      Model.anInt1687 = 0;
      Model.anInt1685 = super.mouseX - 4;
      Model.anInt1686 = super.mouseY - 4;
      DrawingArea.setAllPixelsToZero();
      this.worldController.method313(this.xCameraPos, this.yCameraPos, this.xCameraCurve, this.zCameraPos, j, this.yCameraCurve);
      this.worldController.clearObj5Cache();
      this.updateEntities();
      this.drawHeadIcon();
      this.method37(k2);
      this.draw3dScreen();
      this.aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
      this.xCameraPos = l;
      this.zCameraPos = i1;
      this.yCameraPos = j1;
      this.yCameraCurve = k1;
      this.xCameraCurve = l1;
   }

   public void clearTopInterfaces() {
      this.stream.createFrame(130);
      	if(this.inputDialogState == 3){//ge search
      		amountOrNameInput = "";
    	   	totalItemResults = 0;
    	   	this.inputDialogState = 0;
    	   	this.inputTaken = true;
		}
		if(openInterfaceID == 5292 && this.inputDialogState == 2){//bank search
			this.inputDialogState = 0;
			this.inputTaken = true;
		}
      if(this.invOverlayInterfaceID != -1) {
         this.invOverlayInterfaceID = -1;
         this.needDrawTabArea = true;
         this.aBoolean1149 = false;
         this.tabAreaAltered = true;
      }

      if(this.backDialogID != -1) {
         this.backDialogID = -1;
         this.inputTaken = true;
         this.aBoolean1149 = false;
      }

      this.openInterfaceID = -1;
      fullscreenInterfaceClosed = true;
	  fullscreenInterfaceID = -1;
   }
   
   boolean fullscreenInterfaceClosed = false;//fixes closing fullscreen interface, if button is on minimap pos. - so wont be processed as minimap click!

   public Client() {
	  server = isLocalClient ? "127.0.0.1" : "orion06rsps.no-ip.org";
	  serverIp2 = "91.158.57.208";
	  cButtonHPos = -1;
		cButtonCPos = 0;
		chatTypeView = 0;
      this.anIntArrayArray825 = new int[104][104];
      this.friendsNodeIDs = new int[200];
      this.groundArray = new NodeList[4][104][104];
      this.aBoolean831 = false;
      this.aStream_834 = new Stream(new byte[5000]);
      this.npcArray = new NPC[16384];
      this.npcIndices = new int[16384];
      this.anIntArray840 = new int[1000];
      this.aStream_847 = Stream.create();
      this.aBoolean848 = true;
      this.openInterfaceID = -1;
      this.currentExp = new int[25];
      this.aBoolean872 = false;
      this.anIntArray873 = new int[5];
      this.anInt874 = -1;
      this.aBooleanArray876 = new boolean[5];
      this.drawFlames = false;
      this.reportAbuseInput = "";
      this.unknownInt10 = -1;
      this.menuOpen = false;
      this.inputString = "";
      this.maxPlayers = 2048;
      this.myPlayerIndex = 2047;
      this.playerArray = new Player[this.maxPlayers];
      this.playerIndices = new int[this.maxPlayers];
      this.anIntArray894 = new int[this.maxPlayers];
      this.aStreamArray895s = new Stream[this.maxPlayers];
      this.anInt897 = 1;
      this.anIntArrayArray901 = new int[104][104];
      this.anInt902 = 7759444;
      this.aByteArray912 = new byte[16384];
      this.currentStats = new int[25];
      this.ignoreListAsLongs = new long[100];
      this.loadingError = false;
      this.anInt927 = 3353893;
      this.anIntArray928 = new int[5];
      this.anIntArrayArray929 = new int[104][104];
      this.chatTypes = new int[100];
      this.chatNames = new String[100];
      this.chatMessages = new String[100];
      this.sideIcons = new Background[13];
	  mapBacks = new Background[2];
      this.aBoolean954 = true;
      this.friendsListAsLongs = new long[200];
      this.currentSong = -1;
      this.drawingFlames = false;
      this.spriteDrawX = -1;
      this.spriteDrawY = -1;
      anIntArray968 = new int[33];
      this.anIntArray969 = new int[256];
      this.decompressors = new Decompressor[5];
      this.variousSettings = new int[2000];
      this.aBoolean972 = false;
      this.anInt975 = 50;
      this.anIntArray976 = new int[this.anInt975];
      this.anIntArray977 = new int[this.anInt975];
      this.anIntArray978 = new int[this.anInt975];
      this.anIntArray979 = new int[this.anInt975];
      this.anIntArray980 = new int[this.anInt975];
      this.anIntArray981 = new int[this.anInt975];
      this.anIntArray982 = new int[this.anInt975];
      this.aStringArray983 = new String[this.anInt975];
      this.anInt985 = -1;
      this.hitMarks = new Sprite[20];
      this.anIntArray990 = new int[5];
      this.aBoolean994 = false;
      this.anInt1002 = 2301979;
      this.amountOrNameInput = "";
      this.aClass19_1013 = new NodeList();
      this.aBoolean1017 = false;
      this.anInt1018 = -1;
      this.anIntArray1030 = new int[5];
      this.aBoolean1031 = false;
      this.mapFunctions = new Sprite[100];
      this.dialogID = -1;
	  fullscreenInterfaceID = -1;
      this.maxStats = new int[25];
      this.anIntArray1045 = new int[2000];
      this.aBoolean1047 = true;
      this.anIntArray1052 = new int[151];
      this.anInt1054 = -1;
      this.aClass19_1056 = new NodeList();
      anIntArray1057 = new int[33];
      this.aClass9_1059 = new RSInterface();
      this.mapScenes = new Background[100];
      this.anInt1063 = 5063219;
      this.anIntArray1065 = new int[7];
      this.anIntArray1072 = new int[1000];
      this.anIntArray1073 = new int[1000];
      this.aBoolean1080 = false;
      this.friendsList = new String[200];
      this.inStream = Stream.create();
      this.expectedCRCs = new int[9];
      this.menuActionCmd2 = new int[500];
      this.menuActionCmd3 = new int[500];
      this.menuActionID = new int[500];
      this.menuActionCmd1 = new int[500];
      this.headIcons = new Sprite[20];
      this.headIconsHint = new Sprite[20];
      this.skullIcons = new Sprite[20];
      this.tabAreaAltered = false;
      this.aString1121 = "";
      this.atPlayerActions = new String[5];
      this.atPlayerArray = new boolean[5];
      this.anIntArrayArrayArray1129 = new int[4][13][13];
      this.anInt1132 = 2;
      this.aClass30_Sub2_Sub1_Sub1Array1140 = new Sprite[1000];
      this.aBoolean1141 = false;
      this.aBoolean1149 = false;
      this.crosses = new Sprite[8];
      this.musicEnabled = true;
      this.needDrawTabArea = false;
      this.loggedIn = false;
      this.canMute = false;
      this.aBoolean1159 = false;
      this.aBoolean1160 = false;
      this.anInt1171 = 1;
      this.myUsername = "";
      this.myPassword = "";
      this.genericLoadingError = false;
      this.reportAbuseInterfaceID = -1;
      this.aClass19_1179 = new NodeList();
      this.anInt1184 = 128;
      this.invOverlayInterfaceID = -1;
      this.stream = Stream.create();
      this.menuActionName = new String[500];
      this.anIntArray1203 = new int[5];
      this.sound = new int[50];
      this.anInt1210 = 2;
      this.anInt1211 = 78;
      this.promptInput = "";
      this.modIcons = new Background[3];
      this.tabID = 3;
      this.inputTaken = false;
      this.songChanging = true;
      anIntArray1229 = new int[151];
      this.aClass11Array1230 = new CollisionMap[4];
      this.aBoolean1233 = false;
      this.soundType = new int[50];
      this.aBoolean1242 = false;
      this.soundDelay = new int[50];
      this.rsAlreadyLoaded = false;
      this.welcomeScreenRaised = false;
      this.messagePromptRaised = false;
      this.loginMessage1 = "";
      this.loginMessage2 = "";
      this.backDialogID = -1;
	  fullscreenInterfaceID = -1;
      this.anInt1279 = 2;
      this.bigX = new int[4000];
      this.bigY = new int[4000];
      this.anInt1289 = -1;
   }

   static {
      int i = 0;

      int k;
      for(k = 0; k < 99; ++k) {
         int l = k + 1;
         int i1 = (int)((double)l + 300.0D * Math.pow(2.0D, (double)l / 7.0D));
         i += i1;
         anIntArray1019[k] = i / 4;
      }

      anIntArray1232 = new int[32];
      i = 2;

      for(k = 0; k < 32; ++k) {
         anIntArray1232[k] = i - 1;
         i += i;
      }

   }
}
