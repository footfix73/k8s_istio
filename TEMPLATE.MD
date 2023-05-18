# PLANTILLA PARA LOS COMMITS

````
<type>(<scope>): <subject>
<Línea en blanco>
<body>
<Línea en blanco>
<footer>
````

Escribir un header es obligatorio en los commits, pero el scope es opcional.

## Type

    Debe ser uno de los siguientes:

    - feat: una nueva funcionalidad

    - fix: una solución de un error

    - docs: cambios en la documentación

    - style: cambios que no afectan al funcionamiento del código como eliminar espacios en blando, formatear código, añadir   un nuevo salto de línea...

    - refactor: un cambio en el código que ni añade una funcionalidad ni arregla un error

    - perf: un cambio en el código que mejora el rendimiento del código

    - test: añadir nuevos tests o arreglar los ya existentes

    - chore: cambios en el proceso de compilado o en el uso de herramientas externas

## Scope
 
    - El scope se refiere a la parte del código en el que se está realizando un cambio, es decir, a qué clase o archivo afecta, si es para algún navegador específico, si es sobre una herramienta que se ha añadido, etc.

## Subject
    Esta es la parte que realmente describe lo que hemos cambiado dentro del scope. Debemos aplicar las siguientes reglas:

    Utilizar el imperativo
    No poner mayúscula en la primera letra
    No añadir un punto (.) al final de la frase
    Evitar escribir más de 50 caracteres

    Algunos ejemplos de un subject bien escrito podrían ser:

        update deployment documentation
        remove deprecated method
        merge pull request #312 from branch/name
        release version 1.2.3

    Algunos ejemplos de un subject mal escrito podrían ser:

        refactored method to improve readability: no utiliza el imperativo
        more changes to class X: tampoco utiliza el imperativo
        I have released a new version.: no utiliza imperativo y tiene punto final
## Body
    El body sirve para explicar qué cambios hemos hecho y por qué los hemos hecho. No todos los commits son lo suficientemente complejos como para tener que escribir un body y a veces basta con escribir un buen header.

## Footer
    El footer también es opcional y suele utilizarse para añadir enlaces a algún issue tracker que estés utilizando como Jira, Redmine o Clubhouse.