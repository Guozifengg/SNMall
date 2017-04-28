package guozifeng.zshx.com.snmall.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import guozifeng.zshx.com.snmall.R;
import guozifeng.zshx.com.snmall.base.BaseFragment;
import guozifeng.zshx.com.snmall.home.adapter.HomeFragmentAdapter;
import guozifeng.zshx.com.snmall.home.bean.ResultBeanData;
import guozifeng.zshx.com.snmall.utils.Constants;
import okhttp3.Call;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/24.
 * 作用：
 */

public class HomeFragment extends BaseFragment{

    private RecyclerView rvHome;
    private ImageButton ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean mResultBean;
    private HomeFragmentAdapter homeFragmentAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.home_layout, null);

        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

        //RecyclerView滑动监听
        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>=5){
                    ib_top.setVisibility(View.VISIBLE);
                }
                if(dy==0){
                    ib_top.setVisibility(View.GONE);
                }
            }
        });
        //给按钮设置监听
        initListener();
        return view;
    }

    @Override
    public void initData() {
        //使用OKhttp工具类get请求网络
        OkhttpGETData();
    }

    private void OkhttpGETData() {
        //创建一个url，想要改变地址也好改变
        String url= Constants.HOME_URL;
        OkHttpUtils
                .get()//网络请求方式
                .url(url)//设置请求地址
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String s, int i) {
                        //定义一个方法去解析获取到的数据
                        processData(s);
                    }
                });
    }

    private void processData(String s) {
        //使用fastJson解析数据，和Gson使用差不多
        ResultBeanData resultBeanData= JSON.parseObject(s,ResultBeanData.class);
        //得到数据的集合
        mResultBean = resultBeanData.getResult();
        //取出相应的数据
        /*String name=mResultBean.getHot_info().get(0).getName();
        String pic=mResultBean.getBanner_info().get(0).getImage();
        Log.d("zzz",name);
        Log.d("zzz",pic);*/

        if(mResultBean!=null){
            //有数据，创建RecyclerView的适配器，参数：1.上下文 2、数据
            homeFragmentAdapter = new HomeFragmentAdapter(mContext,mResultBean);
            //RecyclerView设置适配器
            rvHome.setAdapter(homeFragmentAdapter);
            //设置布局管理器，决定RecyclerView的整体样貌 参数：1、上下文 2、决定样貌格式
            rvHome.setLayoutManager(new GridLayoutManager(mContext,1));

        }else{
            Toast.makeText(mContext, "数据为空", Toast.LENGTH_SHORT).show();
        }
    }

    //所有按钮的监听
    private void initListener() {

        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部操作
                rvHome.scrollToPosition(0);
            }
        });

        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击搜索", Toast.LENGTH_SHORT).show();
            }
        });

        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
