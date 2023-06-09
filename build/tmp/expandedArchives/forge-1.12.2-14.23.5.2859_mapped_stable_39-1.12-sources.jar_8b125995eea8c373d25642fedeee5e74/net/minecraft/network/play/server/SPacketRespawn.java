package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPacketRespawn implements Packet<INetHandlerPlayClient>
{
    private int dimensionID;
    private EnumDifficulty difficulty;
    private GameType gameType;
    private WorldType worldType;

    public SPacketRespawn()
    {
    }

    public SPacketRespawn(int dimensionIdIn, EnumDifficulty difficultyIn, WorldType worldTypeIn, GameType gameModeIn)
    {
        this.dimensionID = dimensionIdIn;
        this.difficulty = difficultyIn;
        this.gameType = gameModeIn;
        this.worldType = worldTypeIn;
    }

    public void processPacket(INetHandlerPlayClient handler)
    {
        handler.handleRespawn(this);
    }

    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.dimensionID = buf.readInt();
        this.difficulty = EnumDifficulty.byId(buf.readUnsignedByte());
        this.gameType = GameType.getByID(buf.readUnsignedByte());
        this.worldType = WorldType.byName(buf.readString(16));

        if (this.worldType == null)
        {
            this.worldType = WorldType.DEFAULT;
        }
    }

    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeInt(this.dimensionID);
        buf.writeByte(this.difficulty.getId());
        buf.writeByte(this.gameType.getID());
        buf.writeString(this.worldType.getName());
    }

    @SideOnly(Side.CLIENT)
    public int getDimensionID()
    {
        return this.dimensionID;
    }

    @SideOnly(Side.CLIENT)
    public EnumDifficulty getDifficulty()
    {
        return this.difficulty;
    }

    @SideOnly(Side.CLIENT)
    public GameType getGameType()
    {
        return this.gameType;
    }

    @SideOnly(Side.CLIENT)
    public WorldType getWorldType()
    {
        return this.worldType;
    }
}
