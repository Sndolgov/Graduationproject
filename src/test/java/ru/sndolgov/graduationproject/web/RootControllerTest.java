package ru.sndolgov.graduationproject.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.sndolgov.graduationproject.TestUtil.userAuth;
import static ru.sndolgov.graduationproject.UserTestData.ADMIN;
import static ru.sndolgov.graduationproject.UserTestData.USER;


public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testRestaurants() throws Exception {
        mockMvc.perform(get("/restaurants")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(view().name("restaurants"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/restaurants.jsp"));
    }

    @Test
    public void testVoting() throws Exception {
        mockMvc.perform(get("/voting")
                .with(userAuth(USER)))
                .andDo(print())
                .andExpect(view().name("voting"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/voting.jsp"));
    }
}