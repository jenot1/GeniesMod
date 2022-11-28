package net.jenot.genies;

import net.jenot.genies.block.ModBlocks;
import net.jenot.genies.block.entity.ModBlockEntities;
import net.jenot.genies.block.entity.ModWoodTypes;
import net.jenot.genies.effect.ModEffects;
import net.jenot.genies.enchantment.ModEnchantments;
import net.jenot.genies.entity.ModEntityTypes;
import net.jenot.genies.entity.client.DaoRenderer;
import net.jenot.genies.entity.client.IfritRenderer;
import net.jenot.genies.fluid.ModFluids;
import net.jenot.genies.item.ModItems;
import net.jenot.genies.painting.ModPaintings;
import net.jenot.genies.particle.ModParticles;
import net.jenot.genies.potion.ModPotions;
import net.jenot.genies.recipe.ModRecipes;
import net.jenot.genies.screen.GemCuttingStationScreen;
import net.jenot.genies.screen.ModMenuTypes;
import net.jenot.genies.sound.ModSounds;
import net.jenot.genies.util.BetterBrewingRecipe;
import net.jenot.genies.util.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Genies.MOD_ID)
public class Genies {
    public static final String MOD_ID = "genies";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();


    public Genies() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModEnchantments.register(eventBus);
        ModBlocks.register(eventBus);
        ModFluids.register(eventBus);
        ModPaintings.register(eventBus);
        ModSounds.register(eventBus);
        ModEffects.register(eventBus);
        ModPotions.register(eventBus);
        ModParticles.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        ModEntityTypes.register(eventBus);


        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.EBONY_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.EBONY_TRAPDOOR.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_ROSE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_PINK_ROSE.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WINTER_WINDOW.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_CUTTING_STATION.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CUCUMBER_PLANT.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.EBONY_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.EBONY_SAPLING.get(), RenderType.cutout());

        ModItemProperties.addCustomItemProperties();

        MenuScreens.register(ModMenuTypes.GEM_CUTTING_STATION_MENU.get(), GemCuttingStationScreen::new);

        WoodType.register(ModWoodTypes.EBONY);
        BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);

        EntityRenderers.register(ModEntityTypes.IFRIT.get(), IfritRenderer::new);
        EntityRenderers.register(ModEntityTypes.DAO.get(), DaoRenderer::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PINK_ROSE.getId(), ModBlocks.POTTED_PINK_ROSE);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                    ModItems.CITRINE.get(), ModPotions.FREEZE_POTION.get()));

            Sheets.addWoodType(ModWoodTypes.EBONY);
        });
    }
}