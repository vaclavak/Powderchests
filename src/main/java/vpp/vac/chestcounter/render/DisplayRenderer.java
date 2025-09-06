package vpp.vac.chestcounter.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vpp.vac.chestcounter.main.Main;

public class DisplayRenderer {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        
        if (!Main.displayEnabled) {
            return;
        }
        
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRenderer = mc.fontRendererObj;
        
        String counterText = "Chests: " + Main.count;
        fontRenderer.drawStringWithShadow(counterText, Main.displayX, Main.displayY, 0xFFFFFF);
    }
}