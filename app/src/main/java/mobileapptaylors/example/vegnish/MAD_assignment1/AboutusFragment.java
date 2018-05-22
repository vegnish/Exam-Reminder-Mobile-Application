package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobileapptaylors.example.vegnish.MAD_assignment1.R;

public class AboutusFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mobileapptaylors.example.vegnish.MAD_assignment1.R.layout.fragment_aboutus, container, false);
    }
}
