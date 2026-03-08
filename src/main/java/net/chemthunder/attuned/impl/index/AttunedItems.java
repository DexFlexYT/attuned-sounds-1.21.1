package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.item.Item;

public interface AttunedItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Attuned.MOD_ID);

    Item TUNING_FORK = ITEMS.register("tuning_fork", TuningForkItem::new, new Item.Settings().maxCount(1).attributeModifiers(TuningForkItem.createAttributeModifiers()));

    static void init() {
        //
    }
}
