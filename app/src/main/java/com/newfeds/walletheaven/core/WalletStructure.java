package com.newfeds.walletheaven.core;

/**
 * Created by GT on 12/26/2015.
 */
public class WalletStructure {
    private int drawableId;
    private String title;
    private String key;

    public WalletStructure(int drawableId, String title, String key) {
        this.drawableId = drawableId;
        this.title = title;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
