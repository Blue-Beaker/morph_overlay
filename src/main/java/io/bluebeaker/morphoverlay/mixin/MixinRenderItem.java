package io.bluebeaker.morphoverlay.mixin;

import io.bluebeaker.morphoverlay.MorphOverlayHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem {
    @Shadow public abstract void renderItemIntoGUI(ItemStack stack, int x, int y);
    @Inject(method = "renderItemOverlayIntoGUI",at = @At("HEAD"))
    public void renderOverlays(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, String text, CallbackInfo ci){
        if(MorphOverlayHandler.isMorphedTool(stack)){
            morph_overlay$drawCornerIcon(xPosition, yPosition, MorphOverlayHandler.getMorphToolStack());
        }
        if(MorphOverlayHandler.isAkashicTome(stack)){
            morph_overlay$drawCornerIcon(xPosition, yPosition, MorphOverlayHandler.getAkashicTomeStack());
        }
    }

    @Unique
    private void morph_overlay$drawCornerIcon(int xPosition, int yPosition, ItemStack AkashicTomeStack) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(xPosition, yPosition+8, 0);
        GlStateManager.scale(0.5, 0.5, 1);
        this.renderItemIntoGUI(AkashicTomeStack, 0, 0);
        GlStateManager.popMatrix();
    }
}
