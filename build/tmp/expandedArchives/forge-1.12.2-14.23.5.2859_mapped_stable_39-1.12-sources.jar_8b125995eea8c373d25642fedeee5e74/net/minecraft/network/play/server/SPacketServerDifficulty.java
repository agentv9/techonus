package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPacketServerDifficulty implements Packet<INetHandlerPlayClient>
{
    private EnumDifficulty difficulty;
    private boolean difficultyLocked;

    public SPacketServerDifficulty()
    {
    }

    public SPacketServerDifficulty(EnumDifficulty difficultyIn, boolean difficultyLockedIn)
    {
        this.difficulty = difficultyIn;
        this.difficultyLocked = difficultyLockedIn;
    }

    public void processPacket(INetHandlerPlayClient handler)
    {
        handler.handleServerDifficulty(this);
    }

    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.difficulty = EnumDifficulty.byId(buf.readUnsignedByte());
    }

    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeByte(this.difficulty.getId());
    }

    @SideOnly(Side.CLIENT)
    public boolean isDifficultyLocked()
    {
        return this.difficultyLocked;
    }

    @SideOnly(Side.CLIENT)
    public EnumDifficulty getDifficulty()
    {
        return this.difficulty;
    }
}
