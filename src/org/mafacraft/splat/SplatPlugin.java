package org.mafacraft.splat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rnorian
 * Date: 10/18/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplatPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info(getClass().getSimpleName() + " enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    private boolean isRecording = false;
    private SplatObject recordingObject;
    private HashMap<String,SplatObject> splatObjectMap = new HashMap<String, SplatObject>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();
        if (cmd.startsWith("splat")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("only a player can issue splat action: record");
                return true;
            }

            if (args.length < 1) {
                sender.sendMessage("splat command must be followed by an action: record, save, cancel, splat");
                return true;
            }

            String splatAction = args[0].toLowerCase();
            if (splatAction.equals("record")) {
                isRecording = true;
                recordingObject = new SplatObject();

            }
            else if (splatAction.equals("save")) {
                if (args.length < 2) {
                    sender.sendMessage("splat save requires a splat-name - example 'splat save <myObject>'");
                    return true;
                }

                isRecording = false;

                String objectName = args[1].toLowerCase();
                saveSplatObject(objectName);
            }
            return true;
        }

        return super.onCommand(sender, command, label, args);
    }

    private void saveSplatObject(String pSplatName) {
        splatObjectMap.put(pSplatName, recordingObject);
        recordingObject = new SplatObject();
    }
}



class SplatObject implements Listener {

}