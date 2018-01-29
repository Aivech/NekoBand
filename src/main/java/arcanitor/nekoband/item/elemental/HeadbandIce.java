package arcanitor.nekoband.item.elemental;

import arcanitor.nekoband.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class HeadbandIce extends Headband implements IElementalArmor {

    public HeadbandIce() {
        super("headband_ice",0,ELEMENTAL);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = Headband.ARMOR_DEFAULT;
        if (source == DamageSource.DROWN) {
            prop.AbsorbRatio = 1;
        } else if (!source.isFireDamage()||(source == PIERCING_FIRE)) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }

    @Override
    public void doElementalAttack(EntityLivingBase target) {
        target.damageEntity(PIERCING_COLD,4.0f);
    }
}
