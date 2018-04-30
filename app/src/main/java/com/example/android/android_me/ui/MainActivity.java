package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.Fragments.BodyFragment;
import com.example.android.android_me.Fragments.MasterListGridFragment;
import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.data.ImageAndroid;

public class MainActivity extends AppCompatActivity implements MasterListGridFragment.OnImageCLickListener {

    int headIndex = 0;
    int bodyIndex = 0;
    int legsIndex = 0;

    private BodyFragment headFragment;
    private BodyFragment bodyFragment;
    private BodyFragment legsFragment;

    private final String headIdTag = "headidMain";
    private final String bodyIdTag = "bodyidMain";
    private final String leggsIdTag = "legidMain";

    Boolean dosPaneles = false;
    FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();


        if (savedInstanceState != null) {
            headIndex = savedInstanceState.getInt(getResources().getString(R.string.headIndex));
            bodyIndex = savedInstanceState.getInt(getResources().getString(R.string.bodyIndex));
            legsIndex = savedInstanceState.getInt(getResources().getString(R.string.legsIndex));
        }

        if (findViewById(R.id.linearLayoutPortrait600dp) != null) {


            dosPaneles = true;


            headFragment = (BodyFragment) fragmentManager.findFragmentByTag(headIdTag);
            bodyFragment = (BodyFragment) fragmentManager.findFragmentByTag(bodyIdTag);
            legsFragment = (BodyFragment) fragmentManager.findFragmentByTag(leggsIdTag);


            if (headFragment == null) {

                headFragment = new BodyFragment();
                bodyFragment = new BodyFragment();
                legsFragment = new BodyFragment();

                headFragment.setBodyItemList(AndroidImageAssets.getHeads());
                headFragment.setDisplayElement(headIndex);

                bodyFragment.setBodyItemList(AndroidImageAssets.getBodies());
                bodyFragment.setDisplayElement(bodyIndex);

                legsFragment.setBodyItemList(AndroidImageAssets.getLegs());
                legsFragment.setDisplayElement(legsIndex);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.headContainer, headFragment, headIdTag);
                fragmentTransaction.add(R.id.bodyContainer, bodyFragment, bodyIdTag);
                fragmentTransaction.add(R.id.legsContainer, legsFragment, leggsIdTag);

                fragmentTransaction.commit();
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(getResources().getString(R.string.headIndex), headIndex);
        outState.putInt(getResources().getString(R.string.bodyIndex), bodyIndex);
        outState.putInt(getResources().getString(R.string.legsIndex), legsIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (dosPaneles) {
            getMenuInflater().inflate(R.menu.options_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.randomButton:
                headFragment.displayRandomElement();
                bodyFragment.displayRandomElement();
                legsFragment.displayRandomElement();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnImageClick(int imagenID) {


        ImageAndroid imageAndroid = AndroidImageAssets.getTypeOfImage_AllArray(imagenID);

        switch (imageAndroid.image_type) {
            case HEAD:
                headIndex = imageAndroid.innerIndex;
                break;
            case BODY:
                bodyIndex = imageAndroid.innerIndex;
                break;
            case LEGS:
                legsIndex = imageAndroid.innerIndex;
                break;
        }

        Bundle bundle = new Bundle();

        bundle.putInt(getResources().getString(R.string.headIndex), headIndex);
        bundle.putInt(getResources().getString(R.string.bodyIndex), bodyIndex);
        bundle.putInt(getResources().getString(R.string.legsIndex), legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        Button nextButton = (Button) findViewById(R.id.nextButton);

        if (!dosPaneles) {

            nextButton.setVisibility(View.VISIBLE);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });


        } else {
            headFragment.displayElement(headIndex);
            bodyFragment.displayElement(bodyIndex);
            legsFragment.displayElement(legsIndex);
            nextButton.setVisibility(View.GONE);
        }

    }
}
