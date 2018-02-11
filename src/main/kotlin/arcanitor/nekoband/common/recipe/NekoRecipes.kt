package arcanitor.nekoband.common.recipe

import arcanitor.nekoband.common.logger
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object NekoRecipes {
    @SubscribeEvent
    fun initCraftingRecipes(e: RegistryEvent.Register<IRecipe>) {
        logger.info("Registering custom crafting recipes!")
        e.registry.registerAll(
                RecipeDye
        )
    }
}