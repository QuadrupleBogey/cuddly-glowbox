package com.mortonstudios.app.serializable;

/**
 * Created by Cameron on 17/08/2018.
 */
public class OCRDTO {

    private final long UUID;

    private final String image;

    public OCRDTO(long UUID, String image){
        this.image = image;
        this.UUID = UUID;
    }

    public long getUUID() {
        return UUID;
    }

    public String getImage() {
        return image;
    }
}
