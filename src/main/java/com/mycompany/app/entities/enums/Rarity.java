package com.mycompany.app.entities.enums;

public enum Rarity {
    COMMON, UNCOMMON, RARE, EXCEPTIONAL, PRISTINE;

    public EnchantmentLevel toEnchantmentLevel() {
       switch (this) {
           case COMMON:
               return EnchantmentLevel.E0;
           case UNCOMMON:
               return EnchantmentLevel.E1;
           case RARE:
               return EnchantmentLevel.E2;
           case EXCEPTIONAL:
               return EnchantmentLevel.E3;
           case PRISTINE:
               return EnchantmentLevel.E4;
       }
	   return null;     
    }
}
