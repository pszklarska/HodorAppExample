package pl.droidsonroids.yo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import pl.droidsonroids.yo.R;
import pl.droidsonroids.yo.model.User;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListViewHolder> {

    private final List<User> mUserList;

    public UsersListAdapter(final List<User> userList) {
        mUserList = userList;
    }

    @Override
    public UsersListViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new UsersListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users, parent, false));
    }

    @Override
    public void onBindViewHolder(final UsersListViewHolder holder, final int position) {
        holder.bindData(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
