package io.github.coreycao.emojiboard;

import java.io.Serializable;

/**
 * Created by sycao on 26/01/2018.
 * emoji表情实体类
 */

public class CEmoji implements Serializable {

    public int id;
    public String emojiPath;
    public String emojiName;

    public CEmoji() {

    }

    public CEmoji(int id, String emojiPath, String emojiName) {
        this.id = id;
        this.emojiPath = emojiPath;
        this.emojiName = emojiName;
    }
}
