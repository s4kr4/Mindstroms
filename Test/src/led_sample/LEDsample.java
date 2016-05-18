package led_sample;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class LEDsample {

	public static void main(String[] args) {

		//---- LED点灯
		// LEDPatternには 0, 1, 2, 3, 4, 5, 6 の状態があります。
		// 各数字のLEDの状態を調べてみよう。
		for (int k = 0; k < 6; k++) {
			Button.LEDPattern(k);
			// 3000ms = 3s 停止
			Delay.msDelay(3000);
		}
	}

}
