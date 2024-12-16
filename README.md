# Codegen Compile time generator Plugin

A Java annotation processor for generating HTTP service implementations based on annotated interfaces.

## Usage

1. Add the plugin dependency:
   ```xml
   <dependencies>
    <dependency>
        <groupId>com.generation.tools</groupId>
        <artifactId>codegen-plugin</artifactId>
        <version>1.0.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>io.clientcore</groupId>
        <artifactId>client-core</artifactId>
        <version>1.0.0</version>
    </dependency>
   </dependencies>
   ```
2. Annotate your interfaces with `@Service` and `@Endpoint` annotations:
   ```java
   @ServiceInterface(host = "https://api.example.com")
   public interface ExampleService {
     @HttpRequestInformation(
       method = HttpMethod.GET,
       path = "/users/{userId}",
       expectedStatusCodes = {200})
   User getUser(@PathParam("userId") String userId);
   }
   ```
3. Build your project and the plugin will generate an implementation of the annotated interface.
The processor would generate an implementation:
   ```java
   public class ExampleServiceImpl implements ExampleService {
    private final HttpPipeline pipeline;

    public ExampleServiceImpl(HttpPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public User getUser(String userId) {
        HttpRequest request = new HttpRequest(HttpMethod.GET, "https://api.example.com/users/" + userId);
        HttpResponse response = pipeline.send(request);
        if (response.getStatusCode() == 200) {
            return response.getBodyAs(User.class);
        }
        throw new RuntimeException("Unexpected response: " + response.getStatusCode());
    }
   }
   ```