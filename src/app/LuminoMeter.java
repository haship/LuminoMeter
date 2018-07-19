/**
 *
 */
package app;

import jp.etrobo.ev3.sample.EV3way;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

/**
 * @author hashi
 *
 */
public class LuminoMeter {

	//定数
	public static final int INIT = 1;				//初期状態
	public static final int STAND_READY = 2;		//完全停止中、計測待機
	public static final int STAND_MEASURE = 3;	//完全停止中、計測中


	//属性・関連端名
    private EV3way         body;            // EV3 本体
    private boolean        touchPressed;    // タッチセンサーが押されたかの状態
	private int state;		//現在の状態

	//コンストラクタ
	public LuminoMeter() {
		body = new EV3way();	//
		state = INIT;			//現在の状態を初期状態にする
	}


	//操作

    /**
     * 尻尾を完全停止位置に固定し、スタート指示があるかをチェックする。
     * @return true=wait / false=start
     */
    public boolean waitForStart() {
        boolean res = true;
        body.controlTail(EV3way.TAIL_ANGLE_STAND_UP);
        if (body.touchSensorIsPressed()) {
            touchPressed = true;          // タッチセンサーが押された
        } else {
            if (touchPressed) {
                res = false;
                touchPressed = false;     // タッチセンサーが押された後に放した
            }
        }
        //リモートスタート部分は使用しないので削除
        return res;
    }

    /**
     * 計測終了指示のチェック。
     */
    public boolean waitForStop() {
    	boolean res = true;
        if (Button.ESCAPE.isDown()) {	//戻るボタンを押したら終了
            res = false;
        }
        return res;
    }

	//計測終了


	public static void main(String[] args) {

		//初期化処理を行う
        LCD.drawString("Please Wait...  ", 0, 4);	//画面表示
        LuminoMeter program = new LuminoMeter();
        Sound.playTone(440, 5);						//初期化終了を音で知らせる

        //ループ

        while(true) {





        	if(program.waitForStop()) break;

        }





	}

}
