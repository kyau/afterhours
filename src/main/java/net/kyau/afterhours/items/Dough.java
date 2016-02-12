package net.kyau.afterhours.items;

import java.util.List;

import net.kyau.afterhours.references.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dough extends BaseItem {

  public Dough() {
    super();
    this.setUnlocalizedName(Ref.ItemID.DOUGH);
    this.maxStackSize = 64;
  }

  @Override
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    return super.onItemRightClick(stack, world, player);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, player, tooltip, advanced);
  }
}
