package irwing.moura.shrtner.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import irwing.moura.shrtner.model.Url;
import irwing.moura.shrtner.service.UrlService;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UrlRestController.class})
@ExtendWith(SpringExtension.class)
class UrlRestControllerTest {
    @Autowired
    private UrlRestController urlRestController;

    @MockBean
    private UrlService urlService;

    @Test
    void testRedirectToOriginalUrl() throws Exception {
        Url url = new Url();
        url.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        url.setLastTimeCalled(LocalDateTime.of(1, 1, 1, 1, 1));
        url.setShortLink("https://example.org/example");
        url.setId(123L);
        url.setOriginalLink("https://example.org/example");
        doNothing().when(this.urlService).linkAcessed((Url) any());
        when(this.urlService.getEncodedUrl((String) any())).thenReturn(url);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/{shortLink}", "Short Link");
        MockMvcBuilders.standaloneSetup(this.urlRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("https://example.org/example"));
    }
}

