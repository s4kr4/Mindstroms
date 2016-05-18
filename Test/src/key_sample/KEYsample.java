package key_sample;

import lejos.hardware.Button;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class KEYsample {

	public static void main(String[] args) {
		while(true){
			LCD.clear();
			//キースイッチの状態をリストに格納
			boolean keysState[]={
					Button.ENTER.isDown(),
					Button.ESCAPE.isDown(),
					Button.UP.isDown(),
					Button.DOWN.isDown(),
					Button.RIGHT.isDown(),
					Button.LEFT.isDown(),
			};
		//----キーの状態表示部分
			for(int k=0; k<keysState.length; k++){
				//"キーの状態を文字列に変換"の呼び出しと格納
				String drawData = drawkeyState(keysState[k], k);
				//LCDの文字列状態を出力
				LCD.drawString(drawData, 1, k);
			}
			LCD.refresh();
			Delay.msDelay(10);
		}
	}
	//----キー状態を文字に変換
	public static String drawkeyState(boolean state,int k) {
		switch (k) {
		case 0: return "Enter is "+sevaralState(state);
		case 1: return "Escape is "+sevaralState(state);
		case 2: return "UP is "+sevaralState(state);
		case 3: return "Down is " +sevaralState(state);
		case 4: return "Right is "+sevaralState(state);
		case 5: return "Left is "+sevaralState(state);
		default: return null;
		}
	}
	public  static String sevaralState(boolean state){
		return state ?"Down":"UP";
	}
}
