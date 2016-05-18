package color_sample;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class COLORsample {
	//�g�p����Z���T�[�̒�`
	static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
	
	public static void main(String[] args) {
		//�Z���T�[���[�h�̐ݒ�
		SensorMode color = colorSensor.getMode(2);
		//�Z���T�[�̎擾�l���i�[����z��̗p��
		float value[] = new float[color.sampleSize()];
		//��ʂ̏�����
		LCD.clear();
		while(true){
			//�Z���T�[���[�h��\��
			LCD.drawString(color.getName(),1,0);
			//�Z���T�[�l��p�ӂ����z��Ɋi�[
			color.fetchSample(value,0);
			for(int k = 0;k < color.sampleSize(); k++){
				LCD.drawString("vail[" + k +"] :"+ value[k],1,k+1);
				
			}
			Delay.msDelay(10);
			LCD.refresh();
			
		}
	}

}
