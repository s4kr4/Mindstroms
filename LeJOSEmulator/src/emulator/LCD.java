package emulator;
/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * a stub that emulates the actions of the LCD class in lejos
 */
public class LCD{

  /**
   * clears the LCD
   */
  public static void clear(){
    System.out.println();
    if (Start.graphical) Start.setLCD("");
  }

  /**
   * shows a numerical value on the LCD screen
   * @param aValue the value to display
   */
  public static void showNumber(int aValue){
    String msg = "LCD: " + aValue; 
    System.out.println(msg);
    if (Start.graphical) Start.setLCD(String.valueOf(aValue));
    if (Start.graphical) Start.addEvent(msg);
  }
  
}
