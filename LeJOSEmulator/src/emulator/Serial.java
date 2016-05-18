package emulator;

/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * allows the use of serial command on the RCX
 */
public class Serial{

  /**
   * adds a serial listener to the specified class
   * @param aListener the class that wishes to listen
   */
  public static void addSerialListener(SerialListener aListener){}

  /**
   * sends the specified packet
   * @return true iff the send was successful
   */
  public static boolean sendPacket(byte[] aBuffer, int aOffset, int aLen){
    return true;
  }

}
