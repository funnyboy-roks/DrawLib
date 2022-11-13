package com.funnyboyroks.drawlib.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A relatively simple 3D vector class
 */
public class Vector implements Cloneable {

    public double x, y, z;

    /**
     * Create a vector from a set of coordinates
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a vector from a Bukkit {@link Location} using the x, y, and z components of the {@link Location}
     */
    public Vector(Location loc) {
        this(loc.getX(), loc.getY(), loc.getZ());
    }

    /**
     * Get the magnitude of the vector squared (Faster than {@link #getMag})
     * @return The magnitude of the vector squared
     */
    public double getMagSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    /**
     * Get the magnitude of the vector (Only use this if you can't use {@link #getMagSq} because you need the actual value)
     * @return The magnitude of the vector
     */
    public double getMag() {
        return Math.sqrt(this.getMagSq());
    }

    /**
     * Get the dot product of this vector and the other
     * @param other The vector to get the dot with
     * @return The dot product
     */
    public double getDot(@NotNull Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Multiply this vector by some value
     * @param n The value to multiply against
     * @return this
     */
    @Contract("_ -> this")
    public @NotNull Vector mult(double n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;

        return this;
    }

    /**
     * Add another vector to this vector
     * @param other The vector to add
     * @return this
     */
    @Contract("_ -> this")
    public @NotNull Vector add(@NotNull Vector other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;

        return this;
    }

    /**
     * Add values to this vector
     * @param dx Value to add to x
     * @param dy Value to add to y
     * @param dz Value to add to z
     * @return this
     */
    @Contract("_, _, _ -> this")
    public @NotNull Vector add(double dx, double dy, double dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;

        return this;
    }

    /**
     * Get the cross product of this and another vector
     * @param other The vector to cross with
     * @return The new vector representing the cross product
     */
    @Contract("_ -> new")
    public @NotNull Vector getCross(@NotNull Vector other) {
        double x = this.y * other.z - this.z * other.y;
        double y = this.z * other.x - this.x * other.z;
        double z = this.x * other.y - this.y * other.x;
        return new Vector(x, y, z);
    }

    /**
     * Get the unit vector of this vector
     * @return A new unit vector that has a magnitude of 1
     */
    @Contract("-> new")
    public @NotNull Vector getUnit() {
        double mag = this.getMag();
        return new Vector(
            x / mag,
            y / mag,
            z / mag
        );
    }

    /**
     * Convert this vector to a Bukkit {@link Location}
     * @param world The world for the {@link Location}
     * @return The {@link Location} in {@code world}
     */
    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z);
    }

    /**
     * Clone this vector
     * @return A new vector with the same x, y, and z components.
     */
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
