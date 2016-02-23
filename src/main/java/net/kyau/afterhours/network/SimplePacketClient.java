package net.kyau.afterhours.network;

import io.netty.buffer.ByteBuf;
import net.kyau.afterhours.network.SimplePacketClient.SimpleClientMessage;
import net.kyau.afterhours.references.ModInfo;
import net.kyau.afterhours.references.Ref;
import net.kyau.afterhours.utils.LogHelper;
import net.kyau.afterhours.utils.SoundUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SimplePacketClient implements IMessageHandler<SimpleClientMessage, IMessage> {

  @Override
  public IMessage onMessage(SimpleClientMessage message, MessageContext ctx) {
    // just to make sure that the side is correct
    if (ctx.side.isClient()) {
      if (ModInfo.DEBUG)
        LogHelper.info("recieved packet from server! (" + message.type + ": " + message.text + ")");

      if (message.type == 1) {
        SoundUtil.playSound(message.text);
      } else if (message.type == 2) {
        Long time = Long.valueOf(message.text);
        EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(message.player);
        NBTTagCompound playerNBT = player.getEntityData();
        playerNBT.setLong(Ref.NBT.LASTUSE, time);
      }
    }
    return null;
  }

  public static class SimpleClientMessage implements IMessage {

    private String text;
    private int type;
    private String simpleString;
    private boolean simpleBool;
    private int player;

    // this constructor is required otherwise you'll get errors (used
    // somewhere in fml through reflection)
    public SimpleClientMessage() {
    }

    public SimpleClientMessage(int type, String text, int player) {
      this.text = text;
      this.type = type;
      this.player = player;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
      type = buf.readInt();
      text = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
      buf.writeInt(this.type);
      ByteBufUtils.writeUTF8String(buf, this.text);
    }
  }
}