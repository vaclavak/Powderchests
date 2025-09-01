package vpp.vac.chestcounter.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		return "MetaRipper";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/mr <reset|check|savecounter <directory>|loadcounter <directory>>";
	}

	@Override
	public List<String> getCommandAliases() {
		List<String> commandAliases = new ArrayList();
		commandAliases.add("metaripper");
		commandAliases.add("mr");
		return commandAliases;
	}
	
	

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if(args.length == 0) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, please specify a command"));
			return;
		}
		
		if(args[0].equalsIgnoreCase("reset")) {
			Main.count = 0;
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Chest counter reset!"));
		}else if(args[0].equalsIgnoreCase("checkcounter") || args[0].equalsIgnoreCase("check")) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Current chest counter: " + Main.count));
		}else if(args[0].equalsIgnoreCase("savecounter")) {
			if(args.length < 2) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, please specify a directory path"));
				return;
			}
			
			String directoryPath = args[1];
			File directory = new File(directoryPath);
			
			// Check if directory exists
			if(!directory.exists()) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, directory does not exist: " + directoryPath));
				return;
			}
			
			// Check if it's actually a directory
			if(!directory.isDirectory()) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, path is not a directory: " + directoryPath));
				return;
			}
			
			// Create the counter file
			File counterFile = new File(directory, "counter.txt");
			
			try {
				FileWriter writer = new FileWriter(counterFile);
				writer.write(String.valueOf(Main.count));
				writer.close();
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Counter saved to " + counterFile.getAbsolutePath()));
			} catch (IOException e) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error saving counter file: " + e.getMessage()));
			}
		}else if(args[0].equalsIgnoreCase("loadcounter")) {
			if(args.length < 2) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, please specify a directory path"));
				return;
			}
			
			String directoryPath = args[1];
			File directory = new File(directoryPath);
			
			// Check if directory exists
			if(!directory.exists()) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, directory does not exist: " + directoryPath));
				return;
			}
			
			// Check if it's actually a directory
			if(!directory.isDirectory()) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, path is not a directory: " + directoryPath));
				return;
			}
			
			// Check if the counter file exists
			File counterFile = new File(directory, "counter.txt");
			if(!counterFile.exists()) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, counter.txt file does not exist in directory: " + directoryPath));
				return;
			}
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(counterFile));
				String line = reader.readLine();
				reader.close();
				
				if(line == null || line.trim().isEmpty()) {
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, counter file is empty"));
					return;
				}
				
				int loadedCount = Integer.parseInt(line.trim());
				Main.count = loadedCount;
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Counter loaded from " + counterFile.getAbsolutePath() + " - Current count: " + Main.count));
			} catch (IOException e) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error reading counter file: " + e.getMessage()));
			} catch (NumberFormatException e) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, invalid number format in counter file"));
			}
		}else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, invalid argument"));
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
		TabList.add("savecounter");
		TabList.add("loadcounter");
		return TabList;		
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}

