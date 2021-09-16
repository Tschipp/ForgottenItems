
package tschipp.forgottenitems.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLifebelt extends ModelBiped
{

    private ModelRenderer Shape1;
    private ModelRenderer Shape11;
    private ModelRenderer Shape2;
    private ModelRenderer Shape21;
  
  public ModelLifebelt(float scale)
  {
	  super(scale, 0, 64, 64);
    textureWidth = 64;
    textureHeight = 64;
    
     
      Shape1 = new ModelRenderer(this, 0, 38);
      Shape1.addBox(-6F, 9F, 2F, 12, 2, 2);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 45);
      Shape11.addBox(-6F, 9F, -4F, 12, 2, 2);
      Shape11.setRotationPoint(0F, 0F, 0F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 28, 38);
      Shape2.addBox(-6F, 9F, -2F, 2, 2, 4);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 28, 45);
      Shape21.addBox(4F, 9F, -2F, 2, 2, 4);
      Shape21.setRotationPoint(0F, 0F, 0F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
      
      bipedBody.addChild(Shape1);
      bipedBody.addChild(Shape11);
      bipedBody.addChild(Shape2);
      bipedBody.addChild(Shape21);

  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);

  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  

}
