package io.github.pepsidawg.mapbuddy.maptools.commandbinder;

import io.github.pepsidawg.mapbuddy.utilities.triplet.Triplet;
import org.bukkit.Material;

import java.util.UUID;

public class Bindings {
    private static Bindings self;
    private static Triplet<UUID, Material, String> bindings;

    private Bindings() {
        bindings = new Triplet<>();
    }

    public static Bindings getInstance() {
        if(self == null)
            self = new Bindings();
        return self;
    }

    public Triplet getBindings() {
        return bindings;
    }
}
