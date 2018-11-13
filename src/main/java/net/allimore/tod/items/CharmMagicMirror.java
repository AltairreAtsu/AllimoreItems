package net.allimore.tod.items;

import net.allimore.tod.*;
import net.allimore.tod.Utilities.CharmLang;
import net.allimore.tod.Utilities.CharmSounds;
import net.allimore.tod.Utilities.SoundInfo;
import net.allimore.tod.Utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CharmMagicMirror {
    public static String NAME = ChatColor.AQUA + "Magic Mirror";
    public static Material MATERIAL = Material.MUSIC_DISC_WAIT;

    private static String LORE1 = CharmLang.LORE_COLOR + "Transports you back to your home location";

    private static String USE_STRING = CharmLang.POSITIVE_COLOR + "You gaze into the mirror and as you do the world shifts and bends around you.";
    private static String FAIL_STRING = CharmLang.GENERIC_FAIL_NEED_HOME;

    private static SoundInfo USE_SOUND = CharmSounds.USE_SOUND;
    private static SoundInfo FAIL_SOUND = CharmSounds.FAIL_SOUND;

    public static ItemStack CreateMagicMirror(){
        ArrayList<String> lore = new ArrayList<String>() {{ add(LORE1); }};
        return Utils.ConstructItemStack(NAME, MATERIAL, lore);
    }

    public static void RunCharm(PlayerInteractEvent event){
        event.setCancelled(true);

        Player player = event.getPlayer();

        try {
            Location home = AllimoreItems.ESSENTIALS.getUser(player).getHome("home");
            player.teleport(home);
            player.sendMessage(USE_STRING);
            USE_SOUND.PlaySound(player);
            return;
        } catch (Exception e) {
            FAIL_SOUND.PlaySound(player);
            player.sendMessage(FAIL_STRING);
            return;
        }
    }

}
