package me.keet.compactgolems.items;

import me.keet.compactgolems.Compactgolems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.entity.Spawner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModItems {
    public static Item register(Item item, RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static void initialize() {
        modifyGroups();
    }

    public static final RegistryKey<Item> COMPACT_IRON_GOLEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Compactgolems.MOD_ID, "compact_iron_golem"));
    public static final Item COMPACT_IRON_GOLEM = register(
            new CompactItem(EntityType.IRON_GOLEM, new Item.Settings().registryKey(COMPACT_IRON_GOLEM_KEY)),
            COMPACT_IRON_GOLEM_KEY
    );

    public static final RegistryKey<Item> COMPACT_SNOWMAN_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Compactgolems.MOD_ID, "compact_snowman"));
    public static final Item COMPACT_SNOWMAN = register(
            new CompactItem(EntityType.SNOW_GOLEM, new Item.Settings().registryKey(COMPACT_SNOWMAN_KEY)),
            COMPACT_SNOWMAN_KEY
    );

    public static void modifyGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.COMPACT_IRON_GOLEM);
                    itemGroup.add(ModItems.COMPACT_SNOWMAN);
                });
    }
}

final class CompactItem extends SpawnEggItem {
    public CompactItem(EntityType<? extends MobEntity> type, Item.Settings settings) {
        super(type, settings);
        SPAWN_EGGS.remove(type, this);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        if (world.getBlockEntity(blockPos) instanceof Spawner) {
            return ActionResult.FAIL;
        }

        return super.useOnBlock(context);
    }
}
