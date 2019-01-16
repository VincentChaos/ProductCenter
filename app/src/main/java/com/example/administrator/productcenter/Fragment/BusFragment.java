package com.example.administrator.productcenter.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.productcenter.Presenter.BusPresenter;
import com.example.administrator.productcenter.R;
import com.example.administrator.productcenter.View.HorizontalViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BusFragment extends Fragment {
    @BindView(R.id.viewpager_robot)
    HorizontalViewPager viewPager;
    private BusPresenter presenter;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bus,null);

        init(view);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(View view) {

        unbinder = ButterKnife.bind(this,view);

        presenter = new BusPresenter(getContext(), getActivity());

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
