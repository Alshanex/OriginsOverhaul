package net.alshanex.originsoverhaulmod.RenderTypes;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
public class MummyRenderType extends RenderType{
    private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/block/mummy.png");

    public MummyRenderType(String nameIn, VertexFormat formatIn, VertexFormat.Mode drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public static RenderType getMummyMobRenderType(float x, float y) {
        RenderStateShard.TextureStateShard textureState = new TextureStateShard(MUMMY_TEXTURE, false, false);
        RenderType.CompositeState rendertype = RenderType.CompositeState.builder().setShaderState(RenderType.RENDERTYPE_ENTITY_CUTOUT_SHADER).setTextureState(textureState).setTransparencyState(NO_TRANSPARENCY).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true);
        return create("mummy_entity_type", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, rendertype);
    }
}
