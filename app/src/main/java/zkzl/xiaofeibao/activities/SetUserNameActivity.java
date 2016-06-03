package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;

@ContentView(R.layout.activity_set_user_name)
public class SetUserNameActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.other_button)
    private TextView otherButton;

    @ViewInject(R.id.title)
    private TextView titleButton;


    @ViewInject(R.id.edit_username)
    private EditText usernameEditText;

    @ViewInject(R.id.clear_username)
    private TextView clearTextView;


    private TextWatcher mTextWatcher = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                clearTextView.setVisibility(View.VISIBLE);
            } else {
                clearTextView.setVisibility(View.INVISIBLE);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        otherButton.setVisibility(View.VISIBLE);
        otherButton.setText("保存");
        titleButton.setText("姓名");


        usernameEditText.addTextChangedListener(mTextWatcher);


    }


    @Event(R.id.back_button)
    private void backEvent(View v) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.other_button)
    public void saveUserNameEvent(View v) {

    }

    @Event(R.id.clear_username)
    private void clearEditText(View view) {
        usernameEditText.setText("");
    }


    @Override
    public void onBackPressed() {
        backPressed(this);
    }
}
