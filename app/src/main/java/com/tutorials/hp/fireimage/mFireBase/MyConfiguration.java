package com.tutorials.hp.fireimage.mFireBase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Oclemy on 12/11/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyConfiguration {

    /*
    BUCKET ERFERENCES
     */
    public static String  BASE_BUCKET_NAME="gs://camposha-info.appspot.com";
    public static String  DESTINATION_FOLDER_NAME="Galileo";
    public static String  IMAGE_NAME="Enterprise.jpg";

    private static StorageReference baseBucket,folderReference,imageReference;


    /*
    INITIALIZE STORAGE BUCKETS
     */
    private static void initializeFirebase()
    {
        baseBucket = FirebaseStorage.getInstance().getReferenceFromUrl(BASE_BUCKET_NAME);
        folderReference=baseBucket.child(DESTINATION_FOLDER_NAME);
        imageReference=folderReference.child(IMAGE_NAME);
    }

    /*
    RETURN IMAGE REFERENCE
     */
    public static StorageReference getImageReference()
    {
        initializeFirebase();
        return imageReference;
    }



}
