package com.Jumbo.store.storeapi.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Location implements Serializable {

    @NotNull
    private String city;
    @NotNull
    private String street;
    private String street2;
    private String street3;
    @NotNull
    private String postalCode;
    @NotNull
    private double longitude;
    @NotNull
    private double latitude;

    public Location() {
    }

    public Location(@NotNull String city, @NotNull String street, String street2, String street3, @NotNull String postalCode, @NotNull double longitude, @NotNull double latitude) {
        this.city = city;
        this.street = street;
        this.street2 = street2;
        this.street3 = street3;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet3() {
        return street3;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public static class Builder{
        private String city;
        private String street;
        private String street2;
        private String street3;
        private String postalCode;
        private double longitude;
        private double latitude;

        public Builder setCity(String city){
            this.city=city;
            return this;
        }

        public Builder setStreet(String street){
            this.street=street;
            return this;
        }

        public Builder setStreet2(String street2){
            this.street2=street2;
            return this;
        }

        public Builder setStreet3(String street3){
            this.street3=street3;
            return this;
        }

        public Builder setPostalCode(String postalCode){
            this.postalCode=postalCode;
            return this;
        }

        public Builder setLongitude(double longitude){
            this.longitude=longitude;
            return this;
        }

        public Builder setLatitude(double latitude){
            this.latitude=latitude;
            return this;
        }

        public Location build(){
            return new Location(city,street,street2,street3,postalCode,longitude,latitude);
        }
    }

    @Override
    public String toString() {
        return "Location{"
                +"city='"+ city+"\'"
                +",street='"+street+"\'"
                +",postalCode='"+postalCode+"\'"
                +"}";
    }
}
