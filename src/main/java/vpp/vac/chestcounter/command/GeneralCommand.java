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
import vpp.vac.chestcounter.config.ConfigManager;
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
		return "/cc <reset|check|savecounter <directory>|loadcounter <directory>|display <on/off>|display <x> <y>>";
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
		}else if(args[0].equalsIgnoreCase("display")) {
			if(args.length < 2) {
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, please specify 'on', 'off', or coordinates (x y)"));
				return;
			}
			
			if(args[1].equalsIgnoreCase("on")) {
				Main.displayEnabled = true;
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Display enabled! Counter will now be shown on screen."));
				ConfigManager.saveConfig();
			} else if(args[1].equalsIgnoreCase("off")) {
				Main.displayEnabled = false;
				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Display disabled!"));
				ConfigManager.saveConfig();
			} else {
				// Try to parse as coordinates
				if(args.length < 3) {
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, please specify both x and y coordinates"));
					return;
				}
				
				try {
					int x = Integer.parseInt(args[1]);
					int y = Integer.parseInt(args[2]);
					
					if(x < 0 || y < 0) {
						sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, coordinates must be positive"));
						return;
					}
					
					Main.displayX = x;
					Main.displayY = y;
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + Main.PREFIX + "Display position updated to (" + x + ", " + y + ")"));
					ConfigManager.saveConfig();
				} catch (NumberFormatException e) {
					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + Main.PREFIX + "Error, invalid coordinates. Please use numbers."));
				}
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
		TabList.add("display");
		return TabList;		
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}

