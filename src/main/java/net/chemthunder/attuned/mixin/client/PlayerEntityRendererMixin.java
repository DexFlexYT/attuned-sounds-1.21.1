package net.chemthunder.attuned.mixin.client;

import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void tuningForkPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.getItem() instanceof TuningForkItem) {
            if (player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            } else {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
    }
}
