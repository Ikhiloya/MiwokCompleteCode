package com.example.android.miwok;

/**
 * represents a vocabulary Word that the user wants to learn
 * It contains a default translation and a Miwok translation for that Word
 * Created by user on 6/15/2017.
 */
public class Word {
    /**
     * Default translation for the Word
     **/
    private String mDefaultTranslation;

    /**
     * Miwok translation for the Word
     **/
    private String mMiwokTranslation;

    /**
     * constant to check the value of the image resource ids
     */
    private static final int NO_IMAGE_PROVIDED =-1;

    /**
     * Image resource variable
     **/
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    /**
     * Audio resource variable
     **/
    private int mAudioResourceId;


    /**
     * Constructor that takes the Default, Miwok languages and the mediaPlayerResourceId as args
     *
     * @param defaultTranslation is the English Translation
     * @param miwokTranslation   is the miwok translation
     * @param audioResourceId is the id for the mediaplayer in the raw folder
     */
    public Word(String defaultTranslation, String miwokTranslation, int  audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId =audioResourceId;
    }


    /**
     * Constructor that takes both the Default, Miwok languages   and image resource Id  as args
     *
     * @param defaultTranslation is the English Translation
     * @param miwokTranslation   is the miwok translation
     * @param imageResourceId    is the resource id for the images stored in the drawable folder associated with the word
     * @param audioResourceId is the id for the mediaplayer in the raw folder
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int  audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId =audioResourceId;
    }


    /**
     * gets the default Translation word
     *
     * @return the  defaultTranslation;
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * gets the default Miwok word
     *
     * @return the MiwokTranslation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }


    /**
     * gets the image resource id word
     *
     * @return the ImageResourceId
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     *
     * @return true if @mImageResourceId is not equal to 1
     */
    public boolean hasImage(){
        return mImageResourceId !=NO_IMAGE_PROVIDED;
    }

    /**
     *
     * @return the audioResourceId
     */
    public int getAudioResourceId(){return mAudioResourceId;}
}
