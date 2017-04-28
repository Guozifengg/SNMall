package guozifeng.zshx.com.snmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/24.
 * 作用：
 */

public abstract class BaseFragment extends Fragment {
    //定义上下文
    public Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取到上下文
        mContext=getActivity();
    }
    //这是Fragment加载XML布局资源的方法，因为每个Fragment加载的布局是不同的，所以返回一个抽象方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }
    //子类通过重写此方法，来加载不同布局
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //Fragment与Activity被创建时回调，进行数据的初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //数据的初始化，获取到数据需要重写此方法
    public abstract void initData();


}
