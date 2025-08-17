package io.bluebeaker.morphoverlay.mixin;

import io.bluebeaker.morphoverlay.MorphOverlayConfig;
import io.bluebeaker.morphoverlay.MorphOverlayUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem {
    @Shadow public abstract void renderItem(ItemStack stack, IBakedModel model);

    @Shadow public abstract IBakedModel getItemModelWithOverrides(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entitylivingbaseIn);

    @Shadow public float zLevel;

    @Inject(method = "renderItemOverlayIntoGUI",at = @At("HEAD"))
    public void renderOverlays(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, String text, CallbackInfo ci){
        if(!MorphOverlayConfig.enable) return;
        if(MorphOverlayUtils.isMorphedTool(stack)){
            MorphOverlayUtils.drawCornerIcon( xPosition, yPosition, 0,0,this.zLevel);
        }
        if(MorphOverlayUtils.isAkashicTome(stack)){
            MorphOverlayUtils.drawCornerIcon(xPosition, yPosition, 0,16,this.zLevel);
        }
    }

}
