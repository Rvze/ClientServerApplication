package authorizer;

import java.io.IOException;

public interface ClientAuthorizer {
    void registerClient() throws IOException;

    void authorizerClient() throws IOException;
}
