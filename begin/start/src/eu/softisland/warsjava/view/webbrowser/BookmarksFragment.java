package eu.softisland.warsjava.view.webbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.model.webbrowser.Bookmark;
import eu.softisland.warsjava.webbrowser.BookmarksAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment {

    private static List<Bookmark> linkDataList = new ArrayList<Bookmark>();
    private BookmarksAdapter bookmarksAdapter;

    static {
        linkDataList.add(new Bookmark("My blog :)", "http://newbility.blogspot.com/"));
        linkDataList.add(new Bookmark("Touk - blog", "http://touk.pl/blog"));
        linkDataList.add(new Bookmark("Android", "http://www.android.com"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookmarks, container, false);
        ListView listView = (ListView) view.findViewById(R.id.urls);
        bookmarksAdapter = new BookmarksAdapter(linkDataList, getActivity());
        listView.setAdapter(bookmarksAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                Bookmark data = bookmarksAdapter.getItem(position);
                ((ChangeLinkListener) getActivity()).onLinkChange(data.getLink());
            }

        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        if (! (activity instanceof ChangeLinkListener) )
            throw new RuntimeException("Parent activite must implement ChangeLinkListenerInterface");
        super.onAttach(activity);
    }

    public interface ChangeLinkListener {
        public void onLinkChange(String link);
    }
}
