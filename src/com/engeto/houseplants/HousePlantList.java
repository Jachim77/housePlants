package com.engeto.houseplants;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class HousePlantList {
    private List<HousePlant> listOfHousePlants = new ArrayList<>();
    private static final String MySeparator = "/t";

    public void addHousePlant (HousePlant housePlant) {
        listOfHousePlants.add(housePlant);
    }

    public HousePlant getHousePlantAtIndex (int index) {
        return listOfHousePlants.get(index);
    }

    public void removeHousePlantAtIndex(int index) {
        listOfHousePlants.remove(index);
    }

    public void addHousePlantFromFile (String fileName) throws PlantException {    //cteni ze souboru
        long lineNumber = 0;
        String[] registerHousePlants = new String[0];
        String line = "";
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                line = scanner.nextLine();
                registerHousePlants = (line.split("\t"));

                HousePlant housePlant = new HousePlant(
                        registerHousePlants[0],
                        registerHousePlants[1],
                        LocalDate.parse(registerHousePlants[4]),
                        LocalDate.parse(registerHousePlants[3]),
                        Integer.parseInt(registerHousePlants[2])
                );
                listOfHousePlants.add(housePlant);
            }

        } catch (FileNotFoundException e) {
            throw new PlantException("Nepodařilo se nalézt soubor" + fileName + "\n\"" + e.getLocalizedMessage() + "\"");
        } catch (NumberFormatException e) {
            throw new PlantException("Špatně zadané číslo na řádku:" + lineNumber + ", zadáno:" + registerHousePlants[2] + "\n"
                    + e.getLocalizedMessage() + "\"");
        } catch (DateTimeException e) {
            throw new PlantException("Špatně zadané číslo na řádku:" + lineNumber + ", zadáno: " + registerHousePlants[3] + ", "+registerHousePlants[4] + "\n"
                    + e.getLocalizedMessage() + "\"");
        }
    }

        public String getAllWateringInfo() {     //seznam zalivky pro všechny rostliny
        String allWatering = "";
        for (HousePlant housePlant : listOfHousePlants) {
            allWatering += housePlant.getWateringInfo() + "\n";
        }
        return allWatering;
        }



        public String getAllHousePlants() {    //vrati retezec - seznam vsech rostlin
            String all = "";
            for (HousePlant plant : listOfHousePlants) {
                all += "Rostlina: "+ plant.getName() + ", Poznámka: " + plant.getNotes() + ", Datum zasazení: " + plant.getDatePlanted() + ", Poslední zálivka: " + plant.getLastWatering() + ", Frekvence zálivky: " + plant.getFrequencyOfWatering() + "\n";
            }
            return all;
        }

        public void saveAllToFile (String fileName) throws PlantException {     //zapis do souboru
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName))))
            {
                for (HousePlant plant : listOfHousePlants) {
                    String record = plant.getName() + "\t"
                            + plant.getNotes() + "\t"
                            + plant.getFrequencyOfWatering() + "\t"
                            + plant.getLastWatering() + "\t"
                            + plant.getDatePlanted();
                    writer.println(record);
                }
            }
        catch (IOException e) {throw new PlantException("Došlo k chybě při zápisu do souboru" + fileName + e.getLocalizedMessage());

            }

        }

    public List<HousePlant> getListOfHousePlants() {
        return listOfHousePlants;
    }

    public List <LocalDate> getDatesPlanted() {
        Set<LocalDate> dates = new HashSet<>();
        for (HousePlant plant : listOfHousePlants) {
            dates.add(plant.getDatePlanted());
        }
        List<LocalDate> dates1 = new ArrayList<>(dates);
        Collections.sort(dates1);

        return dates1;
    }

    public List <LocalDate> getDatesPlantedLastMonth() {
        Set <LocalDate> dates = new HashSet<>();
        for (HousePlant plant : listOfHousePlants) {
            dates.add(plant.getDatePlanted());
        }
        List<LocalDate> dates2 = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        for (LocalDate date : dates) {
            int yearPlanted = date.getYear();
            int monthPlanted = date.getMonthValue();
            if (yearPlanted == currentYear || monthPlanted == currentMonth) {
            dates2.add(date);
            }
        }
        return dates2;
    }

    public void removeAllHousePlant() {listOfHousePlants.clear();}

    public void sort(){
        Collections.sort(listOfHousePlants);
    }

    public List<HousePlant>getCopyOfListHouseplants() {
        return new ArrayList<>(listOfHousePlants);
    }

    }

