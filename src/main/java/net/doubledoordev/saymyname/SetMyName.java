package net.doubledoordev.saymyname;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.server.packs.resources.ReloadableResourceManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("setmyname")
public class SetMyName
{

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public SetMyName()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::reload);
    }

    public void reload(AddReloadListenerEvent event)
    {
        ((ReloadableResourceManager) event.getDataPackRegistries().getResourceManager()).registerReloadListener(new NameDumper());
    }
}
