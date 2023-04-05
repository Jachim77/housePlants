package com.engeto.houseplants;

import java.time.LocalDate;
import java.util.Objects;

public class HousePlant implements Comparable<HousePlant>{
    private String name;
    private String notes;
    private LocalDate datePlanted;
    private LocalDate lastWatering;
    private int frequencyOfWatering;

    public HousePlant(String name, String notes, LocalDate datePlanted, LocalDate lastWatering, int frequencyOfWatering) throws PlantException {
        if (checkFrequencyOfWatering(frequencyOfWatering) && checkLastWatering(datePlanted,lastWatering)) {
            this.name = name;
            this.notes = notes;
            this.datePlanted = datePlanted;
            this.lastWatering = lastWatering;
            this.frequencyOfWatering = frequencyOfWatering;
        }
    }


    public HousePlant(String name, LocalDate datePlanted, int frequencyOfWatering) throws PlantException {
        this(name,"",datePlanted,LocalDate.now(),frequencyOfWatering);
    }

    public HousePlant(String name) throws PlantException {
      this(name, "",LocalDate.now(),LocalDate.now(),7);
    }

    ///region
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(LocalDate datePlanted) {
        this.datePlanted = datePlanted;
    }

    public LocalDate getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(LocalDate lastWatering) throws PlantException{
        if (checkLastWatering(this.datePlanted,lastWatering)) {
            this.lastWatering = lastWatering;
        }
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException{
        if(checkFrequencyOfWatering(frequencyOfWatering)) {
            this.frequencyOfWatering = frequencyOfWatering;
        }
    }
    ///endregion

    public String getWateringInfo() {
            String info = "Rostlina: " + name + ", poslední zálivka: " + lastWatering + ", příští zálivka: " + lastWatering.plusDays(frequencyOfWatering);
            return info;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousePlant that = (HousePlant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(HousePlant o) {
        int result = getName().compareTo(o.getName());
        return result;
    }

    @Override
    public String toString() {
        return ("jméno: " + name +  ", poznámka:  " + notes + ", datePlanted: " + datePlanted + ", lastWatering: " + lastWatering + ", frequencyOfWatering: " + frequencyOfWatering + "\n");
    }

    public Boolean checkLastWatering(LocalDate datePlanted, LocalDate lastWatering) throws PlantException {
        if (lastWatering.isBefore(datePlanted)) {
            throw new PlantException("Zadán chybný údaj: Datum zálivky nesmí být starší než datum vysazení rostliny. \n" +
                    "Datum vysazení - zadáno: " + datePlanted + ",\n" +
                    "datum poslední zálivky- zadáno: " + lastWatering + ".");
        }
        return true;
    }

        public Boolean checkFrequencyOfWatering (int frequencyOfWatering) throws PlantException {
            if (frequencyOfWatering < 1) {
                throw new PlantException("Zadán chybný údaj: Parametr zálivky musí být min. 1, bylo zadáno: " + frequencyOfWatering);
            }
            return true;
        }

}
