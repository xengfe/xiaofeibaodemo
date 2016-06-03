package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.WalletListAdapter;
import zkzl.xiaofeibao.bean.Wallet;
import zkzl.xiaofeibao.widget.WalletDialog;

@ContentView(R.layout.activity_wallet)
public class WalletActivity extends BaseActivity implements WalletDialog.ClickListener {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.other_button)
    private TextView otherButton;

    @ViewInject(R.id.wallet_list)
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        otherButton.setVisibility(View.VISIBLE);
        otherButton.setText("菜单");
        titleButton.setText("我的钱包");

        WalletListAdapter walletListAdapter = new WalletListAdapter(this);
        walletListAdapter.setDatas(getDatas());
        listView.setAdapter(walletListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startOtherActivity(ACTION_WALLETINFO);
            }
        });

    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }

    @Event(R.id.other_button)
    private void menuEvent(View v) {
        WalletDialog walletDialog = new WalletDialog(this, this);
        walletDialog.setCanceledOnTouchOutside(false);
        walletDialog.show();
    }

    @Override
    public void click(View v) {
        switch (v.getId()) {
            case R.id.trade_log_btn:
                startOtherActivity(ACTION_TRADE);
                break;
            case R.id.pay_pwd_magager_btn:
                startOtherActivity(ACTION_MANAGERPWD);
                break;
            case R.id.wallet_dialog_canlce_btn:
                break;
            default:
                break;
        }
    }


    private List<Wallet> getDatas() {
        List<Wallet> list = new ArrayList<Wallet>();
        for (int i = 0; i < 10 ; i++) {
            Wallet wallet = new Wallet();
            wallet.id = i ;
            wallet.name = "  余额";
            wallet.info = "3000.00元";
            list.add(wallet);
        }
        return list;
    }
}
