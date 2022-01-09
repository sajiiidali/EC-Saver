package com.myECapplication.sajiiidali.ecsaver.Row_Data;

public class Row_data {

    public String EC_TYPE, EC_NUMBER, DATE, DAYOFF;

    public Row_data(String EC_TYPE, String EC_NUMBER, String DATE) {
        this.EC_TYPE = EC_TYPE;
        this.EC_NUMBER = EC_NUMBER;
        this.DATE = DATE;
    }

    public Row_data(String DATE, String DAYOFF){
        this.DATE = DATE;
        this.DAYOFF = DAYOFF;
    }

    public String getDAYOFF() {
        return DAYOFF;
    }

    public void setDAYOFF(String DAYOFF) {
        this.DAYOFF = DAYOFF;
    }

    public String getEC_TYPE() {
        return EC_TYPE;
    }

    public void setEC_TYPE(String EC_TYPE) {
        this.EC_TYPE = EC_TYPE;
    }

    public String getEC_NUMBER() {
        return EC_NUMBER;
    }

    public void setEC_NUMBER(String EC_NUMBER) {
        this.EC_NUMBER = EC_NUMBER;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }
}
