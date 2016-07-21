package j8gfw;

import j8gfw.inputs.KeyInput;
import j8gfw.render.RenderLoop;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;

public class NubDevEngine {

    public static void main(String[] args) {
        new NubDevEngine();
    }

    private Config config = new Config();
    private KeyInput input = new KeyInput();

    private RenderLoop loop;
    private long window;



    public NubDevEngine(){
        try {
            init(); //TODO: init vs init Window (GLFW platform)
            loop = new RenderLoop(config, input);
            run();

            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
        } finally {

            loop.terminate();

            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    private void init() {
        GLFWErrorCallback.createPrint().set();
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(config.getWidth(), config.getHeight(), config.getTitle(), 0, 0);
        if(window == 0){
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetKeyCallback(window, (win, key, scanCode, action, mods) -> {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
                glfwSetWindowShouldClose(win, true);
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidMode.width() - config.getWidth()) / 2,
                (vidMode.height() - config.getHeight()) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        //glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        GL.createCapabilities();
    }

    private void run() { //TODO: limit to one run
        long lastTime = System.nanoTime();
        int ticksPerSecond = 60;
        double ns = 1000000000.0 / ticksPerSecond;
        double delta = 0;
        long timer = System.currentTimeMillis();

        int ticks = 0;
        int frames = 0;

        while(!glfwWindowShouldClose(window)){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;

            render();
            frames++;

            if(delta >= 1){
                tick();
                ticks++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                timer += 1000;
                System.out.println(ticks + " ticks | " + frames + " fps");
                ticks = 0;
                frames = 0;
            }
        }
    }

    private void render() {
        glfwSwapBuffers(window);
        glfwPollEvents();

        loop.display();
    }

    private void tick() {

    }
}