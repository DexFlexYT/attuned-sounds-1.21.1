package net.chemthunder.attuned.data.provider;

import net.chemthunder.attuned.impl.index.AttunedBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class AttunedBlockTagGen extends FabricTagProvider.BlockTagProvider {
    public AttunedBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(AttunedBlocks.ANCILLARY)
                .setReplace(false);

        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(AttunedBlocks.ANCILLARY)
                .setReplace(false);
    }
}
