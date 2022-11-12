package com.funnyboyroks.drawlib.renderer;

import com.funnyboyroks.drawlib.util.Util;
import com.funnyboyroks.drawlib.util.Vector;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public class ShapeRenderer {

    private Color        color;
    private List<Player> receivers;

    public ShapeRenderer() {
        this.color = Color.RED;
        this.receivers = null;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param receivers The players that will receive the particles -- null for all players in world
     */
    public void setReceivers(@Nullable List<Player> receivers) {
        this.receivers = receivers;
    }

    public void drawPoint(Location point) {
        point.getWorld().spawnParticle(
            Particle.REDSTONE,
            this.receivers,
            null,
            point.getX(),
            point.getY(),
            point.getZ(),
            1,
            0,
            0,
            0,
            0,
            new Particle.DustOptions(this.color, .5f),
            false
        );
    }

    public void drawLine(Location a, Location b) {
        if (a.getWorld() != b.getWorld()) {
            throw new IllegalArgumentException("Cannot draw lines between differing worlds.");
        }

        double stepSize = .1; // metres

        Vector vec = new Vector(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());

        Vector unit = vec.getUnit();

        for (float mag = 0; mag * mag < vec.getMagSq(); mag += stepSize) {
            Location l = unit.clone().mult(mag).toLocation(a.getWorld());
            l.add(a);
            drawPoint(l);
        }

    }

    public void drawSphere(Location centre, double radius) {
        double stepSize = .1;
        for (double phi = 0; phi <= Math.PI; phi += stepSize) {
            double z = radius * Math.cos(phi);
            for (double theta = 0; theta <= 2 * Math.PI; theta += stepSize) {
                double x = radius * Math.sin(phi) * Math.cos(theta);
                double y = radius * Math.sin(phi) * Math.sin(theta);
                drawPoint(centre.clone().add(x, y, z));
            }
        }
    }

    public void drawCuboid(Location a, Location b) {
        Vector min = Vector.from(Util.minLocation(a, b));
        Vector max = Vector.from(Util.maxLocation(a, b));

        Location[] vertices = {
            min.toLocation(a.getWorld()),
            new Vector(min.x, min.y, max.z).toLocation(a.getWorld()),
            new Vector(min.x, max.y, min.z).toLocation(a.getWorld()),
            new Vector(min.x, max.y, max.z).toLocation(a.getWorld()),
            new Vector(max.x, min.y, min.z).toLocation(a.getWorld()),
            new Vector(max.x, min.y, max.z).toLocation(a.getWorld()),
            new Vector(max.x, max.y, min.z).toLocation(a.getWorld()),
            max.toLocation(a.getWorld())
        };

        drawLine(vertices[0], vertices[1]);
        drawLine(vertices[0], vertices[2]);
        drawLine(vertices[0], vertices[4]);

        drawLine(vertices[3], vertices[1]);
        drawLine(vertices[3], vertices[2]);
        drawLine(vertices[3], vertices[7]);

        drawLine(vertices[5], vertices[1]);
        drawLine(vertices[5], vertices[4]);
        drawLine(vertices[5], vertices[7]);

        drawLine(vertices[6], vertices[2]);
        drawLine(vertices[6], vertices[4]);
        drawLine(vertices[6], vertices[7]);
    }

    public void drawVector(Location point, Vector vec) {

        drawLine(point, point.clone().add(vec.toLocation(point.getWorld())));

    }

    public void drawLine(Location[] points, boolean closed) {
        if (points.length == 0) return;
        if (closed) {
            Location[] a = new Location[points.length + 1];
            System.arraycopy(points, 0, a, 0, points.length);
            a[a.length - 1] = a[0];
            points = a;
        }

        for (int i = 0; i < points.length - 1; ++i) {
            drawLine(points[i], points[i + 1]);
        }
    }
}
