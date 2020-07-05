package com.wolve.uberclone.includes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wolve.uberclone.R;

public class MyToolbar {
    public static void Show(AppCompatActivity activity, String title, Boolean upButton){
        Toolbar mToolBar = activity.findViewById(R.id.mtoolbar);
        activity.setSupportActionBar(mToolBar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
