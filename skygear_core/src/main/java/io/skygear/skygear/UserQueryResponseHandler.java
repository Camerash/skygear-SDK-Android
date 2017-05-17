package io.skygear.skygear;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Skygear User Query Response Handler.
 */
public abstract class UserQueryResponseHandler implements ResponseHandler {
    /**
     * Query success callback.
     *
     * @param users the users
     */
    public abstract void onQuerySuccess(User[] users);

    /**
     * Query fail callback.
     *
     * @param error the error
     */
    public abstract void onQueryFail(Error error);

    @Override
    public void onSuccess(JSONObject result) {
        try {
            JSONArray data = result.getJSONArray("result");
            int count = data.length();
            User[] users = new User[count];

            for (int idx = 0; idx < count; idx++) {
                JSONObject perUserData = data.getJSONObject(idx).getJSONObject("data");
                User perUser = UserSerializer.deserialize(perUserData);

                users[idx] = perUser;
            }

            this.onQuerySuccess(users);
        } catch (JSONException e) {
            this.onQueryFail(new Error("Malformed server response"));
        }
    }

    @Override
    public void onFail(Error error) {
        this.onQueryFail(error);
    }
}
