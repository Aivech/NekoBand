package arcanitor.nekoband.proxy;

import arcanitor.nekoband.common.NekoBand;
import arcanitor.nekoband.common.item.elemental.IElementalArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        NekoBand.logger.info("NekoPreInitialize!");
    }

    public void init(FMLInitializationEvent e) {

    }

    @SubscribeEvent
    public static void modifyAttacks(AttackEntityEvent e) {
        Entity t = e.getTarget();
        if (t instanceof EntityLivingBase) {
            Item head = e.getEntityPlayer().getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
            //todo: other armor pieces maybe

            if (head instanceof IElementalArmor) {
                NekoBand.logger.info("Valid armor found.");

                EntityLivingBase target = (EntityLivingBase)t;
                target.hurtResistantTime = 0;

                ((IElementalArmor) head).doElementalAttack(target, e.getEntityPlayer());
            }

        }

    }

}
