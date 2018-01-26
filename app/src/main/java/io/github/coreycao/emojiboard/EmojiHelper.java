package io.github.coreycao.emojiboard;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sycao on 26/01/2018.
 * 从资源文件中读取emoji表情列表
 */

public class EmojiHelper {

    private static final String TAG = "EmojiHelper";

    private static final String emojiDeletePath = "file:///android_asset/emoji/img/emoji_delete.png";

    private static final String emojiDeleteName = "[删除]";

    private static final int emojiDeleteId = 0;

    public static final CEmoji emojiDelete = new CEmoji(emojiDeleteId, emojiDeletePath, emojiDeleteName);

    /**
     * 获取全部emojiList
     *
     * @param context
     * @return
     */
    public static List<CEmoji> getEmojiList(Context context) {
        String data = FileUtil.loadAsset(context, "emoji/emojiList.json");
        List<CEmoji> dataList = new ArrayList<>();
        try {
            dataList.addAll(JSON.parseArray(data, CEmoji.class));
        } catch (Exception e) {
            Log.e(TAG, "#getEmojiList:" + e.getMessage());
        }
        return dataList;
    }

    /**
     * 分页获取emojiList
     *
     * @param page     页码，从0开始
     * @param pageSize 单页容量
     * @return
     */
    public static List<CEmoji> getEmojiListByPage(Context context, int page, int pageSize) {
        int listSize = getEmojiList(context).size();
        if ((page + 1) * pageSize > listSize) {
            return getEmojiList(context).subList(page * pageSize, listSize);
        } else {
            return getEmojiList(context).subList(page * pageSize, (page + 1) * pageSize);
        }
    }

    /**
     * 根据id获取对应的emoji对象
     *
     * @param context 上下文对象
     * @param id      CEmoji id
     * @return
     */
    public static CEmoji getEmojiById(Context context, int id) {
        CEmoji uEmoji = null;
        for (CEmoji emoji : getEmojiList(context)) {
            if (emoji.id == id) {
                uEmoji = emoji;
            }
        }
        return uEmoji;
    }

    /**
     * 根据emojiName获取相应的emoji对象，若不存在该对象则返回null
     *
     * @param context   上下文对象
     * @param emojiName CEmoji Name
     * @return
     */
    public static CEmoji getEmojiByName(Context context, String emojiName) {
        CEmoji cEmoji = null;
        for (CEmoji emoji : getEmojiList(context)) {
            if (emoji.emojiName.equals(emojiName)) {
                cEmoji = emoji;
                break;
            }
        }
        return cEmoji;
    }
}
