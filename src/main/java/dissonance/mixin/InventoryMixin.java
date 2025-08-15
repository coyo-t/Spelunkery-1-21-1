package dissonance.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.orcinus.galosphere.init.GDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"), method = "dropAll")
    private boolean G$isEmpty(ItemStack instance) {
        return instance.has(GDataComponents.PRESERVED.get());
    }

}
