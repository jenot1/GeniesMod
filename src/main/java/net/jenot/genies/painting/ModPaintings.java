package net.jenot.genies.painting;

import net.jenot.genies.Genies;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOITVES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Genies.MOD_ID);

    public static final RegistryObject<Motive> MARATHON =
            PAINTING_MOITVES.register("marathon", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> FAMILY =
            PAINTING_MOITVES.register("family", () -> new Motive(16, 32));

    public static void register(IEventBus eventBus){
        PAINTING_MOITVES.register(eventBus);
    }
}
