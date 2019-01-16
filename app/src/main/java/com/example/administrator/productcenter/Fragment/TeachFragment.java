package com.example.administrator.productcenter.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.productcenter.Presenter.TeachPresenter;
import com.example.administrator.productcenter.R;
import com.example.administrator.productcenter.View.HorizontalViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TeachFragment extends Fragment {
    @BindView(R.id.viewpager_robot)
    HorizontalViewPager viewPager;
    private TeachPresenter presenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teach,null);

        init(view);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        return view;
    }

    private void init(View view){

        unbinder = ButterKnife.bind(this,view);

        presenter = new TeachPresenter(getContext(),getActivity());

        presenter.initViewPager(viewPager);
    }

    public void slideLeft() {
        presenter.slideLeft();
    }

    public void slideRight() {
        presenter.slideRight();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
