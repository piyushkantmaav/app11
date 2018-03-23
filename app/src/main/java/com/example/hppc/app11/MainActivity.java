package com.example.hppc.app11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int player = 0;
    int[] blocks = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active = true;

    public void fillBox(View view){
        ImageView img = (ImageView) view;

        int curBlock = Integer.parseInt(img.getTag().toString());
        if(blocks[curBlock]==-1&&active){
            blocks[curBlock]=player;
            img.setTranslationY(-1000);
            // System.out.println(img.getTag().toString());
            if(player==0){
                img.setImageResource(R.drawable.cross);
                player=1;

            }else{
                img.setImageResource(R.drawable.zero);
                player=0;

            }
            img.animate().translationYBy(1000).setDuration(1000);


            for(int[] x : wins){

                if(blocks[x[0]]==blocks[x[1]]&&blocks[x[1]]==blocks[x[2]]&&blocks[x[0]]!=-1){
                   // System.out.println(x[0]+"-"+x[1]+"-"+x[2]);
                    active = false;
                    String winner = "Player 2";
                    if(blocks[x[0]]==0){
                        winner = "Player 1";
                    }
                    TextView msgText = findViewById(R.id.msgText);
                    msgText.setText(winner+" won the Game");

                    LinearLayout msgBox = findViewById(R.id.msgBox);
                    msgBox.setVisibility(View.VISIBLE);
                }else{
                    boolean draw = true;

                    for(int i =0; i<blocks.length;i++){
                        if(blocks[i]==-1){
                            draw = false;
                        }
                    }
                    if(draw){
                        TextView msgText = findViewById(R.id.msgText);
                        msgText.setText("Match Draw");

                        LinearLayout msgBox = findViewById(R.id.msgBox);
                        msgBox.setVisibility(View.VISIBLE);

                    }
                }
            }
        }

    }
    public void playAgain(View view){
        active = true;
        LinearLayout msgBox = findViewById(R.id.msgBox);
        msgBox.setVisibility(View.INVISIBLE);
        for(int i = 0;i<blocks.length;i++){
            blocks[i] = -1;
        }
        GridLayout gridBox = findViewById(R.id.gridBox);
        for(int i = 0;i<gridBox.getChildCount();i++){
            ((ImageView)gridBox.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
