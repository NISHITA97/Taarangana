package com.example.admin.taarangana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {
    TextView score;
    ImageView i1,i2,i3,i4,i5;
    Button left,right;
    boolean state5;
    boolean select,selectp;
    int cn=0;
    int correct =0,miss=-1,wrong=0;
    int arrows[]={R.drawable.arrow1,R.drawable.arrow2};
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score=(TextView) findViewById(R.id.score);
        left=(Button)findViewById(R.id.left);
        right=(Button)findViewById(R.id.right);
        state5=false;
        r=new Random();
        i1=(ImageView)findViewById(R.id.img1);
        i2=(ImageView)findViewById(R.id.img2);
        i3=(ImageView)findViewById(R.id.img3);
        i4=(ImageView)findViewById(R.id.img4);
        i5=(ImageView)findViewById(R.id.img5);
        left.setEnabled(false);
        right.setEnabled(false);
    }

    public void setimg()
    {
        r=new Random();
        boolean a1 = r.nextBoolean();
        if (a1) {
            i1.setImageResource(arrows[1]);
            i2.setImageResource(arrows[1]);
            i4.setImageResource(arrows[1]);
            i5.setImageResource(arrows[1]);

        }else {
            i1.setImageResource(arrows[0]);
            i2.setImageResource(arrows[0]);
            i4.setImageResource(arrows[0]);
            i5.setImageResource(arrows[0]);
        }
        a1=r.nextBoolean();
        if(a1) {
            i3.setImageResource(arrows[1]);
            state5=true;
        }else {
            i3.setImageResource(arrows[0]);
            state5=false;
        }
        if(cn<=2)
        {
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setimg();
                }
            }, 1500);
            cn++;
            if (!select)
                miss++;
            updateUI();
            selectp=select;
            select=false;
            left.setEnabled(true);
            right.setEnabled(true);
        }
        else{

            if(correct>=2) {
                score.setText("Winn!!!!");
                Intent i=new Intent(this,Login.class);
                i.putExtra("pass",true);
                startActivity(i);
            }
            else
            {
                Toast t=Toast.makeText(getApplicationContext(),"You cannot register. Try Again",Toast.LENGTH_LONG);
                t.show();
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
            }
        }
    }
    public void leftm(View view) {
        if(state5)//state 5 is right
            wrong++;
        else
            correct++;
        select=true;
        left.setEnabled(false);
        right.setEnabled(false);
        updateUI();


    }

    public void rightm(View view) {
        if(state5)
            correct++;
        else
            wrong++;
        select=true;
        updateUI();
        right.setEnabled(false);
        left.setEnabled(false);
    }

    public void updateUI()
    {
        score.setText("Chance="+cn+" \n Correct="+correct+" \n Wrong=" + wrong+" \n Miss="+miss);
    }
    public void Shuffle(View view) {
        left.setEnabled(true);
        right.setEnabled(true);
        setimg();
    }
}
