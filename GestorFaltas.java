import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Un objeto de esta clase permite registrar estudiantes de un
 * curso (leyendo la información de un fichero de texto) y 
 * emitir listados con las faltas de los estudiantes, justificar faltas, 
 * anular matrícula dependiendo del nº de faltas, .....
 *
 */
public class GestorFaltas {
     private int pos;
     private Estudiante[] estudiantes;

    public GestorFaltas(int n) {
      estudiantes = new Estudiante[n];
      pos = 0;
    }

    /**
     * Devuelve true si el array de estudiantes está completo,
     * false en otro caso
     */
    public boolean cursoCompleto() {
        return pos == estudiantes.length; 
    }

    /**
     *    Añade un nuevo estudiante solo si el curso no está completo y no existe ya otro
     *    estudiante igual (con los mismos apellidos). 
     *    Si no se puede añadir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El estudiante se añade de tal forma que queda insertado en orden alfabético de apellidos
     *    (de menor a mayor)
     *    !!OJO!! No hay que ordenar ni utilizar ningún algoritmo de ordenación
     *    Hay que insertar en orden 
     *    
     */
    public void addEstudiante(Estudiante nuevo) {
        for(int i = 0; i < pos; i++){
        if(pos < estudiantes.length && buscarEstudiante(nuevo.getApellidos()) == -1 ) 
        {
            estudiantes[pos] = nuevo;
            pos++;
        
        }
        else if(pos >= estudiantes.length){
            System.out.println("Error, esta completo");
        
        
        }
        else if(buscarEstudiante(nuevo.getApellidos()) == 1){
        
            System.out.println("Estudiante repetido");
        
        }
        
        }

    }


    /**
     * buscar un estudiante por sus apellidos
     * Si está se devuelve la posición, si no está se devuelve -1
     * Es indiferente mayúsculas / minúsculas
     * Puesto que el curso está ordenado por apellido haremos la búsqueda más
     * eficiente
     *  
     */
    public int buscarEstudiante(String apellidos) {
        for(int i = 0; i < pos; i++){
            if(estudiantes[i].getApellidos().equalsIgnoreCase(apellidos)){
            
                return i;
            
            }
        
        }
        
        return -1;
        
        
        
    }

    /**
     * Representación textual del curso
     * Utiliza StringBuilder como clase de apoyo.
     *  
     */
    public String toString() {
       StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pos; i++){
            sb.append(estudiantes[i].toString());
        }
        return sb.toString();

    }

    /**
     *  Se justifican las faltas del estudiante cuyos apellidos se proporcionan
     *  El método muestra un mensaje indicando a quién se ha justificado las faltas
     *  y cuántas
     *  
     *  Se asume todo correcto (el estudiante existe y el nº de faltas a
     *  justificar también)
     */
    public void justificarFaltas(String apellidos, int faltas) {
        Estudiante justificado = estudiantes[buscarEstudiante(apellidos)];
        
        
        
        justificado.justificar(faltas);
        
        System.out.println("Nº de faltas justificadas " + faltas
        + " a "  + justificado.getApellidos() + ","
        + justificado.getNombre() + "\n") ;
    }

    /**
     * ordenar los estudiantes de mayor a menor nº de faltas injustificadas
     * si coinciden se tiene en cuenta las justificadas
     * Método de selección directa
     */
    public void ordenar() {
        

    }

    /**
     * anular la matrícula (dar de baja) a 
     * aquellos estudiantes con 30 o más faltas injustificadas
     */
    public void anularMatricula() {
         

    }

    /**
     * Lee de un fichero de texto los datos de los estudiantes
     *   con ayuda de un objeto de la  clase Scanner
     *   y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("estudiantes.txt"));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                Estudiante estudiante = new Estudiante(linea);
                this.addEstudiante(estudiante);

            }

        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

}
