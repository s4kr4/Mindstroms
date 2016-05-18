package touch_sample;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class TOUCHsample {
	// �g�p����Z���T�[�̒�`
	static EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S4);

	public static void main(String[] args) {
		// �Z���T�[���[�h�̐ݒ�
		SensorMode touch = touchSensor.getMode(0);
		// �Z���T�[�̎擾�l���i�[����z��̗p��
		float value[] = new float[touch.sampleSize()];
		// ��ʂ̏�����
		LCD.clear();
		while (true) {
			// �Z���T�[�l��p�ӂ����z��Ɋi�[
			touch.fetchSample(value, 0);
			if (value[0] == 1) {
				LCD.drawString("Button is pressed", 1, 1);
			}
			LCD.refresh();
		}
	}
}