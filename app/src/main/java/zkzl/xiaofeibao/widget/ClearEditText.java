package zkzl.xiaofeibao.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Admin on 2015/12/18.
 */
public class ClearEditText  extends ViewGroup implements View.OnClickListener {


    private TextView clearImage;
    private EditText editText;
    private int clearImageRightPading = 20;
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                clearImage.setVisibility(View.GONE);
            } else {
                clearImage.setVisibility(View.VISIBLE);
            }
        }
    };

    public ClearEditText(Context context) {
        super(context);
        initViews();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        final Context mContext = getContext();
        clearImage = new TextView(mContext);
        clearImage.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        clearImage.setOnClickListener(this);
        clearImage.setVisibility(View.GONE);
        editText = new EditText(mContext);
        editText.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        editText.addTextChangedListener(textWatcher);
        editText.setSingleLine(true);
        addView(editText);
        addView(clearImage);
        requestLayout();
    }

    /**
     * 设置 清除图片 的资源
     *
     * @param drawableId
     */
    public void setClearImageDrawableId(int drawableId) {
        clearImage.setBackgroundResource(drawableId);
    }

    /**
     * 设置EditText的padding（如果调用了{@link #setEditTextBackGround(int)}
     * 方法，其背景图片是.9图的话，必须要调用此方法）
     *
     * @param paddingLeft
     *            左边的padding
     * @param paddinTop
     *            上边padding
     * @param paddingRight
     *            右边padding（这个padding是指文章到 清除图片的 padding）
     * @param paddingBottom
     *            下边padding
     */
    public void setEditTextPadding(int paddingLeft, int paddinTop, int paddingRight, int paddingBottom) {
        paddingRight += clearImageRightPading + clearImage.getBackground().getIntrinsicWidth();
        editText.setPadding(paddingLeft, paddinTop, paddingRight, paddingBottom);
    }


    /**
     * 设置删除图片距离右边的距离
     *
     * @param clearImageRightPading
     */
    public void setClearImageRightPading(int clearImageRightPading) {
        this.clearImageRightPading = clearImageRightPading;
        requestLayout();
    }


    /**
     * 设置EditText的背景</br>Note</br>(1)调用了该方法之后，如果背景图是.9图的话，必须调用
     * {@link #setEditTextPadding(int, int, int, int)}方法 </br>(2)此方法必须是调用了
     * {@link #setClearImageDrawableId(int)}之后才能调用
     *
     * @param resid
     */
    public void setEditTextBackGround(int resid) {
        editText.setBackgroundResource(resid);
    }


    public void setTextColor(int color) {
        editText.setTextColor(color);
    }

    public void setTextColorSize(int size) {
        editText.setTextSize(size);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // int height = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == 0) {
                getChildAt(i).measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                        getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
            } else {
                TextView secondView = (TextView) getChildAt(i);
                secondView.measure(MeasureSpec.makeMeasureSpec(secondView.getBackground().getIntrinsicWidth(), MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(secondView.getBackground().getIntrinsicHeight(), MeasureSpec.UNSPECIFIED));
            }
        }
        setMeasuredDimension(width, getChildAt(0).getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final EditText firstView = editText;
        final View secondView = clearImage;
        final int editTextWith = firstView.getMeasuredWidth();
        final int imageViewWidth = secondView.getMeasuredWidth();
        firstView.layout(0, 0, editTextWith, firstView.getMeasuredHeight());
        final int top = (firstView.getMeasuredHeight() - secondView.getMeasuredHeight()) >> 1;
        secondView.layout(editTextWith - imageViewWidth - clearImageRightPading, top, editTextWith - clearImageRightPading, top
                + secondView.getMeasuredHeight());
    }

    @Override
    public void onClick(View v) {
        editText.setText("");
    }

}
