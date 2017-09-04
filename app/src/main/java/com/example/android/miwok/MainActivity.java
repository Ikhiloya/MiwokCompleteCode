/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

//main Activity implements the TabLayout.OnTabSelectedListener which has three methods to be overridden whenever a TabLayout is used
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    //Global Variables for the Viewpager and the Tab Layout
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Ensures that the tablayout aligns with the parent view and
        //it ensures that the tab layout is shadow effect is not seen
        getSupportActionBar().setElevation(0f);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Find the TabLayout from the activity_main
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        //set the Text colors of the tab layout when it's selected ans when it's not selected
        tabLayout.setTabTextColors(
                //color when the tab is not selected
                ContextCompat.getColor(this, android.R.color.white),
                //color when the tab is selected
                ContextCompat.getColor(this, android.R.color.holo_orange_light));


        // Create an adapter that knows which fragment should be shown on each page
        CategoryPagerAdapter adapter = new CategoryPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        assert viewPager != null;
        viewPager.setAdapter(adapter);

        //sets up the viewpager with the tab layout
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //calls the appropriate page to be displayed  when the user clicks on a tab i.e based on the tab position
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

//
//        //find views that shows all categories
//        TextView numbers = (TextView) findViewById(R.id.numbers);
//        TextView family = (TextView) findViewById(R.id.family);
//        TextView colors = (TextView) findViewById(R.id.colors);
//        TextView phrases = (TextView) findViewById(R.id.phrases);
//
//
//        assert numbers != null;
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //create a new intent to open the numbers activity
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//
//                //start the intent
//                startActivity(numbersIntent);
//
//            }
//        });
//
//        assert family != null;
//        family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //create a new intent to open the numbers activity
//                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
//
//                //start the intent
//                startActivity(familyIntent);
//
//            }
//        });
//
//        assert colors != null;
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //create a new intent to open the numbers activity
//                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
//
//                //start the intent
//                startActivity(colorsIntent);
//
//            }
//        });
//
//
//        assert phrases != null;
//        phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //create a new intent to open the phrases activity
//                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
//
//                //start the intent
//                startActivity(phrasesIntent);
//
//            }
//        });



