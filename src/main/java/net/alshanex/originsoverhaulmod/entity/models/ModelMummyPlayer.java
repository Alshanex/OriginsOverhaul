package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.entity.custom.Mummy;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

public class ModelMummyPlayer extends HumanoidModel<Mummy> {
    public ModelMummyPlayer(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void setupAnim(@NotNull Mummy entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
