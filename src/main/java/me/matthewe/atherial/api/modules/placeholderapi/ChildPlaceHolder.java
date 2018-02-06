package me.matthewe.atherial.api.modules.placeholderapi;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class ChildPlaceHolder {
    private String identifier;
    private ChildPlaceHolderRequest childPlaceHolderRequest;

    public ChildPlaceHolder(String identifier, ChildPlaceHolderRequest childPlaceHolderRequest) {
        this.identifier = identifier;
        this.childPlaceHolderRequest = childPlaceHolderRequest;
    }

    public ChildPlaceHolderRequest getChildPlaceHolderRequest() {
        return childPlaceHolderRequest;
    }

    public String getIdentifier() {
        return identifier;
    }
}

