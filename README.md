# Sistema de Facturación

Este proyecto es un sistema de facturación desarrollado en Java utilizando Spring Boot, Hibernate y SQL. Permite gestionar productos, clientes e información de facturación, y establece relaciones entre estas entidades en una base de datos MySQL.

## Tecnologías Utilizadas
- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para simplificar la creación y configuración de aplicaciones.
- **Hibernate**: Framework de mapeo objeto-relacional (ORM) para manejar la persistencia de datos.
- **MySQL**: Base de datos relacional para almacenar la información de productos, clientes y facturas.
- **Maven**: Herramienta de gestión de dependencias y construcción.

## Estructura de Entidades y Relaciones
- **Clients**: Representa a los clientes del sistema de facturación.
- **Products**: Almacena los productos disponibles para la venta, incluyendo descripción, código, stock y precio.
- **Invoice**: Representa una factura generada para un cliente en una fecha específica.
- **Invoice_Details**: Detalles de cada factura, indicando los productos específicos comprados, las cantidades y el precio.

### Relaciones
- **One-to-Many**: Un cliente puede tener múltiples facturas.
- **Many-to-One**: Cada detalle de factura está asociado con un producto específico y una factura específica.
- **Many-to-Many**: A través de `Invoice_Details`, los productos se relacionan con las facturas.

## Instalación

### Requisitos
- **JDK** 11 o superior
- **MySQL** instalado y en funcionamiento
- **Maven** para la construcción del proyecto
- **IDE** como IntelliJ IDEA o Eclipse (opcional)
