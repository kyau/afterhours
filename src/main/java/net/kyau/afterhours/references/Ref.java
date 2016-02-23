package net.kyau.afterhours.references;

import net.kyau.afterhours.config.ConfigHandler;

public class Ref {

  public static final class BlockID {

    public static final String DARKMATTERCLUSTER = "darkmattercluster";
    public static final String VOIDSTONE = "voidstone";
    public static final String INFUSED_VOIDSTONE = "infused_voidstone";
    public static final String QUANTUM_CHARGEPAD = "quantum_chargepad";
    public static final String QUANTUM_STABILIZER = "quantum_stabilizer";
  }

  public static final class ItemID {

    public static final String ANTENNA = "antenna";
    public static final String DARKMATTER = "darkmatter";
    public static final String DARKMATTER_SWORD = "darkmatter_sword";
    public static final String DARKMATTER_SHOVEL = "darkmatter_shovel";
    public static final String DARKMATTER_PICKAXE = "darkmatter_pickaxe";
    public static final String DARKMATTER_AXE = "darkmatter_axe";
    public static final String DARKMATTER_BOOTS = "darkmatter_boots";
    public static final String DARKMATTER_CHESTPLATE = "darkmatter_chestplate";
    public static final String DARKMATTER_LEGGINGS = "darkmatter_leggings";
    public static final String DARKMATTER_HELMET = "darkmatter_helmet";
    public static final String DOUGH = "dough";
    public static final String QRD = "qrd";
    public static final String QUANTUMROD = "quantumrod";
    public static final String RAWHIDE = "rawhide";
    public static final String SINGULARITY = "singularity";
    public static final String STABLECORE = "stablecore";
    public static final String UNSTABLECORE = "unstablecore";
    public static final String UNSTABLE_DARKMATTER = "unstable_darkmatter";
    public static final String VOIDCRYSTAL = "voidcrystal";
    public static final String VOIDJOURNAL = "voidjournal";
    public static final String VOIDPEARL = "voidpearl";
    public static final String WORMHOLE_MANIPULATOR = "wormhole_manipulator";
  }

  public static final class ItemCooldown {

    public static int VOIDPEARL = ConfigHandler.cooldownVoidPearl;
    public static int WORMHOLE_MANIPULATOR = ConfigHandler.cooldownWormholeManipulator;
  }

  public static final class ItemStat {

    public static final String IMPRINTED = "Imprinted";
    public static final String LIMITED = "Limited";
    public static final int ENERGY_DARKMATTER_CHESTPLATE = 5000;
    public static int ENERGY_WORMHOLE_MANIPULATOR = ConfigHandler.wormholeEnergyMax;
    public static int ENERGYONUSE_WORMHOLE_MANIPULATOR = ConfigHandler.wormholeEnergyOnUse;
    public static int WORMHOLE_MANIPULATOR_PERTICK = ConfigHandler.wormholeEnergyPerTick;
  }

  public static final class NBT {

    public static final String DISPLAY = "display";
    public static final String ENERGY_LEVEL = "energyLevel";
    public static final String ENERGY_MAX = "energyMax";
    public static final String ITEMS = "Items";
    public static final String ITEMS_MOD = "ItemsMod";
    public static final String MODE = "mode";
    public static final String OWNER = "Owner";
    public static final String LASTUSE = "LastUse";
    public static final String OWNER_UUID_MOST_SIG = "ownerUUIDMostSig";
    public static final String OWNER_UUID_LEAST_SIG = "ownerUUIDLeastSig";
    public static final String UUID_MOST_SIG = "UUIDMostSig";
    public static final String UUID_LEAST_SIG = "UUIDLeastSig";
    public static final String QRD_GUI_OPEN = "qrdGuiOpen";
    public static final String QS_GUI_OPEN = "qsGuiOpen";
    public static final String FACING = "teFacing";
    public static final String STATE = "teState";
    public static final String CUSTOM_NAME = "CustomName";
  }

  public static final class Containers {

    public static final String INVENTORY = ModInfo.MOD_ID + ".container:inventory";
    public static final String QRD_MAIN = ModInfo.MOD_ID + ".container:" + ItemID.QRD + "Main";
    public static final String QRD_VOID = ModInfo.MOD_ID + ".container:" + ItemID.QRD + "Void";
    public static final int QRD_MAIN_ROWS = 5;
    public static final int QRD_MAIN_COLS = 9;
    public static final String VOID_SYNC_TRUE = "Sync";
    public static final String VOID_SYNC_FALSE = "";
    public static final String QUANTUM_STABILIZER = ModInfo.MOD_ID + ".container:" + BlockID.QUANTUM_STABILIZER;
  }

  public static final class Dimension {

    public static int DIM = ConfigHandler.idDimension;
    public static int BIOMEID = ConfigHandler.idBiome;
    public static final String INTERNAL_NAME = "void";
  }

  public static final class DarkMatter {

    public static final int DURABILITY = 2431;
    public static final int HARVEST_LEVEL = 4;
    public static final float EFFICIENCY = 50.0F;
    public static final float DAMAGE = 9.42F;
    public static final String NAME = "Dark Matter";
  }

  public static final class Enchant {

    public static final String ABSORPTION = "Absorption";
    public static final int ABSORPTION_ID = 85;
    public static final String ENTANGLEMENT = "Entanglement";
    public static final int ENTANGLEMENT_ID = 86;
    public static final String GRAVITATION = "Gravitation";
    public static final int GRAVITATION_ID = 87;
    public static final String QUANTUMBOOST = "Quantum Boost";
    public static final int QUANTUMBOOST_ID = 88;
    public static final String QUANTUMDISPLAY = "Quantum Display";
    public static final int QUANTUMDISPLAY_ID = 89;
  }

  public static final class Translation {

    public static final String MORE_INFORMATION = "afterhours.msg.more_information";
    public static final String IMPRINT_SUCCESS = "afterhours.msg.imprint_success";
    public static final String IMPRINT_SCAN_FAILED = "afterhours.msg.imprint_scan_failed";
    public static final String INVALID_DIM = "afterhours.msg.invalid_dimension";
    public static final String PREIMPRINT = "afterhours.msg.preimprint";
    public static final String IMPRINTED = "afterhours.itemstat.imprinted";
    public static final String COOLDOWN = "afterhours.itemstat.cooldown";
    public static final String OWNER = "afterhours.itemstat.owner";
    public static final String ENERGY = "afterhours.itemstat.energy";
  }

  public static final class Vanilla {

    public static boolean TOOLTIP = ConfigHandler.vanillaTooltip;
  }

  public static final class BlockStat {

    public static final int STABILIZER_TIME = 200;
    public static final int STABILIZER_DARKMATTER_TIME = 6000;
    public static final int STABILIZER_QUANTUMROD_TIME = 12000;
    public static final int CHARGEPAD_PERTICK = 11;
  }
}
