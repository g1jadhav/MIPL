package mahyco.mipl.nxg;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import mahyco.mipl.nxg.util.BaseActivity;
import mahyco.mipl.nxg.util.Preferences;
import mahyco.mipl.nxg.view.login.Login;

public class Splash extends BaseActivity {

    private Context mContext;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void init() {
        mContext = this;

        Animation mTopAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        Animation mBottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        ImageView mLogoImageView = findViewById(R.id.logo_imageview);
        ImageView mAppNameImageView = findViewById(R.id.app_name_imageview);

        mLogoImageView.setAnimation(mTopAnim);
        mAppNameImageView.setAnimation(mBottomAnim);

        new Handler().postDelayed(() -> {
            if (Preferences.get(mContext, Preferences.USER_ID) != null && !Preferences.get(mContext, Preferences.USER_ID).trim().equals("")) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(mContext, Login.class);
                startActivity(intent);
                finish();
            }
        }, 2500);
    }
}
