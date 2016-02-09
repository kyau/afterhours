package net.kyau.afterhours;

import javax.annotation.Nonnull;

import net.kyau.afterhours.config.AfterHoursTab;
import net.kyau.afterhours.event.GuiHandler;
import net.kyau.afterhours.init.ModItems;
import net.kyau.afterhours.items.RecipeManager;
import net.kyau.afterhours.network.PacketHandler;
import net.kyau.afterhours.proxy.IProxy;
import net.kyau.afterhours.references.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ModInfo.MOD_ID,
     name = ModInfo.MOD_NAME,
     version = ModInfo.MOD_VERSION,
     acceptedMinecraftVersions = "[1.8.9]",
     dependencies = "required-after:Forge@[11.15.1.1722,);")
public class AfterHours {

  @Mod.Instance(ModInfo.MOD_ID)
  public static AfterHours instance;

  @SidedProxy(clientSide = ModInfo.PROXY_CLIENT,
              serverSide = ModInfo.PROXY_SERVER)
  public static IProxy proxy;

  public static CreativeTabs AfterHoursTab = new AfterHoursTab(CreativeTabs.getNextID(), "AfterHours");

  public static int guiIndex = 0;
  public static final int GUI_VOIDJOURNAL = guiIndex++;
  public static final int GUI_VRAD = guiIndex++;

  @Mod.EventHandler
  public void preInit(@Nonnull FMLPreInitializationEvent event) {
    // LogManager.getLogger(ModInfo.MOD_ID).log(Level.INFO, "preInit() START");
    // hard-coded mcmod.info
    event.getModMetadata().autogenerated = false;
    event.getModMetadata().authorList.add(ModInfo.MOD_AUTHOR);
    event.getModMetadata().description = ModInfo.MOD_DESC;
    event.getModMetadata().url = ModInfo.MOD_URL;
    event.getModMetadata().logoFile = ModInfo.MOD_LOGO;
    PacketHandler.init();
    proxy.registerEventHandlers();
    proxy.registerKeybindings();
    ModItems.init(event);
    // LogManager.getLogger(ModInfo.MOD_ID).log(Level.INFO, "preInit() END");
  }

  @Mod.EventHandler
  public void init(@Nonnull FMLInitializationEvent event) {
    // LogManager.getLogger(ModInfo.MOD_ID).log(Level.INFO, "init() START");
    proxy.initRenderingAndTextures();
    RecipeManager.init();

    NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    // LogManager.getLogger(ModInfo.MOD_ID).log(Level.INFO, "init() END");
  }

  @Mod.EventHandler
  public void postInit(@Nonnull FMLPostInitializationEvent event) {
    // LogManager.getLogger(ModInfo.MOD_ID).log(Level.INFO, "postInit() START");
    RecipeManager.post();
    // LogManager.getLogger(ModInfo.MOD_ID).log(Level.INFO, "postInit() END");
  }
}
