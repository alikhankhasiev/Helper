package com.example.dstest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

import com.example.dstest.utils.YandexMetriсaHelper;

public class TTS {
    private final Context context;
    private TextToSpeech tts;
    private static TTS instance;

    public TTS() {
        context = MainActivity.context;
        tts = new TextToSpeech(MainActivity.context, new TextToSpeech.OnInitListener() {
            @Override
            /*public void onInit(int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (!tts.getAvailableLanguages().contains(Locale.getDefault())) {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MainActivity.context);
                        dlgAlert.setMessage(R.string.install_tts);
                        dlgAlert.setTitle(R.string.lang_doesnt_support);
                        dlgAlert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                YandexMetriсaHelper.openTTSInstall();
                                final String appPackageName = "com.google.android.tts";
                                try {
                                    MainActivity.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    MainActivity.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }
                            }
                        });
                        dlgAlert.create().show();

                    }
                }
                tts.setLanguage(Locale.getDefault());
            }*/
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("en");
                    int result = tts.setLanguage(locale);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTs", "Language not supported");
                    }
                }else {
                    Log.e("TTs", "Initialization failed");
                }
            }
        });

    }

    public static TTS getInstance() {
        if(instance==null?true:instance.context!=MainActivity.context){
            instance = new TTS();
        }

        return instance;
    }

    public void speak (String text){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}

