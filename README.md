<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyecto Final - Estructura de Datos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }
        .center {
            text-align: center;
        }
        .red {
            color: red;
        }
        img {
            max-width: 100%;
        }
    </style>
</head>
<body>

<h1 class="red center">Proyecto Final - Estructura de Datos</h1>
<div class="center">
    <img src="https://drive.google.com/uc?id=1vktiEFvA6a9KMZhUFFxx8SAPHqPdbCZl" alt="Descripción del logo">
</div>

<div class="center">
    <p class="red">Carrera:</p>
    <p>Computación</p>

    <p class="red">Materia:</p>
    <p>Estructura de Datos</p>

    <p class="red">Nombres:</p>
    <p>José Vanegas:<br>jvanegasp1@est.ups.edu.ec</p>
    <p>Miguel Vanegas:<br>mvanegasp@est.ups.edu.ec</p>
    <p>Ricardo Romero:<br>rromeroc5@est.ups.edu.ec</p>
</div>

<h2 class="red">Descripción del problema</h2>
<p>
    El problema consiste en el desarrollo de una aplicación en Java, implementando todo lo aprendido, incluyendo el patrón MVC (Modelo, Vista, Controlador), los métodos de búsqueda, la creación de obstáculos para visualizar los métodos de búsqueda que van desde el inicio al fin, y la implementación de los tiempos de cada método.
</p>

<h2 class="red">Propuesta de solución</h2>

<h3>Marco Teórico:</h3>
<ul>
    <li>
        <strong>Dinámica:</strong> La programación dinámica es un método para resolver problemas de programación matemática que pueden dividirse en subproblemas relacionados entre sí, de forma que, resolviéndolos, podemos hallar una solución óptima para el problema original.
    </li>
    <li>
        <strong>BFS y DFS:</strong> El algoritmo BFS (Breadth-First Search o Búsqueda en Amplitud) explora los nodos vecinos del nodo actual antes de pasar a los nodos que están más lejos. A diferencia de DFS, que explora tan lejos como sea posible a lo largo de cada rama antes de retroceder.
    </li>
</ul>

<h3>Descripción de la propuesta de solución, herramientas y/o lenguajes utilizados:</h3>
<p>
    Dividimos el código en componentes más pequeños y específicos. Cada componente (Modelo, Vista, Controlador) tiene responsabilidades claramente definidas. Los métodos largos y repetitivos se dividen en métodos auxiliares para mejorar la legibilidad y mantenibilidad. Utilizamos el lenguaje Java con el IDE Eclipse. Las herramientas que usamos son:
</p>

<ul>
    <li>
        <strong>Swing:</strong> Biblioteca de Java para crear interfaces gráficas de usuario. Utilizada para diseñar la interfaz de la aplicación, con componentes como JFrame, JPanel, JButton, etc.
    </li>
    <li>
        <strong>JOptionPane:</strong> Para mostrar mensajes de diálogo y notificaciones al usuario.
    </li>
    <li>
        <strong>MVC (Modelo-Vista-Controlador):</strong> Para organizar y mejorar la implementación del código, manejando los datos con más eficiencia. 
    </li>
</ul>

<p>
    En el modelo, tenemos la creación de nuestras celdas y el recorrido de cada método de búsqueda. En la vista, implementamos los métodos para crear los botones, los paneles donde interactúa el usuario, y las celdas. En el controlador, tenemos atributos de vista y modelo que hacen funcionar los botones con eventos, la creación de celdas y el recorrido de los métodos, y un botón de resetear que borra todas las celdas.
</p>

<h2 class="red">Criterio por integrante de su propuesta</h2>

<p>
    <strong>Ricardo:</strong> Modularizar la lógica, separando las responsabilidades de la interfaz de usuario y los algoritmos de búsqueda en diferentes clases. Además, implementar patrones de diseño como MVC (Modelo-Vista-Controlador).
</p>
<p>
    <strong>José:</strong> La interfaz de usuario para que sea más intuitiva y agregar pruebas unitarias para garantizar una experiencia más robusta y amigable.
</p>
<p>
    <strong>Miguel:</strong> En la parte de los botones de inicio y fin, fue una buena idea porque en programación al ingresar los datos inician desde 0, lo cual puede ser problemático para los usuarios. Propuso usar un JTextArea para mostrar las posiciones y el tiempo que tarda en recorrer cada método, ya que se vería mejor que un cuadro de diálogo.
</p>

<h2 class="red">Capturas de la implementación final de la UI</h2>

<p>Normal:</p>
<div class="center">
    <img src="https://i.imgur.com/vqqCOhL.jpg" alt="Normal">
</div>

<p>Extra:</p>
<div class="center">
    <img src="https://i.imgur.com/7a4Nr5I.png" alt="Extra">
</div>

<h2 class="red">Conclusiones</h2>
<p>
    Para nuestras conclusiones, hicimos 4 o 5 pruebas con tiempos de cada método, donde el método BFS aproximadamente demoró 2.284 segundos, DFS demoró 1.313 segundos, cache demoró 2.394 segundos, y sin cache demoró 2.391 segundos. Sabemos que:
</p>

<ul>
    <li>BFS es generalmente el más efectivo para encontrar el camino más corto en un espacio de búsqueda sin ponderaciones y con un número razonable de nodos.</li>
    <li>DFS puede ser más rápido en encontrar una solución en espacios de búsqueda profundos, pero no garantiza el camino más corto.</li>
    <li>Los métodos dinámicos son más efectivos en espacios de búsqueda con muchas subestructuras repetitivas y cuando se requiere optimización en términos de tiempo de computación y costo del camino.</li>
</ul>

<p>
    Por lo tanto, DFS es el mejor método ya que es el más efectivo para encontrar el camino en profundidad.
</p>

<h2 class="red">Consideraciones</h2>

<p>
    <strong>Ricardo:</strong> Añadir comentarios y documentación para explicar la lógica del código puede ayudar a otros desarrolladores a entender lo que hace cada parte del código, e intentar separar la lógica de la visualización de los métodos para que cada uno tenga su propia lógica.
</p>
<p>
    <strong>Miguel:</strong> La interfaz gráfica debería ser intuitiva, responsiva y proporcionar una buena experiencia al usuario. Esto incluye la claridad de los botones, la retroalimentación visual y la facilidad de uso. Realizar pruebas de usabilidad con usuarios reales o simulados para identificar posibles mejoras en la interfaz y la interacción.
</p>
<p>
    <strong>José:</strong> La facilidad con la que el código puede ser modificado o extendido sin introducir errores. Esto incluye la claridad de los comentarios y la documentación. Revisar si el código está bien documentado y si las modificaciones futuras requieren cambios en varias partes del código o solo en áreas específicas.
</p>

</body>
</html>
