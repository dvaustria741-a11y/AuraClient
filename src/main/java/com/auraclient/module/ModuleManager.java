package com.auraclient.module;

import com.auraclient.module.combat.KillAura;
import com.auraclient.module.combat.Reach;
import com.auraclient.module.combat.Velocity;
import com.auraclient.module.misc.AntiAFK;
import com.auraclient.module.misc.FastPlace;
import com.auraclient.module.movement.NoFall;
import com.auraclient.module.movement.Speed;
import com.auraclient.module.movement.Sprint;
import com.auraclient.module.render.ESP;
import com.auraclient.module.render.Fullbright;
import com.auraclient.module.render.Zoom;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        reg(new KillAura());
        reg(new Velocity());
        reg(new Reach());
        reg(new Sprint());
        reg(new Speed());
        reg(new NoFall());
        reg(new ESP());
        reg(new Fullbright());
        reg(new Zoom());
        reg(new AntiAFK());
        reg(new FastPlace());
    }

    private static void reg(Module m) { modules.add(m); }

    public static void onTick(MinecraftClient client) {
        for (Module m : modules) {
            if (m.isEnabled()) m.onTick(client);
        }
    }

    public static List<Module> getModules() { return modules; }

    public static List<Module> getByCategory(Module.Category cat) {
        return modules.stream().filter(m -> m.getCategory() == cat).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Module> T get(Class<T> clazz) {
        return (T) modules.stream().filter(m -> m.getClass() == clazz).findFirst().orElse(null);
    }
}
