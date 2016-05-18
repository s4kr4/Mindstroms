package emulator;
import java.io.*;

/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * a class that emulates the behavior of the lejos sensor class
 */
public class Sensor{

  /**
   * Sensor 1 on the RCX
   */
  public static Sensor S1 = new Sensor(1);
  /**
   * Sensor 2 on the RCX
   */
  public static Sensor S2 = new Sensor(2);  
  /**
   * Sesnors 3 on the RCX
   */
  public static Sensor S3 = new Sensor(3);
  /**
   * a list of all available sensors
   */
  public static Sensor[] SENSORS = {S1, S2, S3};
  /**
   * used to read input form the command line
   */
  private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
  /**
   * default value used for invalid inputs
   */
  private static final int DEFAULT = 0;

  /**
   * the identification number for the sensor
   */
  private int ID;

  /**
   * creates a new sensor
   * @param id the identification number of the sensor
   */
  private Sensor(int id){
    ID=id;
  }

  /**
   * activates the sensor
   */
  public void activate(){
    String msg = ID + " activated";
    System.out.println(msg);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * sets the type and mode of the sensor
   * @param aType the type of sensor
   * @param aMode the mode the sensor will run in
   */
  public void setTypeAndMode(int aType, int aMode){
    String msg = ID + " set to type: " + aType + " and mode: " + aMode;
    System.out.println(msg);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * adds a sensor liistener to the sepcified class
   * @param aListener the class that wants to listen in to the sensor
   */
  public void addSensorListener(SensorListener aListener){
    boolean done = false;
    for (int i=0;i<Start.list[ID-1].length;i++){
      if (Start.list[ID-1][i]==null){
        Start.list[ID-1][i] = aListener;
        done = true;
        break;
      }
    }
    if (!done) throw new IllegalArgumentException("Too many Listeners!");
    String msg = ID + " added Sensor Listener " + aListener.toString();
    System.out.println(msg);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * reads a boolean value from the sensor
   * @return true iff the sensor value is non-zero
   */
  public boolean readBooleanValue(){
    String temp="";
    try{
      if (Start.graphical){
        temp = String.valueOf(Start.current[ID-1]!=0);
      }else{
        System.err.print("Please enter in a boolean value for sensor " +  ID +" \'t\' for true: ");
        temp = r.readLine();
      }
    }catch (Exception e){}

    if (temp.length()==2 && temp.charAt(0)=='s' && Character.isDigit(temp.charAt(1))){
      temp=""+temp.charAt(1);
      int value = Integer.valueOf(temp)-1;
      stateChanged(value);
    }
    
    String msg = "Sensor " + ID + " value: " + (temp.equals("t") || temp.equals("true"));
    System.out.println(msg);
    if (Start.graphical) Start.addEvent(msg);
    if (Start.graphical) Start.setSensor(ID, String.valueOf(temp.equals("t") || temp.equals("true")));
    if(temp.equals("t") || temp.equals("true")) Start.current[ID-1] = 1;
    else Start.current[ID-1] = 0;
    return temp.equals("t")|| temp.equals("true");  
  }

  /**
   * calls the state changed for all classes listening to this sensor
   * @param value the sensor idetification number
   */
  private void stateChanged(int value){
    if (value<3 && value>=0){
      int i=0;
      while(Start.list[value][i]!=null && i < Start.list.length){
        Start.list[value][i++].stateChanged(SENSORS[value],0,0);
      }
    }
  }

  /**
   * reads a numerical value form the sensor
   * @return a numerical representation of the sensors value
   */
  public int readValue(){
    if(Start.num[ID-1] < Start.samples[ID-1]){
      Start.num[ID-1]++;
      return Start.current[ID-1];
    }else{
      String temp="";
      try{
        if (Start.graphical){
          temp = String.valueOf(Start.current[ID-1]);//getInput("Sensor " + ID + " value | s[1,2,3]", ID);
        }else{
          System.err.print("Please enter in a numerical value for sensor " + ID + ": ");
          temp = r.readLine();
        }
      }catch (Exception e){}
      if (temp.length()==2 && temp.charAt(0)=='s' && Character.isDigit(temp.charAt(1))){
        temp=""+temp.charAt(1);
        int value = Integer.valueOf(temp)-1;
        stateChanged(value);
      }else{
        Start.num[ID-1]=0;
        if (temp.equals("current")) return Start.current[ID-1];
        try{
          Start.current[ID-1] = Integer.valueOf(temp);
        }catch(Exception e){
          Start.current[ID-1] = DEFAULT;
        }
      }
      String msg = "Sensor " + ID + " value: " + Start.current[ID-1];
      System.out.println(msg);
      if (Start.graphical) Start.addEvent(msg);
      if (Start.graphical) Start.setSensor(ID,String.valueOf(Start.current[ID-1]));
      return Start.current[ID-1];
    }
  }

}
