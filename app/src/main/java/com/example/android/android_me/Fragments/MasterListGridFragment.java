package com.example.android.android_me.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.android.android_me.R;

public class MasterListGridFragment extends Fragment {

    OnImageCLickListener onImageCLickListener;

    public interface OnImageCLickListener {
        void OnImageClick(int imagenID);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            onImageCLickListener = (OnImageCLickListener) context;
        } catch (ClassCastException cse) {
            throw new ClassCastException(context.toString() + " debe implementar OnImageCLickListener.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        MasterListAdapter masterListAdapter = new MasterListAdapter(rootView.getContext());

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayoutGrid);

       
        GridView gridView = (GridView) linearLayout.findViewById(R.id.gridViewBodyParts);
        gridView.setNumColumns(3);
        gridView.setAdapter(masterListAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onImageCLickListener.OnImageClick(position);
            }
        });

        ViewGroup parent = (ViewGroup) linearLayout.getParent();
        if (parent != null) {
            parent.removeView(linearLayout);
        }
        return linearLayout;

    }
}
