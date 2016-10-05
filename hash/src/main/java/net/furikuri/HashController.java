package net.furikuri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HashController {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${app.url}")
  private String url;

  @RequestMapping("/")
  public String hash(HttpServletResponse response) throws NoSuchAlgorithmException {
    String body = restTemplate.getForObject(url, String.class);
    response.setHeader("x-hash", hash(body));
    return body;
  }

  private String hash(String body) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] mdbytes = md.digest(body.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte mdbyte : mdbytes) {
      sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }
}
