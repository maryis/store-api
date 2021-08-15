package com.Jumbo.store.storeapi.dto;

import javax.validation.constraints.NotNull;

public class Position {

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    public Position(@NotNull double longitude, @NotNull double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{"
                +"longitude='"+ longitude+"\'"
                +"latitude='"+ latitude+"\'"
                +"}";    }
}
