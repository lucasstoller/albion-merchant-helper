package com.mycompany.app.entities.enums;

public enum EnchantmentLevel {
    E0, E1, E2, E3, E4;


    public Rarity toRarity() {
       switch (this) {
           case E0: 
               return Rarity.COMMON;
           case E1:
               return Rarity.UNCOMMON;
           case E2:
               return Rarity.RARE;
           case E3:
               return Rarity.EXCEPTIONAL;
           case E4:
               return Rarity.PRISTINE;
       }
	   return null;     
    }
}
