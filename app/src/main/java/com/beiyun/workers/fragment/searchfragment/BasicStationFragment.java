package com.beiyun.workers.fragment.searchfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.entity.ApplyAcceptEntity;
import com.beiyun.workers.entity.BaseStationEntity;
import com.beiyun.workers.view.AddImageView;
import com.beiyun.workers.view.CountEditText;
import com.beiyun.workers.view.FormView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicStationFragment extends BaseSearchFragment {


    @BindView(R.id.oneType)
    FormView oneType;
    @BindView(R.id.secondType)
    FormView secondType;
    @BindView(R.id.townCode)
    FormView townCode;
    @BindView(R.id.villageCode)
    FormView villageCode;
    @BindView(R.id.groupCode)
    FormView groupCode;
    @BindView(R.id.mainSubject)
    FormView mainSubject;
    @BindView(R.id.linkTel)
    FormView linkTel;
    @BindView(R.id.header)
    FormView header;
    @BindView(R.id.workUnit)
    FormView workUnit;
    @BindView(R.id.itemNo)
    FormView itemNo;
    @BindView(R.id.place)
    FormView place;
    @BindView(R.id.longitude)
    FormView longitude;
    @BindView(R.id.latitude)
    FormView latitude;
    @BindView(R.id.endLongitude)
    FormView endLongitude;
    @BindView(R.id.endLatitude)
    FormView endLatitude;
    @BindView(R.id.endGPS)
    LinearLayout endGPS;
    @BindView(R.id.beUsed)
    FormView beUsed;
    @BindView(R.id.execUnit)
    FormView execUnit;
    @BindView(R.id.benefitedArea)
    FormView benefitedArea;
    @BindView(R.id.capacity)
    FormView capacity;
    @BindView(R.id.constrContent)
    CountEditText constrContent;
    @BindView(R.id.pipMater)
    FormView pipMater;
    @BindView(R.id.purchaseBody)
    FormView purchaseBody;
    @BindView(R.id.orgCode)
    FormView orgCode;
    @BindView(R.id.installSmokeType)
    FormView installSmokeType;
    @BindView(R.id.supplier)
    FormView supplier;
    @BindView(R.id.barnSupplier)
    FormView barnSupplier;
    @BindView(R.id.specification)
    FormView specification;
    @BindView(R.id.molectronics)
    FormView molectronics;
    @BindView(R.id.quantity)
    FormView quantity;
    @BindView(R.id.purchaseTime)
    FormView purchaseTime;
    @BindView(R.id.machineName)
    FormView machineName;
    @BindView(R.id.brand)
    FormView brand;
    @BindView(R.id.proEnterprise)
    FormView proEnterprise;
    @BindView(R.id.power)
    FormView power;
    @BindView(R.id.qualifiedNo)
    FormView qualifiedNo;
    @BindView(R.id.engineNo)
    FormView engineNo;
    @BindView(R.id.frameNo)
    FormView frameNo;
    @BindView(R.id.efficiency)
    FormView efficiency;
    @BindView(R.id.supplyUnit)
    FormView supplyUnit;
    @BindView(R.id.constrArea)
    FormView constrArea;
    @BindView(R.id.nurseryArea)
    FormView nurseryArea;
    @BindView(R.id.transArea)
    FormView transArea;
    @BindView(R.id.transAreaLinear)
    LinearLayout transAreaLinear;
    @BindView(R.id.totalCost)
    FormView totalCost;
    @BindView(R.id.subsidies)
    FormView subsidies;
    @BindView(R.id.startTime)
    FormView startTime;
    @BindView(R.id.completedTime)
    FormView completedTime;
    @BindView(R.id.manager)
    FormView manager;
    Unbinder unbinder;
    @BindView(R.id.images)
    AddImageView images;

    public BasicStationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_station, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        com.beiyun.library.util.Events.register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        com.beiyun.library.util.Events.unregister(this);
    }


    @Subscribe
    public void onReceive(BaseStationEntity entity){
         oneType.setEditText(entity.getOneType());
         secondType.setEditText(entity.getSecondType());
         townCode.setEditText(entity.getTownCode());
         villageCode.setEditText(entity.getVillageCode());
         groupCode.setEditText(entity.getGroupCode());
         mainSubject.setEditText(entity.getMainSubject());
         linkTel.setEditText(entity.getLinkTel());
         header.setEditText(entity.getHeader());
         workUnit.setEditText(entity.getWorkUnit());
         itemNo.setEditText(entity.getItemNo());
         place.setEditText(entity.getPlace());
         longitude.setEditText(entity.getLongitude());
         latitude.setEditText(entity.getLatitude());
         endLongitude.setEditText(entity.getEndLongitude());
         endLatitude.setEditText(entity.getEndLatitude());
         beUsed.setEditText(entity.getBeUsed());
         execUnit.setEditText(entity.getExecUnit());
         benefitedArea.setEditText(entity.getBenefitedArea());
         capacity.setEditText(entity.getCapacity());
         constrContent.setText(entity.getConstrContent());
         pipMater.setEditText(entity.getPipMater());
         purchaseBody.setEditText(entity.getPurchaseBody());
         orgCode.setEditText(entity.getOrgCode());
         installSmokeType.setEditText(entity.getInstallSmokeType());
         supplier.setEditText(entity.getSupplier());
         barnSupplier.setEditText(entity.getBarnSupplier());
         specification.setEditText(entity.getSpecification());
         molectronics.setEditText(entity.getMolectronics());
         quantity.setEditText(entity.getQuantity());
         purchaseTime.setEditText(entity.getPurchaseTime());
         machineName.setEditText(entity.getMachineName());
         brand.setEditText(entity.getBrand());
         proEnterprise.setEditText(entity.getProEnterprise());
         power.setEditText(entity.getPower());
         qualifiedNo.setEditText(entity.getQualifiedNo());
         engineNo.setEditText(entity.getEngineNo());
         frameNo.setEditText(entity.getFrameNo());
         efficiency.setEditText(entity.getEfficiency());
         supplyUnit.setEditText(entity.getSupplyUnit());
         constrArea.setEditText(entity.getConstrArea());
         nurseryArea.setEditText(entity.getNurseryArea());
         transArea.setEditText(entity.getTransArea());
         totalCost.setEditText(entity.getTotalCost());
         subsidies.setEditText(entity.getSubsidies());
         startTime.setEditText(entity.getStartTime());
         completedTime.setEditText(entity.getCompletedTime());
         manager.setEditText(entity.getManager());
         images.setImages(entity.getImages());

    }
}
