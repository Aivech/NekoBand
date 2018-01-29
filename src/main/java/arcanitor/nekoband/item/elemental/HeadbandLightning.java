package arcanitor.nekoband.item.elemental;

import arcanitor.nekoband.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class HeadbandLightning extends Headband implements IElementalArmor {
    public HeadbandLightning() {
        super("headband_lighting",0,ELEMENTAL);
    }

    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = Headband.ARMOR_DEFAULT;

        if (source == DamageSource.LIGHTNING_BOLT || source == DamageSource.ANVIL) {
            prop.AbsorbRatio = 1;
        } else if (source != DamageSource.GENERIC) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }

    @Override
    public void doElementalAttack(EntityLivingBase target) {
        target.damageEntity(PIERCING_LIGHTNING,4.0f);
    }
}
