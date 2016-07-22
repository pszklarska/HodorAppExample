package pl.droidsonroids.hodor.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import pl.droidsonroids.hodor.Constants;
import pl.droidsonroids.hodor.HodorApplication;
import pl.droidsonroids.hodor.HodorPreferences;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.model.User;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_login) EditText mEditTextLogin;

    private HodorPreferences mHodorPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mHodorPreferences = HodorApplication.getInstance().getPreferences();

        checkIfLoggedIn();
    }

    private void checkIfLoggedIn() {
        if (mHodorPreferences.getUsername() != null) {
            goToMain();
        }
    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {
        final ProgressDialog loginProgressDialog = ProgressDialog.show(this,
                getString(R.string.login_dialog_title),
                getString(R.string.login_dialog_msg),
                true);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        final String username = mEditTextLogin.getText().toString();

        User user = new User(username, FirebaseInstanceId.getInstance().getToken());
        reference.child(Constants.DB_USER).child(username).setValue(user).addOnCompleteListener(task -> {
            mHodorPreferences.setUsername(username);
            goToMain();
            loginProgressDialog.cancel();
        });
    }

    private void goToMain() {
        finish();
        MainActivity.startActivity(this);
    }
}
