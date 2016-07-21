package j8gfw.render.shaders;

import libs.Matrix4f;
import com.sun.javafx.geom.Vec2f;
import com.sun.javafx.geom.Vec3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(4 * 4);

    public ShaderProgram(String vertexFile, String fragmentFile){

        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);

        programID = glCreateProgram();

        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        glLinkProgram(programID);
        glValidateProgram(programID);
        getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName){
        return glGetUniformLocation(programID,uniformName);
    }

    public void start(){
        glUseProgram(programID);
    }

    public void stop(){
        glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttributes(int attribute, String variableName){
        glBindAttribLocation(programID, attribute, variableName);
    }

    protected void loadFloat(int location, float value){
        glUniform1f(location, value);
    }

    protected void loadInt(int location, int value){
        glUniform1i(location, value);
    }

    protected void loadVector(int location, Vec3f vector){
        glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void load2DVector(int location, Vec2f vector){
        glUniform2f(location,vector.x,vector.y);
    }

    protected void loadBoolean(int location, boolean value){
        glUniform1f(location, value ? 1: 0);
    }

    protected void loadMatrix(int location, Matrix4f matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        glUniformMatrix4fv(location, false, matrixBuffer);
    }

    private int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource.toString());
        glCompileShader(shaderID);

        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) != GL_TRUE){
            String sType = (type == GL_VERTEX_SHADER) ? "Vertex Shader: " : "Fragment Shader: ";

            int length = glGetShaderi(shaderID, GL20.GL_INFO_LOG_LENGTH);
            String log = glGetShaderInfoLog(shaderID, length);

            System.err.println("Error compiling the " + sType  + log);
            //TODO throw exception or recover
        }

        return shaderID;
    }
}


