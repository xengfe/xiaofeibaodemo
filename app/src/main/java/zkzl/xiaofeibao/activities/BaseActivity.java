package zkzl.xiaofeibao.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.utils.ProgressBarManager;
import zkzl.xiaofeibao.utils.ScreenManager;
import zkzl.xiaofeibao.utils.SharedPreferenceManager;
import zkzl.xiaofeibao.utils.SystemStatusManager;

public class BaseActivity extends AppCompatActivity {

    public static final String ACTION_BASE = "zkzl.xiaofeibao.action";
    public static final String ACTION_RAISE_PLAN = ACTION_BASE + ".web_view";
    public static final String ACTION_RAISE_PROMOTE = ACTION_BASE + ".web_view";
    public static final String ACTION_RAISE_EARN = ACTION_BASE + ".web_view";
    public static final String ACTION_RAISE_SECURITY = ACTION_BASE + ".web_view";
    public static final String ACTION_RAISE_ASK = ACTION_BASE + ".raise_ask";
    public static final String ACTION_RAISE_JOIN = ACTION_BASE + ".raise_join";
    public static final String ACTION_RAISE_PAY_TYPE = ACTION_BASE + ".raise_pay_type";
    public static final String ACTION_AVOID_DISTURB = ACTION_BASE + ".avoid_disturb";
    public static final String ACTION_RESET_PASSWORD = ACTION_BASE + ".reset_password";
    public static final String ACTION_RESET_PHONE = ACTION_BASE + ".reset_phone";
    public static final String ACTION_SUBMIT_PHONE = ACTION_BASE + ".submit_phone";
    public static final String ACTION_SUGGESTION = ACTION_BASE + ".suggestion";
    public static final String ACTION_ABOUT = ACTION_BASE + ".about";
    public static final String ACTION_LOGIN = ACTION_BASE + ".login";
    public static final String ACTION_REGIST = ACTION_BASE + ".regist";
    public static final String ACTION_FIND_PASSWORD = ACTION_BASE + ".find_password";
    public static final String ACTION_SET_USERNAME = ACTION_BASE + ".reset_username";
    public static final String ACTION_MAIN = ACTION_BASE + ".MainActivity";
    public static final String ACTION_SETTING = ACTION_BASE + ".setting";
    public static final String ACTION_SEARCH = ACTION_BASE + ".search";
    public static final String ACTION_CHAT = ACTION_BASE + ".chat";
    public static final String ACTION_CHATGROUPSETTING = ACTION_BASE + ".chatgroupsetting";
    public static final String ACTION_WALLET = ACTION_BASE + ".wallet";
    public static final String ACTION_CHATGROUP = ACTION_BASE + ".chatgroup";
    public static final String ACTION_FORGET_PAYPWD = ACTION_BASE + ".forget_paypwd";
    public static final String ACTION_REBINDBANK = ACTION_BASE + ".rebind_bank";
    public static final String ACTION_RESETBANKPWD = ACTION_BASE + ".reset_bank_pwd";
    public static final String ACTION_TRADE = ACTION_BASE + ".trade";
    public static final String ACTION_MANAGERPWD = ACTION_BASE + ".managerpwd";
    public static final String ACTION_RECHARGE = ACTION_BASE + ".recharge";
    public static final String ACTION_WALLETINFO = ACTION_BASE + ".walletinfo";
    public static final String ACTION_WITHDRAW = ACTION_BASE + ".withdraw";
    public static final String ACTION_RESET_PAYPWD = ACTION_BASE + ".reset_paypwd";
    public static final String ACTION_FINSHPAYPWD = ACTION_BASE + ".reset_paypwd_finish";
    public static final String ACTION_WITHDRAWDETAIL = ACTION_BASE + ".withdrawdetail";
    public static final String ACTION_BANKADD = ACTION_BASE + ".bankadd";
    public static final String ACTION_SETPAYPWD = ACTION_BASE + ".setpaypwd";
    public static final String ACTION_BANKADFINISH = ACTION_BASE + ".bankaddfinish";
    public static final String ACTION_BANKLIST = ACTION_BASE + ".banklist";
    public static final String ACTION_BANKINFO = ACTION_BASE + ".bankinfo";

    //业务类型
    public static final String RECHARGE = "recharge";
    public static final String JOINRAISE = "joinraise";


    public final static Gson gson = new Gson();
    public final static SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.getSharedPreferenceManager();
    public static final String LOGIN_STATUS = "loginStatus";
    public ScreenManager screenManager = null;
    public final static ProgressBarManager progressBarManager = ProgressBarManager.getProgressBarManager();

    // 填写从短信SDK应用后台注册得到的APPKEY
    public static String APPKEY = "e1063e96c94c";

    // 填写从短信SDK应用后台注册得到的APPSECRET
    public static String APPSECRET = "be4c6c009b648f027e6d4633010e48cd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        screenManager = ScreenManager.getScreenManager();
        screenManager.pushActivity(this);
        progressBarManager.init(this);
    }

    public void showShortToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    public boolean checkSdkVersion() {
        boolean flag;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public void startOtherActivity(String action) {
        Intent intent = new Intent(action);
        startActivity(intent);
        startActivityAnimation();
    }

    public void startOtherActivityWithParas(String action, String title, String url) {
        Intent intent = new Intent(action);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
        startActivityAnimation();
    }


    /**
     * @param action 将要跳转的下一页
     * @param title  下一页的标题
     * @param type   业务类型（加入众筹or充值）
     */
    public void startOtherActivityWithPayParas(String action, String title, String type) {
        Intent intent = new Intent(action);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        startActivity(intent);
        startActivityAnimation();
    }

    /***
     * avtivity 启动动画
     */
    public void startActivityAnimation() {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /***
     * activity 返回动画
     */
    public void finishActivityAnimation() {
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }


    /***
     * 所有的子类调用此方法返回
     *
     * @param child
     */
    public void backPressed(Activity child) {
        screenManager.popActivity(child);
        finishActivityAnimation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void checkLoginStatus() {
        boolean flag = sharedPreferenceManager.getBoolean(this, LOGIN_STATUS);
        if (!flag) {
            startOtherActivity(ACTION_LOGIN);
        }
    }


    public void hideSystemKeyBoard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    public void showSystemKeyBoard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!imm.isActive()) {
            imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
        }
    }



}
