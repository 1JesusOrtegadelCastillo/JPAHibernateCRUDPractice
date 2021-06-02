package model;
import javax.persistence.*;

@Entity // specifies this class as an entity
@Table(name = "product") // set the table name where values are going to be saved
public class Product {
    @Id // specifies an atribute as Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set this field as AUTO INCREMENT
    private Long id;
    @Column // specifies to hibernate that a database field is called nombre and there's going to be mapped to this.
    private String nombre;
    @Column // specifies to hibernate that a database field is called precio and there's going to be mapped to this.
    private double precio;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
