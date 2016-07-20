package pl.droidsonroids.yo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import pl.droidsonroids.yo.Constants;
import pl.droidsonroids.yo.MainActivity;
import pl.droidsonroids.yo.R;
import pl.droidsonroids.yo.User;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_login) EditText mEditTextLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {
        final ProgressDialog loginProgressDialog = ProgressDialog.show(this, "Login", "Login in progress...", true);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String username = mEditTextLogin.getText().toString();

        User user = new User(username, FirebaseInstanceId.getInstance().getToken());
        reference.child(Constants.DB_USER)
                .child(username)
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull final Task<Void> task) {
                        loginProgressDialog.cancel();
                        MainActivity.startActivity(LoginActivity.this);
                    }
                });
    }
}
