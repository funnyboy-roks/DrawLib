package com.funnyboyroks.drawlib.util;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Util {

    public static @NotNull Location minLocation(@NotNull Location a, @NotNull Location b) {
        if (a.getWorld() != b.getWorld()) {
            throw new IllegalArgumentException("Cannot compare locations in different worlds.");
        }
        return new Location(
            a.getWorld(),
            Math.min(a.getX(), b.getX()),
            Math.min(a.getY(), b.getY()),
            Math.min(a.getZ(), b.getZ())
        );
    }

    public static @NotNull Location maxLocation(@NotNull Location a, @NotNull Location b) {
        if (a.getWorld() != b.getWorld()) {
            throw new IllegalArgumentException("Cannot compare locations in different worlds.");
        }
        return new Location(
            a.getWorld(),
            Math.max(a.getX(), b.getX()),
            Math.max(a.getY(), b.getY()),
            Math.max(a.getZ(), b.getZ())
        );
    }

}
