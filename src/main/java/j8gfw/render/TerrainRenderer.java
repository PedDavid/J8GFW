package j8gfw.render;

import j8gfw.render.shaders.TerrainShader;
import j8gfw.terrain.Terrain;
import libs.Maths;
import libs.Matrix4f;
import com.sun.javafx.geom.Vec3f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glDrawElements;

public class TerrainRenderer extends EntityRenderer {

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        super(shader, projectionMatrix);
    }

    public void render(List<Terrain> terrains){
        for(Terrain terrain: terrains){
            prepareTexturedModel(terrain);
            prepareInstance(terrain);
            glDrawElements(GL_TRIANGLES, terrain.getModel().getVertexCount(), GL_UNSIGNED_INT, 0);
            unbindTextureModel();
        }
    }

    protected void prepareInstance(Terrain terrain){
        Matrix4f transformationMatrix =
                Maths.createTransformationMatrix(new Vec3f(terrain.getX(), 0, terrain.getZ()), 0, 0, 0, 1);
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
