package vpp.vac.chestcounter.main;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ChatReceiver {

	@SubscribeEvent
	public void onChatReceived(ClientChatReceivedEvent event) {
		// Get the unformatted text of the chat message
		String message = event.message.getUnformattedText();

		// Optional: print full chat message for debugging
		System.out.println("Chat Message Received: " + message);

		// Split into lines (just in case)
		String[] lines = message.split("\n");
		for (String line : lines) {
			if (line.contains("You uncovered a treasure chest!")) {
				Main.count++;
				System.out.println("Treasure chest found! New count is: " + Main.count);
			}
		}
	}
}
