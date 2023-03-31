import com.engeto.houseplants.HousePlant;
import com.engeto.houseplants.HousePlantComparator;
import com.engeto.houseplants.HousePlantList;
import com.engeto.houseplants.PlantException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    private final static String InputFile1 = "kvetiny.txt";
    private final static String InputFile2 = "kvetiny-spatne-datum.txt";
    private final static String InputFile3 = "kvetiny-spatne-frekvence.txt";
    public static void main(String[] args) {
        /*try {
            HousePlant housePlant = new HousePlant("Azalka", "Červená", LocalDate.of(2019, 7, 22), LocalDate.of(2023, 3, 12), 5);
            System.out.println("Jenom zkouška: \n" + housePlant + "\n");
            housePlant.setFrequencyOfWatering(2);
            System.out.println("Jenom zkouška: \n" + housePlant + "\n");
            housePlant.setLastWatering(LocalDate.of(2023,3,22));
            System.out.println("Jenom zkouška: \n" + housePlant + "\n");

        }catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }*/

        try {
            HousePlantList housePlantList = new HousePlantList();
            housePlantList.addHousePlantFromFile(InputFile1);  //načtení ze souboru
            System.out.println("Informace o zálivce:\n"+ housePlantList.getAllWateringInfo());
            System.out.println("Načtený původní seznam: \n" + housePlantList.getAllHousePlants());
            HousePlant plant1 = new HousePlant("Begonie", "malá", LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 5), 3);
            HousePlant plant2 = new HousePlant("Fíkus", "v obýváku", LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 10), 4);
            housePlantList.addHousePlant(plant1);
            housePlantList.addHousePlant(plant2);
            housePlantList.removeHousePlantAtIndex(2);
            System.out.println("Aktualizovaný seznam: \n" + housePlantList.getAllHousePlants());
            housePlantList.saveAllToFile(InputFile1);   //uložení aktualizovaného seznamu do souboru
            housePlantList.removeAllHousePlant();      //vymazání původního seznamu
            housePlantList.addHousePlantFromFile(InputFile1);   //opětovné načtení
            System.out.println("Načtený aktualizovaný seznam: \n" + housePlantList.getAllHousePlants());

            List<HousePlant> housePlantList3 = housePlantList.getCopyOfListHouseplants();
            System.out.println("Neseřazený seznam: \n"+housePlantList3);
            Collections.sort(housePlantList3);
            System.out.println("Seřazeno podle jména: \n"+housePlantList3);
            Collections.sort(housePlantList3,new HousePlantComparator());
            System.out.println("Seřazeno podle data poslední zálivky: \n"+housePlantList3);

            System.out.println("Datumy vysazení rostlin: " + housePlantList.getDatesPlanted() + "\n");

            System.out.println("Datumy vysazení rostlin za poslední měsíc: " + housePlantList.getDatesPlantedLastMonth() + "\n");

           /* System.out.println("nový köd: \n");
            Collections.sort(housePlantList);
            System.out.println(housePlantList);*/


        } catch
            (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }


    }
}