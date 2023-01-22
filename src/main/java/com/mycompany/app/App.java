package com.mycompany.app;

import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.Price;
import com.mycompany.app.entities.enums.Quality;
import com.mycompany.app.entities.enums.Rarity;
import com.mycompany.app.entities.enums.Tier;
import com.mycompany.app.enums.Action;
import com.mycompany.app.services.MarketService;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Welcome to Albion Merchant Helper!" );

        Scanner sc = new Scanner(System.in);

        System.out.print("Insert what do you like to do (buy/sell): ");
        String inputedActionAString = sc.nextLine(); 
        Action action;
        try {
            action = Action.valueOf(inputedActionAString.toUpperCase());   
        } catch (Exception e) {
            sc.close();
            throw new Exception(inputedActionAString + " is not a valid action!");
        }
 
        System.out.printf("%nInsert the name of the item without atributes");
        System.out.printf("%nExample: %nComplete Item Name -> Pristine Log Tree %nInsert only -> Log Tree.");
        System.out.printf("%nInsert item name: ");
        String itemName = sc.nextLine();

        System.out.printf("%nInput the item Tier (T1, T2, T3 or T4): ");
        String inputedItemTier = sc.nextLine();
        Tier itemTier;
        try {
            itemTier = Tier.valueOf(inputedItemTier.toUpperCase());   
        } catch (Exception e) {
            sc.close();
            throw new Exception(inputedItemTier + " is not a valid action!");
        }

        System.out.printf("%nSelect the product Quality:");
        for (int i = 0; i < Quality.values().length; i++) {
            System.out.printf("%n%d. %s", i, Quality.values()[i]);
        }
        System.out.printf("%nInput the option number: ");
        int inputedQualityOptionNumber = sc.nextInt();
        sc.nextLine();
        Quality itemQuality;

        try {
            itemQuality = Quality.values()[inputedQualityOptionNumber];
        } catch (Exception e) {
            sc.close();
            throw new Exception("Option Invalid!");
        }

        System.out.printf("%nSelect the product Rarity (EnchantmentLevel):");
        for (int i = 0; i < Rarity.values().length; i++) {
            System.out.printf("%n%d. %s", i, Rarity.values()[i]);
        }
        System.out.printf("%nInput the option number: ");
        int inputedRarityOptionNumber = sc.nextInt();
        sc.nextLine();
        Rarity itemRarity;

        try {
            itemRarity = Rarity.values()[inputedRarityOptionNumber];
        } catch (Exception e) {
            sc.close();
            throw new Exception("Option Invalid!");
        }

        Item item = new Item(itemName, itemTier, itemQuality, itemRarity);

        List<Price> itemPrices = new MarketService().getAllItemPrices(item);

        sc.close();
    }
}
