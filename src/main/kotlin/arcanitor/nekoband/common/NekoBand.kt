package arcanitor.nekoband.common

import arcanitor.nekoband.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@SidedProxy(clientSide = "arcanitor.nekoband.proxy.ClientProxy", serverSide = "arcanitor.nekoband.proxy.ServerProxy")
var proxy: CommonProxy? = null

@Mod(modid = NekoBand.MODID, name = NekoBand.NAME, version = NekoBand.VERSION, useMetadata = true)
object NekoBand {
    const val MODID = "nekoband"
    const val NAME = "NekoBand"
    const val VERSION = "0.0.1"

    lateinit var logger : Logger

    @EventHandler fun preInit(e: FMLPreInitializationEvent) {
        logger = e.modLog
        proxy!!.preInit(e)
    }
}