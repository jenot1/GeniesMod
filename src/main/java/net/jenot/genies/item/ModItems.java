package net.jenot.genies.item;


import net.jenot.genies.Genies;
import net.jenot.genies.block.ModBlocks;
import net.jenot.genies.entity.ModEntityTypes;
import net.jenot.genies.fluid.ModFluids;
import net.jenot.genies.item.custom.CoalCokeItem;
import net.jenot.genies.item.custom.DataTabletItem;
import net.jenot.genies.item.custom.DowsingRodItem;
import net.jenot.genies.item.custom.LevitationSwordItem;
import net.jenot.genies.item.custom.ModArmorItem;
import net.jenot.genies.sound.ModSounds;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Genies.MOD_ID);


    //Items
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> MAGIC_DUST = ITEMS.register("magic_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new RecordItem(4, ModSounds.BAR_BRAWL,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).stacksTo(1)));
    public static final RegistryObject<Item> DATA_TABLET = ITEMS.register("data_tablet",
            () -> new DataTabletItem(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).stacksTo(1)));

    public static final RegistryObject<Item> HONEY_BUCKET = ITEMS.register("honey_bucket",
            () -> new BucketItem(ModFluids.HONEY_FLUID,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).stacksTo(1)));


    //BlocksW/OItems

    public static final RegistryObject<Item> EBONY_SIGN = ITEMS.register("ebony_sign",
            () -> new SignItem(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).stacksTo(16),
                    ModBlocks.EBONY_SIGN.get(), ModBlocks.EBONY_WALL_SIGN.get()));

    //Food

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).food(ModFoods.CUCUMBER)));

    //Crops

    public static final RegistryObject<Item> CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CUCUMBER_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    //Fuel

    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke",
            () -> new CoalCokeItem(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    //Tools

    public static final RegistryObject<Item> CITRINE_SWORD = ITEMS.register("citrine_sword",
            () -> new LevitationSwordItem(ModTiers.CITRINE,2,3f,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_PICKAXE = ITEMS.register("citrine_pickaxe",
            () -> new PickaxeItem(ModTiers.CITRINE,1,1f,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_SHOVEL = ITEMS.register("citrine_shovel",
            () -> new ShovelItem(ModTiers.CITRINE,0,1f,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_AXE = ITEMS.register("citrine_axe",
            () -> new AxeItem(ModTiers.CITRINE,4,0f,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_HOE = ITEMS.register("citrine_hoe",
            () -> new HoeItem(ModTiers.CITRINE,0,0f,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));
    public static final RegistryObject<Item> MYSTIC_BOW = ITEMS.register("mystic_bow",
            () -> new BowItem(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).durability(500)));

    public static final RegistryObject<Item> CITRINE_STAFF = ITEMS.register("citrine_staff",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).stacksTo(1)));

    //Special Tools

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).durability(16)));

    public static final RegistryObject<Item> GEM_CUTTER_TOOLS = ITEMS.register("gem_cutter_tool",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD).durability(32)));

    //Armor

    public static final RegistryObject<Item> CITRINE_HELMET = ITEMS.register("citrine_helmet",
            () -> new ModArmorItem(ModArmorMaterials.CITRINE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_CHESTPLATE = ITEMS.register("citrine_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CITRINE, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_LEGGINGS = ITEMS.register("citrine_leggings",
            () -> new ArmorItem(ModArmorMaterials.CITRINE, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> CITRINE_BOOTS = ITEMS.register("citrine_boots",
            () -> new ArmorItem(ModArmorMaterials.CITRINE, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    //Eggz

    public static final RegistryObject<Item> IFRIT_SPAWN_EGG = ITEMS.register("ifrit_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.IFRIT,0x49000a, 0xfa5209,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static final RegistryObject<Item> DAO_SPAWN_EGG = ITEMS.register("dao_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DAO,0x26262c, 0x1a932a,
                    new Item.Properties().tab(ModCreativeModeTab.GENIES_MOD)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
