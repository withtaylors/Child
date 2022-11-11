package com.example.aemotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_3 extends Fragment {
    TextView three;
    int CheckON2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frame_3, container, false);
        CheckON2= VO.getCheckON2();
        three = (TextView) rootView.findViewById(R.id.three);

        if(CheckON2==2) {
            three.setBackgroundResource(R.drawable.sad_learn3);
        }
        else if(CheckON2==3){
            three.setBackgroundResource(R.drawable.surprise_learn3);
        }
        else if (CheckON2==4){
            three.setBackgroundResource(R.drawable.angry_learn3);
        }
        return rootView;
    }

}