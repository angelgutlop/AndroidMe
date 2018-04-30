/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.android_me.Fragments.BodyFragment;
import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {
//Estas variables se reinicializan al girar

    private BodyFragment headFragment;
    private BodyFragment bodyFragment;
    private BodyFragment legsFragment;

    private final String headIdTag = "headid";
    private final String bodyIdTag = "bodyid";
    private final String leggsIdTag = "legid";

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        fragmentManager = getSupportFragmentManager();


        headFragment = (BodyFragment) fragmentManager.findFragmentByTag(headIdTag);
        bodyFragment = (BodyFragment) fragmentManager.findFragmentByTag(bodyIdTag);
        legsFragment = (BodyFragment) fragmentManager.findFragmentByTag(leggsIdTag);

        int headIndex = 0;
        int bodyIndex = 0;
        int legsIndex = 0;


        Intent intent = getIntent();
        if (intent != null) {

            String key = getResources().getString(R.string.headIndex);
            if (intent.hasExtra(key)) headIndex = intent.getIntExtra(key, 0);

            key = getResources().getString(R.string.bodyIndex);
            if (intent.hasExtra(key)) bodyIndex = intent.getIntExtra(key, 0);

            key = getResources().getString(R.string.legsIndex);
            if (intent.hasExtra(key)) legsIndex = intent.getIntExtra(key, 0);

        }

        if (headFragment == null) {

            headFragment = new BodyFragment();
            bodyFragment = new BodyFragment();
            legsFragment = new BodyFragment();

            headFragment.setBodyItemList(AndroidImageAssets.getHeads());
            // headFragment.setDisplayElementRandom();
            headFragment.setDisplayElement(headIndex);

            bodyFragment.setBodyItemList(AndroidImageAssets.getBodies());
            //bodyFragment.setDisplayElementRandom();
            bodyFragment.setDisplayElement(bodyIndex);

            legsFragment.setBodyItemList(AndroidImageAssets.getLegs());
            //legsFragment.setDisplayElementRandom();
            legsFragment.setDisplayElement(legsIndex);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.headContainer, headFragment, headIdTag);
            fragmentTransaction.add(R.id.bodyContainer, bodyFragment, bodyIdTag);
            fragmentTransaction.add(R.id.legsContainer, legsFragment, leggsIdTag);

            fragmentTransaction.commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(headIdTag, headFragment.getDisplayElement());
        outState.putInt(bodyIdTag, bodyFragment.getDisplayElement());
        outState.putInt(leggsIdTag, legsFragment.getDisplayElement());


        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.randomButton: {
                this.generateRandomCharacter();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateRandomCharacter() {
        headFragment.displayRandomElement();
        bodyFragment.displayRandomElement();
        legsFragment.displayRandomElement();
    }


}
