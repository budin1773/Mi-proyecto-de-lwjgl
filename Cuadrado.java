import static org.lwjgl.opengl.GL33.*;
import org.lwjgl.stb.STBImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.MemoryStack;

public class Cuadrado {
int vao;
int vbo;
int program;
private float ancho, alto;
public float cubeX, cubeY;
private Texture textura;
private Colision colision;
private float colisionAncho;
private float colisionAlto;

private static final String VERTEX_SRC = """
 #version 330 core

layout(location = 0) in vec2 aPos;
layout(location = 1) in vec2 aUV;

out vec2 vUV;

uniform mat4 vista;
uniform mat4 proyeccion;

void main() {
    gl_Position = vec4(aPos, 0.0, 1.0);
    vUV = aUV;
}
""";

private static final String FRAGMENT_SRC = """
#version 330 core
in vec2 vUV;
out vec4 color;
uniform sampler2D miTextura;
void main() {
    color = texture(miTextura, vUV);
}
""";


public Cuadrado(Texture textura) {
this.textura = textura;
}


public void Make(float cubeX, float cubeY, float ancho, float alto){
    this.cubeX = cubeX;
    this.cubeY = cubeY;

    this.ancho = ancho;
    this.alto = alto;

    float midAncho = ancho / 1.0f;
    float midAlto = alto / 1.0f;


float[] vertices = {

 cubeX - midAncho, cubeY - midAlto, 0.0f, 0.0f,
 cubeX + midAncho, cubeY - midAlto, 1.0f, 0.0f,
 cubeX + midAncho, cubeY + midAlto, 1.0f, 1.0f,

 cubeX + midAncho,  cubeY + midAlto, 1.0f, 1.0f,
 cubeX - midAncho,  cubeY + midAlto, 0.0f, 1.0f,
 cubeX - midAncho,  cubeY - midAlto, 0.0f, 0.0f

};

vbo = glGenBuffers(); // Reserva un identificador int para vbo
vao = glGenVertexArrays(); // Reserva un identificador int para vao

// Toma las variables (vbo y vao) con sus identificadores int
glBindVertexArray(vao); 
glBindBuffer(GL_ARRAY_BUFFER, vbo);

// Reserva memoria en la GPU y copia sus vertices de la RAM a la VRAM. Los copia al vbo bindeado
glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

// Atributos o caracteristicas almacenadas en un buffer
glVertexAttribPointer(0, 2, GL_FLOAT, false, 16, 0L); // Lee X y Y
glEnableVertexAttribArray(0);

glVertexAttribPointer(1, 2, GL_FLOAT, false, 16, 8L); // Lee U y V
glEnableVertexAttribArray(1);

// Estructura y posicion geometrica
int vertexShader = glCreateShader(GL_VERTEX_SHADER);
glShaderSource(vertexShader, VERTEX_SRC);
glCompileShader(vertexShader);

// Diseño y acabado estetico 
int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
glShaderSource(fragmentShader, FRAGMENT_SRC);
glCompileShader(fragmentShader);

//Conecta java con opengl, vulkan y Opengv para crear imagenes
program = glCreateProgram(); //Crea un espacio vacio
//Los adjunta al programa
glAttachShader(program, vertexShader);
glAttachShader(program, fragmentShader);
// Los conecta para que trabajen juntos
glLinkProgram(program);
glUseProgram(program); // Le dice a OpenGL que los utilice para dibujar
int ubicacion = glGetUniformLocation(program, "miTextura");
glUniform1i(ubicacion, 0);
}

public Colision getColision(){
return colision;
}

public void draw() {
    glUseProgram(program);

    glActiveTexture(GL_TEXTURE0); //Selecciona una unidad de textura 0
    textura.bind(); //Conecta la textura con esa unidad para que OpenGL la pueda usar

    glBindVertexArray(vao); // Selecciona los datos de los vertices que va a usar
    glDrawArrays(GL_TRIANGLES, 0, 6); // Dibuja el cuadrado
}

public void actualizarPosicion() {
    float mitadAncho = ancho / 2f;
    float mitadAlto = alto / 2f;

    float[] vertices = {
        cubeX - mitadAncho, cubeY - mitadAlto, 0.0f, 0.0f,
        cubeX + mitadAncho, cubeY - mitadAlto, 1.0f, 0.0f,
        cubeX + mitadAncho, cubeY + mitadAlto, 1.0f, 1.0f,

        cubeX + mitadAncho, cubeY + mitadAlto, 1.0f, 1.0f,
        cubeX - mitadAncho, cubeY + mitadAlto, 0.0f, 1.0f,
        cubeX - mitadAncho, cubeY - mitadAlto, 0.0f, 0.0f
    };

    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

  if (colision != null) {
        colision.update(
            cubeX - colisionAncho / 2f,
            cubeY - colisionAlto / 2f
        );
}
}

public void setColision(float ancho, float alto) {
    this.colisionAncho = ancho;
    this.colisionAlto = alto;
    colision = new Colision(cubeX - colisionAncho / 2.0f, cubeY - colisionAlto / 2.0f, colisionAncho, colisionAlto); 
}

public void shouldBucle() {
    glDeleteBuffers(vbo);
    glDeleteVertexArrays(vao);
    glDeleteProgram(program);
}
}

