package arcanitor.nekoband.common.item.elemental;

import arcanitor.nekoband.common.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class HeadbandLightning extends Headband implements IElementalArmor {
    public HeadbandLightning() {
        super("headband_lightningw",0);
        //super("headband_lightning",0,ELEMENTAL);
    }

    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = armorProp;

        if (source == DamageSource.LIGHTNING_BOLT || source == DamageSource.ANVIL) {
            prop.AbsorbRatio = 1;
        } else if (source != DamageSource.GENERIC) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }

    @Override
    public void doElementalAttack(EntityLivingBase target, EntityPlayer player) {
        target.attackEntityFrom(PIERCING_LIGHTNING,4.0f*player.getCooledAttackStrength(0.5f));
    }


}
