package zkzl.xiaofeibao.persenter;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IRegisterStatus;
import zkzl.xiaofeibao.model.IStatus;
import zkzl.xiaofeibao.model.impl.RegistStatus;
import zkzl.xiaofeibao.view.IRegistView;

/**
 * Created by Admin on 2015/12/22.
 */
public class RegistPersenter {

    private IRegistView mIRegistView;
    private IRegisterStatus mIRegisterStatus;

    public RegistPersenter(IRegistView registView) {
        mIRegistView = registView;
        mIRegisterStatus = new RegistStatus();
    }


    public void registSuccess(String username,String phone,String regNum, String password,String affirmPassword) {
        mIRegisterStatus.regist(username,phone,regNum, password,affirmPassword, new IStatusCallback() {
            @Override
            public void onStatus(IStatus iStatus) {
                RegistStatus registStatus = (RegistStatus) iStatus;
                switch (registStatus.getStatus()) {
                    case IRegisterStatus.STATUS_REGIST_VERIFY_FAIL:
                    case IRegisterStatus.STATUS_REGIST_FAIL:
                        mIRegistView.hideLoadding();
                        mIRegistView.showMsg(registStatus.getMsg());
                        break;
                    case IRegisterStatus.STATUS_REGIST_ING:
                        mIRegistView.showLoadding();
                        break;
                    case IRegisterStatus.STATUS_REGIST_SUCCESS:
                        mIRegistView.hideLoadding();
                        mIRegistView.moveToLogin();
                        break;
                    default:
                        break;
                }
            }
        });
    }




}
