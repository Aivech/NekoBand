package arcanitor.nekoband.common.item;

import arcanitor.nekoband.api.HeadbandBase;
import arcanitor.nekoband.common.NekoBand;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Headband extends Item implements ISpecialArmor, IItemColor {
    public static HashMap<String,HeadbandBase> bases = new HashMap<>();
    private static Set<ResourceLocation> ears = new HashSet<>();

    private ArmorMaterial armorMat;
    protected ArmorProperties armorProp = new ArmorProperties(0,0,Integer.MAX_VALUE);

    public Headband(String name, int durability, ArmorMaterial armorMat) {
        setRegistryName(new ResourceLocation(NekoBand.MODID,name));
        setUnlocalizedName(NekoBand.MODID+":"+name);
        setMaxStackSize(1);
        setMaxDamage(durability);
        this.armorMat = armorMat;

        //MUST be done last
        ModItems.NEKOTAB.addToTab(new ItemStack(this));
    }

    public static void addValidBase(HeadbandBase base) {
        NekoBand.logger.info("Added "+base.getItem().getUnlocalizedName()+" as valid base.");
        bases.put(base.getItem().getUnlocalizedName(),base);
    }

    public static void addEarTexture(ResourceLocation tex) {
        ears.add(tex);
    }

    public static ImmutableSet<ResourceLocation> getEarTextures() {
        return ImmutableSet.copyOf(ears);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) { return true; }

    @Override
    public int getItemEnchantability(ItemStack stack) { return this.armorMat.getEnchantability(); }

    @Override
    public boolean getIsRepairable(ItemStack damaged, ItemStack repair) {
        return damaged.isItemDamaged() && repair.isItemEqual(armorMat.repairMaterial);
    }

    //Override isValidArmor?
    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) { return EntityEquipmentSlot.HEAD; }

    @Override
    public int getItemStackLimit(ItemStack item) { return 1; }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        return armorProp;
    }

    public void setProperties(ArmorProperties prop) { this.armorProp = prop; }

    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        return this.armorMat.getDamageReductionAmount(EntityEquipmentSlot.HEAD);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
        if (stack.getMaxDamage() == 0) return;
        stack.damageItem(Math.max(Math.floorDiv(damage,4),1),entity);
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        NBTTagCompound tag = safeReadNBT(stack);

        switch (tintIndex) {
            case 1: return tag.getInteger("nekoband_color_primary");
            case 2: return tag.getInteger("nekoband_color_secondary");
            case 3: return tag.getInteger("nekoband_color_headband");
        }
        return 0;
    }

    private static NBTTagCompound safeReadNBT(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }
        return tag;
    }

    @SideOnly(Side.CLIENT)
    public void initItemModel() {
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation("nekoband:models/item/headband","inventory"));
    }
}