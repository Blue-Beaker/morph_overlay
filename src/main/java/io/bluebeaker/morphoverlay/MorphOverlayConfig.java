package io.bluebeaker.morphoverlay;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;

@Config(modid = MorphOverlay.MODID,type = Type.INSTANCE,category = "general")
public class MorphOverlayConfig {
    @Comment("Enable the overlay")
    @LangKey("config.morphoverlay.enable.name")
    public static boolean enable = true;
}