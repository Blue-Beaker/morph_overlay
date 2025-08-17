package io.bluebeaker.morphoverlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MorphOverlayUtils {
    private static Item morphTool;
    private static Item akashicTome;
    private static ItemStack morphToolStack;
    private static ItemStack akashicTomeStack;

    protected static final ResourceLocation OVERLAY_TEXTURES = new ResourceLocation(MorphOverlay.MODID,"textures/gui/overlays.png");

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

    public static void drawCornerIcon(float x, float y, int textureX, int textureY, float z) {
        int width = 16;
        int height = 16;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        Minecraft.getMinecraft().getTextureManager().bindTexture(OVERLAY_TEXTURES);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, z).tex((float)(textureX) * 0.03125F, (float)(textureY + height) * 0.03125F).endVertex();
        bufferbuilder.pos(x + width, y + height, z).tex((float)(textureX + width) * 0.03125F, (float)(textureY + height) * 0.03125F).endVertex();
        bufferbuilder.pos(x + width, y, z).tex((float)(textureX + width) * 0.03125F, (float)(textureY) * 0.03125F).endVertex();
        bufferbuilder.pos(x, y, z).tex((float)(textureX) * 0.03125F, (float)(textureY) * 0.03125F).endVertex();
        tessellator.draw();
    }
}
