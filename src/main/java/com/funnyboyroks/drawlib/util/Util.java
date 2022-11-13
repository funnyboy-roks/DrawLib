package com.funnyboyroks.drawlib.util;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Util {

    /**
     * Get the min corner of the cuboid specified by a and b
     * @param a Corner 1 of cuboid
     * @param b Corner 2 of cuboid
     * @return The min corner of the cuboid (min x, y, and z)
     */
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

    /**
     * Get the max corner of the cuboid specified by a and b
     * @param a Corner 1 of cuboid
     * @param b Corner 2 of cuboid
     * @return The max corner of the cuboid (min x, y, and z)
     */
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
