package kr.ac.cnu.cse.termproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    ActionBar abar;
    Button addUserInfoButton, addFaceButton;
    TextView faceAddText;
    ImageView faceImage;

    static final int REQUEST_CODE_FACE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //setContentView(R.layout.activity_edit_userinfo);

        abar = this.getSupportActionBar();
        abar.show();
        abar.setTitle("유저정보");

        addUserInfoButton = (Button)findViewById(R.id.addUserInfoButton);
        addFaceButton = (Button) findViewById(R.id.faceAddButton);
        faceAddText = (TextView)findViewById(R.id.faceAddText);
        faceImage = (ImageView)findViewById(R.id.faceImage);

        addFaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);

                i.setType("image/*");
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivityForResult(i.createChooser(i,"Open"),REQUEST_CODE_FACE);
            }
        });

        addUserInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_FACE){
                if (data != null){
                    Bundle bundle = data.getExtras();
                    String path = data.getData().getPath();
                    String name = data.getData().getLastPathSegment();
                    Uri uri = data.getData();
                    Toast.makeText(getApplicationContext(),path,Toast.LENGTH_LONG).show();
                    faceImage.setImageURI(uri);
                    faceAddText.setText(name);
                }
            }
        }
    }
}
