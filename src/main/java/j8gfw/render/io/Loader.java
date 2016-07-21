package j8gfw.render.io;

import j8gfw.entities.RawModel;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;


public class Loader {

    private static ArrayList<Integer> vaos = new ArrayList<>();
    private static ArrayList<Integer> vbos = new ArrayList<>();
    private static ArrayList<Integer> textures = new ArrayList<>();


    public RawModel loadToVAO(float[] positions, float[] textureCords, float[] normals, int[] indices){
        return loadToVAO(
                FloatBuffer.wrap(positions),
                FloatBuffer.wrap(textureCords),
                FloatBuffer.wrap(normals),
                IntBuffer.wrap(indices)
        );
    }

    public RawModel loadToVAO(
            FloatBuffer positions,
            FloatBuffer textureCords,
            FloatBuffer normals,
            IntBuffer indices
    ){
        int vaoID = createVAO();

        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, textureCords);
        storeDataInAttributeList(2, 3, normals);

        glBindVertexArray(0); /* Unbind VAO */
        System.out.println("The file was now loaded to the VAO " + vaoID);
        return new RawModel(vaoID, indices.capacity());
    }

    public int loadTexture(String fileName){
//
//        try {
//            TextureData image = TextureIO.newTextureData(gl.getGLProfile(),
//                    new File("res/" + fileName + ".png"), false, ".png");
//            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
//            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
//            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, image.getWidth(), image.getHeight(),
//                    0, GL_BGR_INTEGER, GL_UNSIGNED_BYTE, image.getBuffer()); //GL_BGR <-> GL_BGR_INTEGER
//        } catch(Throwable t) {
//            t.printStackTrace();
//        }
//        glBindTexture(GL_TEXTURE_2D, 0);
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/" + fileName + ".png");
            PNGDecoder decoder = new PNGDecoder(inputStream);
            int tWidth = decoder.getWidth();
            int tHeight = decoder.getHeight();
            ByteBuffer buf = ByteBuffer.allocateDirect(4 * tWidth * tHeight);
            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.RGBA);
            buf.flip();

            int id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, tWidth, tHeight,
                    0, GL_BGR, GL_UNSIGNED_BYTE, buf); //GL_BGR <-> GL_BGR_INTEGER
            //Texture texture = TextureLoader.getTexture("PNG", inputStream);
            System.out.println("Texture " + fileName + " was loaded successfully");
            //int id = texture.getTextureID();
            textures.add(id);
            inputStream.close();
            return id;
        } catch (IOException e) {
            //TODO: throw new RuntimeException();
            return 0;
        }
    }

    private int createVAO(){
        int id = glGenVertexArrays();
        glBindVertexArray(id);

        vaos.add(id);
        return id;
    }

    public void cleanUp(){
        glDeleteVertexArrays(vaos.stream().mapToInt(Integer::intValue).toArray());
        glDeleteBuffers(vbos.stream().mapToInt(Integer::intValue).toArray());
        glDeleteTextures(textures.stream().mapToInt(Integer::intValue).toArray());
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinates, FloatBuffer data){
        int id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        vbos.add(id);

        glEnableVertexAttribArray(attributeNumber);

        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeNumber, coordinates, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0); /* Unbind Vbo */
    }

    private void bindIndicesBuffer(IntBuffer data) {
        int id = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        vbos.add(id);

        glEnableVertexAttribArray(2);

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        //gl.glVertexAttribPointer(2, 3, GL2.GL_UNSIGNED_INT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0); /* Unbind Vbo */
    }

}
