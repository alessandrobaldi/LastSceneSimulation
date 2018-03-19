/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.study.scenary.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alessandro
 */

public class Scenary {
    
    private List<House> scenary = new ArrayList<House>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    int total;

    public List<House> getScenary() {
        return scenary;
    }
   
    public void setScenary(List<House> scenary) {
        this.scenary = scenary;
    }

    public Scenary(String houses, String linkedhouses, String houseswithtrap, String houseswithfocus, String houseswithmosquito) {
        //criar construtores das casas e dos links
        String[] totalHouses = houses.split(",");
        String[] totalLinked = linkedhouses.split(",");
        String[] totalTrap = houseswithtrap.split(",");
        String[] totalFocus = houseswithfocus.split(",");
        String[] totalMosquito = houseswithmosquito.split(",");

        for (int create = 0; create < totalHouses.length; create++) {
            House newHouse = new House(totalHouses[create]);
            scenary.add(newHouse);
        }

        for (int create = 0; create < totalLinked.length; create++) {
            String link = totalLinked[create];
            String[] linkNow = link.split("-");
            House findone = scenary.get(0);
            House findtwo = scenary.get(0);
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(linkNow[0]))
                {
                     findone=scenary.get(find);
                }
                if(scenary.get(find).getName().equals(linkNow[1]))
                {
                     findtwo=scenary.get(find);
                }
            }
            findone.addNeighborhood(findtwo);
            findtwo.addNeighborhood(findone);
        }

         for (int create = 0; create < totalTrap.length; create++) {
            String trap = totalTrap[create];
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(trap))
                {
                    scenary.get(find).addTrap();
                }
            }
         }
         
            for (int create = 0; create < totalFocus.length; create++) {
            String focus = totalFocus[create];
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(focus))
                {
                    scenary.get(find).addFocus();
                }
            }
         }
         
            for (int create = 0; create < totalMosquito.length; create++) {
            String mosquito = totalMosquito[create];
            //House houseMosquito = scenary.get(0);
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(mosquito))
                {
                    scenary.get(find).addMosquito();
                }
            }
         }
    }

   

    public int randomize(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    
    
    public void getStats()
    {
        int totalMosquitos = 0;
        int totalEggs = 0;
         for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
         House verify = this.scenary.get(verifyingHouses);
         int mosquitosHere = verify.getMosquitos().size();
         int eggsHere= verify.getEggs().size();
         totalMosquitos=totalMosquitos+mosquitosHere;
         totalEggs=totalEggs+eggsHere;
         }
        System.out.println();
                System.out.println();
                System.out.println("Total: " + totalMosquitos + " mosquitos and "+ totalEggs + " eggs.");
         
    }


}
