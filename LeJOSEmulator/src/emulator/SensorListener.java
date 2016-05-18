package emulator;

/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * provides an interface for class so that they can listen to sensors
 */
public interface SensorListener{

  /**
   * the action that will be performed when the value of the sensor changes
   * @param aSource the sensor whose value changed
   * @param aOldValue the old value of the sensor
   * @param aNewValue the new value of the sensor
   */
  public void stateChanged(Sensor aSource, int aOldValue, int aNewValue);

}
