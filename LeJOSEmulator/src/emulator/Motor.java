package emulator;
/**
 * @author <a href="mailto:tauxi@users.sourceforge.net">Jeffery</a>
 * @version 0.2A nov-2006
 * emulates the behavior of the lejos Motor class
 */
public class Motor{
  /**
   * Motor A on the RCX
   */
  public static Motor A = new Motor('A');
  /**
   * Motor B on the RCX
   */
  public static Motor B = new Motor('B');
  /**
   * Motor C on the RCX
   */
  public static Motor C = new Motor('C');

  /**
   * the character representing the motor
   */
  private char ID;
  /**
   * the states that the motor is able to have
   */
  public enum STATE {
    /**
     * the state that the motor is in when the motor is going backwards
     */
    BACKWARD, 
    /**
     * the state that the motor is in when the motor is coasting to a halt
     */
    FLOATING, 
    /**
     * the state that the motor is in when it has been halted
     */
    STOPPED, 
    /**
     * the state the motor is in when the motor is moving forwards
     */
    FORWARD};
  /**
   * the current state of the motor
   */
  private STATE state = STATE.STOPPED;
  /**
   * the current power level of the motor
   */
  private int power = 0;

  /**
   * creates a new motor with the specified id
   * @param id the character to represent the motor with
   */
  private Motor(char id){
    ID=id;
  }

  /**
   * makes the motor turn backwards
   */
  public void backward(){
    state = STATE.BACKWARD;
    String msg = ID + " backwards";
    System.out.println(msg);
    if (Start.graphical) Start.setMotorDirection(ID, Start.MotorDirection.BACKWARDS);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * makes the motor move forwards
   */
  public void forward(){
    state = STATE.FORWARD;
    String msg = ID + " forward";
    System.out.println(msg);
    if (Start.graphical) Start.setMotorDirection(ID, Start.MotorDirection.FORWARDS);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * stops the motor
   */
  public void stop(){
    state = STATE.STOPPED;
    String msg = ID + " stop";
    System.out.println(msg);
    if (Start.graphical) Start.setMotorDirection(ID, Start.MotorDirection.STOPPED);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * change sthe power level of the motor
   * @param aPower the power the motor should have 0-7
   */
  public void setPower(int aPower){
    power = aPower;
    String msg = "power " + ID + ": " + aPower;
    System.out.println(msg);
    if(Start.graphical) Start.addEvent(msg);
  }

  /**
   * allows the motors to coast to a halt
   */
  public void flt(){
    state = STATE.FLOATING;
    setPower(0);
    String msg = ID + "floating";
    System.out.println(msg);
    if (Start.graphical) Start.setMotorDirection(ID, Start.MotorDirection.FLOATING);
    if (Start.graphical) Start.addEvent(msg);
  }

  /**
   * determines if the motor is going backwards
   * @return true iff the motor is going backwards
   */
  public boolean isBackward(){
    return state == STATE.BACKWARD;
  }

  /**
   * determines if the motor is floating
   * @return true iff the motor is floating
   */
  public boolean isFloating(){
    return state == STATE.FLOATING;
  }

  /**
   * determine if the motor is moving forwards
   * @return true iff the motor is moving forwards
   */
  public boolean isForward(){
    return state == STATE.FORWARD;
  }

  /**determines if the motor is currently stopped
   * @return true iff the motor is currently stopped
   */
  public boolean isStopped(){
    return state == STATE.STOPPED;
  }

  /**
   * determines if the motor is currently moving
   * @return true iff the motor is going backwards or forwards
   */
  public boolean isMoving(){
    return isForward() || isBackward();
  }

  /**
   * reverses the direction of the motor if the motor is moving
   */
  public void reverseDirection(){
    if (state == STATE.FORWARD){
      backward();
    }else if (state == STATE.BACKWARD){
      forward();
    }
  }

  /**
   * gets the id of the motor
   * @return the identification of the motor
   */
  public char getID(){
    return ID;
  }

  /**
   * gets the current power of the motor
   * @return the current power level of the motor
   */
  public int getPower(){
    return power;
  }

}
