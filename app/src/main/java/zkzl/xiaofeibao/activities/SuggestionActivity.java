package zkzl.xiaofeibao.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.persenter.SuggestionPersenter;
import zkzl.xiaofeibao.view.ISuggestView;

@ContentView(R.layout.activity_suggestion)
public class SuggestionActivity extends BaseActivity implements ISuggestView{

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.other_button)
    private TextView submitButton;

    @ViewInject(R.id.suggest_et)
    private EditText suggestET;

    private SuggestionPersenter persenter;
    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        submitButton.setText("提交");
        titleButton.setText("意见反馈");

        persenter = new SuggestionPersenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("提交中...");
        dialog.setCanceledOnTouchOutside(false);
    }


    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.other_button)
    private void submitSuggestionEvent(View v) {
        persenter.submit(suggestET.getText().toString().trim());
    }

    @Override
    public void hideLoadding() {
        dialog.dismiss();
    }


    @Override
    public void showLoadding() {
        dialog.show();
    }


    @Override
    public void showMsg(String msg) {
      showShortToast(msg);
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }
}
