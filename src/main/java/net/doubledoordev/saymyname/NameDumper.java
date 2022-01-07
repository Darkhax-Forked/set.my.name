package net.doubledoordev.saymyname;

import java.util.ArrayList;

import net.minecraft.core.NonNullList;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class NameDumper implements SyncReloadListener
{
    @Override
    public void reloadSync()
    {
        SetMyName.LOGGER.error("====SET.MY.NAME SEARCH TAG====");
        SetMyName.LOGGER.error("This is a list of all the missing Translation keys from the mods you have installed! If there are none, Great!");

        ArrayList<String> missingTranslations = new ArrayList<>(Bootstrap.getMissingTranslations());

        // This is required to catch special items/blocks because of goofy stuff like potions.
        ForgeRegistries.ITEMS.forEach(item -> {
            NonNullList<ItemStack> list = NonNullList.create();
            item.fillItemCategory(CreativeModeTab.TAB_SEARCH, list);
            list.forEach(itemStack -> {
                //Requires square braces to be added to the descriptor ID because display name resource location to string adds them.
                if (itemStack.getDisplayName().getString().equals("[" + itemStack.getDescriptionId() + "]") && !missingTranslations.contains(itemStack.getDescriptionId()))
                {
                    missingTranslations.add(itemStack.getDescriptionId());
                }
            });
        });

        SetMyName.LOGGER.error("SetMyName has found " + (missingTranslations.size()) + " missing lang keys! " +
                "Please make sure to inform devs of these missing keys!");
        SetMyName.LOGGER.error("Each entry is automatically prepared for inserting into a language json file, just copy paste!");

        missingTranslations.forEach(s -> SetMyName.LOGGER.error("\"" + s + "\": \" \","));
    }
}
