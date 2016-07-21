package libs;

import j8gfw.entities.Camera;
import com.sun.javafx.geom.Vec3f;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vec3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rx), new Vec3f(1, 0, 0));
        matrix.rotate((float) Math.toRadians(ry), new Vec3f(0, 1, 0));
        matrix.rotate((float) Math.toRadians(rz), new Vec3f(0, 0, 1));
        matrix.mult(new Vec3f(scale, scale, scale));

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotate((float)Math.toRadians(camera.getPitch()), new Vec3f(1, 0, 0));
        viewMatrix.rotate((float)Math.toRadians(camera.getYaw()), new Vec3f(0, 1, 0));
        Vec3f cameraPost = camera.getPosition();
        Vec3f negativeCameraPos = new Vec3f(-cameraPost.x, -cameraPost.y, -cameraPost.z);
        viewMatrix.translate(negativeCameraPos);
        return viewMatrix;
    }
}
