package j8gfw.entities;

public class ModelTexture {

    private int id;

    private float shineDamper = 1;
    private float reflectivity = 0;

    public ModelTexture(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}
