package zkzl.xiaofeibao.fragments;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.xutils.x;

import zkzl.xiaofeibao.R;


public class FragmentBase extends Fragment {

    public static final String ACTION_BASE = "zkzl.xiaofeibao.action";
    public static final String ACTION_MY_PROJECT = ACTION_BASE + ".my_project";
    public static final String ACTION_FOUCS_PROJECT = ACTION_BASE + ".foucs_project";
    public static final String ACTION_SETTING = ACTION_BASE + ".setting";
    public static final String ACTION_PRE_RAISE_DETAIL = ACTION_BASE + ".pre_raise_detail";
    public static final String ACTION_RAISE_DETAIL = ACTION_BASE + ".raise_detail";
    public static final String ACTION_SET_USER = ACTION_BASE + ".user_info";
    public static final String ACTION_LOGIN = ACTION_BASE + ".login";
    public static final String LOGIN_STATUS = "loginStatus";
    public static final String ACTION_WALLET = ACTION_BASE + ".wallet";
    public static final String ACTION_CHAT = ACTION_BASE + ".chat";
    public static final String ACTION_CHATGROUP = ACTION_BASE + ".chatgroup";
    public static final String ACTION_SEARCH = ACTION_BASE + ".search";

    private boolean injected = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    public void startOtherActivity(String action) {
        Intent intent = new Intent(action);
        startActivity(intent);
        startActivityAnimation();
    }

    public void startActivityAnimation() {
        getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }


}
