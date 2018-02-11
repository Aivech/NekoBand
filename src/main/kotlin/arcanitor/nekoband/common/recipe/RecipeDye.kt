package arcanitor.nekoband.common.recipe

import arcanitor.nekoband.common.MODID
import arcanitor.nekoband.common.item.Headband
import arcanitor.nekoband.common.item.ear.IEar
import arcanitor.nekoband.common.logger
import arcanitor.nekoband.util.NBTUtils
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.oredict.DyeUtils
import net.minecraftforge.registries.IForgeRegistryEntry
import java.util.ArrayList

object RecipeDye : IForgeRegistryEntry.Impl<IRecipe>(), IRecipe {
    init {
        registryName = ResourceLocation(MODID, "recipe_dye")
        logger.info("Created dyeing recipe!")
    }

    override fun matches(inv: InventoryCrafting, worldIn: World): Boolean {
        logger.debug("Checking if is valid dye recipe")
        var dyeable = ItemStack.EMPTY
        val dyes = ArrayList<ItemStack>()
        for (i in 0 until inv.sizeInventory) {
            val test = inv.getStackInSlot(i)

            //if this slot has an item in it
            if (!test.isEmpty) {

                //if we haven't already found a dyeable item
                if (dyeable.isEmpty) {
                    //and this is a potential dyeable item
                    if (test.item is Headband) { //a headband

                        if (test.hasTagCompound()) { //that has NBT
                            val nbt = test.tagCompound //get the NBT
                            if (nbt!!.hasKey("dyeable")) { //and see if we can dye it
                                if (nbt.getBoolean("dyeable")) {
                                    dyeable = test
                                } else {
                                    return false
                                }
                            } else {
                                return false
                            }
                        } else {
                            return false
                        }
                    } else if (test.item is IEar) { //an ear piece
                        if ((test.item as IEar).isDyeable) { //can we dye it?
                            dyeable = test
                        } else {
                            return false
                        }
                    } else if (!DyeUtils.isDye(test)) {
                        return false
                    } else {
                        dyes.add(test)
                    }

                } else {
                    if (!DyeUtils.isDye(test)) {
                        return false
                    }
                    dyes.add(test)
                }
            }
        }

        return !dyeable.isEmpty && !dyes.isEmpty()
    }

    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        var dyeable = ItemStack.EMPTY
        val rgb = IntArray(3)
        var numDyes = 0
        var magic = 0 //not sure what this does

        //for each slot
        for (i in 0 until inv.sizeInventory) {
            //get the itemstack in that slot
            val slot = inv.getStackInSlot(i)
            if (!slot.isEmpty) {
                val item = slot.item

                if (DyeUtils.isDye(slot)) { //the slot has a dye
                    val color = DyeUtils.colorFromStack(slot).get().colorValue
                    val r = color shr 16 and 0xFF
                    val g = color shr 8 and 0xFF
                    val b = color and 0xFF
                    magic += Math.max(r, Math.max(g, b))
                    rgb[0] += r
                    rgb[1] += g
                    rgb[2] += b
                    numDyes++
                } else if (item is IEar) { //the slot has a headband component
                    if ((item as IEar).isDyeable) { //that can be dyed
                        if (slot.hasTagCompound() && slot.tagCompound!!.hasKey("color")) {
                            val color = slot.tagCompound!!.getInteger("color")
                            val r = color shr 16 and 0xFF
                            val g = color shr 8 and 0xFF
                            val b = color and 0xFF
                            magic += Math.max(r, Math.max(g, b))
                            rgb[0] += r
                            rgb[1] += g
                            rgb[2] += b
                            numDyes++
                        }
                        dyeable = slot.copy()
                        dyeable.count = 1
                    } else {
                        return ItemStack.EMPTY
                    } //that cannot be dyed
                } else if (item is Headband) { //the slot has a headband
                    if (slot.hasTagCompound() && slot.tagCompound!!.hasKey("dyeable")) { //with valid NBT
                        val nbt = slot.tagCompound
                        if (nbt!!.getBoolean("dyeable")) { //that can be dyed
                            if (nbt.hasKey("color_headband")) { //add its color to the pool
                                val color = slot.tagCompound!!.getInteger("color_headband")
                                rgb[0] += color shr 16 and 0xFF
                                rgb[1] += color shr 8 and 0xFF
                                rgb[2] += color and 0xFF
                                numDyes++
                            }
                            dyeable = slot.copy()
                            dyeable.count = 1
                        } else {
                            return ItemStack.EMPTY
                        }
                    } else {
                        return ItemStack.EMPTY
                    }
                } else {
                    return ItemStack.EMPTY
                }
            }
        }

        if (dyeable.isEmpty) {
            return ItemStack.EMPTY
        }
        //create the result
        var red = rgb[0] / numDyes
        var green = rgb[1] / numDyes
        var blue = rgb[2] / numDyes
        val adj1 = magic.toFloat() / numDyes.toFloat() //magic adjustment value 1
        val adj2 = Math.max(red, Math.max(green, blue)).toFloat() //magic adjustment value 2

        red = (red.toFloat() * adj1 / adj2).toInt()
        green = (green.toFloat() * adj1 / adj2).toInt()
        blue = (blue.toFloat() * adj1 / adj2).toInt()

        var newColor = (red shl 8) + green
        newColor = (newColor shl 8) + blue

        val nbt = NBTUtils.safeReadNBT(dyeable)

        val dyeableItem = dyeable.item
        if (dyeableItem is IEar) {
            nbt.setInteger("color", newColor)
        } else if (dyeableItem is Headband) {
            nbt.setInteger("color_headband", newColor)
        }

        return dyeable
    }

    override fun canFit(width: Int, height: Int): Boolean {
        return width * height > 1
    }

    override fun getRecipeOutput(): ItemStack {
        return ItemStack.EMPTY
    }

    override fun isDynamic(): Boolean {
        return true
    }
}