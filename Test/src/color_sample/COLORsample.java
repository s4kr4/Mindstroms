package color_sample;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class COLORsample {
	//使用するセンサーの定義
	static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
	
	public static void main(String[] args) {
		//センサーモードの設定
		SensorMode color = colorSensor.getMode(2);
		//センサーの取得値を格納する配列の用意
		float value[] = new float[color.sampleSize()];
		//画面の初期化
		LCD.clear();
		while(true){
			//センサーモードを表示
			LCD.drawString(color.getName(),1,0);
			//センサー値を用意した配列に格納
			color.fetchSample(value,0);
			for(int k = 0;k < color.sampleSize(); k++){
				LCD.drawString("vail[" + k +"] :"+ value[k],1,k+1);
				
			}
			Delay.msDelay(10);
			LCD.refresh();
			
		}
	}

}
