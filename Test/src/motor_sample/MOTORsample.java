package motor_sample;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MOTORsample {

	// �g�p���郂�[�^�[�̒�`
	static RegulatedMotor testMotor = Motor.A;

	public static void main(String[] args) {

		// ���[�^�[�p�x������
		testMotor.resetTachoCount();
		
		// ���[�^�[�ʒu������
		testMotor.rotateTo(0);
		for (int k = 0; k < 5; k++) {
			// ���[�^�[�X�s�[�h��i�K�I�ɃZ�b�g
			testMotor.setSpeed(200 * k);
			
			// ���[�^�[�O�i
			testMotor.forward();
			Delay.msDelay(2000);
		}
		for (int k = 0; k < 1; k++) {
			// ���[�^�[�X�s�[�h��i�K�I�ɃZ�b�g
			testMotor.setSpeed(200 * k * -1);
			
			// ���[�^�[��i
			testMotor.backward();
			Delay.msDelay(2000);
		}
	}

}
