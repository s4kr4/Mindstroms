package touch_sample;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class TOUCHsample {
	// 使用するセンサーの定義
	static EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S4);

	public static void main(String[] args) {
		// センサーモードの設定
		SensorMode touch = touchSensor.getMode(0);
		// センサーの取得値を格納する配列の用意
		float value[] = new float[touch.sampleSize()];
		// 画面の初期化
		LCD.clear();
		while (true) {
			// センサー値を用意した配列に格納
			touch.fetchSample(value, 0);
			if (value[0] == 1) {
				LCD.drawString("Button is pressed", 1, 1);
			}
			LCD.refresh();
		}
	}
}