package net.gamerdragon525.sf_plus.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import net.gamerdragon525.sf_plus.model.ModelShell;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ShellRenderer extends HumanoidMobRenderer<ShellEntity, HumanoidModel<ShellEntity>> {
    public ShellRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.1f);
        this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new RenderLayer<ShellEntity, HumanoidModel<ShellEntity>>(this) {
            final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("sf_plus:textures/entities/shell.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, ShellEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entitySolid(LAYER_TEXTURE));
                EntityModel model = new ModelShell(Minecraft.getInstance().getEntityModels().bakeLayer(ModelShell.LAYER_LOCATION));
                this.getParentModel().copyPropertiesTo(model);
                model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                if (!entity.isInvisible()) {
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0));
                }
                //super.render(entity, entityYww, partialTicks, poseStack, buffer, packedLight);
            }
        });
    }

    @Override
    protected void scale(ShellEntity entity, PoseStack poseStack, float f) {
        poseStack.scale(1f, 1f, 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(ShellEntity entity) {
        return ResourceLocation.parse("dragon_lib:textures/entities/null.png");
    }
}
