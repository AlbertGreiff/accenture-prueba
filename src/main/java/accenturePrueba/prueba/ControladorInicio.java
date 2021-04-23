package accenturePrueba.prueba;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import accenturePrueba.prueba.domain.Producto;
import lombok.extern.slf4j.Slf4j;

//mvnw.cmd spring-boot:run

@Controller
@Slf4j
public class ControladorInicio {

    ArrayList<Producto> compras = new ArrayList<>();
    ArrayList<Producto> productos = new ArrayList<>();
    int total = 0;
    boolean envio = true;
    float temporal;

    @GetMapping("/")
    public String inicio(Model model) {

        var producto = new Producto();
        producto.setNombre("producto 1");
        producto.setCodigoId("123");
        producto.setPrecio(50000);

        var producto2 = new Producto();
        producto2.setNombre("producto 2");
        producto2.setCodigoId("1234");
        producto2.setPrecio(30000);

        var producto3 = new Producto();
        producto3.setNombre("producto 3");
        producto3.setCodigoId("1235");
        producto3.setPrecio(60000);

        var producto4 = new Producto();
        producto4.setNombre("producto 4");
        producto4.setCodigoId("12346");
        producto4.setPrecio(45000);

        productos.add(producto);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);

        model.addAttribute("productos", productos);

        return "index";

    }

    @GetMapping("/agregar/{codigoId}")
    public String agregar(Producto producto, Model model) {

        boolean encontrado = false;

        for (int x = 0; x < productos.size(); x++) {
            Producto p = productos.get(x);

            if (p.getCodigoId().equals(producto.getCodigoId())) {
                encontrado = true;
                compras.add(p);
                break;
            }
        }
        if (encontrado) {
            log.info("Existe una concidencia!!");

            if (!compras.isEmpty()) {
                total = 0;
                for (Producto product : compras) {
                    total += product.getPrecio();
                }
                if (total >= 70000 & total <= 99999) {
                    temporal = 0;
                    temporal = total;
                    temporal *= 0.19;
                    total += temporal;
                    envio = true;
                    float domicilio = 7500;
                    total += domicilio;
                }
                if (total >= 100000) {
                    temporal = 0;
                    temporal = total;
                    temporal *= 0.19;
                    total += temporal;
                    envio = false;
                }

                System.out.println(temporal);
            }
        } else {
            System.out.println("sin considencia ojo!!");

        }
        System.out.println(total);

        model.addAttribute("compras", compras);
        model.addAttribute("total", total);
        model.addAttribute("temporal", temporal);
        model.addAttribute("envio", envio);
        return "compras";
    }

}
