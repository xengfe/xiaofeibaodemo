package zkzl.xiaofeibao.model;

/**
 * Created by feixiang on 2015/12/22
 * <br/>
 * 公共的接口
 */
public interface IStatus {

    public static final int STATUS_SUCCESS = 1;//成功
    public static final int STATUS_FAIL = 0;//失败
    public static final int STATUS_DOING = 2;//进行中
    public static final int STATUS_ERROR = -1;//异常

}
