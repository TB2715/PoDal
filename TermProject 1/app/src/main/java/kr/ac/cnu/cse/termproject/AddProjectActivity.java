package kr.ac.cnu.cse.termproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddProjectActivity extends AppCompatActivity {
    ActionBar abar;
    Button posterAddButton, addProjectButton;
    TextView posterAddText;
    public static final int REQUEST_CODE_POSTER = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        abar = this.getSupportActionBar();
        abar.show();
        abar.setTitle("프로젝트 등록");

        posterAddButton = (Button)findViewById(R.id.posterAddButton);
        posterAddText = (TextView)findViewById(R.id.posterAddText);
        addProjectButton = (Button)findViewById(R.id.addProjectButton);

        posterAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);

                i.setType("image/*");
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivityForResult(i.createChooser(i,"Open"),REQUEST_CODE_POSTER);
            }
        });

        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_POSTER){
                if (data != null){
                    Bundle bundle = data.getExtras();
                    String path = data.getData().getPath();
                    String name = data.getData().getLastPathSegment();
                    Toast.makeText(getApplicationContext(),path,Toast.LENGTH_LONG).show();
                    posterAddText.setText(name);
                }
            }
        }
    }
}
