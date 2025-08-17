package io.bluebeaker.morphoverlay;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MorphOverlayHandler {
    private static Item morphTool;
    private static Item akashicTome;
    private static ItemStack morphToolStack;
    private static ItemStack akashicTomeStack;
    public static void init(){
        morphTool = ForgeRegistries.ITEMS.getValue(new ResourceLocation("morphtool:tool"));
        akashicTome = ForgeRegistries.ITEMS.getValue(new ResourceLocation("akashictome:tome"));
        morphToolStack=new ItemStack(morphTool);
        akashicTomeStack = new ItemStack(akashicTome);
    }
    public static boolean isMorphedTool(ItemStack stack){
        if(morphTool==null || stack.getItem()==morphTool || stack.isEmpty() || stack.getTagCompound()==null){
            return false;
        }
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("morphtool:is_morphing");
    }
    public static boolean isAkashicTome(ItemStack stack){
        if(akashicTome==null || stack.getItem()==akashicTome || stack.isEmpty() || stack.getTagCompound()==null){
            return false;
        }
        return (stack.hasTagCompound() && stack.getTagCompound().getBoolean("akashictome:is_morphing"));
    }

    public static ItemStack getMorphToolStack() {
        return morphToolStack;
    }

    public static ItemStack getAkashicTomeStack() {
        return akashicTomeStack;
    }
}
