package arcanitor.nekoband.item.elemental;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;

public interface IElementalArmor {

    void doElementalAttack(EntityLivingBase target);

    ItemArmor.ArmorMaterial ELEMENTAL = EnumHelper.addArmorMaterial(
            "elemental",
            "elemental",
            1000,
            new int[] {3,0,0,0},
            15,
            SoundEvents.ENTITY_CAT_PURR,
            2
    );

    DamageSource PIERCING_FIRE = new DamageSource("piercing_fire").setDamageBypassesArmor().setDamageIsAbsolute();
    DamageSource PIERCING_COLD = new DamageSource("piercing_cold").setDamageBypassesArmor().setDamageIsAbsolute();
    DamageSource PIERCING_EARTH = new DamageSource("piercing_earth").setDamageBypassesArmor().setDamageIsAbsolute();




}
