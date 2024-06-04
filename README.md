![](https://maven.funnyboyroks.com/api/badge/latest/snapshots/com/funnyboyroks/DrawLib?color=40c14a&name=DrawLib&prefix=v)

# DrawLib

(Name Pending to Change)

A simple library to draw some simple shapes using particles in Paper servers

![Screenshot](./img/screenshot.png)

## Usage

See the [maven repo](https://maven.funnyboyroks.com/#/snapshots/com/funnyboyroks/DrawLib/1.0.0-SNAPSHOT) for the latest version and code snippets for your `pom.xml`.

```java
ShapeRenderer renderer = new ShapeRenderer();

renderer.setColor(Color.RED);
renderer.setStepSize(0.1);
renderer.setReceivers(player);
renderer.drawLine(point1, point2);
```

## Current Shapes

- Point
- Line
- Cuboid
- Sphere