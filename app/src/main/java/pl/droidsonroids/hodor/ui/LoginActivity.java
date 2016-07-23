package pl.droidsonroids.hodor.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.iid.FirebaseInstanceId;
import pl.droidsonroids.hodor.HodorApplication;
import pl.droidsonroids.hodor.HodorPreferences;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.model.User;
import pl.droidsonroids.hodor.util.DatabaseHelper;
import pl.droidsonroids.hodor.util.GooglePlayUtils;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_login) EditText mEditTextLogin;

    private HodorPreferences mHodorPreferences;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mHodorPreferences = HodorApplication.getInstance().getPreferences();
        mDatabaseHelper = HodorApplication.getInstance().getDatabaseHelper();

        checkIfLoggedIn();
    }

    private void checkIfLoggedIn() {
        if (mHodorPreferences.getUsername() != null) {
            goToMain();
        }
    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {
        if (GooglePlayUtils.isGooglePlayServicesAvailable(this)) {
            final ProgressDialog loginProgressDialog = showProgressDialog();
            final String username = mEditTextLogin.getText().toString();
            User user = new User(username, FirebaseInstanceId.getInstance().getToken());
            mDatabaseHelper.saveUserInDatabase(user, () -> {
                mHodorPreferences.setUsername(username);
                goToMain();
                loginProgressDialog.cancel();
            });
        }
    }

    private ProgressDialog showProgressDialog() {
        return ProgressDialog.show(this,
                getString(R.string.login_dialog_title),
                getString(R.string.login_dialog_msg),
                true);
    }

    private void goToMain() {
        finish();
        MainActivity.startActivity(this);
    }
}
