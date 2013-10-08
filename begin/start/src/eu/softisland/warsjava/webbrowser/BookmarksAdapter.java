package eu.softisland.warsjava.webbrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.model.webbrowser.Bookmark;

import java.util.List;

public class BookmarksAdapter extends ArrayAdapter<Bookmark>{

    private Context ctx;
    private List<Bookmark> linkDataList;

    public BookmarksAdapter(List<Bookmark> linkDataList, Context ctx) {
        super(ctx, R.layout.bookmark_row,linkDataList);
        this.ctx = ctx;
        this.linkDataList = linkDataList;
    }

    @Override
    public int getCount() {
        return linkDataList.size();
    }

    @Override
    public Bookmark getItem(int position) {
        return linkDataList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return linkDataList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bookmark_row, null);
        }

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView linkTextVIew = (TextView) view.findViewById(R.id.link);

        Bookmark bookmark = linkDataList.get(position);
        nameTextView.setText(bookmark.getName());
        linkTextVIew.setText(bookmark.getLink());

        return view;
    }


}
