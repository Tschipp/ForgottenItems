package tschipp.forgottenitems.containers;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import tschipp.forgottenitems.FIM;
import tschipp.forgottenitems.blocks.tileentity.TileEntityAdvancedRuneReader;

public class GuiAdvancedRuneReader extends GuiContainer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(FIM.MODID + ":textures/gui/advanced_rune_reader.png");
	
	private TileEntityAdvancedRuneReader te;
	private IInventory playerInv;
	
	public GuiAdvancedRuneReader(IInventory playerInv, TileEntityAdvancedRuneReader te) {
		super(new ContainerAdvancedRuneReader(playerInv, te));

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
        fontRenderer.drawString(s, 8, 7, 4210752);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 95 + 2, 4210752);
        renderHoveredToolTip(mouseX - this.guiLeft, mouseY - this.guiTop);

    }


}
