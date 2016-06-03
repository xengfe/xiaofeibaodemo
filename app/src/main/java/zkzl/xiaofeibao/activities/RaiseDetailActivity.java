package zkzl.xiaofeibao.activities;

import android.os.Bundle;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.widget.CallPhoneDialog;
import zkzl.xiaofeibao.widget.ShareDialog;
import zkzl.xiaofeibao.utils.PhoneUtil;

@ContentView(R.layout.activity_raise_detail)
public class RaiseDetailActivity extends BaseActivity implements ShareDialog.ShareListener,
        CallPhoneDialog.CallListener, PlatformActionListener {

    public static final int SHARE_SUCCESS_WX = 1;
    public static final int SHARE_SUCCESS_WXF = 2;
    public static final int SHARE_SUCCESS_QQ = 3;
    public static final int SHARE_SUCCESS_ZONE = 4;
    public static final int SHARE_CANCLE = 5;
    public static final int SHARE_ERROR = 6;

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.other_button)
    private TextView shareButton;

    @ViewInject(R.id.title)
    private TextView titleButton;
    @ViewInject(R.id.header_image_view)
    private ImageView headImageView;
    @ViewInject(R.id.time_tv)
    private TextView rasieDateTextView;
    @ViewInject(R.id.header_name)
    private TextView raiseNameTextView;
    @ViewInject(R.id.raise_progressbar)
    private ProgressBar progressBar;
    @ViewInject(R.id.raise_progress_num)
    private TextView rasieProgressTextView;
    @ViewInject(R.id.raise_money)
    private TextView rasieMoneyTextView;
    @ViewInject(R.id.raise_left_day)
    private TextView raiseLeftDayTextView;

    @ViewInject(R.id.raise_plan)
    private Button raisePalnButton;
    @ViewInject(R.id.raise_promote)
    private Button raisePromoteButton;
    @ViewInject(R.id.raise_earn)
    private Button rasieEarnButton;
    @ViewInject(R.id.raise_security)
    private Button raiseSecurityButton;
    @ViewInject(R.id.raise_ask)
    private Button raiseAskButton;


    @ViewInject(R.id.raise_focus)
    private Button focusButton;
    @ViewInject(R.id.raise_invest)
    private Button investButton;

    @ViewInject(R.id.call_button)
    private Button callButton;


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SHARE_SUCCESS_WX:
                    showShortToast("微信分享成功");
                    break;
                case SHARE_SUCCESS_WXF:
                    showShortToast("朋友圈分享成功");
                    break;
                case SHARE_SUCCESS_QQ:
                    showShortToast("QQ分享成功");
                    break;
                case SHARE_SUCCESS_ZONE:
                    showShortToast("QQ空间分享成功");
                    break;
                case SHARE_CANCLE:
                    showShortToast("取消分享");
                    break;
                case SHARE_ERROR:
                    showShortToast("分享失败");
                    break;

                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setText("分享");
        titleButton.setText("众筹详情");
        ShareSDK.initSDK(this);

    }


    @Event(R.id.back_button)
    private void backEvent(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }


    @Event(R.id.other_button)
    private void shareButtonClick(View view) {
//        SharePopuWin sharePopuWin = new SharePopuWin(this, this);
//        sharePopuWin.showAtLocation(findViewById(R.id.raise_main), Gravity.CENTER, 0, 0);

        ShareDialog shareDialog = new ShareDialog (this, this);
        shareDialog.setCanceledOnTouchOutside(false);
        shareDialog.show();
    }

    @Override
    public void share(View v) {
        switch (v.getId()) {

            case R.id.share_qq:
                QQ.ShareParams spQQ = new QQ.ShareParams();
                spQQ.setTitle("我是分享标题");
                spQQ.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");
                spQQ.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
                spQQ.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(RaiseDetailActivity.this); // 设置分享事件回调
                qq.share(spQQ);
                break;
            case R.id.share_qzion:
                QZone.ShareParams spZone = new QZone.ShareParams();
                spZone.setTitle("我是分享标题");
                spZone.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");
                spZone.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");
                spZone.setTitleUrl("http://www.baidu.com");
                Platform zone = ShareSDK.getPlatform(QZone.NAME);
                zone.setPlatformActionListener(RaiseDetailActivity.this);
                zone.share(spZone);
                break;
            case R.id.share_wechat:
                Wechat.ShareParams sp = new Wechat.ShareParams();
                sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
                sp.setTitle("我是分享标题");  //分享标题
                sp.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");   //分享文本
                sp.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
                sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(RaiseDetailActivity.this); // 设置分享事件回调
                wechat.share(sp);
                break;
            case R.id.share_wechat_fri:
                WechatMoments.ShareParams spf = new WechatMoments.ShareParams();
                spf.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                spf.setTitle("我是分享标题");  //分享标题
                spf.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");   //分享文本
                spf.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
                spf.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情
                Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                wechatMoments.setPlatformActionListener(RaiseDetailActivity.this); // 设置分享事件回调
                wechatMoments.share(spf);
                break;

            default:
                break;
        }
    }


    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        arg2.printStackTrace();
        Message msg = new Message();
        msg.what = SHARE_ERROR;
        msg.obj = arg2.getMessage();
        handler.sendMessage(msg);
    }


    @Override
    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        if (arg0.getName().equals(Wechat.NAME)) {
            handler.sendEmptyMessage(SHARE_SUCCESS_WX);
        } else if (arg0.getName().equals(WechatMoments.NAME)) {
            handler.sendEmptyMessage(SHARE_SUCCESS_WXF);
        } else if (arg0.getName().equals(QQ.NAME)) {
            handler.sendEmptyMessage(SHARE_SUCCESS_QQ);
        } else if (arg0.getName().equals(QZone.NAME)) {
            handler.sendEmptyMessage(SHARE_SUCCESS_ZONE);
        }

    }

    @Override
    public void onCancel(Platform arg0, int arg1) {//回调的地方是子线程，进行UI操作要用handle处理
        handler.sendEmptyMessage(SHARE_CANCLE);
    }

    @Event(R.id.raise_plan)
    private void intentPlanClick(View view) {
        startOtherActivityWithParas(ACTION_RAISE_PLAN, "众筹计划", "http://www.baidu.com");

    }

    @Event(R.id.raise_promote)
    private void intentPromoteClick(View view) {
        startOtherActivityWithParas(ACTION_RAISE_PROMOTE, "提升计划", "http://www.baidu.com");
    }

    @Event(R.id.raise_earn)
    private void intentEarnClick(View view) {
        startOtherActivityWithParas(ACTION_RAISE_EARN, "投资收益", "http://www.baidu.com");
    }

    @Event(R.id.raise_security)
    private void intentSecurityClick(View view) {
        startOtherActivityWithParas(ACTION_RAISE_SECURITY, "投资保障", "http://www.baidu.com");
    }

    @Event(R.id.raise_ask)
    private void intentAskClick(View view) {
        startOtherActivity(ACTION_RAISE_ASK);
    }

    @Event(R.id.raise_focus)
    private void focusClick(View view) {
        focusButton.setText("已关注");
        showShortToast("已经关注！");

    }

    @Event(R.id.raise_invest)
    private void investClick(View view) {
        startOtherActivityWithPayParas(ACTION_RAISE_JOIN,"参加众筹",JOINRAISE);
    }


    @Event(R.id.call_button)
    private void callButtonClick(View view) {
//        CallPopuWin callPopuWin = new CallPopuWin(this, "刘志伟", "15010183136", this);
//        callPopuWin.showAtLocation(findViewById(R.id.raise_main), Gravity.CENTER, 0, 0);

        CallPhoneDialog callPhoneDialog = new CallPhoneDialog(this, "刘志伟", "15010183136", this);
        callPhoneDialog.setCanceledOnTouchOutside(false);
        callPhoneDialog.show();
    }

    @Override
    public void call(String phoneNumber) {
        PhoneUtil.callPhones(this, phoneNumber);
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }
}
