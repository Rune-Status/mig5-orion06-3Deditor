package client;

import java.awt.BorderLayout;
import worldmap.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.InetAddress;
import java.net.URI;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.filechooser.FileFilter;
import javax.swing.*;

import Editor.ItemSpawn;
import Editor.NpcSpawn;
import Editor.Position;

import java.io.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;

public class Jframe extends Client implements ActionListener {

   public static JMenuItem menuItem;
   public JFrame frame;
   public static JMenu donatorMenu;
   public static JMenu serverMenu;
   public JPanel e;

   public Jframe(String[] args) {
      try {
		try{
			SignLink.startpriv(InetAddress.getByName(server));
		 } catch (Exception e) {
			server = serverIp2;
			SignLink.startpriv(InetAddress.getByName(server));
		 }
		try {
			SpriteLoader.loadSprites();
			SpriteLoader.loadInterfaceSprites();
			cacheSprite = SpriteLoader.sprites;
			cacheSpriteI = SpriteLoader.spritesI;
		} catch (Exception e) {
			System.out.println("Failed to load custom sprites.");
		}
         this.initUI();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }
   
   void setTransparent(JButton button){
	   button.setOpaque(false);
	   button.setContentAreaFilled(false);
	   button.setBorderPainted(false);
   }
   
   void setTransparent(JMenu button){
	   button.setOpaque(false);
	   button.setContentAreaFilled(false);
	   button.setBorderPainted(false);
   }
   
   int pX, pY;
   
   public void updateTitle(){
	   this.frame.setTitle(Client.NAME+" [EDITOR]");
   }
   
   private JPanel editPanel;
   
   public static JTextField itemId;
   JLabel itemIdLabel;
   
   public static JTextField itemAmount;
   JLabel itemAmountLabel;
   
   public static JTextField itemPosition;
   JLabel itemPositionLabel;
   
   public static JCheckBox isP2PItemButton;
   
   public static JTextField npcId;
   
   public static JTextField npcPosition;
   
   public static JCheckBox isP2PNpcButton;
   
   static JComboBox npcFaceList;
   
   String npcFace[] = {"Walk", "North", "South", "East", "West"};
   
   public void initUI() {
	      try {
			 JFrame.setDefaultLookAndFeelDecorated(true);
			 UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel"); //Substance<NAME>LookAndFeel
	         JPopupMenu.setDefaultLightWeightPopupEnabled(false);
	         this.frame = new JFrame();
	         this.frame.setLayout(new BorderLayout());
	         this.frame.setResizable(false);
	         //this.frame.setDefaultCloseOperation(3);
			 this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            		if(Client.loggedIn && !editMode)
							JOptionPane.showMessageDialog(frame, "Please log out first before closing the client.");
	            		else
							System.exit(0);
	            }
			});
	         e = new JPanel();
	         e.setLayout(new BorderLayout());
	         e.add(this);
	         
	         
	         e.setPreferredSize(new Dimension(765, 503));
	         JMenu fileMenu = new JMenu("File");
	         
	         String[] mainButtons = new String[] { "Load Position", "Save Spawns"};

				for (String name : mainButtons) {
					JMenuItem menuItem = new JMenuItem(name);
					if (name.equalsIgnoreCase("-")) {
						fileMenu.addSeparator();
					} else {
						menuItem.addActionListener(this);
						fileMenu.add(menuItem);
					}
				}
	         
			 JButton btnForums = new JButton("Forums");
			 btnForums.addActionListener(this);
			 JButton btnWorldmap = new JButton("World Map");
			 btnWorldmap.addActionListener(this);
			 JButton btnScreenshot = new JButton("Screenshot");
			 btnScreenshot.addActionListener(this);
			 
			 JButton btnClearSelections = new JButton("Clear selections");
			 btnClearSelections.addActionListener(this);

			 /*JButton btnExit = new JButton("Exit");
			 btnExit.addActionListener(this);*/
			 JMenuBar menuBar = new JMenuBar();
			 serverMenu = new JMenu("Server Menu");
			 JMenuItem menuItem21 = new JMenuItem("Original");
			 JMenuItem menuItem22 = new JMenuItem("Easy (x40)");
			 menuItem21.addActionListener(this);
			 menuItem22.addActionListener(this);
			 serverMenu.add(menuItem21);
			 serverMenu.add(menuItem22);
			 donatorMenu = new JMenu("Donator Menu");
			 JMenuItem menuItem11 = new JMenuItem("317 Gameframe");
			 JMenuItem menuItem12 = new JMenuItem("459 Gameframe");
			 JMenuItem menuItem13 = new JMenuItem("474 Gameframe");
			 menuItem11.addActionListener(this);
			 menuItem12.addActionListener(this);
			 menuItem13.addActionListener(this);
			 donatorMenu.add(menuItem11);
			 donatorMenu.add(menuItem12);
			 donatorMenu.add(menuItem13);
			 menuBar.add(fileMenu);
			 /*menuBar.add(btnForums);
			 menuBar.add(btnWorldmap);
			 menuBar.add(btnScreenshot);
			 menuBar.add(serverMenu);
			 menuBar.add(donatorMenu);*/
			 //menuBar.add(btnExit);
			 menuBar.add(btnClearSelections);
			 
			 editPanel = new JPanel();
			 editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
			 
			 itemIdLabel = new JLabel("Id: ");  
			 editPanel.add(itemIdLabel);
			 itemId = new JTextField(10);             
			 editPanel.add(itemId);
			 itemId.setMaximumSize(new java.awt.Dimension(200, 20));
			 
			 itemAmountLabel = new JLabel("Amount: ");  
			 editPanel.add(itemAmountLabel);
			 itemAmount = new JTextField(10);             
			 editPanel.add(itemAmount);
			 itemAmount.setMaximumSize(new java.awt.Dimension(200, 20));
			 
			 itemPositionLabel = new JLabel("Position: ");  
			 editPanel.add(itemPositionLabel);
			 itemPosition = new JTextField(10);             
			 editPanel.add(itemPosition);
			 itemPosition.setMaximumSize(new java.awt.Dimension(200, 20));
			 
			 
			 JButton addItemButton = new JButton("Add item");       
			 editPanel.add(addItemButton);
			 addItemButton.addActionListener(this);
				
			 JButton removeItemButton = new JButton("Remove item");       
			 editPanel.add(removeItemButton);
			 removeItemButton.addActionListener(this);
			 
			 JButton modifyItemButton = new JButton("Modify item");       
			 editPanel.add(modifyItemButton);
			 modifyItemButton.addActionListener(this); 
			 
			 isP2PItemButton = new JCheckBox("P2P");
			 isP2PItemButton.addActionListener(this);
			 editPanel.add(isP2PItemButton);
			 
			 
			 
			 itemIdLabel = new JLabel("Id: ");  
			 editPanel.add(itemIdLabel);
			 npcId = new JTextField(10);             
			 editPanel.add(npcId);
			 npcId.setMaximumSize(new java.awt.Dimension(200, 20));
			 
			 itemPositionLabel = new JLabel("Position: ");  
			 editPanel.add(itemPositionLabel);
			 npcPosition = new JTextField(10);             
			 editPanel.add(npcPosition);
			 npcPosition.setMaximumSize(new java.awt.Dimension(200, 20));
			 
			 npcFaceList = new JComboBox(npcFace);
			 npcFaceList.addActionListener(this);
			 npcFaceList.setMaximumSize(new java.awt.Dimension(200, 20));
			 editPanel.add(npcFaceList);
			 npcFaceList.addActionListener(this);
			 
			 
			 JButton addNpcButton = new JButton("Add npc");       
			 editPanel.add(addNpcButton);
			 addNpcButton.addActionListener(this);
				
			 JButton removeNpcButton = new JButton("Remove npc");       
			 editPanel.add(removeNpcButton);
			 removeNpcButton.addActionListener(this);
			 
			 JButton modifyNpcButton = new JButton("Modify npc");       
			 editPanel.add(modifyNpcButton);
			 modifyNpcButton.addActionListener(this); 
			 
			 isP2PNpcButton = new JCheckBox("P2P");
			 isP2PNpcButton.addActionListener(this);
			 editPanel.add(isP2PNpcButton);
			 
			 
			 
			 donatorMenu.setEnabled(false);
			 this.frame.getContentPane().add(editPanel, "West");
			 this.frame.getContentPane().add(menuBar, "North");
	         this.frame.getContentPane().add(e, "Center");
	         this.frame.pack();
	         this.frame.setLocationRelativeTo((Component)null);
	         this.frame.setVisible(true);
	         this.frame.setResizable(false);
	         this.init();
	         
	         updateTitle();
	      } catch (Exception var2) {
	         var2.printStackTrace();
	      }

	   }
   
   /*public void initUI() {
      try {
		 //JFrame.setDefaultLookAndFeelDecorated(true);
		 UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel"); //Substance<NAME>LookAndFeel
         JPopupMenu.setDefaultLightWeightPopupEnabled(false);
         this.frame = new JFrame(Client.NAME);
         this.frame.setUndecorated(true);
         this.frame.setLayout(new BorderLayout());
         this.frame.setResizable(false);
         //this.frame.setDefaultCloseOperation(3);
         this.frame.addMouseListener(new MouseAdapter() {
             public void mousePressed(MouseEvent me) {
                 // Get x,y and store them
                 pX = me.getX();
                 pY = me.getY();

             }

              public void mouseDragged(MouseEvent me) {

                 frame.setLocation(frame.getLocation().x + me.getX() - pX,
                         frame.getLocation().y + me.getY() - pY);
             }
         });

         this.frame.addMouseMotionListener(new MouseMotionAdapter() {
             public void mouseDragged(MouseEvent me) {

                 frame.setLocation(frame.getLocation().x + me.getX() - pX,
                         frame.getLocation().y + me.getY() - pY);
             }
         });
		 this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            		if(Client.loggedIn)
						JOptionPane.showMessageDialog(frame, "Please log out first before closing the client.");
            		else
						System.exit(0);
            }
		});
         e = new JPanel();
         e.setLayout(new BorderLayout());
         e.add(this);
         e.setPreferredSize(new Dimension(765, 503));
		 JButton btnForums = new JButton("Forums");
		 btnForums.addActionListener(this);
		 
		 Font font = btnForums.getFont();
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 btnForums.setFont(font.deriveFont(attributes));
		 
		 JButton btnWorldmap = new JButton("World Map");
		 btnWorldmap.addActionListener(this);
		 btnWorldmap.setFont(font.deriveFont(attributes));
		 JButton btnScreenshot = new JButton("Screenshot");
		 btnScreenshot.addActionListener(this);
		 btnScreenshot.setFont(font.deriveFont(attributes));
		 JButton btnExit = new JButton("Exit");
		 btnExit.addActionListener(this);
		 btnExit.setFont(font.deriveFont(attributes));
		 //JMenuBar menuBar = new JMenuBar();
		 final int forumsX = 94;
		 final int worldmapX = 178;
		 final int screenShotX = 300;
		 final int donatorX = 400;
		 JMenuBar menuBar=new JMenuBar(){

			  public void paintComponent(Graphics g)

			  {
			  g.drawImage(Client.cacheSprite[45].toImage(),0,0,this);//background
			  g.drawImage(Client.cacheSprite[46].toImage(),10,2,this);//logo
			  g.drawImage(Client.cacheSprite[47].toImage(),forumsX,2,this);//forums arrow
			  g.drawImage(Client.cacheSprite[48].toImage(),worldmapX,1,this);//forums arrow
			  g.setColor(Color.black);
			  g.drawLine(0, 25, 765, 25);
			  }

			 };
		 donatorMenu = new JMenu("Donator Menu");
		 donatorMenu.setFont(font.deriveFont(attributes));
		 JMenuItem menuItem11 = new JMenuItem("317 Gameframe");
		 JMenuItem menuItem12 = new JMenuItem("459 Gameframe");
		 JMenuItem menuItem13 = new JMenuItem("474 Gameframe");
		 menuBar.setPreferredSize(new Dimension(765, 26));
		 menuItem11.addActionListener(this);
		 menuItem12.addActionListener(this);
		 menuItem13.addActionListener(this);
		 donatorMenu.add(menuItem11);
		 donatorMenu.add(menuItem12);
		 donatorMenu.add(menuItem13);
		 setTransparent(btnForums);
		 setTransparent(btnWorldmap);
		 setTransparent(btnScreenshot);
		 setTransparent(donatorMenu);
		 setTransparent(btnExit);
		 menuBar.setLayout(null);
		 menuBar.add(btnForums).setBounds(forumsX+13, 0, 66, 25);
		 menuBar.add(btnWorldmap).setBounds(worldmapX+23, 0, 66, 25);
		 menuBar.add(btnScreenshot).setBounds(screenShotX, 0, 66, 25);
		 menuBar.add(donatorMenu).setBounds(donatorX, 0, 81, 25);
		 menuBar.add(btnExit).setBounds(699, 0, 66, 25);
		 donatorMenu.setEnabled(false);
		 this.frame.getContentPane().add(menuBar, "North");
         this.frame.getContentPane().add(e, "Center");
         this.frame.pack();
         this.frame.setLocationRelativeTo((Component)null);
         this.frame.setVisible(true);
         this.frame.setResizable(false);
         this.init();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }*/

   public static void openUpWebSite(String url) {
      Desktop d = Desktop.getDesktop();

      try {
         d.browse(new URI(url));
      } catch (Exception var3) {
         ;
      }

   }
   
   void addItem(){
	   String s = itemId.getText();
		int id = Integer.valueOf(s);
		s = itemAmount.getText();
		int amount = Integer.valueOf(s);
		String[] coords = itemPosition.getText().split(",");
		int x = 0;
		int y = 0;
		int z = this.plane;
		boolean isP2P = isP2PItemButton.isSelected();
		if(coords.length == 3){
			  x = Integer.valueOf(coords[0]);
			  y = Integer.valueOf(coords[1]);
			  z = Integer.valueOf(coords[2]);
		  } else if(coords.length == 2){
			  x = Integer.valueOf(coords[0]);
			  y = Integer.valueOf(coords[1]);
		  }
		if(id >= 0 && amount >= 0 && x > 0 && y > 0 && z >= 0 && z <= 3){
			this.selectedItem = this.spawnFakeItem(id, amount, x, y, z, new ItemSpawn(id, amount, new Position(x, y, z), isP2P));
			this.pushMessage("New item spawned: "+id+" ("+x+","+y+","+z+")", 0, "");
		}
   }
   
   void addNpc(){
	   String s = npcId.getText();
		int id = Integer.valueOf(s);
		int face = getFacing();
		String[] coords = npcPosition.getText().split(",");
		int x = 0;
		int y = 0;
		int z = this.plane;
		boolean isP2P = isP2PNpcButton.isSelected();
		if(coords.length == 3){
			  x = Integer.valueOf(coords[0]);
			  y = Integer.valueOf(coords[1]);
			  z = Integer.valueOf(coords[2]);
		  } else if(coords.length == 2){
			  x = Integer.valueOf(coords[0]);
			  y = Integer.valueOf(coords[1]);
		  }
		if(id >= 0 && x > 0 && y > 0 && z >= 0 && z <= 3){
			this.selectedNpc = spawnFakeNpc(id, x, y, z, face, new NpcSpawn(id, face, new Position(x, y, z), isP2P));
			this.pushMessage("New npc spawned: "+id+" ("+x+","+y+","+z+")", 0, "");
		}
   }
   
   /*int NORTH = 2;
	int SOUTH = 0;
	int WALK = 1;
	int WEST = 5;
	int EAST = 4;
	
	*
	*
	*npcFaceList;
   
   String npcFace[] = {"Walk", "North", "South", "East", "West"};
	*/
   
   int getFacing(){
	   int i = npcFaceList.getSelectedIndex();
	   if(i == 2){//S
		   return 0;
	   }else if(i == 1){//N
		   return 2;
	   }else if(i == 3){//E
		   return 4;
	   }else if(i == 4){//W
		   return 5;
	   }//Walk
	   return 1;
   }
   
   public static void setFacing(int i){
	   if(i == 0){//S
		   npcFaceList.setSelectedIndex(2);
		   return;
	   }else if(i == 2){//N
		   npcFaceList.setSelectedIndex(1);
		   return;
	   }else if(i == 4){//E
		   npcFaceList.setSelectedIndex(3);
		   return;
	   }else if(i == 5){//W
		   npcFaceList.setSelectedIndex(4);
		   return;
	   }//Walk
	   npcFaceList.setSelectedIndex(0);
   }

   public void actionPerformed(ActionEvent evt) {
      String cmd = evt.getActionCommand();

      try {
         if(cmd != null) {
        	 
        	 if (cmd=="Clear selections"){
        		 this.selectedItem = null;
         		this.selectedNpc = null;
         		this.pushMessage("Selections cleared.", 0, "");
 			  }
			 
        	 if (cmd=="Add item"){
        		addItem();
			  }
        	 
        	 if (cmd=="Remove item"){
				 if(this.selectedItem == null){
					 this.pushMessage("No item selected!", 0, "");
					 return;
				 }
				 removeItem();
			  }
        	 
        	 if (cmd=="Modify item"){
        		 if(this.selectedItem == null){
					 this.pushMessage("No item selected!", 0, "");
					 return;
				 }
				 removeItem();
				 addItem();
			  }
        	 
        	 if (cmd=="Add npc"){
         		addNpc();
 			  }
         	 
         	 if (cmd=="Remove npc"){
 				 if(this.selectedNpc == null){
 					 this.pushMessage("No npc selected!", 0, "");
 					 return;
 				 }
 				 removeNpc();
 			  }
         	 
         	 if (cmd=="Modify npc"){
         		 if(this.selectedNpc == null){
 					 this.pushMessage("No npc selected!", 0, "");
 					 return;
 				 }
 				 removeNpc();
 				 addNpc();
 			  }
         	 
         	 if (cmd=="Save Spawns"){
        		 ItemSpawn.saveSpawns();
        		 NpcSpawn.saveSpawns();
        		 this.saveLocation();
        		 this.pushMessage("Saved spawns.", 0, "");
			  }
        	 
			  if (cmd=="Load Position"){
				  String[] coords = JOptionPane.showInputDialog(frame, "Enter the coordinates for map: (X,Y,(Z))", "Coordinates", 1).split(",");
				  int x = 3217;
				  int y = 3218;
				  int z = 0;
				  if(coords.length == 3){
					  x = Integer.valueOf(coords[0]);
					  y = Integer.valueOf(coords[1]);
					  z = Integer.valueOf(coords[2]);
				  } else if(coords.length == 2){
					  x = Integer.valueOf(coords[0]);
					  y = Integer.valueOf(coords[1]);
				  }
				  this.loadPos(x, y, z);
			  }
			  
        	 
            /*if(cmd.equalsIgnoreCase("Exit")) {
            	if(Client.loggedIn)
					JOptionPane.showMessageDialog(frame, "Please log out first before closing the client.");
        		else
					System.exit(0);
            }

			if(cmd.equals("317 Gameframe")) {
				try{
					Client.gameframeVersion = 317;
					Client.updateGameframeSprites();
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("459 Gameframe")) {
				try{
					Client.gameframeVersion = 459;
					Client.updateGameframeSprites();
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("474 Gameframe")) {
				try{
					Client.gameframeVersion = 474;
					Client.updateGameframeSprites();
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("Original")) {
				try{
					if(Client.loggedIn)
						JOptionPane.showMessageDialog(frame, "Please log out first before server change.");
	        		else{
	        			Client.isEasyOrionClient = false;
	        			Client.writeSettings();
						updateTitle();
	        		}
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("Easy (x40)")) {
				try{
					if(Client.loggedIn)
						JOptionPane.showMessageDialog(frame, "Please log out first before server change.");
	        		else{
	        			Client.isEasyOrionClient = true;
	        			Client.writeSettings();
	        			updateTitle();
	        		}
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
            if(cmd.equalsIgnoreCase("Forums")) {
               openUpWebSite("http://orion06rsps.icyboards.net/");
            }
            
            if(cmd.equalsIgnoreCase("World Map")) {
             	worldmap.Main.main(new String[]{""});
             }
			
			if(cmd.equalsIgnoreCase("Screenshot")) {
               takeScreenshot(true);
            }*/
         }
      } catch (Exception var4) {
         ;
      }

   }
   
   public void takeScreenshot(boolean flag)
    {
        BufferedImage bufferedimage;
        try
        {
            Robot robot = new Robot();
            Point point = e.getLocationOnScreen();
            Rectangle rectangle = new Rectangle(point.x, point.y, e.getWidth(), e.getHeight());
            bufferedimage = robot.createScreenCapture(rectangle);
        }
        catch(Throwable throwable)
        {
             JOptionPane.showMessageDialog(frame, "An error occured while trying to create a screenshot!", "Screenshot Error", 0);
            return;
        }
        String s = null;
        try
        {
            s = getNearestScreenshotFilename();
        }
        catch(IOException ioexception)
        {
            if(flag)
            {
                 JOptionPane.showMessageDialog(frame, "A screenshot directory does not exist, and could not be created!", "No Screenshot Directory", 0);
                return;
            }
        }
        if(s == null && flag)
        {
             JOptionPane.showMessageDialog(frame, "There are too many screenshots in the screenshot directory!\n"+"Delete some screen\n" +"shots and try again." , "Screenshot Directory Full", 0);
            return;
        }
        if(!flag)
        {
            final JFileChooser fileChooser = new JFileChooser();
            final JDialog fileDialog = createFileChooserDialog(fileChooser, "Save Screenshot", this);
            final BufferedImage si = bufferedimage;
            JFileChooser _tmp = fileChooser;
            fileChooser.setFileSelectionMode(0);
            fileChooser.addChoosableFileFilter(new imageFileFilter());
            fileChooser.setCurrentDirectory(new File("./screenshots/"));
            fileChooser.setSelectedFile(new File(s));
            JFileChooser _tmp1 = fileChooser;
            fileChooser.setDialogType(1);
            fileChooser.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent actionevent)
                {
                    String s1 = actionevent.getActionCommand();
                    if(s1.equals("ApproveSelection"))
                    {
                        File file = fileChooser.getSelectedFile();
                        if(file != null && file.isFile())
                        {
                            int i = JOptionPane.showConfirmDialog(frame, (new StringBuilder()).append(file.getAbsolutePath()).append(" already exists.\n"+"Do you want to replace it?").toString(), "Save Screenshot", 2);
                            if(i != 0)
                            {
                                return;
                            }
                        }
                        try
                        {
                            ImageIO.write(si, "png", file);
                        }
                        catch(IOException ioexception2)
                        {
                             JOptionPane.showMessageDialog(frame, "An error occured while trying to save the screenshot!\n"+"Please make sure you have\n"+" write access to the screenshot directory." , "Screenshot Error", 0);
                        }
                        fileDialog.dispose();
                    } else
                    if(s1.equals("CancelSelection"))
                    {
                        fileDialog.dispose();
                    }
                }


            {

            }
            });
            fileDialog.setVisible(true);
        } else
        {
            try
            {
                ImageIO.write(bufferedimage, "png", new File((new StringBuilder()).append("./screenshots/").append(s).toString()));
				System.out.println("You took a nice screenshot.");
            }
            catch(IOException ioexception1)
            {
                 JOptionPane.showMessageDialog(frame, "An error occured while trying to save the screenshot!\n"+"Please make sure you have\n"+" write access to the screenshot directory." , "Screenshot Error", 0);
            }
        }
    }
	
	public static String getNearestScreenshotFilename()
        throws IOException
    {
        File file = new File("./screenshots");
        int i = 0;
        do
        {
            String s = "Pic .png";
            if(i < 10)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" 000").append(i).toString());
            } else
            if(i < 100)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" 00").append(i).toString());
            } else
            if(i < 1000)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" 0").append(i).toString());
            } else
            if(i < 10000)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" ").append(i).toString());
            }
            if((new File(file, s)).isFile())
            {
                i++;
            } else
            {
                return s;
            }
        } while(i < 10000);
        return null;
    }
	
	public JDialog createFileChooserDialog(JFileChooser jfilechooser, String s, Container container)
	    {
	        JDialog jdialog = new JDialog(frame, s, true);
	        jdialog.setDefaultCloseOperation(2);
	        jdialog.add(jfilechooser);
	        jdialog.pack();
	        jdialog.setLocationRelativeTo(container);
	        return jdialog;
    }
	
	class imageFileFilter extends FileFilter
	{

		imageFileFilter()
		{
		}

		public boolean accept(File file)
		{
			String s = file.getName();
			return file.isDirectory() || s.toLowerCase().endsWith(".png") && s.indexOf("$") == -1;
		}

		public String getDescription()
		{
			return "PNG (*.png)";
		}
	}
   
}
