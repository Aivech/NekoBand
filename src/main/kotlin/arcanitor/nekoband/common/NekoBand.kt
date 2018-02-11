package arcanitor.nekoband.common

import arcanitor.nekoband.common.recipe.NekoRecipes
import arcanitor.nekoband.proxy.CommonProxy
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

const val MODID = "nekoband"
const val NAME = "NekoBand"
const val VERSION = "0.0.1"

@SidedProxy(
        clientSide = "arcanitor.nekoband.proxy.ClientProxy",
        serverSide = "arcanitor.nekoband.proxy.ServerProxy"
)
var proxy: CommonProxy? = null

lateinit var logger: Logger

@Mod(
        modid = MODID,
        name = NAME,
        version = VERSION,
        useMetadata = true
)
class NekoBand {
    @EventHandler fun preInit(e: FMLPreInitializationEvent) {
        logger = e.modLog
        MinecraftForge.EVENT_BUS.register(proxy)
        MinecraftForge.EVENT_BUS.register(NekoRecipes)


    }
}