package soner_sample;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class SONARsample {

	// 使用するセンサーの定義
	static EV3UltrasonicSensor sonicSensor = new EV3UltrasonicSensor(SensorPort.S4);
	
	public static void main(String[] args) {
		//センサーモードの設定
		SensorMode sonic = sonicSensor.getMode(0);
		//センサーの取得値を格納する配列の用意
		float value[] = new float[sonic.sampleSize()];
		//画面の初期化
		LCD.clear();

		//----センサー値取得部分
		while (true) {
			//センサーのモードを表示
			LCD.drawString(sonic.getName(), 1, 0);
			//センサー値を配列に格納
			sonic.fetchSample(value, 0);
			//----センサーの値LCD表示部分
			for (int k = 0; k < sonic.sampleSize(); k++) {
				LCD.drawString("val[" + k + "] :" + value[k] + "　m", 1, k+1);
			}
			Delay.msDelay(100);
			LCD.refresh();
		}

	}
}
