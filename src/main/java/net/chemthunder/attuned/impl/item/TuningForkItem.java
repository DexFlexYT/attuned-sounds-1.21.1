package net.chemthunder.attuned.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.index.AttunedDataComponents;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class TuningForkItem extends Item implements ModelVaryingItem, CustomHitParticleItem, ColorableItem, CustomHitSoundItem {
    public static final int maxCharges = 30;
    public static final SimpleParticleType[] EFFECTS = new SimpleParticleType[]{
            AcornParticles.LIGHT_GRAY_SWEEP,
            AcornParticles.GRAY_SWEEP
    };

    public static final SimpleParticleType[] ALT_EFFECTS = new SimpleParticleType[]{
            AcornParticles.MAGENTA_SWEEP,
            AcornParticles.BLACK_SWEEP
    };

    public int startColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF2d2534;
        }
        return 0xFF2a2e28;
    }

    public int endColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF7a6873;
        }
        return 0xFFa99797;
    }

    public int backgroundColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF0c070e;
        }
        return 0xF0171315;
    }

    public TuningForkItem(Item.Settings settings) {
        super(settings.component(AttunedDataComponents.CHARGES, 0).component(AttunedDataComponents.SKIN, 0));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            ItemStack stack = player.getStackInHand(player.getActiveHand());

            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(Blocks.SMITHING_TABLE)) {
                var skin = AttunedDataComponents.SKIN;
                int result = 0;

                switch (stack.getOrDefault(skin, 0)) {
                    case 0 -> result = 1;
                    case 1 -> result = 0;
                }

                stack.set(skin, result);
                player.stopUsingItem();

                player.swingHand(player.getActiveHand());
                player.playSoundToPlayer(SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1, 1);
            }
        }

        return super.useOnBlock(context);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        String skinId = "";

        switch (getSkin(stack)) {
            case 0 -> skinId = "tuning_fork";
            case 1 -> skinId = "skin/valediction";
        }

        if (entity != null) {
            if (entity.isUsingItem()) {
                return MiscUtils.isGui(renderMode) ? Attuned.id(skinId) : Attuned.id(skinId + "_blocking");
            }
        }

      //  Armada.LOGGER.info(skinId);
        return MiscUtils.isGui(renderMode) ? Attuned.id(skinId) : Attuned.id(skinId + "_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Attuned.id("tuning_fork"),
                Attuned.id("tuning_fork_handheld"),
                Attuned.id("tuning_fork_blocking"),
                Attuned.id("skin/valediction"),
                Attuned.id("skin/valediction_handheld"),
                Attuned.id("skin/valediction_blocking")
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 7.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 1.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public void spawnHitParticles(PlayerEntity playerEntity, Entity target) {
        ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);

        if (getSkin(stack) == 1) {
            ParticleUtils.spawnSweepParticles(ALT_EFFECTS[playerEntity.getRandom().nextInt(ALT_EFFECTS.length)], playerEntity);
        } else {
            ParticleUtils.spawnSweepParticles(EFFECTS[playerEntity.getRandom().nextInt(EFFECTS.length)], playerEntity);
        }

    }

    public Text getName(ItemStack stack) {
        if (getSkin(stack) == 1) {
            return Text.literal("Valediction").withColor(0x7d153b);
        }
        return super.getName(stack).copy().withColor(0xFFa99797);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var charges = AttunedDataComponents.CHARGES;

        if (stack.getOrDefault(charges, 0) != TuningForkItem.maxCharges) {
            stack.set(charges, stack.getOrDefault(charges, 0) + 1);
        }
        return super.postHit(stack, target, attacker);
    }

    public static int getSkin(ItemStack stack) {
        return stack.getOrDefault(AttunedDataComponents.SKIN, 0);
    }

    public void playHitSound(PlayerEntity player, Entity target) {
        ItemStack stack = player.getMainHandStack();

        if (getSkin(stack) == 1) {
            player.playSound(SoundEvents.BLOCK_MANGROVE_ROOTS_BREAK, 1, 0.2f);
        } else {
            player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1, 4);
        }
    }
}
