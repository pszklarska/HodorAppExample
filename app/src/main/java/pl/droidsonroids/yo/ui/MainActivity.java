package pl.droidsonroids.yo.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.yo.Constants;
import pl.droidsonroids.yo.R;
import pl.droidsonroids.yo.adapter.UsersListAdapter;
import pl.droidsonroids.yo.model.User;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_users) RecyclerView mRecyclerViewUsers;

    private List<User> mUserList = new ArrayList<>();
    private UsersListAdapter mAdapter;

    public static void startActivity(final Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setView();
    }

    private void setView() {
        mRecyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UsersListAdapter(mUserList);
        mRecyclerViewUsers.setAdapter(mAdapter);
    }

    @OnClick(R.id.fab)
    public void onAddUserClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add user");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = input.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child(Constants.DB_USER)
                        .child(username)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(final DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                mUserList.add(user);
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(final DatabaseError databaseError) {

                            }
                        });
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
