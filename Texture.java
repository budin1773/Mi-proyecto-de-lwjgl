
import static org.lwjgl.opengl.GL33.*;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture {
private int id, ancho, alto;

    public Texture(String ruta) {
        STBImage.stbi_set_flip_vertically_on_load(true);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer canales = stack.mallocInt(1);

            ByteBuffer imagen = STBImage.stbi_load(
                ruta, w, h, canales, 4
            );

            if (imagen == null) {
                throw new RuntimeException(
                    "No se pudo cargar " + ruta + ": "
                    + STBImage.stbi_failure_reason()
                );
            }

            ancho = w.get(0);
            alto = h.get(0);

System.out.println("Ancho: " + ancho + ", Alto: " + alto);
id = glGenTextures(); // reservar un espacio

glBindTexture(GL_TEXTURE_2D, id); // conectar el espacio

glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, ancho, alto, 0, GL_RGBA, GL_UNSIGNED_BYTE, imagen);
STBImage.stbi_image_free(imagen);

}
}
public void bind(){
glBindTexture(GL_TEXTURE_2D, id);
}
public void cleanUp(){
glDeleteTextures(id);

}

}
