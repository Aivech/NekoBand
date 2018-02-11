package arcanitor.nekoband.proxy

import arcanitor.nekoband.common.item.elemental.IElementalArmor
import arcanitor.nekoband.common.logger
import net.minecraft.entity.EntityLivingBase
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
class CommonProxy {


    companion object {
        @SubscribeEvent
        @JvmStatic fun modifyAttacks(e : AttackEntityEvent) {
            val t = e.target
            if (t is EntityLivingBase) {
                val head = e.entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).item
                //todo: other armor pieces maybe

                if (head is IElementalArmor) {
                    logger.info("Valid armor found.")

                    t.hurtResistantTime = 0

                    head.doElementalAttack(t, e.entityPlayer)
                }

            }
        }
    }
}