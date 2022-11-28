package net.jenot.genies.entity;

import net.jenot.genies.Genies;
import net.jenot.genies.entity.custom.DaoEntity;
import net.jenot.genies.entity.custom.IfritEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Genies.MOD_ID);

    public static final RegistryObject<EntityType<IfritEntity>> IFRIT =
            ENTITY_TYPES.register("ifrit",
                    () -> EntityType.Builder.of(IfritEntity::new, MobCategory.CREATURE)
                            .sized(1.2f, 3.0f)
                            .build(new ResourceLocation(Genies.MOD_ID, "ifrit").toString()));

    public static final RegistryObject<EntityType<DaoEntity>> DAO =
            ENTITY_TYPES.register("dao",
                    () -> EntityType.Builder.of(DaoEntity::new, MobCategory.CREATURE)
                            .sized(1.5f, 3.5f)
                            .build(new ResourceLocation(Genies.MOD_ID, "dao").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}