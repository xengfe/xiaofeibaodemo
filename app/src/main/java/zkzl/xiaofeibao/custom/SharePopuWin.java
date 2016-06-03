package zkzl.xiaofeibao.custom;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2015/12/25.
 */
public class SharePopuWin extends PopupWindow {

    private ShareListener shareListener;

    public SharePopuWin(Context context, ShareListener shareListener) {

        this.shareListener = shareListener;
        View view = LayoutInflater.from(context).inflate(R.layout.share_pop, null);
        ImageView shareQQ = (ImageView) view.findViewById(R.id.share_qq);
        shareQQ.setOnClickListener(listener);
        ImageView shareQzion = (ImageView) view.findViewById(R.id.share_qzion);
        shareQzion.setOnClickListener(listener);
        ImageView shareWechat = (ImageView) view.findViewById(R.id.share_wechat);
        shareWechat.setOnClickListener(listener);
        ImageView shareWechatFri = (ImageView) view.findViewById(R.id.share_wechat_fri);
        shareWechatFri.setOnClickListener(listener);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_share_cancle);
        btn_cancel.setOnClickListener(listener);
//        this.setOutsideTouchable(false);
//        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        this.view.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int height = view.findViewById(R.id.pop_layout).getTop();
//
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });

         /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(new BitmapDrawable());
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.share_popu_anim);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shareListener.share(v);
            dismiss();
        }
    };


    public interface ShareListener {
        void share(View view);
    }
}
