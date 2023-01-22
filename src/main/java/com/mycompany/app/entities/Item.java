package com.mycompany.app.entities;

import com.mycompany.app.entities.enums.EnchantmentLevel;
import com.mycompany.app.entities.enums.Quality;
import com.mycompany.app.entities.enums.Rarity;
import com.mycompany.app.entities.enums.Tier;

/**
 * Item
 */
public class Item {
    public String name;
    public Tier tier;
    public Quality quality;
    public Rarity rarity;
    public EnchantmentLevel enchantmentLevel;

    public Item(String name, Tier tier, Quality quality, Rarity rarity) {
        this.name = name;
        this.tier = tier;
        this.quality = quality;
        this.rarity = rarity;
        this.enchantmentLevel = rarity.toEnchantmentLevel();
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", "
            .concat( "Tier: " + this.tier + ", ")
            .concat( "Quality: " + this.quality + ", ")
            .concat( "Rarity: " + this.rarity + ", ")
            .concat( "Enchantment Level: " + this.enchantmentLevel);
    }
}
