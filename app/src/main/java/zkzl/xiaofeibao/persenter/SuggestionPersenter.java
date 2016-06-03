package zkzl.xiaofeibao.persenter;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IStatus;
import zkzl.xiaofeibao.model.ISuggestStatus;
import zkzl.xiaofeibao.model.impl.SuggestStatus;
import zkzl.xiaofeibao.view.ISuggestView;

/**
 * Created by Admin on 2016/1/8.
 */
public class SuggestionPersenter {

    private SuggestStatus status;
    private ISuggestView view;

    public SuggestionPersenter(ISuggestView view) {
        this.view = view;
        status = new SuggestStatus();
    }

    public void submit(String suggetion) {
        status.submit(suggetion, new IStatusCallback() {
            @Override
            public void onStatus(IStatus iStatus) {
                SuggestStatus suggestStatus = (SuggestStatus) iStatus;
                switch (suggestStatus.getStatus()) {
                    case ISuggestStatus.STATUS_ERROR:
                    case ISuggestStatus.STATUS_FAIL:
                        view.hideLoadding();
                        view.showMsg(status.getMessage());
                        break;
                    case ISuggestStatus.STATUS_DOING:
                        view.showLoadding();
                        break;
                    case ISuggestStatus.STATUS_SUCCESS:
                        view.hideLoadding();
                        view.showMsg(status.getMessage());
                        break;
                    default:
                        break;

                }
            }
        });
    }
}
