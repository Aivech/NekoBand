package arcanitor.nekoband.common.item.elemental;

import arcanitor.nekoband.common.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class HeadbandEarth extends Headband implements IElementalArmor {

    public HeadbandEarth() {
        super("headband_earth",0);
        //super("headband_earth",0,ELEMENTAL);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = ARMOR_PROP;

        if (source == DamageSource.IN_WALL || source == DamageSource.LIGHTNING_BOLT) {
            prop.AbsorbRatio = 1;
        } else if (!source.isExplosion()) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }

    @Override
    public void doElementalAttack(EntityLivingBase target, EntityPlayer player) {
        target.attackEntityFrom(PIERCING_EARTH,4.0f*player.getCooledAttackStrength(0.5f));
    }
}
