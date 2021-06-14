package com.tutalizm.garage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GarageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldParkReturnJSON() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/park")
                .param("plate", "34CAY229")
                .param("colour", "Lemans Blue")
                .param("vehicleType", "Car")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult).isNotNull();
    }

    @Test
    public void shouldParkReturn405() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/park")
                .param("plate", "34CAY229")
                .param("colour", "Lemans Blue")
                .param("vehicleType", "Car")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc
                .perform(request)
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

        assertThat(mvcResult).isNotNull();
    }

    @Test
    public void shouldLeaveReturnJSON() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/leave/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult).isNotNull();
    }

    @Test
    public void shouldLeaveReturn404() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/leave")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc
                .perform(request)
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(mvcResult).isNotNull();
    }

    @Test
    public void shouldStatusReturnJSON() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/status")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult).isNotNull();
    }

    @Test
    public void shouldStatusReturn404() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/statuses")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc
                .perform(request)
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(mvcResult).isNotNull();
    }

}
