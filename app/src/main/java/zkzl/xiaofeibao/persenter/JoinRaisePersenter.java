package zkzl.xiaofeibao.persenter;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IJoinRaiseStatus;
import zkzl.xiaofeibao.model.IStatus;
import zkzl.xiaofeibao.model.impl.JoinRaiseStatus;
import zkzl.xiaofeibao.view.IJoinRaiseView;

/**
 * Created by Admin on 2015/12/31.
 */
public class JoinRaisePersenter {

    private IJoinRaiseStatus status;
    private IJoinRaiseView view;


    public JoinRaisePersenter(IJoinRaiseView view) {
        this.view = view;
        status = new JoinRaiseStatus();
    }


    public void pay(String money) {
        status.pay(money,new IStatusCallback() {
            @Override
            public void onStatus(IStatus iStatus) {
                JoinRaiseStatus payStatus = (JoinRaiseStatus) iStatus;
                switch (payStatus.getStatus()) {
                    case IJoinRaiseStatus.STATUS_PAY_ERROR:
                        view.hideLoadding();
                        view.error();
                        break;
                    case IJoinRaiseStatus.STATUS_PAY_FAIL:
                        view.hideLoadding();
                        view.showMsg(payStatus.getMessage());
                        break;
                    case IJoinRaiseStatus.STATUS_PAY_ING:
                        view.showLoadding();
                        break;
                    case IJoinRaiseStatus.STATUS_PAY_SUCCESS:
                        view.hideLoadding();
                        view.showMsg(payStatus.getMessage());
                        view.toMain();
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
