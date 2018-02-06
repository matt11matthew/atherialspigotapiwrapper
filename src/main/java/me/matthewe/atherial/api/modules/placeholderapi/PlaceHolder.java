package me.matthewe.atherial.api.modules.placeholderapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class PlaceHolder {
    private final String identifier;
    private PlaceHolderRequest placeHolderRequest;
    private List<ChildPlaceHolder> childPlaceHolderList;

    public PlaceHolder(String identifier, PlaceHolderRequest placeHolderRequest, ChildPlaceHolder... childPlaceHolders) {
        this.identifier = identifier;
        this.placeHolderRequest = placeHolderRequest;
        this.childPlaceHolderList = new ArrayList<>();
        if (childPlaceHolders != null && (childPlaceHolders.length > 0)) {
            this.childPlaceHolderList.addAll(Arrays.asList(childPlaceHolders));
        }
    }

    public PlaceHolder(String identifier,ChildPlaceHolder... childPlaceHolders) {
        this(identifier, null, childPlaceHolders);
    }

    public String getIdentifier() {
        return identifier;
    }

    public PlaceHolderRequest getPlaceHolderRequest() {
        return placeHolderRequest;
    }

    public List<ChildPlaceHolder> getChildPlaceHolderList() {
        return childPlaceHolderList;
    }
}
