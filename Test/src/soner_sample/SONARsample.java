package soner_sample;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class SONARsample {

	// �g�p����Z���T�[�̒�`
	static EV3UltrasonicSensor sonicSensor = new EV3UltrasonicSensor(SensorPort.S4);
	
	public static void main(String[] args) {
		//�Z���T�[���[�h�̐ݒ�
		SensorMode sonic = sonicSensor.getMode(0);
		//�Z���T�[�̎擾�l���i�[����z��̗p��
		float value[] = new float[sonic.sampleSize()];
		//��ʂ̏�����
		LCD.clear();

		//----�Z���T�[�l�擾����
		while (true) {
			//�Z���T�[�̃��[�h��\��
			LCD.drawString(sonic.getName(), 1, 0);
			//�Z���T�[�l��z��Ɋi�[
			sonic.fetchSample(value, 0);
			//----�Z���T�[�̒lLCD�\������
			for (int k = 0; k < sonic.sampleSize(); k++) {
				LCD.drawString("val[" + k + "] :" + value[k] + "�@m", 1, k+1);
			}
			Delay.msDelay(100);
			LCD.refresh();
		}

	}
}
