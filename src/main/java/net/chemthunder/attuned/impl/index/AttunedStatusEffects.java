package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.StatusEffectRegistrant;
import net.acoyt.acornlib.api.template.StatusEffectBase;
import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;

public interface AttunedStatusEffects {
    StatusEffectRegistrant STATUS_EFFECTS = new StatusEffectRegistrant(Attuned.MOD_ID);

    RegistryEntry<StatusEffect> DEAFENED = STATUS_EFFECTS.registerRef("deafened", new StatusEffectBase(StatusEffectCategory.HARMFUL, 0x000000));

    static void init() {
        //
    }
}
