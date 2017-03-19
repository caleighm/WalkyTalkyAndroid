package com.example.jacobtutu.walkytalky;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp wrapper for HTTP tasks.
 */
class HttpTask {

    private static final String TAG = "HttpTask";

    private static final String DOMAIN = "http://206.87.101.248:8000";
    private static final String ALL_TOURS = "/all-tours";
    private static final String TOUR = "/tour?tourid=";

    final private Context context; // context for accessing SharedPreferences for authorization tokens
    private OkHttpClient client;
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    /**
     * Construct HttpTask.
     * @param context context for accessing SharedPreferences
     */
    HttpTask(Context context) {
        this.context = context;
        client = new OkHttpClient.Builder()
                .build();

    }

    public void getAllTours(final HttpCallback httpCallback) {
        Request.Builder requestBuilder = new Request.Builder();
        final Request request = requestBuilder.url(DOMAIN + ALL_TOURS).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "HTTP GET " + ALL_TOURS + " failure", e);
                httpCallback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "HTTP GET " + ALL_TOURS + " error: " + response);
                    httpCallback.onError(response);
                }
                else {
                    Log.d(TAG, "HTTP GET " + ALL_TOURS + " successful");
                    httpCallback.onSuccess(response);
                }

            }
        });
    }

    public void getPoints(int tourId, final HttpCallback httpCallback) {
        Request.Builder requestBuilder = new Request.Builder();
        final Request request = requestBuilder.url(DOMAIN + TOUR + tourId).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "HTTP GET " + TOUR + " failure", e);
                httpCallback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "HTTP GET " + TOUR + " error: " + response);
                    httpCallback.onError(response);
                }
                else {
                    Log.d(TAG, "HTTP GET " + TOUR + " successful");
                    httpCallback.onSuccess(response);
                }

            }
        });
    }

    /**
     * Interface for callback required by HttpTask methods.
     */
    interface HttpCallback {

        /**
         * Called when the request could not be executed due to cancellation, a connectivity
         * problem or timeout.
         *
         * @param e exception
         */
        void onFailure(Exception e);

        /**
         * Called when server responds but the response was not 2xx.
         * @param response server response
         */
        void onError(Response response);

        /**
         * Called when server responds successfully with a 2xx code.
         * @param response server response
         */
        void onSuccess(Response response);
    }

    /**
     * Interface for getting an HttpTask from an activity.
     */
    interface HttpActivity {
        HttpTask getHttpTask();
        JSONObject getUserInfo();
    }


}

