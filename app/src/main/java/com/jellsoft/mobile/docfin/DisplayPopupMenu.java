package com.jellsoft.mobile.docfin;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by atulanand on 8/11/16.
 */
public class DisplayPopupMenu implements View.OnClickListener
{

    Context context;

    public DisplayPopupMenu(Context context)
    {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
// This is an android.support.v7.widget.PopupMenu;
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mi_appointments:
                        //todo
                        return true;

                    case R.id.mi_search:

                        return true;

                    case R.id.mi_settings:

                        return true;

                    default:
                        return true;
                }
            }
        });
        popupMenu.inflate(R.menu.toolbar_menu);
        popupMenu.show();
    }
}
