package com.example.android.miwok;



import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ColorsFragment extends Fragment {
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus while playing a sound file
     */
    private AudioManager mAudioManager;

    //This listens for any change in AudioFocus within the app
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == mAudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == mAudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                        // short amount of time; The AUDIOFOCUS_TRANSIENT_CAN_DUCK case means
                        //our app is allowed to continue playing sound but at a lower volume.
                        //both cases are handled thesame way because our app is playing short files

                        //Pause playback and reset player to the start of the file.
                        //That way, the word is played from the beginning when we resume playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //The AUDIOFOCUS_GAIN case means we have regained focus
                        // and can resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                        // stop playback and cleanup resources
                        releaseMediaPlayer();
                    }
                }
            };
    //create new  global MediaPlayer.OnCompletionListener object
    //This listener gets trigerred when the mediaplayer has completed playing the file
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        //this releases the memory once the audio file  has finished playing
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public ColorsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //create and set up AudioManager to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //TODO: create an ArrayList of Strings to store data of type Word in the custom Word Class

        //The list words is made final so that it can be accessed from the inner class
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow", "topiise", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiite", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        //link the listView with the List item in the xml
        //ListView listView = (ListView)findViewById(R.id.list);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //attach the adapter that has the arrayList of strings to the listView object
        assert listView != null;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //creates new object of the Word class which gets the current position of the item selected on the List
                Word word = words.get(position);
                //method to play the associated media file
                playMedia(word);
            }
        });

       return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * A Helper method to manage and clean media resources
     * it cleans up the media player by releasing its resources
     */
    private void releaseMediaPlayer() {
        //if the media player is not null, then it may be currently playing a sound
        if (mMediaPlayer != null) {
            //Regardless of the current state of the media player, release its resources
            //because we no longer need it
            mMediaPlayer.release();

            //set the media player back to null, for this code
            // setting the variable to null is an easy way to tell that
            //the media player is not configured to play an audio file
            mMediaPlayer = null;

            //Regardless of whether or not we were granted audio focus, abandon it.
            //This also unregisters the AudioFocusListener so we don't get anymore callbacks
            //Abandon audio focus when playback is complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

    /**
     * @param word an object of the Word class, which has the current position of the "words" list
     *             and is used to play the associated audio file
     */
    private void playMedia(Word word) {
        //releases the media player resources before a new media player  is configured
        releaseMediaPlayer();

        //request audio focus for playback
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                //use the music Stream.
                AudioManager.STREAM_MUSIC,
                //Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //we have audio focus now.

            //creates a new object of the mediaPlayer class and calls the getMediaPlayerResourceId method from the Word class
            mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

            //start the audio file
            mMediaPlayer.start();

            //setup a listener on the media player, so that we can stop and release the
            //media player once the sound has finished playing
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }

}
