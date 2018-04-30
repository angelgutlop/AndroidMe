package com.example.android.android_me.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BodyFragment extends Fragment implements View.OnClickListener {

    List<Integer> bodyItemList = null;
    private Integer nBody = 0;

    private View bodyView;
    private ImageView bodyImageView;


    private static final String itemListID = "id_item_list";
    private static final String nbodyID = "id_body_number";


    public BodyFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            nBody = savedInstanceState.getInt(nbodyID);
            bodyItemList = savedInstanceState.getIntegerArrayList(itemListID);
        }

        bodyView = inflater.inflate(R.layout.fragment_body, container, false);
        bodyImageView = (ImageView) bodyView.findViewById(R.id.body_imageView);

        bodyImageView.setOnClickListener(this);

        setImageView(nBody);

        return bodyView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(nbodyID, nBody);
        outState.putIntegerArrayList(itemListID, (ArrayList<Integer>) bodyItemList);
        super.onSaveInstanceState(outState);
    }


    private void setImageView(int n) {

        if (bodyItemList != null) {
            bodyImageView.setImageResource(bodyItemList.get(n));
        }
    }

    public void setBodyItemList(List<Integer> bodyItemList) {
        this.bodyItemList = bodyItemList;
    }

    public void setDisplayElement(int nBody) {
        this.nBody = nBody;
    }

    public void displayElement(int nelement) {
        this.nBody = nelement;
        setImageView(nelement);
    }

    public Integer getDisplayElement() {
        return this.nBody;
    }

    Handler mHandler = new Handler();

    AtomicInteger cont = new AtomicInteger(0);

    public void displayRandomElement() {


        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    mHandler.postDelayed(this, 50);

                    nBody = getRandomElementIndex();
                    setImageView(nBody);

                    if (cont.incrementAndGet() >= 10) {
                        cont.getAndSet(0);
                        mHandler.removeCallbacks(this);
                    }
                }
            }
        };

        mHandler.post(runnable);

    }


    public void setDisplayElementRandom() {
        this.nBody = getRandomElementIndex();
    }

    public Integer getRandomElementIndex() {

        if (bodyItemList == null) return null;
        Random random = new Random();
        return random.nextInt(bodyItemList.size() - 1);
    }

    public Integer getNextListItemIndex() {
        if (nBody == null) nBody = 0;
        else nBody++;

        if (nBody > bodyItemList.size() - 1) nBody = 0;
        return nBody;
    }

    @Override
    public void onClick(View v) {
        nBody = getNextListItemIndex();
        setImageView(nBody);
    }


//


}
