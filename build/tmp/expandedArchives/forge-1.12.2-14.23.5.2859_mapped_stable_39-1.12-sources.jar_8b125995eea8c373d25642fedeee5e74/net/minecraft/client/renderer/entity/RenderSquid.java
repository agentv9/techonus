package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSquid extends RenderLiving<EntitySquid>
{
    private static final ResourceLocation SQUID_TEXTURES = new ResourceLocation("textures/entity/squid.png");

    public RenderSquid(RenderManager p_i47192_1_)
    {
        super(p_i47192_1_, new ModelSquid(), 0.7F);
    }

    protected ResourceLocation getEntityTexture(EntitySquid entity)
    {
        return SQUID_TEXTURES;
    }

    protected void applyRotations(EntitySquid entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
    {
        float f = entityLiving.prevSquidPitch + (entityLiving.squidPitch - entityLiving.prevSquidPitch) * partialTicks;
        float f1 = entityLiving.prevSquidYaw + (entityLiving.squidYaw - entityLiving.prevSquidYaw) * partialTicks;
        GlStateManager.translate(0.0F, 0.5F, 0.0F);
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, -1.2F, 0.0F);
    }

    protected float handleRotationFloat(EntitySquid livingBase, float partialTicks)
    {
        return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
    }
}
