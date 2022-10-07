package org.example;

import org.example.controller.BrandController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BrandController.class)
public class BrandControllerTest
{
    @Autowired
    private MockMvc mvc;

    @Test
    public void testInsert()
    {
        try
        {
            mvc.perform(post("/brand/insert").param("name","BMW"))
                    .andExpect(status().isOk());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
