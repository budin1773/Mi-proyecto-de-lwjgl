import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
private long win;

public void begin() {
        glfwInit();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);                     
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

win = glfwCreateWindow(1280, 720, "title", NULL, NULL); // creamos la ventana 
glfwMakeContextCurrent(win); // creamos contexto actual en esta ventana especificamente (win)
glfwSwapInterval(0); // fotogramas por segundo (Vsync)
GL.createCapabilities(); // creamos capacidades o las habilitamos
glViewport(0, 0, 1280, 720); // el angulo de donde se crea el viewport o ventana, en este caso el mismo tmaño que la original

glEnable(GL_BLEND); //Activa el mezclado de colores
glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
}

public void clear(){
glClearColor(0.6f, 0.4f, 0.3f, 0.2f); // el color de la ventana
glClear(GL_COLOR_BUFFER_BIT); // limpiamos la ventana

}
public void swapandpoll(){
glfwSwapBuffers(win); // cargamos buffers
glfwPollEvents(); // cargamos eventos
}

public long getWin(){
return win;
}
public void End(){
glfwTerminate();
}
}


