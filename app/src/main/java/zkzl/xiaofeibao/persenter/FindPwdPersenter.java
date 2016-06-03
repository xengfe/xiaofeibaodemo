package zkzl.xiaofeibao.persenter;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IStatus;
import zkzl.xiaofeibao.model.impl.FindPwdStatus;
import zkzl.xiaofeibao.view.IFindPwdView;

/**
 * Created by Admin on 2016/1/7.
 */
public class FindPwdPersenter {

    private IFindPwdView iFindPwdView;
    private FindPwdStatus findPwdStatus;

    public FindPwdPersenter(IFindPwdView iFindPwdView) {
        this.iFindPwdView = iFindPwdView;
        findPwdStatus = new FindPwdStatus();
    }


    public void submit(String phone,String regNumber,String oldpwd1,String oldpwd2) {
        findPwdStatus.submit(phone,regNumber,oldpwd1,oldpwd2,new IStatusCallback(){
            @Override
            public void onStatus(IStatus iStatus) {
                FindPwdStatus status = (FindPwdStatus)iStatus;

                switch (status.getStatus()) {
                    case FindPwdStatus.STATUS_FAIL:
                    case FindPwdStatus.STATUS_ERROR:
                        iFindPwdView.hideLoadding();
                        iFindPwdView.showMsg(status.getMessage());
                        break;
                    case FindPwdStatus.STATUS_DOING:
                        iFindPwdView.showLoadding();
                        break;
                    case FindPwdStatus.STATUS_SUCCESS:
                        iFindPwdView.hideLoadding();
                        iFindPwdView.showMsg(status.getMessage());
                        iFindPwdView.toLogin();
                        break;

                    default:
                        break;
                }
            }
        });
    }
}
