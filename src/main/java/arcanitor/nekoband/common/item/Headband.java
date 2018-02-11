package arcanitor.nekoband.common.item;

import arcanitor.nekoband.api.HeadbandBase;
import arcanitor.nekoband.common.NekoBand;
import arcanitor.nekoband.util.NBTUtils;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static arcanitor.nekoband.util.NBTUtils.safeReadNBT;

public class Headband extends ItemArmor implements ISpecialArmor, IItemColor {
    public static HashMap<String,HeadbandBase> bases = new HashMap<>();
    private static Set<ResourceLocation> ears = new HashSet<>();

    private static final ArmorMaterial ARMOR_MAT =
            EnumHelper.addArmorMaterial("headband_default","",0,
                    new int[]{0,0,0,0},0, SoundEvents.ENTITY_CAT_PURREOW,0);
    private static final ArmorProperties ARMOR_PROP = new ArmorProperties(0,0,Integer.MAX_VALUE);

    public Headband(String name, int durability/*, ArmorMaterial armorMat*/) {
        super(ARMOR_MAT,0,EntityEquipmentSlot.HEAD);
        setRegistryName(new ResourceLocation(NekoBand.MODID,name));
        setUnlocalizedName(NekoBand.MODID+":"+name);
        setMaxStackSize(1);
        setMaxDamage(durability);
        //this.armorMat = armorMat;

        //MUST be done last
        ModItems.NEKOTAB.addToTab(new ItemStack(this));
    }

    public static void addValidBase(HeadbandBase base) {
        NekoBand.logger.info("Added "+base.getItemStack().getUnlocalizedName()+" as valid base.");
        bases.put(base.getItemStack().getUnlocalizedName(),base);
    }

    public static void addEarTexture(ResourceLocation tex) { ears.add(tex); }

    public static ImmutableSet<ResourceLocation> getEarTextures() {
        return ImmutableSet.copyOf(ears);
    }

    //Item overrides
    @Override
    public boolean isEnchantable(ItemStack stack) {
        NBTTagCompound nbt = safeReadNBT(stack);
        if(validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            return base.getItem().isEnchantable(base);
        }
        return false;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        NBTTagCompound nbt = safeReadNBT(stack);
        if(validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            return base.getItem().getItemEnchantability(base);
        }
        return 0;
    }

    @Override
    public boolean getIsRepairable(ItemStack damaged, ItemStack repair) {
        NBTTagCompound nbt = safeReadNBT(damaged);
        if(validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            base.setItemDamage(damaged.getItemDamage());
            EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(damaged),base);
            return base.getItem().getIsRepairable(base, repair);
        }
        return false;
    }

    @Override
    public int getItemStackLimit(ItemStack item) { return 1; }



    //ISpecialArmor overrides
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        NBTTagCompound nbt = safeReadNBT(armor);
        if(validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            if (base.getItem() instanceof ISpecialArmor) {
                base.setItemDamage(armor.getItemDamage());
                EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(armor),base);
                return ((ISpecialArmor) base.getItem()).getProperties(player,base,source,damage,slot);
            }
        }
        return ARMOR_PROP;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        NBTTagCompound nbt = safeReadNBT(stack);
        if (validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            return base.getItem().getMaxDamage(base);
        }
        return 0;
    }


    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        NBTTagCompound nbt = safeReadNBT(armor);
        if (validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            if (base.getItem() instanceof ISpecialArmor) {
                base.setItemDamage(armor.getItemDamage());
                EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(armor),base);

                return ((ISpecialArmor) base.getItem()).getArmorDisplay(player,base,slot);
            }
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
        NBTTagCompound nbt = safeReadNBT(stack);
        if (validateNBT(nbt)) {
            ItemStack base = bases.get(nbt.getString("base")).getItemStack().copy();
            if (stack.getMaxDamage() == 0 || !base.isItemStackDamageable()) return;

            stack.damageItem(Math.max(Math.floorDiv(damage,4),1),entity);
        }
    }

    @Override
    public int colorMultiplier(@Nonnull ItemStack stack, int tintIndex) {
        NBTTagCompound tag = NBTUtils.safeReadNBT(stack);
        if(tag.getBoolean("dyeable"))
        switch (tintIndex) {
            case 1: return tag.getInteger("color_primary");
            case 2: return tag.getInteger("color_secondary");
            case 3: return tag.getInteger("color_headband");
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void initItemModel() {
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation("nekoband:models/item/band","inventory"));
    }

    public static boolean validateNBT(@Nonnull NBTTagCompound nbt) {
        if (nbt.hasKey("base", Constants.NBT.TAG_STRING) && bases.containsKey(nbt.getString("base"))) {
            return true;
        }
        NekoBand.logger.error("Tried to look up NBT for an invalid Headband itemstack!");
        return false;
    }
}