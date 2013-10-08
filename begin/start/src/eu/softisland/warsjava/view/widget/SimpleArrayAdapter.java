package eu.softisland.warsjava.view.widget;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

public class SimpleArrayAdapter extends ArrayAdapter<String> {

    private static int counter = 0;
    private static int ROW_LAYOUT = android.R.layout.simple_list_item_1;
    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
    List<String> items;

    public SimpleArrayAdapter(Context context, List<String> items) {
        super(context, ROW_LAYOUT, items);
        this.items = items;
        for (int i = 0; i < items.size(); ++i) {
            mIdMap.put(items.get(i), counter++);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public void addItem(String item) {
        mIdMap.put(item, counter++);
        items.add(item);
        notifyDataSetChanged();
    }
}

