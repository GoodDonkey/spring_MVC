package hi.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 인풋을 utf-8 string 으로 변환

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("request-body-string-v1: OK");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 인풋을 utf-8 string 으로 변환

        log.info("messageBody = {}", messageBody);

        responseWriter.write("request-body-string-v2: OK");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String body = httpEntity.getBody();

        log.info("messageBody = {}", body);

        return new HttpEntity<>("request-body-string-v3: OK");
    }

    @PostMapping("/request-body-string-v3_2")
    public ResponseEntity<String> requestBodyStringV3_2(RequestEntity<String> httpEntity) throws IOException {

        // HttpEntity 를 상속받은 RequestEntity, ResponseEntity 를 사용할 수도 있다.
        String body = httpEntity.getBody();

        log.info("messageBody = {}", body);

        return new ResponseEntity<>("request-body-string-v3_2: OK", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody = {}", messageBody);

        return "request-body-string-v4: OK";
    }
}
