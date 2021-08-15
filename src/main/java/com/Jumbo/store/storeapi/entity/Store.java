package com.Jumbo.store.storeapi.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Store implements Serializable {

    @NotNull
    @Id
    private String uuid;

    @JsonUnwrapped
    @NotNull
    private Location location;

    private String locationType;
    private String addressName;
    private long complexNumber;
    private boolean showWarningMessage;
    private String todayOpen;
    private String todayClose;
    private boolean collectionPoint;
    private int sapStoreID;

    public Store() {
    }

    public Store(@NotNull String uuid, @NotNull Location location, String locationType, String addressName, long complexNumber, boolean showWarningMessage, String todayOpen, String todayClose, boolean collectionPoint, int sapStoreID) {
        this.uuid = uuid;
        this.location = location;
        this.locationType = locationType;
        this.addressName = addressName;
        this.complexNumber = complexNumber;
        this.showWarningMessage = showWarningMessage;
        this.todayOpen = todayOpen;
        this.todayClose = todayClose;
        this.collectionPoint = collectionPoint;
        this.sapStoreID = sapStoreID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public long getComplexNumber() {
        return complexNumber;
    }

    public void setComplexNumber(long complexNumber) {
        this.complexNumber = complexNumber;
    }

    public boolean isShowWarningMessage() {
        return showWarningMessage;
    }

    public void setShowWarningMessage(boolean showWarningMessage) {
        this.showWarningMessage = showWarningMessage;
    }

    public String getTodayOpen() {
        return todayOpen;
    }

    public void setTodayOpen(String todayOpen) {
        this.todayOpen = todayOpen;
    }

    public String getTodayClose() {
        return todayClose;
    }

    public void setTodayClose(String todayClose) {
        this.todayClose = todayClose;
    }

    public boolean isCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(boolean collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public int getSapStoreID() {
        return sapStoreID;
    }

    public void setSapStoreID(int sapStoreID) {
        this.sapStoreID = sapStoreID;
    }

    public static class Builder{
        private String uuid;
        private Location location;
        private String locationType;
        private String addressName;
        private long complexNumber;
        private boolean showWarningMessage;
        private String todayOpen;
        private String todayClose;
        private boolean collectionPoint;
        private int sapStoreID;

        public Builder setUuid(String uuid){
            this.uuid=uuid;
            return this;
        }

        public Builder setLocation(Location location){
            this.location=location;
            return this;
        }

        public Builder setLocationType(String locationType){
            this.locationType=locationType;
            return this;
        }

        public Builder setAddressName(String addressName){
            this.addressName=addressName;
            return this;
        }

        public Builder setComplexNumber(long complexNumber){
            this.complexNumber=complexNumber;
            return this;
        }

        public Builder setTodayOpen(String todayOpen){
            this.todayOpen=todayOpen;
            return this;
        }

        public Builder setTodayClose(String todayClose){
            this.todayClose=todayClose;
            return this;
        }

        public Builder setCollectionPoint(boolean collectionPoint){
            this.collectionPoint=collectionPoint;
            return this;
        }

        public Store build(){
            return new Store(uuid,location,locationType,addressName,complexNumber,showWarningMessage,todayOpen,todayClose,collectionPoint,sapStoreID);
        }

    }

    @Override
    public String toString() {
        return "Store{"
                +"name='"+ addressName+"\'"
                +"locationType='"+ locationType+"\'"
                +",address='"+location.toString()+"\'"
                +"}";
    }
}
