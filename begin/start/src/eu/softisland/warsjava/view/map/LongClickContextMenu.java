package eu.softisland.warsjava.view.map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LongClickContextMenu {

    private AlertDialog dialog;

    private Map<MenuItem, OnClickMenuItem> listeners = new HashMap<MenuItem, OnClickMenuItem>();

    public LongClickContextMenu(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(MenuItem.extractMenuItems(), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                MenuItem menuItem = MenuItem.getItem(item);
                OnClickMenuItem onClickMenuItem = listeners.get(menuItem);
                if(onClickMenuItem == null) {
                    throw new RuntimeException("There is no action for " + menuItem);
                }
                onClickMenuItem.onClick();
                dialog.cancel();
            }
        });
        dialog = builder.create();
    }

    public void setOnClickListener(MenuItem menuItem, OnClickMenuItem onClickMenuItem) {
        listeners.put(menuItem, onClickMenuItem);
    }

    public void show() {
        dialog.show();
    }

    public interface OnClickMenuItem {
        public void onClick();
    }

    public enum MenuItem {
        PUT_LANDMARK("Postaw znacznik");

        private String title;

        private MenuItem(String title) {
            this.title = title;
        }


        public static CharSequence[] extractMenuItems() {
            ArrayList<String> items = new ArrayList<String>();
            for(MenuItem menuItem: MenuItem.values()) {
                items.add(menuItem.title);
            }
            return  items.toArray(new String[items.size()]);
        }

        public static MenuItem getItem(int item) {
            return MenuItem.values()[item];
        }
    }
}
