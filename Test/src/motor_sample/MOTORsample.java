package motor_sample;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MOTORsample {

	// 使用するモーターの定義
	static RegulatedMotor testMotor = Motor.A;

	public static void main(String[] args) {

		// モーター角度初期化
		testMotor.resetTachoCount();
		
		// モーター位置初期化
		testMotor.rotateTo(0);
		for (int k = 0; k < 5; k++) {
			// モータースピードを段階的にセット
			testMotor.setSpeed(200 * k);
			
			// モーター前進
			testMotor.forward();
			Delay.msDelay(2000);
		}
		for (int k = 0; k < 1; k++) {
			// モータースピードを段階的にセット
			testMotor.setSpeed(200 * k * -1);
			
			// モーター後進
			testMotor.backward();
			Delay.msDelay(2000);
		}
	}

}
