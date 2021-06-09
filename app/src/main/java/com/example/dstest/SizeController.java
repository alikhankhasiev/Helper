package com.example.dstest;

import android.content.Context;

class SizeController {
    private static SizeController instance;

    public int size;
    private Context context;

    SizeController()
    {
        context = MainActivity.context;
        size = (Cookie.getInstance().getImageSize());
    }

    public static SizeController getInstance() {
        if(instance==null?true:instance.context!=MainActivity.context) {
            instance = new SizeController();
        }
        return instance;
    }

    public int setSize(int s){
        size = s;
        Cookie.getInstance().setImageSize(s);
        GridViewController.getInstance().setColumnWidth(s+5);
        return s;
    }
    public void update(){
        setSize(size);
    }
}

