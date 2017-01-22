package com.tutorials.hp.fireimage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tutorials.hp.fireimage.mFireBase.ImageUploader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageView img= (ImageView) findViewById(R.id.img);
        Button uploadBtn= (Button) findViewById(R.id.uploadBtn);
        Button refreshBtn= (Button) findViewById(R.id.refreshBtn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //upload the image
                new ImageUploader(MainActivity.this,img).uploadFromImageView();
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reload the initial image to imageview
                img.setImageResource(R.drawable.enterprise);
            }
        });

    }

}
