package stech.yendee.samaporn.mystech;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by samaporn on 4/6/2017.
 */

public class GetUser extends AsyncTask<Void ,Void , String> {


    private Context context;
        private static final String urlJson = "http://swiftcodingthai.com/tech/php_get_data_pu.php";

    public GetUser(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJson).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("TestV2", "e doIn ==> " + e.toString());
                return null;
            }

    }
}  //Main Class
