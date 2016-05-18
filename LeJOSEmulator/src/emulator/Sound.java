package emulator;

/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * emulates the Sound class of the lejos libraries
 */
public class Sound{
  
  /**
   * plays the specified tone for a specified duration
   * @param aFrequency  the frequency of the note to be played
   * @param aDuration the lenght of time to play th enote for
   */
  public static void playTone(int aFrequency, int aDuration){
    String msg = "Played " + aFrequency + "Hz for " + aDuration + "ms";
    System.out.println(msg);
    if (Start.graphical) Start.setSound(aFrequency+ "Hz for " + aDuration + "ms");
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * plays a buzzing noise
   */
  public static void buzz(){
    String msg = "Played a low buzz...";
    System.out.println(msg);
    if (Start.graphical) Start.setSound("low buzz");
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * plays the specified system sound
   * @param aQueued wether to queue the sound
   * @param aCode the code of the sound to play
   */
  public static void systemSound(boolean aQueued, int aCode){
    if (aCode < 0 || aCode > 5){
      throw new IllegalArgumentException("Invalid aCode");
    }
    String msg = "Played System Sound " + aCode + " with value " + aQueued;
    System.out.println(msg);
    if (Start.graphical) Start.setSound("System Sound " + aCode);
    if (Start.graphical) Start.addEvent(msg);
  }
  
}
