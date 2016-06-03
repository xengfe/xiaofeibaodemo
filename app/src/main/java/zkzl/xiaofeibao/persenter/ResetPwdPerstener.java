package zkzl.xiaofeibao.persenter;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IResetPwdStatus;
import zkzl.xiaofeibao.model.IStatus;
import zkzl.xiaofeibao.model.impl.ResetPwdStatus;
import zkzl.xiaofeibao.view.IResetPwdView;

/**
 * Created by Admin on 2016/1/7.
 */
public class ResetPwdPerstener {

    private ResetPwdStatus status;
    private IResetPwdView view;


    public ResetPwdPerstener(IResetPwdView view) {
        this.view = view;
        status = new ResetPwdStatus();
    }

    public void resetPwd(String phone,String oldpwd,String newpwd1,String newpwd2) {
        status.resetPwd(phone,oldpwd,newpwd1,newpwd2,new IStatusCallback(){
            @Override
            public void onStatus(IStatus iStatus) {
                ResetPwdStatus resetPwdStatus = (ResetPwdStatus)iStatus;
                switch (resetPwdStatus.getStatus()){

                    case IResetPwdStatus.STATUS_ERROR:
                    case IResetPwdStatus.STATUS_FAIL:
                        view.hideLoadding();
                        view.showMsg(status.getMessage());
                        break;
                    case IResetPwdStatus.STATUS_DOING:
                        view.showLoadding();
                        break;
                    case IResetPwdStatus.STATUS_SUCCESS:
                        view.hideLoadding();
                        view.showMsg(status.getMessage());
                        view.toLogin();
                        break;

                    default:
                        break;
                }
            }
        });
    }
}
