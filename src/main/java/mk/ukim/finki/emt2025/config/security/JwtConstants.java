package mk.ukim.finki.emt2025.config.security;

public class JwtConstants {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static final long EXPIRATION_TIME = 2_592_000_000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
