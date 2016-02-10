import javax.swing.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;

/*
* Timothy Endersby
* ISTE 121
* Homework 2 - "GUI from Hell"
*/

public class GUIFromHell extends JFrame implements ActionListener, MouseListener{
   
   //Personal imformation
   private JLabel jlName;
   private JLabel jlAge;
   private JTextField jtfName;
   private JTextField jtfAge;
   //Array of names for each letter of the alphabet
   private String[] names;
   //Solor sliders and button and something else
   private JLabel jlRed;
   private JLabel jlGreen;
   private JLabel jlBlue;
   private JSlider jsRed;
   private JSlider jsGreen;
   private JSlider jsBlue;
   private JButton jbSetColor;
   //Slider values
   private int red;
   private int blue;
   private int green;
   //Rating buttins
   private JLabel jlRate;
   private JRadioButton jrbOne;
   private JRadioButton jrbTwo;
   private JRadioButton jrbThree;
   private JRadioButton jrbFour;
   private JRadioButton jrbFive;
   private JRadioButton jrbHidden;
   //File menu options
   private JMenuItem jmiExit;
   private JMenuItem jmiClear;
   private JMenuItem jmiAbout;
   //holds last value for age - makes sure age dosn't repeat add if user presses enter
   private String lastAge;
   //Exit counter - counts number of times exit has been pressed (reset to 0 with clear)
   private int exitCount;
   //Removed "FINE DON'T PUSH IT" after button was pushed
   private boolean buttonPushed;

   /*
   * Starts the GUI after asking several anoying questions
   */
   public static void main(String [] args){
      //ask lots of annoying questions
      if(true){//Set to false when working on the code to speed things up
         try{//Try catch needed for sleep method
            JOptionPane.showMessageDialog(null, "Welcome to GUI from Hell");
            JOptionPane.showMessageDialog(null, "Made by Timothy Endersby");
            JOptionPane.showMessageDialog(null, "Are you ready to begin?");
            Thread.sleep(2500);//wait 2.5 seconds - prevents user from just hitting enter over and over
            JOptionPane.showMessageDialog(null, "Are you sure?");
            Thread.sleep(1000);
            JOptionPane.showMessageDialog(null, "You may start...");
            Thread.sleep(1000);
            JOptionPane.showMessageDialog(null, "right...");
            Thread.sleep(1000);
            JOptionPane.showMessageDialog(null, "now...");
         }catch(InterruptedException e){}
      }
      new GUIFromHell();
   }
   
   /*
   * Creates the 4 sections of the GUI
   * Menu Bar
   * Personal Info
   * Color Sliders
   * Rating
   */
   public GUIFromHell(){
      //Set defaults
      addMouseListener(this);
      lastAge = "";
      red = 128;
      green = 128;
      blue = 128;
      getContentPane().setBackground(Color.YELLOW);
      exitCount = 0;
      buttonPushed = false;
      //List of names and gender of those names are random
      names = new String[]{"Anthony", "Brian", "Cara", "David", "Emma", "Freddy", "Grace", "Hannah", "Isaac", "Jessica", "Kyle",
                           "Lauren", "Matthew ", "Natalie", "Oliver", "Paige", "Quinn", "Ryan", "Samuel", "Thomas", "Urias", "Victoria",
                            "Willian", "Xavier", "Yazmin", "Zachary"}; 
                            
      //Make top section with menu bar and personal info
      JPanel jpTop = new JPanel(new BorderLayout());
         jpTop.setOpaque(false);//So background color can come through
         //Make Menu Bar
         JMenuBar menuBar = new JMenuBar();
            JMenu jmFile = new JMenu("File");
               
               jmiClear = new JMenuItem("Clear");
               jmFile.add(jmiClear);
               
               jmiExit = new JMenuItem("Exit");
               jmFile.add(jmiExit);
               
            menuBar.add(jmFile);
            
            JMenu jmHelp = new JMenu("Help");
            
               jmiAbout = new JMenuItem("About");
               jmHelp.add(jmiAbout);
            
            menuBar.add(jmHelp);
            
            jmiClear.addActionListener(this);
            jmiExit.addActionListener(this);
            jmiAbout.addActionListener(this);
         
         jpTop.add(menuBar, BorderLayout.NORTH);
                                
         //Make personal information section  
         JPanel jpInfo = new JPanel(new GridLayout(0, 2));
            jpInfo.setOpaque(false);
            
            jlName = new JLabel("Name:");
            jlName.setHorizontalAlignment(JLabel.RIGHT);
            jpInfo.add(jlName);
            jtfName = new JTextField(10);
            jpInfo.add(jtfName);
            
            jlAge = new JLabel("Age:");
            jlAge.setHorizontalAlignment(JLabel.RIGHT);
            jpInfo.add(jlAge);
            jtfAge = new JTextField(10);
            jpInfo.add(jtfAge);
            
         jpTop.add(jpInfo);
         
      add(jpTop, BorderLayout.NORTH);
      
      //add Key Listener to name text field
      KeyListener klName = new KeyListener() {
         public void keyPressed(KeyEvent keyEvent) {}
         public void keyTyped(KeyEvent keyEvent) {   }
         /*
         * Replaces name with name from array matching first letter
         * if not a letter, it will be cleared
         * turns off listener once name has been set
         */
         public void keyReleased(KeyEvent keyEvent) {
            try{
               String name = names[(int)Character.toLowerCase(keyEvent.getKeyChar()) - 97];
               jtfName.setText(name);
               jtfName.enable(false);
            }catch(ArrayIndexOutOfBoundsException e){
               jtfName.setText("");
               System.out.println("Text Error - tried to acess: " + ((int)keyEvent.getKeyChar() - 97) + " / " + keyEvent.getKeyChar());
            }  
         }  
      };
      jtfName.addKeyListener(klName);
       
      //add Age key listener
      KeyListener klAge = new KeyListener(){
         public void keyPressed(KeyEvent keyEvent) {}
         public void keyTyped(KeyEvent keyEvent) {}
         /*
         * Pop up window, tells user what number they typed
         * Adds one to the number after user closes the window
         */
         public void keyReleased(KeyEvent keyEvent) {
            if(!jtfAge.getText().equals(lastAge)){//to ensure no duplicates due to user pressing enter or spacebar
               try{
                  int age = Integer.parseInt(jtfAge.getText());
                  JOptionPane.showMessageDialog(null, "You typed a " + keyEvent.getKeyChar());
                  jtfAge.setText((age+1) + "");
                  lastAge = jtfAge.getText();
               }catch(NumberFormatException e){
                  jtfAge.setText("");
               }
            }
         } 
      };
      jtfAge.addKeyListener(klAge);
      
      //Make section two - Color Sliders
      JPanel jpSliders = new JPanel(new GridLayout(7,0));
         //jpSliders.addMouseListener(this);
         jpSliders.setOpaque(false);
         
         //Make Label
         jlRed = new JLabel("Red");
         jlRed.setHorizontalAlignment(JLabel.CENTER);
         jpSliders.add(jlRed);
         
         //Make red slider changes
         jsRed = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
         jsRed.addMouseListener(this);
         jsRed.setMinorTickSpacing(16);
         jsRed.setMajorTickSpacing(32);
         jsRed.setPaintTicks(true);
         jsRed.setPaintLabels(true);
         jsRed.setOpaque(false);
         jpSliders.add(jsRed);
         
         //Ad change listener to change color when slider is moved
         jsRed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
               red = ((JSlider) ce.getSource()).getValue();
               getContentPane().setBackground(new Color(red, 0, 0));
            }
         });
         
         //Green slider
         jlGreen = new JLabel("Green");
         jlGreen.setHorizontalAlignment(JLabel.CENTER);
         jpSliders.add(jlGreen);
         jsGreen = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
         jsGreen.addMouseListener(this);
         jsGreen.setMinorTickSpacing(16);
         jsGreen.setMajorTickSpacing(32);
         jsGreen.setPaintTicks(true);
         jsGreen.setPaintLabels(true);
         jsGreen.setOpaque(false);
         jpSliders.add(jsGreen);
         
         jsGreen.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
               green = ((JSlider) ce.getSource()).getValue();
               getContentPane().setBackground(new Color(0, green, 0));
            }
         });
         
         //Blue slider
         jlBlue = new JLabel("Blue");
         jlBlue.setHorizontalAlignment(JLabel.CENTER);
         jpSliders.add(jlBlue);
         jsBlue = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
         jsBlue.addMouseListener(this);
         jsBlue.setMinorTickSpacing(16);
         jsBlue.setMajorTickSpacing(32);
         jsBlue.setPaintTicks(true);
         jsBlue.setPaintLabels(true);
         jsBlue.setOpaque(false);
         jpSliders.add(jsBlue);
         
         jsBlue.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
               blue = ((JSlider) ce.getSource()).getValue();
               getContentPane().setBackground(new Color(0, 0, blue));
            }
         });
         
         //"Set color" button
         jbSetColor = new JButton("Set Color");
         jbSetColor.setToolTipText("This button is pretty self explanatory");
         jpSliders.add(jbSetColor);
         jbSetColor.addActionListener(this);
         
         jbSetColor.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {
               jbSetColor.setBackground(new Color(red, blue, green));
               if(!buttonPushed){  
                  JOptionPane.showMessageDialog(null, "FINE, DON'T PUSH IT");
               }else{
                  buttonPushed = false;
               }
            }
            public void mouseEntered(MouseEvent e) {
               jbSetColor.setBackground(new Color(red, blue, green).brighter());
               JOptionPane.showMessageDialog(null, "You going to press the button or not?");
               
            }    
         });
               
         
      add(jpSliders, BorderLayout.CENTER);
      
      //Make app 5 star rating section
      JPanel jpRating = new JPanel(new GridLayout(2, 0));
         jlRate = new JLabel("Please Rate this app from 1 to 5 stars");
      jpRating.add(jlRate);
      jpRating.setOpaque(false);
         jlRate.setOpaque(false);
         
         JPanel jpStars = new JPanel(new GridLayout(0, 5));
            jpStars.setOpaque(false);
            //Make the 6 (one invisible) radio buttons
		      jrbOne = new JRadioButton("1");
               jrbOne.setOpaque(false);
		      jrbTwo = new JRadioButton("2");
               jrbTwo.setOpaque(false);
		      jrbThree = new JRadioButton("3");
               jrbThree.setOpaque(false);
            jrbFour = new JRadioButton("4");
               jrbFour.setOpaque(false);
            jrbFive = new JRadioButton("5");
               jrbFive.setOpaque(false);
            jrbHidden = new JRadioButton("hidden");//this one is not visible, selected to deselect all others
		      
            //Add 6 Radio buttons to the group
		      ButtonGroup bgStars = new ButtonGroup();
		      bgStars.add(jrbOne);
            bgStars.add(jrbTwo);	
            bgStars.add(jrbThree);	
            bgStars.add(jrbFour);	
            bgStars.add(jrbFive);
            bgStars.add(jrbHidden);			
		      
            //Add 5 to the panel
		      jpStars.add(jrbOne);
            jpStars.add(jrbTwo);
            jpStars.add(jrbThree);
            jpStars.add(jrbFour);
            jpStars.add(jrbFive);
            
            //Add action listeners to the 5 buttons
            ActionListener ratingListener = new ActionListener() {
			   public void	actionPerformed(ActionEvent ae){
            
               Object choice = ae.getSource();
               
			      if(choice == jrbOne){
                  JOptionPane.showMessageDialog(null, "ONLY ONE STAR?!?!?!?!?! WHO ARE YOU?");
                  jrbHidden.setSelected(true);
               }
               else if(choice == jrbTwo){
                  JOptionPane.showMessageDialog(null, "I put a lot of time into this, and you only gave me two stars?\nYou're a terrible human.");
                  jrbHidden.setSelected(true);
               }
               else if(choice == jrbThree){
                  JOptionPane.showMessageDialog(null, "Really? Three Star? This must be a mistake.");
                  jrbHidden.setSelected(true);
               }
               else if(choice == jrbFour){
                  JOptionPane.showMessageDialog(null, "Seriously? Why only 4?");
                  jrbHidden.setSelected(true);
               }
               else if(choice == jrbFive){
                  JOptionPane.showMessageDialog(null, "Thank you\nSince you're so nice, we're going to let you select that again.");
                  jrbHidden.setSelected(true);
               }
				}
			};	
         
         jrbOne.addActionListener(ratingListener);
         jrbTwo.addActionListener(ratingListener);
         jrbThree.addActionListener(ratingListener);
         jrbFour.addActionListener(ratingListener);
         jrbFive.addActionListener(ratingListener);
            
         jpRating.add(jpStars);
      add(jpRating, BorderLayout.SOUTH);
       
      pack();
      setLocationRelativeTo(null);
      setTitle("Tim Endersby - GUI from Hell");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   /*
   * Controls responses for rating buttons, set color, and menu options
   */
   public void actionPerformed(ActionEvent ae){
   
      Object choice = ae.getSource();
      
      //Set color button
      if(choice == jbSetColor){
         randomTextColors();
         getContentPane().setBackground(new Color(red, green, blue));
         JOptionPane.showMessageDialog(null, "Color set to " + red + ", " + blue + ", " + green);
         buttonPushed = true;
         try{
            Thread.sleep(1500);
         }catch(InterruptedException e){}
         getContentPane().setBackground(Color.YELLOW);
         JOptionPane.showMessageDialog(null, "Nevermind, that color was UGLY!" 
                                         + "\n     I like yellow better"); 
      //Menu items
      }else if(choice == jmiClear){
         clear();
         
      }else if(choice == jmiAbout){
         JOptionPane.showMessageDialog(null, "GUI from hell"
                                         + "\nMade by Timothy Endersby"
                                       + "\n\nWhen you first open the game"
	                                      + "\n     A series of JOptionPanes will pop up, welcoming you to the game"                                   
	                                      + "\n     Between some of them there is 1 or 2 second delay, stopping the user from just pushing enter over and over"

                                      + "\n\nControls for GUI from hell"
	                                      + "\n     Menu"
		                                   + "\n          File"
			                                + "\n               Clear - Fills personal info with random name and age (0-100), sets background to random color and sliders to match that color, sets a 5 star rating, sets text to random colors"
			                                + "\n               Exit - First time you click, JOptionPane pops up, program does not exit, Second time you click, JOptionPane pops up, program closes (counter for this resets with clear)"
		                                   + "\n          About"
			                                + "\n               Help - Shows help window with information about the program (This one)"

	                                    + "\n\n     Personal info"
		                                   + "\n          Name - When user enters a letter, it fills the field with a name that stars with that letter, and locks it (if something other than a letter is entered, it is deleted)"
		                                   + "\n          Age - When user enters a number, a JOptionPane pops up, telling the user what number it enterned, and then adds one to the age ((if something other than a number is entered, it is deleted)"

	                                    + "\n\n     Color"
		                                   + "\n          Sliders - When selected, background shows color of that slider only (with other colors set to zero) - when mouse released, color is set back to yellow"
		                                   + "\n          Set Color - Changes to color of sliders when moused over, JOptionPane pops up when moused over, when mouse leaves button, another JOptionPane apears"
			                                + "\n                When button is clicked, background is set to color of sliders, and JOptionPane pops up to show color values, after 1.5 seconds, color is set back to yellow as a JOptionPane pops up"
                                         + "\n                Also sets all text to random colors"
	                                      
                                       + "\n\n     Rating"
		                                 + "\n          1-4 stars - JOptionPane pops up, asks why they scored it so low, and then deselects their choice"
		                                 + "\n          5 stars - JOptionPane pops up, thanks them for picking 5, deselects 5 and tells them to do it again"); 
      }else if(choice == jmiExit){
         exitCount++;
         if(exitCount == 1){
            JOptionPane.showMessageDialog(null, "Why do you want to leave so soon?\nI'm sure if you spend more time you'll like it");
         }else if(exitCount == 2){
            JOptionPane.showMessageDialog(null, "FINE! LEAVE! WE DON'T NEED YOU");
            JOptionPane.showMessageDialog(null, "Closing...");
            System.exit(0);
         }else{
            System.out.println("exit count error");
            System.exit(0);
         }  
      }
      else{
         System.out.println("How did you get here with " + choice);
      }
   }
   
   /*
   * Executed when clear is pressed in menu
   * Sets name, age, color, and sliders to random values
   * Sets rating to 5 stars
   * Resets exit counter
   */
   private void clear(){
      Random ran = new Random();
      try{
         randomTextColors();
         //Random personal info
         jtfName.setText(names[ran.nextInt(26)]);
         jtfName.enable(false);
         jtfAge.setText(ran.nextInt(100) + "");
         //Random color, and slider to match that color
         red = ran.nextInt(256);
         green = ran.nextInt(256);
         blue = ran.nextInt(256);
         jsRed.setValue(red);
         jsGreen.setValue(green);
         jsBlue.setValue(blue);
         getContentPane().setBackground(new Color(red, green, blue));
         //Select 5 stars
         jrbFive.setSelected(true);
         //reser ecit counter
         exitCount = 0;
      }catch(ArrayIndexOutOfBoundsException e){
         System.out.println("Random number error");
      }
   }
   
   /*
   * Sets all the text in the GUI to random colors
   */
   public void randomTextColors(){
      Random ran = new Random();
      jlName.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jlAge.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      
      jrbOne.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jrbTwo.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jrbThree.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jrbFour.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jrbFive.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
         
      jlRed.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jlGreen.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jlBlue.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jsRed.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jsGreen.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
      jsBlue.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
         
      jbSetColor.setForeground(new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));      
   }
      
   /*
   * Sets background color to yellow after slider is released
   */
   public void mouseReleased(MouseEvent e) {
     getContentPane().setBackground(Color.YELLOW);
   }
   public void mousePressed(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mouseClicked(MouseEvent e) {}
}
