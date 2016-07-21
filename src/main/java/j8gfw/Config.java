package j8gfw;

public class Config {

    //TODO: Needs a lot of changes (and why no Properties :'( )

    /* move this to xml or something */

    private final String title = "J8GFW";

    private final int aspectRatio = 16 / 9;
    private final int width = 1280;
    private final int height = width / 16 * 9;

    private final float fov = 70.0f;
    private final float nearPlane = 0.1f;
    private final float farPlane = 100.0f;

    public String getTitle() {
        return title;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getFov() {
        return fov;
    }

    public float getNearPlane() {
        return nearPlane;
    }

    public float getFarPlane() {
        return farPlane;
    }
}
