package net.kyau.afterhours.inventory;

import net.kyau.afterhours.items.VRAD;
import net.kyau.afterhours.utils.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerItem extends Container {

  public final InventoryVRAD inventory;
  private static final int INV_START = InventoryVRAD.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
      HOTBAR_END = HOTBAR_START + 8;

  public ContainerItem(EntityPlayer player, InventoryVRAD inventoryVRAD) {
    this.inventory = inventoryVRAD;
    int i;
    // ITEM INVENTORY - you'll need to adjust the slot locations to match your texture file
    // I have them set vertically in columns of 4 to the right of the player model
    for (i = 0; i < InventoryVRAD.INV_SIZE; ++i) {
      // You can make a custom Slot if you need different behavior,
      // such as only certain item types can be put into this slot
      // We made a custom slot to prevent our inventory-storing item
      // from being stored within itself, but if you want to allow that and
      // you followed my advice at the end of the above step, then you
      // could get away with using the vanilla Slot class
      this.addSlotToContainer(new SlotItemInv(this.inventory, i, 8 + (18 * (int) (i / 3)), 17 + (18 * (i % 3))));
    }
    // If you want, you can add ARMOR SLOTS here as well, but you need to
    // make a public version of SlotArmor. I won't be doing that in this tutorial.
    /*
    for (i = 0; i < 4; ++i)
    {
        // These are the standard positions for survival inventory layout
        this.addSlotToContainer(new SlotArmor(this.player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18, i));
    } */
    // PLAYER INVENTORY - uses default locations for standard inventory texture file
    for (i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }
    // PLAYER ACTION BAR - uses default locations for standard action bar texture file
    for (i = 0; i < 9; ++i) {
      this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer player) {
    return true;
  }

  /**
   * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
   */

  @Override
  public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index) {
    ItemStack itemstack = null;
    Slot slot = (Slot) this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack()) {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();

      // If item is in our custom Inventory or armor slot
      if (index < INV_START) {
        // try to place in player inventory / action bar
        if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
          return null;
        }

        slot.onSlotChange(itemstack1, itemstack);
      }
      // Item is in inventory / hotbar, try to place in custom inventory or armor slots
      else {
        if (index >= INV_START) {
          // place in custom inventory
          if (!this.mergeItemStack(itemstack1, 0, INV_START, false)) {
            return null;
          }
        }
        // item in action bar - place in player inventory
        else if (index >= HOTBAR_START && index < HOTBAR_END + 1) {
          if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false)) {
            return null;
          }
        }
      }

      if (itemstack1.stackSize == 0) {
        slot.putStack((ItemStack) null);
      } else {
        slot.onSlotChanged();
      }

      if (itemstack1.stackSize == itemstack.stackSize) {
        return null;
      }

      slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
    }

    return itemstack;
  }

  /**
   * You should override this method to prevent the player from moving the stack that
   * opened the inventory, otherwise if the player moves it, the inventory will not
   * be able to save properly
   */
  @Override
  public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
    // this will prevent the player from interacting with the item that opened the inventory:
    if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
      return null;
    }
    return super.slotClick(slot, button, flag, player);
  }

  @Override
  public void onContainerClosed(EntityPlayer player) {
    super.onContainerClosed(player);

    if (!player.worldObj.isRemote) {
      InventoryPlayer invPlayer = player.inventory;
      for (ItemStack itemStack : invPlayer.mainInventory) {
        if (itemStack != null) {
          if (NBTHelper.hasTag(itemStack, "vradGuiOpen")) {
            NBTHelper.removeTag(itemStack, "vradGuiOpen");
          }
        }
      }

      saveInventory(player);
    }
  }

  public void saveInventory(EntityPlayer player) {
    inventory.onGuiSaved(player);
  }

  public class SlotItemInv extends Slot {

    public SlotItemInv(IInventory inv, int index, int xPos, int yPos) {
      super(inv, index, xPos, yPos);
    }

    // This is the only method we need to override so that
    // we can't place our inventory-storing Item within
    // its own inventory (thus making it permanently inaccessible)
    // as well as preventing abuse of storing a VRAD within a VRAD
    /**
     * Check if the stack is a valid item for this slot.
     */
    @Override
    public boolean isItemValid(ItemStack itemstack) {
      // Everything returns true except an instance of our Item
      return !(itemstack.getItem() instanceof VRAD);
    }

  }

}
