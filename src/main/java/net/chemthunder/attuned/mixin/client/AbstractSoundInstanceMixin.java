package net.chemthunder.attuned.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.attuned.impl.index.AttunedStatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSoundInstance.class)
public abstract class AbstractSoundInstanceMixin {
    @ModifyReturnValue(method = "getVolume", at = @At("RETURN"))
    private float attuned$deafened(float original) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            if (player.hasStatusEffect(AttunedStatusEffects.DEAFENED)) {
                return 0;
            }
        }
        return original;
    }
}
