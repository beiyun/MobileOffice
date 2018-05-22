package com.beiyun.workers.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseFragment;
import com.beiyun.workers.entity.User;
import com.beiyun.workers.ui.ReportActivity;
import com.beiyun.workers.ui.UpdatePasswordStep2Activity;
import com.beiyun.workers.utils.UpdateManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.beiyun.library.util.Events.clearCache;

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
                updatePassword();
                break;
            case R.id.person_sim_card:
                break;
            case R.id.person_money:
                break;
            case R.id.person_opinion:
                startActivity(new Intent(getActivity(), ReportActivity.class));
                break;
            case R.id.person_caches:
                clearCaches();
                break;
            case R.id.person_version:
                checkVersion();
                break;
                default:
        }
    }

    private void clearCaches() {
        try {
            new MaterialDialog.Builder(getActivity())
                    .title("清除缓存")
                    .content("共检测出缓存文件"+ com.beiyun.library.util.AppCaches.getTotalCacheSize())
                    .positiveText("立即清除")
                    .negativeText("取消")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            com.beiyun.library.util.AppCaches.clearAllCache();
                            dialog.dismiss();
                        }
                    }).build().show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePassword() {
        User user = (User) com.beiyun.library.util.Sps.get(User.class);
        com.beiyun.library.util.Events.post(user.getUserNumber());
        startActivity(new Intent(getActivity(), UpdatePasswordStep2Activity.class));
    }

    private void checkVersion() {
        UpdateManager.init().checkVersion(true);
    }





}
