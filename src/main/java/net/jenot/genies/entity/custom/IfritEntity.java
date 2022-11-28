package net.jenot.genies.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static java.lang.Math.sqrt;

public class IfritEntity extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public IfritEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }


//    public void start() {
//        super.start();
//        IfritEntity.this.setAggressive(true);
//    }


    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0f)
                .add(Attributes.ATTACK_SPEED, 1.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(10, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new IfritRangedAttack(this, 16));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }





    static class IfritRangedAttack extends Goal {
        private IfritEntity ifrit;
        public int chargeTime;

        private final float attackRadiusSqr;

        public int distanceToPlayer(IfritEntity ifritEntity){
            this.ifrit = ifritEntity;
            LivingEntity livingentity = this.ifrit.getTarget();
            int a1 = (int) this.ifrit.getX();
            int a2 = (int) this.ifrit.getZ();
            int b1 = (int) livingentity.getX();
            int b2 = (int) livingentity.getZ();

            double d = sqrt( ( a1 - b1 )^2 + ( a2 - b2 )^2);

            return (int) d;
        }

        public IfritRangedAttack(IfritEntity ifritEntity, float pAttackRadius) {
            this.ifrit = ifritEntity;
            this.attackRadiusSqr = pAttackRadius * pAttackRadius;
        }
        public boolean canUse(IfritEntity ifritEntity ) {
            this.ifrit = ifritEntity;
            LivingEntity livingentity = this.ifrit.getTarget();
            int a1 = (int) this.ifrit.getX();
            int a2 = (int) this.ifrit.getZ();
            int b1 = (int) livingentity.getX();
            int b2 = (int) livingentity.getZ();

            double d = sqrt( ( a1 - b1 )^2 + ( a2 - b2 )^2);

            return this.ifrit.getTarget() != null && d > 8 && d < 32;
        }


        @Override
        public boolean canUse() {
            return false;
        }

        public void start() {
            this.chargeTime = 0;
        }


        public void stop() {
            this.ifrit.setCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }


        public void tick() {
            LivingEntity livingentity = this.ifrit.getTarget();
            if (livingentity != null) {
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.ifrit) < 4096.0D && this.ifrit.hasLineOfSight(livingentity)) {
                    Level level = this.ifrit.level;
                    ++this.chargeTime;
                    if (this.chargeTime == 10 && !this.ifrit.isSilent()) {
                        level.levelEvent((Player)null, 1015, this.ifrit.blockPosition(), 0);
                    }

                    if (this.chargeTime == 20) {
                        double d1 = 4.0D;
                        Vec3 vec3 = this.ifrit.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.ifrit.getX() + vec3.x * 4.0D);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.ifrit.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.ifrit.getZ() + vec3.z * 4.0D);
                        if (!this.ifrit.isSilent()) {
                            level.levelEvent((Player)null, 1016, this.ifrit.blockPosition(), 0);
                        }

                        LargeFireball largefireball = new LargeFireball(level, this.ifrit, d2, d3, d4, this.ifrit.getExplosionPower());
                        largefireball.setPos(this.ifrit.getX() + vec3.x * 4.0D, this.ifrit.getY(0.5D) + 0.5D, largefireball.getZ() + vec3.z * 4.0D);
                        level.addFreshEntity(largefireball);
                        this.chargeTime = -40;
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                this.ifrit.setCharging(this.chargeTime > 10);
            }
        }
    }

    private int getExplosionPower() {
        return (int) 2.0F;
    }

    private void setCharging(boolean b) {
    }




    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ifrit.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ifrit.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState MeeleAttackPredicate(AnimationEvent animationEvent) {
        if (this.swinging && animationEvent.getController().getAnimationState().equals(AnimationState.Stopped)){
            animationEvent.getController().markNeedsReload();
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ifrit.meele_attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

//
//    private PlayState RangedAttackPredicate(AnimationEvent animationEvent) {
//        if (this.){
//            animationEvent.getController().markNeedsReload();
//            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ifrit.ranged_attack", false));
//            this. = false;
//        }
//        return PlayState.CONTINUE;
//    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "meele_attackController",
                0, this::MeeleAttackPredicate));
//        data.addAnimationController(new AnimationController(this, "ranged_attackController",
//                0, this::RangedAttackPredicate));
    }







    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.HOGLIN_STEP, 0.15F, 0.1F);
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ZOMBIE_HORSE_AMBIENT, 0.15F, 0.1F);
    }

    //    protected SoundEvent getAmbientSound() {
//        return SoundEvents.ZOMBIE_HORSE_AMBIENT;
//    }

    @Override
    protected void playHurtSound(DamageSource pSource) {
        this.playSound(SoundEvents.ILLUSIONER_HURT, 0.15F, 0.1F);
    }


//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundEvents.ZOMBIE_HURT;
//    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }


}