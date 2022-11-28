package net.jenot.genies.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jenot.genies.Genies;
import net.jenot.genies.entity.custom.IfritEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class IfritRenderer extends GeoEntityRenderer<IfritEntity> {
    public IfritRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IfritModel());
        this.shadowRadius = 0.75f;
    }

    @Override
    public ResourceLocation getTextureLocation(IfritEntity instance) {
        return new ResourceLocation(Genies.MOD_ID, "textures/entity/ifrit/ifrit.png");
    }

    @Override
    public RenderType getRenderType(IfritEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
