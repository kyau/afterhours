package net.kyau.afterhours.items;

import java.util.List;

import net.kyau.afterhours.AfterHours;
import net.kyau.afterhours.references.ModInfo;
import net.kyau.afterhours.references.Names;
import net.kyau.afterhours.utils.ItemHelper;
import net.kyau.afterhours.utils.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

public class VRAD extends BaseItem {

  public VRAD() {
    super();
    this.setUnlocalizedName(Names.Items.VRAD);
    this.maxStackSize = 1;
  }

  @Override
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    if (!world.isRemote) {
      if (player.isSneaking()) {
        InventoryEnderChest invEnderChest = player.getInventoryEnderChest();
        if (invEnderChest != null)
          player.displayGUIChest(invEnderChest);
      } else {
        // player.openGui(AfterHours.instance, AfterHours.GUI_VRAD, world, (int) player.posX, (int) player.posY, (int)
        // player.posZ);
        if (!ItemHelper.hasOwnerUUID(stack)) {
          ItemHelper.setOwner(stack, player);
          player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.LIGHT_PURPLE + "Soulbound!"));
        }

        // Set a UUID on the Alchemical Bag, if one doesn't exist already
        NBTHelper.setUUID(stack);
        // TODO Do a scan of inventory and if we find a bag with the same UUID, change it's UUID
        NBTHelper.setBoolean(stack, "vradGuiOpen", true);
        player.openGui(AfterHours.instance, AfterHours.GUI_VRAD, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
      }
    }
    return super.onItemRightClick(stack, world, player);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
    // Soulbound status / item types
    if (ItemHelper.hasOwner(stack)) {
      tooltip.add(EnumChatFormatting.DARK_PURPLE + "Soulbound");
    } else {
      tooltip.add(EnumChatFormatting.DARK_PURPLE + "Right-click to soulbind item!");
    }
    // Description
    if (Keyboard.isKeyDown(0x2A) || Keyboard.isKeyDown(0x36)) {
      tooltip.add(EnumChatFormatting.GRAY + "The VRAD or Void Remote Access Device");
      tooltip.add(EnumChatFormatting.GRAY + "is used to remotely connect through");
      tooltip.add(EnumChatFormatting.GRAY + "the void to secure storage containers.");
    } else {
      tooltip.add(EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.WHITE + "SHIFT" + EnumChatFormatting.GRAY + " for more information.");
    }
    // Owner information
    if (ItemHelper.hasOwner(stack)) {
      String owner = ItemHelper.getOwnerName(stack);
      // list.add(EnumChatFormatting.DARK_PURPLE + "Soulbound, Limited");
      if (owner.equals(player.getDisplayNameString())) {
        tooltip.add(EnumChatFormatting.GREEN + "Owner: " + owner);
      } else {
        if (ModInfo.DEBUG) {
          tooltip.add(EnumChatFormatting.RED + "Owner: " + owner);
        } else {
          tooltip.add(EnumChatFormatting.RED + "Owner: " + EnumChatFormatting.OBFUSCATED + owner);
        }
      }
    }
    super.addInformation(stack, player, tooltip, advanced);
  }
}
