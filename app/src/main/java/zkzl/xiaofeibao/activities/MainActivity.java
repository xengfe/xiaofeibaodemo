package zkzl.xiaofeibao.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.fragments.FragmentHome;
import zkzl.xiaofeibao.fragments.FragmentPreRaise;
import zkzl.xiaofeibao.fragments.FragmentRaise;
import zkzl.xiaofeibao.fragments.FragmentUser;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    public static final String TAG_EXIT = "exit";

    private FragmentHome fragmentHome;
    private FragmentRaise fragmentRaise;
    private FragmentPreRaise fragmentPreRaise;
    private FragmentUser fragmentUser;

    private boolean mIsExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        initView();
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }


    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    /**
     * 初始化组件
     */
    private void initView() {

        fragmentHome = new FragmentHome();
        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentHome).commit();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.tab_radio_group);
        radioGroup.getBackground().setAlpha(50);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_home_rb:
                        if (fragmentHome == null) {
                            fragmentHome = new FragmentHome();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentHome).commit();
                        break;
                    case R.id.tab_raise_rb:
                        if (fragmentRaise == null) {
                            fragmentRaise = new FragmentRaise();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentRaise).commit();
                        break;
                    case R.id.tab_preraise_rb:
                        if (fragmentPreRaise == null) {
                            fragmentPreRaise = new FragmentPreRaise();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentPreRaise).commit();
                        break;
                    case R.id.tab_user_rb:
                        if (fragmentUser == null) {
                            fragmentUser = new FragmentUser();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentUser).commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }


//    @Event(R.id.other_button)
//    private void searchEvent(View v) {
//        startOtherActivity(ACTION_SEARCH);
//    }




//    private void doJsonObjectRequest() {
//        String tag_json_obj = "json_obj_req";
//        String url = "http://api.androidhive.info/volley/person_object.json";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        VolleyLog.v(TAG, "response:" + response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", "Androidhive");
//                params.put("email", "abc@androidhive.info");
//                params.put("password", "password123");
//
//                return params;
//            }
//
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                headers.put("apiKey", "xxxxxxxxxxxxxxx");
//                return headers;
//            }
//
//            @Override
//            public Priority getPriority() {
//                return super.getPriority();
//            }
//        };
//
//
//        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);
//
//    }
//
//
//    private void doStringHttpRequest() {
//        String tag_string_req = "string_req";
//
//        String url = "http://api.androidhive.info/volley/string_response.html";
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        StringRequest strReq = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        VolleyLog.v(TAG, "response:" + response);
//                        pDialog.hide();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                pDialog.hide();
//            }
//        });
//
//        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
//    }

}
