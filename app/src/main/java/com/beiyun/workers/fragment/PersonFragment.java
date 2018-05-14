package com.beiyun.workers.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心
 */
public class PersonFragment extends BaseFragment {


    @BindView(R.id.person_account)
    TextView personAccount;
    @BindView(R.id.person_update_password)
    TextView personUpdatePassword;
    @BindView(R.id.person_sim_card)
    TextView personSimCard;
    @BindView(R.id.person_money)
    TextView personMoney;
    @BindView(R.id.person_opinion)
    TextView personOpinion;
    @BindView(R.id.person_caches)
    TextView personCaches;
    @BindView(R.id.person_version)
    TextView personVersion;
    Unbinder unbinder;

    public PersonFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.person_account, R.id.person_update_password, R.id.person_sim_card, R.id.person_money, R.id.person_opinion, R.id.person_caches, R.id.person_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_account:
                break;
            case R.id.person_update_password:
                break;
            case R.id.person_sim_card:
                break;
            case R.id.person_money:
                break;
            case R.id.person_opinion:
                break;
            case R.id.person_caches:
                break;
            case R.id.person_version:
                break;
        }
    }
}
