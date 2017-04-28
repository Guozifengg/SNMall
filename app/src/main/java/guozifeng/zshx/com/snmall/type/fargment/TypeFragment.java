package guozifeng.zshx.com.snmall.type.fargment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import guozifeng.zshx.com.snmall.R;
import guozifeng.zshx.com.snmall.base.BaseFragment;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/24.
 * 作用：
 */

public class TypeFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.type_layout, null);
        return view;
    }

    @Override
    public void initData() {

    }
}
