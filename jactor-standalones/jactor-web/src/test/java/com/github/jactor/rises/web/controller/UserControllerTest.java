package com.github.jactor.rises.web.controller;

import com.github.jactor.rises.client.datatype.Username;
import com.github.jactor.rises.client.domain.User;
import com.github.jactor.rises.client.facade.UserFacade;
import com.github.jactor.rises.web.JactorWeb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {JactorWeb.class})
@DisplayName("The UserController")
@PropertySource("classpath:application.properties")
class UserControllerTest {

    private MockMvc mockMvc;
    private @MockBean UserFacade userFacadeMock;
    private @Value("${spring.mvc.view.prefix}") String prefix;
    private @Value("${spring.mvc.view.suffix}") String suffix;

    @BeforeEach void mockMvcWithViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix(prefix);
        internalResourceViewResolver.setSuffix(suffix);

        mockMvc = standaloneSetup(new UserController(userFacadeMock))
                .setViewResolvers(internalResourceViewResolver)
                .build();
    }

    @DisplayName("should not fetch user by username if the username is missing from the request")
    @Test void shouldNotFetchUserByUsernameIfTheUsernameIsMissongFromTheRequest() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());

        verify(userFacadeMock, never()).find(any(Username.class));
    }

    @DisplayName("should not fetch user by username when the username is requested, but is only whitespace")
    @Test void shouldNotFetchUserByUsernameIfTheUsernameInTheRequestIsNullOrAnEmptyString() throws Exception {
        mockMvc.perform(
                get("/user").requestAttr(UserController.REQUEST_USER, " \n \t")
        ).andExpect(status().isOk());

        verify(userFacadeMock, never()).find(any(Username.class));
    }

    @DisplayName("should fetch user by username when the username is requested")
    @Test void shouldFetchTheUserIfChooseParameterExist() throws Exception {
        when(userFacadeMock.find(new Username("jactor"))).thenReturn(Optional.of(mock(User.class)));

        ModelAndView modelAndView = mockMvc.perform(
                get("/user").param(UserController.REQUEST_USER, "jactor")
        ).andExpect(status().isOk()).andReturn().getModelAndView();

        assertThat(modelAndView.getModel().get(UserController.ATTRIBUTE_USER)).isNotNull();
    }

    @DisplayName("should fetch user by username, but not find user")
    @Test void shouldFetchTheUserByUsernameButNotFindUser() throws Exception {
        when(userFacadeMock.find(any(Username.class))).thenReturn(Optional.empty());

        ModelAndView modelAndView = mockMvc.perform(
                get("/user").param(UserController.REQUEST_USER, "someone")
        ).andExpect(status().isOk()).andReturn().getModelAndView();

        assertThat(modelAndView.getModel().get(UserController.ATTRIBUTE_USER_UNKNOWN)).isEqualTo("someone");
    }
}
