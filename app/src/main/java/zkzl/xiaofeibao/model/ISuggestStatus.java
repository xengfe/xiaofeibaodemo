package zkzl.xiaofeibao.model;

import zkzl.xiaofeibao.listener.IStatusCallback;

/**
 * Created by Admin on 2016/1/8.
 */
public interface ISuggestStatus extends IStatus{

    void submit(String suggestion,IStatusCallback callback);
}
