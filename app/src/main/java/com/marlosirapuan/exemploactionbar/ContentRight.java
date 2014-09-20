package com.marlosirapuan.exemploactionbar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by marlos on 9/19/14.
 */
public class ContentRight extends Fragment {

    public ContentRight() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_right, container, false);
    }
}
