package j8gfw.render.shaders;

public class EntityShader extends StaticShader {

    private static final String VERTEX_FILE = "res/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "res/shaders/fragmentShader.txt";

    public EntityShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
}
