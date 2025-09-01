package vpp.vac.chestcounter.main;

import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import vpp.vac.chestcounter.command.GeneralCommand;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "metaripper";
    public static final String VERSION = "1.0";
    public static final String PREFIX = "[METARIPPER] ";
    public static int count;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {   	
    	System.out.println(PREFIX + "MetaRipper Mod by vaclavak");
    	System.out.println(PREFIX + "Version: " + VERSION);
        System.out.println(PREFIX + "[INIT] INJECTION COMPLETE!");    
        MinecraftForge.EVENT_BUS.register(new ChatReceiver());
        ClientCommandHandler.instance.registerCommand(new GeneralCommand());
    }
}
