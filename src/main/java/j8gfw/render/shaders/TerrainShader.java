package j8gfw.render.shaders;

public class TerrainShader extends StaticShader {

    private static final String VERTEX_FILE = "res/shaders/terrainVertexShader.txt";
    private static final String FRAGMENT_FILE = "res/shaders/terrainFragmentShader.txt";

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
}
