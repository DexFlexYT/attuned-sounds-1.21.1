package net.chemthunder.attuned.data;

import net.chemthunder.attuned.data.provider.AttunedLangGen;
import net.chemthunder.attuned.data.provider.AttunedModelGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AttunedDataGen implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AttunedModelGen::new);
        pack.addProvider(AttunedLangGen::new);
	}
}
