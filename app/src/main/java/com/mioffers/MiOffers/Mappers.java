package com.mioffers.MiOffers;

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
        ex.setCompany(msgData.get(Constants.FIRE_BASE_CHILD_COMPANY).toString());
        ex.setDescription(msgData.get(Constants.FIRE_BASE_CHILD_DESCRIPTION).toString());
        ex.setTitle(msgData.get(Constants.FIRE_BASE_CHILD_TITLE).toString());
        ex.setValidity(msgData.get(Constants.FIRE_BASE_CHILD_VALIDITY).toString());
        ex.setId(dataSnapshot.getKey());
        LatLng latLng = new LatLng((Double)msgData.get(Constants.FIRE_BASE_CHILD_LAT),(Double)msgData.get(Constants.FIRE_BASE_CHILD_LONG));
        ex.setLatLng(latLng);
        return ex;
    }
}
