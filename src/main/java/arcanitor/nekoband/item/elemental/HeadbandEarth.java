package arcanitor.nekoband.item.elemental;

import arcanitor.nekoband.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class HeadbandEarth extends Headband implements IElementalArmor {

    public HeadbandEarth() {
        super("headband_earth",0,ELEMENTAL);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = Headband.ARMOR_DEFAULT;

        if (source == DamageSource.IN_WALL || source == DamageSource.LIGHTNING_BOLT) {
            prop.AbsorbRatio = 1;
        } else if (!source.isExplosion()) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }

    @Override
    public void doElementalAttack(EntityLivingBase target) {
        target.damageEntity(PIERCING_EARTH,4.0f);
    }
}
