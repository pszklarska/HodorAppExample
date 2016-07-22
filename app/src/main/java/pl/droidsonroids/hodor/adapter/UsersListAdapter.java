package pl.droidsonroids.hodor.adapter;

import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import pl.droidsonroids.hodor.HodorPreferences;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.retrofit.RestAdapter;
import pl.droidsonroids.hodor.util.DatabaseHelper;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListViewHolder> {

    private final List<String> mUserList;
    private RestAdapter mRestAdapter;
    private HodorPreferences mHodorPreferences;
    private DatabaseHelper mDatabaseHelper;

    private int[] colors = {
            R.color.blue, R.color.violet, R.color.green, R.color.turquoise, R.color.orange, R.color.graphite,
            R.color.red
    };

    public UsersListAdapter(final List<String> userList, final RestAdapter restAdapter,
            final HodorPreferences hodorPreferences, final DatabaseHelper databaseHelper) {
        mUserList = userList;
        mRestAdapter = restAdapter;
        mHodorPreferences = hodorPreferences;
        mDatabaseHelper = databaseHelper;
    }

    @Override
    public UsersListViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new UsersListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users, parent, false), mRestAdapter, mHodorPreferences, mDatabaseHelper);
    }

    @Override
    public void onBindViewHolder(final UsersListViewHolder holder, final int position) {
        holder.bindData(mUserList.get(position), getBackgroundColorRes(position));
    }

    @ColorRes
    private int getBackgroundColorRes(final int position) {
        return colors[position % colors.length];
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
