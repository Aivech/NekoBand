package arcanitor.nekoband.item.special;

import arcanitor.nekoband.item.Headband;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;

public class HeadbandIce extends Headband {
    public static ItemArmor.ArmorMaterial mat = EnumHelper.addArmorMaterial(
            "ICEBAND",
            "headband_ice",
            1000,
            new int[]{3,0,0,0},
            12,
            SoundEvents.ENTITY_CAT_PURR,
            2
    );
    public HeadbandIce() {
        super("ice_neko",0,mat);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        ArmorProperties prop = Headband.ARMOR_DEFAULT;
        if (source == DamageSource.DROWN) {
            prop.AbsorbRatio = 1;
        } else if (!source.isFireDamage()) {
            prop.AbsorbRatio = .1;
        }
        return prop;
    }
}
