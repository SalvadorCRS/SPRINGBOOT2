package com.eyykyoo.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyykyoo.apirest.apirest.Repositories.ProductoRepository;
import com.eyykyoo.apirest.apirest.Entities.Producto;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with ID: " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto ){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualProducto(@PathVariable long id, @RequestBody Producto detallesProducto ){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException(" product not found with ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException(" product not found with ID: " + id));

        productoRepository.save(producto);
        return "the product with id" + id + "was deleted" ;
    }

}