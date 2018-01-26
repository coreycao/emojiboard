package io.github.coreycao.emojiboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by sycao on 26/01/2018.
 * Fragment to show the emoji GridView.
 */

public class EmojiFragment extends Fragment {

    public final static String KEY_EMOJ_PAGE = "key_emoji_page";

    int page = 0;

    int pageSize = 23;

    private EmojiClickListener emojiClickListener;

    GridView gvEmojiGrid;

    public static EmojiFragment newInstance(int page) {
        EmojiFragment fragment = new EmojiFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_EMOJ_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(KEY_EMOJ_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emoji, container, false);

        gvEmojiGrid = view.findViewById(R.id.grid_view_emoji);

        EmojiGridAdapter emojiGridAdapter = new EmojiGridAdapter(getActivity(),
                EmojiHelper.getEmojiListByPage(getActivity(), page, pageSize));
        gvEmojiGrid.setAdapter(emojiGridAdapter);
        gvEmojiGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (emojiClickListener == null) return;
                performEmojiClicked(id);

            }
        });

        return view;
    }

    private void performEmojiClicked(long id) {
        if (id == 0L) {
            emojiClickListener.onClicked(EmojiHelper.emojiDelete);
        } else {
            emojiClickListener.onClicked(EmojiHelper.getEmojiById(getActivity(), (int) id));
        }
    }

    public EmojiFragment setEmojiClickListener(EmojiClickListener emojiClickListener) {
        this.emojiClickListener = emojiClickListener;
        return this;
    }

    public interface EmojiClickListener {
        public void onClicked(CEmoji uEmoji);
    }

}
