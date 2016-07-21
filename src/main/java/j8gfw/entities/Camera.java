package j8gfw.entities;

import j8gfw.inputs.KeyInput;
import com.sun.javafx.geom.Vec3f;
import java.awt.event.KeyEvent;

public class Camera {

    private final KeyInput input;
    private Vec3f position = new Vec3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(KeyInput input){
        this.input = input;
    }

    public void move(){
        if(input.getPressed(KeyEvent.VK_DOWN)){
            position.y -= 0.1f;
        }
        if(input.getPressed(KeyEvent.VK_UP)){
            position.y += 0.1f;
        }
        if(input.getPressed(KeyEvent.VK_LEFT)){
            position.x -= 0.1f;
        }
        if(input.getPressed(KeyEvent.VK_RIGHT)){
            position.x += 0.1f;
        }
        if(input.getPressed(KeyEvent.VK_W)){
            position.z += 0.1f;
        }
        if(input.getPressed(KeyEvent.VK_S)){
            position.z -= 0.1f;
        }
    }

    public Vec3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
