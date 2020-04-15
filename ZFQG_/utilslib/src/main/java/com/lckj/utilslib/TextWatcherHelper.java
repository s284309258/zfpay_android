package com.lckj.utilslib;

import android.text.Editable;
import android.widget.TextView;

public class TextWatcherHelper {
    public static void bindView(final boolean isDecimal, final TextView bindBtn, TextView... ets) {
        final boolean[] bs = new boolean[ets.length];
        for (int i = 0; i<ets.length; i++) {
            final int finalI = i;
            bs[finalI] = ets[i].getText().toString().length()>0;
            ets[i].addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().length()>0){
                        if (isDecimal&&Double.valueOf(s.toString())<=0){
                            bs[finalI] = false;
                        }else{
                            bs[finalI] = true;
                        }
                    }else {
                        bs[finalI] = false;
                    }
                    bindBtn.setEnabled(allTrue(bs));
                }
            });
        }

    }

    private static boolean allTrue(boolean[] bs) {
        for (boolean b : bs) {
            if (!b)return false;
        }
        return true;
    }
}
