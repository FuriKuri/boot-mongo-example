package net.furikuri;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.annotation.PostConstruct;

@RestController
public class PostController {

  @Value("${post.path}")
  private String fileLocation;

  private Path path;

  @PostConstruct
  public void init() throws IOException {
    path = Paths.get(this.fileLocation);
    if (!Files.exists(path)) {
      Files.createFile(path);
    }
  }

  @RequestMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
  public String post(@RequestParam(name = "msg", required = false) String msg) throws IOException {
    if (!StringUtils.isEmpty(msg)) {
      append(msg);
    }
    return read();
  }

  private void append(String msg) throws IOException {
    Files.write(path,
        (msg + "\n").getBytes(),
        StandardOpenOption.APPEND);
  }

  private String read() throws IOException {
    return new String(Files.readAllBytes(path));
  }
}
