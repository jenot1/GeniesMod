package net.jenot.genies.event;

import net.jenot.genies.Genies;
import net.jenot.genies.entity.ModEntityTypes;
import net.jenot.genies.entity.custom.IfritEntity;
import net.jenot.genies.event.loot.CoalCokeFromCreeperAdditionModifier;
import net.jenot.genies.event.loot.CucumberSeedsFromGrassAdditionModifier;
import net.jenot.genies.event.loot.DowsingRodInIglooAdditionModifier;
import net.jenot.genies.particle.ModParticles;
import net.jenot.genies.particle.custom.CitrineParticles;
import net.jenot.genies.recipe.GemCuttingStationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Genies.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new CucumberSeedsFromGrassAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Genies.MOD_ID,"cucumber_seeds_from_grass")),
                new DowsingRodInIglooAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Genies.MOD_ID,"dowsing_rod_in_igloo")),
                new CoalCokeFromCreeperAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Genies.MOD_ID,"coal_coke_from_creeper"))
        );
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, GemCuttingStationRecipe.Type.ID, GemCuttingStationRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.CITRINE_PARTICLES.get(),
                CitrineParticles.Provider::new);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.IFRIT.get(), IfritEntity.setAttributes());
        event.put(ModEntityTypes.DAO.get(), IfritEntity.setAttributes());
    }
}
