package io.github.coreycao.emojiboard;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sycao on 26/01/2018.
 * 操作文件的工具类
 */

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static String loadAsset(Context context, String path) {
        if (context == null || TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(path);
            return readStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String readStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        try {
            StringBuilder builder = new StringBuilder(inputStream.available() + 10);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] data = new char[4096];
            int len = -1;
            while ((len = bufferedReader.read(data)) > 0) {
                builder.append(data, 0, len);
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                Log.e(TAG, "#loadAsset: " + e.getMessage());
            }
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "#loadAsset: " + e.getMessage());
            }
        }

        return "";
    }
}
