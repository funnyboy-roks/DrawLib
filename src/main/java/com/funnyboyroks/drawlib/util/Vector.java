package com.funnyboyroks.drawlib.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Vector implements Cloneable {

    public double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector from(Location loc) {
        return new Vector(loc.getX(), loc.getY(), loc.getZ());
    }

    public double getMagSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double getMag() {
        return Math.sqrt(this.getMagSq());
    }

    public double getDot(@NotNull Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    @Contract("_ -> this")
    public @NotNull Vector mult(double n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;

        return this;
    }

    @Contract("_ -> this")
    public @NotNull Vector add(@NotNull Vector other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;

        return this;
    }

    @Contract("_ -> new")
    public @NotNull Vector getCross(@NotNull Vector other) {
        double x = this.y * other.z - this.z * other.y;
        double y = this.z * other.x - this.x * other.z;
        double z = this.x * other.y - this.y * other.x;
        return new Vector(x, y, z);
    }

    @Contract("-> new")
    public @NotNull Vector getUnit() {
        double mag = this.getMag();
        return new Vector(
            x / mag,
            y / mag,
            z / mag
        );
    }

    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z);
    }

    @Contract("-> new")
    @Override
    public Vector clone() {
        return new Vector(this.x, this.y, this.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (Double.compare(vector.x, x) != 0) return false;
        if (Double.compare(vector.y, y) != 0) return false;
        return Double.compare(vector.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
