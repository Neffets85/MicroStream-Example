package org.example.controller;

import org.example.database.DB;
import org.example.database.model.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController
{
    @GetMapping("/brand/list")
    public List<Brand> getAll() {
        return DB.getRoot().getBrands();
    }

    @PostMapping("/brand/insert")
    public Brand insert(@RequestParam String name) {

        List<Brand> brands = DB.getRoot().getBrands();

        Brand brand = new Brand();
        brand.setName(name);

        brands.add(brand);

        DB.getInstance().store(brands);

        return brand;
    }

    @PutMapping("/brand/update")
    public void update(@RequestParam String oldName,@RequestParam String newName) {

        Brand brandToUpdate = DB.getRoot().getBrands()
                .stream()
                .filter(brand -> brand.getName().equals(oldName))
                .findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Brand %s Not Found", oldName)));

        brandToUpdate.setName(newName);

        DB.getInstance().store(brandToUpdate);
    }

}
