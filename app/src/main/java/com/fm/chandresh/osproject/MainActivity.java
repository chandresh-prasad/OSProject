package com.fm.chandresh.osproject;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputLayout frameSizeTextInputLayout, pagesTextInputLayout;
    EditText frameSizeEditText, pagesEditText;
    Button processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setUpButtonClick();
    }

    private void setUpViews() {
        frameSizeTextInputLayout = (TextInputLayout) findViewById(R.id.frameSizeTextInputLayout);
        pagesTextInputLayout = (TextInputLayout) findViewById(R.id.pagesTextInputLayout);
        frameSizeEditText = (EditText) findViewById(R.id.frameSizeEditText);
        pagesEditText = (EditText) findViewById(R.id.pagesEditText);
        processButton = (Button) findViewById(R.id.processButton);
    }

    private void setUpButtonClick() {
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(areAllFieldsValid()){
                    int frameSize = Integer.parseInt(frameSizeEditText.getText().toString());
                    String pages = pagesEditText.getText().toString().replaceAll("\\s+","").trim();

                    ProcessingActivity.actionProcess(MainActivity.this, frameSize, pages);
                }
            }
        });
    }

    private boolean areAllFieldsValid() {
        boolean returnValue = true;

        String frameSize = frameSizeEditText.getText().toString();
        if (frameSize.length() == 0) {
            frameSizeTextInputLayout.setError("Please enter valid Frame Size");
            returnValue = false;
        } else {
            try {
                int size = Integer.parseInt(frameSize);
                if (size <= 0) {
                    frameSizeTextInputLayout.setError("Please enter valid Frame Size");
                    returnValue = false;
                }
            } catch (NumberFormatException e) {
                frameSizeTextInputLayout.setError("Please enter numeric value");
                returnValue = false;
            }
        }
        if (returnValue)
            frameSizeTextInputLayout.setError(null);

        String pages = pagesEditText.getText().toString();
        pages = pages.replaceAll("\\s+","").trim();    // removing all spaces(if entered);

        if (pages.length() == 0) {
            pagesTextInputLayout.setError("Please enter valid input Ex: 7,0,1");
            returnValue = false;
        } else {
            String PATTERN = "[0-9, /,]+";  // this regular expression checks if string has only number and comma
            if (!pages.matches(PATTERN)) {
                pagesTextInputLayout.setError("Please enter valid input Ex: 7,0,1");
                returnValue = false;
            } else if (pages.startsWith(",") || pages.endsWith(",")) {
                pagesTextInputLayout.setError("Please enter valid input Ex: 7,0,1");
                returnValue = false;
            }
        }

        if (returnValue)
            pagesTextInputLayout.setError(null);

        return returnValue;
    }
}
