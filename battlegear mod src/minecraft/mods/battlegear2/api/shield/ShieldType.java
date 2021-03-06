package mods.battlegear2.api.shield;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Defines a shield "material", since not necessarily a tool
 * Roughly covers the data asked for by {@link IShield}
 */
public class ShieldType {

    public static final ShieldType WOOD = new ShieldType("wood", 1F/1F/20F, 1F/20F, 40, 15, 0xFFbc9862); // 1 second block
    public static final ShieldType HIDE = new ShieldType("hide", 1F/1.5F/20F, 1F/20F, 40, 12, 0xFF9b482b); //1.5 second block
    public static final ShieldType IRON = new ShieldType("iron", 1F/3F/20F, 1F/20F, 120,  9, 0xFFacacac); //3 second block
    public static final ShieldType DIAMOND = new ShieldType("diamond", 1F/5F/20F, 1F/20F, 263, 10, 0xFF23bfbf); //5 second block
    public static final ShieldType GOLD = new ShieldType("gold", 1F/2F/20F, 1F/20F, 56, 25, 0xFFa8a400); //2 second block

    private final float decayRate;
    private final float damageDecay;
    private final String name;
    private final int enchantability;
    private final int maxDamage;
    private final int defaultRGB;

    public ShieldType(String name, float decayRate, float damageDecay, int maxDamage, int enchantability, int defaultColour){
        this.name = name;
        this.decayRate = decayRate;
        this.damageDecay = damageDecay;
        this.enchantability = enchantability;
        this.maxDamage = maxDamage;
        defaultRGB = defaultColour;
    }

    /**
     * See {@link IShield#getDecayRate(net.minecraft.item.ItemStack)}
     */
    public float getDecayRate() {
        return decayRate;
    }

    /**
     * See {@link IShield#getDamageDecayRate(net.minecraft.item.ItemStack, float)}
     */
    public float getDamageDecay() {
        return damageDecay;
    }

    public String getName() {
        return name;
    }

    /**
     * See {@link Item#getItemEnchantability(net.minecraft.item.ItemStack)}
     */
    public int getEnchantability() {
        return enchantability;
    }

    /**
     * See {@link Item#getMaxDamage(net.minecraft.item.ItemStack)}
     */
    public int getMaxDamage() {
        return maxDamage;
    }

    public int getDefaultRGB() {
        return defaultRGB;
    }

    /**
     * Make a new instance based on the compressed data
     * Note: Only a valid name is required
     * @param compound data to read from
     * @return the new type, or null if name is not readable
     */
    public static ShieldType fromNBT(NBTTagCompound compound){
        String name = compound.getString("Name");
        if(name.equals(""))
            return null;
        else
            return new ShieldType(name, compound.getFloat("DecayRate"), compound.getFloat("DamageDecay"), compound.getInteger("MaxDamage"), compound.getInteger("Enchantability"), compound.getInteger("RGB"));
    }
}
