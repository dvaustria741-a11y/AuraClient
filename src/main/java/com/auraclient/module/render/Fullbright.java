package com.auraclient.module.render;

import com.auraclient.module.Module;
import net.minecraft.client.MinecraftClient;

public class Fullbright extends Module {
    private double prevGamma = 1.0;

    public Fullbright() {
        super("Fullbright", "Maximizes brightness for full visibility in dark areas.", Category.RENDER);
    }

    @Override
    public void onEnable() {
        if (mc.options != null) prevGamma = mc.options.gamma.getValue();
    }

    @Override
    public void onDisable() {
        if (mc.options != null) mc.options.gamma.setValue(prevGamma);
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.options != null) client.options.gamma.setValue(16.0);
    }
}
