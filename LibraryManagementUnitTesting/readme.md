# Library Management System

## Arquitectura del Sistema

### Clases Principales

1. **[`Library`](src/main/java/Library.java)**
   - Gestiona la colección de libros y usuarios
   - Métodos sincronizados para operaciones concurrentes
   - Implementa lista de espera para libros
   - Principales métodos:
     - `synchronized checkOutBook()`: Maneja préstamos de forma segura
     - `synchronized returnBook()`: Procesa devoluciones
     - `synchronized addToWaitingList()`: Gestiona lista de espera

2. **[`Patron`](src/main/java/Patron.java)**
   - Implementa `Runnable` para operaciones concurrentes
   - Simula comportamiento de usuarios
   - Métodos principales:
     - `run()`: Simula acciones aleatorias de préstamo/devolución
     - `synchronized checkOutBook()`: Registra libros prestados
     - `synchronized returnBook()`: Maneja devoluciones

3. **[`Book`](src/main/java/Book.java)**
   - Representa libros en el sistema
   - Mantiene estado de préstamo y fechas
   - Implementa `equals()` y `hashCode()` para comparaciones

### Manejo de Concurrencia

- Métodos sincronizados para acceso seguro
- Lista de espera para libros no disponibles
- Colecciones thread-safe
- Bloqueos a nivel de método

## Tests de Concurrencia

Los tests demuestran:

1. **Préstamos Simultáneos**
```java
@Test
public void testConcurrentBookCheckout()
```
- Verifica que solo un patrón puede tomar un libro

2. **Lista de Espera**
```java
@Test
public void testWaitingListFunctionality()
```
- Prueba el sistema de lista de espera

3. **Estado de la Biblioteca**
- Monitoreo en tiempo real del estado
- Registro de operaciones

## Ejecución del Sistema

1. **Requisitos Previos**
   - Java 11 o superior
   - Maven

2. **Compilación y Tests**
```bash
mvn clean install
```

3. **Ejecución de la Simulación**
```bash
java -cp target/LibraryManagementUnitTesting-1.0-SNAPSHOT.jar LibrarySimulation
```

## Características de Multihilo

- Múltiples patrones operando simultáneamente
- Sincronización de acceso a recursos compartidos
- Sistema de lista de espera para libros populares
- Manejo seguro de estados concurrentes

## Registros y Monitoreo

El sistema registra:
- Préstamos exitosos/fallidos
- Devoluciones de libros
- Estado de la biblioteca
- Gestión de lista de espera

## Casos de Prueba

1. **Concurrencia**
   - Múltiples préstamos simultáneos
   - Devoluciones aleatorias
   - Lista de espera

2. **Manejo de Errores**
   - Préstamos de libros no disponibles
   - Devoluciones incorrectas
   - Usuarios duplicados

3. **Estado del Sistema**
   - Verificación de consistencia
   - Monitoreo de operaciones