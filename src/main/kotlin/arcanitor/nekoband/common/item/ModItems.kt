package arcanitor.nekoband.common.item

import arcanitor.nekoband.api.HeadbandBase
import arcanitor.nekoband.common.MODID
import arcanitor.nekoband.common.item.ear.ItemEarInner
import arcanitor.nekoband.common.item.ear.ItemEarOuter
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly


object ModItems {
    val NEKOTAB : TabNekoband = TabNekoband("NEKOTAB")

    lateinit var earDyeableInner : ItemEarInner private set
    lateinit var earDyeableOuter : ItemEarOuter private set

    lateinit var itemHeadband: Headband private set
    lateinit var itemHeadbandBiped : ItemHeadbandBiped

    @SubscribeEvent
    fun initItems(e: RegistryEvent.Register<Item>) {
        earDyeableInner = ItemEarInner(
                "ear_secondary_dyeable",
                ResourceLocation(MODID, "item/ears/cat_ears_dyeable"),
                true
        )
        earDyeableOuter = ItemEarOuter(
                "ear_primary_dyeable",
                ResourceLocation(MODID, "item/ears/cat_ears_dyeable"),
        true
        )

        itemHeadband = Headband("modular_headband",0)
        itemHeadbandBiped = ItemHeadbandBiped("headbandBiped")

        e.registry.registerAll(earDyeableInner, earDyeableOuter, itemHeadband, itemHeadbandBiped)

        registerBases()



    }

    @SideOnly(Side.CLIENT)
    fun initItemModels() {
        itemHeadbandBiped.initItemModel()
    }

    private fun registerBases() {
        HeadbandBase.addBase(
                ItemStack(Items.LEATHER_HELMET),
                ResourceLocation(MODID, "item/band/leather")
        )
        HeadbandBase.addBase(
                ItemStack(Items.IRON_HELMET),
                ResourceLocation(MODID, "item/band/iron")
        )
        HeadbandBase.addBase(
                ItemStack(Items.GOLDEN_HELMET),
                ResourceLocation(MODID, "item/band/gold")
        )
        HeadbandBase.addBase(
                ItemStack(Items.DIAMOND_HELMET),
                ResourceLocation(MODID, "item/band/diamond")
        )
    }

}