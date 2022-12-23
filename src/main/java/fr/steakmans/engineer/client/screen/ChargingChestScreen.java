package fr.steakmans.engineer.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.blockentities.ChargingChestBlockEntity;
import fr.steakmans.engineer.common.container.ChargingChestContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.minecraftforge.server.command.TextComponentHelper;

public class ChargingChestScreen extends AbstractContainerScreen<ChargingChestContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/charging_chest.png");

    public ChargingChestScreen(ChargingChestContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 175;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(PoseStack stack, float mouseX, int mouseY, int partialTicks) {
        renderBackground(stack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        drawString(stack, font, title, this.leftPos, this.topPos + 3, 0x404040);
        drawString(stack, font, playerInventoryTitle, this.leftPos, this.topPos + 80, 0x404040);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);

        font.draw(stack, playerInventoryTitle, this.leftPos + 8, this.topPos + 74, 0x404040);
        font.draw(stack, title, this.leftPos + 8, this.topPos + 5, 0x404040);

        final int currentEnergyStored = this.menu.data.get(0);
        final int maxEnergyStorable = this.menu.data.get(1);
        final int scaled = (int) mapNumber(currentEnergyStored, 0, maxEnergyStorable, 0, 160);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, this.leftPos + 8, this.topPos + 54, 0, 166, scaled, 17);

        this.renderTooltip(stack, mouseX, mouseY);
    }

    public static double mapNumber(double value, double rangeMin, double rangeMax, double resultMin, double resultMax) {
        return (value - rangeMin) / (rangeMax - rangeMin) * (resultMax - resultMin) + resultMin;
    }

    @Override
    protected void init() {
        super.init();
        /*this.addRenderableWidget(new ExtendedButton(leftPos, topPos, 16, 16, Component.literal("yolo"), btn -> {

        }));*/
    }
}
