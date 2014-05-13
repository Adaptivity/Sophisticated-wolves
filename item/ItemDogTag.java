package sophisticated_wolves.item;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sophisticated_wolves.Resources;
import sophisticated_wolves.SWConfiguration;
import sophisticated_wolves.SophisticatedWolvesMod;
import sophisticated_wolves.entity.SophisticatedWolf;
import sophisticated_wolves.gui.GuiEditName;

public class ItemDogTag extends Item {

    public ItemDogTag() {
        super();
        setUnlocalizedName("dogtag");
        setTextureName(Resources.DOG_TAG);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
        if (SWConfiguration.nameTagForAnyPets) {
            if (entity instanceof EntityTameable) {
                return setName((EntityTameable) entity, stack);
            }
        } else if (entity instanceof SophisticatedWolf) {
            return setName((EntityTameable) entity, stack);
        }
        return super.itemInteractionForEntity(stack, player, entity);
    }

    private static boolean setName(EntityTameable pet, ItemStack stack) {
        FMLClientHandler.instance().getClient().displayGuiScreen(new GuiEditName(pet));
        --stack.stackSize;
        return true;
    }
}