package arcanitor.nekoband.item.elemental;

import arcanitor.nekoband.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class HeadbandFire extends Headband implements IElementalArmor {
    public HeadbandFire() {
        super("headband_fire",0,ELEMENTAL);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = Headband.ARMOR_DEFAULT;
        if (source.isFireDamage()) {
            prop.AbsorbRatio = 1;
        } else if (!(source == DamageSource.DROWN || source == PIERCING_COLD)) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }

    @Override
    public void doElementalAttack(EntityLivingBase target) {
        target.damageEntity(PIERCING_FIRE,4.0f);
    }
}
