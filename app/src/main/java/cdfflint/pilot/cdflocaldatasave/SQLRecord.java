package cdfflint.pilot.cdflocaldatasave;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "record_table")
public class SQLRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String collectionDate;
    private String collectionTime;
    private int tabletNumber;
    private String timeRunning;
    private String waterTemp;
    private String normalUse;
    private String waterColor;
    private String waterSmell;
    private String waterTaste;
    private String rottenEgg;
    private String sedimentPresent;
    private String sedimentFeathery;
    private String bacteriaResult;
    private String hardnessPpm;
    private String chlorinePpm;
    private String alkalinityPpm;
    private String copperPpm;
    private String ironPpm;
    private String phValue;
    private String pesticideResult;
    private String leadResult;
    private String nitriteResult;
    private String nitrateResult;

    public SQLRecord(String collectionDate, String collectionTime, int tabletNumber,
                     String timeRunning, String waterTemp, String normalUse, String waterColor,
                     String waterSmell, String waterTaste, String rottenEgg, String sedimentPresent,
                     String sedimentFeathery, String bacteriaResult, String hardnessPpm,
                     String chlorinePpm, String alkalinityPpm, String copperPpm, String ironPpm,
                     String phValue, String pesticideResult, String leadResult,
                     String nitriteResult, String nitrateResult) {

        this.collectionDate = collectionDate;
        this.collectionTime = collectionTime;
        this.tabletNumber = tabletNumber;
        this.timeRunning = timeRunning;
        this.waterTemp = waterTemp;
        this.normalUse = normalUse;
        this.waterColor = waterColor;
        this.waterSmell = waterSmell;
        this.waterTaste = waterTaste;
        this.rottenEgg = rottenEgg;
        this.sedimentPresent = sedimentPresent;
        this.sedimentFeathery = sedimentFeathery;
        this.bacteriaResult = bacteriaResult;
        this.hardnessPpm = hardnessPpm;
        this.chlorinePpm = chlorinePpm;
        this.alkalinityPpm = alkalinityPpm;
        this.copperPpm = copperPpm;
        this.ironPpm = ironPpm;
        this.phValue = phValue;
        this.pesticideResult = pesticideResult;
        this.leadResult = leadResult;
        this.nitriteResult = nitriteResult;
        this.nitrateResult = nitrateResult;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public int getTabletNumber() {
        return tabletNumber;
    }

    public String getTimeRunning() {
        return timeRunning;
    }

    public String getWaterTemp() {
        return waterTemp;
    }

    public String getNormalUse() {
        return normalUse;
    }

    public String getWaterColor() {
        return waterColor;
    }

    public String getWaterSmell() {
        return waterSmell;
    }

    public String getWaterTaste() {
        return waterTaste;
    }

    public String getRottenEgg() {
        return rottenEgg;
    }

    public String getSedimentPresent() {
        return sedimentPresent;
    }

    public String getSedimentFeathery() {
        return sedimentFeathery;
    }

    public String getBacteriaResult() {
        return bacteriaResult;
    }

    public String getHardnessPpm() {
        return hardnessPpm;
    }

    public String getChlorinePpm() {
        return chlorinePpm;
    }

    public String getAlkalinityPpm() {
        return alkalinityPpm;
    }

    public String getCopperPpm() {
        return copperPpm;
    }

    public String getIronPpm() {
        return ironPpm;
    }

    public String getPhValue() {
        return phValue;
    }

    public String getPesticideResult() {
        return pesticideResult;
    }

    public String getLeadResult() {
        return leadResult;
    }

    public String getNitriteResult() {
        return nitriteResult;
    }

    public String getNitrateResult() {
        return nitrateResult;
    }
}
