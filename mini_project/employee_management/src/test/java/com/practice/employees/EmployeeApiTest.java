package com.practice.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "app.scheduling.system-running-rate-ms=3600000")
@AutoConfigureMockMvc
class EmployeeApiTest {
    @Autowired MockMvc mvc;

    @Test
    void helloIsPublic() throws Exception {
        mvc.perform(get("/api/hello")).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee Management is running"));
    }

    @Test
    void userCanReadButCannotCreate() throws Exception {
        mvc.perform(get("/api/employees").with(httpBasic("user", "User@123")))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].departmentName").exists());
        mvc.perform(post("/api/employees").with(httpBasic("user", "User@123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test\",\"email\":\"test@example.com\",\"departmentId\":1}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanCreateAndValidationIsReadable() throws Exception {
        mvc.perform(post("/api/employees").with(httpBasic("admin", "Admin@123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Le Minh Khoa\",\"email\":\"khoa@example.com\",\"departmentId\":1}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.code").value(org.hamcrest.Matchers.startsWith("EMP-")));
        mvc.perform(post("/api/employees").with(httpBasic("admin", "Admin@123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"email\":\"wrong\",\"departmentId\":null}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.detail").exists());
    }
}
