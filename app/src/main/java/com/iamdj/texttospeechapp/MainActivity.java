package com.iamdj.texttospeechapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {
    Button speakBtn;
    EditText speakText;

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakText = (EditText) findViewById(R.id.txtSpeak);
        speakBtn = (Button)findViewById(R.id.btnSpeech);

        textToSpeech = new TextToSpeech(this, this); // for text2speech
        speakBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        textToSpeakMethod();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Error, This Language is not supported", Toast.LENGTH_SHORT).show();
            } else {
                textToSpeakMethod();
            }
        } else {
            Toast.makeText(this, "Error, Failed to Initialize", Toast.LENGTH_SHORT).show();
        }
    }

// Optional Code
//    @Override
//    public void onDestroy() {
//        if (textToSpeech != null) {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
//        super.onDestroy();
//    }

    // Method to perform speak
    private void textToSpeakMethod() {
        String text = speakText.getText().toString();
        if ("".equals(text)) {
            text = "Please enter some text to speak.";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }


}