package io.github.coreycao.emojiboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;

/**
 * Created by sycao on 26/01/2018.
 * Demo how the emojiboard looks like and how it works.
 */

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et_main);

        viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return EmojiFragment.newInstance(position).setEmojiClickListener(new EmojiFragment.EmojiClickListener() {
                    @Override
                    public void onClicked(CEmoji cEmoji) {
                        if (cEmoji == null) return;
                        else if (cEmoji.id == 0) {
                            deleteText();
                        } else {
                            insertText(cEmoji.emojiName);
                        }
                    }
                });
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    /**
     * 在EditText的光标处插入文字
     */
    private void insertText(String text) {
        if (editText == null) return;
        int index = editText.getSelectionStart();//获取光标所在位置
        Editable edit = editText.getEditableText();//获取EditText的文字
        if (index < 0 || index >= edit.length()) {
            edit.append(text);
        } else {
            edit.insert(index, text);//光标所在位置插入文字
        }
    }

    /**
     * 删除EditText光标前的内容
     */
    private void deleteText() {
        if (editText == null) return;
        int index = editText.getSelectionStart();
        Editable editable = editText.getEditableText();
        if (index > 0) {
            editable.delete(index - 1, index);
        }
    }
}
