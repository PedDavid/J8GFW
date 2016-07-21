package j8gfw.render;


import j8gfw.Config;
import j8gfw.entities.Camera;
import j8gfw.entities.Entity;
import j8gfw.entities.Light;
import j8gfw.render.io.OBJLoader;
import j8gfw.entities.RawModel;
import j8gfw.entities.TexturedModel;
import j8gfw.inputs.KeyInput;
import j8gfw.entities.ModelTexture;
import j8gfw.render.io.Loader;
import j8gfw.terrain.Terrain;
import com.sun.javafx.geom.Vec3f;
import java.awt.event.KeyEvent;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

public class RenderLoop {

    private Config config;
    private KeyInput input;

    private Terrain terrain;
    private Loader loader;
    private ModelTexture texture;
    private TexturedModel texModel;
    private MasterRenderer render;
    private Entity entity;
    private Camera camera;
    private RawModel superModel;
    private Light light;

    public RenderLoop(Config config, KeyInput input){
        this.config = config;
        this.input = input;

        init();
    }

    private void init() {
        glEnable(GL_TEXTURE_2D);

        camera = new Camera(input);
        loader = new Loader();
        superModel = OBJLoader.loadObjModel("dragon", loader);

        render = new MasterRenderer(config);

        texture = new ModelTexture(loader.loadTexture("crate"));
        texture.setReflectivity(1);
        texture.setShineDamper(10);

        texModel = new TexturedModel(superModel, texture);

        terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("dirt")));

        entity = new Entity(texModel, new Vec3f(0, -5, -20), 0, 0, 0, 1);
        light = new Light(new Vec3f(4, 0, -15), new Vec3f(1, 1, 1));
    }

    public void display() {

        camera.move();
        render.processTerrain(terrain);
        render.processEntity(entity);
        render.render(light, camera);
        if(input.getPressed(KeyEvent.VK_A)){
            entity.increaseRotation(0, -1.0f, 0);
        }
        if(input.getPressed(KeyEvent.VK_D)){
            entity.increaseRotation(0, 1.0f, 0);
        }
    }

    public void terminate() {
        System.out.println("Terminate");
        render.cleanUp();
        loader.cleanUp();
    }
}
