package com.meimei.meimusic.http;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.entity.Song;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 梅梅 on 2017/5/2.
 */
public class OkHttpUtil {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    public static void requestForGet(String url, final OnSongUrlCallback callBack) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent","user-agent:Mozilla / 5.0(Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                boolean b = response.isSuccessful();

                if (response.isSuccessful()){
                    String s = response.body().string();

                    JsonParser parser = new JsonParser();
                    JsonElement element =parser.parse(s);
                    JsonArray array = element.getAsJsonObject()
                            .get("songurl")
                            .getAsJsonObject()
                            .get("url")
                            .getAsJsonArray();
                    Song.UrlInfo urlInfo = MyApplication.getGsonInstance().fromJson(array.get(0),Song.UrlInfo.class);

                    callBack.onSuccess(urlInfo);
                }

            }
        });
    }

    public interface OnSongUrlCallback{
        void onSuccess(Song.UrlInfo urlInfo);
        void onFailure(String failure);
    }

}
