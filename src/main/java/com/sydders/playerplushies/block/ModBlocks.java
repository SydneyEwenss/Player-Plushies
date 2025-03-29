package com.sydders.playerplushies.block;

import com.sydders.playerplushies.PlayerPlushies;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block PLAYER_PLUSHIE = registerBlock("player_plushie",
        new PlayerPlushieBlock(AbstractBlock.Settings.create()
                .nonOpaque()
                .strength(1f)
                .sounds(BlockSoundGroup.WOOL)
        ));

    public static final Block TEST_BLOCK = registerBlock("test_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .sounds(BlockSoundGroup.ANVIL)
            ));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PlayerPlushies.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PlayerPlushies.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PlayerPlushies.LOGGER.info("Registering blocks for " + PlayerPlushies.MOD_ID);

        BlockRenderLayerMap.INSTANCE.putBlock(PLAYER_PLUSHIE, RenderLayer.getCutout());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.PLAYER_PLUSHIE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.TEST_BLOCK);
        });
    }
}
