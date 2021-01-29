package com.training.SampleApplication.controller;

import com.training.SampleApplication.controllers.BooksController;
import com.training.SampleApplication.model.Book;
import com.training.SampleApplication.services.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BooksControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BooksService booksService;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(booksController)
                .addFilter(((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }))
                .build();
    }

    private String bookName1 = "Abc";
    private String bookName2 = "Coś";
    private String booksUrl = "/books";

    @Test
    void getAllBooks() throws Exception {
        //given
        Book b1 = new Book(1,bookName1, 1, 2000);
        Book b2 = new Book(1,bookName2, 1, 1990);
        given(booksService.getAllBooks()).willReturn(List.of(b1,b2));
        //when
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get(booksUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        //then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(response.getContentAsString()).contains(bookName1,bookName2);
    }

    @Test
    void getBookById() {
    }

    @Test
    void addBook() {
    }

    @Test
    void deleteBookById() {
    }
}