package guozifeng.zshx.com.snmall.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import guozifeng.zshx.com.snmall.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //使用handler的延时操作，两秒后进入主页；方法内有两个参数要注意：Runnable对象和延迟的时间；
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转方法
                jumpMainActivity();

            }
        }, 2000);
    }


    private void jumpMainActivity(){
        Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        //结束当前Activity
        finish();
    }
}
