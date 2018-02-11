package arcanitor.nekoband.common.recipe;

import arcanitor.nekoband.common.NekoBand;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class NekobandRecipes {
    @SubscribeEvent
    public static void initCraftingRecipes(RegistryEvent.Register<IRecipe> e) {
        NekoBand.log.info("Registering custom crafting recipes!");
        e.getRegistry().registerAll(
                new RecipeDye()
        );
    }
}
