package led_sample;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class LEDsample {

	public static void main(String[] args) {

		//---- LED�_��
		// LEDPattern�ɂ� 0, 1, 2, 3, 4, 5, 6 �̏�Ԃ�����܂��B
		// �e������LED�̏�Ԃ𒲂ׂĂ݂悤�B
		for (int k = 0; k < 6; k++) {
			Button.LEDPattern(k);
			// 3000ms = 3s ��~
			Delay.msDelay(3000);
		}
	}

}
