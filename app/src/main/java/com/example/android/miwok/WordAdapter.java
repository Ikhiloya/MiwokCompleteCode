package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * WordAdapter is a custom Adapter that can provide the layout for each layout based
 * on a data source which is a lisit of Word objects
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    /**
     * Resource id for the background color for this list of words
     **/
    private int mColorResourceId;


    /**
     * This is our own constructor(it does not mirror a superclass constructor)
     * The context is used to inflate the layout file and the list is the data
     * we want to populate into the list
     *
     * @param context The current context used to inflate the layout file
     * @param words   A list of Word objects to display in a list
     */


    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        //Here, we initialize the ArrayAdapter's internal storage for the context and the list
        //the second argument is used when the ArrayAdapter is populating a single textview
        //Because this is a custom adapter for two TextViews and an ImageView, the adapter is
        //not going to use this second argument, so it can be any value, here we used 0

        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    /**
         * provides a view for the AdapterView(e.g ListView, GridView, etc)
         *
         * @param position    the adapterView position that is requesting a view
         * @param convertView the recycled view to populate
         * @param parent      the parent ViewGroup that is used for the inflation
         * @return the view for the position on the AdapterView
         */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Get the Word object located at this position in the list
            final Word currentWord = getItem(position);

            //check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if (listItemView == null) {
                //manually converts the xml into view objects
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            //find the TextView in the list_item.xml layout with the ID textview name
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            //get the miwok name from the current Word object and set the text on the miwokTextView
            miwokTextView.setText(currentWord.getMiwokTranslation());

            //find the TextView in the list_item.xml layout with the ID textview name
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            //get the miwok name from the current Word object and set the text on the defaultTextView
            defaultTextView.setText(currentWord.getDefaultTranslation());

            //find the ImageView in the list_item.xml layout with the ID image view name
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
            if (currentWord.hasImage()) {

                //get the image view from the current word obk=ject and set the image on the ImageView
                imageView.setImageResource(currentWord.getImageResourceId());

                //set the view to visible since it will be reused by the recycled view
                imageView.setVisibility(View.VISIBLE);
            } else {
                //set the image view to GONE, i.e it removes the imageview and the space it occupies from the layout
                imageView.setVisibility(View.GONE);

            }

            //Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            //find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            //set the background color of the text container
            textContainer.setBackgroundColor(color);


            //returns a layout with 2 textviews and one imageview
            return listItemView;
    }
}
