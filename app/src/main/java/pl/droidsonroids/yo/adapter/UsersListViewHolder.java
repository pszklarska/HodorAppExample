package pl.droidsonroids.yo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.yo.R;
import pl.droidsonroids.yo.model.User;

public class UsersListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_username) TextView mTextViewUsername;

    public UsersListViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final User user) {
        mTextViewUsername.setText(user.getUsername());
        mTextViewUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(mTextViewUsername.getContext(), "Device token: " + user.getToken(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
