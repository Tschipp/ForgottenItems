package tschipp.forgottenitems.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class ModelGolemArmor extends ModelBiped {
	public ModelRenderer RightLeg;
	public ModelRenderer LeftLeg;
	public ModelRenderer RightFoot;
	public ModelRenderer LeftFoot;
	private ModelRenderer RightFootTop;
	private ModelRenderer LeftFootTop;

	public ModelGolemArmor(float scale) {
		super(scale, 0, 128, 128);
		textureWidth = 128;
		textureHeight = 128;

		bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 115, -5.0F, -8.6F, -5.0F, 10, 3, 10, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 70, 115, -5.0F, -2.9F, -5.1F, 4, 3, 10, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 98, 115, 1.0F, -2.9F, -5.1F, 4, 3, 10, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 62, 110, -1.0F, -2.9F, 2.9F, 2, 3, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 73, 81, -4.6F, -5.7F, -4.6F, 1, 5, 9, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 95, 98, 3.6F, -5.7F, -4.6F, 1, 5, 9, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 27, 116, -4.5F, -5.7F, 3.6F, 9, 5, 1, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 36, 80, -5.5F, -9.5F, 3.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 84, 78, -2.5F, -9.5F, 3.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 72, 79, 0.5F, -9.5F, 3.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 27, 94, 3.5F, -9.5F, 3.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 89, 96, 3.5F, -9.5F, 0.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 66, 81, 3.5F, -9.5F, -2.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 95, 94, 3.5F, -9.5F, -5.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 35, 104, 0.5F, -9.5F, -5.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 54, 99, -2.5F, -9.5F, -5.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 121, -5.5F, -9.5F, -5.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 95, 103, -5.5F, -9.5F, -2.5F, 2, 2, 2, 0.0F, false));
		bipedHead.cubeList.add(new ModelBox(bipedHead, 84, 86, -5.5F, -9.5F, 0.5F, 2, 2, 2, 0.0F, false));

		bipedBody.cubeList.add(new ModelBox(bipedBody, 0, 100, -5.0F, -0.3F, -4.0F, 10, 7, 8, 0.0F, false));
		bipedBody.cubeList.add(new ModelBox(bipedBody, 40, 116, -4.5F, 5.9F, -2.9F, 9, 6, 6, 0.0F, false));
		bipedBody.cubeList.add(new ModelBox(bipedBody, 81, 101, -3.0F, 5.9F, -3.5F, 6, 8, 1, 0.0F, false));

		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 47, 84, -5.8F, -5.0F, -3.5F, 6, 5, 7, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 116, 109, -2.8F, -5.5F, 2.0F, 2, 2, 2, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 0, 117, -6.3F, -5.5F, 2.0F, 2, 2, 2, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 96, 99, -6.3F, -5.5F, -1.0F, 2, 2, 2, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 116, 113, -6.3F, -5.5F, -4.0F, 2, 2, 2, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 116, 121, -2.8F, -5.5F, -4.0F, 2, 2, 2, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 108, 89, -3.7F, -3.0F, -2.5F, 5, 4, 5, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 0, 78, -3.7F, 1.0F, -2.5F, 5, 2, 5, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 16, 79, -3.7F, 3.0F, -3.0F, 5, 6, 6, 0.0F, false));
		bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 38, 78, -4.7F, 1.0F, -3.0F, 1, 7, 6, 0.0F, false));

		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 36, 104, -0.2F, -5.0F, -3.5F, 6, 5, 7, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 116, 117, 0.8F, -5.5F, -4.0F, 2, 2, 2, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 84, 82, 4.3F, -5.5F, -4.0F, 2, 2, 2, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 0, 100, 4.3F, -5.5F, -1.0F, 2, 2, 2, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 0, 104, 4.3F, -5.5F, 2.0F, 2, 2, 2, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 36, 76, 0.8F, -5.5F, 2.0F, 2, 2, 2, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 106, 98, -1.3F, -3.0F, -2.5F, 5, 4, 5, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 108, 79, -1.3F, 1.0F, -2.5F, 5, 2, 5, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 0, 88, -1.3F, 3.0F, -3.0F, 5, 6, 6, 0.0F, true));
		bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 64, 109, 3.7F, 1.0F, -3.0F, 1, 7, 6, 0.0F, true));

		RightLeg = bipedRightLeg;
		LeftLeg = bipedLeftLeg;
		RightFoot = bipedRightLeg;
		LeftFoot = bipedLeftLeg;

		RightLeg.cubeList.add(new ModelBox(RightLeg, 93, 81, -2.7F, -0.1F, -2.52F, 5, 8, 5, 0.0F, false));
		RightLeg.cubeList.add(new ModelBox(RightLeg, 46, 78, -2.7F, 7.9F, -2.52F, 5, 1, 5, 0.0F, false));

		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 88, 112, -2.3F, -0.1F, -2.5F, 5, 8, 5, 0.0F, false));
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 74, 95, -2.3F, 7.9F, -2.5F, 5, 1, 5, 0.0F, false));

		RightFoot.cubeList.add(new ModelBox(RightFoot, 28, 91, -3.7F, 6.52F, -3.52F, 6, 6, 7, 0.0F, false));
		RightFootTop = new ModelRenderer(this);
		RightFootTop.cubeList.add(new ModelBox(RightFoot, 68, 85, -3.7F, 2.52F, -3.52F, 6, 4, 1, 0.0F, false));

		LeftFoot.cubeList.add(new ModelBox(LeftFoot, 55, 96, -2.3F, 6.5F, -3.5F, 6, 6, 7, 0.0F, false));
		LeftFootTop = new ModelRenderer(this);
		LeftFootTop.cubeList.add(new ModelBox(LeftFoot, 72, 110, -2.3F, 2.5F, -3.5F, 6, 4, 1, 0.0F, false));

		bipedRightLeg.addChild(RightFootTop);
		bipedLeftLeg.addChild(LeftFootTop);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		if (entityIn instanceof EntityArmorStand) {
			EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
			this.bipedHead.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
			this.bipedHead.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
			this.bipedHead.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
			this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
			this.bipedBody.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
			this.bipedBody.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
			this.bipedBody.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
			this.bipedLeftArm.rotateAngleX = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
			this.bipedLeftArm.rotateAngleY = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
			this.bipedLeftArm.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();
			this.bipedRightArm.rotateAngleX = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
			this.bipedRightArm.rotateAngleY = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
			this.bipedRightArm.rotateAngleZ = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();
			this.bipedLeftLeg.rotateAngleX = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
			this.bipedLeftLeg.rotateAngleY = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
			this.bipedLeftLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
			this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
			this.bipedRightLeg.rotateAngleX = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
			this.bipedRightLeg.rotateAngleY = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
			this.bipedRightLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
			this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
			copyModelAngles(this.bipedHead, this.bipedHeadwear);
		} else
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor,
					entityIn);
	}
}
