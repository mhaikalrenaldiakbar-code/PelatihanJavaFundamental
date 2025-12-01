// package com.example.productapp.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// import com.example.productapp.entity.Product;
// import com.example.productapp.service.ProductService;

// @Controller
// public class ProductController {
    
//     @Autowired
//     private ProductService productService;
    
//     // Halaman utama - redirect ke /products
//     @GetMapping("/")
//     public String home() {
//         return "redirect:/products";
//     }
    
//     // Menampilkan semua produk
//     @GetMapping("/products")
//     public String listProducts(Model model) {
//         List<Product> products = productService.getAllProducts();
//         model.addAttribute("products", products);
//         return "products"; // file: templates/products/products.html
//     }
    
//     // Form tambah produk
//     @GetMapping("/products/add")
//     public String showAddForm(Model model) {
//         model.addAttribute("product", new Product());
//         return "/add-product"; // file: templates/products/add-product.html
//     }
    
//     // Proses tambah produk
//     @PostMapping("/products/add")
//     public String addProduct(Product product) {
//         productService.saveProduct(product);
//         return "redirect:/products";
//     }
    
//     // Form edit produk
//     @GetMapping("/products/edit/{id}")
//     public String showEditForm(@PathVariable Integer id, Model model) {
//         Product product = productService.getProductById(id);
//         model.addAttribute("product", product);
//         return "/edit-product"; // file: templates/products/edit-product.html
//     }
    
//     // Proses edit produk
//     @PostMapping("/products/edit")
//     public String updateProduct(Product product) {
//         productService.updateProduct(product);
//         return "redirect:/products";
//     }
    
//     // Hapus produk
//     @GetMapping("/products/delete/{id}")
//     public String deleteProduct(@PathVariable Integer id) {
//         productService.deleteProduct(id);
//         return "redirect:/products";
//     }
// }

package com.example.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.productapp.entity.Product;
import com.example.productapp.service.ProductService;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getAllProducts(@RequestParam(required = false) String keyword,Model model) {
        if(keyword != null && !keyword.isEmpty()) {
            List<Product> products = productService.searchProductsByName(keyword);
            model.addAttribute("products", products);
            model.addAttribute("keyword", keyword);
        } else {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
        }
        return "products";
    }
    
    // LIST PRODUCT
    // @GetMapping("/products")
    // public String listProducts(Model model) {
    //     List<Product> products = productService.getAllProducts();
    //     model.addAttribute("products", products);
    //     return "products";
    // }
    
    // FORM ADD
    @GetMapping("/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }
    
    // PROSES ADD
    @PostMapping("/products/add")
    public String addProduct(Product product, RedirectAttributes redirect) {
        productService.saveProduct(product);
        redirect.addFlashAttribute("success", "Produk berhasil ditambahkan.");
        return "redirect:/products";
    }
    
    // FORM EDIT
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }
    
    // PROSES EDIT
    @PostMapping("/products/edit")
    public String updateProduct(Product product, RedirectAttributes redirect) {
        productService.updateProduct(product);
        redirect.addFlashAttribute("success", "Produk berhasil diperbarui.");
        return "redirect:/products";
    }
    
    // DELETE
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirect) {
        productService.deleteProduct(id);
        redirect.addFlashAttribute("success", "Produk berhasil dihapus.");
        return "redirect:/products";
    }
}
