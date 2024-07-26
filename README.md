# <span style="color: red;">Proyecto Final - Estructura de Datos</span>
![Caratula](https://drive.google.com/uc?id=1vktiEFvA6a9KMZhUFFxx8SAPHqPdbCZl)


<div style="text-align: center;">

<span style="color: red;">Carrera: </span>
</div>

<div style="text-align: center;">
 Computación
 </div>

<div style="text-align: center;">
<span style="color: red;">Materia: </span>
</div> 

<div style="text-align: center;">
Estructura de Datos
</div>

<div style="text-align: center;">
<span style="color: red;">Nombres: </span> 
</div>

<div style="text-align: center;">
José Vanegas:
		jvanegasp1@est.ups.edu.ec,
		</div>

<div style="text-align: center;">
Miguel Vanegas:  
mvanegasp@est.ups.edu.ec
		</div>
		
<div style="text-align: center;">
Ricardo Romero:
rromeroc5@est.ups.edu.ec
		</div>





# <span style="color: red;">Descripción del problema</span>

El problema consisto en el desarrollo de la aplicación en java, implementar todo lo aprendido, implementar el mvc (Modelo,Vista, Controlador), implementación de los métodos de búsqueda, en la creación de obstáculos para poder visualizar los métodos de búsqueda que va desde el inicio al fin  y la implementación de los tiempos de cada método.

Propuesta de solución
##Marco Teórico: 
*Dinámica:
La programación dinámica es un método para resolver problemas de programación matemática que puedan dividirse en subproblemas relacionados entre sí de forma que, resolviéndolos, podemos hallar una solución óptima para el problema original.
*BFS y  DFS: 
El algoritmo BFS (Breadth-First Search o Búsqueda en Amplitud) es otro algoritmo de búsqueda utilizado en grafos. A diferencia de DFS, que explora tan lejos como sea posible a lo largo de cada rama antes de retroceder, BFS explora los nodos vecinos del nodo actual antes de pasar a los nodos que están más lejos.

##Descripción de la propuesta de solución, herramientas y/o lenguajes que usuraron:

Dividir el código en componentes más pequeños y específicos. Cada componente (Modelo, Vista, Controlador) debe tener responsabilidades claramente definidas. Los métodos largos y repetitivos se deben dividir en métodos auxiliares para mejorar la legibilidad y la mantenibilidad y usamos el lenguaje de java: Eclipse.
Las Herramientas que usamos son:

*Swing: Biblioteca de Java para crear interfaces gráficas de usuario. Utilizada para diseñar la interfaz de la aplicación, con componentes como JFrame, JPanel, JButton, etc.

*JOptionPane: Para mostrar mensajes de diálogo y notificaciones al usuario.

*Propisimos crear un mvc (Modelo-Vista-Cotrolador) para poder organizar y con eso tenemos una mejor implementación de nuestro codigo y para manejar los datos con mas eficiencia, en la parte del modelo tenemos dos ,la creacion de nuestars celda y el otro como va recorrer cada metodo de busqueda , en la parte de la vista implementamos en la vista lo que seria metodos de crear los botones los paneles donde va a interactura el usuario y  que te hacer las cedas , en el controlador 
que tendremos unos atributos de vista y modelo que va a hacer funcionas los botones con events y la creación de celdas y recorrido de los método y en la parte extra esta para limpiar el recorrido y el botón resetear que hará borrar todo la celda.


#<span style="color: red;">Criterio por integrante de su propuesta </span>

Ricardo: Modularizar la lógica, separando las responsabilidades de la interfaz de usuario y los algoritmos de búsqueda en diferentes clases. Además, implementar patrones de diseño como MVC (Modelo-Vista-Controlador).

José: La interfaz de usuario para que sea más intuitiva y agregar pruebas unitarias garantizarán una experiencia más robusta y amigable.es la funcionabilidad de la interfaz.

Miguel: En la parte de los botones de inicio y fin para mi me fue una buena idea por que como en programacion al ingersear los datos inican desde 0, por lo cual la gente no lo toma encuenta y estaria con problemas al usar nuestro aplicacion. Yo tenia pensando usar un JTextArea para mostrar las posiciones y el tiemp que tarda al recorrer cada metodo ya que se veria mejor que un cuadro de dialogo ya que simepre tengo la idea que mas se usa para errores que para que salidad de informacion.

#<span style="color: red;">Capturas de la implementación final de la UI </span>

Normal:

![Normal](C:\Users/DELL\Downloads\Norma.png.jpeg)

Extra:

![Extra](C:\Users\DELL\Downloads\extra.png.jpeg)


#<span style="color: red;">Conclusiones:</span>

Para nuestras conclusiones para ver cual es mejor metodod de buqueda hicimos unos 4 o 5 prubas con tiempo de cada metodo donde el metodo BFS apreoximadamente demoro 2.284 segundos , DFS dermorro 1,313 segundos , cache demoro 2.394 segundos y sin cach demoro 2.391 segundos donde sabemos que.

BFS es generalmente el más efectivo para encontrar el camino más corto en un espacio de búsqueda sin ponderaciones y con un número razonable de nodos.

DFS puede ser más rápido en encontrar una solución en espacios de búsqueda profundos, pero no garantiza el camino más corto.
Pero el método dinámica son más efectivos en espacios de búsqueda con muchas subestructuras repetitivas y cuando se requiere optimización en términos de tiempo de computación y costo del camino.

por lo tanto el DFS es el mejor por que  es el mas efectivo para encontrar el camino en profundidad.

#<span style="color: red;">Consideraciones:</span>

Ricardo: Modularizar la lógica, separando las responsabilidades de la interfaz de usuario y los algoritmos de búsqueda en diferentes clases. Además, implementar patrones de diseño como MVC (Modelo-Vista-Controlador). Finalmente, rediseñar la interfaz de usuario para que sea más intuitiva y agregar pruebas unitarias garantizarán una experiencia más robusta y amigable.

Miguel: La interfaz gráfica debería ser intuitiva, responsiva y proporcionar una buena experiencia al usuario. Esto incluye la claridad de los botones, la retroalimentación visual y la facilidad de uso. Con la cual se realizara con pruebas e usabilidad con usuarios reales o simulados para identificar posibles mejoras en la interfaz y la interacción.

José: La facilidad con la que el código puede ser modificado o extendido sin introducir errores. Esto incluye la claridad de los comentarios y la documentación se hace realizando revisar si el código está bien documentado y si las modificaciones futuras requieren cambios en varias partes del código o solo en áreas específicas.

