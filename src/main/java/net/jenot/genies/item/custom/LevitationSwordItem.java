package net.jenot.genies.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class LevitationSwordItem extends SwordItem {
    public LevitationSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        //                                                                     time     strength      ambient  particles visibility
        pTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 2, false, false), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
