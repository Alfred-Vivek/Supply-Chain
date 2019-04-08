package com.example.supplychain.Activity;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import com.example.supplychain.R;
import com.example.supplychain.Utils.PrefUtils;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
            finish();
        }
        start();
    }
    private void start() {
        Thread splash = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    String org = PrefUtils.getFromPrefs(getApplicationContext(), PrefUtils.user_dcorg,"");
                    if(org.equalsIgnoreCase(""))
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(!org.equalsIgnoreCase("tpw"))
                    {
                        Intent intent = new Intent(getApplicationContext(), LogisticsHome.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(getApplicationContext(), TpwHome.class);
                        intent.putExtra("from","splashScreen");
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        splash.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}