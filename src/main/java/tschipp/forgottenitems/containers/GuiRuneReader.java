package tschipp.forgottenitems.containers;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.blocks.tileentity.TileEntityRuneReader;

public class GuiRuneReader extends GuiContainer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(FIM.MODID + ":textures/gui/rune_reader.png");
	
	private TileEntityRuneReader te;
	private IInventory playerInv;
	
	public GuiRuneReader(IInventory playerInv, TileEntityRuneReader te) {
		super(new ContainerRuneReader(playerInv, te));

		this.xSize = 175;
		this.ySize = 221;
		
		this.te = te;
		this.playerInv = playerInv;
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		drawDefaultBackground();
		
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int marginHorizontal = (width - xSize) / 2;
        int marginVertical = (height - ySize) / 2;
        drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);
	}
	
	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = te.getDisplayName().getUnformattedText();
        fontRenderer.drawString(s, 52, 15, 4210752);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 95 + 2, 4210752);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        renderHoveredToolTip(mouseX - this.guiLeft, mouseY - this.guiTop);
    }


}
