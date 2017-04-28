package guozifeng.zshx.com.snmall.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/25.
 * 作用：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化OKhttp工具类
        initOkhttp();
    }

    private void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
               //设置连接超时
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //设置读取超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();
        //进行OKhttp工具类初始化的操作
        OkHttpUtils.initClient(okHttpClient);
    }
}
