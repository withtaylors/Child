package com.example.aemotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_1 extends Fragment {

    TextView one;
    int CheckON2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frame_1, container, false);
        CheckON2= VO.getCheckON2();

        one = (TextView) rootView.findViewById(R.id.one);

        if(CheckON2==2) {
            one.setBackgroundResource(R.drawable.sad_learn);
        }
        else if(CheckON2==3){
            one.setBackgroundResource(R.drawable.surprise_learn);
        }
        else if(CheckON2==4){
            one.setBackgroundResource(R.drawable.angry_learn);
        }
        return rootView;
    }

}