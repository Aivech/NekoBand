package arcanitor.nekoband.proxy

import arcanitor.nekoband.common.item.ModItems
import net.minecraftforge.client.event.ModelRegistryEvent

class ClientProxy : CommonProxy() {
    fun initModels(e : ModelRegistryEvent) {
        ModItems.initItemModels()
    }
}

class ServerProxy : CommonProxy()