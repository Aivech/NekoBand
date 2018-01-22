package arcanitor.nekoband.item;

import arcanitor.nekoband.NekoBand;
import arcanitor.nekoband.client.model.ModelHeadband;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemHairband extends ItemArmor implements ISpecialArmor {
    private boolean isUnbreakable = false;

    public ItemHairband(String name,ArmorMaterial mat) {
        super(mat,0,EntityEquipmentSlot.HEAD);
        setCreativeTab(ModItems.NEKOTAB);
        setRegistryName(new ResourceLocation(NekoBand.MODID,name));
        setUnlocalizedName(name);
        NekoBand.logger.info(this.getRegistryName().toString());
    }

    public ItemHairband(String name) {
        this(name,MATERIAL_HAIRBAND);
    }

    public void setUnbreakable(boolean bool) {
        isUnbreakable = bool;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable())
            return new ArmorProperties(0,0,0);
        return new ArmorProperties(0,damageReduceAmount/25D,armor.getMaxDamage());
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        return this.getArmorMaterial().getDamageReductionAmount(EntityEquipmentSlot.HEAD);
    }

    @Override
    public void damageArmor(EntityLivingBase e, @Nonnull ItemStack stack, DamageSource src, int dmg, int slot) {
        if (isUnbreakable||src.isUnblockable()) return;
        stack.damageItem(Math.max(Math.floorDiv(dmg,4),1),e);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return NekoBand.MODID+":textures/model/"+stack.getItem().getRegistryName().toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase e, ItemStack stack, EntityEquipmentSlot slot, ModelBiped model) {
        return new ModelHeadband();
    }

    public static final ArmorMaterial MATERIAL_HAIRBAND = EnumHelper.addArmorMaterial( //default material
            "HAIRBAND_DEFAULT",
            "hairband_default",
            50,
            new int[]{3, 3, 3, 3},
            9,
            SoundEvents.ENTITY_CAT_PURREOW,
            0.0F
    );
}
