package com.mioffers.MiOffers;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.google.android.gms.maps.model.LatLng;
import com.mioffers.MiOffers.entity.ExpandableParentItem;

import java.util.Map;

/**
 * Created by laxman.muttineni on 06/03/16.
 */
public class Mappers {
    public static ExpandableParentItem mapMsgDataToExpandableParent (DataSnapshot dataSnapshot) {
        Map<String, Object> msgData = (Map<String, Object>) dataSnapshot.getValue();
        ExpandableParentItem ex= new ExpandableParentItem();
        ex.setCompany(msgData.get(Constants.fireBaseChildCompany).toString());
        ex.setDescription(msgData.get(Constants.fireBaseChildDescription).toString());
        ex.setTitle(msgData.get(Constants.fireBaseChildTitle).toString());
        ex.setValidity(msgData.get(Constants.fireBaseChildValidity).toString());
        ex.setId(dataSnapshot.getKey());
        LatLng latLng = new LatLng((Double)msgData.get(Constants.fireBaseChildLat),(Double)msgData.get(Constants.fireBaseChildLong));
        ex.setLatLng(latLng);
        return ex;
    }
}
