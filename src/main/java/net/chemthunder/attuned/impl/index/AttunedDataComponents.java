package net.chemthunder.attuned.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.component.ComponentType;

public interface AttunedDataComponents {
    ComponentTypeRegistrant DATA_COMPONENTS = new ComponentTypeRegistrant(Attuned.MOD_ID);

    ComponentType<Integer> CHARGES = DATA_COMPONENTS.register("charges", builder -> builder.codec(Codec.INT));
    ComponentType<Integer> SKIN = DATA_COMPONENTS.register("skin", builder -> builder.codec(Codec.INT));

    ComponentType<Integer> HELD_TICKS = DATA_COMPONENTS.register("held_ticks", builder -> builder.codec(Codec.INT));

    static void init() {
        //
    }
}
