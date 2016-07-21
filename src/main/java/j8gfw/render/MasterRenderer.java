package j8gfw.render;

import j8gfw.Config;
import j8gfw.entities.Camera;
import j8gfw.entities.Entity;
import j8gfw.entities.Light;
import j8gfw.entities.TexturedModel;
import j8gfw.render.shaders.EntityShader;
import j8gfw.render.shaders.StaticShader;
import j8gfw.render.shaders.TerrainShader;
import j8gfw.terrain.Terrain;
import libs.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class MasterRenderer {

    private Config config;

    private Matrix4f projectionMatrix;
    private StaticShader shader;
    private TerrainShader terrainShader;

    private EntityRenderer entityRenderer;
    private TerrainRenderer terrainRenderer;

    public MasterRenderer(Config config){
        this.config = config;

        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        shader = new EntityShader();
        terrainShader = new TerrainShader();
        createProjectionMatrix();
        entityRenderer = new EntityRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
    private List<Terrain> terrains = new ArrayList<>();

    public void render(Light light, Camera camera){
        prepare();
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        entityRenderer.render(entities);
        shader.stop();
        entities.clear();

        terrainShader.start();
        terrainShader.loadLight(light);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();
        terrains.clear();
    }

    public void prepare(){
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void processEntity(Entity entity){
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch!=null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void processTerrain(Terrain terrain){
        terrains.add(terrain);
    }

    public void cleanUp(){
        shader.cleanUp();
        terrainShader.cleanUp();
    }

    private void createProjectionMatrix(){
        float aspectRatio = (float) config.getWidth() / (float) config.getHeight();
        float yScale = (float)((1f/ Math.tan(Math.toRadians(config.getFov() / 2f)))) * aspectRatio;
        float xScale = yScale / aspectRatio;
        float frustumLength = config.getFarPlane() - config.getNearPlane();

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = xScale;
        projectionMatrix.m11 = yScale;
        projectionMatrix.m22 = -((config.getFarPlane() + config.getNearPlane()) / frustumLength);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * config.getNearPlane() * config.getFarPlane()) / frustumLength);
        projectionMatrix.m33 = 0;
    }
}
