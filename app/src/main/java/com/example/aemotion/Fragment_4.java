package com.example.aemotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.aemotion.R;

public class Fragment_4 extends Fragment {

    TextView four;
    int CheckON2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frame_4, container, false);

        CheckON2= VO.getCheckON2();
        four = (TextView) rootView.findViewById(R.id.four);

        if(CheckON2==2) {
            four.setBackgroundResource(R.drawable.sad_learn4);
        }
        else if(CheckON2==3){
            four.setBackgroundResource(R.drawable.surprise_learn4);
        }
        else if (CheckON2==4){
            four.setBackgroundResource(R.drawable.angry_learn4);
        }

        return rootView;
    }

}