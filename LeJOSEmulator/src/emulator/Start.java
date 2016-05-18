package emulator;
import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * the controlling class for the emulation of the lejos libraries.
 * It allows for the use of either a command line interface or a 
 * graphical user interface.<br/>
 * the emulator can be run by:<br/>
 * java {pathToStart}/Start [ -g | -c] {classname}
 * <br/><br/>
 * eg. java emulator/Start -g mittens.Mittens
 */
public class Start extends Frame implements Runnable{

  
  /**
   * the number of samples each sensor will use the input for
   * when in the command line version
   */
  public static final int[] samples = {100,100,100};
  /**
   * the number of times the value has been used for each sensor
   */
  public static int[] num = {100,100,100};
  /**
   * the current values of each of the sensors
   */
  public static int[] current = {0,0,0};
  /**
   * a list of all classes listening to the sensors
   */
  public static SensorListener[][] list = new SensorListener[3][8];
  /**
   * shows weather the graphical of command line has been invoked
   */
  public static boolean graphical;

  //graphical interface components
  //private static TextField in;
  /**
   * input field for Sensor 1
   */
  private static TextField in1;
  /**
   * input fild for sensor 2
   */
  private static TextField in2;
  /**
   * input field for sensor 3
   */
  private static TextField in3;
  /**
   * the panel that groups the sensor input fields
   */
  private static Panel sensor_in;
  /**
   * an input label that shows what each of the inputs is for
   */
  private static Label in_desc;
  /**
   * label for displaying the motor A's state
   */
  private static Label motas;
  /**
   * label for displaying motor B's state
   */
  private static Label motbs;
  /**
   * label for showing motor C's state
   */
  private static Label motcs;
  /**
   * descriptive label for Motor A
   */
  private static Label mota;
  /**
   * descriptive label for motor B
   */
  private static Label motb;
  /**
   * descriptive label for motor C
   */
  private static Label motc;
  /**
   * label for showing the value for sensor 1
   */
  private static Label sen1s;
  /**
   * label for showing the value for sensor 2
   */
  private static Label sen2s;
  /**
   * label for showing the value for sensor 3
   */
  private static Label sen3s;
  /**
   * descriptive label for sensor 1
   */
  private static Label sen1;
  /**
   * descriptive label for sensor 2
   */
  private static Label sen2;
  /**
   * descriptive labek for sensor 3
   */
  private static Label sen3;
  /**
   * descriptive label for the sound output
   */
  private static Label sound_desc;
  /**
   * the sound that is outputted
   */
  private static Label sound;
  /**
   * descriptive label for teh LCD
   */
  private static Label lcd_desc;
  /**
   * the contents of the LCD display
   */
  private static Label lcd;
  /**
   * the command line output of the program
   */
  private static TextArea output;
  /**
   * the layout used for many of the panels
   */
  private static GridBagLayout g;
  /**
   * panels used to group the LCD items together
   */
  private static Panel lcd_c;
  /**
   * panel to group the motor labels to gether
   */
  private static Panel motor_l;
  /**
   * panel to group the sensor labels together
   */
  private static Panel sensor_l;
  /**
   * panel that groups the motor status together
   */
  private static Panel motor_c;
  /**
   * the panel that groups the senor values together
   */
  private static Panel sensor_c;
  /**
   * the panel thaht groups the sound labels together
   */
  private static Panel sound_c;
  /**
   * the panel that groups the buttons for the sensor together
   */
  private static Panel sensor_b;
  /**
   * shows weather a value has been entered or not
   */
  private static boolean entered = false;
  /**
   * button to change the state of sensor 1
   */
  private static Button sensor1;
  /**
   * button to change the state of sensor 2
   */
  private static Button sensor2;
  /**
   * button to change the state of sensor 3
   */
  private static Button sensor3;

  /**
   * creates a new instance of the interrface
   * @param w the width of the interface
   * @param h the height of the interface
   * @param title the title of the interface
   */
  public Start(int w, int h, String title){
    super(title);
    setSize(w,h);
    //initilises the graphical components
    in1 = new TextField(8);
    in2 = new TextField(8);
    in3 = new TextField(8);
    sensor_in = new Panel();
    in_desc= new Label("    Sensor 1     Sensor 2     Sensor 3 ");
    motas = new Label("  STOPPED  ",Label.CENTER);
    motbs = new Label("  STOPPED  ",Label.CENTER);
    motcs = new Label("  STOPPED  ",Label.CENTER);
    mota =  new Label("   Motor A   ");
    motb =  new Label("   Motor B   ");
    motc =  new Label("   Motor C   ");
    sen1s = new Label("  STOPPED  ",Label.CENTER);
    sen2s = new Label("  STOPPED  ",Label.CENTER);
    sen3s = new Label("  STOPPED  ",Label.CENTER);
    sen1 =  new Label("  Sensor 1   ");
    sen2 =  new Label("  Sensor 2   ");
    sen3 =  new Label("  Sensor 3   ");
    sound_desc = new Label("Sound: ");
    sound = new Label("                              ");
    lcd_desc = new Label("LCD:   ");
    lcd = new Label("                    ");
    output = new TextArea("", 10, 27,TextArea.SCROLLBARS_VERTICAL_ONLY);
    g = new GridBagLayout();
    lcd_c = new Panel(g);
    motor_l = new Panel(g);
    sensor_l = new Panel(g);
    motor_c = new Panel(new GridLayout(1,3));
    sensor_c = new Panel(new GridLayout(1,3));
    sound_c = new Panel(g);
    sensor1=new Button("Sensor  1");
    sensor2=new Button("Sensor  2");
    sensor3=new Button("Sensor  3");
    sensor_b = new Panel();
    setVisible(true);
    //in.setEditable(false);
    output.setEditable(false);
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx=0;
    c.gridwidth=1;
    c.anchor = GridBagConstraints.WEST;
    
    //positions the sensor elements
    c.gridheight=1;
    c.gridy=0;
    add(motor_l,c);
    c.gridy+=1;
    add(motor_c,c);
    c.gridy+=1;
    add(motorR_l,c);
    c.gridy+=1;
    add(motorR_c,c);
    c.gridy+=1;
    add(sensor_l,c);
    c.gridy+=1;
    add(sensor_c,c);
    c.gridy+=1;
    add(sensorR_l,c);
    c.gridy+=1;
    add(sensorR_c,c);
    c.gridy+=1;
    add(lcd_c,c);
    c.gridy+=1;
    add(sound_c,c);
    c.gridy+=1;
    c.gridheight=10;
    add(output,c);
    c.gridy+=10;
    c.gridheight=1;
    add(in_desc,c);
    c.gridy+=1;
    add(sensor_in,c);
    c.gridy+=1;
    add(sensor_b,c);
    c.gridy+=1;
    add(inR_desc,c);
    c.gridy+=1;
    add(sensorR_in,c);
    c.gridy+=1;
    add(sensorR_b,c);

    //positions the motor elements
    c.gridy=0;
    c.gridx=0;
    motor_l.add(mota,c);
    c.gridx=1;
    motor_l.add(motb,c);
    c.gridx=2;
    motor_l.add(motc,c);

    motor_c.add(motas,c);
    motor_c.add(motbs,c);
    motor_c.add(motcs,c);

    //positions the sensor elements
    c.gridy=0;
    c.gridx=0;
    sensor_l.add(sen1,c);
    c.gridx=1;
    sensor_l.add(sen2,c);
    c.gridx=2;
    sensor_l.add(sen3,c);

    sensor_c.add(sen1s);
    sensor_c.add(sen2s);
    sensor_c.add(sen3s);
    
    //positions the lcd elements
    c.gridx=0;
    lcd_c.add(lcd_desc);
    c.gridx=1;
    c.gridwidth=4;
    lcd_c.add(lcd);

    //positions the sensor input elements
    sensor_in.add(in1);
    sensor_in.add(in2);
    sensor_in.add(in3);

    //positions the sound elements
    c.gridx=0;
    c.gridwidth=1;
    sound_c.add(sound_desc);
    c.gridx=0;
    c.gridwidth=4;
    sound_c.add(sound);
    
    //positions the statechanging buttons for the sensors
    sensor_b.add(sensor1);
    sensor_b.add(sensor2);
    sensor_b.add(sensor3);

    //adds listeners to the input fields and the buttons
    sensor1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        callListeners(0);
      }
    });

    sensor2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        callListeners(1);
      }
    });
    
    sensor3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        callListeners(2);
      }
    });

    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        quit();
      }
    });

    in1.addKeyListener(new KeyListener(){
      public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
          setEntered(0);
        }
      }
      public void keyReleased(KeyEvent e){}
      public void keyTyped(KeyEvent e){}
    });
    
    in2.addKeyListener(new KeyListener(){
      public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
          setEntered(1);
        }
      }
      public void keyReleased(KeyEvent e){}
      public void keyTyped(KeyEvent e){}
    });

    in3.addKeyListener(new KeyListener(){
      public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
          setEntered(2);
        }
      }
      public void keyReleased(KeyEvent e){}
      public void keyTyped(KeyEvent e){}
    });

  private void callListeners(int ID){
    int i=0;
    if (ID < 3){
      while(list[ID][i]!=null && i < list.length){
        System.out.println("Calling listener: " + i);
        list[ID][i++].stateChanged(Sensor.SENSORS[ID],0,0);
      }
    }
  }


  /**
   * reads in the value entered in for a sensor
   * @param ID an identification number for the sensor
   */
  private void setEntered(int ID){
    /*if (!in.getText().equals("")){
      entered=true;
      in.setEditable(false);
    }*/
    String temp="";
    switch(ID){
      case 0:
        if (in1.getText().equals("")) return;
        temp = in1.getText();
        in1.setText("");
        break;
      case 1:
        if (in2.getText().equals("")) return;
        temp = in2.getText();
        in2.setText("");
        break;
      case 2:
        if (in3.getText().equals("")) return;
        temp = in3.getText();
        in3.setText("");
        break;
    }
    try{
      if(ID<3){
        current[ID]=(Integer.valueOf(temp)).intValue();
      }
    }catch(Exception e){}
  }

  /**
   * quits the emulator
   */
  private void quit(){
    System.exit(1);
  }
  
  /**
   * the possible motor states
   */
  public enum MotorDirection{
    /**
     * the state of the motor when it is moving forward
     */
    FORWARDS, 
    /**
     * the state of the motor when it has been stopped
     */
    STOPPED,
    /**
     * the state of the motor when it is going backwards
     */
    BACKWARDS,
    /**
     * the state of the motor when it is coasting to a stop
     */
    FLOATING};
  /**
   * uptdaes the motor status
   * @param ID the identification of the motor
   * @param m the direction that the motor is going in
   */
  public static void setMotorDirection(char ID, MotorDirection m){
    switch(ID){
      case 'A':
        motas.setText(m.toString());
        break;
      case 'B':
        motbs.setText(m.toString());
        break;
      case 'C':
        motcs.setText(m.toString());
        break;
    }
  }

  /**
   * sets the sound that is being played
   * @param msg the msg to be displayed
   */
  public static void setSound(String msg){
    sound.setText(msg);
  }

  /**
   * sets the value of the lcd screen
   * @param str the string to be displayed
   */
  public static void setLCD(String str){
    lcd.setText(str);
  }

  /**
   * adds an event into the output box
   * @param str the message to be added
   */
  public static void addEvent(String str){
    output.append(str + "\n");
  }

  /**
   * sets the value for the sensor
   * @param ID the identification of the sensor
   * @param value the value of the sensor
   */
  public static void setSensor(int ID, String value){
    switch(ID){
      case 1:
        sen1s.setText(value);
        break;
      case 2:
        sen2s.setText(value);
        break;
      case 3:
        sen3s.setText(value);
        break;
    }
  }

  public void run(){
    
  }

  /**
   * starts the emulator
   * @param args the arguments from the command line
   */
  public static void main(String[] args){
    System.err.println("You can call the stateChanged method of any objected listening to a");
    System.err.println("sensor by typing a 's' followed by the number of the sensor (1,2,3)");
    System.err.println();
    if(args.length!=2){
      System.err.println("Usage: java Start [-g | -c] <class>");
      System.exit(0);
    }
    if(args[0].equals("-g")){
      //create interface
      Start window = new Start(800,800,"Lejos - " + args[1]);
      window.pack();
      window.setVisible(true);
      new Thread(window); 
      graphical=true;
      try{
        Thread.sleep(500);
      }catch (Exception e){}
      sen1s.setText("0");
      sen2s.setText("0");
      sen3s.setText("0");
    }else if(args[0].equals("-c")){
      //use command line only
      graphical=false;
    }else{
      System.err.println("Usage: java Start [-g | -c] <class>");
      System.exit(0);
    }
    Class[] arg = new Class[1];
    arg[0] = String[].class;
    try{
      Method mainMethod = Class.forName(args[1]).getDeclaredMethod("main", arg);
      Object[] argList = new Object[1];
      argList[0] = new String[0];
      mainMethod.invoke(null,argList);
    }catch(ClassNotFoundException e){
      System.err.println("Class " + args[1] + " was not found");
    }catch(NoSuchMethodException e){
      System.err.println("Class " + args[1] + " does not have main method");
    }catch(InvocationTargetException e){
      System.err.println("Exception while executing " + args[1] + ": " + e.getTargetException());
    }catch(IllegalAccessException e){
      System.err.println("main method in " + args[1] + " is not public");
    }
  }

}
