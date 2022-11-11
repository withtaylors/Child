package com.example.aemotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.aemotion.R;

import org.w3c.dom.Text;

public class Fragment_5 extends Fragment {

    TextView five;
    int CheckON2, CheckON3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frame_5, container, false);

        CheckON2= VO.getCheckON2();
        five = (TextView) rootView.findViewById(R.id.five);

        if(CheckON2==2) {
            five.setBackgroundResource(R.drawable.sad_learn5);
        }
        else if(CheckON2==3){
            five.setBackgroundResource(R.drawable.surprise_learn5);
        }
        else if (CheckON2==4){
            five.setBackgroundResource(R.drawable.angry_learn5);
        }
        return rootView;
    }

}