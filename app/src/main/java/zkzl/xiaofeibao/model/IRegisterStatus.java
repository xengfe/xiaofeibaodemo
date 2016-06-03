package zkzl.xiaofeibao.model;

import zkzl.xiaofeibao.listener.IStatusCallback;

/**
 * Created by feixiang on 2015/12/22.
 */
public interface IRegisterStatus extends IStatus{

    public static final int STATUS_REGIST_VERIFY_FAIL = -1;//验证失败
    public static final int STATUS_REGIST_FAIL = -2;//注册失败
    public static final int STATUS_REGIST_SUCCESS = 0;//注册成功
    public static final int STATUS_REGIST_ING = 1;//注册中




    /**
     * 注册行为
     * @param username 用户名
     * @param phone 手机号
     * @param regNum 手机验证码
     * @param psw 密码
     * @param affirmPwd 确认密码
     * @param callback 状态码
     */
     void regist(String username,String phone,String regNum,String psw,String affirmPwd,IStatusCallback callback);



}
