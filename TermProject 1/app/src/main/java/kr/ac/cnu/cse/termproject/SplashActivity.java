package kr.ac.cnu.cse.termproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Usung on 2017-11-27.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        startActivity(new Intent(this, TestActivity.class));
        finish();
    }
}
