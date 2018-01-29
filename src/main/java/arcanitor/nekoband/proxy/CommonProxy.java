package arcanitor.nekoband.proxy;

import arcanitor.nekoband.NekoBand;
import arcanitor.nekoband.item.ItemHeadband;
import arcanitor.nekoband.item.elemental.HeadbandIce;
import arcanitor.nekoband.item.elemental.IElementalArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        NekoBand.logger.info("NekoPreInitialize!");
    }

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemHeadband("headband"));
        e.getRegistry().register(new HeadbandIce());
    }

    @SubscribeEvent
    public static void modifyAttacks(AttackEntityEvent e) {
        Entity target = e.getTarget();
        if (target instanceof EntityLivingBase) {
            Item head = e.getEntityPlayer().getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
            //todo: other armor pieces maybe

            if (head instanceof IElementalArmor) {
                NekoBand.logger.info("Valid armor found.");
                ((IElementalArmor) head).doElementalAttack((EntityLivingBase)e.getTarget());
            }

        }

    }

}
