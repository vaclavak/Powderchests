package vpp.vac.chestcounter.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import vpp.vac.chestcounter.main.Main;

public class GeneralCommand implements ICommand{
	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "Chestcounter";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Chestcounter command";
	}

	@Override
	public List<String> getCommandAliases() {
		List<String> commandAliases = new ArrayList();
		commandAliases.add("chestcount");
		commandAliases.add("cc");
		return commandAliases;
	}
	
	

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args[0].equalsIgnoreCase("reset")) {
			Main.count = 0;
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Chest counter reset!"));
		}else {
			if(args[0].equalsIgnoreCase("checkcounter") || args[0].equalsIgnoreCase("check")) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Current chest counter: " + Main.count));
			}else {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, invalid argument"));
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		// TODO Auto-generated method stub
		List<String> TabList = new ArrayList();
		TabList.add("reset");
		TabList.add("checkcounter");
		return TabList;		
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}

