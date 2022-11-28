package net.jenot.genies.potion;

import net.jenot.genies.Genies;
import net.jenot.genies.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Genies.MOD_ID);

    public static final RegistryObject<Potion> FREEZE_POTION = POTIONS.register("freeze_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.FREEZE.get(), 200, 0)));



    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}