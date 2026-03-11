package net.chemthunder.attuned.data.provider;

import net.chemthunder.attuned.impl.index.AttunedBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AttunedBlockLootTableGen extends FabricBlockLootTableProvider {
    public AttunedBlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        addDrop(AttunedBlocks.ANCILLARY);
    }
}
