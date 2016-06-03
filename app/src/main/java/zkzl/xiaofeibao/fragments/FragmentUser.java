package zkzl.xiaofeibao.fragments;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.activities.LoginActivity;
import zkzl.xiaofeibao.utils.ScreenManager;
import zkzl.xiaofeibao.utils.SharedPreferenceManager;
import zkzl.xiaofeibao.widget.CircleImageView;

@ContentView(R.layout.fragment_fragment_user)
public class FragmentUser extends FragmentBase {

    @ViewInject(R.id.title)
    private TextView titleView;

    @ViewInject(R.id.user_avator)
    private ImageView avatar ;

    @ViewInject(R.id.user_name)
    private TextView userNameView ;

    @ViewInject(R.id.has_invest)
    private TextView hasInvestView;

    @ViewInject(R.id.has_earn)
    private TextView hasEarnView;

    @ViewInject(R.id.joined_project)
    private Button joinProjectButton;

    @ViewInject(R.id.foucs_project)
    private Button foucsProjectButton;

    @ViewInject(R.id.setting)
    private Button setButton;

    @ViewInject(R.id.user_avator)
    private CircleImageView circleImageView;

    private  ImageLoader imageLoader;




    public FragmentUser() {
        imageLoader = MyApplication.getInstance().getImageLoader();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView.setText(R.string.app_name);
       boolean flag =  SharedPreferenceManager.getSharedPreferenceManager().getBoolean(getActivity(),LOGIN_STATUS);
        if (!flag) {
            startOtherActivity(ACTION_LOGIN);
        }

        circleImageView.setImageUrl("http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu2.jpg",imageLoader);
    }



    @Event(R.id.wallet_button)
    private void walletEvent (View view) {
        startOtherActivity(ACTION_WALLET);
    }

    @Event(R.id.joined_project)
    private void joinProjectClick(View view) {
        startOtherActivity(ACTION_MY_PROJECT);
    }

    @Event(R.id.foucs_project)
    private void foucsProjectClick(View view) {
        startOtherActivity(ACTION_FOUCS_PROJECT);
    }

    @Event(R.id.group_chat_button)
    private void chatGroupEvent(View view) {
        startOtherActivity(ACTION_CHATGROUP);
    }

    @Event(R.id.setting)
    private void settingClick(View view) {
        startOtherActivity(ACTION_SETTING);
    }

    @Event(R.id.user_avator)
    private void setuserInfoClick(View view) {
        startOtherActivity(ACTION_SET_USER);
    }



}
