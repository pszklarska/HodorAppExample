package pl.droidsonroids.hodor.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.hodor.HodorPreferences;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.model.User;
import pl.droidsonroids.hodor.retrofit.RestAdapter;
import pl.droidsonroids.hodor.util.DatabaseHelper;

public class UsersListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_username) TextView mTextViewUsername;

    private RestAdapter mRestAdapter;
    private HodorPreferences mHodorPreferences;
    private DatabaseHelper mDatabaseHelper;

    public UsersListViewHolder(final View itemView, final RestAdapter restAdapter,
            final HodorPreferences hodorPreferences, final DatabaseHelper databaseHelper) {
        super(itemView);
        mRestAdapter = restAdapter;
        mHodorPreferences = hodorPreferences;
        mDatabaseHelper = databaseHelper;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final String username, final int backgroundColorRes) {
        mTextViewUsername.setText(username);
        mTextViewUsername.setBackgroundColor(ContextCompat.getColor(mTextViewUsername.getContext(),
                backgroundColorRes));

        mDatabaseHelper.getUserFromDatabase(username, this::setOnClickListener);
    }

    private void setOnClickListener(final User friend) {
        mTextViewUsername.setOnClickListener(v -> mRestAdapter.sendPush(friend.getToken(),
                mHodorPreferences.getUsername()));
    }
}
