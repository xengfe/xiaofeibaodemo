package zkzl.xiaofeibao.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.utils.Const;

public class ImageLoaderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkImageView imgNetWorkView = new NetworkImageView(this);
        ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();
        imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);
    }
}
