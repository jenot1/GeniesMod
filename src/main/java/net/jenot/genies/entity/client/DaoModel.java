package net.jenot.genies.entity.client;

import net.jenot.genies.Genies;
import net.jenot.genies.entity.custom.DaoEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class DaoModel extends AnimatedGeoModel<DaoEntity> {
    @Override
    public ResourceLocation getModelLocation(DaoEntity object) {
        return new ResourceLocation(Genies.MOD_ID, "geo/dao.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DaoEntity object) {
        return new ResourceLocation(Genies.MOD_ID, "textures/entity/dao/dao.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DaoEntity animatable) {
        return new ResourceLocation(Genies.MOD_ID, "animations/dao.animation.json");
    }

//        @Override
//    public void setLivingAnimations(DaoEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
//        super.setLivingAnimations(entity, uniqueID, customPredicate);
//        IBone head = this.getAnimationProcessor().getBone("head");
//
//        LivingEntity entityIn = (LivingEntity) entity;
//        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
//        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
//        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
//    }

    @Override
    public void setLivingAnimations(DaoEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
