package com.example.testbyjava;


import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;


public class DesignKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean isCap = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard,null);
        keyboard = new Keyboard(this,R.xml.inputkeyboard);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onPress(int primaryCode) {

    }


    //set cac su kien Delete, Enter, Uppercase trong virtual keyboard
    @Override
    public void onKey(int i, int[] ints) {

        InputConnection ic = getCurrentInputConnection();
        playClick(i);
        switch (i){
            //DELETE
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1,0);

            //In tat ca ca ki tu trong virtual keyboard lenman hinh
            case Keyboard.KEYCODE_SHIFT :
                isCap = !isCap;
                keyboard.setShifted(isCap);
                kv.invalidateAllKeys();
                break;

            //ENTER
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
                break;

            //UPPERCASE
            default:
                char code =(char)i;
                if(Character.isLetter(code) && isCap){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }

    }


    //ham se tao ra am thanh moi lan nhan phim
    private void playClick(int i){

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
