package arcanitor.nekoband.common.recipe;

import arcanitor.nekoband.common.NekoBand;
import arcanitor.nekoband.common.item.Headband;
import arcanitor.nekoband.common.item.ear.IEar;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.DyeUtils;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class RecipeDye extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe{
    public RecipeDye() {
        super();
        setRegistryName(new ResourceLocation(NekoBand.MODID,"recipe_dye"));
        NekoBand.logger.info("Created dyeing recipe!");
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        NekoBand.logger.info("Checking if is valid dye recipe");
        ItemStack dyeable = ItemStack.EMPTY;
        List<ItemStack> dyes = new ArrayList<>();
        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack test = inv.getStackInSlot(i);

            //if this slot has an item in it
            if(!test.isEmpty()) {

                //if we haven't already found a dyeable item
                if(dyeable.isEmpty()) {
                    //and this is a potential dyeable item
                    if(test.getItem() instanceof Headband) { //a headband

                        if (test.hasTagCompound()) { //that has NBT
                            NBTTagCompound nbt = test.getTagCompound(); //get the NBT
                            if(nbt.hasKey("dyeable")) { //and see if we can dye it
                                if (nbt.getBoolean("dyeable" )) {
                                    dyeable = test;
                                } else { return false; }
                            } else { return false; }
                        } else { return false; }
                    } else if (test.getItem() instanceof IEar) { //an ear piece
                        if (((IEar) test.getItem()).isDyeable()) { //can we dye it?
                            dyeable = test;
                        } else { return false; }
                    }
                }

                //has an item that isn't dye or a dyeable item
                if (!DyeUtils.isDye(test)) {
                    return false;
                }
                //add this dye to the list
                dyes.add(test);
            }
        }

        return !dyeable.isEmpty() && !dyes.isEmpty();
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack dyeable = ItemStack.EMPTY;
        int[] rgb = new int[3];
        int numDyes = 0;
        Item item = null;

        int magic = 0; //not sure what this does

        //for each slot
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            //get the itemstack in that slot
            ItemStack slot = inv.getStackInSlot(i);
            if (!slot.isEmpty()) {
                item = slot.getItem();

                if (DyeUtils.isDye(slot)) { //the slot has a dye
                    int color = DyeUtils.colorFromStack(slot).get().getColorValue();
                    int r = color >> 16 & 0xFF;
                    int g = color >> 8 & 0xFF;
                    int b = color & 0xFF;
                    magic += Math.max(r,Math.max(g,b));
                    rgb[0] += r;
                    rgb[1] += g;
                    rgb[2] += b;
                    numDyes++;
                } else if (item instanceof IEar) { //the slot has a headband component
                    if (((IEar) item).isDyeable()) { //that can be dyed
                        if(slot.hasTagCompound() && slot.getTagCompound().hasKey("color")) {
                            int color = slot.getTagCompound().getInteger("color");
                            int r = color >> 16 & 0xFF;
                            int g = color >> 8 & 0xFF;
                            int b = color & 0xFF;
                            magic += Math.max(r,Math.max(g,b));
                            rgb[0] += r;
                            rgb[1] += g;
                            rgb[2] += b;
                            numDyes++;
                        }
                        dyeable = slot.copy();
                        dyeable.setCount(1);
                    } else { return ItemStack.EMPTY; } //that cannot be dyed
                } else if(item instanceof Headband) { //the slot has a headband
                    if (slot.hasTagCompound() && slot.getTagCompound().hasKey("dyeable")) { //with valid NBT
                        NBTTagCompound nbt = slot.getTagCompound();
                        if(nbt.getBoolean("dyeable")) { //that can be dyed
                            if(nbt.hasKey("color_headband")) { //add its color to the pool
                                int color = slot.getTagCompound().getInteger("color_headband");
                                rgb[0] += (color >> 16 & 0xFF);
                                rgb[1] += (color >> 8 & 0xFF);
                                rgb[2] += (color & 0xFF);
                                numDyes++;
                            }
                            dyeable = slot.copy();
                            dyeable.setCount(1);
                        } else { return ItemStack.EMPTY; }
                    } else { return ItemStack.EMPTY; }
                } else { return ItemStack.EMPTY; }
            }
        }

        if (dyeable.isEmpty()) { return ItemStack.EMPTY; }
        //create the result
        int red = rgb[0]/numDyes;
        int green = rgb[1]/numDyes;
        int blue = rgb[2]/numDyes;
        float adj1 = (float)magic / (float)numDyes; //magic adjustment value 1
        float adj2 = (float)Math.max(red, Math.max(green,blue)); //magic adjustment value 2

        red = (int)((float)red*adj1/adj2);
        green = (int)((float)green*adj1/adj2);
        blue = (int)((float)blue*adj1/adj2);

        int newColor = (red << 8) + green;
        newColor = (newColor << 8) + blue;

        NBTTagCompound nbt = dyeable.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            dyeable.setTagCompound(nbt);
        }
        if (item instanceof IEar) {
            nbt.setInteger("color",newColor);
        } else if (item instanceof Headband) {
            nbt.setInteger("color_headband",newColor);
        }

        return dyeable;
    }

    @Override
    public boolean canFit(int width, int height) { return (width*height)>1; }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() { return ItemStack.EMPTY; }

    @Override
    public boolean isDynamic() { return true; }
}
