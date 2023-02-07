package tschipp.forgottenitems.items;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.models.ModelGolemArmor;
import tschipp.forgottenitems.util.FIHelper;
import net.minecraft.client.resources.I18n;

public class ItemGolemArmor extends ItemArmor {

	private int id;
    private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};


	public ItemGolemArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, int id, Item core) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		registerItem(name);
		this.setCreativeTab(FIM.forgottenItems);
		this.id = id;
		FIHelper.setOutputCore(id, this, core);
	}

	private void registerItem(String name)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(FIM.MODID + ":" + name));
		ForgeRegistries.ITEMS.register(this);
	} 


	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return "" + TextFormatting.DARK_AQUA + I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name");
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".desc"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nullable
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped defaultModel)
	{
		if(!stack.isEmpty())
		{
			if(stack.getItem() instanceof ItemArmor)
			{
				ModelGolemArmor armorModel = (ModelGolemArmor) FIM.proxy.getGolemArmor().get(this);

				if(armorModel != null)
				{

					armorModel.bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;
					armorModel.bipedHeadwear.showModel = slot == EntityEquipmentSlot.HEAD;
					armorModel.bipedBody.showModel = slot == EntityEquipmentSlot.CHEST;
					armorModel.bipedRightArm.showModel = slot == EntityEquipmentSlot.CHEST;
					armorModel.bipedLeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
					armorModel.LeftLeg.showModel = slot == EntityEquipmentSlot.LEGS;
					armorModel.RightLeg.showModel = slot == EntityEquipmentSlot.LEGS;
					armorModel.LeftFoot.showModel = slot == EntityEquipmentSlot.FEET;
					armorModel.RightFoot.showModel = slot == EntityEquipmentSlot.FEET;

					armorModel.isSneak = defaultModel.isSneak;
					armorModel.isRiding = defaultModel.isRiding;
					armorModel.isChild = defaultModel.isChild;

					if(living instanceof EntityArmorStand)
						armorModel.swingProgress = 0;

					armorModel.rightArmPose = defaultModel.rightArmPose;
					armorModel.leftArmPose = defaultModel.leftArmPose;
					
					return armorModel;
				}
			}
		}
		return null;
	}

	
	@Override
	 public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	    {
	        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

	        if (equipmentSlot == this.armorType)
	        {
	            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double)this.damageReduceAmount, 0));
	            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double)this.toughness, 0));
	            multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "generic.knockbackResistance", 0.25, 0));
	        }

	        return multimap;
	    }


	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking() && !world.isRemote && player.isCreative())
		{
			FIHelper.printCraftingRecipe(world, player, this.id);
			return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else
		{
			return super.onItemRightClick(world, player, hand);

		}
	}





}
