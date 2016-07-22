package pl.droidsonroids.hodor.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import pl.droidsonroids.hodor.HodorApplication;

public class HodorInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        HodorApplication.getInstance().getDatabaseHelper().updateUserToken(refreshedToken);
    }
}
