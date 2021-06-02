package app;

import model.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main (String[] args){
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        Product product;

        // retrieve factory object for database connection
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

        while (opcion != 5){
            System.out.println("1. Crear producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.println("Elija una opcion:");
            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Introduzca el nombre del producto: ");
                    product = new Product(); // creating Product object
                    product.setId(null);
                    sc.nextLine();
                    product.setNombre(sc.nextLine()); // setting new product name
                    System.out.println("Introduzca el precio del producto:");
                    product.setPrecio(sc.nextDouble()); // setting product price
                    System.out.println(product);
                    entity.getTransaction().begin(); // JDBC starting transaction
                    entity.persist(product); //Saving new product in the database giving product object
                   // entity.getTransaction().begin();
                    entity.persist(product);
                    entity.getTransaction().commit(); // transaction ends and saving changes
                    System.out.println("Producto registrado exitosamente.");
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Introduzca el id del producto que desea buscar:");
                    product = new Product();
                    // using entity object with find method (use 2 parameters, 1= class where object is, 2= id inserted in scanner)
                    product = entity.find(Product.class, sc.nextLong());
                    if (product != null) {
                        System.out.println(product);
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("Producto no encontrado ...");
                        // List all products creating an array using entity.createQuery
                        List<Product> productList = new ArrayList<>();
                        Query query = entity.createQuery("SELECT p FROM Producto p");
                        // getting all products mapped from the database
                        productList = query.getResultList();
                        for (Product p : productList){
                            System.out.println(p);
                        }
                        System.out.println();
                    }
                    System.out.println();

                    break;

                case 3:
                    System.out.println("Introduzca el id del producto a actualizar:");
                    product = new Product();

                    product = entity.find(Product.class, sc.nextLong());
                    if (product != null) {
                        System.out.println(product);
                        System.out.println("Introduzca nuevo nombre del producto:");
                        sc.nextLine();
                        product.setNombre(sc.nextLine());
                        System.out.println("Introduzca nuevo precio del producto:");
                        product.setPrecio(sc.nextDouble());
                        entity.getTransaction().begin(); // transaction begins
                        entity.merge(product); // giving new product values and updating
                        entity.getTransaction().commit(); // saving changes
                        System.out.println("Producto actualizado");
                        System.out.println();
                    } else {
                        System.out.println("Producto no encontrado ...");
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.println("Introduzca el id del producto a eliminar:");
                    product = new Product();

                    product = entity.find(Product.class, sc.nextLong());
                    if (product != null){
                        System.out.println(product);
                        entity.getTransaction().begin();
                        entity.remove(product); // deleting product using remove method
                        entity.getTransaction().commit();
                        System.out.println("Producto eliminado");
                    } else {
                        System.out.println("Producto no encontrado");
                    }
                    break;

                case 5:
                    entity.close(); JPAUtil.shutdown();
                break;

                default:
                    System.out.println("opcion no valida");
                break;

            }

        }

    }
}
