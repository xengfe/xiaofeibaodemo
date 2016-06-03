package zkzl.xiaofeibao.view;

/**
 * Created by Admin on 2015/12/22.
 */
public interface IView {

    /**
     * 加载中
     */
    public void showLoadding();
    /**
     * 隐藏
     */
    public void hideLoadding();

    /**
     * 弹出提示信息。
     */
    public void showMsg(String msg);
}
