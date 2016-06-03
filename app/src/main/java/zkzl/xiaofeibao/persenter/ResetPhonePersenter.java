package zkzl.xiaofeibao.persenter;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IResetPhoneStatus;
import zkzl.xiaofeibao.model.IStatus;
import zkzl.xiaofeibao.model.impl.ResetPhoneStatus;
import zkzl.xiaofeibao.view.IResetPhoneView;

/**
 * Created by Admin on 2016/1/7.
 */
public class ResetPhonePersenter {

    private ResetPhoneStatus status;
    private IResetPhoneView view;

    public ResetPhonePersenter(IResetPhoneView view) {
        this.view = view;
        status = new ResetPhoneStatus();
    }


    public void resetPhone(String phone, String reg) {
        status.resetPhone(phone, reg, new IStatusCallback() {
            @Override
            public void onStatus(IStatus iStatus) {
                ResetPhoneStatus resetPhoneStatus = (ResetPhoneStatus) iStatus;
                switch (resetPhoneStatus.getStatus()) {
                    case IResetPhoneStatus.STATUS_ERROR:
                    case IResetPhoneStatus.STATUS_FAIL:
                        view.hideLoadding();
                        view.showMsg(status.getMessage());
                        break;
                    case IResetPhoneStatus.STATUS_DOING:
                        view.showLoadding();
                        break;
                    case IResetPhoneStatus.STATUS_SUCCESS:
                        view.hideLoadding();
                        view.showMsg(status.getMessage());
                        view.toNextActivity();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
