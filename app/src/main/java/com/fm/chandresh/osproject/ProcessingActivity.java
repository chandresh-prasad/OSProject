package com.fm.chandresh.osproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ProcessingActivity extends AppCompatActivity {

    private static final String FRAME_SIZE = "frameSize";
    private static final String PAGES = "pages";

    public static void actionProcess(Context context, int frameSize, String pages) {
        Intent intent = new Intent(context, ProcessingActivity.class);
        intent.putExtra(FRAME_SIZE, frameSize);
        intent.putExtra(PAGES, pages);
        context.startActivity(intent);
    }

    TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);

        displayText = (TextView) findViewById(R.id.displayText);

        int frameSize = getIntent().getIntExtra(FRAME_SIZE, 3);
        String pages = getIntent().getStringExtra(PAGES);

        String[] pagesString = pages.split(",");

        int[] array = new int[pagesString.length];
        for (int i = 0; i < pagesString.length; i++) {
            array[i] = Integer.parseInt(pagesString[i]);
        }


        FifoAlgorithm algorithm = new FifoAlgorithm(frameSize);
        for (int i = 0; i < array.length; i++) {
            algorithm.insert(array[i]);
            displayPages(algorithm.print());
        }

        displayPages("-----===-----");
        displayPages("HIT : " + algorithm.getHit() + "\nFAULT : " + algorithm.getFault());
        displayPages("-----===-----");
    }

    private void displayPages(String pages) {
        displayText.setText(displayText.getText().toString() + "\n" + pages);
    }


}
