package com.tutorials.hp.fireimage.mFireBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.tutorials.hp.fireimage.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by Oclemy on 12/11/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 *
 * READ AN IMAGE BYTES FROM IMAGEVIEW AND UPLOAD IT TO FIREBASE
 */
public class ImageUploader {

    Context c;
    ImageView img;
    ProgressDialog pd;

    /*
    CONSTRUCTOR
     */
    public ImageUploader(Context c,ImageView img) {
        this.c = c;
        this.img=img;
        this.pd = new ProgressDialog(c);
    }


    /*
    1.GET RAW BYTES FROM AN IMAGEVIEW
    2.RETURN IT AS BYTE ARRAY
     */
    private byte[] getRawImageBytes()
    {
        img.setDrawingCacheEnabled(true);
        img.buildDrawingCache();

        Bitmap bm=img.getDrawingCache();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

        return outputStream.toByteArray();
    }

    /*
    SHOW PROGRESS DIALOG
     */
    private void showProgressDialog()
    {
        pd.setTitle("Firebase Image Upload");
        pd.setMessage("Hey Uploading.......Please wait");
        pd.show();
    }

    /*
    DISMISS PROGRESS DIALOG
     */
    private void dismissProgressDialog()
    {
        if(pd.isShowing())
        {
            pd.dismiss();
        }
    }

    /*
    UPLAOD THE IMAGEVIEW TO FIREBASE STORAGE
     */
    public void uploadFromImageView()
    {
        this.showProgressDialog();
        byte[] rawImageBytes=getRawImageBytes();

        UploadTask uploadTask=MyConfiguration.getImageReference().putBytes(rawImageBytes);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //failure
                dismissProgressDialog();
                String error=String.format("FAILURE : %s", e.getMessage());
                Toast.makeText(c, error, Toast.LENGTH_LONG).show();
            }

        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //success
                dismissProgressDialog();
                if(taskSnapshot != null)
                {
                    String urlSnapshot=String.format("SUCCESS : %s",taskSnapshot.getDownloadUrl().toString());
                    Toast.makeText(c, urlSnapshot, Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(c, "SUCCESS but we've received null response.", Toast.LENGTH_LONG).show();

                }
                img.setImageResource(R.drawable.placeholder);

            }
        });

    }



}
