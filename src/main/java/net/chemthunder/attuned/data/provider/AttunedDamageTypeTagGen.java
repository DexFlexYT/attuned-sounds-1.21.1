package net.chemthunder.attuned.data.provider;

import net.chemthunder.attuned.impl.index.data.AttunedDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class AttunedDamageTypeTagGen extends FabricTagProvider<DamageType> {
    public AttunedDamageTypeTagGen(FabricDataOutput output, CompletableFuture registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_INVULNERABILITY).add(AttunedDamageTypes.MAGNUM_OPUS).setReplace(false);
    }
}
