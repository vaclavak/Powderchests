package vpp.vac.chestcounter.main;

import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import vpp.vac.chestcounter.command.GeneralCommand;
import vpp.vac.chestcounter.render.DisplayRenderer;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "chestcounter";
    public static final String VERSION = "1.3";
    public static final String PREFIX = "[CHESTCOUNTER] ";
    public static int count;
    public static boolean displayEnabled = false;
    public static int displayX = 10;
    public static int displayY = 10;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {   	
    	System.out.println(PREFIX + "ChestCounter Mod by vaclavak");
    	System.out.println(PREFIX + "Version: " + VERSION);
        System.out.println(PREFIX + "[INIT] INJECTION COMPLETE!");    
        MinecraftForge.EVENT_BUS.register(new ChatReceiver());
        MinecraftForge.EVENT_BUS.register(new DisplayRenderer());
        ClientCommandHandler.instance.registerCommand(new GeneralCommand());
    }
}
