package com.beiyun.workers.fragment.searchfragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.util.Events;
import com.beiyun.library.util.Times;
import com.beiyun.library.util.Views;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.interf.IFromView;
import com.beiyun.workers.view.CountEditText;
import com.beiyun.workers.view.FormView;
import com.beiyun.workers.view.TGBasicInfoLayou;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PersonInfoFragment extends BaseSearchFragment {


    @BindView(R.id.name)
    FormView name;
    @BindView(R.id.identity)
    FormView identity;
    @BindView(R.id.sex)
    FormView sex;
    @BindView(R.id.age)
    FormView age;
    @BindView(R.id.headImg)
    SimpleDraweeView headImg;
    @BindView(R.id.towns)
    FormView towns;
    @BindView(R.id.village)
    FormView village;
    @BindView(R.id.villageGroup)
    FormView villageGroup;
    @BindView(R.id.accountNumber)
    FormView accountNumber;
    @BindView(R.id.linkTel)
    FormView linkTel;
    @BindView(R.id.education)
    FormView education;
    @BindView(R.id.bankAccount)
    FormView bankAccount;
    @BindView(R.id.bank)
    FormView bank;
    @BindView(R.id.familyMember)
    FormView familyMember;
    @BindView(R.id.labour)
    FormView labour;
    @BindView(R.id.positiveImg)
    SimpleDraweeView positiveImg;
    @BindView(R.id.oppositeImg)
    SimpleDraweeView oppositeImg;
    @BindView(R.id.homeImg)
    SimpleDraweeView homeImg;
    @BindView(R.id.dataList)
    TGBasicInfoLayou dataList;
    @BindView(R.id.annual)
    FormView annual;
    @BindView(R.id.arableArea)
    FormView arableArea;
    @BindView(R.id.leaseArea)
    FormView leaseArea;
    @BindView(R.id.preceding)
    FormView preceding;
    @BindView(R.id.combineArea)
    FormView combineArea;
    @BindView(R.id.tianArea)
    FormView tianArea;
    @BindView(R.id.diArea)
    FormView diArea;
    @BindView(R.id.variety)
    FormView variety;
    @BindView(R.id.strains)
    FormView strains;
    @BindView(R.id.mandatoryAmount)
    FormView mandatoryAmount;
    @BindView(R.id.exportAmount)
    FormView exportAmount;
    @BindView(R.id.allGrowInfoLayout)
    LinearLayout allGrowInfoLayout;
    @BindView(R.id.cPhone)
    FormView cPhone;
    @BindView(R.id.cUIM)
    FormView cUIM;
    @BindView(R.id.cSet)
    FormView cSet;
    @BindView(R.id.cSetDetail)
    CountEditText cSetDetail;
    @BindView(R.id.cNumber)
    FormView cNumber;
    @BindView(R.id.cAccount)
    FormView cAccount;
    @BindView(R.id.cPassword)
    FormView cPassword;
    @BindView(R.id.cDate)
    FormView cDate;
    @BindView(R.id.cProxy)
    FormView cProxy;
    @BindView(R.id.person_info_root)
    LinearLayout personInfoRoot;
    Unbinder unbinder;
    @BindView(R.id.zhigongNumber)
    FormView zhigongNumber;
    @BindView(R.id.zhigongType)
    FormView zhigongType;
    @BindView(R.id.zhigongUserName)
    FormView zhigongUserName;
    @BindView(R.id.zhigongStatus)
    FormView zhigongStatus;
    @BindView(R.id.ruzhiTime)
    FormView ruzhiTime;
    @BindView(R.id.lizhiTime)
    FormView lizhiTime;
    @BindView(R.id.zhigongUnit)
    FormView zhigongUnit;
    @BindView(R.id.zhigongStation)
    FormView zhigongStation;
    @BindView(R.id.zhigongAddress)
    FormView zhigongAddress;
    @BindView(R.id.zhigongFamilyAddress)
    FormView zhigongFamilyAddress;
    @BindView(R.id.suoxiaVillage)
    FormView suoxiaVillage;
    @BindView(R.id.beizhu)
    CountEditText beizhu;
    @BindView(R.id.zhigongLayout)
    LinearLayout zhigongLayout;
    @BindView(R.id.framerLayout)
    LinearLayout framerLayout;
    @BindView(R.id.fudaoyuanGuaPianZhiGong)
    FormView fudaoyuanGuaPianZhiGong;
    @BindView(R.id.tobaccoLayout)
    LinearLayout tobaccoLayout;


    public PersonInfoFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_info, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Events.register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        Events.unregister(this);
    }


    @Subscribe
    public void onReceive(TGBasicInfoEntity entity) {
        if (entity.getCategory() != 1 && entity.getCategory() != 2) {
            zhigongLayout.setVisibility(View.GONE);
            tobaccoLayout.setVisibility(View.GONE);
            name.setEditText(entity.getName());
            identity.setEditText(entity.getIdentity());
            sex.setEditText("1".equals(entity.getSex()) ? "男" : "2".equals(entity.getSex()) ? "女" : "");
            age.setEditText(entity.getAge());
            headImg.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getHeaderImg()));
            towns.setEditText(entity.getTowns());
            village.setEditText(entity.getVillage());
            villageGroup.setEditText(entity.getVillageGroup());
            accountNumber.setEditText(entity.getAccountNumber());
            linkTel.setEditText(entity.getLinkTel());
            education.setEditText(entity.getEducation());
            bankAccount.setEditText(entity.getBankAccount());
            bank.setEditText(entity.getBank());
            familyMember.setEditText(entity.getFamilyMember());
            labour.setEditText(entity.getLabour());
            positiveImg.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getPositiveImg()));
            oppositeImg.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getOppositeImg()));
            homeImg.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getHomeImg()));
            dataList.setInfo(entity.getDataList());
            //种烟信息
            final List<TGBasicInfoEntity.GrowInfo> farmerDetail = entity.getFarmerDetail();
            if (farmerDetail != null && !farmerDetail.isEmpty()) {

                String[] years = new String[farmerDetail.size()];

                for (int i = 0; i < farmerDetail.size(); i++) {
                    String year = farmerDetail.get(i).getAnnual();
                    years[i] = year;

                    //默认显示当年的种烟信息
                    if (String.valueOf(Times.getYear()).equals(year)) {
                        bindGrowInfo(farmerDetail.get(i));
                        annual.setEditText(year);
                    }
                }

                if (years.length != 0) {
                    annual.setStringArray(years);
                    annual.setOnItemClickedListener(new IFromView.OnItemClickedListener() {
                        @Override
                        public void getPos(int pos) {
                            TGBasicInfoEntity.GrowInfo growInfo = farmerDetail.get(pos);
                            bindGrowInfo(growInfo);
                        }
                    });
                }

            }
            cPhone.setEditText(entity.getcPhone());
            cUIM.setEditText(entity.getcUIM());
            cSet.setEditText(entity.getcSet());
            cSetDetail.setText(entity.getcSetDetail());
            cNumber.setEditText(entity.getcNumber());
            cAccount.setEditText(entity.getcAccount());
            cPassword.setEditText(entity.getcPassword());
            cDate.setEditText(entity.getcDate());
            cProxy.setEditText(entity.getcProxy());
        } else {
            framerLayout.setVisibility(View.GONE);
            if(entity.getCategory() == 1){
                tobaccoLayout.setVisibility(View.GONE);
            }else{
                fudaoyuanGuaPianZhiGong.setEditText(entity.getWid());
                suoxiaVillage.setEditText(entity.getVillageGroup());
            }
            zhigongNumber.setEditText(entity.getUserNumber());
            zhigongType.setEditText(entity.getType());
            zhigongUserName.setEditText(entity.getUserName());
            zhigongStatus.setEditText(entity.getAvailable());
            ruzhiTime.setEditText(entity.getHiredate());
            lizhiTime.setEditText(entity.getLeavedate());
            zhigongUnit.setEditText(entity.getUname());
            zhigongStation.setEditText(entity.getDepartment());
            zhigongAddress.setEditText(entity.getProvince() + "\t" + entity.getCity() + "\t" + entity.getCounty());
            zhigongFamilyAddress.setEditText(entity.getAddress());
            beizhu.setText(entity.getRemark());
            name.setEditText(entity.getNickname());
            identity.setEditText(entity.getIdentity());
            headImg.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getHeaderImg()));
            sex.setEditText("1".equals(entity.getSex()) ? "男" : "2".equals(entity.getSex()) ? "女" : "");
            towns.setEditText(entity.getTowns());
            village.setEditText(entity.getVillageCommittee());
            villageGroup.setEditText(entity.getVillageGroup());
            linkTel.setEditText(entity.getTel());
            education.setEditText(entity.getEducation());
            cPhone.setEditText(entity.getPhone());
            cUIM.setEditText(entity.getUim());
            cSet.setEditText(entity.getDiscount());
            cSetDetail.setText(entity.getDiscountDetail());
            cNumber.setEditText(entity.getImei());
            cAccount.setEditText(entity.getVpdnNumber());
            cPassword.setEditText(entity.getVpdnPassword());
            cDate.setEditText(entity.getRegdate());
            cProxy.setEditText(entity.getAgent());

        }

        Views.disableControl(getView());
    }


    //显示种烟信息
    private void bindGrowInfo(TGBasicInfoEntity.GrowInfo growInfo) {
        if (growInfo == null) return;
        arableArea.setEditText(growInfo.getArableArea());//耕地面积
        leaseArea.setEditText(growInfo.getLeaseArea());//租赁面积
        preceding.setEditText(growInfo.getPreceding());//前茬
        combineArea.setEditText(growInfo.getCombineArea());//当年种烟合计
        tianArea.setEditText(growInfo.getTianArea());//田烟面积
        diArea.setEditText(growInfo.getDiArea());//地烟面积
        variety.setEditText(growInfo.getVariety());//种植品种
        strains.setEditText(growInfo.getStrains());//种植株数
        mandatoryAmount.setEditText(growInfo.getMandatoryAmount());//指令性量
        exportAmount.setEditText(growInfo.getExportAmount());//其中出口备货
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
