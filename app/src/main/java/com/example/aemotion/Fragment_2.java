package com.example.aemotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_2 extends Fragment {

    TextView two;
    int CheckON2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frame_2, container, false);
        CheckON2= VO.getCheckON2();
        two = (TextView) rootView.findViewById(R.id.two);

        if(CheckON2==2) {
            two.setBackgroundResource(R.drawable.sad_learn2);
        }
        else if(CheckON2==3){
            two.setBackgroundResource(R.drawable.surprise_learn2);
        }
        else if (CheckON2==4){
            two.setBackgroundResource(R.drawable.angry_learn2);
        }

        return rootView;
    }

}